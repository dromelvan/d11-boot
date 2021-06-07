package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A Premier League team.
 */
@Data
@Entity
public class Team extends D11Entity {

    /**
     * Minimum allowed value for the established property.
     */
    public static final int MIN_ESTABLISHED_YEAR = 1000;
    /**
     * Maximum allowed value for the established property.
     */
    public static final int MAX_ESTABLISHED_YEAR = 3000;
    /**
     * Code string length.
     */
    public static final int CODE_LENGTH = 3;

    /**
     * Team id on whoscored.
     */
    @Min(1)
    private int whoscoredId;
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
     * Team nickname.
     */
    @NotEmpty
    private String nickname;
    /**
     * The year the team was established.
     */
    @Min(MIN_ESTABLISHED_YEAR)
    @Max(MAX_ESTABLISHED_YEAR)
    private int established;
    /**
     * Team motto, if it has one.
     */
    private String motto;
    /**
     * Primary team colour.
     */
    @NotEmpty
    private String colour;
    /**
     * Indicates if the team is a dummy team or a real team.
     */
    private boolean dummy;
    /**
     * Name of the file with the team club crest, if there is one.
     */
    private String photoFileName;
    /**
     * Official team website url.
     */
    @NotEmpty
    private String url;

    /**
     * The stadium the team plays in.
     */
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stadium_id", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Stadium stadium;

}
