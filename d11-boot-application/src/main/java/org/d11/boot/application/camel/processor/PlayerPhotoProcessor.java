package org.d11.boot.application.camel.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.zip.CRC32;

/**
 * Processes a player photo by checking if a photo with the same CRC already exists and setting the savePhotoFile
 * property in the Camel exchange to true or false depending on the answer.
 */
@Slf4j
public class PlayerPhotoProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) {
        final byte[] playerPhotoBytes = exchange.getIn().getBody(byte[].class);
        final CRC32 crc32 = new CRC32();
        crc32.update(playerPhotoBytes);

        final Long photoFileCrc = exchange.getProperty("photoFileCrc", Long.class);
        if(photoFileCrc == null || photoFileCrc == crc32.getValue()) {
            exchange.setProperty("savePhotoFile", false);
        } else {
            exchange.setProperty("savePhotoFile", true);
        }
    }

}
