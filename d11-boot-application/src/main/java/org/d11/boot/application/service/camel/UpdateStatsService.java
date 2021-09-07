package org.d11.boot.application.service.camel;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.application.model.jpa.D11Match;
import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.D11TeamMatchWeekStat;
import org.d11.boot.application.model.jpa.D11TeamSeasonStat;
import org.d11.boot.application.model.jpa.Match;
import org.d11.boot.application.model.jpa.MatchWeek;
import org.d11.boot.application.model.jpa.PlayerMatchStat;
import org.d11.boot.application.model.jpa.PlayerSeasonStat;
import org.d11.boot.application.model.jpa.Season;
import org.d11.boot.application.model.jpa.Team;
import org.d11.boot.application.model.jpa.TeamMatchWeekStat;
import org.d11.boot.application.model.jpa.TeamSeasonStat;
import org.d11.boot.application.repository.D11MatchRepository;
import org.d11.boot.application.repository.D11TeamMatchWeekStatRepository;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.repository.D11TeamSeasonStatRepository;
import org.d11.boot.application.repository.MatchRepository;
import org.d11.boot.application.repository.MatchWeekRepository;
import org.d11.boot.application.repository.PlayerMatchStatRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.TeamMatchWeekStatRepository;
import org.d11.boot.application.repository.TeamSeasonStatRepository;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.parser.model.MatchData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for updating various stats after a match has been updated.
 */
@Service
@Slf4j
@SuppressWarnings("PMD.ExcessiveImports")
public class UpdateStatsService extends CamelService {

    /**
     * Updates player season stats, team season and match week stats and D11 team season and match week stats for
     * players, teams and D11 teams affected by a match update.
     *
     * @param matchData Match data with the match for which stats will be updated.
     */
    @Transactional
    public void updateStats(final MatchData matchData) {
        log.debug("Updating player season stats.");
        updatePlayerSeasonStats(matchData);
        log.debug("Updating team match week stats.");
        updateTeamMatchWeekStats(matchData);
        log.debug("Updating team season stats.");
        updateTeamSeasonStats(matchData);
        log.debug("Updating D11 team match week stats.");
        updateD11TeamMatchWeekStats(matchData);
        log.debug("Updating D11 team season stats.");
        updateD11TeamSeasonStats(matchData);
        log.debug("Updating match week winners.");
        updateMatchWeekWinners(matchData);
    }

    /**
     * Updates player season stats for players with player match stats belonging to a match. Updates rankings for all
     * player season stats for the season.
     *
     * @param matchData Match data with the match id for which player season stats will be updated.
     */
    private void updatePlayerSeasonStats(final MatchData matchData) {
        final Match match = getRepository(MatchRepository.class).findById(matchData.getMatchId()).orElseThrow(NotFoundException::new);
        final PlayerSeasonStatRepository playerSeasonStatRepository = getRepository(PlayerSeasonStatRepository.class);

        final List<PlayerSeasonStat> playerSeasonStats = playerSeasonStatRepository.findByMatchId(match.getId());
        for(final PlayerSeasonStat playerSeasonStat : playerSeasonStats) {
            final List<PlayerMatchStat> playerMatchStats = getRepository(PlayerMatchStatRepository.class)
                    .findByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime(
                            playerSeasonStat.getPlayer().getId(),
                            playerSeasonStat.getSeason().getId()
                    );
            playerSeasonStat.updateStats(playerMatchStats);
        }
        playerSeasonStatRepository.saveAll(playerSeasonStats);
        playerSeasonStatRepository.updateRankingsBySeasonId(match.getMatchWeek().getSeason().getId());
    }

    /**
     * Updates team match week stats for the teams playing a match. Updates rankings for all team match week stats for
     * all match weeks for the season.
     *
     * @param matchData Match data with team info.
     */
    private void updateTeamMatchWeekStats(final MatchData matchData) {
        final Match match = getRepository(MatchRepository.class).findById(matchData.getMatchId()).orElseThrow(NotFoundException::new);

        final List<TeamMatchWeekStat> teamMatchWeekStats = updateTeamMatchWeekStats(match.getHomeTeam(), match.getMatchWeek().getSeason());
        teamMatchWeekStats.addAll(updateTeamMatchWeekStats(match.getAwayTeam(), match.getMatchWeek().getSeason()));

        final TeamMatchWeekStatRepository teamMatchWeekStatRepository = getRepository(TeamMatchWeekStatRepository.class);
        teamMatchWeekStatRepository.saveAll(teamMatchWeekStats);

        for(final MatchWeek matchWeek : match.getMatchWeek().getSeason().getMatchWeeks()) {
            teamMatchWeekStatRepository.updateRankingsByMatchWeekId(matchWeek.getId());
        }
        teamMatchWeekStatRepository.updatePreviousRankingsBySeasonId(match.getMatchWeek().getSeason().getId());
    }

