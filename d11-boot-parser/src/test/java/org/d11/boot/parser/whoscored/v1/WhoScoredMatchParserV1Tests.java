package org.d11.boot.parser.whoscored.v1;

import org.d11.boot.api.model.StatusDTO;
import org.d11.boot.api.model.UpdateMatchDTO;
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
        final UpdateMatchDTO updateMatchDTO = whoScoredMatchParserV1.parse(pendingMatchFile);

        assertEquals(Long.valueOf(1_549_539), updateMatchDTO.getWhoscoredId(),
                "Update match DTO whoscoredId should equal pending file matchId.");

        assertEquals(MatchHeader.ELAPSED_PENDING, updateMatchDTO.getElapsed(),
                "Update match DTO elapsed should be N/A.");
        assertEquals(StatusDTO.PENDING, updateMatchDTO.getStatus(), "Update match DTO status should be pending.");

        assertNotNull(updateMatchDTO.getHomeTeam(), "Pending file update match DTO home team should not be null.");
        assertEquals(Long.valueOf(189), updateMatchDTO.getHomeTeam().getWhoscoredId(),
                "Update match DTO home team id should equal pending file home team id.");

        assertNotNull(updateMatchDTO.getAwayTeam(), "Pending file update match DTO away team should not be null.");
        assertEquals(Long.valueOf(13), updateMatchDTO.getAwayTeam().getWhoscoredId(),
                "Update match DTO away team id should equal pending file away team id.");

        assertEquals(LocalDateTime.of(2021, 8, 13, 22, 0, 0), updateMatchDTO.getDatetime(),
                "Update match DTO datetime should equal pending file datetime.");

        assertTrue(updateMatchDTO.getGoals().isEmpty(),
                "Update match DTO goals should not empty.");

        assertTrue(updateMatchDTO.getPlayerMatchStats().isEmpty(),
                "Update match DTO player match stats should be empty.");
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
        final UpdateMatchDTO updateMatchDTO = whoScoredMatchParserV1.parse(finishedMatchFile);

        assertEquals(Long.valueOf(1_485_563), updateMatchDTO.getWhoscoredId(),
                "Update match DTO whoscoredId should equal finished file matchId.");

        assertEquals(MatchHeader.ELAPSED_FULL_TIME, updateMatchDTO.getElapsed(),
                "Update match DTO elapsed should be FT.");
        assertEquals(StatusDTO.FULL_TIME, updateMatchDTO.getStatus(), "Update match DTO status should be full time.");

        assertNotNull(updateMatchDTO.getHomeTeam(), "Full time file update match DTO home team should not be null.");
        assertEquals(Long.valueOf(161), updateMatchDTO.getHomeTeam().getWhoscoredId(),
                "Update match DTO home team id should equal finished file home team id.");

        assertNotNull(updateMatchDTO.getAwayTeam(), "Full time file update match DTO away team should not be null.");
        assertEquals(Long.valueOf(32), updateMatchDTO.getAwayTeam().getWhoscoredId(),
                "Update match DTO away team id should equal finished file away team id.");

        assertEquals(LocalDateTime.of(2021, 5, 23, 18, 0, 0), updateMatchDTO.getDatetime(),
                "Update match DTO datetime should equal finished file datetime.");

        assertFalse(updateMatchDTO.getGoals().isEmpty(),
                "Update match DTO goals should not be empty.");

        assertFalse(updateMatchDTO.getPlayerMatchStats().isEmpty(),
                "Update match DTO player match stats should not be empty.");
    }

}
