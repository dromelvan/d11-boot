package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * D11 team repository tests.
 */
class D11TeamRepositoryTests extends AbstractRepositoryTests<D11Team, D11TeamRepository> {

    /**
     * Email of the test data user with no D11 team.
     */
    private static final String NO_TEAM_USER_EMAIL = "user3@email.com";

    /**
     * User repository used to look up users for D11 team ownership tests.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Tests D11TeamRepository::findByOrderByName.
     */
    @Test
    void testFindByOrderByName() {
        final List<D11Team> d11Teams = getEntities();
        d11Teams.sort(Comparator.comparing(D11Team::getName));

        final List<D11Team> result = getRepository().findByOrderByName();

        assertNotNull(result);
        assertEquals(d11Teams, result);
    }

    /**
     * Tests D11TeamRepository::findByOwnerOrCoOwner.
     */
    @Test
    void testFindByOwnerOrCoOwner() {
        final List<D11Team> d11Teams = getEntities();

        d11Teams.stream()
            .filter(d11Team -> d11Team.getOwner() != null)
            .forEach(d11Team -> {
                final D11Team result =
                    getRepository().findByOwnerOrCoOwner(d11Team.getOwner(), d11Team.getOwner()).orElse(null);
                assertNotNull(result);
                assertEquals(d11Team, result);
            });

        d11Teams.stream()
            .filter(d11Team -> d11Team.getCoOwner() != null)
            .forEach(d11Team -> {
                final D11Team result =
                    getRepository().findByOwnerOrCoOwner(d11Team.getCoOwner(), d11Team.getCoOwner()).orElse(null);
                assertNotNull(result);
                assertEquals(d11Team, result);
            });

        final User noTeamUser = this.userRepository.findByEmail(NO_TEAM_USER_EMAIL).orElseThrow();

        assertTrue(getRepository().findByOwnerOrCoOwner(noTeamUser, noTeamUser).isEmpty());
    }

}