    /**
     * Updates team match week stats for a team and a season.
     *
     * @param team   The team for which team match week stats will be updated.
     * @param season The season for which team match week stats will be updated.
     * @return List of team match week stats that were updated.
     */
    private List<TeamMatchWeekStat> updateTeamMatchWeekStats(final Team team, final Season season) {
        final TeamMatchWeekStatRepository teamMatchWeekStatRepository = getRepository(TeamMatchWeekStatRepository.class);
        final List<TeamMatchWeekStat> teamMatchWeekStats =
                teamMatchWeekStatRepository.findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(team.getId(), season.getId());

        for(int i = 0; i < teamMatchWeekStats.size(); ++i) {
            final TeamMatchWeekStat teamMatchWeekStat = teamMatchWeekStats.get(i);
            teamMatchWeekStat.updateStats();
            if(i > 0) {
                teamMatchWeekStat.updateCumulativeStats(teamMatchWeekStats.get(i - 1));
            }
        }

        return teamMatchWeekStats;
    }

    /**
     * Updates team season stats for the teams in a match that's been updated.
     *
     * @param matchData Match data with the teams for which team season stats will be updated.
     */
    private void updateTeamSeasonStats(final MatchData matchData) {
        final Match match = getRepository(MatchRepository.class).findById(matchData.getMatchId()).orElseThrow(NotFoundException::new);
        updateTeamSeasonStat(match.getHomeTeam(), match.getMatchWeek().getSeason());
        updateTeamSeasonStat(match.getAwayTeam(), match.getMatchWeek().getSeason());
        getRepository(TeamSeasonStatRepository.class).updateRankingsBySeasonId(match.getMatchWeek().getSeason().getId());
    }

    /**
     * Updates team season stats for a team and a season.
     *
     * @param team   The team for which team season stats will be updated.
     * @param season The season for which team season stats will be updated.
     */
    private void updateTeamSeasonStat(final Team team, final Season season) {
        final TeamSeasonStat teamSeasonStat = getRepository(TeamSeasonStatRepository.class).findByTeamIdAndSeasonId(
                team.getId(),
                season.getId()
        ).orElseThrow(NotFoundException::new);
        final List<TeamMatchWeekStat> teamMatchWeekStats = getRepository(TeamMatchWeekStatRepository.class)
                .findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(team.getId(), season.getId());

        final int previousRanking = teamSeasonStat.getPreviousRanking();
        teamSeasonStat.reset();
        teamSeasonStat.updateCumulativeStats(teamMatchWeekStats.get(teamMatchWeekStats.size() - 1));
        teamSeasonStat.setPreviousRanking(previousRanking);

        final List<Match> matches = getRepository(MatchRepository.class)
                .findByTeamIdAndMatchWeekSeasonId(team.getId(), season.getId()).stream()
                .filter(Match::isStarted)
                .collect(Collectors.toList());

        final int toIndex = matches.size();
        final int fromIndex = Math.max(0, toIndex - 5);
        final List<Match> formMatches = matches.subList(fromIndex, toIndex);

        for(final Match match : formMatches) {
            teamSeasonStat.getFormMatchPoints().add(match.getPoints(team));
            teamSeasonStat.setFormPoints(teamSeasonStat.getFormPoints() + match.getPoints(team));
        }

        getRepository(TeamSeasonStatRepository.class).save(teamSeasonStat);
    }

    /**
     * Updates team match week stats for the teams playing a match. Updates rankings for all team match week stats for
     * all match weeks for the season.
     *
     * @param matchData Match data with team info.
     */
    private void updateD11TeamMatchWeekStats(final MatchData matchData) {
        final Match match = getRepository(MatchRepository.class).findById(matchData.getMatchId()).orElseThrow(NotFoundException::new);
        final Season season = match.getMatchWeek().getSeason();
        final List<D11Team> d11Teams = getRepository(D11TeamRepository.class).findByD11TeamSeasonStatSeasonIdOrderByName(season.getId());
        final List<D11TeamMatchWeekStat> d11TeamMatchWeekStats = new ArrayList<>();

        for(final D11Team d11Team : d11Teams) {
            d11TeamMatchWeekStats.addAll(updateD11TeamMatchWeekStats(d11Team, season));
        }

        final D11TeamMatchWeekStatRepository d11TeamMatchWeekStatRepository = getRepository(D11TeamMatchWeekStatRepository.class);
        d11TeamMatchWeekStatRepository.saveAll(d11TeamMatchWeekStats);

        for(final MatchWeek matchWeek : match.getMatchWeek().getSeason().getMatchWeeks()) {
            d11TeamMatchWeekStatRepository.updateRankingsByMatchWeekId(matchWeek.getId());
        }
        d11TeamMatchWeekStatRepository.updatePreviousRankingsBySeasonId(match.getMatchWeek().getSeason().getId());
    }

