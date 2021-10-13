package org.d11.boot.parser.whoscored.v1;

import org.d11.boot.jms.model.MatchData;
import org.d11.boot.jms.model.Status;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.match.whoscored.v1.WhoScoredMatchParserV1;
import org.d11.boot.parser.match.whoscored.v1.model.MatchHeader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for WhoScoredMatchParserV1.
 */
@SuppressWarnings("checkstyle:MagicNumber")
public class WhoScoredMatchParserV1Tests {

    /**
     * Tests parsing a pending WhoScored match file.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    public void parsePendingMatch() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File pendingMatchFile = new File("src/test/resources/whoscored/v1/match/pending.html");
        final MatchData matchData = whoScoredMatchParserV1.parse(pendingMatchFile);

        assertEquals(Long.valueOf(1_549_539), matchData.getWhoscoredId(),
                "Match data whoscoredId should equal pending file matchId.");

        assertEquals(MatchHeader.ELAPSED_PENDING, matchData.getElapsed(),
                "Match data elapsed should be N/A.");
        assertEquals(Status.PENDING, matchData.getStatus(), "Match data status should be pending.");

        assertNotNull(matchData.getHomeTeamWhoscoredId(), "Pending file match data home team should not be null.");
        assertEquals(Long.valueOf(189), matchData.getHomeTeamWhoscoredId(),
                "Match data home team id should equal pending file home team id.");

        assertNotNull(matchData.getAwayTeamWhoscoredId(), "Pending file match data away team should not be null.");
        assertEquals(Long.valueOf(13), matchData.getAwayTeamWhoscoredId(),
                "Match data away team id should equal pending file away team id.");

        assertEquals(LocalDateTime.of(2021, 8, 13, 22, 0, 0), matchData.getDatetime(),
                "Match data datetime should equal pending file datetime.");

        assertTrue(matchData.getGoals().isEmpty(),
                "Match data goals should not empty.");

        assertTrue(matchData.getPlayers().isEmpty(),
                "Match data player match stats should be empty.");
    }

    /**
     * Tests parsing a full time WhoScored match file.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    public void parseFullTimeMatch() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File finishedMatchFile = new File("src/test/resources/whoscored/v1/match/fullTime.html");
        final MatchData matchData = whoScoredMatchParserV1.parse(finishedMatchFile);

        assertEquals(Long.valueOf(1_485_563), matchData.getWhoscoredId(),
                "Match data whoscoredId should equal finished file matchId.");

        assertEquals(MatchHeader.ELAPSED_FULL_TIME, matchData.getElapsed(),
                "Match data elapsed should be FT.");
        assertEquals(Status.FULL_TIME, matchData.getStatus(), "Match data status should be full time.");

        assertNotNull(matchData.getHomeTeamWhoscoredId(), "Full time file match data home team should not be null.");
        assertEquals(Long.valueOf(161), matchData.getHomeTeamWhoscoredId(),
                "Match data home team id should equal finished file home team id.");

        assertNotNull(matchData.getAwayTeamWhoscoredId(), "Full time file match data away team should not be null.");
        assertEquals(Long.valueOf(32), matchData.getAwayTeamWhoscoredId(),
                "Match data away team id should equal finished file away team id.");

        assertEquals(LocalDateTime.of(2021, 5, 23, 18, 0, 0), matchData.getDatetime(),
                "Match data datetime should equal finished file datetime.");

        assertFalse(matchData.getGoals().isEmpty(),
                "Match data goals should not be empty.");

        assertFalse(matchData.getPlayers().isEmpty(),
                "Match data player match stats should not be empty.");
    }

}
