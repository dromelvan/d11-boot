package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * A D11 League for a specific season.
 */
@Data
@Entity
@Table(name = "d11_league")
public class D11League extends League {

    /**
     * List of match weeks belonging to this Premier League, ordered by match week number.
     */
    @OneToMany(mappedBy = "d11League", cascade = CascadeType.ALL)
    @OrderBy("matchWeekNumber ASC")
    private List<D11MatchWeek> d11MatchWeeks = new ArrayList<>();

    /**
     * Top 3 D11 team table stats sorted by ranking for this D11 league.
     */
    @OneToMany(mappedBy = "d11League", cascade = CascadeType.ALL)
    @OrderBy("ranking")
    @Where(clause = "main = true AND ranking <= 3")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<D11TeamTableStat> top3D11TeamTableStats;

}
