package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.SeasonWinners;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * Finds all seasons with their ranking-1 stat entries (D11 team, PL team, player) ordered by descending date.
     *
     * @return List of season winners ordered by descending date.
     */
    @Query("""
            SELECT new SeasonWinners(s, dss, tss, pss)
            FROM Season s
            LEFT JOIN D11TeamSeasonStat dss ON dss.season = s AND dss.ranking = 1
            LEFT JOIN TeamSeasonStat tss ON tss.season = s AND tss.ranking = 1
            LEFT JOIN PlayerSeasonStat pss ON pss.season = s AND pss.ranking = 1
            ORDER BY s.date DESC
            """)
    List<SeasonWinners> findAllSeasonWinners();

}
