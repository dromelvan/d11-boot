package org.d11.boot.parser.whoscored.v1;

import org.d11.boot.jms.model.MatchData;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.fixtures.whoscored.v1.WhoScoredFixturesParserV1;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for WhoScoredFixturesParserV1.
 */
public class WhoScoredFixturesParserV1Tests {

    /**
     * Tests parsing a WhoScored fixtures file.
     */
    @Test
    public void parseFixtures() throws IOException, ParserException {
        final int matchDataCount = 49;

        final WhoScoredFixturesParserV1 whoScoredFixturesParserV1 = new WhoScoredFixturesParserV1();
        final File pendingMatchFile = new File("src/test/resources/whoscored/v1/fixtures/2022-08.html");
        final List<MatchData> matchDatas = whoScoredFixturesParserV1.parse(pendingMatchFile);

        assertNotNull(matchDatas, "Match datas should not be null.");
        assertEquals(matchDataCount, matchDatas.size(),
                "Match datas should have " + matchDataCount + " match datas.");
    }

}

