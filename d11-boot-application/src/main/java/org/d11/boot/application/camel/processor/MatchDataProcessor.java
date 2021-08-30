package org.d11.boot.application.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.d11.boot.application.model.jms.UpdateMatchMessage;
import org.d11.boot.parser.model.MatchData;

/**
 * Processes a match data JMS message by setting matchId, dataFileName and finish properties in the Camel exchange and
 * sets an update match JMS message as exchange message body.
 */
public class MatchDataProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) {
        final long matchId = exchange.getProperty("matchId", Long.class);
        final boolean finish = exchange.getProperty("finish", Boolean.class);
        final MatchData matchData = exchange.getIn().getBody(MatchData.class);
        matchData.setMatchId(matchId);

        exchange.setProperty("dataFileName", String.format("%s vs %s (%s).json", matchData.getHomeTeamName(),
                                                                                 matchData.getAwayTeamName(),
                                                                                 matchData.getElapsed().replace("/", "")));
        final UpdateMatchMessage updateMatchMessage = new UpdateMatchMessage(matchData, finish);
        exchange.getIn().setBody(updateMatchMessage);
    }

}
