package org.d11.boot.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

/**
 * Base class for parsing external resources.
 *
 * @param <T> Class of the output result of the parsing.
 */
public abstract class AbstractParser<T> implements Parser<T> {

    /**
     * Href attribute name.
     */
    protected static final String HREF = "href";

    /**
     * ObjectMapper instance for serializing and deserializing JSON content.
     */
    @Getter(AccessLevel.PROTECTED)
    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES.mappedFeature())
            .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES.mappedFeature())
            .enable(JsonReadFeature.ALLOW_MISSING_VALUES.mappedFeature())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());

    @Override
    public T parse(final String input) throws ParserException {
        return doParse(input);
    }

    @Override
    public T parse(final File file) throws IOException, ParserException {
        return parse(Files.newInputStream(file.toPath()));
    }

    @Override
    public T parse(final InputStream inputStream) throws ParserException {
        final String input = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        return doParse(input);
    }

    /**
     * Serializes an object into a JSON string.
     *
     * @param object The object.
     * @return Object representation of the JSON string.
     * @throws ParserException If something goes wrong when serializing the JSON string.
     */
    protected String serialize(final Object object) throws ParserException {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new ParserException(e);
        }
    }

    /**
     * Deserializes a JSON string to an object.
     *
     * @param json        The JSON string.
     * @param objectClass Class of the object the JSON string will be serialized to.
     * @param <U>         Type of the object class.
     * @return Object representation of the JSON string.
     * @throws ParserException If something goes wrong when serializing the JSON string.
     */
    protected <U> U deserialize(final String json, final Class<U> objectClass) throws ParserException {
        try {
            return this.objectMapper.readValue(json, objectClass);
        } catch (final JsonProcessingException e) {
            throw new ParserException(e);
        }
    }

    /**
     * Implement in subclasses to do the actual parsing.
     *
     * @param input The input that will be parsed.
     * @return The result of the parse.
     * @throws ParserException If the parse fails in an unrecoverable way.
     */
    protected abstract T doParse(String input) throws ParserException;

}
