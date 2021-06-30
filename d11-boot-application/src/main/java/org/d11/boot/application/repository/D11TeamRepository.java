package org.d11.boot.application.repository;

import org.d11.boot.application.model.D11Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for D11 team entities.
 */
@Repository
public interface D11TeamRepository extends D11EntityRepository<D11Team> {

    /**
     * Gets D11 teams participating in a specific season.
     *
     * @param seasonId Id for the season for which D11 teams will be looked up.
     * @return D11 teams participating in the specified season.
     */
    @Query("SELECT d11Team FROM D11Team d11Team " +
            "JOIN D11TeamSeasonStat d11TeamSeasonStat ON d11TeamSeasonStat.d11Team = d11Team " +
            "WHERE d11TeamSeasonStat.season.id = :seasonId " +
            "ORDER BY d11Team.name")
    List<D11Team> findByD11TeamSeasonStatSeasonIdOrderByName(@Param("seasonId") Long seasonId);

}
