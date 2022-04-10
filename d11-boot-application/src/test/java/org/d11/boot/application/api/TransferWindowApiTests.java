package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.TransferWindowDTO;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.TransferWindow;
import org.d11.boot.application.repository.TransferWindowRepository;
import org.d11.boot.client.api.TransferWindowApi;
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
 * Transfer window API tests.
 */
public class TransferWindowApiTests extends AbstractRepositoryApiTests<TransferWindow, TransferWindowRepository> {

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
        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);
        for(final TransferWindow transferWindow : getRepository().findAll()) {
            final TransferWindowDTO result = transferWindowApi.findTransferWindowById(transferWindow.getId());
            final TransferWindowDTO transferWindowDTO = map(transferWindow, TransferWindowDTO.class);
            assertNotNull(result, "Transfer window by id should not be null.");
            assertEquals(transferWindowDTO, result, "Transfer window by id should equal TransferWindow.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> transferWindowApi.findTransferWindowById(-1L),
                     "Transfer window not found should throw NotFound exception.");
    }

    /**
     * Tests the findCurrentTransferWindow API operation.
     */
    @Test
    public void findCurrentTransferWindow() {
        final TransferWindow currentTransferWindow = getEntities().get(0);
        final TransferWindowDTO transferWindowDTO = map(currentTransferWindow, TransferWindowDTO.class);

        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);
        final TransferWindowDTO result = transferWindowApi.findCurrentTransferWindow();
        assertNotNull(result, "Current transfer window should not be null.");
        assertEquals(transferWindowDTO, result, "Current transfer window result should equal current transfer window.");
    }

    /**
     * Tests the findTransferWindowBySeasonId API operation.
     */
    @Test
    public void findTransferWindowBySeasonId() {
        final Map<Season, List<TransferWindow>> seasonMap = new HashMap<>();
        for(final TransferWindow transferWindow : getEntities()) {
            final List<TransferWindow> transferWindows = seasonMap.computeIfAbsent(transferWindow.getMatchWeek().getSeason(), p -> new ArrayList<>());
            transferWindows.add(transferWindow);
        }

        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);
        for(final Map.Entry<Season, List<TransferWindow>> entry : seasonMap.entrySet()) {
            final Season season = entry.getKey();
            final List<TransferWindow> transferWindows = entry.getValue();

            transferWindows.sort(Comparator.comparing(TransferWindow::getDatetime).reversed());

            final List<TransferWindowDTO> result = transferWindowApi.findTransferWindowBySeasonId(season.getId());

            assertNotNull(result, "Transfer window by season id should not be null.");
            assertEquals(map(transferWindows, TransferWindowDTO.class), result,
                    "Transfer windows by season id should equal transfer windows.");
        }

        assertTrue(transferWindowApi.findTransferWindowBySeasonId(-1L).isEmpty(),
                "Transfer window by season id not found should be empty.");
    }

}
