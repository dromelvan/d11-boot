package org.d11.boot.spring.util;

import org.d11.boot.spring.model.Country;
import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Goal;
import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.PlayerMatchStat;
import org.d11.boot.spring.model.Position;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Stadium;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.model.User;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;
import org.jeasy.random.randomizers.text.StringRandomizer;

/**
 * EasyRandom parameters with suitable restrictions for generating objects for D11 tests.
 */
@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class D11BootEasyRandomParameters extends EasyRandomParameters {

    /**
     * Max positive general integer that will be generated.
     */
    private static final int MAX_POSITIVE = 100_000;

    /**
     * Max id that will be generated.
     */
    private static final long MAX_ID = 10_000;

    /**
     * Creates new random parameters.
     */
    public D11BootEasyRandomParameters() {
        randomize(FieldPredicates.named("id"), new LongRangeRandomizer(1L, MAX_ID));
        randomize(Integer.class, new IntegerRangeRandomizer(1, MAX_POSITIVE));

        randomize(FieldPredicates.named("email").and(FieldPredicates.inClass(User.class)), new EmailRandomizer());

        randomize(FieldPredicates.named("iso").and(FieldPredicates.inClass(Country.class)),
                  new StringRandomizer(Country.ISO_CODE_LENGTH, Country.ISO_CODE_LENGTH, System.currentTimeMillis()));

        randomize(FieldPredicates.named("opened").and(FieldPredicates.inClass(Stadium.class)),
                  new IntegerRangeRandomizer(Stadium.MIN_OPENED_YEAR, Stadium.MAX_OPENED_YEAR));

        randomize(FieldPredicates.named("code").and(FieldPredicates.inClass(Position.class)),
                  new StringRandomizer(1, Position.MAX_CODE_LENGTH, System.currentTimeMillis()));
        randomize(FieldPredicates.named("maxCount").and(FieldPredicates.inClass(Position.class)),
                  new IntegerRangeRandomizer(1, Position.MAX_MAX_COUNT));

        randomize(FieldPredicates.named("maxCount").and(FieldPredicates.inClass(Position.class)),
                  new IntegerRangeRandomizer(1, Position.MAX_MAX_COUNT));

        randomize(FieldPredicates.named("code").and(FieldPredicates.inClass(Team.class)),
                  new StringRandomizer(Team.CODE_LENGTH, Team.CODE_LENGTH, System.currentTimeMillis()));
        randomize(FieldPredicates.named("established").and(FieldPredicates.inClass(Team.class)),
                  new IntegerRangeRandomizer(Team.MIN_ESTABLISHED_YEAR, Team.MAX_ESTABLISHED_YEAR));

        randomize(FieldPredicates.named("code").and(FieldPredicates.inClass(D11Team.class)),
                new StringRandomizer(D11Team.CODE_LENGTH, D11Team.CODE_LENGTH, System.currentTimeMillis()));

        randomize(FieldPredicates.named("name").and(FieldPredicates.inClass(Season.class)),
            new YearIntervalRandomizer());

        randomize(FieldPredicates.named("matchWeekNumber").and(FieldPredicates.inClass(MatchWeek.class)),
                  new IntegerRangeRandomizer(1, MatchWeek.MAX_MATCH_WEEK_NUMBER));
        randomize(FieldPredicates.named("elapsed").and(FieldPredicates.inClass(MatchWeek.class)),
                  new IntegerRangeRandomizer(0, MatchWeek.MAX_ELAPSED));

        randomize(FieldPredicates.named("elapsed").and(FieldPredicates.inClass(Match.class)),
                  new StringRandomizer(1, Match.ELAPSED_TIME_MAX_LENGTH, System.currentTimeMillis()));

        randomize(FieldPredicates.named("elapsed").and(FieldPredicates.inClass(D11Match.class)),
                  new StringRandomizer(1, Match.ELAPSED_TIME_MAX_LENGTH, System.currentTimeMillis()));

        randomize(FieldPredicates.named("time").and(FieldPredicates.inClass(Goal.class)),
                  new IntegerRangeRandomizer(0, Goal.MAX_GOAL_TIME));

        randomize(FieldPredicates.named("playedPosition").and(FieldPredicates.inClass(PlayerMatchStat.class)),
                  new StringRandomizer(1, PlayerMatchStat.MAX_PLAYED_POSITION_LENGTH, System.currentTimeMillis()));
        randomize(FieldPredicates.named("substitutionOnTime").and(FieldPredicates.inClass(PlayerMatchStat.class)),
                  new IntegerRangeRandomizer(0, PlayerMatchStat.MAX_MATCH_TIME));
        randomize(FieldPredicates.named("substitutionOffTime").and(FieldPredicates.inClass(PlayerMatchStat.class)),
                  new IntegerRangeRandomizer(0, PlayerMatchStat.MAX_MATCH_TIME));
        randomize(FieldPredicates.named("yellowCardTime").and(FieldPredicates.inClass(PlayerMatchStat.class)),
                  new IntegerRangeRandomizer(0, PlayerMatchStat.MAX_MATCH_TIME));
        randomize(FieldPredicates.named("redCardTime").and(FieldPredicates.inClass(PlayerMatchStat.class)),
                  new IntegerRangeRandomizer(0, PlayerMatchStat.MAX_MATCH_TIME));

        randomize(FieldPredicates.named("redCardTime").and(FieldPredicates.inClass(PlayerMatchStat.class)),
                  new IntegerRangeRandomizer(0, PlayerMatchStat.MAX_MATCH_TIME));
    }

}
