package org.d11.boot.jms.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.d11.boot.jms.model.JmsModel;
import org.d11.boot.jms.model.TeamData;

/**
 * JMS message to put on the update squad queue to trigger a squad update.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSquadMessage extends JmsModel {

    /**
     * Parsed team data that will be used to update the squad.
     */
    private TeamData teamData;

}
