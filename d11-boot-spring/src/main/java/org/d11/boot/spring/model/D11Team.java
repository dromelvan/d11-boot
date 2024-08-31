package org.d11.boot.spring.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
     * Id for the default D11 team.
     */
    public static final long DEFAULT_D11_TEAM_ID = 1;

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
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User owner;

    /**
     * The co owner of the D11 team, if there is one.
     */
    // We need fetch typ LAZY to find D11 teams with null co-owners.
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "co_owner_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User coOwner;

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

}
