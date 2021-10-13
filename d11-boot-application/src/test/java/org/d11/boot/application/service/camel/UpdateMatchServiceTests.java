package org.d11.boot.application.service.camel;

import org.d11.boot.application.model.Match;
import org.d11.boot.application.repository.MatchRepository;
import org.d11.boot.application.service.ServiceTests;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.jms.message.DownloadWhoscoredMatchMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Camel match service tests.
 */
public class UpdateMatchServiceTests extends ServiceTests<UpdateMatchService, MatchRepository> {

    /**
     * Tests the getDownloadMatchRequest service.
     */
    @Test
    public void testGetDownloadMatchRequest() {
        for(final Match match : getD11EntityRepository().findAll()) {
            final DownloadWhoscoredMatchMessage result = getD11BootService().getDownloadWhoscoredMatchMessage(match.getId(), false);

            assertNotNull(result, "Result should not be null.");
            assertEquals(match.getId(), result.getId(), "Result id should equal match id.");
            assertEquals(match.getWhoscoredId(), result.getWhoscoredId(), "Result whoscoredId should equal match whoscoredId");
            assertEquals(match.getMatchWeek().getMatchWeekNumber(), result.getMatchWeekNumber(),
                    "Result match week number should equal match match week match week number.");
            assertEquals(match.getMatchWeek().getSeason().getName(), result.getSeasonName(),
                    "Result season name should equal match match week season name.");
        }

        assertThrows(NotFoundException.class, () -> getD11BootService().getDownloadWhoscoredMatchMessage(-1L, false));
    }

}
