package org.d11.boot.application.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.jms.model.PlayerData;

/**
 * Processes a player data JMS message by setting photoFileName property in the Camel exchange.
 */
public class PlayerDataPhotoProcessor implements Processor {

    /**
     * Player repository used to find player id.
     */
    private final PlayerRepository playerRepository;

    /**
     * Creates a new processor.
     *
     * @param playerRepository The player repository the processor will use.
     */
    public PlayerDataPhotoProcessor(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void process(final Exchange exchange) {
        final PlayerData playerData = exchange.getIn().getBody(PlayerData.class);
        try {
            final Player player = this.playerRepository.findByPremierLeagueId(playerData.getId()).orElseThrow(NotFoundException::new);
            final String photoFileName = String.format("%s-%d.png", player.getParameterizedName(), player.getId());
            exchange.setProperty("photoFileName", photoFileName);
        } catch(final NotFoundException e) {
            final String photoFileName = String.format("Unknown player %s (%d).png", playerData.getName(), playerData.getId());
            exchange.setProperty("photoFileName", photoFileName);
        }
    }

}
