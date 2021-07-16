package org.d11.boot.application.api;

import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.api.service.TransferDayApiService;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferWindow;
import org.d11.boot.application.repository.TransferDayRepository;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer day API tests.
 */
public class TransferDayApiTests extends AbstractRepositoryApiTests<TransferDay, TransferDayRepository, TransferDayApiService> {

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
        for(final TransferDay transferDay : getRepository().findAll()) {
            final TransferDayDTO result = getApiService().findTransferDayById(transferDay.getId());
            final TransferDayDTO transferDayDTO = map(transferDay, TransferDayDTO.class);
            assertNotNull(result, "Transfer day by id should not be null.");
            assertEquals(transferDayDTO, result, "Transfer day by id should equal TransferDay.");
        }

        assertNull(getApiService().findTransferDayById(-1L), "Transfer day not found should return null.");
        assertBadRequest(get("BAD_TRANSFER_DAY_REQUEST"));
    }

    /**
     * Tests the findCurrentTransferDay API operation.
     */
    @Test
    public void findCurrentTransferDay() {
        final TransferDay currentTransferDay = getRepository().findFirstByOrderByDatetimeDesc().orElse(null);
        final TransferDayDTO transferDayDTO = map(currentTransferDay, TransferDayDTO.class);

        final TransferDayDTO result = getApiService().findCurrentTransferDay();
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

        for(final Map.Entry<TransferWindow, List<TransferDay>> entry : transferWindowMap.entrySet()) {
            final TransferWindow transferWindow = entry.getKey();
            final List<TransferDay> transferDays = entry.getValue();

            transferDays.sort(Comparator.comparing(TransferDay::getDatetime).reversed());

            final List<TransferDayDTO> result = getApiService().findTransferDayByTransferWindowId(transferWindow.getId());

            assertNotNull(result, "Transfer days by transfer window id should not be null.");
            assertEquals(map(transferDays, TransferDayDTO.class), result,
                    "Transfer days by transfer window id should equal transfer days.");
        }

        assertTrue(getApiService().findTransferDayByTransferWindowId(-1L).isEmpty(),
                "Transfer days by transfer window id not found should be empty.");
    }

}
