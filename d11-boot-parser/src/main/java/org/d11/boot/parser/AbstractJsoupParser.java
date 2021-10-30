package org.d11.boot.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Base class for parsing Jsoup Documents.
 *
 * @param <T> Class of the output result of the parsing.
 */
public abstract class AbstractJsoupParser<T> extends AbstractParser<T> {

    @Override
    protected T doParse(final String input) throws ParserException {
        return doParse(Jsoup.parse(input));
    }

    /**
     * Implement in subclasses to do the actual parsing.
     *
     * @param document The document that will be parsed.
     * @return The result of the parse.
     * @throws ParserException If the parse fails in an unrecoverable way.
     */
    protected abstract T doParse(Document document) throws ParserException;

}
