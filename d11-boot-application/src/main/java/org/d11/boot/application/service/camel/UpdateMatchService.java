package org.d11.boot.application.service.camel;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.api.model.InsertPlayerDTO;
import org.d11.boot.api.model.InsertPlayerResultDTO;
import org.d11.boot.api.model.UpdatePlayerDTO;
import org.d11.boot.api.model.UpdatePlayerResultDTO;
import org.d11.boot.application.model.jms.DownloadWhoscoredMatchMessage;
import org.d11.boot.application.model.jpa.Card;
import org.d11.boot.application.model.jpa.D11Match;
import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.D11TeamSeasonStat;
import org.d11.boot.application.model.jpa.Goal;
import org.d11.boot.application.model.jpa.Lineup;
import org.d11.boot.application.model.jpa.Match;
import org.d11.boot.application.model.jpa.MatchWeek;
import org.d11.boot.application.model.jpa.Player;
import org.d11.boot.application.model.jpa.PlayerMatchStat;
import org.d11.boot.application.model.jpa.PlayerSeasonStat;
import org.d11.boot.application.model.jpa.Status;
import org.d11.boot.application.model.jpa.Substitution;
import org.d11.boot.application.model.jpa.Team;
import org.d11.boot.application.model.jpa.TeamSeasonStat;
import org.d11.boot.application.model.jpa.util.PlayerMatchStatPointsCalculator;
import org.d11.boot.application.repository.D11MatchRepository;
import org.d11.boot.application.repository.D11TeamSeasonStatRepository;
import org.d11.boot.application.repository.GoalRepository;
import org.d11.boot.application.repository.MatchLogMessageRepository;
import org.d11.boot.application.repository.MatchRepository;
import org.d11.boot.application.repository.MatchWeekRepository;
import org.d11.boot.application.repository.PlayerMatchStatRepository;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.TeamRepository;
import org.d11.boot.application.repository.TeamSeasonStatRepository;
import org.d11.boot.application.service.api.PlayerAdminService;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.application.util.Parameterizer;
import org.d11.boot.parser.model.GoalData;
import org.d11.boot.parser.model.MatchData;
import org.d11.boot.parser.model.PlayerMatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for updating match data from Camel routes.
 */
@Service
@Slf4j
@SuppressWarnings({ "PMD.ExcessiveImports", "PMD.GodClass", "checkstyle:ClassFanOutComplexity" })
public class UpdateMatchService extends CamelService {

    /**
     * Unknown position id.
     */
    public static final long UNKNOWN_POSITION_ID = 6L;

    /**
     * Player admin service used to create new players and update existing player.
     */
    private final PlayerAdminService playerAdminService;
    /**
     * Points calculator for player match stats.
     */
    private final PlayerMatchStatPointsCalculator playerMatchStatPointsCalculator;

    /**
     * Creates a new service.
     *
     * @param playerAdminService              The player admin service the service will use.
     * @param playerMatchStatPointsCalculator Implementation of points calculator for the current rules.
     */
    @Autowired
    public UpdateMatchService(final PlayerAdminService playerAdminService,
                              final PlayerMatchStatPointsCalculator playerMatchStatPointsCalculator) {
        this.playerAdminService = playerAdminService;
        this.playerMatchStatPointsCalculator = playerMatchStatPointsCalculator;
    }

    /**
     * Finds the match with the provided match id and maps it to a download whoscored match message.
     *
     * @param matchId Id for the match that will be looked up.
     * @param finish  The match should or should not be finished after update.
     * @return Download whoscored match message for the match.
     */
    public DownloadWhoscoredMatchMessage getDownloadWhoscoredMatchMessage(final long matchId, final boolean finish) {
        final Match match = getRepository(MatchRepository.class).findById(matchId).orElseThrow(NotFoundException::new);
        final DownloadWhoscoredMatchMessage downloadWhoscoredMatchMessage = map(match, DownloadWhoscoredMatchMessage.class);
        downloadWhoscoredMatchMessage.setFinish(finish);
        return downloadWhoscoredMatchMessage;
    }

