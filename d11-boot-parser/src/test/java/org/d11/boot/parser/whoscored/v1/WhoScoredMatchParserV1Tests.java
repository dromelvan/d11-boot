package org.d11.boot.parser.whoscored.v1;

import org.d11.boot.jms.model.MatchData;
import org.d11.boot.jms.model.PlayerMatchData;
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
@SuppressWarnings({ "PMD.AvoidDuplicateLiterals", "checkstyle:MagicNumber", "checkstyle:MultipleStringLiterals" })
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
                "Pending match data goals should be empty.");

        assertTrue(matchData.getPlayers().isEmpty(),
                "Pending match data player match stats should be empty.");
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
                "Match data whoscoredId should equal full time file matchId.");

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
                "Full time match data goals should not be empty.");

        assertFalse(matchData.getPlayers().isEmpty(),
                "Full time match data player match stats should not be empty.");

        for(final PlayerMatchData playerMatchData : matchData.getPlayers()) {
            if(playerMatchData.getTeamWhoscoredId() == matchData.getHomeTeamWhoscoredId().intValue()) {
                assertEquals(2, (int) playerMatchData.getGoalsConceded(),
                             "Player match data home team goals conceded should equal");
            } else {
                assertEquals(1, (int) playerMatchData.getGoalsConceded(),
                             "Player match data away team goals conceded should equal");
            }
        }
    }

    /**
     * Tests parsing a "green" full time WhoScored match file.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    public void parse90PlusMatch() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File finishedMatchFile = new File("src/test/resources/whoscored/v1/match/90+.html");
        final MatchData matchData = whoScoredMatchParserV1.parse(finishedMatchFile);

        assertEquals(Long.valueOf(1_549_638), matchData.getWhoscoredId(),
                "Match data whoscoredId should equal 90+ file matchId.");

        assertEquals(MatchHeader.ELAPSED_90_PLUS_TIME, matchData.getElapsed(),
                "Match data elapsed should be 90+.");
        assertEquals(Status.ACTIVE, matchData.getStatus(), "Match data status should be active.");

        assertNotNull(matchData.getHomeTeamWhoscoredId(), "90+ file match data home team should not be null.");
        assertEquals(Long.valueOf(161), matchData.getHomeTeamWhoscoredId(),
                "Match data home team id should equal 90+ file home team id.");

        assertNotNull(matchData.getAwayTeamWhoscoredId(), "90+ file match data away team should not be null.");
        assertEquals(Long.valueOf(31), matchData.getAwayTeamWhoscoredId(),
                "Match data away team id should equal 90+ file away team id.");

        assertEquals(LocalDateTime.of(2021, 11, 01, 22, 0, 0), matchData.getDatetime(),
                "Match data datetime should equal 90+ file datetime.");

        assertFalse(matchData.getGoals().isEmpty(),
                "90+ match data goals should not be empty.");

        assertFalse(matchData.getPlayers().isEmpty(),
                "90+ match data player match stats should not be empty.");

        for(final PlayerMatchData playerMatchData : matchData.getPlayers()) {
            if(playerMatchData.getTeamWhoscoredId() == matchData.getHomeTeamWhoscoredId().intValue()) {
                assertEquals(1, (int) playerMatchData.getGoalsConceded(),
                             "Player match data home team goals conceded should equal");
            } else {
                assertEquals(2, (int) playerMatchData.getGoalsConceded(),
                             "Player match data away team goals conceded should equal");
            }
        }
    }

    /**
     * Tests parsing another version of "green" full time WhoScored match file.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    public void parse90PlusMatch2() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File finishedMatchFile = new File("src/test/resources/whoscored/v1/match/90+2.html");
        final MatchData matchData = whoScoredMatchParserV1.parse(finishedMatchFile);

        assertEquals(Long.valueOf(1_549_719), matchData.getWhoscoredId(),
                "Match data whoscoredId should equal 90+2 file matchId.");

        assertEquals(MatchHeader.ELAPSED_90_PLUS_TIME, matchData.getElapsed(),
                "Match data elapsed should be 90+.");
        assertEquals(Status.ACTIVE, matchData.getStatus(), "Match data status should be active.");

        assertNotNull(matchData.getHomeTeamWhoscoredId(), "90+ file match data home team should not be null.");
        assertEquals(Long.valueOf(24), matchData.getHomeTeamWhoscoredId(),
                "Match data home team id should equal 90+ file home team id.");

        assertNotNull(matchData.getAwayTeamWhoscoredId(), "90+ file match data away team should not be null.");
        assertEquals(Long.valueOf(15), matchData.getAwayTeamWhoscoredId(),
                "Match data away team id should equal 90+ file away team id.");

        assertEquals(LocalDateTime.of(2021, 12, 26, 19, 30, 0), matchData.getDatetime(),
                "Match data datetime should equal 90+ file datetime.");

        assertFalse(matchData.getGoals().isEmpty(),
                "90+2 match data goals should not be empty.");

        assertFalse(matchData.getPlayers().isEmpty(),
                "90+2 match data player match stats should not be empty.");

        for(final PlayerMatchData playerMatchData : matchData.getPlayers()) {
            if(playerMatchData.getTeamWhoscoredId() == matchData.getHomeTeamWhoscoredId().intValue()) {
                assertEquals(3, (int) playerMatchData.getGoalsConceded(),
                             "Player match data home team goals conceded should equal");
            } else {
                assertEquals(1, (int) playerMatchData.getGoalsConceded(),
                             "Player match data away team goals conceded should equal");
            }
        }
    }

    /**
     * Tests parsing yet another version of "green" full time WhoScored match file.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    public void parse90PlusMatch3() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File finishedMatchFile = new File("src/test/resources/whoscored/v1/match/90+3.html");
        final MatchData matchData = whoScoredMatchParserV1.parse(finishedMatchFile);

        assertEquals(Long.valueOf(1_729_295), matchData.getWhoscoredId(),
                     "Match data whoscoredId should equal 90+2 file matchId.");

        assertEquals(MatchHeader.ELAPSED_90_PLUS_TIME, matchData.getElapsed(),
                     "Match data elapsed should be 90+.");
        assertEquals(Status.ACTIVE, matchData.getStatus(), "Match data status should be active.");

        assertNotNull(matchData.getHomeTeamWhoscoredId(), "90+ file match data home team should not be null.");
        assertEquals(Long.valueOf(183), matchData.getHomeTeamWhoscoredId(),
                     "Match data home team id should equal 90+ file home team id.");

        assertNotNull(matchData.getAwayTeamWhoscoredId(), "90+ file match data away team should not be null.");
        assertEquals(Long.valueOf(13), matchData.getAwayTeamWhoscoredId(),
                     "Match data away team id should equal 90+ file away team id.");

        assertEquals(LocalDateTime.of(2023, 9, 30, 17, 0, 0), matchData.getDatetime(),
                     "Match data datetime should equal 90+ file datetime.");

        assertFalse(matchData.getGoals().isEmpty(),
                    "90+3 match data goals should not be empty.");

        assertFalse(matchData.getPlayers().isEmpty(),
                    "90+3 match data player match stats should not be empty.");

        for(final PlayerMatchData playerMatchData : matchData.getPlayers()) {
            if(playerMatchData.getTeamWhoscoredId() == matchData.getHomeTeamWhoscoredId().intValue()) {
                assertEquals(4, (int) playerMatchData.getGoalsConceded(),
                             "Player match data home team goals conceded should equal");
            } else {
                assertEquals(0, (int) playerMatchData.getGoalsConceded(),
                             "Player match data away team goals conceded should equal");
            }
        }
    }

    /**
     * Tests parsing a postponed match.
     *
     * @throws IOException     If something goes wrong.
     * @throws ParserException If something goes wrong.
     */
    @Test
    public void parsePostponedMatch() throws IOException, ParserException {
        final WhoScoredMatchParserV1 whoScoredMatchParserV1 = new WhoScoredMatchParserV1();
        final File finishedMatchFile = new File("src/test/resources/whoscored/v1/match/postponed.html");
        final MatchData matchData = whoScoredMatchParserV1.parse(finishedMatchFile);

        assertEquals(Long.valueOf(1_549_728), matchData.getWhoscoredId(),
                "Match data whoscoredId should equal postponed file matchId.");

        assertEquals(MatchHeader.ELAPSED_POSTPONED, matchData.getElapsed(),
                "Match data elapsed should be PP.");
        assertEquals(Status.POSTPONED, matchData.getStatus(), "Match data status should be postponed.");

        assertNotNull(matchData.getHomeTeamWhoscoredId(), "Postponed file match data home team should not be null.");
        assertEquals(Long.valueOf(161), matchData.getHomeTeamWhoscoredId(),
                "Match data home team id should equal postponed file home team id.");

        assertNotNull(matchData.getAwayTeamWhoscoredId(), "Postponed file match data away team should not be null.");
        assertEquals(Long.valueOf(27), matchData.getAwayTeamWhoscoredId(),
                "Match data away team id should equal postponed file away team id.");

        assertEquals(LocalDateTime.of(2021, 12, 26, 14, 30, 0), matchData.getDatetime(),
                "Match data datetime should equal postponed file datetime.");

        assertTrue(matchData.getGoals().isEmpty(),
                "Postponed match data goals should be empty.");

        assertTrue(matchData.getPlayers().isEmpty(),
                "Postponed match data player match stats should be empty.");
    }

}
