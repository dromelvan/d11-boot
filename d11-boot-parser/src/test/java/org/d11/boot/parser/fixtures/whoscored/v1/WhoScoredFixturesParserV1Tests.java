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

        assertNotNull(parsedMatchDatas);
        assertEquals(matchDataCount, parsedMatchDatas.size());

        for (final ParsedMatchData parsedMatchData : parsedMatchDatas) {
            assertNotNull(parsedMatchData.getSiteId());
            assertTrue(parsedMatchData.getSiteId() > 0);

            assertNotNull(parsedMatchData.getHomeTeamSiteId());
            assertTrue(parsedMatchData.getHomeTeamSiteId() > 0);

            assertTrue(StringUtils.isNotBlank(parsedMatchData.getHomeTeamName()));

            assertNotNull(parsedMatchData.getAwayTeamSiteId());
            assertTrue(parsedMatchData.getAwayTeamSiteId() > 0);

            assertTrue(StringUtils.isNotBlank(parsedMatchData.getAwayTeamName()));

            assertNotEquals(parsedMatchData.getHomeTeamSiteId(), parsedMatchData.getAwayTeamSiteId());
            assertNotEquals(parsedMatchData.getHomeTeamName(), parsedMatchData.getAwayTeamName());

            assertNotNull(parsedMatchData.getDatetime());
        }
    }

}

