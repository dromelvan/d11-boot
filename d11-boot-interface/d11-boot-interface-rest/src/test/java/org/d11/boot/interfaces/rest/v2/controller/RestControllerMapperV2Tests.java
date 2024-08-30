package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.model.PlayerDTO;
import org.d11.boot.api.v2.model.PlayerInputDTO;
import org.d11.boot.api.v2.model.PlayerSearchResultDTO;
import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.model.Team;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Rest controller mapper tests.
 */
class RestControllerMapperV2Tests extends EasyRandomTests {

    /**
     * Rest controller mapper.
     */
    private final RestControllerMapperV2 mapper = Mappers.getMapper(RestControllerMapperV2.class);

    /**
     * Tests RestControllerMapper::mapToPlayerInput.
     */
    @Test
    void testMapToPlayerInput() {
        final PlayerInputDTO source = generate(PlayerInputDTO.class);

        final PlayerInput destination = this.mapper.mapToPlayerInput(source);

        assertEquals(source.getWhoscoredId(), destination.whoscoredId(),
                     "RestControllerMapper::mapToPlayerInput destination whoscoredId equals");
        assertEquals(source.getPremierLeagueId(), destination.premierLeagueId(),
                     "RestControllerMapper::mapToPlayerInput destination premierLeagueId equals");
        assertEquals(source.getFirstName(), destination.firstName(),
                     "RestControllerMapper::mapToPlayerInput destination firstName equals");
        assertEquals(source.getLastName(), destination.lastName(),
                     "RestControllerMapper::mapToPlayerInput destination lastName equals");
        assertEquals(source.getFullName(), destination.fullName(),
                     "RestControllerMapper::mapToPlayerInput destination fullName equals");
        assertEquals(source.getDateOfBirth(), destination.dateOfBirth(),
                     "RestControllerMapper::mapToPlayerInput destination dateOfBirth equals");
        assertEquals(source.getHeight(), destination.height(),
                     "RestControllerMapper::mapToPlayerInput destination height equals");
        assertEquals(source.getCountry().getId(), destination.countryId(),
                     "RestControllerMapper::mapToPlayerInput destination countryId equals");
        assertEquals(source.isVerified(), destination.verified(),
                     "RestControllerMapper::mapToPlayerInput destination verified equals");
    }

    /**
     * Tests RestControllerMapper::mapToPlayerDTO.
     */
    @Test
    void testMapToPlayerDTO() {
        final Player source = generate(Player.class);

        final PlayerDTO destination = this.mapper.mapToPlayerDTO(source);

        assertEquals(source.getWhoscoredId(), destination.getWhoscoredId(),
                     "RestControllerMapper::mapToPlayerDTO destination whoscoredId equals");
        assertEquals(source.getPremierLeagueId(), destination.getPremierLeagueId(),
                     "RestControllerMapper::mapToPlayerDTO destination premierLeagueId equals");
        assertEquals(source.getFirstName(), destination.getFirstName(),
                     "RestControllerMapper::mapToPlayerDTO destination firstName equals");
        assertEquals(source.getLastName(), destination.getLastName(),
                     "RestControllerMapper::mapToPlayerDTO destination lastName equals");
        assertEquals(source.getFullName(), destination.getFullName(),
                     "RestControllerMapper::mapToPlayerDTO destination fullName equals");
        assertEquals(source.getDateOfBirth(), destination.getDateOfBirth(),
                     "RestControllerMapper::mapToPlayerDTO destination dateOfBirth equals");
        assertEquals(source.getHeight(), destination.getHeight(),
                     "RestControllerMapper::mapToPlayerDTO destination height equals");
        assertEquals(source.getCountry().getId(), destination.getCountry().getId(),
                     "RestControllerMapper::mapToPlayerDTO destination country id equals");
        assertEquals(source.isVerified(), destination.isVerified(),
                     "RestControllerMapper::mapToPlayerDTO destination verified equals");
    }

    /**
     * Tests RestControllerMapper::mapToPlayerSearchResultDTO.
     */
    @Test
    void testMapToPlayerSearchResultDTO() {
        final Player player = generate(Player.class);
        final Team team = generate(Team.class);

        @SuppressWarnings({ "PMD.CommentRequired", "checkstyle:AnonInnerLength" })
        final PlayerSearchResult source = new PlayerSearchResult() {
            @Override
            public Long getId() {
                return player.getId();
            }

            @Override
            public String getName() {
                return player.getName();
            }

            @Override
            public Long getTeamId() {
                return team.getId();
            }

            @Override
            public String getTeamName() {
                return team.getName();
            }
        };

        final PlayerSearchResultDTO destination = this.mapper.mapToPlayerSearchResultDTO(source);

        assertEquals(source.getId(), destination.getId(),
                "RestControllerMapper::mapToPlayerSearchResultDTO destination id equals");
        assertEquals(source.getName(), destination.getName(),
                "RestControllerMapper::mapToPlayerSearchResultDTO destination name equals");
        assertEquals(source.getTeamId(), destination.getTeamId(),
                "RestControllerMapper::mapToPlayerSearchResultDTO destination team id equals");
        assertEquals(source.getTeamName(), destination.getTeamName(),
                "RestControllerMapper::mapToPlayerSearchResultDTO destination team name equals");
    }

}
