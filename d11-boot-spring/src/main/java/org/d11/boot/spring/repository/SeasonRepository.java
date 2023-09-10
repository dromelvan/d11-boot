package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Season;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Season entities.
 */
@Repository
public interface SeasonRepository extends D11EntityRepository<Season> {

    /**
     * Finds the season with the latest start date. This is the current season.
     *
     * @return Optional with the current season or an empty optional if no seasons exist.
     */
    Optional<Season> findFirstByOrderByDateDesc();

    /**
     * Finds a list of all seasons ordered by descending date.
     *
     * @return List of all seasons ordered by descending date.
     */
    List<Season> findByOrderByDateDesc();

}
