package org.d11.boot.cli.camel;

import org.apache.camel.LoggingLevel;
import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.cli.camel.processor.ShutdownProcessor;
import org.d11.boot.jms.JmsQueue;
import org.springframework.stereotype.Component;

/**
 * Builds a route to trigger a player photo update.
 */
@Component
public class PhotosRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new update player photos route builder.
     */
    public PhotosRouteBuilder() {
        super(null, JmsQueue.UPDATE_PLAYER_PHOTOS_REQUEST);
    }

    @Override
    public void configure() {
        from("{{app.route.updatePhotos}}")
                .routeId("UPDATE_PLAYER_PHOTOS")
                .log(LoggingLevel.INFO, "Starting player photo updates...!")
                .marshal(getDestinationDataFormat())
                .to(getDestination())
                .log(LoggingLevel.INFO, "Player photo updates started.")
                .process(new ShutdownProcessor());
    }

}
