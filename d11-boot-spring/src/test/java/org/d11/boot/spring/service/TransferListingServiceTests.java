package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferListingRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Transfer listing service tests.
 */
class TransferListingServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked transfer listing repository.
     */
    @Mock
    private TransferListingRepository transferListingRepository;

    /**
     * Mocked transfer day repository.
     */
    @Mock
    private TransferDayRepository transferDayRepository;

    /**
     * Transfer listing service.
     */
    @InjectMocks
    private TransferListingService transferListingService;

    /**
     * Tests TransferListingService::getByTransferDayId.
     */
    @Test
    void testGetByTransferDayId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String transferDayIdProperty = "transferDayId";

        final BadRequestException nullTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.getByTransferDayId(null, 0),
                             "TransferListingService::getByTransferDayId null transferDayId throws");
        assertEquals(transferDayIdProperty, nullTransferDayIdException.getParameter(),
                     "TransferListingService::getByTransferDayId property equals null transferDayId");

        final BadRequestException invalidTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.getByTransferDayId(-1L, 0),
                             "TransferListingService::getByTransferDayId invalid transferDayId throws");
        assertEquals(transferDayIdProperty, invalidTransferDayIdException.getParameter(),
                     "TransferListingService::getByTransferDayId property equals invalid transferDayId");

        final BadRequestException invalidPageException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.getByTransferDayId(1L, -1),
                             "TransferListingService::getByTransferDayId invalid page throws");
        assertEquals("page", invalidPageException.getParameter(),
                     "TransferListingService::getByTransferDayId property equals invalid page");

        // Success -----------------------------------------------------------------------------------------------------

        final TransferDay transferDay = generate(TransferDay.class);

        final List<TransferListing> transferListings = generateList(TransferListing.class);

        when(this.transferDayRepository.findById(eq(transferDay.getId() + 1))).thenReturn(Optional.empty());

        assertTrue(this.transferListingService.getByTransferDayId(transferDay.getId() + 1, 0).isEmpty(),
                   "TransferListingService::getByTransferDayId invalid transferDayId isEmpty");

        transferDay.setStatus(Status.PENDING);
        when(this.transferDayRepository.findById(eq(transferDay.getId()))).thenReturn(Optional.of(transferDay));

        assertTrue(this.transferListingService.getByTransferDayId(transferDay.getId(), 0).isEmpty(),
                   "TransferListingService::getByTransferDayId transfer day pending isEmpty");

        transferDay.setStatus(Status.ACTIVE);
        when(this.transferListingRepository.findByTransferDayIdOrderByRanking(eq(transferDay.getId()),
                                                                              any(Pageable.class)))
                .thenReturn(transferListings);

        final List<TransferListing> result = this.transferListingService.getByTransferDayId(transferDay.getId(), 0);

        assertEquals(transferListings, result, "TransferListingService::getByTransferDayId result equals");

        verify(this.transferListingRepository, times(1)).findByTransferDayIdOrderByRanking(eq(transferDay.getId()),
                                                                                           any(Pageable.class));
    }

}
