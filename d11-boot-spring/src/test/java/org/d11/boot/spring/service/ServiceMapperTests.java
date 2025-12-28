package org.d11.boot.spring.service;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferDayInput;
import org.d11.boot.spring.model.TransferInput;
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

        assertEquals(source.whoscoredId(), destination.getWhoscoredId());
        assertEquals(source.premierLeagueId(), destination.getPremierLeagueId());
        assertEquals(source.firstName(), destination.getFirstName());
        assertEquals(source.lastName(), destination.getLastName());
        assertEquals(source.fullName(), destination.getFullName());
        assertEquals(source.dateOfBirth(), destination.getDateOfBirth());
        assertEquals(source.height(), destination.getHeight());
        assertEquals(source.verified(), destination.isVerified());
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

        assertEquals(source.whoscoredId(), destination.getWhoscoredId());
        assertEquals(source.premierLeagueId(), destination.getPremierLeagueId());
        assertEquals(source.firstName(), destination.getFirstName());
        assertEquals(source.lastName(), destination.getLastName());
        assertEquals(source.fullName(), destination.getFullName());
        assertEquals(source.dateOfBirth(), destination.getDateOfBirth());
        assertEquals(source.height(), destination.getHeight());
        assertNotEquals(source.countryId(), destination.getCountry().getId());
        assertEquals(source.verified(), destination.isVerified());
    }

    /**
     * Tests ServiceMapper::mapToTransferDay.
     */
    @Test
    void testMapToTransferDayInput() {
        final TransferDayInput source = new TransferDayInput(1, Status.FINISHED, LocalDateTime.now());

        final TransferDay destination = this.mapper.mapToTransferDay(source, new TransferDay());

        assertEquals(source.transferDayNumber(), destination.getTransferDayNumber());
        assertEquals(source.status(), destination.getStatus());
        assertEquals(source.datetime(), destination.getDatetime());
    }

    /**
     * Tests ServiceMapper::mapToTransferWindow.
     */
    @Test
    void testMapToTransferWindowInput() {
        final TransferWindowInput source = new TransferWindowInput(1, true, Status.FINISHED, LocalDateTime.now(), 1);

        final TransferWindow destination = this.mapper.mapToTransferWindow(source, new TransferWindow());

        assertEquals(source.transferWindowNumber(), destination.getTransferWindowNumber());
        assertEquals(source.draft(), destination.isDraft());
        assertEquals(source.status(), destination.getStatus());
        assertEquals(source.datetime(), destination.getDatetime());
    }

    /**
     * Tests ServiceMapper::mapToTransfer.
     */
    @Test
    void testMapToTransfer() {
        final TransferInput source = new TransferInput(5, 1, 2, 3);

        final Transfer destination = this.mapper.mapToTransfer(source);

        assertEquals(source.fee(), destination.getFee());
    }

}
