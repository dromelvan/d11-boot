package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Player entities.
 */
@Repository
public interface PlayerRepository extends D11EntityRepository<Player> {

    /**
     * Finds a player by WhoScored id.
     *
     * @param whoscoredId The WhoScored id.
     * @return Optional with player with the WhoScored id or empty optional if no player was found.
     */
    Optional<Player> findByWhoscoredId(@Param("whoscoredId") Integer whoscoredId);

    /**
     * Finds a player by Premier League id.
     *
     * @param premierLeagueId The Premier League id.
     * @return Optional with player with the Premier League id or empty optional if no player was found.
     */
    Optional<Player> findByPremierLeagueId(@Param("premierLeagueId") Integer premierLeagueId);

    /**
     * Finds a list of players by parameterized name.
     *
     * @param parameterizedName The parameterized name.
     * @return List of players with the parameterized name.
     */
    List<Player> findByParameterizedName(@Param("parameterizedName") String parameterizedName);

    /**
     * Finds players with parameterized names that match the provided parameterized name.
     *
     * @param parameterizedName The parameterized name that will be matched to existing parameterized names.
     * @return List of player search results with matching parameterized names.
     */
    // There's no UNION in JPQL, so we have to make a native query for this.
    @Query(value =
           """ 
           SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, ts.id AS teamId, ts.name AS teamName
           FROM player ps
                JOIN player_season_stat psss ON ps.id = psss.player_id
                JOIN team ts ON ts.id = psss.team_id
                WHERE season_id = (SELECT MAX(id) FROM season)
                AND parameterized_name LIKE CONCAT('%', :parameterizedName, '%')
           UNION
           SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, 1 AS teamId, 'None' AS teamName
           FROM player ps
                WHERE parameterized_name LIKE CONCAT('%', :parameterizedName, '%')
                AND NOT EXISTS(SELECT id
                               FROM player_season_stat psss
                               WHERE player_id = ps.id AND season_id = (SELECT MAX(id) FROM season))
           ORDER BY name
           """,
           nativeQuery = true)
    List<PlayerSearchResult> findByParameterizedNameLike(@Param("parameterizedName") String parameterizedName);

    /**
     * Finds players with parameterized names that are exactly the provided parameterized name.
     *
     * @param parameterizedName The parameterized name that will be compared to existing parameterized names.
     * @return List of player search results with exactly the parameterized names.
     */
    // There's no UNION in JPQL, so we have to make a native query for this.
    @Query(value =
           """ 
           SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, ts.id AS teamId, ts.name AS teamName
           FROM player ps
                JOIN player_season_stat psss ON ps.id = psss.player_id
                JOIN team ts ON ts.id = psss.team_id
                WHERE season_id = (SELECT MAX(id) FROM season)
                AND parameterized_name = :parameterizedName
           UNION
           SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, 1 AS teamId, 'None' AS teamName
           FROM player ps
                WHERE parameterized_name = :parameterizedName
                AND NOT EXISTS(SELECT id
                               FROM player_season_stat psss
                               WHERE player_id = ps.id AND season_id = (SELECT MAX(id) FROM season))
           ORDER BY name
           """,
           nativeQuery = true)
    List<PlayerSearchResult> findByParameterizedNameExact(@Param("parameterizedName") String parameterizedName);

}
