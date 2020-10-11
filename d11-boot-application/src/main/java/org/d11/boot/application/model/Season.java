package org.d11.boot.application.model;

import com.google.common.collect.ComparisonChain;
import lombok.Data;
import org.d11.boot.application.model.converter.StatusConverter;
import org.d11.boot.application.model.validation.YearInterval;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A season of the Premier League and/or D11.
 */
@Data
@Entity
public class Season extends D11Entity implements Comparable<Season> {

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
                .compare(this.getDate(), season.getDate())
                .result();
    }
}
