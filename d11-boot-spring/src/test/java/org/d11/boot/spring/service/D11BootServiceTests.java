package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.D11TeamRepository;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.spring.repository.TeamRepository;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuilder;
import org.d11.boot.util.exception.ConflictException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * D11 Boot service tests.
 */
class D11BootServiceTests extends BaseD11BootServiceTests {

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
     * Mocked season repository.
     */
    @Mock
    private SeasonRepository seasonRepository;

    /**
     * Mocked D11 team repository.
     */
    @Mock
    private D11TeamRepository d11TeamRepository;

    /**
     * Mocked team repository.
     */
    @Mock
    private TeamRepository teamRepository;

    /**
     * Tests D11BootService::getRepository.
     */
    @Test
    void testGetRepository() {
        final D11BootService d11BootService = new D11BootService();

        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        d11BootService.setApplicationContext(this.applicationContext);

        final UserRepository result = d11BootService.getRepository(UserRepository.class);

        assertNotNull(result);
        assertEquals(this.userRepository, result);

        verify(this.applicationContext, times(1)).getBean(eq(UserRepository.class));
    }

    /**
     * Tests D11BootService::getCurrentUser.
     */
    @Test
    void testGetCurrentUser() {
        final D11BootService d11BootService = new D11BootService();

        assertFalse(d11BootService.getCurrentUser().isPresent());

        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        when(this.authentication.getPrincipal()).thenReturn(this.authentication);

        assertFalse(d11BootService.getCurrentUser().isPresent());

        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);

        d11BootService.setApplicationContext(this.applicationContext);

        final User user = new User();
        user.setEmail("user@email.com");

        when(this.jwt.getClaimAsString(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(user.getEmail());
        when(this.userRepository.findByEmail(eq(user.getEmail()))).thenReturn(Optional.of(user));

        final User result = d11BootService.getCurrentUser().orElse(null);

        assertNotNull(result);
        assertEquals(user, result);
    }

    /**
     * Tests D11BootService::getCurrentSeason.
     */
    @Test
    void testGetCurrentSeason() {
        final D11BootService d11BootService = new D11BootService();

        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        d11BootService.setApplicationContext(this.applicationContext);

        assertThrows(ConflictException.class, d11BootService::getCurrentSeason);

        final Season season = generate(Season.class);

        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));

        final Season result = d11BootService.getCurrentSeason();

        assertEquals(season, result);

        verify(this.seasonRepository, times(2)).findFirstByOrderByDateDesc();
    }

    /**
     * Tests D11BootService::getDefaultD11Team.
     */
    @Test
    void testGetDefaultD11Team() {
        final D11BootService d11BootService = new D11BootService();

        when(this.applicationContext.getBean(eq(D11TeamRepository.class))).thenReturn(this.d11TeamRepository);
        d11BootService.setApplicationContext(this.applicationContext);

        assertThrows(ConflictException.class, d11BootService::getDefaultD11Team);

        final D11Team d11Team = generate(D11Team.class);

        when(this.d11TeamRepository.findById(eq(D11Team.DEFAULT_D11_TEAM_ID))).thenReturn(Optional.of(d11Team));

        final D11Team result = d11BootService.getDefaultD11Team();

        assertEquals(d11Team, result);

        verify(this.d11TeamRepository, times(2)).findById(eq(D11Team.DEFAULT_D11_TEAM_ID));
    }

    /**
     * Tests D11BootService::getDefaultTeam.
     */
    @Test
    void testGetDefaultTeam() {
        final D11BootService d11BootService = new D11BootService();

        when(this.applicationContext.getBean(eq(TeamRepository.class))).thenReturn(this.teamRepository);
        d11BootService.setApplicationContext(this.applicationContext);

        assertThrows(ConflictException.class, d11BootService::getDefaultTeam);

        final Team team = generate(Team.class);

        when(this.teamRepository.findById(eq(Team.DEFAULT_TEAM_ID))).thenReturn(Optional.of(team));

        final Team result = d11BootService.getDefaultTeam();

        assertEquals(team, result);

        verify(this.teamRepository, times(2)).findById(eq(Team.DEFAULT_TEAM_ID));
    }

}
