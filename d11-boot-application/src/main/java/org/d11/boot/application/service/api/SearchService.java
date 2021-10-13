package org.d11.boot.application.service.api;

import org.d11.boot.api.model.PlayerSearchResultDTO;
import org.d11.boot.api.model.SearchResultDTO;
import org.d11.boot.application.model.projection.PlayerSearchResult;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.util.Parameterizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Provides search services.
 */
@Service
public class SearchService extends ApiService {

    /**
     * Patter for matching exact search strings, i.e ones that are enclosed in quotes.
     */
    private static final Pattern EXACT_SEARCH_PATTERN = Pattern.compile("(\"|%2522)(.*)(\"|%2522)");
    /**
     * Repository used for player searches.
     */
    private final PlayerRepository playerRepository;

    /**
     * Creates a new search service.
     *
     * @param playerRepository The player repository this service will use.
     */
    @Autowired
    public SearchService(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Searches players by name. If the name is enclosed in quotes then only exact matches will be returned.
     *
     * @param name The name to search for.
     * @return Search result DTO with players with matching names.
     */
    public SearchResultDTO search(final String name) {
        final List<PlayerSearchResult> playerSearchResults;

        final Matcher exactPatternMatcher = EXACT_SEARCH_PATTERN.matcher(name);
        if(exactPatternMatcher.matches()) {
            final String parameterizedName = Parameterizer.parameterize(exactPatternMatcher.group(2));
            playerSearchResults = this.playerRepository.findByParameterizedName(parameterizedName);
        } else {
            final String sqlLike = "%";
            final StringBuilder stringBuilder = new StringBuilder(sqlLike);
            for(final String string : name.split("[ +]")) {
                stringBuilder.append(Parameterizer.parameterize(string));
                stringBuilder.append(sqlLike);
            }
            playerSearchResults = this.playerRepository.findByParameterizedNameLike(stringBuilder.toString());
        }

        final SearchResultDTO searchResultDTO = new SearchResultDTO();
        searchResultDTO.getPlayers().addAll(playerSearchResults.stream().map(player -> map(player, PlayerSearchResultDTO.class)).collect(Collectors.toList()));
        return searchResultDTO;
    }
}
