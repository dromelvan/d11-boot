package org.d11.boot.application.service.camel;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.application.model.Country;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Position;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.model.TransferListing;
import org.d11.boot.application.repository.CountryRepository;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.PositionRepository;
import org.d11.boot.application.repository.TeamRepository;
import org.d11.boot.application.repository.TransferListingRepository;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.application.util.Parameterizer;
import org.d11.boot.jms.model.PlayerData;
import org.d11.boot.jms.model.TeamData;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service for updating a team squad from parsed team data.
 */
@Slf4j
public class UpdateSquadService extends CamelService {

    /**
     * Position name to position map.
     */
    private Map<String, Position> positionMap;

    /**
     * Updates a team squad from parsed team data.
     *
     * @param teamData Parsed team data that will be used to update the team squad.
     */
    @Transactional
    @SuppressWarnings({"PMD.AvoidLiteralsInIfCondition", "PMD.ExcessiveMethodLength",
                        "checkstyle:ExecutableStatementCount", "checkstyle:JavaNCSS"})
    public void updateSquad(final TeamData teamData) {
        log.info("Updating squad for team {}.", teamData.getName());

        // Set up values and maps.
        final Team team = getRepository(TeamRepository.class).findByPremierLeagueId(teamData.getId())
                .orElseThrow(NotFoundException::new);

        final Season season = getCurrentSeason();
        final List<PlayerSeasonStat> currentSquad = getRepository(PlayerSeasonStatRepository.class).findByTeamIdAndSeasonId(team.getId(), season.getId());

        final List<PlayerData> inputSquad = new ArrayList<>(teamData.getPlayers());
        final Map<String, Integer> nameToIdMap = inputSquad.stream()
                .collect(Collectors.toMap(p -> Parameterizer.parameterize(p.getName()), PlayerData::getId, (id1, id2) -> {
                    // It's strange that this can happen but apparently it can
                    log.warn("Duplicate Premier League ids {} and {} in player data.", id1, id2);
                    return id1;
                }));

        // Update missing Premier League ids for the players in the old squad that we can find in the new squad.
        currentSquad.stream().map(PlayerSeasonStat::getPlayer).filter(p -> p.getPremierLeagueId() == 0).forEach(player -> {
            player.setPremierLeagueId(nameToIdMap.getOrDefault(player.getParameterizedName(), 0));
            log.info("Set {} Premier League id to {}.", player.getName(), player.getPremierLeagueId());
        });

        // Set up some maps for the current and input squads.
        final Map<Integer, PlayerSeasonStat> currentSquadMap = currentSquad.stream()
                .filter(pss -> pss.getPlayer().getPremierLeagueId() > 0)
                .collect(Collectors.toMap(pss -> pss.getPlayer().getPremierLeagueId(), Function.identity()));

        final Map<Integer, PlayerData> inputSquadMap = teamData.getPlayers().stream()
                .collect(Collectors.toMap(PlayerData::getId, Function.identity(), (playerData1, playerData2) -> {
                    log.warn("Duplicate player data id {} for players {} and {}.", playerData1.getId(), playerData1.getName(), playerData2.getName());
                    return playerData1;
                }));

        // Go through the current squad and update positions for players where position has changed and remove players
        // that are not in the input squad from the team.
        final Team dummyTeam = getDummyTeam();
        currentSquad.forEach(playerSeasonStat -> {
            if(inputSquadMap.containsKey(playerSeasonStat.getPlayer().getPremierLeagueId())) {
                final PlayerData playerData = inputSquadMap.get(playerSeasonStat.getPlayer().getPremierLeagueId());
                playerSeasonStat.setShirtNumber(playerData.getShirtNumber());
                updatePosition(playerSeasonStat, playerData);
            } else {
                if(playerSeasonStat.getD11Team().isDummy()) {
                    playerSeasonStat.setTeam(dummyTeam);
                    log.info("Removed player {} from team {}.", playerSeasonStat.getPlayer().getName(), team.getName());
                } else {
                    log.warn("Player {} belongs to D11 team {} and could not be removed from team {}.",
                             playerSeasonStat.getPlayer().getName(),
                             playerSeasonStat.getD11Team().getName(),
                             team.getName());
                }
            }
        });

        // Remove all players that are already in the current squad from the input squad since we don't have to add any of them.
        inputSquad.removeIf(p -> currentSquadMap.containsKey(p.getId()));

        // Go through the remaining input squad. If a player doesn't yet exist we create a new player and add them the team.
        // If a player does exist we move them to the team and update their position if necessary and allowed.
        final D11Team dummyD11Team = getDummyD11Team();
        final Country dummyCountry = getDummyCountry();

        inputSquad.forEach(playerData -> {
            final PlayerRepository playerRepository = getRepository(PlayerRepository.class);
            // Find player by Premier League id. If no match then find player by parameterized name
            final List<Player> players = new ArrayList<>();
            playerRepository.findByPremierLeagueId(playerData.getId()).ifPresentOrElse(
                    players::add,
                    () -> players.addAll(playerRepository.findByParameterizedName(Parameterizer.parameterize(playerData.getName())))
            );

            if(players.isEmpty()) {
                // No matches, create a new player.
                Player player = new Player();
                final Country country = getRepository(CountryRepository.class).findByName(playerData.getNationality()).orElseGet(() -> {
                    log.warn("Country {} not found for player {}.", playerData.getNationality(), playerData.getName());
                    return dummyCountry;
                });
                player.setPremierLeagueId(playerData.getId());
                player.setFirstName(playerData.getFirstName());
                player.setLastName(playerData.getLastName());
                player.setCountry(country);
                player = getRepository(PlayerRepository.class).save(player);

                final Position position = getPosition(playerData.getPosition());
                final PlayerSeasonStat playerSeasonStat = new PlayerSeasonStat(player, season, team, dummyD11Team, position);
                getRepository(PlayerSeasonStatRepository.class).save(playerSeasonStat);

                log.info("Created new player {} for team {} with position {}.", playerData.getName(), team.getName(), position.getName());
            } else if(players.size() == 1) {
                // One match, move the player to the team.
                final Player player = players.get(0);
                // Update player Premier League id if we can.
                if(player.getPremierLeagueId() == 0) {
                    player.setPremierLeagueId(playerData.getId());
                }
                try {
                    // If PlayerSeasonStat already exists, update that.
                    final PlayerSeasonStat playerSeasonStat = getRepository(PlayerSeasonStatRepository.class)
                            .findByPlayerIdAndSeasonId(player.getId(), season.getId()).orElseThrow(NotFoundException::new);
                    updatePosition(playerSeasonStat, playerData);
                    playerSeasonStat.setTeam(team);
                    playerSeasonStat.setShirtNumber(playerData.getShirtNumber());
                    log.info("Moved player {} to team {} with position {}.", player.getName(), team.getName(), playerSeasonStat.getPosition().getName());
                } catch(NotFoundException e) {
                    // If PlayerSeasonStat does not exist, create a new one.
                    final Position position = getPosition(playerData.getPosition());
                    final PlayerSeasonStat playerSeasonStat = new PlayerSeasonStat(player, season, team, dummyD11Team, position);
                    playerSeasonStat.setShirtNumber(playerData.getShirtNumber());
                    player.getPlayerSeasonStats().add(playerSeasonStat);
                    log.info("Moved player {} to team {} with position {} and added player season stats.",
                             player.getName(), team.getName(), position.getName());
                }
            } else {
                // If two or more players with the same name exist we'll need manual handling.
                log.info("Found {} matches for player {}.", players.size(), playerData.getName());
            }
        });
        log.info("Squad update for team {} completed.", teamData.getName());
    }

