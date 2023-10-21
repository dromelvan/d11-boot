package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Season;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Season repository tests.
 */
class SeasonRepositoryTests extends AbstractRepositoryTests<Season, SeasonRepository> {

    /**
     * Tests SeasonRepository::findFirstByOrderByDateDesc.
     */
    @Test
    void testFindFirstByOrderByDateDesc() {
        final Season currentSeason = getEntities().stream()
                .max(Comparator.comparing(Season::getDate))
                .orElse(null);
        assertNotNull(currentSeason, "SeasonRepository::findFirstByOrderByDateDesc currentSeason not null");

        final Season result = getRepository().findFirstByOrderByDateDesc().orElse(null);
        assertNotNull(result, "SeasonRepository::findFirstByOrderByDateDesc not null");
        assertEquals(currentSeason, result, "SeasonRepository::findFirstByOrderByDateDesc");
    }

    /**
     * Tests SeasonRepository::findByOrderByDateDesc.
     */
    @Test
    void testFindByOrderByDateDesc() {
        final List<Season> seasons = getEntities();
        seasons.sort(Comparator.comparing(Season::getDate).reversed());

        final List<Season> result = getRepository().findByOrderByDateDesc();

        assertNotNull(result, "SeasonRepository::findByOrderByDateDesc not null");
        assertEquals(seasons, result, "SeasonRepository::findByOrderByDateDesc");
    }

}
