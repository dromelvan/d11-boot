package org.d11.boot.parser.match.whoscored.v1;

import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.match.whoscored.v1.model.MatchHeader;
import org.d11.boot.parser.model.ParsedMatchData;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests WhoScoredMatchParserV1.
 */
@SuppressWarnings("checkstyle:MagicNumber")
class WhoScoredMatchParserV1Tests {

    /**
     * Tests WhoScoredMatchParserV1::parse pending match.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParsePending() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File file = new File("src/test/resources/whoscored/match/v1/pending.html");
        final ParsedMatchData parsedMatchData = whoScoredMatchParserV1.parse(file);

        assertEquals(Long.valueOf(1_549_539), parsedMatchData.getSiteId(),
                     "WhoScoredMatchParserV1::parse pending site id equals");

        assertEquals(MatchHeader.ELAPSED_PENDING, parsedMatchData.getElapsed(),
                     "WhoScoredMatchParserV1::parse pending elapsed equals");
        assertEquals(Status.PENDING, parsedMatchData.getStatus(),
                     "WhoScoredMatchParserV1::parse pending status equals");

        assertNotNull(parsedMatchData.getHomeTeamSiteId(),
                      "WhoScoredMatchParserV1::parse pending home team site id not null");
        assertEquals(Long.valueOf(189), parsedMatchData.getHomeTeamSiteId(),
                     "WhoScoredMatchParserV1::parse pending home team site id equals");

        assertNotNull(parsedMatchData.getAwayTeamSiteId(),
                      "WhoScoredMatchParserV1::parse pending away team site id not null");
        assertEquals(Long.valueOf(13), parsedMatchData.getAwayTeamSiteId(),
                     "WhoScoredMatchParserV1::parse pending away team site id equals");

        assertEquals(LocalDateTime.of(2021, 8, 13, 22, 0, 0), parsedMatchData.getDatetime(),
                     "WhoScoredMatchParserV1::parse pending datetime equals");

        assertTrue(parsedMatchData.getGoals().isEmpty(),
                   "WhoScoredMatchParserV1::parse pending goals empty");

        assertTrue(parsedMatchData.getPlayers().isEmpty(),
                   "WhoScoredMatchParserV1::parse pending players empty");
    }

    /**
     * Tests WhoScoredMatchParserV1::parse full time match.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParseFullTime() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File file = new File("src/test/resources/whoscored/match/v1/fullTime.html");
        final ParsedMatchData parsedMatchData = whoScoredMatchParserV1.parse(file);

        assertEquals(Long.valueOf(1_485_563), parsedMatchData.getSiteId(),
                     "WhoScoredMatchParserV1::parse full time site id equals");

        assertEquals(MatchHeader.ELAPSED_FULL_TIME, parsedMatchData.getElapsed(),
                     "WhoScoredMatchParserV1::parse full time elapsed equals");
        assertEquals(Status.FULL_TIME, parsedMatchData.getStatus(),
                     "WhoScoredMatchParserV1::parse full time status equals");

        assertNotNull(parsedMatchData.getHomeTeamSiteId(),
                      "WhoScoredMatchParserV1::parse full time home team site id not null");
        assertEquals(Long.valueOf(161), parsedMatchData.getHomeTeamSiteId(),
                     "WhoScoredMatchParserV1::parse full time home team site id equals");

        assertNotNull(parsedMatchData.getAwayTeamSiteId(),
                      "WhoScoredMatchParserV1::parse full time away team site id not null");
        assertEquals(Long.valueOf(32), parsedMatchData.getAwayTeamSiteId(),
                     "WhoScoredMatchParserV1::parse full time away team site id equals");

        assertEquals(LocalDateTime.of(2021, 5, 23, 18, 0, 0), parsedMatchData.getDatetime(),
                     "WhoScoredMatchParserV1::parse full time datetime equals");

        assertFalse(parsedMatchData.getGoals().isEmpty(), "WhoScoredMatchParserV1::parse full time goals empty");

        assertFalse(parsedMatchData.getPlayers().isEmpty(), "WhoScoredMatchParserV1::parse full time players empty");
        assertEquals(602, parsedMatchData.getPlayers().get(0).getRating(),
                     "WhoScoredMatchParserV1::parse full time players rating");
    }

    /**
     * Tests WhoScoredMatchParserV1::parse "green" full time match.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParse90Plus() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File file = new File("src/test/resources/whoscored/match/v1/90+.html");
        final ParsedMatchData parsedMatchData = whoScoredMatchParserV1.parse(file);

        assertEquals(Long.valueOf(1_549_638), parsedMatchData.getSiteId(),
                     "WhoScoredMatchParserV1::parse 90+ site id equals");

        assertEquals(MatchHeader.ELAPSED_90_PLUS_TIME, parsedMatchData.getElapsed(),
                     "WhoScoredMatchParserV1::parse 90+ elapsed equals");
        assertEquals(Status.ACTIVE, parsedMatchData.getStatus(), "WhoScoredMatchParserV1::parse 90+ status equals");

        assertNotNull(parsedMatchData.getHomeTeamSiteId(),
                      "WhoScoredMatchParserV1::parse 90+ home team site id not null");
        assertEquals(Long.valueOf(161), parsedMatchData.getHomeTeamSiteId(),
                     "WhoScoredMatchParserV1::parse 90+ home team site id equals");

        assertNotNull(parsedMatchData.getAwayTeamSiteId(),
                      "WhoScoredMatchParserV1::parse 90+ away team site id not null");
        assertEquals(Long.valueOf(31), parsedMatchData.getAwayTeamSiteId(),
                     "WhoScoredMatchParserV1::parse 90+ away team site id equals");

        assertEquals(LocalDateTime.of(2021, 11, 1, 22, 0, 0), parsedMatchData.getDatetime(),
                     "WhoScoredMatchParserV1::parse 90+ datetime equals");

        assertFalse(parsedMatchData.getGoals().isEmpty(), "WhoScoredMatchParserV1::parse 90+ goals empty");

        assertFalse(parsedMatchData.getPlayers().isEmpty(), "WhoScoredMatchParserV1::parse 90+ players empty");
        assertEquals(670, parsedMatchData.getPlayers().get(0).getRating(),
                     "WhoScoredMatchParserV1::parse 90+ players rating");
    }

    /**
     * Tests WhoScoredMatchParserV1::parse another version of "green" full time match.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParse90Plus2() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File file = new File("src/test/resources/whoscored/match/v1/90+2.html");
        final ParsedMatchData parsedMatchData = whoScoredMatchParserV1.parse(file);

        assertEquals(Long.valueOf(1_549_719), parsedMatchData.getSiteId(),
                     "WhoScoredMatchParserV1::parse 90+2 site id equals");

        assertEquals(MatchHeader.ELAPSED_90_PLUS_TIME, parsedMatchData.getElapsed(),
                     "WhoScoredMatchParserV1::parse 90+2 elapsed equals");
        assertEquals(Status.ACTIVE, parsedMatchData.getStatus(), "WhoScoredMatchParserV1::parse 90+2 status equals");

        assertNotNull(parsedMatchData.getHomeTeamSiteId(),
                      "WhoScoredMatchParserV1::parse 90+2 home team site id not null");
        assertEquals(Long.valueOf(24), parsedMatchData.getHomeTeamSiteId(),
                     "WhoScoredMatchParserV1::parse 90+2 home team site id equals");

        assertNotNull(parsedMatchData.getAwayTeamSiteId(),
                      "WhoScoredMatchParserV1::parse 90+2 away team site id not null");
        assertEquals(Long.valueOf(15), parsedMatchData.getAwayTeamSiteId(),
                     "WhoScoredMatchParserV1::parse 90+2 away team site id equals");

        assertEquals(LocalDateTime.of(2021, 12, 26, 19, 30, 0), parsedMatchData.getDatetime(),
                     "WhoScoredMatchParserV1::parse 90+2 datetime equals");

        assertFalse(parsedMatchData.getGoals().isEmpty(), "WhoScoredMatchParserV1::parse 90+2 goals empty");

        assertFalse(parsedMatchData.getPlayers().isEmpty(), "WhoScoredMatchParserV1::parse 90+2 players empty");
        assertEquals(546, parsedMatchData.getPlayers().get(0).getRating(),
                     "WhoScoredMatchParserV1::parse 90+2 players rating");
    }

    /**
     * Tests WhoScoredMatchParserV1::parse postponed match.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void parsePostponedMatch() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File file = new File("src/test/resources/whoscored/match/v1/postponed.html");
        final ParsedMatchData parsedMatchData = whoScoredMatchParserV1.parse(file);

        assertEquals(Long.valueOf(1_549_728), parsedMatchData.getSiteId(),
                     "WhoScoredMatchParserV1::parse postponed site id equals");

        assertEquals(MatchHeader.ELAPSED_POSTPONED, parsedMatchData.getElapsed(),
                     "WhoScoredMatchParserV1::parse postponed elapsed equals");
        assertEquals(Status.POSTPONED, parsedMatchData.getStatus(),
                     "WhoScoredMatchParserV1::parse postponed status equals");

        assertNotNull(parsedMatchData.getHomeTeamSiteId(),
                      "WhoScoredMatchParserV1::parse postponed home team site id not null");
        assertEquals(Long.valueOf(161), parsedMatchData.getHomeTeamSiteId(),
                     "WhoScoredMatchParserV1::parse postponed home team site id equals");

        assertNotNull(parsedMatchData.getAwayTeamSiteId(),
                      "WhoScoredMatchParserV1::parse postponed away team site id not null");
        assertEquals(Long.valueOf(27), parsedMatchData.getAwayTeamSiteId(),
                     "WhoScoredMatchParserV1::parse postponed away team site id equals");

        assertEquals(LocalDateTime.of(2021, 12, 26, 14, 30, 0), parsedMatchData.getDatetime(),
                     "WhoScoredMatchParserV1::parse postponed datetime equals");

        assertTrue(parsedMatchData.getGoals().isEmpty(), "WhoScoredMatchParserV1::parse postponed goals empty");

        assertTrue(parsedMatchData.getPlayers().isEmpty(), "WhoScoredMatchParserV1::parse postponed players empty");
    }

}