    /**
     * Updates results and player match stat for a match.
     *
     * @param matchData Match data that will be validated and used to update the match.
     * @param finish    Do or do not mark the match as finished after it has been updated.
     */
    @Transactional
    public void updateMatchData(final MatchData matchData, final boolean finish) {
        final UpdateMatchContext updateMatchContext = new UpdateMatchContext(matchData, finish);
        validateMatch(updateMatchContext);
        if(!updateMatchContext.hasErrors()) {
            if(matchData.isPending() || matchData.isPostponed()) {
                updateMatch(updateMatchContext, finish);
            } else {
                prepareMatchWeek(updateMatchContext);
                preparePlayers(updateMatchContext);
                preparePlayerMatchStats(updateMatchContext);

                updateMatch(updateMatchContext, finish);
                updateGoals(updateMatchContext);
                updatePlayerMatchStats(updateMatchContext);

                updateD11Matches(updateMatchContext);

                updateMatchLogMessages(updateMatchContext);
                updateMatchWeek(updateMatchContext);
            }
        }
    }

    /**
     * Validates the basic match properties.
     *
     * @param updateMatchContext Context containing the match data that will be validated.
     */
    private void validateMatch(final UpdateMatchContext updateMatchContext) {
        final MatchData matchData = updateMatchContext.getMatchData();
        try {
            final Match match = getRepository(MatchRepository.class).findById(matchData.getMatchId()).orElseThrow(NotFoundException::new);
            updateMatchContext.setMatch(match);

            if(match.getHomeTeam().getWhoscoredId() != matchData.getHomeTeamWhoscoredId()) {
                log.error(String.format("Match data home team WhoScored id %d does not match %d.",
                        matchData.getHomeTeamWhoscoredId(),
                        match.getHomeTeam().getWhoscoredId()));
            }
            if(match.getAwayTeam().getWhoscoredId() != matchData.getAwayTeamWhoscoredId()) {
                log.error(String.format("Match data away team WhoScored id %d does not match %d.",
                        matchData.getAwayTeamWhoscoredId(),
                        match.getAwayTeam().getWhoscoredId()));
            }
        } catch(NotFoundException e) {
            log.error(String.format("Match %d not found.", matchData.getMatchId()));
        }
    }

    /**
     * If the match week is pending, player match stats for all players will be created for the match week and the match
     * week status will be changed to active.
     *
     * @param updateMatchContext Context containing the match week that will be prepared.
     */
    private void prepareMatchWeek(final UpdateMatchContext updateMatchContext) {
        final MatchWeek matchWeek = updateMatchContext.getMatch().getMatchWeek();
        if(matchWeek.getStatus() == Status.PENDING) {
            log.info(String.format("Activating match week %d (%d).", matchWeek.getMatchWeekNumber(), matchWeek.getId()));

            final List<PlayerSeasonStat> playerSeasonStats =
                    getRepository(PlayerSeasonStatRepository.class).findBySeasonIdAndTeamDummy(matchWeek.getSeason().getId(), false);
            final List<PlayerMatchStat> playerMatchStats = new ArrayList<>();

            for(final PlayerSeasonStat playerSeasonStat : playerSeasonStats) {
                final Match match = matchWeek.getMatchForTeamId(playerSeasonStat.getTeam().getId());
                final PlayerMatchStat playerMatchStat = createPlayerMatchStat(playerSeasonStat, match);
                playerMatchStats.add(playerMatchStat);
            }
            getRepository(PlayerMatchStatRepository.class).saveAll(playerMatchStats);

            matchWeek.setStatus(Status.ACTIVE);
            getRepository(MatchWeekRepository.class).save(matchWeek);

            final TeamSeasonStatRepository teamSeasonStatRepository = getRepository(TeamSeasonStatRepository.class);
            final List<TeamSeasonStat> teamSeasonStats = teamSeasonStatRepository.findBySeasonIdOrderByRanking(matchWeek.getSeason().getId());
            for(final TeamSeasonStat teamSeasonStat : teamSeasonStats) {
                teamSeasonStat.setPreviousRanking(teamSeasonStat.getRanking());
            }
            teamSeasonStatRepository.saveAll(teamSeasonStats);

            final D11TeamSeasonStatRepository d11TeamSeasonStatRepository = getRepository(D11TeamSeasonStatRepository.class);
            final List<D11TeamSeasonStat> d11TeamSeasonStats = d11TeamSeasonStatRepository.findBySeasonIdOrderByRanking(matchWeek.getSeason().getId());
            for(final D11TeamSeasonStat d11TeamSeasonStat : d11TeamSeasonStats) {
                d11TeamSeasonStat.setPreviousRanking(d11TeamSeasonStat.getRanking());
            }
            d11TeamSeasonStatRepository.saveAll(d11TeamSeasonStats);
        }
    }

