package org.d11.boot.application.model;

import lombok.Data;

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

}
