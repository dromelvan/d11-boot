package org.d11.boot.cli.camel.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.d11.boot.jms.model.MatchData;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Processes a list of MatchData and outputs migration file SQL for inserting fixtures.
 */
@Slf4j
public class FixturesProcessor implements Processor {

    /**
     * Formatter for kickoff time.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * SQL insert string.
     */
    private static final String INSERT_MATCH =
            "insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, " +
                                                       "whoscored_id, datetime, home_team_goals, away_team_goals, " +
                                                       "previous_home_team_goals, previous_away_team_goals, elapsed, " +
                                                       "status, created_at, updated_at)\n" +
            "    values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = %d),\n" +
            "            (select (id) from ${flyway:defaultSchema}.team where whoscored_id = %d),\n" +
            "            (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = %d " +
                                           "and season_id = (select max(id) from ${flyway:defaultSchema}.season)),\n" +
            "            (select (stadium_id) from ${flyway:defaultSchema}.team where whoscored_id = %d),\n" +
            "            %d, '%s', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);\n";
    /**
     * Max number of matches in one match week.
     */
    private static final int MATCH_WEEK_MATCH_COUNT = 10;

    @Override
    public void process(final Exchange exchange) {
        final List<MatchData> matchDatas = exchange.getIn().getBody(List.class);
        final StringBuilder stringBuilder = new StringBuilder();

        int matchWeekNumber = 1;
        int matchCount = 0;
        for (final MatchData matchData : matchDatas) {
            stringBuilder.append(String.format(INSERT_MATCH, matchData.getHomeTeamWhoscoredId(),
                                                            matchData.getAwayTeamWhoscoredId(),
                                                            matchWeekNumber,
                                                            matchData.getHomeTeamWhoscoredId(),
                                                            matchData.getWhoscoredId(),
                                                            matchData.getDatetime().format(DATE_TIME_FORMATTER)));
            ++matchCount;
            if(matchCount >= MATCH_WEEK_MATCH_COUNT) {
                matchCount = 0;
                ++matchWeekNumber;
            }
        }

        log.info("Parsed {} fixtures.", matchDatas.size());

        exchange.getIn().setBody(stringBuilder.toString().trim(), String.class);
    }

}
