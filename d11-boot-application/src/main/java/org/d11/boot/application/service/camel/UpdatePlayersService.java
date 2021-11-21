package org.d11.boot.application.service.camel;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.application.util.Parameterizer;
import org.d11.boot.jms.model.PlayerData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for updating player info from PremierLeague.com.
 */
@Slf4j
@Service
public class UpdatePlayersService extends CamelService {

    /**
     * Updates player info from PremierLeague.com.
     *
     * @param playerDatas List of player data that should be updated.
     */
    @Transactional
    @SuppressWarnings("checkstyle:CyclomaticComplexity")
    public void updatePlayers(final List<PlayerData> playerDatas) {
        log.info("Updating player info for {} players.", playerDatas.size());
        final PlayerRepository playerRepository = getRepository(PlayerRepository.class);
        for(final PlayerData playerData : playerDatas) {
            try {
                final Player player = playerRepository.findByPremierLeagueId(playerData.getId()).or(() -> {
                    final List<Player> players = playerRepository.findByParameterizedName(Parameterizer.parameterize(playerData.getName()));
                    if(players.size() == 1) {
                        return Optional.of(players.get(0));
                    } else if(players.size() > 1) {
                        log.warn("Found {} matches for player {}.", players.size(), playerData.getName());
                    }
                    return Optional.empty();
                }).orElseThrow(NotFoundException::new);

                log.debug("Updating player info for {} ({}).", playerData.getName(), player.getId());
                if(playerData.getId() != null && player.getPremierLeagueId() == 0) {
                    log.info("Changing {} Premier League id from {} to {}.", player.getName(), player.getPremierLeagueId(), playerData.getId());
                    player.setPremierLeagueId(playerData.getId());
                }
                if(playerData.getDateOfBirth() != null && !playerData.getDateOfBirth().equals(player.getDateOfBirth())) {
                    log.info("Changing {} DoB from {} to {}.", player.getName(), player.getDateOfBirth(), playerData.getDateOfBirth());
                    player.setDateOfBirth(playerData.getDateOfBirth());
                }
                if(playerData.getHeight() != null && !playerData.getHeight().equals(player.getHeight())) {
                    log.info("Changing {} height from {} to {}.", player.getName(), player.getHeight(), playerData.getHeight());
                    player.setHeight(playerData.getHeight());
                }
            } catch(NotFoundException e) {
                log.warn("Could not find player {} with Premier League id {}.", playerData.getName(), playerData.getId());
            }
        }
        log.info("Player info updated.");
    }

}
