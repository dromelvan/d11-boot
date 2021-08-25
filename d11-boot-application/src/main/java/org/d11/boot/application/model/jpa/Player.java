package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.util.Parameterizer;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

/**
 * A Premier League player.
 */
@Data
@Entity
public class Player extends D11Entity {

    /**
     * Player id on whoscored.
     */
    @PositiveOrZero
    private int whoscoredId;
    /**
     * Player first name.
     */
    @NotNull
    private String firstName;
    /**
     * Player last name.
     */
    @NotEmpty
    private String lastName;
    /**
     * Player full name.
     */
    private String fullName;
    /**
     * Player parameterized name for searches.
     */
    private String parameterizedName;
    /**
     * Player birth date.
     */
    private LocalDate dateOfBirth;
    /**
     * Player height.
     */
    @PositiveOrZero
    private int height;
    /**
     * Name of the file with the player photo, if there is one.
     */
    private String photoFileName;
    /**
     * Automagically created players will start out as unverified until someone has had time to set their country,
     * date of birth and other things that aren't available in the match data.
     */
    private boolean verified;

    /**
     * The players nationality.
     */
    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Country country;

    /**
     * Returns the player first name plus last name.
     *
     * @return The player first name plus last name.
     */
    public String getName() {
        return String.format("%s %s", getFirstName(), getLastName()).trim();
    }

    /**
     * Returns a shortened version of the players name.
     *
     * @return The player last name plus the first letter in the first name.
     */
    public String getShortName() {
        if (getFirstName().isEmpty()) {
            return getLastName();
        } else {
            return String.format("%s %c", getLastName(), getFirstName().charAt(0)).trim();
        }
    }

    /**
     * Updates the parameterized name before persisting.
     */
    @Override
    @PrePersist
    public void prePersist() {
        updateParameterizedName();
        super.prePersist();
    }

    /**
     * Updates the parameterized name before updating.
     */
    @Override
    @PreUpdate
    public void preUpdate() {
        updateParameterizedName();
        super.preUpdate();
    }

    /**
     * Updates the parameterized name based on the player name.
     */
    private void updateParameterizedName() {
        setParameterizedName(Parameterizer.parameterize(getName()));
    }

}
