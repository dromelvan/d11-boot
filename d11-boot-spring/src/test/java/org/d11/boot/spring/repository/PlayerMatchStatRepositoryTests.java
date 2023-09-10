package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.model.PlayerMatchStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Player match stat repository tests.
 */
class PlayerMatchStatRepositoryTests extends D11BootRepositoryTests<PlayerMatchStat, PlayerMatchStatRepository> {

    /**
     * Updates positions.
     */
    @Autowired
    private PositionRepository positionRepository;

    /**
     * Creates new player match stat repository tests.
     */
    PlayerMatchStatRepositoryTests() {
        super(PlayerMatchStat.class);
    }

    @Override
    protected void beforeSave(final PlayerMatchStat playerMatchStat) {
        super.beforeSave(playerMatchStat);
        playerMatchStat.getPosition().setId(null);
        playerMatchStat.getMatch().setId(null);
        playerMatchStat.getMatch().getMatchWeek().setId(null);
        playerMatchStat.getMatch().getMatchWeek().getSeason().setId(null);
        playerMatchStat.getMatch().getHomeTeam().setId(null);
        playerMatchStat.getMatch().getHomeTeam().getStadium().setId(null);
        playerMatchStat.getMatch().getAwayTeam().setId(null);
        playerMatchStat.getMatch().getAwayTeam().getStadium().setId(null);
        playerMatchStat.getMatch().getMatchWeek().setId(null);
        playerMatchStat.getMatch().getStadium().setId(null);
        playerMatchStat.getD11Match().setId(null);
        playerMatchStat.getD11Match().getMatchWeek().setId(null);
        playerMatchStat.getD11Match().getMatchWeek().getSeason().setId(null);
        playerMatchStat.getD11Match().getHomeD11Team().setId(null);
        playerMatchStat.getD11Match().getHomeD11Team().getOwner().setId(null);
        playerMatchStat.getD11Match().getHomeD11Team().getCoOwner().setId(null);
        playerMatchStat.getD11Match().getAwayD11Team().setId(null);
        playerMatchStat.getD11Match().getAwayD11Team().getOwner().setId(null);
        playerMatchStat.getD11Match().getAwayD11Team().getCoOwner().setId(null);
        playerMatchStat.getPlayer().setId(null);
        playerMatchStat.getPlayer().getCountry().setId(null);
        playerMatchStat.getTeam().setId(null);
        playerMatchStat.getTeam().getStadium().setId(null);
        playerMatchStat.getD11Team().setId(null);
        playerMatchStat.getD11Team().getOwner().setId(null);
        playerMatchStat.getD11Team().getCoOwner().setId(null);
    }

    /**
     * Tests PlayerMatchStatRepository::findByMatchIdOrderByPositionSortOrder.
     */
    @Test
    void testFindByMatchIdOrderByPositionSortOrder() {
        // Put half the stats in one match and the other half in another match and give them all increasing position
        // sort orders
        final List<PlayerMatchStat> entities = getEntities();
        final Match match1 = entities.get(0).getMatch();
        final Match match2 = entities.get(1).getMatch();

        entities.forEach(playerMatchStat -> {
            final int i = entities.indexOf(playerMatchStat);
            playerMatchStat.setMatch(i % 2 == 0 ? match1 : match2);
            playerMatchStat.getPosition().setSortOrder(i + 1);
        });

        this.positionRepository.saveAll(entities.stream().map(PlayerMatchStat::getPosition).toList());
        getRepository().saveAll(entities);

        // Check the first match
        final List<PlayerMatchStat> result1 = getRepository().findByMatchIdOrderByPositionSortOrder(match1.getId());
        final List<PlayerMatchStat> entities1 = entities.stream()
                .filter(playerMatchStat -> playerMatchStat.getMatch().equals(match1))
                .sorted(Comparator.comparingInt(playerMatchStat -> playerMatchStat.getPosition().getSortOrder()))
                .toList();

        assertNotNull(result1, "PlayerMatchStatRepository::findByMatchIdOrderByPositionSortOrder match 1 not null");
        assertFalse(result1.isEmpty(),
                    "PlayerMatchStatRepository::findByMatchIdOrderByPositionSortOrder match 1 empty");
        assertEquals(entities1, result1,
                     "PlayerMatchStatRepository::findByMatchIdOrderByPositionSortOrder match 1 equals");

        // Check the second match
        final List<PlayerMatchStat> result2 = getRepository().findByMatchIdOrderByPositionSortOrder(match2.getId());
        final List<PlayerMatchStat> entities2 = entities.stream()
                .filter(playerMatchStat -> playerMatchStat.getMatch().equals(match2))
                .sorted(Comparator.comparingInt(playerMatchStat -> playerMatchStat.getPosition().getSortOrder()))
                .toList();

        assertNotNull(result2, "PlayerMatchStatRepository::findByMatchIdOrderByPositionSortOrder match 2 not null");
        assertFalse(result2.isEmpty(),
                    "PlayerMatchStatRepository::findByMatchIdOrderByPositionSortOrder match 2 empty");
        assertEquals(entities2, result2,
                     "PlayerMatchStatRepository::findByMatchIdOrderByPositionSortOrder match 2 equals");
    }

