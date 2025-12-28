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
    void isValid() {
        final TransferListing transferListing = generate(TransferListing.class);

        assertTrue(transferListing.isValid());

        transferListing.setFormMatchPoints(null);
        assertFalse(transferListing.isValid());
        transferListing.setFormMatchPoints(new ArrayList<>());

        transferListing.setTransferDay(null);
        assertFalse(transferListing.isValid());
        transferListing.setTransferDay(new TransferDay());

        transferListing.setPlayer(null);
        assertFalse(transferListing.isValid());
        transferListing.setPlayer(new Player());

        transferListing.setTeam(null);
        assertFalse(transferListing.isValid());
        transferListing.setTeam(new Team());

        transferListing.setD11Team(null);
        assertFalse(transferListing.isValid());
        transferListing.setD11Team(new D11Team());

        transferListing.setPosition(null);
        assertFalse(transferListing.isValid());
        transferListing.setPosition(new Position());

        assertTrue(transferListing.isValid());
    }

    /**
     * Tests TransferListing::init.
     */
    @Test
    void init() {
        final PlayerSeasonStat source = generate(PlayerSeasonStat.class);
        final TransferListing destination = generate(TransferListing.class);

        destination.init(source);

        assertEquals(source.getFormPoints(), destination.getFormPoints());
        assertEquals(source.getFormMatchPoints(), destination.getFormMatchPoints());
        assertEquals(source.getPlayer(), destination.getPlayer());
        assertEquals(source.getTeam(), destination.getTeam());
        assertEquals(source.getD11Team(), destination.getD11Team());
        assertEquals(source.getPosition(), destination.getPosition());
    }

}
