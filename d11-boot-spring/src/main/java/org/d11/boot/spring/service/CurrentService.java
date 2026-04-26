package org.d11.boot.spring.service;

import lombok.RequiredArgsConstructor;
import org.d11.boot.spring.model.CurrentResult;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Service that provides the current season, match week, transfer window and transfer day.
 */
@Service
@RequiredArgsConstructor
public class CurrentService {

    /**
     * Season repository.
     */
    private final SeasonRepository seasonRepository;

    /**
     * Match week repository.
     */
    private final MatchWeekRepository matchWeekRepository;

    /**
     * Transfer window repository.
     */
    private final TransferWindowRepository transferWindowRepository;

    /**
     * Transfer day repository.
     */
    private final TransferDayRepository transferDayRepository;

    /**
     * Gets the current season, match week, transfer window and transfer day. Any resource that does not currently exist
     * will be null in the result.
     *
     * @return Current result with available resources.
     */
    public CurrentResult getCurrent() {
        return new CurrentResult(
                this.seasonRepository.findFirstByOrderByDateDesc().orElse(null),
                this.matchWeekRepository.findFirstBySeasonStatusOrderByDateAsc(Status.PENDING)
                        .or(() -> this.matchWeekRepository
                                .findFirstByDateLessThanEqualOrderByDateDesc(LocalDate.now()))
                        .orElse(null),
                this.transferWindowRepository.findFirstByOrderByDatetimeDesc().orElse(null),
                this.transferDayRepository.findFirstByOrderByDatetimeDesc().orElse(null)
        );
    }

}
