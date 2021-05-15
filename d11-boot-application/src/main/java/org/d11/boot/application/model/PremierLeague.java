package org.d11.boot.application.model;

import lombok.Data;

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

}
