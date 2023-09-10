package org.d11.boot.parser.team.premierleague.v1;

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
 * Tests PremierLeagueSquadParserV1.
 */
class PremierLeagueSquadParserV1Tests {

    /**
     * PremierLeagueSquadParserV1::parse.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParse() throws IOException, ParserException {
        final int playerCount = 31;
        final PremierLeagueSquadParserV1 premierLeagueSquadParserV1 = new PremierLeagueSquadParserV1();
        final File squadFile = new File("src/test/resources/premierleague/squad/v1/squad.html");
        final ParsedTeamData parsedTeamData = premierLeagueSquadParserV1.parse(squadFile);

        assertNotNull(parsedTeamData, "PremierLeagueSquadParserV1::parse team not null");
        assertNotNull(parsedTeamData.getSiteId(), "PremierLeagueSquadParserV1::parse team site id not null");
        assertFalse(StringUtils.isBlank(parsedTeamData.getName()), "PremierLeagueSquadParserV1::parse team name blank");

        assertNotNull(parsedTeamData.getPlayers(), "PremierLeagueSquadParserV1::parse team players not null");
        assertEquals(playerCount, parsedTeamData.getPlayers().size(),
                     "PremierLeagueSquadParserV1::parse team players size");

        for (final ParsedPlayerData playerData : parsedTeamData.getPlayers()) {
            assertNotNull(playerData.getSiteId(), "PremierLeagueSquadParserV1::parse player site id not null");
            assertFalse(StringUtils.isBlank(playerData.getName()),
                        "PremierLeagueSquadParserV1::parse player name blank");
            assertNotNull(playerData.getShirtNumber(),
                          "PremierLeagueSquadParserV1::parse player shirt number not null");
            assertFalse(StringUtils.isBlank(playerData.getPosition()),
                        "PremierLeagueSquadParserV1::parse player position blank");
            assertFalse(StringUtils.isBlank(playerData.getNationality()),
                        "PremierLeagueSquadParserV1::parse player nationality blank");
        }
    }

}
