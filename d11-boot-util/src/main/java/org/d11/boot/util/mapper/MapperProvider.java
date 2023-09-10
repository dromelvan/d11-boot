package org.d11.boot.util.mapper;

import lombok.AccessLevel;
import lombok.Getter;
import org.d11.boot.util.mapper.modelmapper.ModelMapperMapper;

/**
 * Base class for classes that need access to an implementation of Mapper.
 */
public class MapperProvider {

    /**
     * Mapper implementation.
     */
    @Getter(AccessLevel.PROTECTED)
    private final Mapper mapper = new ModelMapperMapper();

}
