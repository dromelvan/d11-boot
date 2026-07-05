package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.util.TransferDayPlayer;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer bid repository tests.
 */
class TransferBidRepositoryTests extends AbstractRepositoryTests<TransferBid, TransferBidRepository> {

    /**
     * Tests TransferBidRepository::saveAndFlush with duplicate transferDayId, playerId and d11TeamId.
     */
    @Test
    void testSaveAndFlushUniqueConstraint() {
        final List<TransferBid> entities = getEntities();
        assertFalse(entities.isEmpty());

        final TransferBid transferBid = entities.get(0);
        final TransferBid duplicate = new TransferBid();

        duplicate.setTransferDay(transferBid.getTransferDay());
        duplicate.setPlayer(transferBid.getPlayer());
        duplicate.setD11Team(transferBid.getD11Team());
        duplicate.setPlayerRanking(transferBid.getPlayerRanking());
        duplicate.setD11TeamRanking(transferBid.getD11TeamRanking());
        duplicate.setFee(transferBid.getFee());
        duplicate.setActiveFee(transferBid.getActiveFee());

        assertThrows(DataIntegrityViolationException.class, () -> getRepository().saveAndFlush(duplicate));
    }

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

        assertTrue(transferDays.size() > 1);

        for (final TransferDay transferDay : transferDays) {
            final long id = transferDay.getId();
            final List<TransferBid> result =
                    getRepository().findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(id);

            final List<TransferBid> expected = entities.stream()
                    .filter(transferBid -> transferBid.getTransferDay().equals(transferDay))
                    .toList();

            assertFalse(expected.isEmpty());

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(expected, result);
        }
    }

    /**
     * Tests TransferBidRepository::findByTransferDayIdAndPlayerIdAndD11TeamId.
     */
    @Test
    void testFindByTransferDayIdAndPlayerIdAndD11TeamId() {
        final List<TransferBid> entities = getEntities();

        assertFalse(entities.isEmpty());

        for (final TransferBid transferBid : entities) {
            final Optional<TransferBid> optional =
                    getRepository().findByTransferDayIdAndPlayerIdAndD11TeamId(transferBid.getTransferDay().getId(),
                                                                               transferBid.getPlayer().getId(),
                                                                               transferBid.getD11Team().getId());

            assertTrue(optional.isPresent());
            optional.ifPresent(result -> assertEquals(transferBid, result));
        }
    }


    /**
     * Test TransferBidRepository::findByTransferDayIdAndPlayerIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc.
     */
    @Test
    void testFindByTransferDayIdAndPlayerIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc() {
        final List<TransferBid> entities = getEntities();
        entities.sort(Comparator
                              .comparing(TransferBid::getPlayerRanking)
                              .thenComparing(TransferBid::getActiveFee, Comparator.reverseOrder())
                              .thenComparing(TransferBid::getD11TeamRanking, Comparator.reverseOrder()));

        final Set<TransferDayPlayer> transferDayPlayers = entities.stream()
                .map(transferBid ->
                    new TransferDayPlayer(transferBid.getTransferDay(), transferBid.getPlayer())
                )
                .collect(Collectors.toSet());

        assertTrue(transferDayPlayers.size() > 1);

        for (final TransferDayPlayer transferDayPlayer : transferDayPlayers) {
            final long tranferDayid = transferDayPlayer.transferDay().getId();
            final long playerid = transferDayPlayer.player().getId();

            final List<TransferBid> result =
                getRepository()
                    .findByTransferDayIdAndPlayerIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(tranferDayid,
                                                                                                          playerid);

            final List<TransferBid> expected = entities.stream()
                    .filter(transferBid -> transferBid.getTransferDay().equals(transferDayPlayer.transferDay())
                                           && transferBid.getPlayer().equals(transferDayPlayer.player()))
                    .toList();

            assertFalse(expected.isEmpty());

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(expected, result);
        }
    }

}
