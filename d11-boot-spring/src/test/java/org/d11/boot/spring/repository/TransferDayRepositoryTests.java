package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer day repository tests.
 */
class TransferDayRepositoryTests extends AbstractRepositoryTests<TransferDay, TransferDayRepository> {

    /**
     * Tests TransferDayRepository::findFirstByOrderByDatetimeDesc.
     */
    @Test
    void testFindFirstByOrderByDatetimeDesc() {
        final List<TransferDay> entities = getEntities();
        entities.sort(Comparator.naturalOrder());

        final TransferDay transferDay = entities.get(0);

        final Optional<TransferDay> optional = getRepository().findFirstByOrderByDatetimeDesc();

        assertTrue(optional.isPresent(), "TransferDayRepository::findFirstByOrderByDatetimeDesc present");
        optional.ifPresent(result -> assertEquals(transferDay, result,
                                                  "TransferDayRepository::findFirstByOrderByDatetimeDesc equals"));
    }

    /**
     * Tests TransferDayRepository::findByTransferWindowIdOrderByDatetimeDesc.
     */
    @Test
    void testFindByTransferWindowIdOrderByDatetimeDesc() {
        final List<TransferDay> entities = getEntities();

        // Set transfer window for two entities to the same one. This way the query should return some but not all the
        // entities.
        final TransferWindow transferWindow = entities.get(0).getTransferWindow();
        entities.get(1).setTransferWindow(transferWindow);
        getRepository().save(entities.get(1));

        final List<TransferDay> expected = entities.subList(0, 2);
        expected.sort(Comparator.naturalOrder());

        final List<TransferDay> result =
                getRepository().findByTransferWindowIdOrderByDatetimeDesc(transferWindow.getId());

        assertNotNull(result, "TransferDayRepository::findByTransferWindowIdOrderByDatetimeDesc not null");
        assertEquals(expected, result, "TransferDayRepository::findByTransferWindowIdOrderByDatetimeDesc equals");
    }

}
