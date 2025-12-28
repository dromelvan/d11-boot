package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer bid tests.
 */
class TransferBidTests extends EasyRandomTests {

    /**
     * Tests TransferBid::isValid.
     */
    @Test
    void isValid() {
        final TransferBid transferBid = generate(TransferBid.class);

        final int validFee = 25;
        final int invalidFee = 23;

        transferBid.setFee(validFee);
        transferBid.setActiveFee(validFee);

        assertTrue(transferBid.isValid());

        transferBid.setPlayerRanking(0);
        assertFalse(transferBid.isValid());
        transferBid.setPlayerRanking(1);

        transferBid.setD11TeamRanking(0);
        assertFalse(transferBid.isValid());
        transferBid.setD11TeamRanking(1);

        transferBid.setFee(validFee * -1);
        assertFalse(transferBid.isValid());
        transferBid.setFee(invalidFee);
        assertFalse(transferBid.isValid());
        transferBid.setFee(validFee);

        transferBid.setActiveFee(validFee * -1);
        assertFalse(transferBid.isValid());
        transferBid.setActiveFee(invalidFee);
        assertFalse(transferBid.isValid());
        transferBid.setActiveFee(validFee);

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

    /**
     * Tests TransferBid::prePersist.
     */
    @Test
    void prePersist() {
        final TransferBid transferBid = generate(TransferBid.class);
        transferBid.setActiveFee(transferBid.getFee() * 2);

        transferBid.prePersist();

        assertEquals(transferBid.getFee(), transferBid.getActiveFee());
    }

}
