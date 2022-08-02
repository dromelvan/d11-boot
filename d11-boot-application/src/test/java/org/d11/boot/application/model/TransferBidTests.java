package org.d11.boot.application.model;

import org.d11.boot.api.model.TransferBidDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer bid tests.
 */
public class TransferBidTests extends D11EasyRandomTests {

    /**
     * Tests transfer bid validity.
     */
    @Test
    public void isValid() {
        final int validFee = 25;
        final int invalidFee = 23;

        final TransferBid transferBid = generate(TransferBid.class);
        transferBid.setFee(validFee);

        assertTrue(transferBid.isValid(), "New transfer bid should be valid.");

        transferBid.setPlayerRanking(0);
        assertFalse(transferBid.isValid(), "Non positive player ranking should not be valid.");
        transferBid.setPlayerRanking(1);

        transferBid.setD11TeamRanking(0);
        assertFalse(transferBid.isValid(), "Non positive D11 team ranking should not be valid.");
        transferBid.setD11TeamRanking(1);

        transferBid.setFee(-1);
        assertFalse(transferBid.isValid(), "Non positive fee should not be valid.");
        transferBid.setFee(invalidFee);
        assertFalse(transferBid.isValid(), "Non divisible by 0.5 fee should not be valid.");
        transferBid.setFee(validFee);

        transferBid.setActiveFee(-1);
        assertFalse(transferBid.isValid(), "Negative active fee should not be valid.");
        transferBid.setActiveFee(1);

        transferBid.setTransferDay(null);
        assertFalse(transferBid.isValid(), "Null transfer day should not be valid.");
        transferBid.setTransferDay(new TransferDay());

        transferBid.setPlayer(null);
        assertFalse(transferBid.isValid(), "Null player should not be valid.");
        transferBid.setPlayer(new Player());

        transferBid.setD11Team(null);
        assertFalse(transferBid.isValid(), "Null D11 team should not be valid.");
        transferBid.setD11Team(new D11Team());

        assertTrue(transferBid.isValid(), "Transfer bid should be valid.");
    }

    /**
     * Tests mapping between TransferBid and TransferBidDTO.
     */
    @Test
    public void map() {
        final TransferBid transferBid = generate(TransferBid.class);

        final TransferBidDTO transferBidDTO = map(transferBid, TransferBidDTO.class);
        final TransferBid mappedTransferBid = map(transferBidDTO, TransferBid.class);

        assertEquals(transferBid, mappedTransferBid, "Transfer bid should equal mapped transfer bid.");
    }

}
