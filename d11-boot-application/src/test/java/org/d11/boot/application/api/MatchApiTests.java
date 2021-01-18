package org.d11.boot.application.api;

import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.api.service.MatchApiService;
import org.d11.boot.application.model.Goal;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.repository.MatchRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Match API tests.
 */
public class MatchApiTests extends AbstractRepositoryApiTests<Match, MatchRepository, MatchApiService> {

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

}
