package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Season;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Player season stat repository tests.
 */
class PlayerSeasonStatRepositoryTests extends D11BootRepositoryTests<PlayerSeasonStat, PlayerSeasonStatRepository> {

    /**
     * Creates new player match stat repository tests.
     */
    PlayerSeasonStatRepositoryTests() {
        super(PlayerSeasonStat.class);
    }

    @Override
    protected void beforeSave(final PlayerSeasonStat playerSeasonStat) {
        super.beforeSave(playerSeasonStat);
        playerSeasonStat.getPosition().setId(null);
        playerSeasonStat.getSeason().setId(null);
        playerSeasonStat.getPlayer().setId(null);
        playerSeasonStat.getPlayer().getCountry().setId(null);
        playerSeasonStat.getTeam().setId(null);
        playerSeasonStat.getTeam().getStadium().setId(null);
        playerSeasonStat.getD11Team().setId(null);
        playerSeasonStat.getD11Team().getOwner().setId(null);
        playerSeasonStat.getD11Team().getCoOwner().setId(null);
    }

    /**
     * Tests PlayerSeasonStatRepository::findByPlayerIdOrderBySeasonIdDesc.
     */
    @Test
    void testFindByPlayerIdOrderBySeasonIdDesc() {
        final List<PlayerSeasonStat> entities = getEntities();
        // Set the player for the first two entities to the same one to ensure we get a result with more than one
        // but not all player season stats.
        final Player player = entities.get(0).getPlayer();
        entities.get(1).setPlayer(player);

        getRepository().save(entities.get(1));

        final List<PlayerSeasonStat> expected = Arrays.asList(entities.get(0), entities.get(1));
        expected.sort(Comparator.comparingLong(playerSeasonStat -> -1 * playerSeasonStat.getSeason().getId()));

        final List<PlayerSeasonStat> result = getRepository().findByPlayerIdOrderBySeasonIdDesc(player.getId());

        assertNotNull(result, "PlayerSeasonStatRepository::findByPlayerIdOrderBySeasonIdDesc not null");
        assertEquals(expected, result, "PlayerSeasonStatRepository::findByPlayerIdOrderBySeasonIdDesc equals");
    }


    /**
     * Tests PlayerSeasonStatRepository::findBySeasonId.
     */
    @Test
    void testFindBySeasonId() {
        final List<PlayerSeasonStat> entities = getEntities();
        // Set the season for the first two entities to the same one to ensure we get a result with more than one
        // but not all player season stats.
        final Season season = entities.get(0).getSeason();
        entities.get(1).setSeason(season);

        getRepository().save(entities.get(1));

        final List<PlayerSeasonStat> expected = Arrays.asList(entities.get(0), entities.get(1));
        expected.sort(Comparator.naturalOrder());

        final List<PlayerSeasonStat> result = getRepository().findBySeasonId(season.getId());
        result.sort(Comparator.naturalOrder());

        assertNotNull(result, "PlayerSeasonStatRepository::findBySeasonId not null");
        assertEquals(expected, result, "PlayerSeasonStatRepository::findBySeasonId equals");
    }

}
