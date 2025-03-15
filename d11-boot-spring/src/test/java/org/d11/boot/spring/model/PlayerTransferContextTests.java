package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player transfer context tests.
 */
class PlayerTransferContextTests extends EasyRandomTests {

    /**
     * Position count property name.
     */
    private static final String POSITION_COUNT = "positionCount";

    /**
     * Player count property name.
     */
    private static final String PLAYER_COUNT = "playerCount";

    /**
     * Fee sum property name.
     */
    private static final String FEE_SUM = "feeSum";

    /**
     * Transfer listing property name.
     */
    private static final String TRANSFER_LISTING = "transferListing";

    /**
     * Transfer count property name.
     */
    private static final String TRANSFER_COUNT = "transferCount";

    /**
     * Player D11 team property name.
     */
    private static final String PLAYER_D11_TEAM = "playerD11Team";

    /**
     * Tests PlayerTransferContext::getMaxBid.
     */
    @Test
    void testGetMaxBid() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);
        final Season season = context.getSeason();
        season.setD11TeamBudget(600);

        context.getTransferDay().setStatus(Status.ACTIVE);
        context.getTransferListing().setD11Team(generate(D11Team.class));

        ReflectionTestUtils.setField(context, POSITION_COUNT, context.getPosition().getMaxCount() - 1);
        ReflectionTestUtils.setField(context, FEE_SUM, 300);

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 0);
        assertEquals(250, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 0 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 1);
        assertEquals(255, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 1 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 2);
        assertEquals(260, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 2 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 3);
        assertEquals(265, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 3 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 4);
        assertEquals(270, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 4 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 5);
        assertEquals(275, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 5 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 6);
        assertEquals(280, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 6 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 7);
        assertEquals(285, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 7 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 8);
        assertEquals(290, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 8 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 9);
        assertEquals(295, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 9 equals");

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 10);
        assertEquals(300, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 10 equals");
    }

    /**
     * Tests PlayerTransferContext::getMaxBid with max player count.
     */
    @Test
    void testGetMaxBidMaxPlayerCount() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);
        final Season season = context.getSeason();
        season.setD11TeamBudget(600);

        context.getTransferDay().setStatus(Status.ACTIVE);
        context.getTransferListing().setD11Team(generate(D11Team.class));

        ReflectionTestUtils.setField(context, POSITION_COUNT, context.getPosition().getMaxCount() - 1);
        ReflectionTestUtils.setField(context, FEE_SUM, 300);

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 11);
        assertEquals(0, context.getMaxBid(), "PlayerTransferContext::getMaxBid playerCount 11 equals");
    }

    /**
     * Tests PlayerTransferContext::getMaxBid with invalid transfer day status.
     */
    @Test
    void testGetMaxBidInvalidTransferDayStatus() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);
        final Season season = context.getSeason();
        season.setD11TeamBudget(600);

        context.getTransferListing().setD11Team(generate(D11Team.class));

        ReflectionTestUtils.setField(context, POSITION_COUNT, context.getPosition().getMaxCount() - 1);
        ReflectionTestUtils.setField(context, FEE_SUM, 300);
        ReflectionTestUtils.setField(context, PLAYER_COUNT, 0);

        context.getTransferDay().setStatus(Status.PENDING);
        assertEquals(0, context.getMaxBid(), "PlayerTransferContext::getMaxBid transferDay status PENDING equals");

        context.getTransferDay().setStatus(Status.FULL_TIME);
        assertEquals(0, context.getMaxBid(), "PlayerTransferContext::getMaxBid transferDay status FULL_TIME equals");

        context.getTransferDay().setStatus(Status.FINISHED);
        assertEquals(0, context.getMaxBid(), "PlayerTransferContext::getMaxBid transferDay status FINISHED equals");

        context.getTransferDay().setStatus(Status.POSTPONED);
        assertEquals(0, context.getMaxBid(), "PlayerTransferContext::getMaxBid transferDay status POSTPONED equals");
    }

    /**
     * Tests PlayerTransferContext::getMaxBid with max position count.
     */
    @Test
    void testGetMaxBidMaxPositionCount() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);
        final Season season = context.getSeason();
        season.setD11TeamBudget(600);

        context.getTransferDay().setStatus(Status.ACTIVE);
        context.getTransferListing().setD11Team(generate(D11Team.class));

        ReflectionTestUtils.setField(context, PLAYER_COUNT, 0);
        ReflectionTestUtils.setField(context, FEE_SUM, 300);

        ReflectionTestUtils.setField(context, POSITION_COUNT, context.getPosition().getMaxCount());
        assertEquals(0, context.getMaxBid(), "PlayerTransferContext::getMaxBid max positionCount equals");
    }

    /**
     * Tests PlayerTransferContext::getMaxBid with transfer listing null.
     */
    @Test
    void testGetMaxBidTransferListingNull() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);
        final Season season = context.getSeason();
        season.setD11TeamBudget(600);

        context.getTransferDay().setStatus(Status.ACTIVE);

        ReflectionTestUtils.setField(context, POSITION_COUNT, context.getPosition().getMaxCount() - 1);
        ReflectionTestUtils.setField(context, FEE_SUM, 300);
        ReflectionTestUtils.setField(context, PLAYER_COUNT, 0);

        ReflectionTestUtils.setField(context, TRANSFER_LISTING, null);
        assertEquals(0, context.getMaxBid(), "PlayerTransferContext::getMaxBid null transferListing equals");
    }

    /**
     * Tests PlayerTransferContext::getMaxBid with invalid transfer listing D11 team.
     */
    @Test
    void testGetMaxBidInvalidTransferListingD11Team() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);
        final Season season = context.getSeason();
        season.setD11TeamBudget(600);

        context.getTransferDay().setStatus(Status.ACTIVE);
        context.getTransferListing().setD11Team(context.getD11Team());

        ReflectionTestUtils.setField(context, POSITION_COUNT, context.getPosition().getMaxCount() - 1);
        ReflectionTestUtils.setField(context, FEE_SUM, 300);
        ReflectionTestUtils.setField(context, PLAYER_COUNT, 0);

        assertEquals(0, context.getMaxBid(), "PlayerTransferContext::getMaxBid invalid transferListing d11Team equals");
    }

    /**
     * Tests PlayerTransferContext::isTransferListable.
     */
    @Test
    void testIsTransferListable() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferDay().setStatus(Status.PENDING);

        ReflectionTestUtils.setField(context, TRANSFER_COUNT, 0);
        ReflectionTestUtils.setField(context, PLAYER_D11_TEAM, context.getD11Team());
        ReflectionTestUtils.setField(context, TRANSFER_LISTING, null);

        assertTrue(context.isTransferListable(), "PlayerTransferContext::isTransferListable");
    }

    /**
     * Tests PlayerTransferContext::isTransferListable with invalid transfer day status.
     */
    @Test
    void testIsTransferListableInvalidTransferDayStatus() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        ReflectionTestUtils.setField(context, TRANSFER_COUNT, 0);
        ReflectionTestUtils.setField(context, PLAYER_D11_TEAM, context.getD11Team());
        ReflectionTestUtils.setField(context, TRANSFER_LISTING, null);

        context.getTransferDay().setStatus(Status.ACTIVE);
        assertFalse(context.isTransferListable(),
                    "PlayerTransferContext::isTransferListable transferDay status ACTIVE");

        context.getTransferDay().setStatus(Status.FULL_TIME);
        assertFalse(context.isTransferListable(),
                    "PlayerTransferContext::isTransferListable transferDay status FULL_TIME");

        context.getTransferDay().setStatus(Status.FINISHED);
        assertFalse(context.isTransferListable(),
                    "PlayerTransferContext::isTransferListable transferDay status FINISHED");

        context.getTransferDay().setStatus(Status.POSTPONED);
        assertFalse(context.isTransferListable(),
                    "PlayerTransferContext::isTransferListable transferDay status POSTPONED");
    }

    /**
     * Tests PlayerTransferContext::isTransferListable with max transfer count.
     */
    @Test
    void testIsTransferListableMaxTransferCount() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferDay().setStatus(Status.PENDING);

        ReflectionTestUtils.setField(context, TRANSFER_COUNT, context.getSeason().getD11TeamMaxTransfers());
        ReflectionTestUtils.setField(context, PLAYER_D11_TEAM, context.getD11Team());
        ReflectionTestUtils.setField(context, TRANSFER_LISTING, null);

        assertFalse(context.isTransferListable(),
                    "PlayerTransferContext::isTransferListable max transfer count");
    }

    /**
     * Tests PlayerTransferContext::isTransferListable with invalid D11 team.
     */
    @Test
    void testIsTransferListableInvalidD11Team() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferDay().setStatus(Status.PENDING);

        ReflectionTestUtils.setField(context, TRANSFER_COUNT, 0);
        ReflectionTestUtils.setField(context, TRANSFER_LISTING, null);

        ReflectionTestUtils.setField(context, PLAYER_D11_TEAM, generate(D11Team.class));
        assertFalse(context.isTransferListable(),
                    "PlayerTransferContext::isTransferListable invalid D11 team");
    }

    /**
     * Tests PlayerTransferContext::isTransferListable with transfer listing not null.
     */
    @Test
    void testIsTransferListableTransferListingNotNull() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferDay().setStatus(Status.PENDING);

        ReflectionTestUtils.setField(context, TRANSFER_COUNT, 0);
        ReflectionTestUtils.setField(context, PLAYER_D11_TEAM, context.getD11Team());
        ReflectionTestUtils.setField(context, TRANSFER_LISTING, generate(TransferListing.class));

        assertFalse(context.isTransferListable(), "PlayerTransferContext::isTransferListable transferListing not null");
    }

    /**
     * Tests PlayerTransferContext::getDeletableTransferListing.
     */
    @Test
    void testGetDeletableTransferListing() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferDay().setStatus(Status.PENDING);
        context.getTransferListing().setD11Team(context.getD11Team());

        assertEquals(context.getTransferListing(),
                     context.getDeletableTransferListing(),
                     "PlayerTransferContext::getDeletableTransferListing");
    }

    /**
     * Tests PlayerTransferContext::getDeletableTransferListing with invalid transfer day status.
     */
    @Test
    void testGetDeletableTransferListingInvalidTransferDayStatus() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferListing().setD11Team(context.getD11Team());

        context.getTransferDay().setStatus(Status.ACTIVE);
        assertNull(context.getDeletableTransferListing(),
                   "PlayerTransferContext::getDeletableTransferListing transferDay status ACTIVE");

        context.getTransferDay().setStatus(Status.FULL_TIME);
        assertNull(context.getDeletableTransferListing(),
                   "PlayerTransferContext::getDeletableTransferListing transferDay status FULL_TIME");

        context.getTransferDay().setStatus(Status.FINISHED);
        assertNull(context.getDeletableTransferListing(),
                   "PlayerTransferContext::getDeletableTransferListing transferDay status FINISHED");

        context.getTransferDay().setStatus(Status.POSTPONED);
        assertNull(context.getDeletableTransferListing(),
                   "PlayerTransferContext::getDeletableTransferListing transferDay status POSTPONED");
    }

    /**
     * Tests PlayerTransferContext::getDeletableTransferListing with invalid D11 team.
     */
    @Test
    void testGetDeletableTransferListingInvalidD11Team() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferDay().setStatus(Status.PENDING);
        context.getTransferListing().setD11Team(generate(D11Team.class));

        assertNull(context.getDeletableTransferListing(),
                   "PlayerTransferContext::getDeletableTransferListing invalid D11 team");
    }

    /**
     * Tests PlayerTransferContext::getActiveTransferBid.
     */
    @Test
    void testGetActiveTransferBid() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferDay().setStatus(Status.ACTIVE);
        context.getTransferBid().setD11Team(context.getD11Team());

        assertEquals(context.getTransferBid(),
                     context.getActiveTransferBid(),
                     "PlayerTransferContext::getActiveTransferBid");
    }

    /**
     * Tests PlayerTransferContext::getActiveTransferBid with invalid transfer day status.
     */
    @Test
    void testGetActiveTransferBidInvalidTransferDayStatus() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferBid().setD11Team(context.getD11Team());

        context.getTransferDay().setStatus(Status.PENDING);
        assertNull(context.getActiveTransferBid(), "PlayerTransferContext::getActiveTransferBid status PENDING");

        context.getTransferDay().setStatus(Status.FULL_TIME);
        assertNull(context.getActiveTransferBid(), "PlayerTransferContext::getActiveTransferBid status FULL_TIME");

        context.getTransferDay().setStatus(Status.FINISHED);
        assertNull(context.getActiveTransferBid(), "PlayerTransferContext::getActiveTransferBid status FINISHED");

        context.getTransferDay().setStatus(Status.POSTPONED);
        assertNull(context.getActiveTransferBid(), "PlayerTransferContext::getActiveTransferBid status POSTPONED");
    }

    /**
     * Tests PlayerTransferContext::getActiveTransferBid invalid D11 team.
     */
    @Test
    void testGetActiveTransferBidInvalidD11Team() {
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        context.getTransferDay().setStatus(Status.ACTIVE);
        context.getTransferBid().setD11Team(generate(D11Team.class));

        assertNull(context.getActiveTransferBid(), "PlayerTransferContext::getActiveTransferBid invalid D11 team");
    }

}
