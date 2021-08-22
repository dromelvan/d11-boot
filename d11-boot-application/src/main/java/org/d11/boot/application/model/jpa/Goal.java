package org.d11.boot.application.model.jpa;

import lombok.Data;
import org.d11.boot.application.model.jpa.MatchEvent;

import javax.persistence.Entity;

/**
 * A goal in a Premier League match.
 */
@Data
@Entity
public class Goal extends MatchEvent {

    /**
     * Was this goal scored from a penalty.
     */
    private boolean penalty;
    /**
     * Was this an own goal.
     */
    private boolean ownGoal;

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
