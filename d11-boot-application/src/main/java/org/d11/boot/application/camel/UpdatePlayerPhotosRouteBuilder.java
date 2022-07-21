package org.d11.boot.application.camel;

import org.apache.camel.LoggingLevel;
import org.d11.boot.application.camel.processor.PlayerDataPhotoProcessor;
import org.d11.boot.application.camel.processor.PlayerPhotoProcessor;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.service.camel.UpdatePlayerService;
import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.camel.processor.WebPageProcessor;
import org.d11.boot.download.DownloadException;
import org.d11.boot.download.premierleague.PremierLeagueClubsDownloader;
import org.d11.boot.download.premierleague.PremierLeaguePlayerPhotoDownloader;
import org.d11.boot.download.premierleague.PremierLeagueSquadDownloader;
import org.d11.boot.jms.JmsQueue;
import org.d11.boot.parser.team.TeamParser;
import org.d11.boot.parser.team.TeamsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Builds a route that downloads player photos.
 */
@Component
public class UpdatePlayerPhotosRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Throttle for the download route just in case there's some sort of anti robot thing we don't want to trigger.
     */
    private static final int THROTTLE_DELAY = 3_000;
    /**
     * Repository used to update player photo file name.
     */
    private final PlayerRepository playerRepository;

    /**
     * Creates a new route builder.
     *
     * @param playerRepository Repository used to update player photo file name.
     */
    @Autowired
    public UpdatePlayerPhotosRouteBuilder(final PlayerRepository playerRepository) {
        super(JmsQueue.UPDATE_PLAYER_PHOTOS_REQUEST);
        this.playerRepository = playerRepository;
    }

    @Override
    public void configure() {
        from(getSource())
                .routeId(getRouteId())
                .log(LoggingLevel.INFO, "Updating player photos...")
                .log(LoggingLevel.INFO, "Downloading Premier League clubs page.")
                .bean(PremierLeagueClubsDownloader.class, "downloadClubs()")
                .process(new WebPageProcessor())
                .log(LoggingLevel.INFO, "Saving page to /files/download/premierleague.com/${exchangeProperty.downloadFileName}.")
                .toD("file://files/download/premierleague.com?fileName=${exchangeProperty.downloadFileName}")
                .bean(TeamsParser.class, "parse(${body})")
                .split().body()
                    .to("direct:downloadTeam")
                .end()
                .log(LoggingLevel.INFO, "Team squad downloads completed.");

        from("direct:downloadTeam")
                .routeId("UPDATE_PLAYER_PHOTOS_DOWNLOAD_TEAM")
                .log(LoggingLevel.INFO, "Downloading Premier League club page for ${body.name} (${body.id}).")
                .bean(PremierLeagueSquadDownloader.class, "downloadSquad(${body.id}, ${body.name})")
                .process(new WebPageProcessor())
                .log(LoggingLevel.INFO, "Saving page to /files/download/premierleague.com/squad/${exchangeProperty.downloadFileName}.")
                .toD("file://files/download/premierleague.com/squad?fileName=${exchangeProperty.downloadFileName}")
                .bean(TeamParser.class, "parse(${body})")
                .split().simple("${body.players}")
                    .to("direct:updatePlayerPhoto")
                .end();

        from("direct:updatePlayerPhoto")
                .onException(DownloadException.class)
                    .log(LoggingLevel.ERROR, "Could not download photo for player ${body.name}.")
                    .handled(true)
                .end()
                .routeId("UPDATE_PLAYER_PHOTO")
                .throttle(1).timePeriodMillis(THROTTLE_DELAY)
                .log(LoggingLevel.INFO, "Downloading ${body.name} player photo.")
                .process(new PlayerDataPhotoProcessor(this.playerRepository))
                .bean(PremierLeaguePlayerPhotoDownloader.class, "downloadPlayerPhoto(${body.photoId})")
                .process(new PlayerPhotoProcessor())
                .choice()
                    .when(simple("${exchangeProperty.savePhotoFile} == true"))
                        .log(LoggingLevel.DEBUG, "Saving photo file.")
                        .toD("file://files/download/premierleague.com/player/photo?fileName=${exchangeProperty.photoFileName}")
                        .bean(UpdatePlayerService.class, "updatePlayerPhotoFileName(${exchangeProperty.playerData})")
                    .otherwise()
                        .log(LoggingLevel.DEBUG, "Player photo already exists.");
    }

}
