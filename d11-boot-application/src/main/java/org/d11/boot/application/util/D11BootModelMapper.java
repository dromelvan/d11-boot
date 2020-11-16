package org.d11.boot.application.util;

import org.modelmapper.ModelMapper;

/**
 * ModelMapper with adaptations for the D11 Boot application.
 */
public class D11BootModelMapper extends ModelMapper {

    /**
     * Creates a new mapper.
     */
    public D11BootModelMapper() {
        addConverter(new MatchesByDateConverter());
    }

}
