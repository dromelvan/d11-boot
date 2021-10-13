package org.d11.boot.application.model;

import org.d11.boot.api.model.TransferDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer tests.
 */
public class TransferTests extends D11EasyRandomTests {

    /**
     * Tests transfer validity.
     */
    @Test
    public void isValid() {
        final Transfer transfer = generate(Transfer.class);

        assertTrue(transfer.isValid(), "New transfer should be valid.");

        transfer.setFee(0);
        assertFalse(transfer.isValid(), "Non positive fee should not be valid.");
        transfer.setFee(1);

        transfer.setTransferDay(null);
        assertFalse(transfer.isValid(), "Null transfer day should not be valid.");
        transfer.setTransferDay(new TransferDay());

        transfer.setPlayer(null);
        assertFalse(transfer.isValid(), "Null player should not be valid.");
        transfer.setPlayer(new Player());

        transfer.setD11Team(null);
        assertFalse(transfer.isValid(), "Null D11 team should not be valid.");
        transfer.setD11Team(new D11Team());

        assertTrue(transfer.isValid(), "Transfer should be valid.");
    }

    /**
     * Tests mapping between Transfer and TransferDTO.
     */
    @Test
    public void map() {
        final Transfer transfer = generate(Transfer.class);

        final TransferDTO transferDTO = map(transfer, TransferDTO.class);
        final Transfer mappedTransfer = map(transferDTO, Transfer.class);

        assertEquals(transfer, mappedTransfer, "Transfer should equal mapped transfer.");
    }

}
