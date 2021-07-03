package org.d11.boot.application.api;

import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.api.model.MatchesByDateDTO;
import org.d11.boot.api.service.MatchApiService;
import org.d11.boot.application.model.Goal;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.repository.MatchRepository;
import org.d11.boot.application.service.MatchWeekService;
import org.d11.boot.application.util.MatchesByDateMapperConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Match API tests.
 */
public class MatchApiTests extends AbstractRepositoryApiTests<Match, MatchRepository, MatchApiService> {

    /**
     * Used to get current match week for findCurrentMatches test.
     */
    @Autowired
    private MatchWeekService matchWeekService;

    /**
     * Tests the findMatchById API operation.
     */
    @Test
    public void findMatchById() {
        for(final Match match : getRepository().findAll()) {
            final MatchDTO result = getApiService().findMatchById(match.getId());
            final MatchDTO matchDTO = map(match, MatchDTO.class);

            assertNotNull(result, "Match by id should not be null.");
            assertEquals(matchDTO, result, "Match by id should equal Match.");
            assertFalse(match.getGoals().isEmpty(), "Match by id goals should not be empty.");
            assertEquals(match.getGoals().size(), matchDTO.getGoals().size(), "Match by id goals size should equal match goal size.");
            for (int i = 0; i < match.getGoals().size(); ++i) {
                final Goal goal = match.getGoals().get(i);
                final Goal mappedGoal = map(result.getGoals().get(i), Goal.class);
                assertEquals(goal, mappedGoal, "Match by id goal should equal match goal.");
            }
        }

        assertNull(getApiService().findMatchById(-1L), "Match not found should return null.");
        assertBadRequest(get("matches", "BAD_REQUEST"));
    }

    /**
     * Tests match goal order.
     */
    @Test
    public void goalOrder() {
        for(final Match match : getRepository().findAll()) {
            assertFalse(match.getGoals().isEmpty(), "Match goals should not be empty.");

            final List<Goal> goals = new ArrayList<>(match.getGoals());
            goals.sort(Comparator.comparing(Goal::getTime).thenComparing(Goal::getAddedTime));

            assertEquals(goals, match.getGoals(), "Goal order should be by time and added time, ascending.");
        }
    }

    /**
     * Tests the findMatchByTeamIdAndSeasonId API operation.
     */
    @Test
    public void findMatchByTeamIdAndSeasonId() {
        final Map<Team, Map<Season, List<Match>>> matchMap = new HashMap<>();
        for(final Match match : getEntities()) {
            Map<Season, List<Match>> seasonMap = matchMap.computeIfAbsent(match.getHomeTeam(), t -> new HashMap<>());
            List<Match> matches = seasonMap.computeIfAbsent(match.getMatchWeek().getSeason(), s -> new ArrayList<>());
            matches.add(match);
            seasonMap = matchMap.computeIfAbsent(match.getAwayTeam(), t -> new HashMap<>());
            matches = seasonMap.computeIfAbsent(match.getMatchWeek().getSeason(), s -> new ArrayList<>());
            matches.add(match);
        }

        for(final Map.Entry<Team, Map<Season, List<Match>>> teamEntry : matchMap.entrySet()) {
            final Team team = teamEntry.getKey();
            for(final Map.Entry<Season, List<Match>> seasonEntry : teamEntry.getValue().entrySet()) {
                final Season season = seasonEntry.getKey();
                final List<Long> matchIds = seasonEntry.getValue().stream()
                        .sorted(Comparator.comparing(Match::getDatetime))
                        .map(Match::getId)
                        .collect(Collectors.toList());

                final List<Long> result = getApiService().findMatchByTeamIdAndSeasonId(team.getId(), season.getId());

                assertNotNull(result, "Result should not be null.");
                assertEquals(matchIds.size(), result.size(),
                        "Matches by team id and season id should have the same size as team matches by season.");
                assertEquals(matchIds, result,
                        "Matches by team id and season id should equal team matches by season.");
            }
        }
    }

    /**
     * Test the findCurrentMatches API operation.
     */
    @Test
    public void findCurrentMatches() {
        final List<Match> matches = new ArrayList<>();
        final MatchWeekDTO matchWeekDTO = this.matchWeekService.findCurrentMatchWeek();
        for(final Match match : getEntities()) {
            if(match.getMatchWeek().getId().equals(matchWeekDTO.getId())
                || match.getStatus().equals(Status.ACTIVE)
                || match.getStatus().equals(Status.FULL_TIME)) {
                matches.add(match);
            }
        }

        matches.sort(Comparator.comparing(Match::getDatetime));
        final MatchesByDateDTO matchesByDateDTO = new MatchesByDateDTO()
                .matches(new MatchesByDateMapperConverter().convert(matches));

        final MatchesByDateDTO result = getApiService().findCurrentMatches();
        assertEquals(matchesByDateDTO, result, "Current matches should equal result.");
    }

}
