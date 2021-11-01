package org.d11.boot.cli.camel;

import org.apache.camel.LoggingLevel;
import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.cli.camel.processor.ShutdownProcessor;
import org.d11.boot.camel.processor.TeamDataProcessor;
import org.d11.boot.camel.processor.WebPageProcessor;
import org.d11.boot.download.premierleague.PremierLeagueClubsDownloader;
import org.d11.boot.download.premierleague.PremierLeagueSquadDownloader;
import org.d11.boot.jms.JmsQueue;
import org.d11.boot.parser.team.TeamParser;
import org.d11.boot.parser.team.TeamsParser;
import org.springframework.stereotype.Component;

/**
 * Builds a route that downloads Premier League squads and sends the data for each team to an ActiveMQ update squad queue.
 */
@Component
public class UpdateSquadsRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new update squads route builder.
     */
    public UpdateSquadsRouteBuilder() {
        super(null, JmsQueue.UPDATE_SQUAD);
    }

    @Override
    public void configure() {
        from("{{app.route.updateSquads}}")
                .routeId("UPDATE_SQUADS")
                .log(LoggingLevel.INFO, "Starting team squad updates...")
                .log(LoggingLevel.INFO, "Downloading Premier League clubs page.")
                .bean(PremierLeagueClubsDownloader.class, "downloadClubs()")
                .process(new WebPageProcessor())
                .log(LoggingLevel.INFO, "Saving page to /files/download/premierleague.com/${exchangeProperty.downloadFileName}.")
                .toD("file://files/download/premierleague.com?fileName=${exchangeProperty.downloadFileName}")
                .bean(TeamsParser.class, "parse(${body})")
                .split().body()
                    .to("direct:updateSquad")
                .end()
                .log(LoggingLevel.INFO, "Team squad updates completed.")
                .process(new ShutdownProcessor());

        from("direct:updateSquad")
                .routeId("UPDATE_SQUAD")
                .log(LoggingLevel.INFO, "Downloading Premier League club page for ${body.name} (${body.id}).")
                .bean(PremierLeagueSquadDownloader.class, "downloadSquad(${body.id}, ${body.name})")
                .process(new WebPageProcessor())
                .log(LoggingLevel.INFO, "Saving page to /files/download/premierleague.com/squad/${exchangeProperty.downloadFileName}.")
                .toD("file://files/download/premierleague.com/squad?fileName=${exchangeProperty.downloadFileName}")
                .bean(TeamParser.class, "parse(${body})")
                .process(new TeamDataProcessor())
                .marshal(getDestinationDataFormat())
                .log(LoggingLevel.INFO, "Saving data to /files/data/premierleague.com/squad/${exchangeProperty.dataFileName}.")
                .toD("file://files/data/premierleague.com/squad?fileName=${exchangeProperty.dataFileName}")
                .to(getDestination());
    }

}
