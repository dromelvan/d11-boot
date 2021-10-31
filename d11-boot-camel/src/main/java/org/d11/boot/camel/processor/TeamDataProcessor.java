package org.d11.boot.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.d11.boot.jms.message.UpdateSquadMessage;
import org.d11.boot.jms.model.TeamData;

/**
 * Processes a team data JMS message by setting dataFileName property in the Camel exchange and sets an update squad JMS
 * message as exchange message body.
 */
public class TeamDataProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) {
        final TeamData teamData = exchange.getIn().getBody(TeamData.class);

        final String fileName = String.format("%s.json", teamData.getName());
        exchange.setProperty("dataFileName", fileName);

        final UpdateSquadMessage updateSquadMessage = new UpdateSquadMessage(teamData);
        exchange.getIn().setBody(updateSquadMessage);
    }

}
