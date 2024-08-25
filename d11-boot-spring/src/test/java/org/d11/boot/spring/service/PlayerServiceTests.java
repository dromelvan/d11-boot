package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.repository.CountryRepository;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.util.Parameterizer;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.NotFoundException;
import org.d11.boot.util.exception.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Player service tests.
 */
class PlayerServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked player repository.
     */
    @Mock
    private PlayerRepository playerRepository;

    /**
     * Mocked country repository.
     */
    @Mock
    private CountryRepository countryRepository;

    /**
     * Player service.
     */
    @InjectMocks
    private PlayerService playerService;

    /**
     * Tests PlayerService::getById.
     */
    @Test
    void testGetById() {
        final List<Player> players = generateList(Player.class);
        when(this.playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        for (final Player player : players) {
            when(this.playerRepository.findById(player.getId())).thenReturn(Optional.of(player));

            final Player result = this.playerService.getById(player.getId());
            assertNotNull(result, "PlayerService::getById not null");
            assertEquals(player, result, "PlayerService::getById");
        }

        assertThrows(NotFoundException.class,
                     () -> this.playerService.getById(-1L),
                     "PlayerService::getById not found");
    }

    /**
     * Tests PlayerService::updatePlayer.
     */
    @Test
    void testUpdatePlayer() {
        final Player player = generate(Player.class);
        final PlayerInput playerInput = new PlayerInput(
                123,
                321,
                "NEW_FIRST_NAME",
                "NEW_LAST_NAME",
                "NEW_FULL_NAME",
                LocalDate.now(),
                456,
                player.getCountry().getId(),
                true
        );

        final BadRequestException e = assertThrows(
                BadRequestException.class,
                () -> this.playerService.updatePlayer(player.getId(),
                                                      new PlayerInput(-1, -1, "", null, null, null, -1, 1, true)));

        final List<String> properties = Arrays.asList("height", "lastName", "premierLeagueId", "whoscoredId");
        assertEquals(properties, e.getValidationErrors().stream().map(ValidationError::property).toList(),
                     "PlayerService::updatePlayer validation error properties equals");

        when(this.playerRepository.findById(eq(player.getId()))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.playerService.updatePlayer(player.getId(), playerInput));

        when(this.playerRepository.findById(eq(player.getId()))).thenReturn(Optional.of(player));

        assertThrows(NotFoundException.class, () -> this.playerService.updatePlayer(player.getId(), playerInput));

        when(this.countryRepository.findById(eq(player.getCountry().getId())))
                .thenReturn(Optional.of(player.getCountry()));
        when(this.playerRepository.save(any(Player.class))).then(AdditionalAnswers.returnsFirstArg());

        final Player result = this.playerService.updatePlayer(player.getId(), playerInput);

        assertEquals(player.getId(), result.getId(), "PlayerService::updatePlayer id equals");
        assertEquals(player.getWhoscoredId(), result.getWhoscoredId(),
                     "PlayerService::updatePlayer whoscoredId equals");
        assertEquals(player.getPremierLeagueId(), result.getPremierLeagueId(),
                     "PlayerService::updatePlayer premierLeagueId equals");
        assertEquals(player.getFirstName(), result.getFirstName(), "PlayerService::updatePlayer firstName equals");
        assertEquals(player.getLastName(), result.getLastName(), "PlayerService::updatePlayer lastName equals");
        assertEquals(player.getFullName(), result.getFullName(), "PlayerService::updatePlayer fullName equals");
        assertEquals(player.getParameterizedName(), result.getParameterizedName(),
                     "PlayerService::updatePlayer parameterizedName equals");
        assertEquals(player.getDateOfBirth(), result.getDateOfBirth(),
                     "PlayerService::updatePlayer dateOfBirth equals");
        assertEquals(player.getHeight(), result.getHeight(), "PlayerService::updatePlayer height equals");
        assertEquals(player.getPhotoFileName(), result.getPhotoFileName(),
                     "PlayerService::updatePlayer photoFileName equals");
        assertEquals(player.isVerified(), result.isVerified(), "PlayerService::updatePlayer verified equals");
        assertEquals(player.getCountry(), result.getCountry(), "PlayerService::updatePlayer country equals");
        assertEquals(player.getCreatedAt(), result.getCreatedAt(), "PlayerService::updatePlayer createdAt equals");
        assertEquals(player.getUpdatedAt(), result.getUpdatedAt(), "PlayerService::updatePlayer updatedAt equals");

        verify(this.playerRepository, times(3)).findById(any(Long.class));
        verify(this.playerRepository, times(1)).save(eq(player));
    }

    /**
     * Tests PlayerService::searchByName.
     */
    @Test
    void testFindByName() {
        final String nameProperty = "name";

        final BadRequestException nullNameException =
                assertThrows(BadRequestException.class, () -> this.playerService.searchByName(null),
                             "PlayerService::searchByName null name throws");
        assertEquals(nameProperty, nullNameException.getParameter(),
                     "PlayerService::searchByName property equals null");

        final BadRequestException emptyNameException =
                assertThrows(BadRequestException.class, () -> this.playerService.searchByName(null),
                             "PlayerService::searchByName empty name throws");
        assertEquals(nameProperty, emptyNameException.getParameter(),
                     "PlayerService::searchByName property equals empty");

        final List<Player> players = generateList(Player.class);

        final String exact = "\"";

        for (final Player player : players) {
            final String likeName = Arrays.stream(player.getName().split("[ +]"))
                    .map(Parameterizer::parameterize)
                    .collect(Collectors.joining(PlayerService.SQL_LIKE));

            final PlayerSearchResult playerSearchResult = mock(PlayerSearchResult.class);

            when(this.playerRepository
                    .findByParameterizedNameLike(eq(PlayerService.SQL_LIKE + likeName + PlayerService.SQL_LIKE)))
                    .thenReturn(Collections.singletonList(playerSearchResult));

            final List<PlayerSearchResult> likeResult = this.playerService.searchByName(player.getName());

            assertEquals(1, likeResult.size(), "PlayerService::searchByName like size equals");
            assertEquals(playerSearchResult, likeResult.get(0), "PlayerService::searchByName like result equals");

            // Update mocked parameterized name since it won't be correct otherwise
            player.preUpdate();

            when(this.playerRepository
                    .findByParameterizedNameExact(eq(player.getParameterizedName())))
                    .thenReturn(Collections.singletonList(playerSearchResult));

            final List<PlayerSearchResult> exactResult =
                    this.playerService.searchByName(exact + player.getName() + exact);

            assertEquals(1, exactResult.size(), "PlayerService::searchByName exact size equals");
            assertEquals(playerSearchResult, exactResult.get(0), "PlayerService::searchByName exact result equals");

            verify(this.playerRepository, times(1))
                    .findByParameterizedNameLike(eq(PlayerService.SQL_LIKE + likeName + PlayerService.SQL_LIKE));
            verify(this.playerRepository, times(1)).findByParameterizedNameExact(eq(player.getParameterizedName()));
        }
    }

}
