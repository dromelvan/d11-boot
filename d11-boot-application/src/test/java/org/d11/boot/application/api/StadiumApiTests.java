package org.d11.boot.application.api;

import org.d11.boot.api.model.StadiumDTO;
import org.d11.boot.application.model.Stadium;
import org.d11.boot.application.repository.StadiumRepository;
import org.d11.boot.client.api.StadiumApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Stadium API tests.
 */
public class StadiumApiTests extends AbstractApiTests {

    /**
     * Stadium repository.
     */
    @Autowired
    private StadiumRepository stadiumRepository;
    /**
     * List of stadia.
     */
    private List<Stadium> stadia;

    /**
     * Sets up mocked stadia for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        this.stadia = this.stadiumRepository.findAll();
    }

    /**
     * Tests the findStadiumById API operation.
     */
    @Test
    public void findStadiumById() {
        final StadiumApi stadiumApi = new StadiumApi(getApiClient());

        for(final Stadium stadium : this.stadia) {
            final StadiumDTO result = stadiumApi.findStadiumById(stadium.getId()).block();
            final StadiumDTO stadiumDTO = map(stadium, StadiumDTO.class);

            assertNotNull(result, "Stadium by id should not be null.");
            assertEquals(stadiumDTO, result, "Stadium by id should equal Stadium.");
        }

        assertNotFound(stadiumApi.findStadiumById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

    /**
     * Correct resource string for 'stadium'.
     *
     * @return "stadia".
     */
    @Override
    protected String getResourceString() {
        return "stadia";
    }

}
