package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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

            assertNull(result.getPlayerId());
            assertFalse(result.isTransferListable());
            assertNull(result.getDeletableTransferListingId());
            assertEquals(0, result.getMaxBid());
            assertNull(result.getActiveTransferBid());
        }
    }

    /**
     * Tests PlayerTransferContextControllerV2::getPlayerTransferContextByPlayerId for bad request.
     */
    @Test
    void testGetPlayerTransferContextByPlayerIdBadRequest() {
        final PlayerTransferContextApi playerTransferContextApi = getUserApi(PlayerTransferContextApi.class);

        assertThrows(FeignException.BadRequest.class,
                () -> playerTransferContextApi.getPlayerTransferContextByPlayerId(null));
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

            assertNotNull(result);

            if (expected == null) {
                assertNull(result.getPlayerId());
                assertFalse(result.isTransferListable());
                assertNull(result.getDeletableTransferListingId());
                assertEquals(0, result.getMaxBid());
                assertNull(result.getActiveTransferBid());
            } else {
                ++expectedCount;

                assertEquals(expected.getPlayer().getId(), result.getPlayerId());
                assertEquals(expected.isTransferListable(), result.isTransferListable());

                if (expected.getDeletableTransferListing() == null) {
                    assertNull(result.getDeletableTransferListingId());
                } else {
                    assertEquals(expected.getDeletableTransferListing().getId(),
                                 result.getDeletableTransferListingId());
                }

                assertEquals(expected.getMaxBid(), result.getMaxBid());

                if (expected.getActiveTransferBid() == null) {
                    assertNull(result.getActiveTransferBid());
                } else {
                    assertEquals(expected.getActiveTransferBid().getId(), result.getActiveTransferBid().getId());
                    assertEquals(expected.getActiveTransferBid().getFee(), result.getActiveTransferBid().getFee());
                }
            }
        }

        assertTrue(expectedCount > 0);
    }

}
