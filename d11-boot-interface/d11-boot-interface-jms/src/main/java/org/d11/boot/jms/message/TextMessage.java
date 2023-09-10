package org.d11.boot.jms.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * A basic JMS message that can be used with the test queue.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextMessage extends JmsMessage {

    /**
     * Message text.
     */
    private String text;

    /**
     * Message datetime.
     */
    private LocalDateTime datetime;

}
