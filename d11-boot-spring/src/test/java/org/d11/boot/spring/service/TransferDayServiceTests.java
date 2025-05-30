package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferDayInput;
import org.d11.boot.spring.model.TransferDayStatusInput;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ErrorCode;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    // updateTransferDay -----------------------------------------------------------------------------------------------

    /**
     * Tests TransferDayService::updateTransferDay for bad request.
     */
    @Test
    void testUpdateTransferDayBadRequest() {
        final TransferDayInput transferDayInput = new TransferDayInput(1, Status.FULL_TIME, LocalDateTime.now());

        assertThrows(BadRequestException.class,
                     () -> this.transferDayService.updateTransferDay(1L, transferDayInput),
                     "TransferDayService::updateTransferDay status FULL_TIME throws");
    }

    /**
     * Tests TransferDayService::updateTransferDay for not found.
     */
    @Test
    void testUpdateTransferDayNotFound() {
        final TransferDayInput transferDayInput = new TransferDayInput(1, Status.FINISHED, LocalDateTime.now());

        when(this.transferDayRepository.findById(eq(1L))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                     () -> this.transferDayService.updateTransferDay(1L, transferDayInput),
                     "TransferDayService::updateTransferDay not found throws");
    }

    /**
     * Tests TransferDayService::updateTransferDay.
     */
    @Test
    void testUpdateTransferDay() {
        final TransferDay transferDay = generate(TransferDay.class)
                .setTransferDayNumber(2)
                .setStatus(Status.PENDING)
                .setDatetime(LocalDateTime.now().minusDays(1L));

        when(this.transferDayRepository.findById(eq(transferDay.getId()))).thenReturn(Optional.of(transferDay));
        when(this.transferDayRepository.save(any(TransferDay.class))).then(AdditionalAnswers.returnsFirstArg());

        final TransferDayInput transferDayInput = new TransferDayInput(1, Status.FINISHED, LocalDateTime.now());

        final TransferDay result = this.transferDayService.updateTransferDay(transferDay.getId(), transferDayInput);

        assertEquals(transferDayInput.transferDayNumber(), result.getTransferDayNumber(),
                     "TransferDayService::updateTransferDay result transferDayNumber equals");
        assertEquals(transferDayInput.status(), result.getStatus(),
                     "TransferDayService::updateTransferDay result status equals");
        assertEquals(transferDayInput.datetime(), result.getDatetime(),
                     "TransferDayService::updateTransferDay result datetime equals");

        verify(this.transferDayRepository, times(1)).save(eq(transferDay));
    }

    // updateTransferDayStatus -----------------------------------------------------------------------------------------

    /**
     * Tests TransferDayService::updateTransferDayStatus for invalid status.
     */
    @Test
    void testUpdateTransferDayStatusStatusInvalid() {
        final TransferDayStatusInput transferDayStatusInput = new TransferDayStatusInput(Status.FULL_TIME, false);

        final BadRequestException e =
                assertThrows(BadRequestException.class,
                             () -> this.transferDayService.updateTransferDayStatus(1L, transferDayStatusInput),
                             "TransferDayService::updateTransferDayStatus invalid status throws");
        assertEquals("transferDay.status", e.getParameter(),
                     "TransferListingService::createTransferListing invalid status property equals");
    }

    /**
     * Tests TransferDayService::updateTransferDayStatus for not found.
     */
    @Test
    void testUpdateTransferDayStatusNotFound() {
        final TransferDayStatusInput transferDayStatusInput = new TransferDayStatusInput(Status.FINISHED, false);

        when(this.transferDayRepository.findById(eq(1L))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                     () -> this.transferDayService.updateTransferDayStatus(1L, transferDayStatusInput),
                     "TransferDayService::updateTransferDayStatus not found throws");
    }

    /**
     * Tests TransferDayService::updateTransferDayStatus for invalid status transition.
     */
    @Test
    void testUpdateTransferDayStatusInvalidStatusTransition() {
        final TransferDayStatusInput transferDayStatusInput = new TransferDayStatusInput(Status.PENDING, true);

        final TransferDay transferDay = generate(TransferDay.class);
        transferDay.setStatus(Status.PENDING);

        when(this.transferDayRepository.findById(eq(1L))).thenReturn(Optional.of(transferDay));

        final ConflictException e =
                assertThrows(ConflictException.class,
                             () -> this.transferDayService.updateTransferDayStatus(1L, transferDayStatusInput),
                             "TransferDayService::updateTransferDayStatus invalid status transition throws");
        assertEquals(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS, e.getErrorCode(),
                 "TransferDayService::updateTransferDayStatus transfer day invalid status transition message equals");
    }

    /**
     * Tests TransferDayService::updateTransferDayStatus.
     */
    @Test
    void testUpdateTransferDayStatus() {
        final TransferDay transferDay = generate(TransferDay.class)
                .setStatus(Status.PENDING);

        when(this.transferDayRepository.findById(eq(transferDay.getId()))).thenReturn(Optional.of(transferDay));
        when(this.transferDayRepository.save(any(TransferDay.class))).then(AdditionalAnswers.returnsFirstArg());

        final TransferDayStatusInput transferDayStatusInput = new TransferDayStatusInput(Status.ACTIVE, false);

        final TransferDay result = this.transferDayService.updateTransferDayStatus(transferDay.getId(),
                                                                                   transferDayStatusInput);

        assertEquals(transferDayStatusInput.status(), result.getStatus(),
                     "TransferDayService::updateTransferDayStatus result status equals");

        verify(this.transferDayRepository, times(1)).save(eq(transferDay));
    }

}
