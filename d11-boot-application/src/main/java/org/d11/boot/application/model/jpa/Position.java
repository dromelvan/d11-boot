package org.d11.boot.application.model.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * A player position.
 */
@Data
@Entity
public class Position extends D11Entity implements Comparable<Position> {

    /**
     * Max position code length.
     */
    public static final int MAX_CODE_LENGTH = 3;
    /**
     * Maximum maxCount value allowed.
     */
    public static final int MAX_MAX_COUNT = 4;

    /**
     * Position name.
     */
    @NotBlank
    private String name;
    /**
     * Position 1-3 letter code.
     */
    @NotNull
    @Size(min = 1, max = MAX_CODE_LENGTH)
    private String code;
    /**
     * Max number of players of this position allowed in a D11 team.
     */
    @Min(0)
    @Max(MAX_MAX_COUNT)
    private int maxCount;
    /**
     * Indicates a position that should be considered a defender for point calculation purposes.
     */
    @NotNull
    private boolean defender;
    /**
     * Position sort order.
     */
    @Positive
    private int sortOrder;

    /**
     * Sorts positions according to sort order.
     *
     * @param position The position that will be compared to this position.
     * @return Sort order of this position minus the sort order of the other position.
     */
    @Override
    public int compareTo(final Position position) {
        return getSortOrder() - position.getSortOrder();
    }

}
