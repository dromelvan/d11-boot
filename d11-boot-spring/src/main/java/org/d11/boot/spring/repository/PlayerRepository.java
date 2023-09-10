package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Player;
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

}
