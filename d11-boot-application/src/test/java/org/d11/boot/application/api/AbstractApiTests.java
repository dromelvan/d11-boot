package org.d11.boot.application.api;

import lombok.Getter;
import org.d11.boot.application.mock.D11EasyRandom;
import org.d11.boot.application.model.Country;
import org.d11.boot.application.model.D11Entity;
import org.d11.boot.application.model.D11League;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.Position;
import org.d11.boot.application.model.PremierLeague;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Stadium;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.CountryRepository;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.repository.PositionRepository;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.application.repository.StadiumRepository;
import org.d11.boot.application.repository.TeamRepository;
import org.d11.boot.application.repository.UserRepository;
import org.d11.boot.application.util.MappingProvider;
import org.d11.boot.client.ApiClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Base class for API tests.
 *
 * @param <T> The entity class this tests is based on.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
@SuppressWarnings({ "checkstyle:ClassFanOutComplexity", "PMD.ExcessiveImports" })
public abstract class AbstractApiTests<T extends D11Entity> extends MappingProvider {

    /**
     * Random object generator.
     */
    private final D11EasyRandom d11EasyRandom = new D11EasyRandom();
    /**
     * Server port used when running tests with SpringBootTest.WebEnvironment.RANDOM_PORT.
     */
    @LocalServerPort
    private int localServerPort;
    /**
     * List of entities.
     */
    @Getter
    private final List<T> entities = new ArrayList<>();
    /**
     * User repository.
     */
    @Autowired
    @Getter
    private UserRepository userRepository;
    /**
     * Country repository.
     */
    @Autowired
    @Getter
    private CountryRepository countryRepository;
    /**
     * Country repository.
     */
    @Autowired
    @Getter
    private PositionRepository positionRepository;
    /**
     * Player repository.
     */
    @Autowired
    @Getter
    private PlayerRepository playerRepository;
    /**
     * Stadium repository.
     */
    @Autowired
    @Getter
    private StadiumRepository stadiumRepository;
    /**
     * Team repository.
     */
    @Autowired
    @Getter
    private TeamRepository teamRepository;
    /**
     * D11 team repository.
     */
    @Autowired
    @Getter
    private D11TeamRepository d11TeamRepository;
    /**
     * Season repository.
     */
    @Autowired
    @Getter
    private SeasonRepository seasonRepository;

    /**
     * Sets up the tests by creating test data.
     */
    @BeforeAll
    public void beforeAllApiTests() {
        List<User> users = generate(User.class, 2);
        users = this.userRepository.saveAll(users);

        for(final User user : users) {
            final D11Team d11Team = generate(D11Team.class);
            d11Team.setOwner(user);
            d11Team.setCoOwner(user);
            this.d11TeamRepository.save(d11Team);
        }

        List<Country> countries = generate(Country.class, 2);
        countries = this.countryRepository.saveAll(countries);

        final List<Position> positions = generate(Position.class, 2);
        this.positionRepository.saveAll(positions);

        for(final Country country : countries) {
            final Player player = generate(Player.class);
            player.setCountry(country);
            this.playerRepository.save(player);
        }

        List<Stadium> stadia = generate(Stadium.class, 2);
        stadia.forEach(stadium -> stadium.setTeams(new HashSet<>()));
        stadia = this.stadiumRepository.saveAll(stadia);

        for(final Stadium stadium : stadia) {
            final Team team = generate(Team.class);
            team.setStadium(stadium);
            this.teamRepository.save(team);
        }

        final List<Season> seasons = generate(Season.class, 2);

        for(final Season season : seasons) {
            final PremierLeague premierLeague = generate(PremierLeague.class);
            season.setPremierLeague(premierLeague);
            premierLeague.setSeason(season);

            final D11League d11League = generate(D11League.class);
            season.setD11League(d11League);
            d11League.setSeason(season);
        }
        this.seasonRepository.saveAll(seasons);
    }

    /**
     * Base path for test server.
     *
     * @return http://localhost:localServerPort.
     */
    protected String getBasePath() {
        return String.format("http://localhost:%d", this.localServerPort);
    }

    /**
     * Gets a configured API client.
     *
     * @return Configured API client.
     */
    protected ApiClient getApiClient() {
        final ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(getBasePath());
        return apiClient;
    }

    /**
     * Gets a mono from an URI.
     *
     * @param uri The uri we want to get a mono for, based on the webclient base path.
     * @return A mono for the request URI.
     */
    protected Mono<?> getMono(final String uri) {
        return getWebClient().get().uri(uri).retrieve().bodyToMono(Object.class);
    }

    /**
     * Gets a configured WebClient.
     *
     * @return A configured WebClient.
     */
    protected WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(String.format("%s/%s/",
                                       getApiClient().getBasePath(),
                                       getResourceString()))
                .build();
    }

    /**
     * Turns FooBarApiTests into foo-bars.
     *
     * @return Kebab cased resource name derived from test class name.
     */
    protected String getResourceString() {
        final String replacement = "$1-$2";
        return getClass().getSimpleName()
                .replace("ApiTest", "")
                .replaceAll("([a-z])([A-Z])", replacement)
                .replaceAll("(D11)([A-Z])", replacement)
                .toLowerCase(Locale.getDefault());
    }

    /**
     * Blocks the mono and assert that it throws a 404 NOT_FOUND exception.
     *
     * @param mono The mono performing thw web request we want to test.
     */
    protected void assertNotFound(final Mono<?> mono) {
        final WebClientResponseException webClientResponseException = assertThrows(WebClientResponseException.class, mono::block);
        assertEquals(HttpStatus.NOT_FOUND,
                webClientResponseException.getStatusCode(),
                "Response should have status NOT_FOUND.");
    }

    /**
     * Blocks the mono and assert that it throws a 400 BAD_REQUEST exception.
     *
     * @param mono The mono performing thw web request we want to test.
     */
    protected void assertBadRequest(final Mono<?> mono) {
        final WebClientResponseException webClientResponseException = assertThrows(WebClientResponseException.class, mono::block);
        assertEquals(HttpStatus.BAD_REQUEST,
                webClientResponseException.getStatusCode(),
                "Response should have status BAD_REQUEST.");
    }

    /**
     * Generates a single object of a D11 entity class.
     *
     * @param clazz Generic class parameter.
     * @param <U> The class of the object that will be generated.
     * @return An object of the specified class.
     */
    protected <U extends D11Entity> U generate(final Class<U> clazz) {
        return this.d11EasyRandom
                .nextObject(clazz);
    }

    /**
     * Generates a number of random objects of a D11 entity class.
     *
     * @param clazz Generic class parameter.
     * @param count The number of objects that will be generated.
     * @param <U> The class of objects that will be generated.
     * @return A list of objects of the specified class with the specified length.
     */
    protected <U extends D11Entity> List<U> generate(final Class<U> clazz, final int count) {
        return this.d11EasyRandom
                .objects(clazz, count)
                .collect(Collectors.toList());
    }

}
