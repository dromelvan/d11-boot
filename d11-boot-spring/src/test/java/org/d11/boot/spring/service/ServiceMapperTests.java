package org.d11.boot.spring.service;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Service mapper tests.
 */
class ServiceMapperTests extends EasyRandomTests {

    /**
     * Service mapper.
     */
    private final ServiceMapper mapper = Mappers.getMapper(ServiceMapper.class);

    /**
     * Tests ServiceMapper::mapToPlayer.
     */
    @Test
    void testMapToPlayer() {
        final PlayerInput source = new PlayerInput(
                123,
                321,
                "NEW_FIRST_NAME",
                "NEW_LAST_NAME",
                "NEW_FULL_NAME",
                LocalDate.now(),
                456,
                654,
                true
        );

        final Player destination = this.mapper.mapToPlayer(source, generate(Player.class));

        assertEquals(source.whoscoredId(), destination.getWhoscoredId(),
                     "ServiceMapper::mapToPlayer destination whoscoredId equals");
        assertEquals(source.premierLeagueId(), destination.getPremierLeagueId(),
                     "ServiceMapper::mapToPlayer destination premierLeagueId equals");
        assertEquals(source.firstName(), destination.getFirstName(),
                     "ServiceMapper::mapToPlayer destination firstName equals");
        assertEquals(source.lastName(), destination.getLastName(),
                     "ServiceMapper::mapToPlayer destination lastName equals");
        assertEquals(source.fullName(), destination.getFullName(),
                     "ServiceMapper::mapToPlayer destination fullName equals");
        assertEquals(source.dateOfBirth(), destination.getDateOfBirth(),
                     "ServiceMapper::mapToPlayer destination dateOfBirth equals");
        assertEquals(source.height(), destination.getHeight(),
                     "ServiceMapper::mapToPlayer destination height equals");
        assertNotEquals(source.countryId(), destination.getCountry().getId(),
                        "ServiceMapper::mapToPlayerInput destination countryId not equals");
        assertEquals(source.verified(), destination.isVerified(),
                     "ServiceMapper::mapToPlayer destination verified equals");
    }

}
