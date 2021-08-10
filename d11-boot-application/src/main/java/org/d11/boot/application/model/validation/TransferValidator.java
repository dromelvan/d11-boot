package org.d11.boot.application.model.validation;

import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Position;
import org.d11.boot.application.model.Season;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Validates a proposed transfer (or transfer bid).
 */
public class TransferValidator {

    /**
     * Transfer fees/bids have to be divisible by this number.
     */
    private static final int FEE_DIVIDER = 5;

    /**
     * The season that provides the D11 team budget for the validation.
     */
    private final Season season;
    /**
     * Player season stats that provide current D11 squad value and position counts for the validation.
     */
    private final Collection<PlayerSeasonStat> playerSeasonStats;

    /**
     * Creates a new validator.
     *
     * @param season            The season that provides the D11 team budget for the validation.
     * @param playerSeasonStats Player season stats that provide current D11 squad value and position counts for
     *                          the validation.
     */
    public TransferValidator(final Season season, final Collection<PlayerSeasonStat> playerSeasonStats) {
        this.season = season;
        this.playerSeasonStats = playerSeasonStats;
    }

    /**
     * Validates a fee and position against the provided season and player season stats.
     *
     * @param fee      The fee that will be validated.
     * @param position The position that will be validated.
     * @return List of validation error messages.
     */
    public List<String> validate(final int fee, final Position position) {
        final List<String> errors = new ArrayList<>();
        final int maxFee = this.season.getD11TeamBudget()
                - this.playerSeasonStats.stream().mapToInt(PlayerSeasonStat::getValue).sum()
                - (11 - this.playerSeasonStats.size()) * 5;
        if(fee > maxFee) {
            errors.add(fee + " fee is greater than max fee " + maxFee + ".");
        }
        if(fee % FEE_DIVIDER != 0) {
            errors.add(fee + " fee is not divisible by 5.");
        }

        final int positionCount = (int) this.playerSeasonStats.stream()
                .filter(playerSeasonStat -> playerSeasonStat.getPosition().equals(position))
                .count();

        if(positionCount >= position.getMaxCount()) {
            errors.add("Position count for " + position.getName() + " is already " + position.getMaxCount());
        }
        return errors;
    }

}
