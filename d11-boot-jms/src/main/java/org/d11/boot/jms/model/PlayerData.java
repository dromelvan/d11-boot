package org.d11.boot.jms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Data for a player in a parsed player or team squad page.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerData extends JmsModel {

    /**
     * Delimiter used for deriving first and last name from the name property.
     */
    public static final String NAME_DELIMITER = " ";

    /**
     * Player id on the site the player was parsed from.
     */
    private Integer id;
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
        if(lastIndex > 0) {
            return this.name.substring(0, lastIndex).trim();
        }
        return "";
    }

    /**
     * Player last name, derived from the name property.
     *
     * @return Player last name or the player name if no last name could be derived.
     */
    @JsonIgnore
    public String getLastName() {
        final int lastIndex = this.name.lastIndexOf(NAME_DELIMITER);
        if(lastIndex > 0) {
            return this.name.substring(lastIndex).trim();
        }
        return this.name;
    }

}
