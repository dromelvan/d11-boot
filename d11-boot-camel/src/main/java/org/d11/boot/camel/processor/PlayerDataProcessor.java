package org.d11.boot.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.d11.boot.jms.model.PlayerData;

/**
 * Processes a player data JMS message by setting dataFileName property in the Camel exchange.
 */
public class PlayerDataProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) {
        final PlayerData playerData = exchange.getIn().getBody(PlayerData.class);

        final String fileName = String.format("%s (%d).json", playerData.getName(), playerData.getId());
        exchange.setProperty("dataFileName", fileName);
    }

}
