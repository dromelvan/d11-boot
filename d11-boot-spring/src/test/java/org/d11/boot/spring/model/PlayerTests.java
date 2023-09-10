package org.d11.boot.spring.model;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Parameterizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player tests.
 */
class PlayerTests extends EasyRandomTests {

    /**
     * Tests Player::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final Player player = generate(Player.class);

        player.prePersist();

        assertTrue(player.isValid(), "Player::isValid");

        player.setCountry(null);
        assertFalse(player.isValid(), "Player::isValid country null");
        player.setCountry(new Country());

        player.setWhoscoredId(-1);
        assertFalse(player.isValid(), "Player::isValid WhoScored id negative");
        player.setWhoscoredId(1);

        player.setPremierLeagueId(-1);
        assertFalse(player.isValid(), "Player::isValid Premier League id negative");
        player.setPremierLeagueId(1);

        player.setFirstName(null);
        assertFalse(player.isValid(), "Player::isValid first name null");
        player.setFirstName("");
        assertTrue(player.isValid(), "Player::isValid first name empty");
        player.setFirstName("Firstname");

        player.setLastName(null);
        assertFalse(player.isValid(), "Player::isValid last name null");
        player.setLastName("");
        assertFalse(player.isValid(), "Player::isValid last name empty");
        player.setLastName("Lastname");

        player.setHeight(-1);
        assertFalse(player.isValid(), "Player::isValid height negative");
        player.setHeight(1);

        player.prePersist();
        assertTrue(player.isValid(), "Player::isValid valid");
    }

    /**
     * Tests Player::getName.
     */
    @Test
    void testGetName() {
        final Player player = generate(Player.class);

        assertEquals(String.format("%s %s", player.getFirstName().trim(), player.getLastName()).trim(),
                     player.getName(),
                     "Player::testGetName equals");
    }

    /**
     * Tests Player::getShortName.
     */
    @Test
    void testGetShortName() {
        final Player player = generate(Player.class);

        assertEquals(String.format("%s %c", player.getLastName(), player.getFirstName().charAt(0)).trim(),
                     player.getShortName(),
                    "Player::testGetShortName first name not empty equals");

        player.setFirstName(StringUtils.EMPTY);

        assertEquals(player.getLastName(), player.getShortName(), "Player::testGetShortName first name empty equals");
    }

    /**
     * Tests Player::prePersist.
     */
    @Test
    void testPrePersist() {
        final Player player = generate(Player.class);
        player.setParameterizedName(null);

        player.prePersist();

        assertNotNull(player.getParameterizedName(), "Player::prePersist parameterized name not null");
        assertEquals(Parameterizer.parameterize(player.getName()),
                     player.getParameterizedName(),
                     "Player::prePersist parameterized name equals");
    }

    /**
     * Tests Player::preUpdate.
     */
    @Test
    void testPreUpdate() {
        final Player player = generate(Player.class);
        player.setParameterizedName(null);

        player.preUpdate();

        assertNotNull(player.getParameterizedName(), "Player::preUpdate parameterized name not null");
        assertEquals(Parameterizer.parameterize(player.getName()),
                player.getParameterizedName(),
                "Player::preUpdate parameterized name equals");
    }

}
