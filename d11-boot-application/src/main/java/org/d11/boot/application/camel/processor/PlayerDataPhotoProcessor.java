package org.d11.boot.application.camel.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.jms.model.PlayerData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.CRC32;

/**
 * Processes a player data JMS message by setting photoFileName property in the Camel exchange.
 */
@Slf4j
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
        exchange.setProperty("playerData", playerData);
        exchange.setProperty("photoFileCrc", 0L);

        try {
            final Player player = this.playerRepository.findByPremierLeagueId(playerData.getId()).orElseThrow(NotFoundException::new);
            final String photoFileName = String.format("%s-%d.png", player.getParameterizedName(), player.getId());
            exchange.setProperty("photoFileName", photoFileName);

            final File photoFile = new File("files/data/premierleague.com/player/photo/" + photoFileName);
            if(photoFile.exists()) {
                final byte[] bytes = Files.readAllBytes(photoFile.toPath());
                final CRC32 crc32 = new CRC32();
                crc32.update(bytes);
                exchange.setProperty("photoFileCrc", crc32.getValue());
            }
        } catch(final IOException e) {
            // Shouldn't really happen
            log.error("Error when processing player data photo.", e);
        } catch(final NotFoundException e) {
            final String photoFileName = String.format("Unknown player %s (%d).png", playerData.getName(), playerData.getId());
            exchange.setProperty("photoFileName", photoFileName);
        }
    }

}
