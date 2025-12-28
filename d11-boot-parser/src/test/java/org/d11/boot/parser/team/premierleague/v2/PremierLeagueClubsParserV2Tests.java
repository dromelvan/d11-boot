package org.d11.boot.parser.team.premierleague.v2;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.model.ParsedTeamData;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests PremierLeagueClubsParserV20.
 */
class PremierLeagueClubsParserV2Tests {

    /**
     * Tests PremierLeagueClubsParserV2::parse.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParse() throws IOException, ParserException {
        final int teamCount = 20;
        final PremierLeagueClubsParserV2 premierLeagueClubsParserV2 = new PremierLeagueClubsParserV2();
        final File clubsFile = new File("src/test/resources/premierleague/clubs/v2/clubs.html");
        final List<ParsedTeamData> parsedTeamDatas = premierLeagueClubsParserV2.parse(clubsFile);

        assertEquals(teamCount, parsedTeamDatas.size());

        for (final ParsedTeamData parsedTeamData : parsedTeamDatas) {
            assertNotNull(parsedTeamData.getSiteId());
            assertFalse(StringUtils.isBlank(parsedTeamData.getName()));
        }
    }

}
