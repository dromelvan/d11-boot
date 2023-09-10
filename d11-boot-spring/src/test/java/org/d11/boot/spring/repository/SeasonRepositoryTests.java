package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Season;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Season repository tests.
 */
class SeasonRepositoryTests extends D11BootRepositoryTests<Season, SeasonRepository> {

    /**
     * Creates new Season repository tests.
     */
    SeasonRepositoryTests() {
        super(Season.class);
    }

    /**
     * Tests SeasonRepository::findFirstByOrderByDateDesc.
     */
    @Test
    void testFindFirstByOrderByDateDesc() {
        final Season currentSeason = getEntities().stream()
                .max(Comparator.comparing(Season::getDate))
                .orElseThrow(NotFoundException::new);

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
        assertTrue(result.size() >= 2, "SeasonRepository::findByOrderByDateDesc size");
        assertEquals(seasons, result, "SeasonRepository::findByOrderByDateDesc");
    }

}
