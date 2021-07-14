package org.d11.boot.application.model;

import org.d11.boot.api.model.TransferWindowDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer window tests.
 */
public class TransferWindowTests extends D11EasyRandomTests {

    /**
     * Tests transfer window validity.
     */
    @Test
    public void isValid() {
        final TransferWindow transferWindow = generate(TransferWindow.class);

        assertTrue(transferWindow.isValid(), "New transfer window should be valid.");

        transferWindow.setTransferWindowNumber(-1);
        assertFalse(transferWindow.isValid(), "Negative whoscoredId should not be valid.");
        transferWindow.setTransferWindowNumber(1);

        transferWindow.setStatus(null);
        assertFalse(transferWindow.isValid(), "Null status not be valid.");
        transferWindow.setStatus(Status.PENDING);

        transferWindow.setDatetime(null);
        assertFalse(transferWindow.isValid(), "Null datetime not be valid.");
        transferWindow.setDatetime(LocalDateTime.now());

        transferWindow.setMatchWeek(null);
        assertFalse(transferWindow.isValid(), "Null match week not be valid.");
        transferWindow.setMatchWeek(new MatchWeek());

        assertTrue(transferWindow.isValid(), "Transfer window should be valid.");
    }

    /**
     * Tests mapping between TransferWindow and TransferWindowDTO.
     */
    @Test
    public void map() {
        final TransferWindow transferWindow = generate(TransferWindow.class);

        final TransferWindowDTO transferWindowDTO = map(transferWindow, TransferWindowDTO.class);
        final TransferWindow mappedTransferWindow = map(transferWindowDTO, TransferWindow.class);

        assertEquals(transferWindow, mappedTransferWindow, "Transfer window should equal mapped transfer window.");
    }

}