    /**
     * Updates position for a player if the new position differs from the current on and the player has not yet been
     * available for transfer the current season.
     *
     * @param playerSeasonStat Current player season data.
     * @param playerData       New player data with the new position.
     */
    private void updatePosition(final PlayerSeasonStat playerSeasonStat, final PlayerData playerData) {
        final Position position = getPosition(playerData.getPosition());
        if(!playerSeasonStat.getPosition().equals(position)) {
            // Only change position for players if they haven't yet been available for transfer this season.
            final List<TransferListing> transferListings = getRepository(TransferListingRepository.class)
                    .findByTransferDayTransferWindowMatchWeekSeasonIdAndPlayerId(playerSeasonStat.getSeason().getId(), playerSeasonStat.getPlayer().getId());
            if(transferListings.isEmpty()) {
                playerSeasonStat.setPosition(position);
                log.info("Changed position for player {} to {}.", playerSeasonStat.getPlayer().getName(), position.getName());
            } else {
                log.warn("Previously available for transfer player {} position could not be changed from {} to {}.",
                         playerSeasonStat.getPlayer().getName(),
                         playerSeasonStat.getPosition().getName(),
                         position.getName());
            }
        }
    }

    /**
     * Gets a position with a specific name.
     *
     * @param position The name of the position that should be looked up.
     * @return The position with the provided name or the 'Unknown' position if the position does not exist.
     */
    private Position getPosition(final String position) {
        if(this.positionMap == null) {
            this.positionMap = getRepository(PositionRepository.class).findAll().stream()
                    .collect(Collectors.toMap(Position::getName, Function.identity()));
        }
        return this.positionMap.getOrDefault(position, this.positionMap.get("Unknown"));
    }

}
