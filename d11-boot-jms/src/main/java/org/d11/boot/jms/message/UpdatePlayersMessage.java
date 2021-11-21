package org.d11.boot.jms.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.d11.boot.jms.model.JmsModel;
import org.d11.boot.jms.model.PlayerData;

import java.util.List;

/**
 * JMS message to put on the update players queue to trigger a player info update.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlayersMessage extends JmsModel {

    /**
     * List of player data that should be updated.
     */
    private List<PlayerData> players;

}