    /**
     * Tests PlayerMatchStatRepository::findByD11MatchIdOrderByPositionSortOrder.
     */
    @Test
    void testFindByD11MatchIdOrderByPositionSortOrder() {
        // Do the same thing as with the match version of this query
        final List<PlayerMatchStat> entities = getEntities();
        final D11Match d11Match1 = entities.get(0).getD11Match();
        final D11Match d11Match2 = entities.get(1).getD11Match();

        entities.forEach(playerMatchStat -> {
            final int i = entities.indexOf(playerMatchStat);
            playerMatchStat.setD11Match(i % 2 == 0 ? d11Match1 : d11Match2);
            playerMatchStat.getPosition().setSortOrder(i + 1);
        });

        this.positionRepository.saveAll(entities.stream().map(PlayerMatchStat::getPosition).toList());
        getRepository().saveAll(entities);

        final List<PlayerMatchStat> result1 =
                getRepository().findByD11MatchIdOrderByPositionSortOrder(d11Match1.getId());
        final List<PlayerMatchStat> entities1 = entities.stream()
                .filter(playerMatchStat -> playerMatchStat.getD11Match().equals(d11Match1))
                .sorted(Comparator.comparingInt(playerMatchStat -> playerMatchStat.getPosition().getSortOrder()))
                .toList();

        assertNotNull(result1,
                      "PlayerMatchStatRepository::findByD11MatchIdOrderByPositionSortOrder D11 match 1 not null");
        assertFalse(result1.isEmpty(),
                    "PlayerMatchStatRepository::findByD11MatchIdOrderByPositionSortOrder D11 match 1 empty");
        assertEquals(entities1, result1,
                     "PlayerMatchStatRepository::findByD11MatchIdOrderByPositionSortOrder D11 match 1 equals");

        final List<PlayerMatchStat> result2 =
                getRepository().findByD11MatchIdOrderByPositionSortOrder(d11Match2.getId());
        final List<PlayerMatchStat> entities2 = entities.stream()
                .filter(playerMatchStat -> playerMatchStat.getD11Match().equals(d11Match2))
                .sorted(Comparator.comparingInt(playerMatchStat -> playerMatchStat.getPosition().getSortOrder()))
                .toList();

        assertNotNull(result2,
                      "PlayerMatchStatRepository::findByD11MatchIdOrderByPositionSortOrder D11 match 2 not null");
        assertFalse(result2.isEmpty(),
                    "PlayerMatchStatRepository::findByD11MatchIdOrderByPositionSortOrder D11 match 2 empty");
        assertEquals(entities2, result2,
                     "PlayerMatchStatRepository::findByD11MatchIdOrderByPositionSortOrder D11 match 2 equals");
    }

}
