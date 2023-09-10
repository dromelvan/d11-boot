package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDay;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer repository tests.
 */
class TransferRepositoryTests extends D11BootRepositoryTests<Transfer, TransferRepository> {

    /**
     * Creates new transfer repository tests.
     */
    TransferRepositoryTests() {
        super(Transfer.class);
    }

    @Override
    protected void beforeSave(final Transfer transfer) {
        super.beforeSave(transfer);
        transfer.getTransferDay().setId(null);
        transfer.getTransferDay().getTransferWindow().setId(null);
        transfer.getTransferDay().getTransferWindow().getMatchWeek().setId(null);
        transfer.getTransferDay().getTransferWindow().getMatchWeek().getSeason().setId(null);
        transfer.getPlayer().setId(null);
        transfer.getPlayer().getCountry().setId(null);
        transfer.getD11Team().setId(null);
        transfer.getD11Team().getOwner().setId(null);
        transfer.getD11Team().getCoOwner().setId(null);
        transfer.setFee(Transfer.FEE_DIVISOR);
    }

    /**
     * Tests TransferRepository::findByTransferDayIdOrderByD11TeamNameAscFeeDesc.
     */
    @Test
    void testFindByTransferDayIdOrderByD11TeamNameAscFeeDesc() {
        final List<Transfer> entities = getEntities();
        // Set transfer day of the first few entities to the same one so the query finds some but not all of them.
        final TransferDay transferDay = entities.get(0).getTransferDay();

        // Sort by D11 team name first
        entities.get(0).getD11Team().setName("A");

        // If D11 team name is equal sort by fee descending
        entities.get(1).setTransferDay(transferDay);
        entities.get(1).getD11Team().setName("B");
        entities.get(1).setFee(Transfer.FEE_DIVISOR * 2);

        entities.get(2).setTransferDay(transferDay);
        entities.get(2).getD11Team().setName(entities.get(1).getD11Team().getName());
        entities.get(2).setFee(Transfer.FEE_DIVISOR);

        getRepository().saveAll(entities);

        final List<Transfer> expected = entities.subList(0, 3);

        final List<Transfer> result =
                getRepository().findByTransferDayIdOrderByD11TeamNameAscFeeDesc(transferDay.getId());

        assertNotNull(result, "TransferRepository::findByTransferDayIdOrderByD11TeamNameAscFeeDesc not null");
        assertEquals(expected, result, "TransferRepository::findByTransferDayIdOrderByD11TeamNameAscFeeDesc equals");
    }

    /**
     * Tests TransferRepository::findByPlayerIdOrderByTransferDayDatetimeDesc.
     */
    @Test
    void testFindByPlayerIdOrderByTransferDayDatetimeDesc() {
        final List<Transfer> entities = getEntities();
        // Set player of the first few entities to the same one so the query finds some but not all of them.
        final Player player = entities.get(0).getPlayer();

        entities.get(1).setPlayer(player);
        entities.get(1).getTransferDay().setDatetime(entities.get(0).getTransferDay().getDatetime().minusDays(1));

        getRepository().saveAll(entities);

        final List<Transfer> expected = entities.subList(0, 2);

        final List<Transfer> result = getRepository().findByPlayerIdOrderByTransferDayDatetimeDesc(player.getId());

        assertNotNull(result, "TransferRepository::findByPlayerIdOrderByTransferDayDatetimeDesc not null");
        assertEquals(expected, result, "TransferRepository::findByPlayerIdOrderByTransferDayDatetimeDesc equals");
    }

    /**
     * Tests TransferRepository::findByPlayerIdAndTransferDayId.
     */
    @Test
    void testFindByPlayerIdAndTransferDayId() {
        final List<Transfer> entities = getEntities();

        assertFalse(entities.isEmpty(), "TransferRepository::findByPlayerIdAndTransferDayId empty");

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
