package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Base class for Premier Leagues and D11 leagues.
 */
@Data
@MappedSuperclass
public class League extends D11Entity {

    /**
     * The name of the league.
     */
    @NotEmpty
    private String name;
    /**
     * The season this league belongs to.
     */
    @OneToOne
    @JoinColumn(name = "season_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Season season;

}
