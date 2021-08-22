package org.d11.boot.application.service.api;

import org.d11.boot.api.model.D11MatchDTO;
import org.d11.boot.api.model.D11MatchesByDateDTO;
import org.d11.boot.application.model.jpa.D11Match;
import org.d11.boot.application.model.jpa.MatchWeek;
import org.d11.boot.application.model.jpa.Status;
import org.d11.boot.application.repository.D11MatchRepository;
import org.d11.boot.application.repository.MatchWeekRepository;
import org.d11.boot.application.util.D11MatchesByDateMapperConverter;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Provides D11 match services.
 */
@Service
public class D11MatchService extends ApiRepositoryService<D11Match, D11MatchDTO, D11MatchRepository> {

    /**
     * A set of statuses that means a match is current.
     */
    private final Set<Status> currentStatuses = Set.of(Status.ACTIVE, Status.FULL_TIME);
    /**
     * Converts a list of matches to a map of date to matches mappings.
     */
    private final D11MatchesByDateMapperConverter d11MatchesByDateMapperConverter = new D11MatchesByDateMapperConverter();
    /**
     * Repository for looking up current match week.
     */
    private final MatchWeekRepository matchWeekRepository;

    /**
     * Creates a new service.
     *
     * @param d11MatchRepository The repository this service will use.
     * @param matchWeekRepository Repository for looking up current match week.
     */
    @Autowired
    public D11MatchService(final D11MatchRepository d11MatchRepository, final MatchWeekRepository matchWeekRepository) {
        super(d11MatchRepository);
        this.matchWeekRepository = matchWeekRepository;
    }

    /**
     * Gets D11 match ids for a specific D11team and a specific season.
     *
     * @param d11TeamId Id for the D11 team for which D11 match ids will be looked up.
     * @param seasonId Id for the season for which D11 match ids will be looked up.
     * @return D11 match ids for the D11team and season.
     */
    public List<Long> findD11MatchByD11TeamIdAndSeasonId(final long d11TeamId, final long seasonId) {
        return getJpaRepository().findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime(d11TeamId, seasonId);
    }

    /**
     * Gets current D11 matches.
     *
     * @return A set of current D11 matches mapped and sorted by datetime.
     */
    public D11MatchesByDateDTO findCurrentD11Matches() {
        final MatchWeek matchWeek = this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(LocalDate.now())
                .orElseThrow(() -> new NotFoundException("Current match week not found"));
        final List<D11Match> d11Matches = getJpaRepository().findByMatchWeekIdOrStatusInOrderByDatetime(matchWeek.getId(), this.currentStatuses);

        return new D11MatchesByDateDTO().d11Matches(this.d11MatchesByDateMapperConverter.convert(d11Matches));
    }


}
