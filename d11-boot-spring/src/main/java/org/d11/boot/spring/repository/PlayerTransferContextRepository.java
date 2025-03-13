package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.PlayerTransferContextId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository for PlayerTransferContext entities.
 */
@org.springframework.stereotype.Repository
public interface PlayerTransferContextRepository extends Repository<PlayerTransferContext, PlayerTransferContextId> {

    /**
     * Finds the current player transfer context for a player as it pertains to the D11 team belonging to a user.
     *
     * @param playerId The player id.
     * @param ownerId Id of the owner or co-owner of the D11 team.
     * @return Current player transfer context for the player and the D11 team.
     */
    @Query("""
           SELECT ptc
           FROM PlayerTransferContext ptc
           WHERE ptc.player.id = :playerId AND (ptc.ownerId = :ownerId OR ptc.coOwnerId = :ownerId)
           """)
    Optional<PlayerTransferContext> findByPlayerIdAndOwnerId(@Param("playerId") Long playerId,
                                                             @Param("ownerId") Long ownerId);

}
