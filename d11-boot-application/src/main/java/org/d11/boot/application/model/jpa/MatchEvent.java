package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Base class for match events.
 */
@Data
@MappedSuperclass
public class MatchEvent extends D11Entity {

    /**
     * Max time for match events.
     */
    public static final int MAX_MATCH_EVENT_TIME = 90;

    /**
     * The time at which the event occurred.
     */
    @Min(0)
    @Max(MAX_MATCH_EVENT_TIME)
    private int time;
    /**
     * The added time at which the event occurred, if after 90 minutes.
     */
    @PositiveOrZero
    private int addedTime;

    /**
     * The match in which the event occurred.
     */
    @ManyToOne
    @JoinColumn(name = "match_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Match match;

    /**
     * The team for which the event occurred.
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Team team;

    /**
     * The player for which the event occurred.
     */
    @ManyToOne
    @JoinColumn(name = "player_id")
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Player player;

}
