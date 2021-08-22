package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.Player;
import org.d11.boot.application.model.jpa.projection.PlayerSearchResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for player entities.
 */
@Repository
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public interface PlayerRepository extends D11EntityRepository<Player> {

    /**
     * Finds players with parameterized names that match the provided parameterized name.
     *
     * @param parameterizedName The parameterized name that will be matched to existing parameterized names.
     * @return List of player search results with matching parameterized names.
     */
    // There's no UNION in JPQL so we have to make a native query for this.
    // TODO: Move this to some properties file or other.
    @Query(value =
            "SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, ps.photo_file_name as photoFileName, ts.id AS teamId, ts.name AS teamName " +
            "FROM {h-schema}player ps " +
            "     JOIN {h-schema}player_season_stat psss ON ps.id = psss.player_id " +
            "     JOIN {h-schema}team ts ON ts.id = psss.team_id " +
            "     WHERE season_id = (SELECT MAX(id) FROM {h-schema}season) " +
            "     AND parameterized_name LIKE :parameterizedName " +
            "UNION " +
            "SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, ps.photo_file_name as photoFileName, 1 AS teamId, 'None' AS teamName " +
            "FROM {h-schema}player ps " +
            "     WHERE parameterized_name LIKE :parameterizedName " +
            "     AND NOT EXISTS(SELECT id " +
            "                    FROM {h-schema}player_season_stat psss " +
            "                    WHERE player_id = ps.id AND season_id = (SELECT MAX(id) FROM {h-schema}season)) " +
            "ORDER BY name",
            nativeQuery = true)
    List<PlayerSearchResult> findByParameterizedNameLike(@Param("parameterizedName")String parameterizedName);

    /**
     * Finds players with parameterized names that are exactly the provided parameterized name.
     *
     * @param parameterizedName The parameterized name that will be compared to existing parameterized names.
     * @return List of player search results with exactly the parameterized names.
     */
    @Query(value =
            "SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, ps.photo_file_name as photoFileName, ts.id AS teamId, ts.name AS teamName " +
            "FROM {h-schema}player ps " +
            "     JOIN {h-schema}player_season_stat psss ON ps.id = psss.player_id " +
            "     JOIN {h-schema}team ts ON ts.id = psss.team_id " +
            "     WHERE season_id = (SELECT MAX(id) FROM {h-schema}season) " +
            "     AND parameterized_name = :parameterizedName " +
            "UNION " +
            "SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, ps.photo_file_name as photoFileName, 1 AS teamId, 'None' AS teamName " +
            "FROM {h-schema}player ps " +
            "     WHERE parameterized_name = :parameterizedName " +
            "     AND NOT EXISTS(SELECT id " +
            "                    FROM {h-schema}player_season_stat psss " +
            "                    WHERE player_id = ps.id AND season_id = (SELECT MAX(id) FROM {h-schema}season)) " +
            "ORDER BY name",
            nativeQuery = true)
    List<PlayerSearchResult> findByParameterizedName(@Param("parameterizedName")String parameterizedName);

}
