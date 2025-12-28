package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TeamSeasonStat;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team season stat repository tests.
 */
class TeamSeasonStatRepositoryTests extends AbstractRepositoryTests<TeamSeasonStat, TeamSeasonStatRepository> {

    /**
     * Tests TeamSeasonStatRepository::findBySeasonIdOrderByRanking.
     */
    @Test
    void testFindBySeasonIdOrderByRanking() {
        final List<TeamSeasonStat> entities = getEntities();
        // Set season for two entities to the same one. This way the query should return some but not all the entities.
        final Season season = entities.get(0).getSeason();
        entities.get(1).setSeason(season);
        getRepository().save(entities.get(1));

        final List<TeamSeasonStat> expected = entities.subList(0, 2);
        expected.sort(Comparator.comparingInt(TeamSeasonStat::getRanking));

        final List<TeamSeasonStat> result = getRepository().findBySeasonIdOrderByRanking(season.getId());

        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Tests TeamSeasonStatRepository::findByTeamIdAndSeasonId.
     */
    @Test
    void testFindByTeamIdAndSeasonId() {
        final List<TeamSeasonStat> entities = getEntities();

        assertFalse(entities.isEmpty());

        for (final TeamSeasonStat expected : entities) {
            final Optional<TeamSeasonStat> optional =
                    getRepository().findByTeamIdAndSeasonId(expected.getTeam().getId(), expected.getSeason().getId());

            assertTrue(optional.isPresent());
            optional.ifPresent(result -> assertEquals(expected, result));
        }
    }

}
