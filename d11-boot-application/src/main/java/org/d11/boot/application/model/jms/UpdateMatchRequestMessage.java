package org.d11.boot.application.model.jms;

import lombok.Data;

/**
 * JMS message to put on the update match request queue to trigger a match update when we only know the D11 match id.
 */
@Data
public class UpdateMatchRequestMessage extends JmsMessage {

    /**
     * D11 match id of the match we want to update.
     */
    private long matchId;
    /**
     * Do or do not finish the match after update.
     */
    private boolean finish;

}
