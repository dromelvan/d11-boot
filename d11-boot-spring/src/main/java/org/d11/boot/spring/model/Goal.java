package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A goal in a Premier League match.
 */
@Data
@Entity
public class Goal extends D11Entity {

    /**
     * Max time for goals.
     */
    public static final int MAX_GOAL_TIME = 90;

    /**
     * The time at which the goal was scored.
     */
    @Min(0)
    @Max(MAX_GOAL_TIME)
    private int time;

    /**
     * The added time at which the goal was scored, if after 90 minutes.
     */
    @PositiveOrZero
    private int addedTime;

    /**
     * Penalty status.
     */
    private boolean penalty;

    /**
     * Own goal status.
     */
    private boolean ownGoal;

    /**
     * The match in which the goal was scored.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "match_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Match match;

    /**
     * The team for which the goal was scored.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Team team;

    /**
     * The player who scored the goal.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Player player;

    /**
     * Validates that a goal is not both a penalty and an own goal.
     *
     * @return False if the goal is both an own goal and a penalty or superclass validation fails. True otherwise.
     */
    @Override
    public boolean isValid() {
        if (this.penalty && this.ownGoal) {
            return false;
        }
        return super.isValid();
    }

}
