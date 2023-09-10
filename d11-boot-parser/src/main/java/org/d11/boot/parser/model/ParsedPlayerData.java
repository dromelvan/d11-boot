package org.d11.boot.parser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

/**
 * Data for a player in a parsed player or team squad page.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParsedPlayerData extends ParsedData {

    /**
     * Delimiter used for deriving first and last name from the name property.
     */
    public static final String NAME_DELIMITER = StringUtils.SPACE;

    /**
     * D11 player id. This is not set by the parser, but it might be a useful property to set when handling parsed
     * player data.
     */
    private Long id;

    /**
     * Player id on whatever site the player data was parsed from.
     */
    private Long siteId;

    /**
     * Player name.
     */
    private String name;

    /**
     * Player shirt number.
     */
    private Integer shirtNumber;

    /**
     * Player position.
     */
    private String position;

    /**
     * Player nationality.
     */
    private String nationality;

    /**
     * Player photo id.
     */
    private String photoId;

    /**
     * Player date of birth.
     */
    private LocalDate dateOfBirth;

    /**
     * Player height.
     */
    private Integer height;

    /**
     * Player first name, derived from the name property.
     *
     * @return Player first name or an empty string if no first name could be derived.
     */
    @JsonIgnore
    public String getFirstName() {
        final int lastIndex = this.name.lastIndexOf(NAME_DELIMITER);
        if (lastIndex > 0) {
            return this.name.substring(0, lastIndex).trim();
        }
        return StringUtils.EMPTY;
    }

    /**
     * Player last name, derived from the name property.
     *
     * @return Player last name or the player name if no last name could be derived.
     */
    @JsonIgnore
    public String getLastName() {
        final int lastIndex = this.name.lastIndexOf(NAME_DELIMITER);
        if (lastIndex > 0) {
            return this.name.substring(lastIndex).trim();
        }
        return this.name;
    }

}
