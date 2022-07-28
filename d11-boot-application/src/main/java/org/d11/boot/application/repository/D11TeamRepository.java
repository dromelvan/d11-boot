package org.d11.boot.application.repository;

import org.d11.boot.application.model.D11Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for D11 team entities.
 */
@Repository
public interface D11TeamRepository extends D11EntityRepository<D11Team> {

    /**
     * Finds all D11 teams.
     *
     * @return List of all teams, ordered by name.
     */
    List<D11Team> findByOrderByName();

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

    /**
     * Gets D11 team owned by a specific user.
     *
     * @param email Email/username of the user for which D11 team will be looked up.
     * @return Optional of D11 team owned by the user.
     */
    Optional<D11Team> findByOwnerEmail(@Param("email") String email);

    /**
     * Gets D11 team owned or co-owned by a specific user.
     *
     * @param ownerEmail   Email/username of the owner for which D11 team will be looked up.
     * @param coOwnerEmail Email/username of the co-owner for which D11 team will be looked up.
     * @return Optional of D11 team owned or co-owned by the user.
     */
    Optional<D11Team> findByOwnerEmailOrCoOwnerEmail(@Param("ownerEmail") String ownerEmail,
                                                     @Param("coOwnerEmail") String coOwnerEmail);
}
