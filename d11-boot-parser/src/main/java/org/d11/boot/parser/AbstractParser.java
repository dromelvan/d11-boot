package org.d11.boot.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Base class for parsing external resources.
 *
 * @param <T> Class of the output result of the parsing.
 */
public abstract class AbstractParser<T extends Object> implements Parser<T> {

    /**
     * Gson instance for parsing JSON content.
     */
    @Getter(AccessLevel.PROTECTED)
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

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
     * Implement in subclasses to do the actual parsing.
     *
     * @param input The input that will be parsed.
     * @return The result of the parse.
     * @throws ParserException If the parse fails in an unrecoverable way.
     */
    protected abstract T doParse(String input) throws ParserException;

}
