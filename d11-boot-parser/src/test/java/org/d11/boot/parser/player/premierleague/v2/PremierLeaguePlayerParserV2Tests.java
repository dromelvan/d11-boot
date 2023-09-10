package org.d11.boot.parser.player.premierleague.v2;

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
 * Tests PremierLeaguePlayerParserV2.
 */
class PremierLeaguePlayerParserV2Tests {

    /**
     * Tests PremierLeaguePlayerParserV2::parse.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParse() throws IOException, ParserException {
        final PremierLeaguePlayerParserV2 premierLeaguePlayerParserV2 = new PremierLeaguePlayerParserV2();
        final File playerFile = new File("src/test/resources/premierleague/player/v2/player.html");
        final ParsedPlayerData parsedPlayerData = premierLeaguePlayerParserV2.parse(playerFile);

        assertNotNull(parsedPlayerData, "PremierLeaguePlayerParserV2::parse player not null");
        assertNotNull(parsedPlayerData.getSiteId(), "PremierLeaguePlayerParserV2::parse player site id not null");
        assertFalse(StringUtils.isBlank(parsedPlayerData.getName()),
                    "PremierLeaguePlayerParserV2::parse player name blank");
        assertNotNull(parsedPlayerData.getShirtNumber(),
                      "PremierLeaguePlayerParserV2::parse player shirt number not null");
        assertFalse(StringUtils.isBlank(parsedPlayerData.getPosition()),
                    "PremierLeaguePlayerParserV2::parse player position blank");
        assertFalse(StringUtils.isBlank(parsedPlayerData.getNationality()),
                    "PremierLeaguePlayerParserV2::parse player nationality blank");
        assertNotNull(parsedPlayerData.getDateOfBirth(),
                      "PremierLeaguePlayerParserV2::parse player date of birth not null");
        assertNotNull(parsedPlayerData.getHeight(), "PremierLeaguePlayerParserV2::parse player height not null");
        assertTrue(parsedPlayerData.getHeight() > 0, "PremierLeaguePlayerParserV2::parse player height positive");
    }

}
