package org.d11.boot.spring.service;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferDayInput;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.model.TransferWindowInput;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    /**
     * Tests ServiceMapper::mapToTransferDay.
     */
    @Test
    void testMapToTransferDayInput() {
        final TransferDayInput source = new TransferDayInput(1, Status.FINISHED, LocalDateTime.now());

        final TransferDay destination = this.mapper.mapToTransferDay(source, new TransferDay());

        assertEquals(source.transferDayNumber(), destination.getTransferDayNumber(),
                "ServiceMapper::mapToTransferDay destination transfer day number equals");
        assertEquals(source.status(), destination.getStatus(),
                     "ServiceMapper::mapToTransferDay destination status equals");
        assertEquals(source.datetime(), destination.getDatetime(),
                     "ServiceMapper::mapToTransferDay destination datetime equals");
    }

    /**
     * Tests ServiceMapper::mapToTransferWindow.
     */
    @Test
    void testMapToTransferWindowInput() {
        final TransferWindowInput source = new TransferWindowInput(1, true, Status.FINISHED, LocalDateTime.now(), 1);

        final TransferWindow destination = this.mapper.mapToTransferWindow(source, new TransferWindow());

        assertEquals(source.transferWindowNumber(), destination.getTransferWindowNumber(),
                     "ServiceMapper::mapToTransferWindow destination transfer window number equals");
        assertEquals(source.draft(), destination.isDraft(),
                     "ServiceMapper::mapToTransferWindow destination draft equals");
        assertEquals(source.status(), destination.getStatus(),
                     "ServiceMapper::mapToTransferWindow destination status equals");
        assertEquals(source.datetime(), destination.getDatetime(),
                     "ServiceMapper::mapToTransferWindow destination datetime equals");
    }

}
