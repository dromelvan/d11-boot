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
    @SuppressWarnings("DataFlowIssue")
    void isValid() {
        final TransferBid transferBid = generate(TransferBid.class);

        final int validFee = 25;
        final int invalidFee = 23;

        transferBid.setFee(validFee);
        transferBid.setActiveFee(validFee);

        assertTrue(transferBid.isValid(), "TransferBid::isValid");

        transferBid.setPlayerRanking(0);
        assertFalse(transferBid.isValid(), "TransferBid::isValid player ranking non positive");
        transferBid.setPlayerRanking(1);

        transferBid.setD11TeamRanking(0);
        assertFalse(transferBid.isValid(), "TransferBid::isValid team ranking non positive");
        transferBid.setD11TeamRanking(1);

        transferBid.setFee(validFee * -1);
        assertFalse(transferBid.isValid(), "TransferBid::isValid fee negative");
        transferBid.setFee(invalidFee);
        assertFalse(transferBid.isValid(), "TransferBid::isValid fee non divisible by 5");
        transferBid.setFee(validFee);

        transferBid.setActiveFee(validFee * -1);
        assertFalse(transferBid.isValid(), "TransferBid::isValid active fee negative");
        transferBid.setActiveFee(invalidFee);
        assertFalse(transferBid.isValid(), "TransferBid::isValid active fee non divisible by 5");
        transferBid.setActiveFee(validFee);

        transferBid.setTransferDay(null);
        assertFalse(transferBid.isValid(), "TransferBid::isValid transfer day null");
        transferBid.setTransferDay(new TransferDay());

        transferBid.setPlayer(null);
        assertFalse(transferBid.isValid(), "TransferBid::isValid player null");
        transferBid.setPlayer(new Player());

        transferBid.setD11Team(null);
        assertFalse(transferBid.isValid(), "TransferBid::isValid D11 team null");
        transferBid.setD11Team(new D11Team());

        assertTrue(transferBid.isValid(), "TransferBid::isValid valid");
    }

    /**
     * Tests TransferBid::prePersist.
     */
    @Test
    void prePersist() {
        final TransferBid transferBid = generate(TransferBid.class);
        transferBid.setActiveFee(transferBid.getFee() * 2);

        transferBid.prePersist();

        assertEquals(transferBid.getFee(), transferBid.getActiveFee(), "TransferBid::prePersist");
    }

}
