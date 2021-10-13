package org.d11.boot.application.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.d11.boot.jms.message.DownloadWhoscoredMatchMessage;

/**
 * Processes a download whoscored match JMS message by setting matchId, matchWeekDirectory and finish properties in the
 * Camel exchange.
 */
public class DownloadWhoscoredMatchProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) {
        final DownloadWhoscoredMatchMessage downloadWhoscoredMatchMessage = exchange.getIn().getBody(DownloadWhoscoredMatchMessage.class);

        exchange.setProperty("matchId", downloadWhoscoredMatchMessage.getId());
        exchange.setProperty("finish", downloadWhoscoredMatchMessage.getFinish());

        final String matchWeekDirectory = String.format("%s/%02d", downloadWhoscoredMatchMessage.getSeasonName(),
                                                                   downloadWhoscoredMatchMessage.getMatchWeekNumber());
        exchange.setProperty("matchWeekDirectory", matchWeekDirectory);
    }

}
