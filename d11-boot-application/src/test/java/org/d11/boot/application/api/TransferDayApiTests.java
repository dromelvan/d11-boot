package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.api.model.UpdateTransferDayDTO;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferWindow;
import org.d11.boot.application.repository.TransferDayRepository;
import org.d11.boot.client.api.TransferDayApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
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
 * Transfer day API tests.
 */
public class TransferDayApiTests extends AbstractRepositoryApiTests<TransferDay, TransferDayRepository> {

    /**
     * Sets up transfer days for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getRepository().findAll());
        Collections.sort(getEntities());
        assertFalse(getEntities().isEmpty(), "Transfer day should not be empty.");
    }

    /**
     * Tests the findTransferDayById API operation.
     */
    @Test
    public void findTransferDayById() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);
        for(final TransferDay transferDay : getRepository().findAll()) {
            final TransferDayDTO result = transferDayApi.findTransferDayById(transferDay.getId());
            final TransferDayDTO transferDayDTO = map(transferDay, TransferDayDTO.class);
            assertNotNull(result, "Transfer day by id should not be null.");
            assertEquals(transferDayDTO, result, "Transfer day by id should equal TransferDay.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> transferDayApi.findTransferDayById(-1L),
                     "Transfer day not found not found should throw NotFound exception.");
    }

    /**
     * Tests the findCurrentTransferDay API operation.
     */
    @Test
    public void findCurrentTransferDay() {
        final TransferDay currentTransferDay = getRepository().findFirstByOrderByDatetimeDesc().orElse(null);
        final TransferDayDTO transferDayDTO = map(currentTransferDay, TransferDayDTO.class);

        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);
        final TransferDayDTO result = transferDayApi.findCurrentTransferDay();
        assertNotNull(result, "Current transfer day should not be null.");
        assertEquals(transferDayDTO, result, "Current transfer day result should equal current transfer day.");
    }

    /**
     * Tests the findTransferDayByTransferWindowId API operation.
     */
    @Test
    public void findTransferDayByTransferWindowId() {
        final Map<TransferWindow, List<TransferDay>> transferWindowMap = new HashMap<>();
        for(final TransferDay transferDay : getRepository().findAll()) {
            final List<TransferDay> transferDays = transferWindowMap.computeIfAbsent(transferDay.getTransferWindow(), p -> new ArrayList<>());
            transferDays.add(transferDay);
        }

        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);
        for(final Map.Entry<TransferWindow, List<TransferDay>> entry : transferWindowMap.entrySet()) {
            final TransferWindow transferWindow = entry.getKey();
            final List<TransferDay> transferDays = entry.getValue();

            transferDays.sort(Comparator.comparing(TransferDay::getDatetime).reversed());

            final List<TransferDayDTO> result = transferDayApi.findTransferDayByTransferWindowId(transferWindow.getId());

            assertNotNull(result, "Transfer days by transfer window id should not be null.");
            assertEquals(map(transferDays, TransferDayDTO.class), result,
                    "Transfer days by transfer window id should equal transfer days.");
        }

        assertTrue(transferDayApi.findTransferDayByTransferWindowId(-1L).isEmpty(),
                "Transfer days by transfer window id not found should be empty.");
    }

    /**
     * Tests the updateTransferDay operation.
     */
    @Test
    public void updateTransferDay() {
        final UpdateTransferDayDTO updateTransferDayDTO = new UpdateTransferDayDTO();
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);
        assertThrows(FeignException.BadRequest.class,
                     () -> transferDayApi.updateTransferDay(updateTransferDayDTO),
                     "Update transfer day request with missing properties should result in BAD_REQUEST.");
        // Add successful tests when we can be bothered figuring out how to not mess up other tests with new data.
    }

}
