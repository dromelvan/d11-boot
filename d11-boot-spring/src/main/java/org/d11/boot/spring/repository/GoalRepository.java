package org.d11.boot.spring.repository;


import org.d11.boot.spring.model.Goal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Goal entities.
 */
@Repository
public interface GoalRepository extends D11EntityRepository<Goal> {

    /**
     * Gets goals scored by players on a specific D11 team in a D11 match.
     *
     * @param d11MatchId The D11 match id.
     * @param d11TeamId  The D11 team id.
     * @return Goals scored by players on the D11 team in the D11 match, ordered by time and added time.
     */
    @Query("""
           SELECT g FROM Goal g
           JOIN FETCH g.player
           JOIN PlayerMatchStat pms ON pms.match = g.match AND pms.player = g.player
           WHERE pms.d11Match.id = :d11MatchId AND pms.d11Team.id = :d11TeamId
           ORDER BY g.time, g.addedTime
           """)
    List<Goal> findByD11MatchIdAndD11TeamId(@Param("d11MatchId") Long d11MatchId,
                                            @Param("d11TeamId") Long d11TeamId);

}
