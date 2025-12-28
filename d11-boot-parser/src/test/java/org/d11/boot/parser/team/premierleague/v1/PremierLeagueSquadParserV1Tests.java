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

        assertNotNull(parsedTeamData);
        assertNotNull(parsedTeamData.getSiteId());
        assertFalse(StringUtils.isBlank(parsedTeamData.getName()));

        assertNotNull(parsedTeamData.getPlayers());
        assertEquals(playerCount, parsedTeamData.getPlayers().size());

        for (final ParsedPlayerData playerData : parsedTeamData.getPlayers()) {
            assertNotNull(playerData.getSiteId());
            assertFalse(StringUtils.isBlank(playerData.getName()));
            assertNotNull(playerData.getShirtNumber());
            assertFalse(StringUtils.isBlank(playerData.getPosition()));
            assertFalse(StringUtils.isBlank(playerData.getNationality()));
        }
    }

}
