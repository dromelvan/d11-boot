package org.d11.boot.jms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Data for a team in parsed team page.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamData extends JmsModel {

    /**
     * Team id on the site the team was parsed from.
     */
    private Integer id;
    /**
     * Team name.
     */
    private String name;
    /**
     * List of players belonging to the team. This is empty if the parsed page did not contain player data.
     */
    private List<PlayerData> players = new ArrayList<>();

    /**
     * Creates a new team data object.
     *
     * @param id   Team id.
     * @param name Team name.
     */
    public TeamData(final int id, final String name) {
        this(id, name, new ArrayList<>());
    }

}
