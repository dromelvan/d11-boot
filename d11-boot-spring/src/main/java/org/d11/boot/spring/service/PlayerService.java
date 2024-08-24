package org.d11.boot.spring.service;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.util.Parameterizer;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Player service.
 */
@Service
public class PlayerService extends RepositoryService<Player, PlayerRepository> {

    /**
     * Patter for matching exact search strings, i.e. ones that are enclosed in quotes.
     */
    public static final Pattern EXACT_SEARCH_PATTERN = Pattern.compile("(\"|%2522)(.*)(\"|%2522)");

    /**
     * SQL 'like' string.
     */
    public static final String SQL_LIKE = "%";

    /**
     * Creates a player service.
     *
     * @param playerRepository The repository the service will use.
     */
    @Autowired
    public PlayerService(final PlayerRepository playerRepository) {
        super(Player.class, playerRepository);
    }

    /**
     * Updates a player.
     *
     * @param player Player with properties that will be updated.
     * @return The updated player.
     */
    public Player updatePlayer(final Player player) {
        if (!player.isValid()) {
            throw new BadRequestException("Invalid player", player.getValidationErrors());
        }

        final Player entity = getById(player.getId());

        map(player, entity);

        return save(entity);
    }

    /**
     * Gets players by matching name. If name is enclosed in quotes then only exact matches will be returned.
     *
     * @param name The name to search for.
     * @return Player search results by name.
     */
    public List<PlayerSearchResult> searchByName(final String name) {
        if (StringUtils.isBlank(name)) {
            throw new BadRequestException("name", "is missing");
        }

        final Matcher exactPatternMatcher = EXACT_SEARCH_PATTERN.matcher(name);
        if (exactPatternMatcher.matches()) {
            final String parameterizedName = Parameterizer.parameterize(exactPatternMatcher.group(2));
            return getJpaRepository().findByParameterizedNameExact(parameterizedName);
        } else {

            final String searchParameter = Arrays.stream(name.split("[ +]"))
                    .map(Parameterizer::parameterize)
                    .collect(Collectors.joining(SQL_LIKE));

            return getJpaRepository().findByParameterizedNameLike(SQL_LIKE + searchParameter + SQL_LIKE);
        }
    }

}
