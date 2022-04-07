package org.d11.boot.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.d11.boot.api.AdministrationApi;
import org.d11.boot.api.model.AdministrationRequestResultDTO;
import org.d11.boot.api.model.UploadMatchStatsDTO;
import org.d11.boot.camel.CamelObjectMapper;
import org.d11.boot.jms.JmsQueue;
import org.d11.boot.jms.message.UpdateMatchMessage;
import org.d11.boot.jms.message.UpdatePlayerPhotosRequestMessage;
import org.d11.boot.jms.message.UpdateSquadsRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * Controller that implements the AdministrationApi and provides administration endpoints.
 */
@RestController
public class AdministrationController implements AdministrationApi {

    /**
     * JMS template used to put messages on the ActiveMQ queues.
     */
    private final JmsTemplate jmsTemplate;
    /**
     * Object mapper used to marshal/unmarshal JSON.
     */
    private final ObjectMapper objectMapper = new CamelObjectMapper();

    /**
     * Creates a new controller.
     *
     * @param jmsTemplate JMS template for posting requests to ActiveMQ.
     */
    @Autowired
    public AdministrationController(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    @RolesAllowed("ADMIN")
    public ResponseEntity<AdministrationRequestResultDTO> updateSquads() {
        sendMessage(JmsQueue.UPDATE_SQUADS_REQUEST, new UpdateSquadsRequestMessage());
        return ResponseEntity.ok(new AdministrationRequestResultDTO().message("Premier League squad update started."));
    }

    @Override
    @RolesAllowed("ADMIN")
    public ResponseEntity<AdministrationRequestResultDTO> updatePhotos() {
        sendMessage(JmsQueue.UPDATE_PLAYER_PHOTOS_REQUEST, new UpdatePlayerPhotosRequestMessage());
        return ResponseEntity.ok(new AdministrationRequestResultDTO()
                .message("Premier League player photo squad update started."));
    }

    @Override
    @RolesAllowed("ADMIN")
    public ResponseEntity<AdministrationRequestResultDTO> uploadMatchStats(final UploadMatchStatsDTO uploadMatchStatsDTO) {
        try {
            final UpdateMatchMessage updateMatchMessage = this.objectMapper.readValue(uploadMatchStatsDTO.getData(),
                                                                                      UpdateMatchMessage.class);
            sendMessage(JmsQueue.UPDATE_MATCH, updateMatchMessage);
            return ResponseEntity.ok(new AdministrationRequestResultDTO().message("Premier League match stats uploaded."));
        } catch(JsonProcessingException e) {
            return ResponseEntity.ok(new AdministrationRequestResultDTO()
                    .message("Could not map uploaded match stats to update match JMS message."));
        }
    }

    /**
     * Sends a JMS message to a queue.
     *
     * @param jmsQueue The queue the message will be sent to.
     * @param message  The message that will be sent.
     */
    private void sendMessage(final JmsQueue jmsQueue, final Object message) {
        this.jmsTemplate.convertAndSend(jmsQueue.getName(), message);
    }

}
