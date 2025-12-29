package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.PlayerApi;
import org.d11.boot.api.v2.model.PlayerDTO;
import org.d11.boot.api.v2.model.PlayerInputDTO;
import org.d11.boot.api.v2.model.PlayerInputRequestBodyDTO;
import org.d11.boot.api.v2.model.PlayerResponseBodyDTO;
import org.d11.boot.api.v2.model.PlayerSearchResultDTO;
import org.d11.boot.api.v2.model.PlayerSearchResultsResponseBodyDTO;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player controller tests.
 */
class PlayerControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Player repository.
     */
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Tests PlayerController::getPlayerById.
     */
    @Test
    void testGetPlayerById() {
        final PlayerApi playerApi = getApi(PlayerApi.class);

        assertThrows(FeignException.NotFound.class, () -> playerApi.getPlayerById(0L));

        final List<Player> players = this.playerRepository.findAll();

        assertFalse(players.isEmpty());

        for (final Player player : players) {
            final PlayerResponseBodyDTO result = playerApi.getPlayerById(player.getId());
            assertNotNull(result);
            assertEquals(getMapper().map(player, PlayerDTO.class), result.getPlayer());
        }
    }

    /**
     * Tests PlayerController::searchPlayersByName.
     */
    @Test
    void testSearchPlayersByName() {
        final PlayerApi playerApi = getApi(PlayerApi.class);

        assertThrows(FeignException.BadRequest.class, () -> playerApi.searchPlayersByName((String) null));

        assertThrows(FeignException.BadRequest.class, () -> playerApi.searchPlayersByName(""));

        // There are players named Bar Foo, Foo and Foo Bar available for this

        final List<PlayerSearchResult> likeFooPlayers = this.playerRepository.findByParameterizedNameLike("foo");
        final PlayerSearchResultsResponseBodyDTO likeFooResponse = playerApi.searchPlayersByName("Foo");

        assertNotNull(likeFooResponse);
        assertEquals(3, likeFooResponse.getPlayers().size());
        assertEquals(getMapper().map(likeFooPlayers, PlayerSearchResultDTO.class), likeFooResponse.getPlayers());

        final List<PlayerSearchResult> exactFooPlayers = this.playerRepository.findByParameterizedNameExact("foo");
        final PlayerSearchResultsResponseBodyDTO exactFooResponse = playerApi.searchPlayersByName("\"Foo\"");

        assertNotNull(exactFooResponse);
        assertEquals(1, exactFooResponse.getPlayers().size());
        assertEquals(getMapper().map(exactFooPlayers, PlayerSearchResultDTO.class), exactFooResponse.getPlayers());

        final List<PlayerSearchResult> likeBarPlayers = this.playerRepository.findByParameterizedNameLike("bar");
        final PlayerSearchResultsResponseBodyDTO likeBarResponse = playerApi.searchPlayersByName("Bar");

        assertNotNull(likeBarResponse);
        assertEquals(2, likeBarResponse.getPlayers().size());
        assertEquals(getMapper().map(likeBarPlayers, PlayerSearchResultDTO.class), likeBarResponse.getPlayers());

        final PlayerSearchResultsResponseBodyDTO exactBarResponse = playerApi.searchPlayersByName("\"Bar\"");

        assertNotNull(exactBarResponse);
        assertTrue(exactBarResponse.getPlayers().isEmpty());
    }

    /**
     * Tests PlayerController::createPlayer.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testCreatePlayer() {
        final Player player = this.playerRepository.findById(1L).orElseThrow(RuntimeException::new);
        final PlayerInputDTO playerInputDTO = map(player, PlayerInputDTO.class)
                .firstName("NEW_FIRST_NAME")
                .lastName("NEW_LAST_NAME");
        final PlayerInputRequestBodyDTO request = new PlayerInputRequestBodyDTO()
                .player(playerInputDTO);

        // 401 Unauthorized --------------------------------------------------------------------------------------------

        assertThrows(FeignException.Unauthorized.class, () -> getApi(PlayerApi.class).createPlayer(request));

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class, () -> getUserApi(PlayerApi.class).createPlayer(request));

        // 400 Bad Request ---------------------------------------------------------------------------------------------

        final PlayerApi playerApi = getAdministratorApi(PlayerApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerApi.createPlayer(new PlayerInputRequestBodyDTO().player(new PlayerInputDTO())));

        // 404 Not Found -----------------------------------------------------------------------------------------------

        request.getPlayer().getCountry().id(123_456L);
        assertThrows(FeignException.NotFound.class, () -> playerApi.createPlayer(request));
        request.getPlayer().getCountry().setId(player.getCountry().getId());

        // 201 CREATED -------------------------------------------------------------------------------------------------

        final PlayerResponseBodyDTO response = playerApi.createPlayer(request);
        final PlayerDTO result = response.getPlayer();

        assertEquals(playerInputDTO.getFirstName(), result.getFirstName());
        assertEquals(playerInputDTO.getLastName(), result.getLastName());
        assertEquals(playerInputDTO.getFullName(), result.getFullName());
        assertEquals(playerInputDTO.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(playerInputDTO.getStatSourceId(), result.getStatSourceId());
        assertEquals(playerInputDTO.getPremierLeagueId(), result.getPremierLeagueId());
        assertEquals(playerInputDTO.getHeight(), result.getHeight());
        assertEquals(playerInputDTO.isVerified(), result.isVerified());
        assertEquals(playerInputDTO.getCountry().getId(), result.getCountry().getId());

        final Player entity = this.playerRepository.findById(result.getId()).orElseThrow(RuntimeException::new);

        assertEquals(playerInputDTO.getFirstName(), entity.getFirstName());
        assertEquals(playerInputDTO.getLastName(), entity.getLastName());
        assertEquals(playerInputDTO.getFullName(), entity.getFullName());
        assertEquals(playerInputDTO.getDateOfBirth(), entity.getDateOfBirth());
        assertEquals(playerInputDTO.getStatSourceId(), entity.getStatSourceId());
        assertEquals(playerInputDTO.getPremierLeagueId(), entity.getPremierLeagueId());
        assertEquals(playerInputDTO.getHeight(), entity.getHeight());
        assertEquals(playerInputDTO.isVerified(), entity.isVerified());
        assertEquals(playerInputDTO.getCountry().getId(), entity.getCountry().getId());
    }

    /**
     * Tests PlayerController::updatePlayer.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testUpdatePlayer() {
        final Player player = this.playerRepository.findById(1L).orElseThrow(RuntimeException::new);
        final PlayerInputDTO playerInputDTO = map(player, PlayerInputDTO.class);
        final PlayerInputRequestBodyDTO request = new PlayerInputRequestBodyDTO()
                .player(playerInputDTO);

        // 401 Unauthorized --------------------------------------------------------------------------------------------

        assertThrows(FeignException.Unauthorized.class,
                     () -> getApi(PlayerApi.class).updatePlayer(player.getId(), request));

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(PlayerApi.class).updatePlayer(player.getId(), request));

        // 400 Bad Request ---------------------------------------------------------------------------------------------

        final PlayerApi playerApi = getAdministratorApi(PlayerApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerApi.updatePlayer(player.getId(), new PlayerInputRequestBodyDTO()));

        // 404 Not Found -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.NotFound.class,
                     () -> playerApi.updatePlayer(123_456L, request));

        request.getPlayer().getCountry().id(123_456L);
        assertThrows(FeignException.NotFound.class, () -> playerApi.updatePlayer(player.getId(), request));
        request.getPlayer().getCountry().setId(player.getCountry().getId());

        // 200 OK ------------------------------------------------------------------------------------------------------

        playerInputDTO
            .firstName("NEW_FIRST_NAME")
            .lastName("NEW_LAST_NAME")
            .fullName("NEW_FULL_NAME")
            .dateOfBirth(LocalDate.now().minusYears(1L))
            .statSourceId(654_321)
            .premierLeagueId(321_654)
            .height(333)
            .verified(false);
        playerInputDTO.getCountry().setId(2L);

        final PlayerResponseBodyDTO response = playerApi.updatePlayer(player.getId(), request);
        final PlayerDTO result = response.getPlayer();

        assertEquals(playerInputDTO.getFirstName(), result.getFirstName());
        assertEquals(playerInputDTO.getLastName(), result.getLastName());
        assertEquals(playerInputDTO.getFullName(), result.getFullName());
        assertEquals(playerInputDTO.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(playerInputDTO.getStatSourceId(), result.getStatSourceId());
        assertEquals(playerInputDTO.getPremierLeagueId(), result.getPremierLeagueId());
        assertEquals(playerInputDTO.getHeight(), result.getHeight());
        assertEquals(playerInputDTO.isVerified(), result.isVerified());
        assertEquals(playerInputDTO.getCountry().getId(), result.getCountry().getId());

        final Player entity = this.playerRepository.findById(player.getId()).orElseThrow(RuntimeException::new);

        assertEquals(playerInputDTO.getFirstName(), entity.getFirstName());
        assertEquals(playerInputDTO.getLastName(), entity.getLastName());
        assertEquals(playerInputDTO.getFullName(), entity.getFullName());
        assertEquals(playerInputDTO.getDateOfBirth(), entity.getDateOfBirth());
        assertEquals(playerInputDTO.getStatSourceId(), entity.getStatSourceId());
        assertEquals(playerInputDTO.getPremierLeagueId(), entity.getPremierLeagueId());
        assertEquals(playerInputDTO.getHeight(), entity.getHeight());
        assertEquals(playerInputDTO.isVerified(), entity.isVerified());
        assertEquals(playerInputDTO.getCountry().getId(), entity.getCountry().getId());
    }

}
