package org.d11.boot.parser;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests AbstractParser.
 */
@SuppressWarnings({ "checkstyle:MagicNumber", "checkstyle:AbstractClassName" })
class AbstractParserTests {

    /**
     * JSON string for testing.
     */
    private static final String JSON_STRING =
            """
            {"stringValue":"stringValue","intValue":1,"booleanValue":true,\
            "dateTime":"1970-01-01T00:00:00","listValue":["A","B","C"]}\
            """;

    /**
     * Dummy object for testing.
     */
    private final JacksonObject jacksonObject = JacksonObject.builder()
            .stringValue("stringValue")
            .intValue(1)
            .booleanValue(true)
            .dateTime(LocalDateTime.of(1970, 1, 1, 0, 0))
            .listValue(Arrays.asList("A", "B", "C"))
            .build();

    /**
     * Tests AbstractParser::serialize.
     */
    @Test
    void testSerialize() throws ParserException {
        final JacksonObjectParser jacksonObjectParser = new JacksonObjectParser();
        final String result = jacksonObjectParser.serialize(this.jacksonObject);

        assertFalse(StringUtils.isBlank(result), "AbstractParser::serialize blank");
        assertEquals(JSON_STRING, result, "AbstractParser::serialize equals");
    }

    /**
     * Tests AbstractParser::deserialize.
     */
    @Test
    void testDeserialize() throws ParserException {
        final JacksonObjectParser jacksonObjectParser = new JacksonObjectParser();
        final JacksonObject result = jacksonObjectParser.deserialize(JSON_STRING, JacksonObject.class);

        assertNotNull(result, "AbstractParser::deserialize not null");
        assertEquals(this.jacksonObject.getStringValue(), result.getStringValue(),
                     "AbstractParser::serialize stringValue equals");
        assertEquals(this.jacksonObject.getIntValue(), result.getIntValue(),
                     "AbstractParser::serialize intValue equals");
        assertEquals(this.jacksonObject.isBooleanValue(), result.isBooleanValue(),
                     "AbstractParser::serialize booleanValue equals");
        assertEquals(this.jacksonObject.getDateTime(), result.getDateTime(),
                     "AbstractParser::serialize datetime equals");
        assertEquals(this.jacksonObject.getListValue(), result.getListValue(),
                     "AbstractParser::serialize listValue equals");
    }

}
