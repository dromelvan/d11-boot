package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.util.Parameterizer;

import java.time.LocalDate;

/**
 * A Premier League player.
 */
@Data
@Entity
public class Player extends D11Entity {

    /**
     * Player id on the stat source site.
     */
    @PositiveOrZero
    private int statSourceId;

    /**
     * Player id on premierleague.com.
     */
    @PositiveOrZero
    private int premierLeagueId;

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
    @NotEmpty
    private String parameterizedName;

    /**
     * Player birthdate.
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
     * The player's nationality.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
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
        return String.format("%s %s", getFirstName().trim(), getLastName()).trim();
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
    public void prePersist() {
        updateParameterizedName();
        super.prePersist();
    }

    /**
     * Updates the parameterized name before updating.
     */
    @Override
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
