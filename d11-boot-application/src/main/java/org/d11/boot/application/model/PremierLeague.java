package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

/**
 * A Premier League for a specific season.
 */
@Data
@Entity
public class PremierLeague extends League {

    /**
     * List of match weeks belonging to this Premier League, ordered by match week number.
     */
    @OneToMany(mappedBy = "premierLeague", cascade = CascadeType.ALL)
    @OrderBy("matchWeekNumber ASC")
    private List<MatchWeek> matchWeeks = new ArrayList<>();

    /**
     * Top 3 team table stats sorted by ranking for this Premier League.
     */
    @OneToMany(mappedBy = "premierLeague", cascade = CascadeType.ALL)
    @OrderBy("ranking")
    @Where(clause = "main = true AND ranking <= 3")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TeamTableStat> top3TeamTableStats;

}
