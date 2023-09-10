package org.d11.boot.parser.player.premierleague.v1;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.model.ParsedPlayerData;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests PremierLeaguePlayerParserV1.
 */
class PremierLeaguePlayerParserV1Tests {

    /**
     * Tests PremierLeaguePlayerParserV1::parse.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParse() throws IOException, ParserException {
        final PremierLeaguePlayerParserV1 premierLeaguePlayerParserV1 = new PremierLeaguePlayerParserV1();
        final File playerFile = new File("src/test/resources/premierleague/player/v1/player.html");
        final ParsedPlayerData parsedPlayerData = premierLeaguePlayerParserV1.parse(playerFile);

        assertNotNull(parsedPlayerData, "PremierLeaguePlayerParserV1::parse player not null");
        assertNotNull(parsedPlayerData.getSiteId(), "PremierLeaguePlayerParserV1::parse player site id not null");
        assertFalse(StringUtils.isBlank(parsedPlayerData.getName()),
                    "PremierLeaguePlayerParserV1::parse player name blank");
        assertNotNull(parsedPlayerData.getShirtNumber(),
                      "PremierLeaguePlayerParserV1::parse player shirt number not null");
        assertFalse(StringUtils.isBlank(parsedPlayerData.getPosition()),
                    "PremierLeaguePlayerParserV1::parse player position blank");
        assertFalse(StringUtils.isBlank(parsedPlayerData.getNationality()),
                    "PremierLeaguePlayerParserV1::parse player nationality blank");
        assertNotNull(parsedPlayerData.getDateOfBirth(),
                      "PremierLeaguePlayerParserV1::parse player date of birth not null");
        assertNotNull(parsedPlayerData.getHeight(), "PremierLeaguePlayerParserV1::parse player height not null");
        assertTrue(parsedPlayerData.getHeight() > 0, "PremierLeaguePlayerParserV1::parse player height positive");
    }

}
