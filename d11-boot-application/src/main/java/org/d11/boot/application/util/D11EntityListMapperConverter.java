package org.d11.boot.application.util;

import org.d11.boot.application.model.jpa.D11Entity;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Converts a list of D11 entities to a list of ids.
 */
public class D11EntityListMapperConverter extends AbstractConverter<List<D11Entity>, List<Long>> {

    @Override
    protected List<Long> convert(final List<D11Entity> d11Entities) {
        return d11Entities.stream().map(D11Entity::getId).collect(Collectors.toList());
    }

}
