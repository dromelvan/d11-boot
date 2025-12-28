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
    void isValid() {
        final Transfer transferBid = generate(Transfer.class);

        final int validFee = 25;
        final int invalidFee = 23;

        transferBid.setFee(validFee);

        assertTrue(transferBid.isValid());

        transferBid.setFee(validFee * -1);
        assertFalse(transferBid.isValid());
        transferBid.setFee(invalidFee);
        assertFalse(transferBid.isValid());
        transferBid.setFee(validFee);

        transferBid.setTransferDay(null);
        assertFalse(transferBid.isValid());
        transferBid.setTransferDay(new TransferDay());

        transferBid.setPlayer(null);
        assertFalse(transferBid.isValid());
        transferBid.setPlayer(new Player());

        transferBid.setD11Team(null);
        assertFalse(transferBid.isValid());
        transferBid.setD11Team(new D11Team());

        assertTrue(transferBid.isValid());
    }

}
