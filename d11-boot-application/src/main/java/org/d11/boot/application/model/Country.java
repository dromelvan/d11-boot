package org.d11.boot.application.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A country that players can come from.
 */
@Data
@Entity
public class Country extends D11Entity {

    /**
     * ISO code length.
     */
    public static final int ISO_CODE_LENGTH = 2;
    /**
     * Id for the dummy country.
     */
    public static final long DUMMY_COUNTRY_ID = 1;

    /**
     * Country name.
     */
    @NotEmpty
    private String name;
    /**
     * Country ISO code.
     */
    @NotNull
    @Size(min = 2, max = 2)
    private String iso;

}
