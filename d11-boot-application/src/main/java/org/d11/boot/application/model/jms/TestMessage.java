package org.d11.boot.application.model.jms;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Test JMS message for the test queue.
 */
@Data
public class TestMessage extends JmsMessage {

    /**
     * Some test text.
     */
    private String text;
    /**
     * Some test date.
     */
    private LocalDateTime datetime;

}
