package org.d11.boot.application.model.jms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.d11.boot.parser.model.MatchData;

/**
 * JMS message to put on the update match queue to trigger a match update.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMatchMessage extends JmsMessage {

    /**
     * Parsed match data that will be used to update the match.
     */
    private MatchData matchData;
    /**
     * Do or do not finish the match after update.
     */
    private boolean finish;

}
