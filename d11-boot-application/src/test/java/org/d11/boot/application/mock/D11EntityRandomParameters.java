package org.d11.boot.application.mock;

import org.d11.boot.application.model.Country;
import org.d11.boot.application.model.D11Match;
import org.d11.boot.application.model.D11MatchWeek;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.model.MatchEvent;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerStatSummary;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Stadium;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.model.User;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;
import org.jeasy.random.randomizers.text.StringRandomizer;

/**
 * Base entity random parameters.
 */
public class D11EntityRandomParameters extends EasyRandomParameters {

    /**
     * Max positive general integer that will be generated.
     */
    private static final int MAX_POSITIVE = 100_000;
    /**
     * Max id that will be generated.
     */
    private static final long MAX_ID = 10_000;
    /**
     * Min year that will be generated.
     */
    private static final int MIN_YEAR = Stadium.MIN_OPENED_YEAR;
    /**
     * Max year that will be generated.
     */
    private static final int MAX_YEAR = Stadium.MAX_OPENED_YEAR;

    /**
     * Creates new random parameters.
     */
    @SuppressWarnings({"checkstyle:ExecutableStatementCount", "checkstyle:MultipleStringLiterals", "checkstyle:JavaNCSS", "PMD.NcssCount"})
    public D11EntityRandomParameters() {
        randomize(FieldPredicates.named("id"), new LongRangeRandomizer(1L, MAX_ID));
        randomize(FieldPredicates.named("whoscoredId"), new IntegerRangeRandomizer(1, (int) MAX_ID));
        randomize(FieldPredicates.named("code"), new StringRandomizer(Team.CODE_LENGTH, Team.CODE_LENGTH, System.currentTimeMillis()));
        randomize(FieldPredicates.named("iso"), new StringRandomizer(Country.ISO_CODE_LENGTH, Country.ISO_CODE_LENGTH, System.currentTimeMillis()));
        randomize(FieldPredicates.named("sortOrder"), new IntegerRangeRandomizer(1, MAX_POSITIVE));
        randomize(FieldPredicates.named("matchWeekNumber"), new IntegerRangeRandomizer(1, MatchWeek.MAX_MATCH_WEEK_NUMBER));
        randomize(FieldPredicates.named("elapsed").and(FieldPredicates.inClass(MatchWeek.class).or(FieldPredicates.inClass(D11MatchWeek.class))),
                new IntegerRangeRandomizer(0, MatchWeek.MAX_ELAPSED));


        randomize(FieldPredicates.named("email").and(FieldPredicates.inClass(User.class)), new EmailRandomizer());

        randomize(FieldPredicates.named("height").and(FieldPredicates.inClass(Player.class)), new IntegerRangeRandomizer(1, MAX_POSITIVE));

        randomize(FieldPredicates.named("capacity").and(FieldPredicates.inClass(Stadium.class)), new IntegerRangeRandomizer(1, MAX_POSITIVE));
        randomize(FieldPredicates.named("opened").and(FieldPredicates.inClass(Stadium.class)), new IntegerRangeRandomizer(MIN_YEAR, MAX_YEAR));

        randomize(FieldPredicates.named("established").and(FieldPredicates.inClass(Team.class)), new IntegerRangeRandomizer(MIN_YEAR, MAX_YEAR));

        randomize(FieldPredicates.named("name").and(FieldPredicates.inClass(Season.class)), new YearIntervalRandomizer());

        randomize(FieldPredicates.named("goals"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("goalAssists"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("ownGoals"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("goalsConceded"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("rating"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("value"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("ranking"), new IntegerRangeRandomizer(1, MAX_POSITIVE));
        randomize(FieldPredicates.named("cleanSheets"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("yellowCards"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("redCards"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("substitutionsOn"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("substitutionsOff"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("manOfTheMatch").and(FieldPredicates.inClass(PlayerStatSummary.class)),
                new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("sharedManOfTheMatch").and(FieldPredicates.inClass(PlayerStatSummary.class)),
                new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("gamesStarted"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("gamesSubstitute"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("gamesDidNotParticipate"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("minutesPlayed"), new IntegerRangeRandomizer(0, MAX_POSITIVE));

        randomize(FieldPredicates.named("substitutionOnTime"), new IntegerRangeRandomizer(0, MatchEvent.MAX_MATCH_EVENT_TIME));
        randomize(FieldPredicates.named("substitutionOffTime"), new IntegerRangeRandomizer(0, MatchEvent.MAX_MATCH_EVENT_TIME));
        randomize(FieldPredicates.named("yellowCardTime"), new IntegerRangeRandomizer(0, MatchEvent.MAX_MATCH_EVENT_TIME));
        randomize(FieldPredicates.named("redCardTime"), new IntegerRangeRandomizer(0, MatchEvent.MAX_MATCH_EVENT_TIME));

        randomize(FieldPredicates.named("homeTeamGoals"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("awayTeamGoals"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("previousHomeTeamGoals"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("previousAwayTeamGoals"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("elapsed").and(FieldPredicates.inClass(Match.class).or(FieldPredicates.inClass(D11Match.class))),
                new StringRandomizer(1, Match.ELAPSED_TIME_MAX_LENGTH));

        randomize(FieldPredicates.named("time"), new IntegerRangeRandomizer(0, MatchEvent.MAX_MATCH_EVENT_TIME));
        randomize(FieldPredicates.named("addedTime"), new IntegerRangeRandomizer(0, MAX_POSITIVE));

        randomize(FieldPredicates.named("matchesPlayed"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("matchesWon"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("matchesDrawn"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("matchesLost"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("goalsFor"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("goalsAgainst"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("points"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("formPoints"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("previousRanking"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("homeMatchesPlayed"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("homeMatchesWon"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("homeMatchesDrawn"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("homeMatchesLost"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("homeGoalsFor"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("homeGoalsAgainst"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("homePoints"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("homeRanking"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("awayMatchesPlayed"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("awayMatchesWon"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("awayMatchesDrawn"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("awayMatchesLost"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("awayGoalsFor"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("awayGoalsAgainst"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("awayPoints"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
        randomize(FieldPredicates.named("awayRanking"), new IntegerRangeRandomizer(0, MAX_POSITIVE));

        randomize(FieldPredicates.named("winCount"), new IntegerRangeRandomizer(0, MAX_POSITIVE));
    }

}
