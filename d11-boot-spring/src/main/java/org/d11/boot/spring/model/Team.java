package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
     * ID for the dummy D11 team.
     */
    public static final long DEFAULT_TEAM_ID = 1;

    /**
     * Team id on the stat source site.
     */
    @Min(1)
    private int statSourceId;

    /**
     * Team id on premierleague.com.
     */
    @PositiveOrZero
    private int premierLeagueId;

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
     * The year the team was established.
     */
    @Min(MIN_ESTABLISHED_YEAR)
    @Max(MAX_ESTABLISHED_YEAR)
    private int established;

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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stadium_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Stadium stadium;

}