    /**
     * Creates and updates players and player season stats for the players in the match that will be updated.
     *
     * @param updateMatchContext Context containing the player data that will be prepared.
     */
    private void preparePlayers(final UpdateMatchContext updateMatchContext) {
        final MatchData matchData = updateMatchContext.getMatchData();
        for(final PlayerMatchData playerMatchData : matchData.getPlayers()) {
            findOrCreatePlayer(playerMatchData, updateMatchContext);
            findOrCreatePlayerSeasonStat(playerMatchData, updateMatchContext);
        }
    }

    /**
     * Tries to find and prepare the player represented by the provided player match data.
     * If a player with provided WhoScored id exists then nothing will be updated.
     * If a player with provided WhoScored id does not exist but a player with the same parameterized name
     * exists in the same team, the WhoScored id of that player will be updated.
     * Otherwise we'll interpret this as if the player is missing completely and a new player will be created.
     *
     * @param playerMatchData    Player match data with WhoScored id, name and team that will be used to look up the player.
     * @param updateMatchContext Context that will be updated with messages and results.
     */
    private void findOrCreatePlayer(final PlayerMatchData playerMatchData, final UpdateMatchContext updateMatchContext) {
        try {
            Player player = findPlayer(playerMatchData);
            if(player.getWhoscoredId() != playerMatchData.getPlayerWhoscoredId()) {
                // If we've found a player with the wrong WhoScored id but the right name in the right team, change
                // the WhoScored id and save the player.
                player.setWhoscoredId(playerMatchData.getPlayerWhoscoredId());
                player = getRepository(PlayerRepository.class).save(player);
                updateMatchContext.addInfo("Changed WhoScored id for %s (%d) to %d.", player.getName(), player.getId(), player.getWhoscoredId());
            }
            playerMatchData.setPlayerId(player.getId());
            updateMatchContext.addPlayer(player);
        } catch(NotFoundException e) {
            // We can't identify the player so create a new one.
            final InsertPlayerDTO insertPlayerDTO = map(playerMatchData, InsertPlayerDTO.class);
            insertPlayerDTO.setTeamId(Team.DUMMY_TEAM_ID);
            if(playerMatchData.getPositionId() == UNKNOWN_POSITION_ID) {
                // If this happens we need to add the position code to the Player parser class position id method.
                updateMatchContext.addError("Unknown played position %s for %s.", playerMatchData.getPlayedPosition(),
                        playerMatchData.getPlayerName());
            }
            final InsertPlayerResultDTO insertPlayerResultDTO = this.playerAdminService.insertPlayer(insertPlayerDTO);
            final PlayerSeasonStat playerSeasonStat = getRepository(PlayerSeasonStatRepository.class)
                    .findById(insertPlayerResultDTO.getPlayerSeasonStatId()).orElseThrow(NotFoundException::new);
            final Player player = playerSeasonStat.getPlayer();
            updateMatchContext.addInfo("Added new player %s (%d), position: %s.", player.getName(),
                    player.getId(),
                    playerSeasonStat.getPosition().getName());
            playerMatchData.setPlayerId(player.getId());
            updateMatchContext.addPlayer(player);
        }
    }

