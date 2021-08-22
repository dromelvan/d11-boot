package org.d11.boot.application.model;

import org.d11.boot.api.model.TransferListingDTO;
import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.Player;
import org.d11.boot.application.model.jpa.Position;
import org.d11.boot.application.model.jpa.Team;
import org.d11.boot.application.model.jpa.TransferDay;
import org.d11.boot.application.model.jpa.TransferListing;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing tests.
 */
public class TransferListingTests extends D11EasyRandomTests {

    /**
     * Tests transfer listing validity.
     */
    @Test
    public void isValid() {
        final TransferListing transferListing = generate(TransferListing.class);

        assertTrue(transferListing.isValid(), "New transfer listing should be valid.");

        transferListing.setFormMatchPoints(null);
        assertFalse(transferListing.isValid(), "Null form match points should not be valid.");
        transferListing.setFormMatchPoints(new ArrayList<>());

        transferListing.setTransferDay(null);
        assertFalse(transferListing.isValid(), "Null transfer day should not be valid.");
        transferListing.setTransferDay(new TransferDay());

        transferListing.setPlayer(null);
        assertFalse(transferListing.isValid(), "Null player should not be valid.");
        transferListing.setPlayer(new Player());

        transferListing.setTeam(null);
        assertFalse(transferListing.isValid(), "Null team should not be valid.");
        transferListing.setTeam(new Team());

        transferListing.setD11Team(null);
        assertFalse(transferListing.isValid(), "Null D11 team should not be valid.");
        transferListing.setD11Team(new D11Team());

        transferListing.setPosition(null);
        assertFalse(transferListing.isValid(), "Null position should not be valid.");
        transferListing.setPosition(new Position());

        assertTrue(transferListing.isValid(), "Transfer listing should be valid.");
    }

    /**
     * Tests mapping between TransferListing and TransferListingDTO.
     */
    @Test
    public void map() {
        final TransferListing transferListing = generate(TransferListing.class);

        final TransferListingDTO transferListingDTO = map(transferListing, TransferListingDTO.class);
        final TransferListing mappedTransferListing = map(transferListingDTO, TransferListing.class);

        assertEquals(transferListing, mappedTransferListing, "Transfer listing should equal mapped transfer listing.");
    }

}
