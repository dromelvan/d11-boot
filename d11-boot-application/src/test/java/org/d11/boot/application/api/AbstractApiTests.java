package org.d11.boot.application.api;

import org.d11.boot.application.mock.D11EasyRandom;
import org.d11.boot.application.model.D11League;
import org.d11.boot.application.model.PremierLeague;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Stadium;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.model.User;
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

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Base class for API tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
@SuppressWarnings("checkstyle:ClassFanOutComplexity")
public abstract class AbstractApiTests extends MappingProvider {

    /**
     * Server port used when running tests with SpringBootTest.WebEnvironment.RANDOM_PORT.
     */
    @LocalServerPort
    private int localServerPort;
    /**
     * User repository.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * Stadium repository.
     */
    @Autowired
    private StadiumRepository stadiumRepository;
    /**
     * Team repository.
     */
    @Autowired
    private TeamRepository teamRepository;
    /**
     * Season repository.
     */
    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Sets up the tests by creating test data.
     */
    @BeforeAll
    public void beforeAllApiTests() {
        final D11EasyRandom d11EasyRandom = new D11EasyRandom();

        final List<User> users = d11EasyRandom.objects(User.class, 2).collect(Collectors.toList());
        this.userRepository.saveAll(users);

        List<Stadium> stadia = d11EasyRandom.objects(Stadium.class, 2).collect(Collectors.toList());
        stadia.forEach(stadium -> stadium.setTeams(new HashSet<>()));
        stadia = this.stadiumRepository.saveAll(stadia);

        for(final Stadium stadium : stadia) {
            final Team team = d11EasyRandom.nextObject(Team.class);
            team.setStadium(stadium);
            this.teamRepository.save(team);
        }

        final List<Season> seasons = d11EasyRandom
                .objects(Season.class, 2)
                .collect(Collectors.toList());

        for(final Season season : seasons) {
            final PremierLeague premierLeague = d11EasyRandom.nextObject(PremierLeague.class);
            season.setPremierLeague(premierLeague);
            premierLeague.setSeason(season);

            final D11League d11League = d11EasyRandom.nextObject(D11League.class);
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
     * @param uri The uri we want to get a mono for, based on the webclient basepath.
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

}
