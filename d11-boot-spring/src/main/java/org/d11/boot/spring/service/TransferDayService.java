package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferDayInput;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ErrorCode;
import org.d11.boot.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Transfer day service.
 */
@Service
public class TransferDayService extends RepositoryService<TransferDay, TransferDayRepository> {

    /**
     * Creates a new transfer day service.
     *
     * @param transferDayRepository The repository the service will use.
     */
    @Autowired
    public TransferDayService(final TransferDayRepository transferDayRepository) {
        super(TransferDay.class, transferDayRepository);
    }

    /**
     * Gets the current transfer day.
     *
     * @return The current transfer day DTO.
     */
    public TransferDay getCurrentTransferDay() {
        final Optional<TransferDay> optional = getJpaRepository().findFirstByOrderByDatetimeDesc();

        return optional.orElseThrow(() -> new ConflictException("Current transfer day does not exist"));
    }

    /**
     * Get transfer days by transfer window id ordered by datetime, descending.
     *
     * @param transferWindowId The transfer window id.
     * @return Transfer days by transfer window id ordered by datetime, descending.
     */
    public List<TransferDay> getByTransferWindowId(final Long transferWindowId) {
        if (transferWindowId == null || transferWindowId <= 0) {
            throw new BadRequestException("transferWindowId", "must be positive");
        }

        return getJpaRepository().findByTransferWindowIdOrderByDatetimeDesc(transferWindowId);
    }

    /**
     * Updates a transfer day.
     *
     * @param transferDayId Transfer day id.
     * @param transferDayInput Transfer day input properties that will be updated..
     * @return The updated transfer day.
     */
    public TransferDay updateTransferDay(final Long transferDayId, final TransferDayInput transferDayInput) {
        if (Status.FULL_TIME.equals(transferDayInput.status())) {
            throw new BadRequestException("transferDay.status", ErrorCode.BAD_REQUEST_INVALID_PARAMETER);
        }

        final TransferDay transferDay = getJpaRepository().findById(transferDayId)
                .orElseThrow(() -> new NotFoundException(transferDayId, TransferDay.class));

        getServiceMapper().mapToTransferDay(transferDayInput, transferDay);

        return getJpaRepository().save(transferDay);
    }

}
