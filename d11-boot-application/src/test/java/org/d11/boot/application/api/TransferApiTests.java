package org.d11.boot.application.api;

import org.d11.boot.api.model.InsertTransferDTO;
import org.d11.boot.api.model.TransferDTO;
import org.d11.boot.api.service.D11ApiServiceException;
import org.d11.boot.api.service.TransferApiService;
import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.Player;
import org.d11.boot.application.model.jpa.Transfer;
import org.d11.boot.application.model.jpa.TransferDay;
import org.d11.boot.application.repository.TransferRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer API tests.
 */
public class TransferApiTests extends AbstractRepositoryApiTests<Transfer, TransferRepository, TransferApiService> {

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
        for(final Transfer transfer : getRepository().findAll()) {
            final TransferDTO result = getApiService().findTransferById(transfer.getId());
            final TransferDTO transferDTO = map(transfer, TransferDTO.class);
            assertNotNull(result, "Transfer by id should not be null.");
            assertEquals(transferDTO, result, "Transfer by id should equal Transfer.");
        }

        assertNull(getApiService().findTransferById(-1L), "Transfer not found should return null.");
        assertBadRequest(get("BAD_TRANSFER_REQUEST"));
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

        for(final Map.Entry<TransferDay, List<Transfer>> entry : transferDayMap.entrySet()) {
            final TransferDay transferDay = entry.getKey();
            final List<Transfer> transfers = entry.getValue();

            transfers.sort(Comparator.comparing(Transfer::getD11Team, Comparator.comparing(D11Team::getName))
                    .thenComparing(Transfer::getFee, (fee1, fee2) -> fee2 - fee1));

            final List<TransferDTO> result = getApiService().findTransferByTransferDayId(transferDay.getId());

            assertNotNull(result, "Transfers by transfer day id should not be null.");
            assertEquals(map(transfers, TransferDTO.class), result,
                    "Transfers by transfer day id should equal transfers.");
        }

        assertTrue(getApiService().findTransferByTransferDayId(-1L).isEmpty(),
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

        for(final Map.Entry<Player, List<Transfer>> entry : playerMap.entrySet()) {
            final Player player = entry.getKey();
            final List<Transfer> transfers = entry.getValue();

            transfers.sort(Comparator.comparing(Transfer::getTransferDay, Comparator.comparing(TransferDay::getDatetime))
                    .reversed());

            final List<TransferDTO> result = getApiService().findTransferByPlayerId(player.getId());

            assertNotNull(result, "Transfers by player id should not be null.");
            assertEquals(map(transfers, TransferDTO.class), result,
                    "Transfers by player id should equal transfers.");
        }

        assertTrue(getApiService().findTransferByPlayerId(-1L).isEmpty(),
                "Transfers by player id not found should be empty.");
    }

    /**
     * Tests the insertTransfer operation.
     */
    @Test
    public void insertTransfer() {
        final InsertTransferDTO insertTransferDTO = new InsertTransferDTO();
        final D11ApiServiceException d11ApiServiceException =
                assertThrows(D11ApiServiceException.class, () -> getApiService().insertTransfer(insertTransferDTO));
        assertEquals(HttpStatus.BAD_REQUEST, d11ApiServiceException.getStatusCode(),
                "Insert transfer request with missing properties should result in BAD_REQUEST.");

        // Add successful tests when we can be bothered figuring out how to not mess up other tests with new data.
    }

}
