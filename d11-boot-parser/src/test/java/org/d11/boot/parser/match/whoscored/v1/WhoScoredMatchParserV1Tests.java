package org.d11.boot.parser.match.whoscored.v1;

import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.match.whoscored.v1.model.MatchHeader;
import org.d11.boot.parser.model.ParsedMatchData;
import org.d11.boot.parser.model.ParsedPlayerMatchData;
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

        assertEquals(Long.valueOf(1_549_539), parsedMatchData.getSiteId());

        assertEquals(MatchHeader.ELAPSED_PENDING, parsedMatchData.getElapsed());
        assertEquals(Status.PENDING, parsedMatchData.getStatus());

        assertNotNull(parsedMatchData.getHomeTeamSiteId());
        assertEquals(Long.valueOf(189), parsedMatchData.getHomeTeamSiteId());

        assertNotNull(parsedMatchData.getAwayTeamSiteId());
        assertEquals(Long.valueOf(13), parsedMatchData.getAwayTeamSiteId());

        assertEquals(LocalDateTime.of(2021, 8, 13, 22, 0, 0), parsedMatchData.getDatetime());

        assertTrue(parsedMatchData.getGoals().isEmpty());

        assertTrue(parsedMatchData.getPlayers().isEmpty());
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

        assertEquals(Long.valueOf(1_485_563), parsedMatchData.getSiteId());

        assertEquals(MatchHeader.ELAPSED_FULL_TIME, parsedMatchData.getElapsed());
        assertEquals(Status.FULL_TIME, parsedMatchData.getStatus());

        assertNotNull(parsedMatchData.getHomeTeamSiteId());
        assertEquals(Long.valueOf(161), parsedMatchData.getHomeTeamSiteId());

        assertNotNull(parsedMatchData.getAwayTeamSiteId());
        assertEquals(Long.valueOf(32), parsedMatchData.getAwayTeamSiteId());

        assertEquals(LocalDateTime.of(2021, 5, 23, 18, 0, 0), parsedMatchData.getDatetime());

        assertFalse(parsedMatchData.getGoals().isEmpty());

        assertFalse(parsedMatchData.getPlayers().isEmpty());
        assertEquals(602, parsedMatchData.getPlayers().get(0).getRating());

        for (final ParsedPlayerMatchData playerMatchData : parsedMatchData.getPlayers()) {
            if (playerMatchData.getTeamSiteId() == parsedMatchData.getHomeTeamSiteId().intValue()) {
                assertEquals(2, (int) playerMatchData.getGoalsConceded());
            } else {
                assertEquals(1, (int) playerMatchData.getGoalsConceded());
            }
        }
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

        assertEquals(Long.valueOf(1_549_638), parsedMatchData.getSiteId());

        assertEquals(MatchHeader.ELAPSED_90_PLUS_TIME, parsedMatchData.getElapsed());
        assertEquals(Status.ACTIVE, parsedMatchData.getStatus());

        assertNotNull(parsedMatchData.getHomeTeamSiteId());
        assertEquals(Long.valueOf(161), parsedMatchData.getHomeTeamSiteId());

        assertNotNull(parsedMatchData.getAwayTeamSiteId());
        assertEquals(Long.valueOf(31), parsedMatchData.getAwayTeamSiteId());

        assertEquals(LocalDateTime.of(2021, 11, 1, 22, 0, 0), parsedMatchData.getDatetime());

        assertFalse(parsedMatchData.getGoals().isEmpty());

        assertFalse(parsedMatchData.getPlayers().isEmpty());
        assertEquals(670, parsedMatchData.getPlayers().get(0).getRating());

        for (final ParsedPlayerMatchData playerMatchData : parsedMatchData.getPlayers()) {
            if (playerMatchData.getTeamSiteId() == parsedMatchData.getHomeTeamSiteId().intValue()) {
                assertEquals(1, (int) playerMatchData.getGoalsConceded());
            } else {
                assertEquals(2, (int) playerMatchData.getGoalsConceded());
            }
        }
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

        assertEquals(Long.valueOf(1_549_719), parsedMatchData.getSiteId());

        assertEquals(MatchHeader.ELAPSED_90_PLUS_TIME, parsedMatchData.getElapsed());
        assertEquals(Status.ACTIVE, parsedMatchData.getStatus());

        assertNotNull(parsedMatchData.getHomeTeamSiteId());
        assertEquals(Long.valueOf(24), parsedMatchData.getHomeTeamSiteId());

        assertNotNull(parsedMatchData.getAwayTeamSiteId());
        assertEquals(Long.valueOf(15), parsedMatchData.getAwayTeamSiteId());

        assertEquals(LocalDateTime.of(2021, 12, 26, 19, 30, 0), parsedMatchData.getDatetime());

        assertFalse(parsedMatchData.getGoals().isEmpty());

        assertFalse(parsedMatchData.getPlayers().isEmpty());
        assertEquals(546, parsedMatchData.getPlayers().get(0).getRating());

        for (final ParsedPlayerMatchData playerMatchData : parsedMatchData.getPlayers()) {
            if (playerMatchData.getTeamSiteId() == parsedMatchData.getHomeTeamSiteId().intValue()) {
                assertEquals(3, (int) playerMatchData.getGoalsConceded());
            } else {
                assertEquals(1, (int) playerMatchData.getGoalsConceded());
            }
        }
    }

    /**
     * Tests WhoScoredMatchParserV1::parse another version of "green" full time match.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    void testParse90Plus3() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File file = new File("src/test/resources/whoscored/match/v1/90+3.html");
        final ParsedMatchData parsedMatchData = whoScoredMatchParserV1.parse(file);

        assertEquals(Long.valueOf(1_729_295), parsedMatchData.getSiteId());

        assertEquals(MatchHeader.ELAPSED_90_PLUS_TIME, parsedMatchData.getElapsed());
        assertEquals(Status.ACTIVE, parsedMatchData.getStatus());

        assertNotNull(parsedMatchData.getHomeTeamSiteId());
        assertEquals(Long.valueOf(183), parsedMatchData.getHomeTeamSiteId());

        assertNotNull(parsedMatchData.getAwayTeamSiteId());
        assertEquals(Long.valueOf(13), parsedMatchData.getAwayTeamSiteId());

        assertEquals(LocalDateTime.of(2023, 9, 30, 17, 0, 0), parsedMatchData.getDatetime());

        assertFalse(parsedMatchData.getGoals().isEmpty());

        assertFalse(parsedMatchData.getPlayers().isEmpty());
        assertEquals(656, parsedMatchData.getPlayers().get(0).getRating());

        for (final ParsedPlayerMatchData playerMatchData : parsedMatchData.getPlayers()) {
            if (playerMatchData.getTeamSiteId() == parsedMatchData.getHomeTeamSiteId().intValue()) {
                assertEquals(4, (int) playerMatchData.getGoalsConceded());
            } else {
                assertEquals(0, (int) playerMatchData.getGoalsConceded());
            }
        }
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

        assertEquals(Long.valueOf(1_549_728), parsedMatchData.getSiteId());

        assertEquals(MatchHeader.ELAPSED_POSTPONED, parsedMatchData.getElapsed());
        assertEquals(Status.POSTPONED, parsedMatchData.getStatus());

        assertNotNull(parsedMatchData.getHomeTeamSiteId());
        assertEquals(Long.valueOf(161), parsedMatchData.getHomeTeamSiteId());

        assertNotNull(parsedMatchData.getAwayTeamSiteId());
        assertEquals(Long.valueOf(27), parsedMatchData.getAwayTeamSiteId());

        assertEquals(LocalDateTime.of(2021, 12, 26, 14, 30, 0), parsedMatchData.getDatetime());

        assertTrue(parsedMatchData.getGoals().isEmpty());

        assertTrue(parsedMatchData.getPlayers().isEmpty());
    }

}