    /**
     * Finds a player by first looking the player up by WhoScored id. If no player is found this way, try to find one
     * by looking up a player with the same parameterized name in the same team as the provided player match data.
     *
     * @param playerMatchData Player match data with id, name and team id that will be used to find a player.
     * @return Player with either matching WhoScored id or matching parameterized name and team.
     */
    private Player findPlayer(final PlayerMatchData playerMatchData) {
        final PlayerRepository playerRepository = getRepository(PlayerRepository.class);
        return playerRepository.findByWhoscoredId(playerMatchData.getPlayerWhoscoredId()).or(() -> {
            // Get the team and look up player by name and team.
            final Team team = getRepository(TeamRepository.class).findByWhoscoredId(playerMatchData.getTeamWhoscoredId())
                    .orElseThrow(NotFoundException::new);
            final List<Player> players = playerRepository.findByTeamIdAndSeasonIdAndParameterizedName(
                    team.getId(),
                    getCurrentSeason().getId(),
                    Parameterizer.parameterize(playerMatchData.getPlayerName())
            );
            return players.isEmpty() ? Optional.empty() : Optional.of(players.get(0));
        }).orElseThrow(NotFoundException::new);
    }

    /**
     * Finds player season stats for the player and the current season.
     * If the player season stat team is not the one in the provided player match data then the player season stat will
     * be updated to the current team.
     * If no stats are found, new ones will be created and returned.
     *
     * @param playerMatchData    Player match data for which player season stats will be looked up.
     * @param updateMatchContext Context that will be updated with messages and results.
     */
    private void findOrCreatePlayerSeasonStat(final PlayerMatchData playerMatchData, final UpdateMatchContext updateMatchContext) {
        final PlayerSeasonStatRepository playerSeasonStatRepository = getRepository(PlayerSeasonStatRepository.class);
        try {
            PlayerSeasonStat playerSeasonStat = playerSeasonStatRepository.findByPlayerIdAndSeasonId(
                    playerMatchData.getPlayerId(),
                    getCurrentSeason().getId()
            ).orElseThrow(NotFoundException::new);
            // If the player belongs to another team, move him to the right one.
            if(playerSeasonStat.getTeam().getWhoscoredId() != playerMatchData.getTeamWhoscoredId()) {
                final Team team = getRepository(TeamRepository.class).findByWhoscoredId(playerMatchData.getTeamWhoscoredId())
                        .orElseThrow(NotFoundException::new);
                playerSeasonStat.setTeam(team);
                playerSeasonStat = playerSeasonStatRepository.save(playerSeasonStat);

                final Player player = playerSeasonStat.getPlayer();
                updateMatchContext.addInfo("Moved player %s (%d) to team %s (%d).", player.getName(), player.getId(), team.getName(), team.getId());
            }
            updateMatchContext.addPlayerSeasonStat(playerSeasonStat);
        } catch(NotFoundException e) {
            final UpdatePlayerDTO updatePlayerDTO = map(playerMatchData, UpdatePlayerDTO.class);
            updatePlayerDTO.setD11TeamId(D11Team.DUMMY_D11_TEAM_ID);
            updatePlayerDTO.setTeamId(updateMatchContext.getMatch().getTeamByWhoscoredId(playerMatchData.getTeamWhoscoredId()).getId());

            final UpdatePlayerResultDTO updatePlayerResultDTO = this.playerAdminService.updatePlayer(updatePlayerDTO, true);
            final PlayerSeasonStat playerSeasonStat = playerSeasonStatRepository.findById(updatePlayerResultDTO.getPlayerSeasonStatId())
                    .orElseThrow(NotFoundException::new);

            final Player player = playerSeasonStat.getPlayer();
            updateMatchContext.addInfo("Added player season stats for player %s (%d).", player.getName(), player.getId());
            updateMatchContext.addPlayerSeasonStat(playerSeasonStat);
        }
    }

