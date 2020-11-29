package org.d11.boot.application.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * A substitution in a Premier League match.
 */
@Data
@Entity
public class Substitution extends MatchEvent {

    /**
     * The player entered the game.
     */
    @ManyToOne
    @JoinColumn(name = "player_in_id")
    @NotNull
    private Player playerIn;

    /**
     * Validates that the same player was not substituted both in and out.
     *
     * @return False if the same player was substituted both in and out or superclass validation fails. True otherwise.
     */
    @Override
    public boolean isValid() {
        if(getPlayer() != null
            && this.playerIn != null
            && getPlayer().getId().equals(this.playerIn.getId())) {
            return false;
        }
        return super.isValid();
    }

}
