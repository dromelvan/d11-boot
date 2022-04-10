package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.TransferBidDTO;
import org.d11.boot.application.model.TransferBid;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.repository.TransferBidRepository;
import org.d11.boot.client.api.TransferBidApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing API tests.
 */
public class TransferBidApiTests extends AbstractRepositoryApiTests<TransferBid, TransferBidRepository> {

    /**
     * Sets up transfer days for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getRepository().findAll());
        assertFalse(getEntities().isEmpty(), "Transfer listings should not be empty.");
    }

    /**
     * Tests the findTransferBidById API operation.
     */
    @Test
    public void findTransferBidById() {
        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);
        for(final TransferBid transferBid : getRepository().findAll()) {
            final TransferBidDTO result = transferBidApi.findTransferBidById(transferBid.getId());
            final TransferBidDTO transferBidDTO = map(transferBid, TransferBidDTO.class);
            assertNotNull(result, "Transfer bid by id should not be null.");
            assertEquals(transferBidDTO, result, "Transfer bid by id should equal TransferBid.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> transferBidApi.findTransferBidById(-1L),
                     "Transfer bid not found should throw NotFound exception.");
    }

    /**
     * Tests the findTransferBidByTransferDayId API operation.
     */
    @Test
    public void findTransferBidByTransferDayId() {
        final Map<TransferDay, List<TransferBid>> transferDayMap = new HashMap<>();
        for(final TransferBid transferBid : getEntities()) {
            final List<TransferBid> transferBids = transferDayMap.computeIfAbsent(transferBid.getTransferDay(), p -> new ArrayList<>());
            transferBids.add(transferBid);
        }

        final TransferBidApi transferBidApi = getApi(TransferBidApi.class);
        for(final Map.Entry<TransferDay, List<TransferBid>> entry : transferDayMap.entrySet()) {
            final TransferDay transferDay = entry.getKey();
            final List<TransferBid> transferBids = entry.getValue();

            transferBids.sort(Comparator.comparing(TransferBid::getPlayerRanking)
                    .thenComparing(TransferBid::getActiveFee, (fee1, fee2) -> fee2 - fee1)
                    .thenComparing(TransferBid::getD11TeamRanking, (d11TeamRanking1, d11TeamRanking2) -> d11TeamRanking2 - d11TeamRanking1));

            final List<TransferBidDTO> result = transferBidApi.findTransferBidByTransferDayId(transferDay.getId());

            assertNotNull(result, "Transfer bids by transfer day id should not be null.");
            assertEquals(map(transferBids, TransferBidDTO.class), result,
                    "Transfer bids by transfer day id should equal transfer bids.");
        }

        assertTrue(transferBidApi.findTransferBidByTransferDayId(-1L).isEmpty(),
                "Transfer bids by transfer day id not found should be empty.");
    }

}