    /**
     * Makes sure there's a player match stat for each player for the match being updated.
     * If no such player match stat exists, create a new one for the player and match.
     * If a new one is created, look for stats for the player for other matches the same match week.
     * If such stats exists and the player did not participate, delete the existing one.
     * If such stats exists and the player participated, reset D11 team and D11 match in the new one.
     *
     * @param updateMatchContext Context that will be updated with messages and results.
     */
    private void preparePlayerMatchStats(final UpdateMatchContext updateMatchContext) {
        final Match match = updateMatchContext.getMatch();
        final MatchData matchData = updateMatchContext.getMatchData();
        for(final PlayerMatchData playerMatchData : matchData.getPlayers()) {
            try {
                final PlayerMatchStat playerMatchStat = match.getPlayerMatchStatByPlayerId(
                        playerMatchData.getPlayerId()
                ).orElseThrow(NotFoundException::new);

                playerMatchStat.reset();
                updateMatchContext.addPlayerMatchStat(playerMatchStat);
            } catch(NotFoundException e) {
                // There's no player match stat for the player in the match so we have to create a new one.
                final PlayerSeasonStat playerSeasonStat = getRepository(PlayerSeasonStatRepository.class).findByPlayerIdAndSeasonId(
                        playerMatchData.getPlayerId(),
                        getCurrentSeason().getId()
                ).orElseThrow(NotFoundException::new);

                PlayerMatchStat playerMatchStat = createPlayerMatchStat(playerSeasonStat, match);

                // We don't want the player to play two matches for the same D11 team the same match week so check if there
                // are existing player match stats for a different match this match week.
                final List<PlayerMatchStat> existingPlayerMatchStats = getRepository(PlayerMatchStatRepository.class)
                        .findByPlayerIdAndMatchIdNotAndMatchMatchWeekId(playerMatchData.getPlayerId(), match.getId(), match.getMatchWeek().getId());
                for(final PlayerMatchStat existingPlayerMatchStat : existingPlayerMatchStats) {
                    if(existingPlayerMatchStat.getLineup().equals(Lineup.DID_NOT_PARTICIPATE)) {
                        // If the player did not participate we'll assume that the player was transferred without that being
                        // registered and delete the player match stat.
                        getRepository(PlayerMatchStatRepository.class).delete(existingPlayerMatchStat);
                        if(existingPlayerMatchStat.getD11Match() != null) {
                            existingPlayerMatchStat.getD11Match().getPlayerMatchStats().remove(existingPlayerMatchStat);
                        }
                        updateMatchContext.addInfo("Deleted DNP match stats for %s (%d), D11 team %s (%d), for match %s vs %s (%d).",
                                existingPlayerMatchStat.getPlayer().getName(),
                                existingPlayerMatchStat.getPlayer().getId(),
                                existingPlayerMatchStat.getD11Team().getName(),
                                existingPlayerMatchStat.getD11Team().getId(),
                                existingPlayerMatchStat.getMatch().getHomeTeam().getName(),
                                existingPlayerMatchStat.getMatch().getAwayTeam().getName(),
                                existingPlayerMatchStat.getMatch().getId());
                    } else {
                        // If the player did participate we'll assume that that is the match that should count for the match week
                        // and then we have make sure the stats don't count for the newly created player match stat.
                        // It's most likely that the updating match has been postponed long enough for transfers to happen.
                        playerMatchStat.setD11Team(getDummyD11Team());
                        playerMatchStat.setD11Match(null);
                        updateMatchContext.addInfo("Player %s (%d), D11 team %s (%d), already participated in match %s vs %s (%d) " +
                                        "so the stats for this game will not be counted for the owner.",
                                existingPlayerMatchStat.getPlayer().getName(),
                                existingPlayerMatchStat.getPlayer().getId(),
                                existingPlayerMatchStat.getD11Team().getName(),
                                existingPlayerMatchStat.getD11Team().getId(),
                                existingPlayerMatchStat.getMatch().getHomeTeam().getName(),
                                existingPlayerMatchStat.getMatch().getAwayTeam().getName(),
                                existingPlayerMatchStat.getMatch().getId());
                    }
                }
                playerMatchStat = getRepository(PlayerMatchStatRepository.class).save(playerMatchStat);
                match.getPlayerMatchStats().add(playerMatchStat);
                updateMatchContext.addPlayerMatchStat(playerMatchStat);
                updateMatchContext.addInfo("Created new player match stats for %s (%d).", playerMatchStat.getPlayer().getName(),
                        playerMatchStat.getPlayer().getId());
            }
        }
    }

