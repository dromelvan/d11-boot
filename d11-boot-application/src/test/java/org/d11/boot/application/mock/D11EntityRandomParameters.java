package org.d11.boot.application.mock;

import org.d11.boot.application.model.Country;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.model.Player;
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
    public D11EntityRandomParameters() {
        randomize(FieldPredicates.named("id"), new LongRangeRandomizer(1L, MAX_ID));
        randomize(FieldPredicates.named("whoscoredId"), new IntegerRangeRandomizer(1, (int) MAX_ID));
        randomize(FieldPredicates.named("code"), new StringRandomizer(Team.CODE_LENGTH, Team.CODE_LENGTH, System.currentTimeMillis()));
        randomize(FieldPredicates.named("iso"), new StringRandomizer(Country.ISO_CODE_LENGTH, Country.ISO_CODE_LENGTH, System.currentTimeMillis()));
        randomize(FieldPredicates.named("sortOrder"), new IntegerRangeRandomizer(1, MAX_POSITIVE));
        randomize(FieldPredicates.named("matchWeekNumber"), new IntegerRangeRandomizer(1, MatchWeek.MAX_MATCH_WEEK_NUMBER));

        randomize(FieldPredicates.named("email").and(FieldPredicates.inClass(User.class)), new EmailRandomizer());

        randomize(FieldPredicates.named("height").and(FieldPredicates.inClass(Player.class)), new IntegerRangeRandomizer(1, MAX_POSITIVE));

        randomize(FieldPredicates.named("capacity").and(FieldPredicates.inClass(Stadium.class)), new IntegerRangeRandomizer(1, MAX_POSITIVE));
        randomize(FieldPredicates.named("opened").and(FieldPredicates.inClass(Stadium.class)), new IntegerRangeRandomizer(MIN_YEAR, MAX_YEAR));

        randomize(FieldPredicates.named("established").and(FieldPredicates.inClass(Team.class)), new IntegerRangeRandomizer(MIN_YEAR, MAX_YEAR));

        randomize(FieldPredicates.named("name").and(FieldPredicates.inClass(Season.class)), new YearIntervalRandomizer());
    }

}
