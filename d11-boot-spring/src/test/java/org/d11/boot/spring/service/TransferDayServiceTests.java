package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Transfer day service tests.
 */
class TransferDayServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked transfer day repository.
     */
    @Mock
    private TransferDayRepository transferDayRepository;

    /**
     * Transfer day service.
     */
    @InjectMocks
    private TransferDayService transferDayService;

    /**
     * Tests TransferDayService::getById.
     */
    @Test
    void testGetById() {
        final List<TransferDay> transferDays = generateList(TransferDay.class);
        when(this.transferDayRepository.findById(anyLong())).thenReturn(Optional.empty());

        for (final TransferDay transferDay : transferDays) {
            when(this.transferDayRepository.findById(transferDay.getId())).thenReturn(Optional.of(transferDay));

            final TransferDay result = this.transferDayService.getById(transferDay.getId());
            assertNotNull(result, "TransferDayService::getById not null");
            assertEquals(transferDay, result, "TransferDayService::getById equals");
        }

        assertThrows(NotFoundException.class, () -> this.transferDayService.getById(-1L),
                     "TransferDayService::getById not found");
    }

    /**
     * Tests TransferDayService::getCurrentTransferDay.
     */
    @Test
    void testGetCurrentTransferDay() {
        final TransferDay current = generate(TransferDay.class);

        when(this.transferDayRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.empty());
        assertThrows(ConflictException.class, () -> this.transferDayService.getCurrentTransferDay(),
                     "TransferDayService::getCurrentTransferDay conflict");

        when(this.transferDayRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.of(current));

        assertEquals(current, this.transferDayService.getCurrentTransferDay(),
                     "TransferDayService::getCurrentTransferDay current");

        verify(this.transferDayRepository, times(2)).findFirstByOrderByDatetimeDesc();
    }

    /**
     * Tests TransferDayService::getByTransferWindowId.
     */
    @Test
    void testGetByTransferWindowId() {
        final List<TransferDay> transferDays = generateList(TransferDay.class);

        assertThrows(BadRequestException.class, () -> this.transferDayService.getByTransferWindowId(-1L),
                     "TransferDayService::getByTransferWindowId transferWindowId negative throws");
        assertThrows(BadRequestException.class, () -> this.transferDayService.getByTransferWindowId(null),
                     "TransferDayService::getByTransferWindowId transferWindowId missing throws");

        final TransferWindow transferWindow = generate(TransferWindow.class);
        when(this.transferDayRepository.findByTransferWindowIdOrderByDatetimeDesc(eq(transferWindow.getId())))
                .thenReturn(transferDays);

        final List<TransferDay> result = this.transferDayService.getByTransferWindowId(transferWindow.getId());

        assertNotNull(result, "TransferDayService::getByTransferWindowId not null");
        assertFalse(result.isEmpty(), "TransferDayService::getByTransferWindowId isEmpty");
        assertEquals(transferDays, result, "TransferDayService::getByTransferWindowId equals");

        verify(this.transferDayRepository, times(1))
                .findByTransferWindowIdOrderByDatetimeDesc(eq(transferWindow.getId()));
    }

}