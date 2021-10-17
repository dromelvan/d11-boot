package org.d11.boot.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;

/**
 * Base class for route builders.
 */
public abstract class AbstractRouteBuilder extends RouteBuilder {

    /**
     * Object mapper used to marshal/unmarshal JSON.
     */
    private final ObjectMapper objectMapper = new CamelObjectMapper();

    /**
     * Gets data format a specific class.
     *
     * @param clazz        The class for which a data format will be created.
     * @param indentOutput Set pretty printing active or inactive.
     * @return Data format for a specific class.
     */
    protected DataFormat getDataFormat(final Class<?> clazz, final boolean indentOutput) {
        final JacksonDataFormat jacksonDataFormat = new JacksonDataFormat(this.objectMapper, clazz);
        if(indentOutput) {
            // This will pretty print the output
            jacksonDataFormat.enableFeature(SerializationFeature.INDENT_OUTPUT);
        }
        return jacksonDataFormat;
    }

}
