package org.d11.boot.parser.team.premierleague.v2;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.model.ParsedPlayerData;
import org.d11.boot.parser.model.ParsedTeamData;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests PremierLeagueSquadParserV2.
 */
class PremierLeagueSquadParserV2Tests {

    /**
     * PremierLeagueSquadParserV2::parse.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParse() throws IOException, ParserException {
        final int playerCount = 36;
        final PremierLeagueSquadParserV2 premierLeagueSquadParserV2 = new PremierLeagueSquadParserV2();
        final File squadFile = new File("src/test/resources/premierleague/squad/v2/squad.html");
        final ParsedTeamData parsedTeamData = premierLeagueSquadParserV2.parse(squadFile);

        assertNotNull(parsedTeamData, "PremierLeagueSquadParserV2::parse team not null");
        assertNotNull(parsedTeamData.getSiteId(), "PremierLeagueSquadParserV2::parse team site id not null");
        assertFalse(StringUtils.isBlank(parsedTeamData.getName()), "PremierLeagueSquadParserV2::parse team name blank");

        assertNotNull(parsedTeamData.getPlayers(), "PremierLeagueSquadParserV2::parse team players not null");
        assertEquals(playerCount, parsedTeamData.getPlayers().size(),
                     "PremierLeagueSquadParserV2::parse team players size");

        for (final ParsedPlayerData playerData : parsedTeamData.getPlayers()) {
            assertNotNull(playerData.getSiteId(), "PremierLeagueSquadParserV2::parse player site id not null");
            assertFalse(StringUtils.isBlank(playerData.getName()),
                        "PremierLeagueSquadParserV2::parse player name blank");
            assertNotNull(playerData.getShirtNumber(),
                          "PremierLeagueSquadParserV2::parse player shirt number not null");
            assertFalse(StringUtils.isBlank(playerData.getPosition()),
                        "PremierLeagueSquadParserV2::parse player position blank");
            assertFalse(StringUtils.isBlank(playerData.getNationality()),
                        "PremierLeagueSquadParserV2::parse player nationality blank");
        }
    }

}
