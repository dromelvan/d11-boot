package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.util.Current;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A D11 team.
 */
@Data
@Entity
@Table(name = "d11_team")
public class D11Team extends D11Entity {

    /**
     * Code string length.
     */
    public static final int CODE_LENGTH = 3;
    /**
     * Id for the dummy D11 team.
     */
    public static final long DUMMY_D11_TEAM_ID = 1;

    /**
     * Team name.
     */
    @NotEmpty
    private String name;
    /**
     * Short version of the team name.
     */
    @NotEmpty
    private String shortName;
    /**
     * Three letter team code.
     */
    @NotNull
    @Size(min = CODE_LENGTH, max = CODE_LENGTH)
    private String code;

    /**
     * Indicates if the team is a dummy team or a real team.
     */
    private boolean dummy;
    /**
     * Name of the file with the team club crest, if there is one.
     */
    private String photoFileName;

    /**
     * The owner of the D11 team.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User owner;

    /**
     * The co owner of the D11 team, if there is one.
     */
    // We need fetch typ LAZY to find D11 teams with null co owners.
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="co_owner_id", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User coOwner;

    /**
     * Player season stats for this D11 team.
     */
    @OneToMany(mappedBy = "d11Team", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PlayerSeasonStat> playerSeasonStats;

    /**
     * D11 team season stats for this D11 team.
     */
    @OneToMany(mappedBy = "d11Team", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<D11TeamSeasonStat> d11TeamSeasonStats;

    /**
     * Transfer listings for this D11 team.
     */
    @OneToMany(mappedBy = "d11Team", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TransferListing> transferListings;

    /**
     * Checks if a user is either an administrator or the owner/co-owner of the D11 team.
     *
     * @param user The user that will be checked.
     * @return True if the user is allowed to administer the team, false if not.
     */
    public boolean isAdministratedBy(final User user) {
        return user.isAdministrator()
                || user.equals(getOwner())
                || user.equals(getCoOwner());
    }

    /**
     * Gets the D11 team season stat for this D11 team and the current season.
     *
     * @return The current season D11 team season stat.
     */
    public Optional<D11TeamSeasonStat> getCurrentD11TeamSeasonStat() {
        final Season currentSeason = Current.getSeason();
        return this.d11TeamSeasonStats.stream()
                .filter(d11TeamSeasonStat -> d11TeamSeasonStat.getSeason().equals(currentSeason))
                .findFirst();
    }

    /**
     * Gets player season stats for this D11 team and the current season.
     *
     * @return Player season stats for the current season.
     */
    private Stream<PlayerSeasonStat> getCurrentPlayerSeasonStats() {
        final Season currentSeason = Current.getSeason();
        return this.playerSeasonStats.stream()
                .filter(playerSeasonStat -> playerSeasonStat.getSeason().equals(currentSeason));
    }

    /**
     * Checks if this D11 team can make a transfer bid for a player with a specific position.
     *
     * @param position The position that will be checked.
     * @return True if the D11 team can make a transfer bid for the position, false otherwise.
     */
    public boolean isBiddablePosition(final Position position) {
        final long positionCount = getCurrentPlayerSeasonStats()
                .filter(playerSeasonStat -> playerSeasonStat.getPosition().equals(position))
                .count();

        return positionCount < position.getMaxCount();
    }

    /**
     * Gets the current value of the D11 team player squad.
     *
     * @return The current value of the D11 team player squad.
     */
    public int getValue() {
        return getCurrentPlayerSeasonStats()
                .mapToInt(PlayerSeasonStat::getValue)
                .sum();
    }

    /**
     * Gets the max bid the D11 team can make based on the current value of the squad and number of empty spots.
     *
     * @return The max bid the D11 team can make.
     */
    @SuppressWarnings("PMD.UselessParentheses")
    public int getMaxBid() {
        if(!Current.isInitialized()
            || (this.owner == null && this.coOwner == null)) {
            return 0;
        }
        final int missingPlayers = 11 - (int) getCurrentPlayerSeasonStats().count();

        return missingPlayers <= 0
                ? 0
                : Current.getSeason().getD11TeamBudget() - getValue() - Transfer.FEE_DIVISOR * (missingPlayers - 1);
    }

    /**
     * Gets transfer listings made by the D11 team for non pending transfer days a specific season.
     *
     * @param season The season for which transfer listings will be looked up.
     * @return List of pending transfer listings for the D11 team.
     */
    public List<TransferListing> getTransferListings(final Season season) {
        return this.transferListings.stream()
                .filter(transferListing -> transferListing.getTransferDay().getTransferWindow().getMatchWeek().getSeason().equals(season)
                                           && !Status.PENDING.equals(transferListing.getTransferDay().getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * Gets transfer listings made by the D11 team for transfer days with status PENDING.
     *
     * @return List of pending transfer listings for the D11 team.
     */
    public List<TransferListing> getPendingTransferListings() {
        return this.transferListings.stream()
                .filter(transferListing -> Status.PENDING.equals(transferListing.getTransferDay().getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * Gets the number of remaining transfers for the D11 team for a specific season.
     *
     * @param season The season remaining transfer count will be found for.
     * @return The number of remaining transfers for the D11 team the specific season.
     */
    public int getRemainingTransfers(final Season season) {
        return season.getMaxTransfers() - (int) this.transferListings.stream()
                .filter(transferListing -> transferListing.getTransferDay().getTransferWindow().getMatchWeek().getSeason().equals(season))
                .count();
    }

}
