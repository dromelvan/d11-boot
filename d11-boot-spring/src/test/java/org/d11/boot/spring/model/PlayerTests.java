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
    void testIsValid() {
        final Player player = generate(Player.class);

        player.prePersist();

        assertTrue(player.isValid());

        player.setCountry(null);
        assertFalse(player.isValid());
        player.setCountry(new Country());

        player.setWhoscoredId(-1);
        assertFalse(player.isValid());
        player.setWhoscoredId(1);

        player.setPremierLeagueId(-1);
        assertFalse(player.isValid());
        player.setPremierLeagueId(1);

        player.setFirstName(null);
        assertFalse(player.isValid());
        player.setFirstName("");
        assertTrue(player.isValid());
        player.setFirstName("Firstname");

        player.setLastName(null);
        assertFalse(player.isValid());
        player.setLastName("");
        assertFalse(player.isValid());
        player.setLastName("Lastname");

        player.setHeight(-1);
        assertFalse(player.isValid());
        player.setHeight(1);

        player.prePersist();
        assertTrue(player.isValid());
    }

    /**
     * Tests Player::getName.
     */
    @Test
    void testGetName() {
        final Player player = generate(Player.class);

        assertEquals(String.format("%s %s", player.getFirstName().trim(), player.getLastName()).trim(),
                     player.getName());
    }

    /**
     * Tests Player::getShortName.
     */
    @Test
    void testGetShortName() {
        final Player player = generate(Player.class);

        assertEquals(String.format("%s %c", player.getLastName(), player.getFirstName().charAt(0)).trim(),
                     player.getShortName());

        player.setFirstName(StringUtils.EMPTY);

        assertEquals(player.getLastName(), player.getShortName());
    }

    /**
     * Tests Player::prePersist.
     */
    @Test
    void testPrePersist() {
        final Player player = generate(Player.class);
        player.setParameterizedName(null);

        player.prePersist();

        assertNotNull(player.getParameterizedName());
        assertEquals(Parameterizer.parameterize(player.getName()), player.getParameterizedName());
    }

    /**
     * Tests Player::preUpdate.
     */
    @Test
    void testPreUpdate() {
        final Player player = generate(Player.class);
        player.setParameterizedName(null);

        player.preUpdate();

        assertNotNull(player.getParameterizedName());
        assertEquals(Parameterizer.parameterize(player.getName()), player.getParameterizedName());
    }

}
