package org.d11.boot.application.util;

import org.d11.boot.application.model.jpa.D11Entity;
import org.modelmapper.AbstractConverter;

import java.util.List;

/**
 * Converts a list of D11 entities to its size.
 */
public class D11EntityListSizeMapperConverter extends AbstractConverter<List<D11Entity>, Integer> {

    @Override
    protected Integer convert(final List<D11Entity> d11Entities) {
        if(d11Entities == null) {
            return 0;
        }
        return d11Entities.size();
    }

}
