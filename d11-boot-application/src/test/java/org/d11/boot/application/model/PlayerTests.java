package org.d11.boot.application.model;

import org.d11.boot.api.model.PlayerDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player tests.
 */
public class PlayerTests extends D11EasyRandomTests {

    /**
     * Tests player validity.
     */
    @Test
    @SuppressWarnings({ "checkstyle:ExecutableStatementCount", "checkstyle:JavaNCSS" })
    public void isValid() {
        final Player player = generate(Player.class);

        player.prePersist();

        assertTrue(player.isValid(), "New player should be valid.");

        player.setCountry(null);
        assertFalse(player.isValid(), "Null country should not be valid.");
        player.setCountry(new Country());

        player.setWhoscoredId(-1);
        assertFalse(player.isValid(), "Negative WhoScored id should not be valid.");
        player.setWhoscoredId(1);

        player.setFirstName(null);
        assertFalse(player.isValid(), "Null first name should not be valid.");
        player.setFirstName("");
        assertTrue(player.isValid(), "Empty first name should be valid.");
        player.setFirstName("Firstname");

        player.setLastName(null);
        assertFalse(player.isValid(), "Null last name should not be valid.");
        player.setLastName("");
        assertFalse(player.isValid(), "Empty last name should not be valid.");
        player.setLastName("Lastname");

        assertEquals(String.format("%s %s", player.getFirstName(), player.getLastName()).trim(), player.getName(),
                     "Name should be correct.");
        assertEquals(String.format("%s %c", player.getLastName(), player.getFirstName().charAt(0)).trim(), player.getShortName(),
                     "ShortName should be correct.");
        player.setFirstName("");
        assertEquals(player.getLastName().trim(), player.getName(), "Name with empty first name should be correct.");
        assertEquals(player.getLastName().trim(), player.getShortName(), "ShortName with empty first name should be correct.");
        player.setFirstName("Name");

        player.setDateOfBirth(null);
        assertFalse(player.isValid(), "Null date of birth should not be valid.");
        player.setDateOfBirth(LocalDate.now());

        player.setHeight(-1);
        assertFalse(player.isValid(), "Negative height should not be valid.");
        player.setHeight(1);

        player.prePersist();
        assertTrue(player.isValid(), "Player should be valid.");

        player.setFirstName("First");
        player.setLastName("Last");
        player.prePersist();
        assertEquals(player.getParameterizedName(), "first-last", "First Last parameterized should be first-last.");

        player.setFirstName("");
        player.setLastName("Jordão");
        player.prePersist();
        assertEquals(player.getParameterizedName(), "jordao", "Jordão parameterized should be jordao.");

        player.setFirstName("Bonds");
        player.setLastName("N'Gala");
        player.prePersist();
        assertEquals(player.getParameterizedName(), "bonds-n-gala", "Bonds N'Gala parameterized should be bonds-n-gala");

        player.setFirstName("Daniel");
        player.setLastName("Tözer");
        player.prePersist();
        assertEquals(player.getParameterizedName(), "daniel-tozer", "Daniel Tözer parameterized daniel-tozer");

        player.setFirstName("Shaun");
        player.setLastName("Wright-Phillips");
        player.prePersist();
        assertEquals(player.getParameterizedName(), "shaun-wright-phillips", "Shaun Wright-Phillips parameterized should be shaun-wright-phillips");
    }

    /**
     * Tests mapping between Player and PlayerDTO.
     */
    @Test
    public void map() {
        final Player player = generate(Player.class);

        final PlayerDTO playerDTO = map(player, PlayerDTO.class);
        final Player mappedPlayer = map(playerDTO, Player.class);

        assertEquals(player, mappedPlayer, "Player should equal mapped player.");
    }

}
