package org.d11.boot.spring.security;

import org.d11.boot.spring.model.User;
import org.springframework.mail.SimpleMailMessage;

import java.io.Serial;

/**
 * Mail message with a link for confirmin a user registration.
 */
public class ConfirmRegistrationLinkMailMessage extends SimpleMailMessage {

    @Serial
    private static final long serialVersionUID = 2342733643658094277L;

    /**
     * Message template for confirm registration email messages.
     */
    private static final String MESSAGE =
        """
        Thank you for signing up for D11! 
        To complete your registration, please confirm your email address by clicking the link below:
        
        Confirm Your Email Address
        
        This link will expire in 24 hours. If it expires, you can request a new confirmation link on the login page.
        
        If you did not sign up for a D11 account, then ignore this message.
        
        Best regards,
        The D11 Team
        """;

    /**
     * Creates a new mail message with a link for confirming a user registration.
     *
     * @param user The user that has been registered.
     * @param link Link to the URL that triggers the confirmation.
     */
    public ConfirmRegistrationLinkMailMessage(final User user, final String link) {
        setTo(user.getEmail());
        setSubject("Welcome to D11! Please Confirm Your Email Address");
        setText(MESSAGE + String.format(link, user.getEmail(), user.getConfirmRegistrationToken()));
    }

}
