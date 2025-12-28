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

        assertNotNull(parsedPlayerData);
        assertNotNull(parsedPlayerData.getSiteId());
        assertFalse(StringUtils.isBlank(parsedPlayerData.getName()));
        assertNotNull(parsedPlayerData.getShirtNumber());
        assertFalse(StringUtils.isBlank(parsedPlayerData.getPosition()));
        assertFalse(StringUtils.isBlank(parsedPlayerData.getNationality()));
        assertNotNull(parsedPlayerData.getDateOfBirth());
        assertNotNull(parsedPlayerData.getHeight());
        assertTrue(parsedPlayerData.getHeight() > 0);
    }

}