    /**
     * Updates D11 team match week stats for a D11 team and a season.
     *
     * @param d11Team The D11 team for which D11 team match week stats will be updated.
     * @param season  The season for which D11 team match week stats will be updated.
     * @return List of D11 team match week stats that were updated.
     */
    private List<D11TeamMatchWeekStat> updateD11TeamMatchWeekStats(final D11Team d11Team, final Season season) {
        final D11TeamMatchWeekStatRepository d11TeamMatchWeekStatRepository = getRepository(D11TeamMatchWeekStatRepository.class);
        final List<D11TeamMatchWeekStat> d11TeamMatchWeekStats =
                d11TeamMatchWeekStatRepository.findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(d11Team.getId(), season.getId());

        for(int i = 0; i < d11TeamMatchWeekStats.size(); ++i) {
            final D11TeamMatchWeekStat d11TeamMatchWeekStat = d11TeamMatchWeekStats.get(i);
            d11TeamMatchWeekStat.updateStats();
            if(i > 0) {
                d11TeamMatchWeekStat.updateCumulativeStats(d11TeamMatchWeekStats.get(i - 1));
            }
        }

        return d11TeamMatchWeekStats;
    }

    /**
     * Updates D11 team season stats for the D11 teams participating in the season of a match that's been updated.
     *
     * @param matchData Match data with the match for which D11 team season stats will be updated.
     */
    private void updateD11TeamSeasonStats(final MatchData matchData) {
        final Match match = getRepository(MatchRepository.class).findById(matchData.getMatchId()).orElseThrow(NotFoundException::new);
        final Season season = match.getMatchWeek().getSeason();
        final List<D11Team> d11Teams = getRepository(D11TeamRepository.class).findByD11TeamSeasonStatSeasonIdOrderByName(season.getId());

        for(final D11Team d11Team : d11Teams) {
            updateD11TeamSeasonStat(d11Team, season);
        }
        getRepository(D11TeamSeasonStatRepository.class).updateRankingsBySeasonId(match.getMatchWeek().getSeason().getId());
    }

    /**
     * Updates D11 team season stats for a D11 team and a season.
     *
     * @param d11Team The D11 team for which D11 team season stats will be updated.
     * @param season  The season for which D11 team season stats will be updated.
     */
    private void updateD11TeamSeasonStat(final D11Team d11Team, final Season season) {
        final D11TeamSeasonStat d11TeamSeasonStat = getRepository(D11TeamSeasonStatRepository.class).findByD11TeamIdAndSeasonId(
                d11Team.getId(),
                season.getId()
        ).orElseThrow(NotFoundException::new);
        final List<D11TeamMatchWeekStat> d11TeamMatchWeekStats = getRepository(D11TeamMatchWeekStatRepository.class)
                .findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(d11Team.getId(), season.getId());

        final int previousRanking = d11TeamSeasonStat.getPreviousRanking();
        d11TeamSeasonStat.reset();
        d11TeamSeasonStat.updateCumulativeStats(d11TeamMatchWeekStats.get(d11TeamMatchWeekStats.size() - 1));
        d11TeamSeasonStat.setPreviousRanking(previousRanking);

        final List<D11Match> d11Matches = getRepository(D11MatchRepository.class)
                .findByD11TeamIdAndMatchWeekSeasonId(d11Team.getId(), season.getId()).stream()
                .filter(D11Match::isStarted)
                .collect(Collectors.toList());

        final int toIndex = d11Matches.size();
        final int fromIndex = Math.max(0, toIndex - 5);
        final List<D11Match> formD11Matches = d11Matches.subList(fromIndex, toIndex);

        for(final D11Match d11Match : formD11Matches) {
            d11TeamSeasonStat.getFormMatchPoints().add(d11Match.getPoints(d11Team));
            d11TeamSeasonStat.setFormPoints(d11TeamSeasonStat.getFormPoints() + d11Match.getPoints(d11Team));
        }

        getRepository(D11TeamSeasonStatRepository.class).save(d11TeamSeasonStat);
    }

    /**
     * Updates Premier League leader, D11 league leader and most valuable player for a match week.
     *
     * @param matchData Match data for the match whose match week will be updated.
     */
    private void updateMatchWeekWinners(final MatchData matchData) {
        final Match match = getRepository(MatchRepository.class).findById(matchData.getMatchId()).orElseThrow(NotFoundException::new);

        final MatchWeekRepository matchWeekRepository = getRepository(MatchWeekRepository.class);

        matchWeekRepository.updatePremierLeagueLeaderByMatchWeekId(match.getMatchWeek().getId());
        matchWeekRepository.updateD11LeagueLeaderByMatchWeekId(match.getMatchWeek().getId());
        matchWeekRepository.updateMostValuablePlayerByMatchWeekId(match.getMatchWeek().getId());
    }

}
