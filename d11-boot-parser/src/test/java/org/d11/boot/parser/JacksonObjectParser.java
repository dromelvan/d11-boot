package org.d11.boot.parser;

/**
 * Dummy parser implementation for testing the Jackson ObjectMapper serializing/deserializing in the AbstractParser.
 */
public class JacksonObjectParser extends AbstractParser<JacksonObject> {

    @Override
    protected JacksonObject doParse(final String input) {
        return new JacksonObject();
    }

}
