package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.PlayerTransferContextRepository;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

/**
 * Player transfer context service tests.
 */
class PlayerTransferContextServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked authentication.
     */
    @Mock
    private Authentication authentication;

    /**
     * Mocked JWT.
     */
    @Mock
    private Jwt jwt;

    /**
     * Mocked application context.
     */
    @Mock
    private ApplicationContext applicationContext;

    /**
     * Mocked user repository.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Mocked player transfer context repository.
     */
    @Mock
    private PlayerTransferContextRepository playerTransferContextRepository;

    /**
     * Player transfer context service.
     */
    @InjectMocks
    private PlayerTransferContextService playerTransferContextService;

    /**
     * Tests PlayerTransferContextService::getByPlayerId with no current user.
     */
    @Test
    void testGetByPlayerIdNoCurrentUser() {
        final Player player = generate(Player.class);

        SecurityContextHolder.getContext().setAuthentication(null);
        final PlayerTransferContext context = this.playerTransferContextService.getByPlayerId(player.getId());

        assertNotNull(context, "PlayerTransferContextService::getByPlayerId no current user not null");
        assertNull(context.getId(), "PlayerTransferContextService::getByPlayerId no current user id not null");

        assertFalse(context.isTransferListable(),
                    "PlayerTransferContextService::getByPlayerId no current user isTransferListable");
        assertNull(context.getDeletableTransferListing(),
                   "PlayerTransferContextService::getByPlayerId no current user deletableTransferListing");
        assertEquals(0, context.getMaxBid(),
                     "PlayerTransferContextService::getByPlayerId no current user maxBid equals");
        assertNull(context.getActiveTransferBid(),
                   "PlayerTransferContextService::getByPlayerId no current user activeTransferBid");

        verifyNoInteractions(this.playerTransferContextRepository);
    }

    /**
     * Tests PlayerTransferContextService::getByPlayerId with player transfer context not found.
     */
    @Test
    void testGetByPlayerIdNotFound() {
        final Player player = generate(Player.class);
        final User owner = generate(User.class);

        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        when(this.jwt.getClaimAsString(JwtBuilder.USERNAME_CLAIM)).thenReturn(JwtBuilder.USERNAME_CLAIM);
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.userRepository.findByEmail(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(Optional.of(owner));

        when(this.playerTransferContextRepository.findByPlayerIdAndOwnerId(player.getId(), owner.getId()))
                .thenReturn(Optional.empty());

        final PlayerTransferContext context = this.playerTransferContextService.getByPlayerId(player.getId());

        assertNotNull(context, "PlayerTransferContextService::getByPlayerId not found not null");
        assertNull(context.getId(), "PlayerTransferContextService::getByPlayerId not found id not null");

        assertFalse(context.isTransferListable(),
                    "PlayerTransferContextService::getByPlayerId not found isTransferListable");
        assertNull(context.getDeletableTransferListing(),
                   "PlayerTransferContextService::getByPlayerId not found deletableTransferListing");
        assertEquals(0, context.getMaxBid(),
                     "PlayerTransferContextService::getByPlayerId not found maxBid equals");
        assertNull(context.getActiveTransferBid(),
                   "PlayerTransferContextService::getByPlayerId not found activeTransferBid");

        verify(this.playerTransferContextRepository, times(1)).findByPlayerIdAndOwnerId(eq(player.getId()),
                                                                                        eq(owner.getId()));
    }

    /**
     * Tests PlayerTransferContextService::getByPlayerId.
     */
    @Test
    void testGetByPlayerId() {
        final Player player = generate(Player.class);
        final User owner = generate(User.class);
        final PlayerTransferContext context = generate(PlayerTransferContext.class);

        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        when(this.jwt.getClaimAsString(JwtBuilder.USERNAME_CLAIM)).thenReturn(JwtBuilder.USERNAME_CLAIM);
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.userRepository.findByEmail(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(Optional.of(owner));

        when(this.playerTransferContextRepository.findByPlayerIdAndOwnerId(player.getId(), owner.getId()))
                .thenReturn(Optional.of(context));

        final PlayerTransferContext result = this.playerTransferContextService.getByPlayerId(player.getId());

        assertNotNull(result, "PlayerTransferContextService::getByPlayerId not null");
        assertEquals(context, result, "PlayerTransferContextService::getByPlayerId result equals");

        verify(this.playerTransferContextRepository, times(1)).findByPlayerIdAndOwnerId(eq(player.getId()),
                                                                                        eq(owner.getId()));
    }

}
