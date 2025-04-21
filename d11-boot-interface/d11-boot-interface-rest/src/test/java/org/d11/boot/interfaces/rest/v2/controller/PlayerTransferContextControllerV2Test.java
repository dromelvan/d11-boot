package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.client.PlayerTransferContextApi;
import org.d11.boot.api.v2.model.PlayerTransferContextDTO;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.spring.repository.PlayerTransferContextRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer listing controller tests.
 */
class PlayerTransferContextControllerV2Test extends D11BootControllerV2Tests {

    /**
     * Player repository.
     */
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Player transfer context repository.
     */
    @Autowired
    private PlayerTransferContextRepository playerTransferContextRepository;

    // getPlayerTransferContextByPlayerId ------------------------------------------------------------------------------

    /**
     * Tests PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId for unauthorized.
     */
    @Test
    void testGetPlayerTransferContextByPlayerIdUnauthorized() {
        final PlayerTransferContextApi playerTransferContextApi = getApi(PlayerTransferContextApi.class);

        final List<Player> players = this.playerRepository.findAll();

        for (final Player player : players) {
            final PlayerTransferContextDTO result =
                    playerTransferContextApi.getPlayerTransferContextByPlayerId(player.getId())
                            .getPlayerTransferContext();

            assertNull(result.getPlayerId(), """
                    PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId unauthorized playerId null
                    """);
            assertFalse(result.isTransferListable(), """
                    PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId unauthorized transferListable
                    """);
            assertNull(result.getDeletableTransferListingId(), """ 
                    PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId unauthorized
                    deletableTransferListingId null
                    """);
            assertEquals(0, result.getMaxBid(),
                    "PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId unauthorized maxBid equals");
            assertNull(result.getActiveTransferBid(), """
                    PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId unauthorized
                    activeTransferBidId null
                    """);
        }
    }

    /**
     * Tests PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId.
     */
    @Test
    void testGetPlayerTransferContextByPlayerId() {
        final PlayerTransferContextApi playerTransferContextApi = getUserApi(PlayerTransferContextApi.class);

        final List<Player> players = this.playerRepository.findAll();

        int expectedCount = 0;

        for (final Player player : players) {
            final PlayerTransferContextDTO result =
                    playerTransferContextApi.getPlayerTransferContextByPlayerId(player.getId())
                            .getPlayerTransferContext();

            final PlayerTransferContext expected = this.playerTransferContextRepository
                    .findByPlayerIdAndOwnerId(player.getId(), 1L).orElse(null);

            assertNotNull(result,
                          "PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId result not null");

            if (expected == null) {
                assertNull(result.getPlayerId(), """
                        PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId not found playerId null
                        """);
                assertFalse(result.isTransferListable(), """
                        PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId not found transferListable
                        """);
                assertNull(result.getDeletableTransferListingId(), """
                        PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId not found
                        deletableTransferListingId null
                        """);
                assertEquals(0, result.getMaxBid(), """
                        PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId not found maxBid equals
                        """);
                assertNull(result.getActiveTransferBid(), """
                        PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId not found
                        activeTransferBidId null
                        """);
            } else {
                ++expectedCount;

                assertEquals(expected.getPlayer().getId(), result.getPlayerId(), """
                        PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId playerId equals
                        """);
                assertEquals(expected.isTransferListable(), result.isTransferListable(), """
                        PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId transferListable
                        """);

                if (expected.getDeletableTransferListing() == null) {
                    assertNull(result.getDeletableTransferListingId(), """
                            PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId
                            deletableTransferListing null deletableTransferListingId null
                            """);
                } else {
                    assertEquals(expected.getDeletableTransferListing().getId(), result.getDeletableTransferListingId(),
                                         """
                                         PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId
                                         deletableTransferListingId eq
                                         """);
                }

                assertEquals(expected.getMaxBid(), result.getMaxBid(), """
                        PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId maxBid equals
                        """);

                if (expected.getActiveTransferBid() == null) {
                    assertNull(result.getActiveTransferBid(), """
                            PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId activeTransferBid null
                            activeTransferBidId null
                            """);
                } else {
                    assertEquals(expected.getActiveTransferBid().getId(), result.getActiveTransferBid().getId(), """
                            PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId activeTransferBid.id
                            equals
                            """);
                    assertEquals(expected.getActiveTransferBid().getFee(), result.getActiveTransferBid().getFee(), """
                            PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId activeTransferBid.fee
                            equals
                            """);
                }
            }
        }

        assertTrue(expectedCount > 0,
                   "PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId valid test setup");
    }

}
