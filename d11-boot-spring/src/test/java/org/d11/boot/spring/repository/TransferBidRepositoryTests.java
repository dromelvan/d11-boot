package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Transfer bid repository tests.
 */
class TransferBidRepositoryTests extends D11BootRepositoryTests<TransferBid, TransferBidRepository> {

    /**
     * Creates new transfer bid repository tests.
     */
    TransferBidRepositoryTests() {
        super(TransferBid.class);
    }

    @Override
    protected void beforeSave(final TransferBid transferBid) {
        super.beforeSave(transferBid);
        transferBid.getTransferDay().setId(null);
        transferBid.getTransferDay().getTransferWindow().setId(null);
        transferBid.getTransferDay().getTransferWindow().getMatchWeek().setId(null);
        transferBid.getTransferDay().getTransferWindow().getMatchWeek().getSeason().setId(null);
        transferBid.getPlayer().setId(null);
        transferBid.getPlayer().getCountry().setId(null);
        transferBid.getD11Team().setId(null);
        transferBid.getD11Team().getOwner().setId(null);
        transferBid.getD11Team().getCoOwner().setId(null);
        transferBid.setFee(Transfer.FEE_DIVISOR);
    }

    /**
     * Tests TransferBidRepository::findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc.
     */
    @Test
    @SuppressWarnings("checkstyle:MagicNumber")
    void testFindByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc() {
        final List<TransferBid> entities = getEntities();
        // Set transfer day of the first few entities to the same one so the query finds some but not all of them.
        final TransferDay transferDay = entities.get(0).getTransferDay();

        // Sort by player ranking first
        entities.get(0).setPlayerRanking(1);

        // If player ranking is equal sort by active fee descending
        entities.get(1).setPlayerRanking(2);
        entities.get(1).setActiveFee(Transfer.FEE_DIVISOR * 2);
        entities.get(1).setTransferDay(transferDay);

        entities.get(2).setPlayerRanking(2);
        entities.get(2).setActiveFee(Transfer.FEE_DIVISOR);
        entities.get(2).setTransferDay(transferDay);

        // If player ranking and active fee is equal sort by D11 team ranking descending
        entities.get(3).setPlayerRanking(3);
        entities.get(3).setActiveFee(Transfer.FEE_DIVISOR);
        entities.get(3).setD11TeamRanking(2);
        entities.get(3).setTransferDay(transferDay);

        entities.get(4).setPlayerRanking(3);
        entities.get(4).setActiveFee(Transfer.FEE_DIVISOR);
        entities.get(4).setD11TeamRanking(1);
        entities.get(4).setTransferDay(transferDay);

        getRepository().saveAll(entities);

        final List<TransferBid> expected = entities.subList(0, 5);

        final List<TransferBid> result =
                getRepository()
                        .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(transferDay.getId());

        assertNotNull(result,
                      """
                      TransferBidRepository::findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc
                      not null
                      """);
        assertEquals(expected, result,
                     """
                     TransferBidRepository::findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc
                     equals
                     """);
    }

}
