package org.d11.boot.spring.service;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.spring.model.Country;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerInput;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.repository.CountryRepository;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.util.Parameterizer;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.NotFoundException;
import org.d11.boot.util.exception.ValidationError;
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
     * Country repository.
     */
    private final CountryRepository countryRepository;

    /**
     * Creates a player service.
     *
     * @param playerRepository  The repository the service will use.
     * @param countryRepository The country repository the service will use.
     */
    @Autowired
    public PlayerService(final PlayerRepository playerRepository, final CountryRepository countryRepository) {
        super(Player.class, playerRepository);
        this.countryRepository = countryRepository;
    }

    /**
     * Updates a player.
     *
     * @param playerId    Player id.
     * @param playerInput Player input properties that will be updated.
     * @return The updated player.
     */
    public Player updatePlayer(final Long playerId, final PlayerInput playerInput) {
        final List<ValidationError> validationErrors = validate(playerInput);
        if (!validationErrors.isEmpty()) {
            throw new BadRequestException("Invalid player", validationErrors);
        }

        final Player player = getById(playerId);
        final Country country = this.countryRepository.findById(playerInput.countryId())
                .orElseThrow(() -> new NotFoundException(playerInput.countryId(), Country.class));

        getServiceMapper().mapToPlayer(playerInput, player);
        player.setCountry(country);

        return save(player);
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
