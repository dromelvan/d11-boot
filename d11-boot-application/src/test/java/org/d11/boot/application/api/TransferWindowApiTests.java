package org.d11.boot.application.api;

import org.d11.boot.api.model.TransferWindowDTO;
import org.d11.boot.api.service.TransferWindowApiService;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.TransferWindow;
import org.d11.boot.application.repository.TransferWindowRepository;
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
 * Transfer window API tests.
 */
public class TransferWindowApiTests extends AbstractRepositoryApiTests<TransferWindow, TransferWindowRepository, TransferWindowApiService> {

    /**
     * Sets up transfer windows for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getRepository().findAll());
        Collections.sort(getEntities());
        assertFalse(getEntities().isEmpty(), "Transfer window should not be empty.");
    }

    /**
     * Tests the findTransferWindowById API operation.
     */
    @Test
    public void findTransferWindowById() {
        for(final TransferWindow transferWindow : getRepository().findAll()) {
            final TransferWindowDTO result = getApiService().findTransferWindowById(transferWindow.getId());
            final TransferWindowDTO transferWindowDTO = map(transferWindow, TransferWindowDTO.class);
            assertNotNull(result, "Transfer window by id should not be null.");
            assertEquals(transferWindowDTO, result, "Transfer window by id should equal TransferWindow.");
        }

        assertNull(getApiService().findTransferWindowById(-1L), "Transfer window not found should return null.");
        assertBadRequest(get("BAD_TRANSFER_WINDOW_REQUEST"));
    }

    /**
     * Tests the findCurrentTransferWindow API operation.
     */
    @Test
    public void findCurrentTransferWindow() {
        final TransferWindow currentTransferWindow = getEntities().get(0);
        final TransferWindowDTO transferWindowDTO = map(currentTransferWindow, TransferWindowDTO.class);

        final TransferWindowDTO result = getApiService().findCurrentTransferWindow();
        assertNotNull(result, "Current transfer window should not be null.");
        assertEquals(transferWindowDTO, result, "Current transfer window result should equal current transfer window.");
    }

    /**
     * Tests the findTransferWindowByPlayerId API operation.
     */
    @Test
    public void findTransferWindowByPlayerId() {
        final Map<Season, List<TransferWindow>> seasonMap = new HashMap<>();
        for(final TransferWindow transferWindow : getEntities()) {
            final List<TransferWindow> transferWindows = seasonMap.computeIfAbsent(transferWindow.getMatchWeek().getSeason(), p -> new ArrayList<>());
            transferWindows.add(transferWindow);
        }

        for(final Map.Entry<Season, List<TransferWindow>> entry : seasonMap.entrySet()) {
            final Season season = entry.getKey();
            final List<TransferWindow> transferWindows = entry.getValue();

            transferWindows.sort(Comparator.comparing(TransferWindow::getDatetime).reversed());

            final List<TransferWindowDTO> result = getApiService().findTransferWindowBySeasonId(season.getId());

            assertNotNull(result, "Transfer window by season id should not be null.");
            assertEquals(map(transferWindows, TransferWindowDTO.class), result,
                    "Transfer windows by season id should equal transfer windows.");
        }

        assertTrue(getApiService().findTransferWindowBySeasonId(-1L).isEmpty(),
                "Transfer window by season id not found should be empty.");
    }

}
