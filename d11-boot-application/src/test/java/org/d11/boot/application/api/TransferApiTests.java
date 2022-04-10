package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.InsertTransferDTO;
import org.d11.boot.api.model.TransferDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.Transfer;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.repository.TransferRepository;
import org.d11.boot.client.api.TransferApi;
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
 * Transfer API tests.
 */
public class TransferApiTests extends AbstractRepositoryApiTests<Transfer, TransferRepository> {

    /**
     * Sets up transfers for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getRepository().findAll());
        assertFalse(getEntities().isEmpty(), "Transfers should not be empty.");
    }

    /**
     * Tests the findTransferById API operation.
     */
    @Test
    public void findTransferById() {
        final TransferApi transferApi = getApi(TransferApi.class);
        for(final Transfer transfer : getRepository().findAll()) {
            final TransferDTO result = transferApi.findTransferById(transfer.getId());
            final TransferDTO transferDTO = map(transfer, TransferDTO.class);
            assertNotNull(result, "Transfer by id should not be null.");
            assertEquals(transferDTO, result, "Transfer by id should equal Transfer.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> transferApi.findTransferById(-1L),
                     "Transfer not found should throw NotFound exception.");
    }

    /**
     * Tests the findTransferByTransferDayId API operation.
     */
    @Test
    public void findTransferByTransferDayId() {
        final Map<TransferDay, List<Transfer>> transferDayMap = new HashMap<>();
        for(final Transfer transfer : getEntities()) {
            final List<Transfer> transfers = transferDayMap.computeIfAbsent(transfer.getTransferDay(), p -> new ArrayList<>());
            transfers.add(transfer);
        }

        final TransferApi transferApi = getApi(TransferApi.class);
        for(final Map.Entry<TransferDay, List<Transfer>> entry : transferDayMap.entrySet()) {
            final TransferDay transferDay = entry.getKey();
            final List<Transfer> transfers = entry.getValue();

            transfers.sort(Comparator.comparing(Transfer::getD11Team, Comparator.comparing(D11Team::getName))
                    .thenComparing(Transfer::getFee, (fee1, fee2) -> fee2 - fee1));

            final List<TransferDTO> result = transferApi.findTransferByTransferDayId(transferDay.getId());

            assertNotNull(result, "Transfers by transfer day id should not be null.");
            assertEquals(map(transfers, TransferDTO.class), result,
                    "Transfers by transfer day id should equal transfers.");
        }

        assertTrue(transferApi.findTransferByTransferDayId(-1L).isEmpty(),
                "Transfers by transfer day id not found should be empty.");
    }

    /**
     * Tests the findTransferByPlayerId API operation.
     */
    @Test
    public void findTransferByPlayerId() {
        final Map<Player, List<Transfer>> playerMap = new HashMap<>();
        for(final Transfer transfer : getEntities()) {
            final List<Transfer> transfers = playerMap.computeIfAbsent(transfer.getPlayer(), p -> new ArrayList<>());
            transfers.add(transfer);
        }

        final TransferApi transferApi = getApi(TransferApi.class);
        for(final Map.Entry<Player, List<Transfer>> entry : playerMap.entrySet()) {
            final Player player = entry.getKey();
            final List<Transfer> transfers = entry.getValue();

            transfers.sort(Comparator.comparing(Transfer::getTransferDay, Comparator.comparing(TransferDay::getDatetime))
                    .reversed());

            final List<TransferDTO> result = transferApi.findTransferByPlayerId(player.getId());

            assertNotNull(result, "Transfers by player id should not be null.");
            assertEquals(map(transfers, TransferDTO.class), result,
                    "Transfers by player id should equal transfers.");
        }

        assertTrue(transferApi.findTransferByPlayerId(-1L).isEmpty(),
                "Transfers by player id not found should be empty.");
    }

    /**
     * Tests the insertTransfer operation.
     */
    @Test
    public void insertTransfer() {
        final InsertTransferDTO insertTransferDTO = new InsertTransferDTO();
        final TransferApi transferApi = getApi(TransferApi.class);
        assertThrows(FeignException.BadRequest.class,
                () -> transferApi.insertTransfer(insertTransferDTO),
                "Insert transfer request with missing properties should result in BAD_REQUEST.");
        // Add successful tests when we can be bothered figuring out how to not mess up other tests with new data.
    }

}
