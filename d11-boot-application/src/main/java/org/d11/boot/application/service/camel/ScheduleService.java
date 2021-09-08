package org.d11.boot.application.service.camel;

import org.d11.boot.application.camel.CamelObjectMapper;
import org.d11.boot.application.camel.JmsQueue;
import org.d11.boot.application.model.jms.UpdateMatchRequestMessage;
import org.d11.boot.application.model.jpa.Match;
import org.d11.boot.application.model.jpa.MatchWeek;
import org.d11.boot.application.model.jpa.Status;
import org.d11.boot.application.model.jpa.projection.EntityId;
import org.d11.boot.application.repository.MatchRepository;
import org.d11.boot.application.repository.MatchWeekRepository;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

/**
 * Provides scheduled services.
 */
@Service
public class ScheduleService {

    /**
     * Set of statuses that means a match should be updated.
     */
    private static final Set<Status> UPDATE_STATUSES = Set.of(Status.PENDING, Status.ACTIVE);
    /**
     * Set of statuses that means a match should be finished.
     */
    private static final Set<Status> FINISH_STATUSES = Set.of(Status.FULL_TIME);
    /**
     * Set of statuses that means a match is finished.
     */
    private static final Set<Status> POSTPONED_STATUSES = Set.of(Status.POSTPONED);
    /**
     * Match repository.
     */
    private final MatchRepository matchRepository;
    /**
     * Match week repository.
     */
    private final MatchWeekRepository matchWeekRepository;
    /**
     * JMS template for posting update request to ActiveMQ.
     */
    private final JmsTemplate jmsTemplate;

    /**
     * Creates a new service.
     *
     * @param matchRepository     The match repository the service will use.
     * @param matchWeekRepository The match week repository the service will use.
     * @param jmsTemplate         The JMS template the service will use.
     */
    @Autowired
    public ScheduleService(final MatchRepository matchRepository, final MatchWeekRepository matchWeekRepository, final JmsTemplate jmsTemplate) {
        this.matchRepository = matchRepository;
        this.matchWeekRepository = matchWeekRepository;
        this.jmsTemplate = jmsTemplate;

        final MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        mappingJackson2MessageConverter.setObjectMapper(new CamelObjectMapper());
        this.jmsTemplate.setMessageConverter(mappingJackson2MessageConverter);
    }

    public void updateMatchDatetimes() {
        final MatchWeek currentMatchWeek =
                this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(LocalDate.now()).orElseThrow(NotFoundException::new);
        final List<Match> matches = this.matchRepository.findByMatchWeekIdOrStatusInOrderByDatetime(currentMatchWeek.getId() + 1L, POSTPONED_STATUSES);
        for(final Match match : matches) {
            final UpdateMatchRequestMessage updateMatchRequestMessage = new UpdateMatchRequestMessage();
            updateMatchRequestMessage.setMatchId(match.getId());
            this.jmsTemplate.convertAndSend(JmsQueue.UPDATE_MATCH_REQUEST.getName(), updateMatchRequestMessage);
        }
    }

    /**
     * Gets all pending and active matches with kickoff times earlier than the current datetime and triggers an update
     * of them by posting to the UPDATE_MATCH_REQUEST JMS queue.
     */
    public void updateMatchStats() {
        final List<EntityId> matchIds = this.matchRepository.findByDatetimeLessThanAndStatusInOrderByDatetimeAscIdAsc(LocalDateTime.now(), UPDATE_STATUSES);
        for(final EntityId matchId : matchIds) {
            final UpdateMatchRequestMessage updateMatchRequestMessage = new UpdateMatchRequestMessage();
            updateMatchRequestMessage.setMatchId(matchId.getId());
            this.jmsTemplate.convertAndSend(JmsQueue.UPDATE_MATCH_REQUEST.getName(), updateMatchRequestMessage);
        }
    }

    /**
     * Gets all full time matches with kickoff time earlier than the current date and triggers finishing of them by
     * posting to the UPDATE_MATCH_REQUEST JMS queue.
     */
    public void finishMatchStats() {
        final LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);

        final List<EntityId> matchIds = this.matchRepository.findByDatetimeLessThanAndStatusInOrderByDatetimeAscIdAsc(localDateTime, FINISH_STATUSES);
        for(final EntityId matchId : matchIds) {
            final UpdateMatchRequestMessage updateMatchRequestMessage = new UpdateMatchRequestMessage();
            updateMatchRequestMessage.setMatchId(matchId.getId());
            updateMatchRequestMessage.setFinish(true);
            this.jmsTemplate.convertAndSend(JmsQueue.UPDATE_MATCH_REQUEST.getName(), updateMatchRequestMessage);
        }
    }

}
