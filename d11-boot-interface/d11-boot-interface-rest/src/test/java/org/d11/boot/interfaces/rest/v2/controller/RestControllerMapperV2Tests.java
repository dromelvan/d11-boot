package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.model.PlayerDTO;
import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.spring.model.PlayerInput;
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
    void testMapToPlayerUpdate() {
        final PlayerDTO source = generate(PlayerDTO.class);

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

}
