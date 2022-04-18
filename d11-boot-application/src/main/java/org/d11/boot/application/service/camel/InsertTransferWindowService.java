package org.d11.boot.application.service.camel;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferWindow;
import org.d11.boot.application.repository.MatchWeekRepository;
import org.d11.boot.application.repository.TransferWindowRepository;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.jms.message.InsertTransferWindowMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;

/**
 * Service for inserting transfer windows.
 */
@Slf4j
@Service
public class InsertTransferWindowService extends CamelService {

    /**
     * Repository used to save the new transfer window.
     */
    private final TransferWindowRepository transferWindowRepository;
    /**
     * Repository used to find the match week the transfers of the new transfer window will be active from.
     */
    private final MatchWeekRepository matchWeekRepository;

    /**
     * Creates a new service.
     *
     * @param transferWindowRepository Repository used to save the new transfer window.
     * @param matchWeekRepository      Repository used to find the match week the transfers of the new transfer window
     *                                 will be active from.
     */
    @Autowired
    public InsertTransferWindowService(final TransferWindowRepository transferWindowRepository,
                                       final MatchWeekRepository matchWeekRepository) {
        this.transferWindowRepository = transferWindowRepository;
        this.matchWeekRepository = matchWeekRepository;
    }

    /**
     * Inserts a new transfer window.
     *
     * @param insertTransferWindowMessage Insert transfer window message with datetime and transfer day delay.
     */
    @Transactional
    public void insertTransferWindow(final InsertTransferWindowMessage insertTransferWindowMessage) {
        final TransferWindow currentTransferWindow =
                this.transferWindowRepository.findFirstByOrderByDatetimeDesc().orElseThrow(NotFoundException::new);

        if(Status.FINISHED.equals(currentTransferWindow.getStatus())) {
            try {
                final MatchWeek matchWeek = this.matchWeekRepository
                        .findFirstByDateGreaterThanOrderByDateAsc(insertTransferWindowMessage.getDatetime().toLocalDate())
                        .orElseThrow(NotFoundException::new);

                final TransferWindow transferWindow = new TransferWindow();
                transferWindow.setMatchWeek(matchWeek);
                transferWindow.setTransferWindowNumber(currentTransferWindow.getTransferWindowNumber() + 1);
                transferWindow.setDatetime(insertTransferWindowMessage.getDatetime());

                final TransferDay transferDay = new TransferDay();
                transferDay.setTransferWindow(transferWindow);
                transferDay.setTransferDayNumber(1);
                transferDay.setDatetime(insertTransferWindowMessage.getDatetime().plus(insertTransferWindowMessage.getTransferDayDelay(),
                                                                                       ChronoUnit.DAYS));
                transferWindow.getTransferDays().add(transferDay);

                this.transferWindowRepository.save(transferWindow);
            } catch(final NotFoundException e) {
                log.error("No match week with date after {} was found.", insertTransferWindowMessage.getDatetime());
            }
        } else {
            log.error("Insert failed due to current transfer window {} {} having status {}.",
                      currentTransferWindow.getTransferWindowNumber(),
                      currentTransferWindow.getMatchWeek().getSeason().getName(),
                      currentTransferWindow.getStatus());
        }
    }

}
