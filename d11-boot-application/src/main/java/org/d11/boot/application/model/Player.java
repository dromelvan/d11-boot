package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.util.Current;
import org.d11.boot.application.util.Parameterizer;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
     * Player season stats sorted by ranking for this season.
     */
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PlayerSeasonStat> playerSeasonStats;

    /**
     * Transfer bids for this player.
     */
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TransferBid> transferBids;

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
        if(getFirstName().isEmpty()) {
            return getLastName();
        } else {
            return String.format("%s %c", getLastName(), getFirstName().charAt(0)).trim();
        }
    }

    /**
     * Gets a transfer bid for the current transfer day made by a specific D11 team, if such a transfer bid exists.
     *
     * @param d11TeamId Id of the D11 team for which a transfer bid will be looked up.
     * @return Optional of the transfer bid if it exists, empty optional if not.
     */
    public Optional<TransferBid> getCurrentTransferBidByD11TeamId(final long d11TeamId) {
        final TransferDay currentTransferDay = Current.getTransferDay();
        return this.transferBids.stream()
                .filter(transferBid -> transferBid.getTransferDay().equals(currentTransferDay)
                                       && transferBid.getD11Team().getId() == d11TeamId)
                .findFirst();
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
