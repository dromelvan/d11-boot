package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer tests.
 */
class TransferTests extends EasyRandomTests {

    /**
     * Tests Transfer::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void isValid() {
        final Transfer transferBid = generate(Transfer.class);

        final int validFee = 25;
        final int invalidFee = 23;

        transferBid.setFee(validFee);

        assertTrue(transferBid.isValid(), "Transfer::isValid");

        transferBid.setFee(validFee * -1);
        assertFalse(transferBid.isValid(), "Transfer::isValid fee negative");
        transferBid.setFee(invalidFee);
        assertFalse(transferBid.isValid(), "Transfer::isValid fee non divisible by 5");
        transferBid.setFee(validFee);

        transferBid.setTransferDay(null);
        assertFalse(transferBid.isValid(), "Transfer::isValid transfer day null");
        transferBid.setTransferDay(new TransferDay());

        transferBid.setPlayer(null);
        assertFalse(transferBid.isValid(), "Transfer::isValid player null");
        transferBid.setPlayer(new Player());

        transferBid.setD11Team(null);
        assertFalse(transferBid.isValid(), "Transfer::isValid D11 team null");
        transferBid.setD11Team(new D11Team());

        assertTrue(transferBid.isValid(), "Transfer::isValid valid");
    }

}
