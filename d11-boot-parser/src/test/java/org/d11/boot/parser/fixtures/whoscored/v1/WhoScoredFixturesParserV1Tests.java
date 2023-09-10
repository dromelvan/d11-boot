package org.d11.boot.parser.fixtures.whoscored.v1;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.model.ParsedMatchData;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Tests WhoScoredFixturesParserV1.
 */
class WhoScoredFixturesParserV1Tests {

    /**
     * Tests WhoScoredFixturesParserV1::parse.
     */
    @Test
    void testParse() throws IOException, ParserException {
        final int matchDataCount = 49;

        final WhoScoredFixturesParserV1 whoScoredFixturesParserV1 = new WhoScoredFixturesParserV1();
        final File pendingMatchFile = new File("src/test/resources/whoscored/fixtures/v1/2022-08.html");
        final List<ParsedMatchData> parsedMatchDatas = whoScoredFixturesParserV1.parse(pendingMatchFile);

        assertNotNull(parsedMatchDatas, "WhoScoredFixturesParserV1::parse not null.");
        assertEquals(matchDataCount, parsedMatchDatas.size(), "WhoScoredFixturesParserV1::parse size");

        for (final ParsedMatchData parsedMatchData : parsedMatchDatas) {
            assertNotNull(parsedMatchData.getSiteId(), "WhoScoredFixturesParserV1::parse site id not null");
            assertTrue(parsedMatchData.getSiteId() > 0, "WhoScoredFixturesParserV1::parse site id positive");

            assertNotNull(parsedMatchData.getHomeTeamSiteId(),
                          "WhoScoredFixturesParserV1::parse home team site id not null");
            assertTrue(parsedMatchData.getHomeTeamSiteId() > 0,
                       "WhoScoredFixturesParserV1::parse home team site id positive");

            assertTrue(StringUtils.isNotBlank(parsedMatchData.getHomeTeamName()),
                       "WhoScoredFixturesParserV1::parse home team name blank");

            assertNotNull(parsedMatchData.getAwayTeamSiteId(),
                          "WhoScoredFixturesParserV1::parse away team site id not null");
            assertTrue(parsedMatchData.getAwayTeamSiteId() > 0,
                       "WhoScoredFixturesParserV1::parse away team site id positive");

            assertTrue(StringUtils.isNotBlank(parsedMatchData.getAwayTeamName()),
                       "WhoScoredFixturesParserV1::parse away team name blank");

            assertNotEquals(parsedMatchData.getHomeTeamSiteId(), parsedMatchData.getAwayTeamSiteId(),
                            "WhoScoredFixturesParserV1::parse home team site id and away team site id not equals");
            assertNotEquals(parsedMatchData.getHomeTeamName(), parsedMatchData.getAwayTeamName(),
                            "WhoScoredFixturesParserV1::parse home team name and away team name not equals");

            assertNotNull(parsedMatchData.getDatetime(), "WhoScoredFixturesParserV1::parse datetime not null");
        }
    }

}

