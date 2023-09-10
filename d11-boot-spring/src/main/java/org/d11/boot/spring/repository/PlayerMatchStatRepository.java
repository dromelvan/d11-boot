package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.PlayerMatchStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for PlayerMatchStat entities.
 */
@Repository
public interface PlayerMatchStatRepository extends D11EntityRepository<PlayerMatchStat> {

    /**
     * Finds player match stats by match id ordered by position sort order.
     *
     * @param matchId The match id.
     * @return Player match stats for the match ordered by position sort order..
     */
    List<PlayerMatchStat> findByMatchIdOrderByPositionSortOrder(@Param("matchId") Long matchId);

    /**
     * Finds player match stats by D11 match id ordered by position sort order.
     *
     * @param d11MatchId The D11 match id.
     * @return Player match stats for the D11 match ordered by position sort order..
     */
    List<PlayerMatchStat> findByD11MatchIdOrderByPositionSortOrder(@Param("d11MatchId") Long d11MatchId);

}
