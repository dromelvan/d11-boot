package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing tests.
 */
class TransferListingTests extends EasyRandomTests {

    /**
     * Tests TransferListing::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void isValid() {
        final TransferListing transferListing = generate(TransferListing.class);

        assertTrue(transferListing.isValid(), "TransferListing::isValid");

        transferListing.setFormMatchPoints(null);
        assertFalse(transferListing.isValid(), "TransferListing::isValid form match points null");
        transferListing.setFormMatchPoints(new ArrayList<>());

        transferListing.setTransferDay(null);
        assertFalse(transferListing.isValid(), "TransferListing::isValid transfer day null");
        transferListing.setTransferDay(new TransferDay());

        transferListing.setPlayer(null);
        assertFalse(transferListing.isValid(), "TransferListing::isValid player null");
        transferListing.setPlayer(new Player());

        transferListing.setTeam(null);
        assertFalse(transferListing.isValid(), "TransferListing::isValid team null");
        transferListing.setTeam(new Team());

        transferListing.setD11Team(null);
        assertFalse(transferListing.isValid(), "TransferListing::isValid D11 team null");
        transferListing.setD11Team(new D11Team());

        transferListing.setPosition(null);
        assertFalse(transferListing.isValid(), "TransferListing::isValid position null");
        transferListing.setPosition(new Position());

        assertTrue(transferListing.isValid(), "TransferListing::isValid valid");
    }

    /**
     * Tests TransferListing::init.
     */
    @Test
    void init() {
        final PlayerSeasonStat source = generate(PlayerSeasonStat.class);
        final TransferListing destination = generate(TransferListing.class);

        destination.init(source);

        assertEquals(source.getFormPoints(), destination.getFormPoints(), "TransferListing::init form points equals");
        assertEquals(source.getFormMatchPoints(), destination.getFormMatchPoints(),
                     "TransferListing::init form match points equals");
        assertEquals(source.getPlayer(), destination.getPlayer(), "TransferListing::init player equals");
        assertEquals(source.getTeam(), destination.getTeam(), "TransferListing::init team equals");
        assertEquals(source.getD11Team(), destination.getD11Team(), "TransferListing::init D11 team equals");
        assertEquals(source.getPosition(), destination.getPosition(), "TransferListing::init position equals");
    }

}
