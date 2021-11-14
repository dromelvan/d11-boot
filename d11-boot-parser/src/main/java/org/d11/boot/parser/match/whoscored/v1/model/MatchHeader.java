package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;
import org.d11.boot.jms.model.MatchData;
import org.d11.boot.jms.model.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Represents a match header in a WhoScored match page script element.
 * <p>
 * Example pending match:
 * <p>
 * require.config.params['matchheader'] = {
 * input: [189,13,'Brentford','Arsenal','13/08/2021 20:00:00','13/08/2021 00:00:00',1,,,,,,'vs','England','England'],
 * matchId: 1549539
 * };
 * <p>
 * Example finished match:
 * <p>
 * require.config.params['matchheader'] = {
 * input: [189,13,'Brentford','Arsenal','13/08/2021 20:00:00','13/08/2021 00:00:00',6,'FT','1 : 2','1 : 2',,,'1 : 2','England','England'],
 * matchId: 1549539
 * };
 */
@Data
public class MatchHeader {

    /**
     * Pattern for script element containing match header data.
     */
    public static final Pattern MATCH_HEADER_PATTERN = Pattern.compile(".*require.config.params\\['matchheader'] = (\\{.*});.*", Pattern.DOTALL);

    /**
     * String for pending match elapsed time.
     */
    public static final String ELAPSED_PENDING = "N/A";
    /**
     * String for full time match elapsed time.
     */
    public static final String ELAPSED_FULL_TIME = "FT";
    /**
     * String for added full time elapsed time.
     */
    public static final String ELAPSED_90_PLUS_TIME = "90+";

    /**
     * Input array index for home team id.
     */
    private static final int HOME_TEAM_ID_INDEX = 0;
    /**
     * Input array index for away team id.
     */
    private static final int AWAY_TEAM_ID_INDEX = 1;
    /**
     * Input array index for home team name.
     */
    private static final int HOME_TEAM_NAME_INDEX = 2;
    /**
     * Input array index for away team name.
     */
    private static final int AWAY_TEAM_NAME_INDEX = 3;
    /**
     * Input array index for datetime.
     */
    private static final int DATE_TIME_INDEX = 4;
    /**
     * Input array index for elapsed.
     */
    private static final int ELAPSED_INDEX = 7;
    /**
     * Input array index for full time score.
     */
    private static final int FULL_TIME_SCORE_INDEX = 12;

    /**
     * Date formatter for match header datetime string.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * WhoScored id for the match.
     */
    private long matchId;
    /**
     * Match input data array.
     */
    private List<Object> input;

    /**
     * Gets the home team id.
     *
     * @return Home team id.
     */
    public Long getHomeTeamId() {
        if(this.input != null) {
            return ((Double) this.input.get(HOME_TEAM_ID_INDEX)).longValue();
        }
        return null;
    }

    /**
     * Gets the away team id.
     *
     * @return Away team id.
     */
    public Long getAwayTeamId() {
        if(this.input != null) {
            return ((Double) this.input.get(AWAY_TEAM_ID_INDEX)).longValue();
        }
        return null;
    }

    /**
     * Gets the home team name.
     *
     * @return Home team name.
     */
    public String getHomeTeamName() {
        if(this.input != null) {
            return (String) this.input.get(HOME_TEAM_NAME_INDEX);
        }
        return null;
    }

    /**
     * Gets the away team name.
     *
     * @return Away team name.
     */
    public String getAwayTeamName() {
        if(this.input != null) {
            return (String) this.input.get(AWAY_TEAM_NAME_INDEX);
        }
        return null;
    }

    /**
     * Gets the match datetime.
     *
     * @return Match datetime.
     */
    public LocalDateTime getDatetime() {
        if(this.input != null) {
            final LocalDateTime localDateTime = LocalDateTime.parse((String) this.input.get(DATE_TIME_INDEX), DATE_TIME_FORMATTER);
            return localDateTime.plusHours(2);
        }
        return null;
    }

    /**
     * Gets the elapsed match time.
     *
     * @return Elapsed match time.
     */
    public String getElapsed() {
        if(this.input != null) {
            final String elapsed = (String) this.input.get(ELAPSED_INDEX);
            // If elapsed is "FT" but the score has a '*'in front then it's still 'green'.
            // Return 90+ so we update it once more in that case.
            if(ELAPSED_FULL_TIME.equals(elapsed)
                    && this.input.size() > FULL_TIME_SCORE_INDEX
                    && this.input.get(FULL_TIME_SCORE_INDEX).toString().startsWith("*")) {
                return ELAPSED_90_PLUS_TIME;
            }
            if(elapsed != null) {
                // We want to change 32' to 32.
                return elapsed.replace("'", "");
            }
        }
        return ELAPSED_PENDING;
    }

    /**
     * Gets the home team id.
     *
     * @return Home team id.
     */
    public Status getStatus() {
        switch(getElapsed()) {
            case ELAPSED_PENDING:
                return Status.PENDING;
            case ELAPSED_FULL_TIME:
                return Status.FULL_TIME;
            default:
                return Status.ACTIVE;
        }
    }

    /**
     * Gets a match data object for the match in the header.
     *
     * @return Match data object for the match in the header.
     */
    public MatchData getMatchData() {
        final MatchData matchData = new MatchData();
        matchData.setWhoscoredId(getMatchId());
        matchData.setHomeTeamName(getHomeTeamName());
        matchData.setHomeTeamWhoscoredId(getHomeTeamId());
        matchData.setAwayTeamName(getAwayTeamName());
        matchData.setAwayTeamWhoscoredId(getAwayTeamId());
        matchData.setDatetime(getDatetime());
        matchData.setElapsed(getElapsed());
        matchData.setStatus(getStatus());
        return matchData;
    }

}