    /**
     * Updates match datetime, status and elapsed time.
     *
     * @param updateMatchContext Context that will be updated with results.
     * @param finish             Do or do not finish the match after the update.
     */
    private void updateMatch(final UpdateMatchContext updateMatchContext, final boolean finish) {
        final Match match = updateMatchContext.getMatch();
        final MatchData matchData = updateMatchContext.getMatchData();

        final String fullTimeElapsed = "FT";
        match.setDatetime(matchData.getDatetime());
        if(finish) {
            match.setStatus(Status.FINISHED);
            match.setElapsed(fullTimeElapsed);
        } else if(matchData.isActive()) {
            match.setStatus(Status.ACTIVE);
            match.setElapsed(matchData.getElapsed());
        } else if(matchData.isFullTime()) {
            match.setStatus(Status.FULL_TIME);
            match.setElapsed(fullTimeElapsed);
        } else if(matchData.isPostponed()) {
            match.setStatus(Status.POSTPONED);
            match.setElapsed("PP");
        } else {
            match.setStatus(Status.PENDING);
            match.setElapsed("N/A");
        }
        updateMatchContext.setMatch(getRepository(MatchRepository.class).save(match));
    }

    /**
     * Deletes all existing goals for the match and creates new ones from the match data in the context.
     *
     * @param updateMatchContext Context containing the goal data that will be prepared.
     */
    private void updateGoals(final UpdateMatchContext updateMatchContext) {
        final Match match = updateMatchContext.getMatch();
        final GoalRepository goalRepository = getRepository(GoalRepository.class);

        goalRepository.deleteAll(match.getGoals());
        match.reset();

        final List<Goal> goals = new ArrayList<>();
        for(final GoalData goalData : updateMatchContext.getMatchData().getGoals()) {
            final Goal goal = new Goal();
            goal.setMatch(match);
            goal.setPlayer(updateMatchContext.getPlayer(goalData.getPlayerWhoscoredId()));
            goal.setTeam(match.getTeamByWhoscoredId(goalData.getTeamWhoscoredId()));
            goal.setTime(Math.min(goalData.getTime(), Goal.MAX_MATCH_EVENT_TIME));
            goal.setAddedTime(0);
            goal.setPenalty(goalData.isPenalty());
            goal.setOwnGoal(goalData.isOwnGoal());
            goals.add(goal);

            if(goal.getTeam().equals(match.getHomeTeam())) {
                match.setHomeTeamGoals(match.getHomeTeamGoals() + 1);
            } else {
                match.setAwayTeamGoals(match.getAwayTeamGoals() + 1);
            }
        }
        goalRepository.saveAll(goals);
        updateMatchContext.setMatch(getRepository(MatchRepository.class).save(match));
    }

    /**
     * Updates all player match stats for the match.
     *
     * @param updateMatchContext Context containing the player match data that will be updated.
     */
    private void updatePlayerMatchStats(final UpdateMatchContext updateMatchContext) {
        final Match match = updateMatchContext.getMatch();
        final List<PlayerMatchStat> playerMatchStats = match.getPlayerMatchStats();
        playerMatchStats.forEach(PlayerMatchStat::reset);

        for(final PlayerMatchData playerMatchData : updateMatchContext.getMatchData().getPlayers()) {
            final PlayerMatchStat playerMatchStat = match.getPlayerMatchStatByPlayerId(playerMatchData.getPlayerId()).orElseThrow(NotFoundException::new);

            // Need to set team in case there's a player who's moved from one team in the match to the other since we
            // will not create a new player match stat for the player if he's still playing in the same match.
            playerMatchStat.setTeam(match.getTeamByWhoscoredId(playerMatchData.getTeamWhoscoredId()));
            playerMatchStat.setPlayedPosition(playerMatchData.getPlayedPosition());
            playerMatchStat.setLineup(playerMatchData.isStartingLineup() ? Lineup.STARTING_LINEUP : Lineup.SUBSTITUTE);
            playerMatchStat.setSubstitutionOnTime(Math.min(playerMatchData.getSubstitutionOnTime(), Substitution.MAX_MATCH_EVENT_TIME));
            playerMatchStat.setSubstitutionOffTime(Math.min(playerMatchData.getSubstitutionOffTime(), Substitution.MAX_MATCH_EVENT_TIME));
            playerMatchStat.setGoals(playerMatchData.getGoals());
            playerMatchStat.setGoalAssists(playerMatchData.getGoalAssists());
            playerMatchStat.setOwnGoals(playerMatchData.getOwnGoals());
            playerMatchStat.setGoalsConceded(playerMatchData.getGoalsConceded());
            playerMatchStat.setYellowCardTime(Math.min(playerMatchData.getYellowCardTime(), Card.MAX_MATCH_EVENT_TIME));
            playerMatchStat.setRedCardTime(Math.min(playerMatchData.getRedCardTime(), Card.MAX_MATCH_EVENT_TIME));
            playerMatchStat.setManOfTheMatch(playerMatchData.getManOfTheMatch());
            playerMatchStat.setSharedManOfTheMatch(playerMatchData.getSharedManOfTheMatch());
            playerMatchStat.setRating(playerMatchData.getRating());

            playerMatchStat.setPoints(this.playerMatchStatPointsCalculator.calculatePoints(playerMatchStat));
        }

        getRepository(PlayerMatchStatRepository.class).saveAll(playerMatchStats);
    }

