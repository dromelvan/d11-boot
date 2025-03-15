package org.d11.boot.spring.util;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.PlayerTransferContextId;
import org.d11.boot.spring.model.Position;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.d11.boot.spring.model.User;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.api.Randomizer;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;

/**
 * Player transfer context randomizer that builds a random player transfer context.
 */
public class PlayerTransferContextRandomizer implements Randomizer<PlayerTransferContext> {

    /**
     * Randomizer used to create players, teams, etc.
     */
    private static final EasyRandom EASY_RANDOM = new EasyRandom(new D11BootEasyRandomParameters());

    @Override
    public PlayerTransferContext getRandomValue() {
        final Player player = EASY_RANDOM.nextObject(Player.class);
        final Position position = EASY_RANDOM.nextObject(Position.class);
        final D11Team playerD11Team = EASY_RANDOM.nextObject(D11Team.class);
        final Season season = EASY_RANDOM.nextObject(Season.class);
        final TransferDay transferDay = EASY_RANDOM.nextObject(TransferDay.class);
        final TransferListing transferListing = EASY_RANDOM.nextObject(TransferListing.class);
        final TransferBid transferBid = EASY_RANDOM.nextObject(TransferBid.class);
        final D11Team d11Team = EASY_RANDOM.nextObject(D11Team.class);

        final User owner = EASY_RANDOM.nextObject(User.class);
        final User coOwner = EASY_RANDOM.nextObject(User.class);

        final int ranking = new IntegerRangeRandomizer(1, PlayerTransferContext.MAX_RANKING).getRandomValue();
        final int playerCount = new IntegerRangeRandomizer(0, PlayerTransferContext.MAX_PLAYER_COUNT).getRandomValue();

        final PlayerTransferContextId id = new PlayerTransferContextId(player.getId(), d11Team.getId());

        return new PlayerTransferContext(
                id,
                owner.getId(),
                coOwner.getId(),
                ranking,
                playerCount,
                season.getD11TeamBudget(),
                season.getD11TeamMaxTransfers(),
                position.getMaxCount(),
                player,
                position,
                playerD11Team,
                season,
                transferDay,
                transferListing,
                transferBid,
                d11Team
        );
    }

}
