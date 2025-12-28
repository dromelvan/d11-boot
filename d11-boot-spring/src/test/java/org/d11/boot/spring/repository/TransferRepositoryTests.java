package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.Season;
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
     * Tests TransferRepository::findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId.
     */
    @Test
    void testFindByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId() {
        final List<Transfer> entities = getEntities();
        entities.sort(Comparator.comparing(Transfer::getId));

        final Set<Season> seasons = entities.stream()
                .map(transfer -> transfer.getTransferDay().getTransferWindow().getMatchWeek().getSeason())
                .collect(Collectors.toSet());

        final Set<D11Team> d11Teams = entities.stream()
                .map(Transfer::getD11Team)
                .collect(Collectors.toSet());

        for (final Season season : seasons) {
            for (final D11Team d11Team : d11Teams) {
                final List<Transfer> expected = entities.stream()
                        .filter(transfer ->
                                transfer.getTransferDay().getTransferWindow().getMatchWeek().getSeason().equals(season)
                                && transfer.getD11Team().equals(d11Team))
                        .toList();

                final List<Transfer> result = getRepository()
                        .findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(season.getId(), d11Team.getId());
                result.sort(Comparator.comparing(Transfer::getId));

                assertEquals(expected, result);
            }
        }
    }

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

        assertTrue(transferDays.size() > 1);

        for (final TransferDay transferDay :transferDays) {
            final List<Transfer> result =
                    getRepository().findByTransferDayIdOrderByD11TeamNameAscFeeDesc(transferDay.getId());

            final List<Transfer> expected = entities.stream()
                    .filter(transfer -> transfer.getTransferDay().equals(transferDay))
                    .toList();

            assertTrue(expected.size() > 1);

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(expected, result);
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

        assertTrue(players.size() > 1);

        for (final Player player : players) {
            final List<Transfer> result = getRepository().findByPlayerIdOrderByTransferDayDatetimeDesc(player.getId());

            final List<Transfer> expected = entities.stream()
                    .filter(transfer -> transfer.getPlayer().equals(player))
                    .toList();

            assertTrue(expected.size() > 1);

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(expected, result);
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

            assertTrue(optional.isPresent());
            optional.ifPresent(result -> assertEquals(transfer, result));
        }
    }

}
