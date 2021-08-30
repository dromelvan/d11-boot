package org.d11.boot.application.model.jpa.util;

import org.d11.boot.application.model.jpa.PlayerMatchStat;
import org.d11.boot.application.model.jpa.Status;

/**
 * Version 1 of player match stat calculator implementation. This calculator is valid from the 2021-2022 season onward.
 */
public class PlayerMatchStatPointsCalculatorV1 implements PlayerMatchStatPointsCalculator {

    @Override
    @SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.NPathComplexity", "PMD.NcssCount", "PMD.AvoidLiteralsInIfCondition",
                        "checkstyle:CyclomaticComplexity", "checkstyle:NPathComplexity",
                        "checkstyle:MagicNumber", "checkstyle:NestedIfDepth", "checkstyle:JavaNCSS" })
    public int calculatePoints(final PlayerMatchStat playerMatchStat) {
        if(playerMatchStat.participated()) {
            int points = 0;

            // Each goal is +4 points, each own goal is -4 points, each assist = +2 points
            points += 4 * playerMatchStat.getGoals() - 4 * playerMatchStat.getOwnGoals();
            points += 2 * playerMatchStat.getGoalAssists();

            // Red card = -4 points, yellow card = -1 points
            if(playerMatchStat.getRedCardTime() > 0) {
                points -= 4;
            } else if(playerMatchStat.getYellowCardTime() > 0) {
                points -= 1;
            }

            // Defenders should get 4 points for a clean sheet and -1 point for every conceded goal after the first
            if(playerMatchStat.getPosition().isDefender()) {
                if(playerMatchStat.getGoalsConceded() == 0) {
                    points += 4;
                } else if(playerMatchStat.getGoalsConceded() >= 2) {
                    points -= playerMatchStat.getGoalsConceded() - 1;
                }
            }

            // Midfielders should get +1 points for a clean sheet
            if(playerMatchStat.getPosition().getId() == 4
                    && playerMatchStat.getGoalsConceded() == 0) {
                points += 1;
            }

            // Man of the match = +4 points, shared man of the match = +2 points
            if(playerMatchStat.isManOfTheMatch()) {
                points += 4;
            } else if(playerMatchStat.isSharedManOfTheMatch()) {
                points += 2;
            }

            // Add points for rating
            final int rating = playerMatchStat.getRating();
            if(rating >= 950) {
                points += 5;
            } else if(rating >= 850) {
                points += 3;
            } else if(rating >= 750) {
                points += 2;
            } else if(rating >= 650) {
                points += 1;
            } else if(rating >= 450 && rating < 550) {
                points -= 1;
            } else if(rating >= 350) {
                points -= 2;
            } else if(rating >= 250) {
                points -= 3;
            } else if(rating >= 150) {
                points -= 4;
            } else if(rating >= 50) {
                points -= 5;
            } else if(rating >= 1) {
                points -= 6;
            } else {
                points -= 7;
            }

            return points;
        } else if(playerMatchStat.getPosition().isDefender()
                  && !playerMatchStat.getMatch().getStatus().equals(Status.PENDING)) {
            return -1;
        }
        return 0;
    }

}
