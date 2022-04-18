package org.d11.boot.jms.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * JMS message to put on the insert transfer window queue to trigger creation of a new transfer window.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertTransferWindowMessage extends JmsMessage {

    /**
     * Transfer window transfer listing deadline.
     */
    private LocalDateTime datetime;

    /**
     * Number of days after transfer listing deadline the first transfer day will take place.
     */
    private int transferDayDelay;

}
