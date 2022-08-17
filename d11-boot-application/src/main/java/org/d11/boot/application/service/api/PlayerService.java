package org.d11.boot.application.service.api;

import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.api.model.PlayerTransferStatusDTO;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides player services.
 */
@Service
public class PlayerService extends ApiRepositoryService<Player, PlayerDTO, PlayerRepository> {

    /**
     * Repository for looking up player season stats.
     */
    private final PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Creates a new service.
     *
     * @param playerRepository The repository this service will use.
     * @param playerSeasonStatRepository The player season stat repository this service will use.
     */
    @Autowired
    public PlayerService(final PlayerRepository playerRepository,
                         final PlayerSeasonStatRepository playerSeasonStatRepository) {
        super(playerRepository);
        this.playerSeasonStatRepository = playerSeasonStatRepository;
    }

    /**
     * Finds player transfer status for a specific player.
     *
     * @param playerId Id of the player for which transfer status will be looked up.
     * @return Current transfer status for the player.
     */
    public PlayerTransferStatusDTO findPlayerTransferStatusById(final long playerId) {
        final PlayerTransferStatusDTO playerTransferStatusDTO = new PlayerTransferStatusDTO();

        final Player player = getJpaRepository().findById(playerId).orElseThrow(NotFoundException::new);

        this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(playerId, getCurrentSeason().getId()).ifPresent(playerSeasonStat -> {
            playerTransferStatusDTO.setId(playerId);

            getCurrentUser().ifPresent(user -> user.getD11Team().ifPresent(d11Team -> {
                final TransferDay transferDay = getCurrentSeason().getCurrentTransferDay();

                if(Status.PENDING.equals(transferDay.getStatus()) && player.isAdministratedBy(user)) {
                    playerTransferStatusDTO.setTransferListRemovable(transferDay.isTransferListed(player));
                    playerTransferStatusDTO.setTransferListable(!playerTransferStatusDTO.isTransferListRemovable()
                                                                 && d11Team.getRemainingTransfers(getCurrentSeason()) > 0);
                } else if(Status.ACTIVE.equals(transferDay.getStatus())
                        && playerSeasonStat.getD11Team().isDummy()
                        && d11Team.isBiddablePosition(playerSeasonStat.getPosition())) {
                    playerTransferStatusDTO.setTransferBidRemovable(transferDay.hasTransferBid(player, d11Team));
                    playerTransferStatusDTO.setTransferBiddable(!playerTransferStatusDTO.isTransferBidRemovable());
                    playerTransferStatusDTO.setMaxBid(d11Team.getMaxBid());
                }
            }));
        });

        return playerTransferStatusDTO;
    }
}
