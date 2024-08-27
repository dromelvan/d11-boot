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
     * Tests ServiceMapper::mapToPlayer(PlayerInput).
     */
    @Test
    void testMapToPlayerInsert() {
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

        final Player destination = this.mapper.mapToPlayer(source);

        assertEquals(source.whoscoredId(), destination.getWhoscoredId(),
                     "ServiceMapper::mapToPlayer(PlayerInput) destination whoscoredId equals");
        assertEquals(source.premierLeagueId(), destination.getPremierLeagueId(),
                     "ServiceMapper::mapToPlayer(PlayerInput) destination premierLeagueId equals");
        assertEquals(source.firstName(), destination.getFirstName(),
                     "ServiceMapper::mapToPlayer(PlayerInput) destination firstName equals");
        assertEquals(source.lastName(), destination.getLastName(),
                     "ServiceMapper::mapToPlayer(PlayerInput) destination lastName equals");
        assertEquals(source.fullName(), destination.getFullName(),
                     "ServiceMapper::mapToPlayer(PlayerInput) destination fullName equals");
        assertEquals(source.dateOfBirth(), destination.getDateOfBirth(),
                     "ServiceMapper::mapToPlayer(PlayerInput) destination dateOfBirth equals");
        assertEquals(source.height(), destination.getHeight(),
                     "ServiceMapper::mapToPlayer(PlayerInput) destination height equals");
        assertEquals(source.verified(), destination.isVerified(),
                     "ServiceMapper::mapToPlayer(PlayerInput) destination verified equals");
    }

    /**
     * Tests ServiceMapper::mapToPlayer(PlayerInput,Player).
     */
    @Test
    void testMapToPlayerUpdate() {
        final PlayerInput source = new PlayerInput(
                123,
                321,
                "UPDATED_FIRST_NAME",
                "UPDATED_LAST_NAME",
                "UPDATED_FULL_NAME",
                LocalDate.now(),
                456,
                654,
                true
        );

        final Player destination = this.mapper.mapToPlayer(source, generate(Player.class));

        assertEquals(source.whoscoredId(), destination.getWhoscoredId(),
                     "ServiceMapper::mapToPlayer(PlayerInput,Player) destination whoscoredId equals");
        assertEquals(source.premierLeagueId(), destination.getPremierLeagueId(),
                     "ServiceMapper::mapToPlayer(PlayerInput,Player) destination premierLeagueId equals");
        assertEquals(source.firstName(), destination.getFirstName(),
                     "ServiceMapper::mapToPlayer(PlayerInput,Player) destination firstName equals");
        assertEquals(source.lastName(), destination.getLastName(),
                     "ServiceMapper::mapToPlayer(PlayerInput,Player) destination lastName equals");
        assertEquals(source.fullName(), destination.getFullName(),
                     "ServiceMapper::mapToPlayer(PlayerInput,Player) destination fullName equals");
        assertEquals(source.dateOfBirth(), destination.getDateOfBirth(),
                     "ServiceMapper::mapToPlayer(PlayerInput,Player) destination dateOfBirth equals");
        assertEquals(source.height(), destination.getHeight(),
                     "ServiceMapper::mapToPlayer(PlayerInput,Player) destination height equals");
        assertNotEquals(source.countryId(), destination.getCountry().getId(),
                        "ServiceMapper::mapToPlayerInput(PlayerInput,Player) destination countryId not equals");
        assertEquals(source.verified(), destination.isVerified(),
                     "ServiceMapper::mapToPlayer(PlayerInput,Player) destination verified equals");
    }

}
