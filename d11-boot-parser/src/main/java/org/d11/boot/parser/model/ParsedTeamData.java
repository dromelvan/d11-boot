package org.d11.boot.parser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Data for a team in a parsed team page.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParsedTeamData extends ParsedData {

    /**
     * D11 team id. This is not set by the parser, but it might be a useful property to set when handling parsed team
     * data.
     */
    private Integer id;

    /**
     * Team id on whatever site the team data was parsed from.
     */
    private Integer siteId;

    /**
     * Team name.
     */
    private String name;

    /**
     * List of players belonging to the team. This is empty if the parsed page did not contain player data.
     */
    @Builder.Default
    private List<ParsedPlayerData> players = new ArrayList<>();

}
