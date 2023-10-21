package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDay;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer repository tests.
 */
class TransferRepositoryTests extends AbstractRepositoryTests<Transfer, TransferRepository> {

    /**
     * Tests TransferRepository::findByTransferDayIdOrderByD11TeamNameAscFeeDesc.
     */
    @Test
    void testFindByTransferDayIdOrderByD11TeamNameAscFeeDesc() {
        final List<Transfer> entities = getEntities();
        entities.sort(Comparator
                              .<Transfer, String>comparing(transfer -> transfer.getD11Team().getName())
                              .thenComparing(Transfer::getFee, Comparator.reverseOrder()));

        final Set<TransferDay> transferDays = entities.stream()
                .map(Transfer::getTransferDay).collect(Collectors.toSet());

        assertTrue(transferDays.size() > 1,
                   "TransferRepository::findByTransferDayIdOrderByD11TeamNameAscFeeDesc transferDays size > 1");

        for (final TransferDay transferDay :transferDays) {
            final List<Transfer> result =
                    getRepository().findByTransferDayIdOrderByD11TeamNameAscFeeDesc(transferDay.getId());

            final List<Transfer> expected = entities.stream()
                    .filter(transfer -> transfer.getTransferDay().equals(transferDay))
                    .toList();

            assertTrue(expected.size() > 1,
                       "TransferRepository::findByTransferDayIdOrderByD11TeamNameAscFeeDesc expected size > 1");

            assertNotNull(result, "TransferRepository::findByTransferDayIdOrderByD11TeamNameAscFeeDesc not null");
            assertFalse(result.isEmpty(),
                        "TransferRepository::findByTransferDayIdOrderByD11TeamNameAscFeeDesc empty");
            assertEquals(expected, result,
                         "TransferRepository::findByTransferDayIdOrderByD11TeamNameAscFeeDesc equals");
        }
    }

    /**
     * Tests TransferRepository::findByPlayerIdOrderByTransferDayDatetimeDesc.
     */
    @Test
    void testFindByPlayerIdOrderByTransferDayDatetimeDesc() {
        final List<Transfer> entities = getEntities();

        entities.sort(Comparator.comparing(transfer -> transfer.getTransferDay().getDatetime(),
                                           Comparator.reverseOrder()));

        final Set<Player> players = entities.stream()
                .map(Transfer::getPlayer).collect(Collectors.toSet());

        assertTrue(players.size() > 1,
                   "TransferRepository::findByPlayerIdOrderByTransferDayDatetimeDesc players size > 1");

        for (final Player player : players) {
            final List<Transfer> result = getRepository().findByPlayerIdOrderByTransferDayDatetimeDesc(player.getId());

            final List<Transfer> expected = entities.stream()
                    .filter(transfer -> transfer.getPlayer().equals(player))
                    .toList();

            assertTrue(expected.size() > 1,
                       "TransferRepository::findByPlayerIdOrderByTransferDayDatetimeDesc expected size > 1");

            assertNotNull(result, "TransferRepository::findByPlayerIdOrderByTransferDayDatetimeDesc not null");
            assertFalse(result.isEmpty(),
                        "TransferRepository::findByPlayerIdOrderByTransferDayDatetimeDesc empty");
            assertEquals(expected, result,
                         "TransferRepository::findByPlayerIdOrderByTransferDayDatetimeDesc equals");
        }
    }

    /**
     * Tests TransferRepository::findByPlayerIdAndTransferDayId.
     */
    @Test
    void testFindByPlayerIdAndTransferDayId() {
        final List<Transfer> entities = getEntities();

        for (final Transfer transfer : entities) {
            final Optional<Transfer> optional =
                    getRepository().findByPlayerIdAndTransferDayId(transfer.getPlayer().getId(),
                                                                   transfer.getTransferDay().getId());

            assertTrue(optional.isPresent(), "TransferRepository::findByPlayerIdAndTransferDayId present");
            optional.ifPresent(result -> assertEquals(transfer, result,
                                                      "TransferRepository::findByPlayerIdAndTransferDayId"));
        }
    }

}
