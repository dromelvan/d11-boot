package org.d11.boot.application.model;

import com.google.common.collect.ComparisonChain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.converter.StatusConverter;
import org.d11.boot.application.model.validation.YearInterval;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
     * The Premier League of this season.
     */
    @OneToOne(mappedBy = Season.MAPPED_BY, cascade = CascadeType.ALL)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PremierLeague premierLeague;

    /**
     * The D11 league of this season.
     */
    @OneToOne(mappedBy = Season.MAPPED_BY, cascade = CascadeType.ALL)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11League d11League;

    /**
     * List of match weeks belonging to this season, ordered by match week number.
     */
    @OneToMany(mappedBy = Season.MAPPED_BY, cascade = CascadeType.ALL)
    @OrderBy("matchWeekNumber ASC")
    private List<MatchWeek> matchWeeks = new ArrayList<>();

    /**
     * Player season stats sorted by ranking for this season.
     */
    @OneToMany(mappedBy = Season.MAPPED_BY, cascade = CascadeType.ALL)
    @OrderBy(Season.RANKING)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PlayerSeasonStat> playerSeasonStats;

    /**
     * Top 3 team season stats sorted by ranking for this season.
     */
    @OneToMany(mappedBy = Season.MAPPED_BY, cascade = CascadeType.ALL)
    @OrderBy(Season.RANKING)
    @Where(clause = "ranking <= 3")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeamSeasonStat> top3TeamSeasonStats;

    /**
     * Top 3 D11 team season stats sorted by ranking for this season.
     */
    @OneToMany(mappedBy = Season.MAPPED_BY, cascade = CascadeType.ALL)
    @OrderBy(Season.RANKING)
    @Where(clause = "ranking <= 3")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<D11TeamSeasonStat> top3D11TeamSeasonStats;

    /**
     * Top 3 player season stats sorted by ranking for this season.
     */
    @OneToMany(mappedBy = Season.MAPPED_BY, cascade = CascadeType.ALL)
    @OrderBy(Season.RANKING)
    @Where(clause = "ranking <= 3")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PlayerSeasonStat> top3PlayerSeasonStats;

    /**
     * Gets a shortened version of the season name with the two last digits of the start and end years.
     *
     * @return A shortened version of the season name.
     */
    public String getShortName() {
        if(this.name != null) {
            final Matcher matcher = SHORT_NAME_PATTERN.matcher(this.name);
            if(matcher.matches()) {
                return String.format("%s-%s", matcher.group(1), matcher.group(2));
            }
        }
        return null;
    }

    @Override
    public int compareTo(final Season season) {
        return ComparisonChain.start()
                .compare(season.getDate(), this.getDate())
                .result();
    }
}
