package org.d11.boot.parser.model;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Parsed player data tests.
 */
class ParsedPlayerDataTests {

    /**
     * Test first name.
     */
    private static final String FIRST_NAME = "FirstName";

    /**
     * Test middle name.
     */
    private static final String MIDDLE_NAME = "MiddleName";

    /**
     * Test last name.
     */
    private static final String LAST_NAME = "Lastname";

    /**
     * Tests ParsedPlayerData::getFirstName.
     */
    @Test
    void testGetFirstName() {
        final ParsedPlayerData parsedPlayerData = new ParsedPlayerData();

        parsedPlayerData.setName(LAST_NAME);
        assertEquals(StringUtils.EMPTY, parsedPlayerData.getFirstName());

        parsedPlayerData.setName(String.join(ParsedPlayerData.NAME_DELIMITER, FIRST_NAME, LAST_NAME));
        assertEquals(FIRST_NAME, parsedPlayerData.getFirstName());

        parsedPlayerData.setName(
                String.join(ParsedPlayerData.NAME_DELIMITER, FIRST_NAME, MIDDLE_NAME, LAST_NAME));
        assertEquals(String.join(ParsedPlayerData.NAME_DELIMITER, FIRST_NAME, MIDDLE_NAME),
                     parsedPlayerData.getFirstName());
    }

    /**
     * Tests ParsedPlayerData::getLastName.
     */
    @Test
    void testGetLastName() {
        final ParsedPlayerData parsedPlayerData = new ParsedPlayerData();

        parsedPlayerData.setName(LAST_NAME);
        assertEquals(LAST_NAME, parsedPlayerData.getLastName());

        parsedPlayerData.setName(String.join(ParsedPlayerData.NAME_DELIMITER, FIRST_NAME, LAST_NAME));
        assertEquals(LAST_NAME, parsedPlayerData.getLastName());

        parsedPlayerData.setName(
                String.join(ParsedPlayerData.NAME_DELIMITER, FIRST_NAME, MIDDLE_NAME, LAST_NAME));
        assertEquals(LAST_NAME, parsedPlayerData.getLastName());
    }

}
