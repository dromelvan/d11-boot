package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User owner;

    /**
     * The co owner of the D11 team, if there is one.
     */
    // We need fetch typ LAZY to find D11 teams with null co owners.
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="co_owner_id", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User coOwner;

}
