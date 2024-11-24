package org.d11.boot.spring.service;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.model.TransferWindowInput;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ErrorCode;
import org.d11.boot.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * Transfer window service.
 */
@Service
public class TransferWindowService extends RepositoryService<TransferWindow, TransferWindowRepository> {

    /**
     * Repository used to find the correct match week for a new transfer window.
     */
    private final MatchWeekRepository matchWeekRepository;

    /**
     * Creates a new transfer window service.
     *
     * @param transferWindowRepository Transfer window repository.
     * @param matchWeekRepository      Match week repository.
     */
    @Autowired
    public TransferWindowService(final TransferWindowRepository transferWindowRepository,
                                 final MatchWeekRepository matchWeekRepository) {
        super(TransferWindow.class, transferWindowRepository);
        this.matchWeekRepository = matchWeekRepository;
    }

    /**
     * Gets the current transfer window.
     *
     * @return The current transfer window.
     */
    public TransferWindow getCurrentTransferWindow() {
        final Optional<TransferWindow> optional = getJpaRepository().findCurrentTransferWindow();

        return optional.orElseThrow(() -> new ConflictException(ErrorCode.CONFLICT_NO_CURRENT_TRANSFER_WINDOW));
    }

    /**
     * Gets a list of transfer windows by season ordered by date.
     *
     * @param seasonId The season id.
     * @return List of all transfer windows foe a season ordered by descending datetime.
     */
    public List<TransferWindow> getBySeasonId(final Long seasonId) {
        return getJpaRepository().findByMatchWeekSeasonIdOrderByDatetimeDesc(seasonId);
    }

    /**
     * Creates a new transfer window.
     *
     * @param datetime         Transfer window transfer listing deadline.
     * @param transferDayDelay Number of days after transfer listing deadline the first transfer day will take place.
     * @return New transfer window.
     */
    @Transactional
    @SuppressWarnings("PMD.PrematureDeclaration")
    public TransferWindow createTransferWindow(final LocalDateTime datetime, final int transferDayDelay) {
        if (datetime == null || !datetime.isAfter(LocalDateTime.now())) {
            throw new BadRequestException("datetime", "must be after current datetime");
        }
        if (transferDayDelay <= 0) {
            throw new BadRequestException("transferDayDelay", "must be positive");
        }

        // Have to use the no entity graph version of the query here. The match week repository query throws stack
        // overflow exception otherwise for reasons that aren't currently clear
        final TransferWindow currentTransferWindow = getJpaRepository().findFirstByOrderByDatetimeDesc()
                .orElseThrow(() -> new ConflictException(ErrorCode.CONFLICT_NO_CURRENT_TRANSFER_WINDOW));

        final MatchWeek matchWeek =
                this.matchWeekRepository.findFirstByDateGreaterThanOrderByDateAsc(datetime.toLocalDate())
                        .orElseThrow(() -> new ConflictException("No match week found after " +
                                                                 datetime.toLocalDate()));

        if (!Status.FINISHED.equals(currentTransferWindow.getStatus())) {
            throw new ConflictException("Current transfer window status is " + currentTransferWindow.getStatus());
        }

        final TransferWindow transferWindow = new TransferWindow();
        transferWindow.setMatchWeek(matchWeek);
        transferWindow.setTransferWindowNumber(currentTransferWindow.getTransferWindowNumber() + 1);
        transferWindow.setDatetime(datetime);

        final TransferDay transferDay = new TransferDay();
        transferDay.setTransferWindow(transferWindow);
        transferDay.setTransferDayNumber(1);
        transferDay.setDatetime(datetime.plus(transferDayDelay, ChronoUnit.DAYS));
        transferWindow.getTransferDays().add(transferDay);

        return save(transferWindow);
    }

    /**
     * Updates a transfer window.
     *
     * @param transferWindowId Transfer window id.
     * @param transferWindowInput Transfer window properties that will be updated.
     * @return The updated transfer day.
     */
    public TransferWindow updateTransferWindow(final Long transferWindowId,
                                               final TransferWindowInput transferWindowInput) {
        if (Status.FULL_TIME.equals(transferWindowInput.status())) {
            throw new BadRequestException("transferWindow.status", ErrorCode.BAD_REQUEST_INVALID_PARAMETER);
        }

        final TransferWindow transferWindow = getJpaRepository().findById(transferWindowId)
                .orElseThrow(() -> new NotFoundException(transferWindowId, TransferWindow.class));
        final MatchWeek matchWeek = getRepository(MatchWeekRepository.class).findById(transferWindowInput.matchWeekId())
                .orElseThrow(() -> new NotFoundException(transferWindowInput.matchWeekId(), MatchWeek.class));

        getServiceMapper().mapToTransferWindow(transferWindowInput, transferWindow);
        transferWindow.setMatchWeek(matchWeek);

        return getJpaRepository().save(transferWindow);
    }

    /**
     * Deletes a transfer window and it's associated transfer days. The transfer window must have status pending.
     *
     * @param transferWindowId Transfer window id.
     */
    @Transactional
    public void deleteTransferWindow(final Long transferWindowId) {
        final TransferWindow transferWindow = getById(transferWindowId);

        if (!Status.PENDING.equals(transferWindow.getStatus())) {
            throw new ConflictException("Transfer window status is " + transferWindow.getStatus());
        }

        getJpaRepository().delete(transferWindow);
    }

}
