package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.d11.boot.spring.model.Season;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team season stat repository tests.
 */
class D11TeamSeasonStatRepositoryTests extends AbstractRepositoryTests<D11TeamSeasonStat, D11TeamSeasonStatRepository> {

    /**
     * Tests D11TeamSeasonStatRepository::findBySeasonIdOrderByRanking.
     */
    @Test
    void testFindBySeasonIdOrderByRanking() {
        final List<D11TeamSeasonStat> entities = getEntities();
        // Set season for two entities to the same one. This way the query should return some but not all the entities.
        final Season season = entities.get(0).getSeason();
        entities.get(1).setSeason(season);
        getRepository().save(entities.get(1));

        final List<D11TeamSeasonStat> expected = entities.subList(0, 2);
        expected.sort(Comparator.comparingInt(D11TeamSeasonStat::getRanking));

        final List<D11TeamSeasonStat> result = getRepository().findBySeasonIdOrderByRanking(season.getId());

        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * Tests D11TeamSeasonStatRepository::findByD11TeamIdAndSeasonId.
     */
    @Test
    void testFindByD11TeamIdAndSeasonId() {
        final List<D11TeamSeasonStat> entities = getEntities();

        assertFalse(entities.isEmpty());

        for (final D11TeamSeasonStat expected : entities) {
            final Optional<D11TeamSeasonStat> optional =
                    getRepository().findByD11TeamIdAndSeasonId(expected.getD11Team().getId(),
                                                               expected.getSeason().getId());

            assertTrue(optional.isPresent());
            optional.ifPresent(result -> assertEquals(expected, result));
        }
    }

}
