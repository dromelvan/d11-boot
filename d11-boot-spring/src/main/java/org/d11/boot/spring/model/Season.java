package org.d11.boot.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.d11.boot.spring.model.converter.StatusConverter;
import org.d11.boot.spring.model.validation.YearInterval;
import org.d11.boot.util.Status;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A season of the Premier League and/or D11.
 */
@Data
@Entity
public class Season extends D11Entity implements Comparable<Season> {

    /**
     * PMD doesn't like having "season" this many times in one class.
     */
    private static final String MAPPED_BY = "season";

    /**
     * PMD doesn't like having "ranking" this many times in one class.
     */
    private static final String RANKING = "ranking";

    /**
     * Pattern for extracting the short name from a season name.
     */
    private static final Pattern SHORT_NAME_PATTERN = Pattern.compile("\\d{2}(\\d{2})-\\d{2}(\\d{2})");

    /**
     * Season name. Should have the form YYYY-YYYY.
     */
    @YearInterval
    private String name;

    /**
     * The amount each D11 team can use to buy players this season.
     */
    @Positive
    @Column(name = "d11_team_budget")
    private int d11TeamBudget;

    /**
     * The maximum number of transfers a D11 team is allowed to make this season. 0 means unlimited.
     */
    @PositiveOrZero
    @Column(name = "d11_team_max_transfers")
    private int d11TeamMaxTransfers;

    /**
     * Season status.
     */
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    /**
     * Season start date.
     */
    @NotNull
    private LocalDate date;

    /**
     * Legacy seasons use a deprecated rule set for points scoring. Scores will not be recalculated when
     * a match belonging to a legacy season is updated.
     */
    private boolean legacy;

    /**
     * Gets a shortened version of the season name with the two last digits of the start and end years.
     *
     * @return A shortened version of the season name.
     */
    public String getShortName() {
        if (this.name != null) {
            final Matcher matcher = SHORT_NAME_PATTERN.matcher(this.name);
            if (matcher.matches()) {
                return String.format("%s-%s", matcher.group(1), matcher.group(2));
            }
        }
        return null;
    }

    @Override
    public int compareTo(final Season season) {
        return Comparator
                .comparing(Season::getDate)
                .compare(season, this);
    }

}
