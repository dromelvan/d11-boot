package org.d11.boot.util.mapper.modelmapper;

import org.d11.boot.util.mapper.Mapper;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Model mapper implementation of the Mapper interface.
 */
public class ModelMapperMapper extends ModelMapper implements Mapper {

    @Override
    public <T> T map(final Object object, final Class<T> destinationClass) {
        return super.map(object, destinationClass);
    }

    @Override
    public <U, T> List<U> map(final List<T> objects, final Class<U> clazz) {
        return objects.stream()
            .map(entity -> map(entity, clazz))
            .collect(Collectors.toList());
    }

}
