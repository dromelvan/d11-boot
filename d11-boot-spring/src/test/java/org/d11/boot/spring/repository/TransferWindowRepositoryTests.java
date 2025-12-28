package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferWindow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer window repository tests.
 */
class TransferWindowRepositoryTests extends AbstractRepositoryTests<TransferWindow, TransferWindowRepository> {

    /**
     * Updates match weeks.
     */
    @Autowired
    private MatchWeekRepository matchWeekRepository;

    /**
     * Tests TransferWindowRepository::findCurrentTransferWindow.
     */
    @Test
    void testFindCurrentTransferWindow() {
        final List<TransferWindow> entities = getEntities();
        entities.sort(Comparator.naturalOrder());

        final TransferWindow transferWindow = entities.get(0);

        final Optional<TransferWindow> optional = getRepository().findCurrentTransferWindow();

        assertTrue(optional.isPresent());
        optional.ifPresent(result -> assertEquals(transferWindow, result));
    }

    /**
     * Tests TransferWindowRepository::findFirstByOrderByDatetimeDesc.
     */
    @Test
    void testFindFirstByOrderByDatetimeDesc() {
        final List<TransferWindow> entities = getEntities();
        entities.sort(Comparator.naturalOrder());

        final TransferWindow transferWindow = entities.get(0);

        final Optional<TransferWindow> optional = getRepository().findFirstByOrderByDatetimeDesc();

        assertTrue(optional.isPresent());
        optional.ifPresent(result -> assertEquals(transferWindow, result));
    }

    /**
     * Tests TransferWindowRepository::findByMatchWeekSeasonIdOrderByDatetimeDesc.
     */
    @Test
    void testFindByMatchWeekSeasonIdOrderByDatetimeDesc() {
        final List<TransferWindow> entities = getEntities();

        // Set season for two entities to the same one. This way the query should return some but not all the entities.
        final Season season = entities.get(0).getMatchWeek().getSeason();
        entities.get(1).getMatchWeek().setSeason(season);
        this.matchWeekRepository.save(entities.get(1).getMatchWeek());

        final List<TransferWindow> expected = entities.subList(0, 2);
        expected.sort(Comparator.naturalOrder());

        final List<TransferWindow> result = getRepository().findByMatchWeekSeasonIdOrderByDatetimeDesc(season.getId());

        assertNotNull(result);
        assertEquals(expected, result);
    }

}
