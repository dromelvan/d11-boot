package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer bid repository tests.
 */
class TransferBidRepositoryTests extends AbstractRepositoryTests<TransferBid, TransferBidRepository> {

    /**
     * Tests TransferBidRepository::findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc.
     */
    @Test
    void testFindByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc() {
        final List<TransferBid> entities = getEntities();
        entities.sort(Comparator
                              .comparing(TransferBid::getPlayerRanking)
                              .thenComparing(TransferBid::getActiveFee, Comparator.reverseOrder())
                              .thenComparing(TransferBid::getD11TeamRanking, Comparator.reverseOrder()));

        final Set<TransferDay> transferDays = entities.stream()
                .map(TransferBid::getTransferDay).collect(Collectors.toSet());

        assertTrue(transferDays.size() > 1,
                   """
                   TransferRepository::findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc
                   transferDays size > 1
                   """);

        for (final TransferDay transferDay : transferDays) {
            final long id = transferDay.getId();
            final List<TransferBid> result =
                    getRepository().findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(id);

            final List<TransferBid> expected = entities.stream()
                    .filter(transferBid -> transferBid.getTransferDay().equals(transferDay))
                    .toList();

            assertFalse(expected.isEmpty(),
                       """
                       TransferRepository::findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc
                       expected empty
                       """);

            assertNotNull(result,
                          """
                          TransferRepository::findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc
                          not null
                          """);
            assertFalse(result.isEmpty(),
                        """
                        TransferRepository::findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc
                        empty
                        """);
            assertEquals(expected, result,
                         """
                         TransferRepository::findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc 
                         equals
                         """);
        }
    }

}
