package org.d11.boot.application.model;

import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.application.model.jpa.Status;
import org.d11.boot.application.model.jpa.TransferDay;
import org.d11.boot.application.model.jpa.TransferWindow;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer day tests.
 */
public class TransferDayTests extends D11EasyRandomTests {

    /**
     * Tests transfer day validity.
     */
    @Test
    public void isValid() {
        final TransferDay transferDay = generate(TransferDay.class);

        assertTrue(transferDay.isValid(), "New transfer day should be valid.");

        transferDay.setTransferDayNumber(-1);
        assertFalse(transferDay.isValid(), "Negative transfer day number should not be valid.");
        transferDay.setTransferDayNumber(1);

        transferDay.setStatus(null);
        assertFalse(transferDay.isValid(), "Null status should not be valid.");
        transferDay.setStatus(Status.PENDING);

        transferDay.setDatetime(null);
        assertFalse(transferDay.isValid(), "Null datetime should not be valid.");
        transferDay.setDatetime(LocalDateTime.now());

        transferDay.setTransferWindow(null);
        assertFalse(transferDay.isValid(), "Null transfer window should not be valid.");
        transferDay.setTransferWindow(new TransferWindow());

        assertTrue(transferDay.isValid(), "Transfer day should be valid.");
    }

    /**
     * Tests mapping between TransferDay and TransferDayDTO.
     */
    @Test
    public void map() {
        final TransferDay transferDay = generate(TransferDay.class);

        final TransferDayDTO transferDayDTO = map(transferDay, TransferDayDTO.class);
        final TransferDay mappedTransferDay = map(transferDayDTO, TransferDay.class);

        assertEquals(transferDay, mappedTransferDay, "Transfer day should equal mapped transfer day.");
    }

}
