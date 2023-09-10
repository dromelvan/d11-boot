package org.d11.boot.spring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * Java mail sender implementation that only logs the message. Used for tests and running locally.
 */
@Component
@ConditionalOnProperty(name = "spring.mail.user", havingValue = "", matchIfMissing = true)
@Slf4j
public class NoopJavaMailSender extends JavaMailSenderImpl {

    @Override
    public void send(final SimpleMailMessage simpleMessage) {
        LOGGER.info("NoopJavaMailSender sending message: {}", simpleMessage.getText());
    }

}
