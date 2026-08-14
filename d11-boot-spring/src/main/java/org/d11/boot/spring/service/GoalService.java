package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Goal;
import org.d11.boot.spring.repository.GoalRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Goal service.
 */
@Service
public class GoalService extends RepositoryService<Goal, GoalRepository> {

    /** Validation message for non-positive id parameters. */
    private static final String MUST_BE_POSITIVE = "must be positive";

    /**
     * Creates a new goal service.
     *
     * @param goalRepository The repository the service will use.
     */
    public GoalService(final GoalRepository goalRepository) {
        super(Goal.class, goalRepository);
    }

    /**
     * Gets goals scored by players on a specific D11 team in a D11 match.
     *
     * @param d11MatchId The D11 match id.
     * @param d11TeamId  The D11 team id.
     * @return Goals scored by players on the D11 team in the D11 match, ordered by time and added time.
     */
    public List<Goal> getByD11MatchIdAndD11TeamId(final Long d11MatchId, final Long d11TeamId) {
        if (d11MatchId == null || d11MatchId <= 0) {
            throw new BadRequestException("d11MatchId", MUST_BE_POSITIVE);
        }
        if (d11TeamId == null || d11TeamId <= 0) {
            throw new BadRequestException("d11TeamId", MUST_BE_POSITIVE);
        }
        return getJpaRepository().findByD11MatchIdAndD11TeamId(d11MatchId, d11TeamId);
    }

}
