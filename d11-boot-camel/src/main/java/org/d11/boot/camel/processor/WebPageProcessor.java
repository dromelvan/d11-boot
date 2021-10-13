package org.d11.boot.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.d11.boot.download.WebPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Processes a WebPage JMS message by setting downloadFileName properties in the Camel exchange.
 */
public class WebPageProcessor implements Processor {

    /**
     * Date time formatter for file name timestamp.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    @Override
    public void process(final Exchange exchange) {
        final WebPage webPage = exchange.getIn().getBody(WebPage.class);

        final String fileName = String.format("%s-%s.html", webPage.getTitle().replace("/", "-"), LocalDateTime.now().format(DATE_TIME_FORMATTER));
        exchange.setProperty("downloadFileName", fileName);

        exchange.getIn().setBody(webPage.getPageSource());
    }

}
