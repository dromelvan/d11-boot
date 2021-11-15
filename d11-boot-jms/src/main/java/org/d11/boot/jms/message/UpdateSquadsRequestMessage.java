package org.d11.boot.jms.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.d11.boot.jms.model.JmsModel;

/**
 * JMS message to put on the update squads request queue to trigger an update of all team squads.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSquadsRequestMessage extends JmsModel {

    /**
     * We just need a dummy value to trigger the queue.
     */
    private boolean update = true;

}
