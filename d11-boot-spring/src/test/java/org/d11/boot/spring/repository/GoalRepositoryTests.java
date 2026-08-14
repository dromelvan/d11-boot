package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Goal;
import org.d11.boot.spring.model.PlayerMatchStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Goal repository tests.
 */
class GoalRepositoryTests extends AbstractRepositoryTests<Goal, GoalRepository> {

    /**
     * Player match stat repository for deriving expected goals per D11 match and D11 team.
     */
    @Autowired
    private PlayerMatchStatRepository playerMatchStatRepository;

    /**
     * Tests GoalRepository::findByD11MatchIdAndD11TeamId.
     */
    @Test
    void testFindByD11MatchIdAndD11TeamId() {
        final List<Goal> goals = getEntities();
        final List<PlayerMatchStat> stats = this.playerMatchStatRepository.findAll();

        assertFalse(goals.isEmpty());
        assertFalse(stats.isEmpty());

        final Map<Long, Map<Long, List<PlayerMatchStat>>> byD11MatchAndTeam = stats.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getD11Match().getId(),
                        Collectors.groupingBy(s -> s.getD11Team().getId())
                ));

        assertTrue(byD11MatchAndTeam.size() > 1);

        boolean hasGoals = false;

        for (final Map.Entry<Long, Map<Long, List<PlayerMatchStat>>> d11MatchEntry : byD11MatchAndTeam.entrySet()) {
            final long d11MatchId = d11MatchEntry.getKey();
            for (final Map.Entry<Long, List<PlayerMatchStat>> d11TeamEntry : d11MatchEntry.getValue().entrySet()) {
                final long d11TeamId = d11TeamEntry.getKey();
                final Set<String> playerMatchKeys = d11TeamEntry.getValue().stream()
                        .map(s -> s.getPlayer().getId() + ":" + s.getMatch().getId())
                        .collect(Collectors.toSet());

                final List<Goal> expected = goals.stream()
                        .filter(g -> playerMatchKeys.contains(
                                g.getPlayer().getId() + ":" + g.getMatch().getId()))
                        .sorted(Comparator.comparing(Goal::getTime).thenComparing(Goal::getAddedTime))
                        .toList();

                final List<Goal> result = getRepository().findByD11MatchIdAndD11TeamId(d11MatchId, d11TeamId);

                assertNotNull(result);
                assertEquals(expected, result);

                if (!expected.isEmpty()) {
                    hasGoals = true;
                }
            }
        }

        assertTrue(hasGoals);
    }

}
