package org.d11.boot.cli.camel.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.d11.boot.api.model.D11MatchDTO;
import org.d11.boot.api.model.D11TeamBaseDTO;
import org.d11.boot.api.model.MatchWeekBaseDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Processor that generates D11 fixtures and outputs migration file SQL for inserting D11 fixtures.
 */
@Slf4j
// CHECKSTYLE:OFF
@SuppressWarnings("PMD")
public class D11FixturesProcessor implements Processor {

    /**
     * SQL insert string.
     */
    private static final String INSERT_D11_MATCH =
            "insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, " +
                                                           "datetime, home_team_goals, away_team_goals, " +
                                                           "home_team_points, away_team_points,\n" +
            "                                               previous_home_team_goals, previous_away_team_goals, " +
                                                           "previous_home_team_points, previous_away_team_points, " +
                                                           "elapsed, status, created_at, updated_at)\n" +
            "    values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = %d " +
                                           "and season_id = (select max(id) from ${flyway:defaultSchema}.season)),\n" +
            "           (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = %d " +
                                           "and season_id = (select max(id) from ${flyway:defaultSchema}.season)),\n" +
            "           (select id from ${flyway:defaultSchema}.match_week where match_week_number = %d " +
                                           "and season_id = (select max(id) from ${flyway:defaultSchema}.season)),\n" +
            "           (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = %d " +
                                           "and season_id = (select max(id) from ${flyway:defaultSchema}.season))" +
                                           "::timestamp + interval '17 hour')),\n" +
            "            0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);\n";

    @Override
    public void process(final Exchange exchange) {
        final List<D11MatchDTO> d11Matches = new ArrayList<>();
        final List<Integer> rankings = new ArrayList<>();

        for (int i = 0; i < 20; ++i) {
            rankings.add(i + 1);
        }

        if(rankings.size() % 2 == 1) {
            // If we have an odd number of d11 teams add a dummy id. Matches with
            // the dummy team will be removed later.
            rankings.add(0);
        }

        Collections.shuffle(rankings);

        final int matchDayCount = rankings.size() - 1;
        final int matchDayMatchCount = rankings.size() / 2;
        String[][] matchDays = new String[matchDayCount][matchDayMatchCount];

        for(int matchDay = 0; matchDay < matchDayCount; matchDay++) {
            for(int match = 0; match < matchDayMatchCount; match++) {
                final int home = (matchDay + match) % (rankings.size() - 1);
                int away = (rankings.size() - 1 - match + matchDay) % (rankings.size() - 1);
                // The last team will stay in place while the others rotate around it.
                if(match == 0) {
                    away = rankings.size() - 1;
                }
                matchDays[matchDay][match] = rankings.get(home) + ":" + rankings.get(away);
            }
        }

        // Mix it up so that home and away games are somewhat evenly distributed.
        final String[][] shuffled = new String[matchDayCount][matchDayMatchCount];

        int even = 0;
        int odd = rankings.size() / 2;
        for (int i = 0; i < matchDays.length; i++) {
            if (i % 2 == 0) {
                shuffled[i] = matchDays[even++];
            } else {
                shuffled[i] = matchDays[odd++];
            }
        }

        matchDays = shuffled;

        // The last team can't play away all the time so switch them to the home team for odd numbered match days.
        for (int matchDay = 0; matchDay < matchDays.length; matchDay++) {
            if (matchDay % 2 == 1) {
                matchDays[matchDay][0] = flip(matchDays[matchDay][0]);
            }
        }

        final String[][] allMatchDays = new String[matchDayCount * 2][matchDayMatchCount];
        for(int i = 0; i < matchDays.length; ++i) {
            System.arraycopy(matchDays[i], 0, allMatchDays[i], 0, matchDays[i].length);
        }
        for(int i = 0; i < matchDays.length; ++i) {
            for(int j = 0; j < matchDays[i].length; ++j) {
                allMatchDays[i + matchDays.length][j] = flip(matchDays[i][j]);
            }
        }

        int matchWeekNumber = 1;

        for(final String[] allMatchDay : allMatchDays) {
            final MatchWeekBaseDTO matchWeekDTO = new MatchWeekBaseDTO().matchWeekNumber(matchWeekNumber);
            for(final String match : allMatchDay) {
                final String[] teamRankings = match.split(":");
                if(Integer.parseInt(teamRankings[0]) > 0 && Integer.parseInt(teamRankings[1]) > 0) {
                    final D11TeamBaseDTO homeD11Team = new D11TeamBaseDTO();
                    homeD11Team.setId(Long.parseLong(teamRankings[0]));
                    final D11TeamBaseDTO awayD11Team = new D11TeamBaseDTO();
                    awayD11Team.setId(Long.parseLong(teamRankings[1]));

                    final D11MatchDTO d11Match = new D11MatchDTO();
                    d11Match.setHomeD11Team(homeD11Team);
                    d11Match.setAwayD11Team(awayD11Team);
                    d11Match.setMatchWeek(matchWeekDTO);

                    d11Matches.add(d11Match);
                }
            }
            ++matchWeekNumber;
        }

        final StringBuilder stringBuilder = new StringBuilder();

        d11Matches.forEach(d11Match -> stringBuilder.append(String.format(INSERT_D11_MATCH,
                                                                        d11Match.getHomeD11Team().getId(),
                                                                        d11Match.getAwayD11Team().getId(),
                                                                        d11Match.getMatchWeek().getMatchWeekNumber(),
                                                                        d11Match.getMatchWeek().getMatchWeekNumber())));

        log.info("Generated {} D11 fixtures.", d11Matches.size());

        exchange.getIn().setBody(stringBuilder.toString().trim(), String.class);
    }

    /**
     * Flips ids for a match.
     *
     * @param match Match ids that will be flipped.
     * @return Flipped match ids.
     */
    private String flip(final String match) {
        final String[] teamIds = match.split(":");
        return teamIds[1] + ":" + teamIds[0];
    }

}
