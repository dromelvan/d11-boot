package org.d11.boot.spring.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
    @Size(min = ISO_CODE_LENGTH, max = ISO_CODE_LENGTH)
    private String iso;

}
