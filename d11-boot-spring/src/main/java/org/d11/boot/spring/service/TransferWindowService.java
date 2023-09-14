package org.d11.boot.spring.service;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
     * Inserts a new transfer window.
     *
     * @param datetime         Transfer window transfer listing deadline.
     * @param transferDayDelay Number of days after transfer listing deadline the first transfer day will take place.
     * @return New transfer window.
     */
    @Transactional
    public TransferWindow insertTransferWindow(final LocalDateTime datetime, final int transferDayDelay) {
        if (datetime == null || !datetime.isAfter(LocalDateTime.now())) {
            throw new BadRequestException("datetime", "Datetime is missing or invalid");
        }
        if (transferDayDelay <= 0) {
            throw new BadRequestException("transferDayDelay", "Transfer day delay is not positive");
        }

        final TransferWindow currentTransferWindow = getJpaRepository().findFirstByOrderByDatetimeDesc()
                .orElseThrow(NotFoundException::new);

        if (!Status.FINISHED.equals(currentTransferWindow.getStatus())) {
            throw new ConflictException("A new transfer window cannot be inserted when the status of the current " +
                                                "transfer window is " + currentTransferWindow.getStatus());
        }

        final MatchWeek matchWeek =
                this.matchWeekRepository.findFirstByDateGreaterThanOrderByDateAsc(datetime.toLocalDate())
                        .orElseThrow(NotFoundException::new);

        final TransferWindow transferWindow = new TransferWindow();
        transferWindow.setMatchWeek(matchWeek);
        transferWindow.setTransferWindowNumber(currentTransferWindow.getTransferWindowNumber() + 1);
        transferWindow.setDatetime(datetime);

        final TransferDay transferDay = new TransferDay();
        transferDay.setTransferWindow(transferWindow);
        transferDay.setTransferDayNumber(1);
        transferDay.setDatetime(datetime.plus(transferDayDelay, ChronoUnit.DAYS));
        transferWindow.getTransferDays().add(transferDay);

        return getJpaRepository().save(transferWindow);
    }

}
