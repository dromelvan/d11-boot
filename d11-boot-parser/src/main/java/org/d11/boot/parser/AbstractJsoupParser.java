package org.d11.boot.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    /**
     * Gets the first child element of a class from a parent element.
     *
     * @param parentElement The parent element.
     * @param className     The class name.
     * @return The first child element of the class from the parent element.
     */
    protected Element getFirstChildElementByClass(final Element parentElement, final String className)
            throws ParserException {
        final Elements elements = parentElement.getElementsByClass(className);
        if (elements.isEmpty()) {
            throw new ParserException("No elements of class %s in parent element %s", className, parentElement);
        }
        return elements.first();
    }

}
