package org.d11.boot.spring.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * A Premier League team stadium.
 */
@Data
@Entity
public class Stadium extends D11Entity {

    /**
     * Minimum allowed value for the opened property.
     */
    public static final int MIN_OPENED_YEAR = 1000;

    /**
     * Maximum allowed value for the opened property.
     */
    public static final int MAX_OPENED_YEAR = 3000;

    /**
     * Stadium name.
     */
    @NotEmpty
    private String name;

    /**
     * Name of the city the stadium is located in.
     */
    @NotEmpty
    private String city;

    /**
     * The capacity of the stadium.
     */
    @Min(1)
    private int capacity;

    /**
     * The year the stadium was opened.
     */
    @Min(MIN_OPENED_YEAR)
    @Max(MAX_OPENED_YEAR)
    private int opened;

    /**
     * Name of the stadium photo file. This is null if no photo of the stadium exists.
     */
    private String photoFileName;

}