    /**
     * Updates D11 matches that belong to the match week that the match that is being updated belongs to.
     *
     * @param updateMatchContext Context containing data that will be updated.
     */
    private void updateD11Matches(final UpdateMatchContext updateMatchContext) {
        final D11MatchRepository d11MatchRepository = getRepository(D11MatchRepository.class);
        final List<D11Match> d11Matches = d11MatchRepository.findByMatchWeekId(updateMatchContext.getMatch().getMatchWeek().getId());
        for(final D11Match d11Match : d11Matches) {
            d11Match.update();
        }
        d11MatchRepository.saveAll(d11Matches);
    }

    /**
     * Saves match log messages produced by the update.
     *
     * @param updateMatchContext Context containing match log messages that will be saved.
     */
    private void updateMatchLogMessages(final UpdateMatchContext updateMatchContext) {
        getRepository(MatchLogMessageRepository.class).saveAll(updateMatchContext.getMatchLogMessages());
    }

    /**
     * Updates Premier League leader, D11 league leader, most valuable player, elapsed time and status for the match week.
     *
     * @param updateMatchContext Context containing data that will be updated.
     */
    private void updateMatchWeek(final UpdateMatchContext updateMatchContext) {
        final MatchWeek matchWeek = updateMatchContext.getMatch().getMatchWeek();

        int startedCount = 0;
        int fullTimeCount = 0;
        int finishedCount = 0;

        final List<Match> matches = matchWeek.getMatches();
        for(final Match match : matchWeek.getMatches()) {
            if(match.isStarted()) {
                ++startedCount;
            }
            if(match.getStatus().equals(Status.FULL_TIME)) {
                ++fullTimeCount;
            } else if(match.getStatus().equals(Status.FINISHED)) {
                ++finishedCount;
            }
        }

        matchWeek.setElapsed(startedCount);
        if(finishedCount == matches.size()) {
            matchWeek.setStatus(Status.FINISHED);
        } else if(fullTimeCount + finishedCount == matches.size()) {
            matchWeek.setStatus(Status.FULL_TIME);
        }
        getRepository(MatchWeekRepository.class).save(matchWeek);
    }

    /**
     * Creates a player match stat from a player season stat and a match.
     *
     * @param playerSeasonStat Player season stats that provide player, team, D11 team and position.
     * @param match            The match the player match stat will be fore.
     * @return Player match stat for the player and the match.
     */
    private PlayerMatchStat createPlayerMatchStat(final PlayerSeasonStat playerSeasonStat, final Match match) {
        final D11Match d11Match = playerSeasonStat.getD11Team().isDummy()
                ? null
                : match.getMatchWeek().getD11MatchForTeamId(playerSeasonStat.getD11Team().getId());

        final PlayerMatchStat playerMatchStat = new PlayerMatchStat();
        playerMatchStat.setPlayer(playerSeasonStat.getPlayer());
        playerMatchStat.setMatch(match);
        playerMatchStat.setD11Match(d11Match);
        playerMatchStat.setTeam(playerSeasonStat.getTeam());
        playerMatchStat.setD11Team(playerSeasonStat.getD11Team());
        playerMatchStat.setPosition(playerSeasonStat.getPosition());
        playerMatchStat.setLineup(Lineup.DID_NOT_PARTICIPATE);
        playerMatchStat.setPlayedPosition("NA");
        playerMatchStat.reset();
        return playerMatchStat;
    }

}
