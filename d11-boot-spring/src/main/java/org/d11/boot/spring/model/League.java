package org.d11.boot.spring.model;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
