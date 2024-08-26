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
import java.time.temporal.ChronoUnit;
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

        assertThrows(FeignException.NotFound.class, () -> playerApi.getPlayerById(0L),
                     "PlayerController::getPlayerById not found");

        final List<Player> players = this.playerRepository.findAll();

        assertFalse(players.isEmpty(), "PlayerController::getPlayerById players not empty");

        for (final Player player : players) {
            final PlayerResponseBodyDTO result = playerApi.getPlayerById(player.getId());
            assertNotNull(result, "PlayerController::getPlayerById not null");
            assertEquals(getMapper().map(player, PlayerDTO.class), result.getPlayer(),
                         "PlayerController::getPlayerById equals");
        }
    }

    /**
     * Tests PlayerController::searchPlayersByName.
     */
    @Test
    void testSearchPlayersByName() {
        final PlayerApi playerApi = getApi(PlayerApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerApi.searchPlayersByName((String) null),
                     "PlayerController::searchPlayersByName name null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerApi.searchPlayersByName(""),
                     "PlayerController::searchPlayersByName name empty throws");

        // There are players named Bar Foo, Foo and Foo Bar available for this

        final List<PlayerSearchResult> likeFooPlayers = this.playerRepository.findByParameterizedNameLike("foo");
        final PlayerSearchResultsResponseBodyDTO likeFooResponse = playerApi.searchPlayersByName("Foo");

        assertNotNull(likeFooResponse, "PlayerController::searchPlayersByName like foo response not null");
        assertEquals(3, likeFooResponse.getPlayers().size(),
                    "PlayerController::searchPlayersByName like foo response size");
        assertEquals(getMapper().map(likeFooPlayers, PlayerSearchResultDTO.class), likeFooResponse.getPlayers(),
                     "PlayerController::searchPlayersByName like foo response equals");

        final List<PlayerSearchResult> exactFooPlayers = this.playerRepository.findByParameterizedNameExact("foo");
        final PlayerSearchResultsResponseBodyDTO exactFooResponse = playerApi.searchPlayersByName("\"Foo\"");

        assertNotNull(exactFooResponse, "PlayerController::searchPlayersByName exact foo response not null");
        assertEquals(1, exactFooResponse.getPlayers().size(),
                     "PlayerController::searchPlayersByName exact foo response size");
        assertEquals(getMapper().map(exactFooPlayers, PlayerSearchResultDTO.class), exactFooResponse.getPlayers(),
                     "PlayerController::searchPlayersByName exact foo response equals");

        final List<PlayerSearchResult> likeBarPlayers = this.playerRepository.findByParameterizedNameLike("bar");
        final PlayerSearchResultsResponseBodyDTO likeBarResponse = playerApi.searchPlayersByName("Bar");

        assertNotNull(likeBarResponse, "PlayerController::searchPlayersByName like bar response not null");
        assertEquals(2, likeBarResponse.getPlayers().size(),
                     "PlayerController::searchPlayersByName like bar response size");
        assertEquals(getMapper().map(likeBarPlayers, PlayerSearchResultDTO.class), likeBarResponse.getPlayers(),
                     "PlayerController::searchPlayersByName like bar response equals");

        final PlayerSearchResultsResponseBodyDTO exactBarResponse = playerApi.searchPlayersByName("\"Bar\"");

        assertNotNull(exactBarResponse, "PlayerController::searchPlayersByName exact bar response not null");
        assertTrue(exactBarResponse.getPlayers().isEmpty(),
                   "PlayerController::searchPlayersByName exact bar response empty");
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
                     () -> getApi(PlayerApi.class).updatePlayer(player.getId(), request),
                     "PlayerController::updatePlayer unauthorized throws");

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(PlayerApi.class).updatePlayer(player.getId(), request),
                     "PlayerController::updatePlayer user throws");

        // 400 Bad Request ---------------------------------------------------------------------------------------------

        final PlayerApi playerApi = getAdministratorApi(PlayerApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerApi.updatePlayer(player.getId(), new PlayerInputRequestBodyDTO()),
                     "PlayerController::updatePlayer request body invalid throws");

        // 404 Not Found -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.NotFound.class,
                     () -> playerApi.updatePlayer(123_456L, request),
                     "PlayerController::updatePlayer request body player not found throws");

        request.getPlayer().getCountry().id(123_456L);
        assertThrows(FeignException.NotFound.class,
                     () -> playerApi.updatePlayer(player.getId(), request),
                     "PlayerController::updatePlayer request body country not found throws");
        request.getPlayer().getCountry().setId(player.getCountry().getId());

        // 200 OK ------------------------------------------------------------------------------------------------------

        playerInputDTO
            .firstName("NEW_FIRST_NAME")
            .lastName("NEW_LAST_NAME")
            .fullName("NEW_FULL_NAME")
            .dateOfBirth(LocalDate.now().minus(1L, ChronoUnit.YEARS))
            .whoscoredId(654_321)
            .premierLeagueId(321_654)
            .height(333)
            .verified(false);
        playerInputDTO.getCountry().setId(2L);

        final PlayerResponseBodyDTO response = playerApi.updatePlayer(player.getId(), request);
        final PlayerDTO result = response.getPlayer();

        assertEquals(playerInputDTO.getFirstName(), result.getFirstName(),
                     "PlayerController::updatePlayer result firstName equals");
        assertEquals(playerInputDTO.getLastName(), result.getLastName(),
                     "PlayerController::updatePlayer result lastName equals");
        assertEquals(playerInputDTO.getFullName(), result.getFullName(),
                     "PlayerController::updatePlayer result fullName equals");
        assertEquals(playerInputDTO.getDateOfBirth(), result.getDateOfBirth(),
                     "PlayerController::updatePlayer result dateOfBirth equals");
        assertEquals(playerInputDTO.getWhoscoredId(), result.getWhoscoredId(),
                     "PlayerController::updatePlayer result whoscoredId equals");
        assertEquals(playerInputDTO.getPremierLeagueId(), result.getPremierLeagueId(),
                     "PlayerController::updatePlayer result premierLeagueId equals");
        assertEquals(playerInputDTO.getHeight(), result.getHeight(),
                     "PlayerController::updatePlayer result height equals");
        assertEquals(playerInputDTO.isVerified(), result.isVerified(),
                     "PlayerController::updatePlayer result verified equals");
        assertEquals(playerInputDTO.getCountry().getId(), result.getCountry().getId(),
                     "PlayerController::updatePlayer result country id equals");

        final Player entity = this.playerRepository.findById(player.getId()).orElseThrow(RuntimeException::new);

        assertEquals(playerInputDTO.getFirstName(), entity.getFirstName(),
                     "PlayerController::updatePlayer entity firstName equals");
        assertEquals(playerInputDTO.getLastName(), entity.getLastName(),
                     "PlayerController::updatePlayer entity lastName equals");
        assertEquals(playerInputDTO.getFullName(), entity.getFullName(),
                     "PlayerController::updatePlayer entity fullName equals");
        assertEquals(playerInputDTO.getDateOfBirth(), entity.getDateOfBirth(),
                     "PlayerController::updatePlayer entity dateOfBirth equals");
        assertEquals(playerInputDTO.getWhoscoredId(), entity.getWhoscoredId(),
                     "PlayerController::updatePlayer entity whoscoredId equals");
        assertEquals(playerInputDTO.getPremierLeagueId(), entity.getPremierLeagueId(),
                     "PlayerController::updatePlayer entity premierLeagueId equals");
        assertEquals(playerInputDTO.getHeight(), entity.getHeight(),
                     "PlayerController::updatePlayer entity height equals");
        assertEquals(playerInputDTO.isVerified(), entity.isVerified(),
                     "PlayerController::updatePlayer entity verified equals");
        assertEquals(playerInputDTO.getCountry().getId(), entity.getCountry().getId(),
                     "PlayerController::updatePlayer entity country id equals");
    }

}
