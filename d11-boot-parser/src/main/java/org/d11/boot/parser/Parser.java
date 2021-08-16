package org.d11.boot.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Parses input and outputs an object representing the input.
 *
 * @param <T> The class of the output.
 */
public interface Parser<T extends Object> {

    /**
     * Parses a String input and outputs and object representing the input.
     *
     * @param input The input that will be parsed.
     * @return Object representing the input.
     * @throws ParserException If parsing fails in an unrecoverable way.
     */
    T parse(String input) throws ParserException;

    /**
     * Parses a String input and outputs and object representing the input.
     *
     * @param file The input file that will be parsed.
     * @return Object representing the input.
     * @throws ParserException If parsing fails in an unrecoverable way.
     */
    T parse(File file) throws IOException, ParserException;

    /**
     * Parses a String input and outputs and object representing the input.
     *
     * @param inputStream The input stream that will be parsed.
     * @return Object representing the input.
     * @throws ParserException If parsing fails in an unrecoverable way.
     */
    T parse(InputStream inputStream) throws ParserException;

}
