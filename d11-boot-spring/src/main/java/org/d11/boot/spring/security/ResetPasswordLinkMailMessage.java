package org.d11.boot.spring.security;

import org.d11.boot.spring.model.User;
import org.springframework.mail.SimpleMailMessage;

import java.io.Serial;

/**
 * Mail message with a link for resetting a user password.
 */
public class ResetPasswordLinkMailMessage extends SimpleMailMessage {

    @Serial
    private static final long serialVersionUID = 4301257652167919558L;

    /**
     * Message template for reset password email messages.
     */
    private static final String MESSAGE =
        """
        There was a request to reset your D11 password.                
        
        If you did not make this request then ignore this email.
        
        Otherwise, click this link to reset your D11 password:
                        
        """;

    /**
     * Creates a new mail message with a link for resetting a user password.
     *
     * @param user The user that has requested a password reset.
     * @param link Link to the api method that resets the password.
     */
    public ResetPasswordLinkMailMessage(final User user, final String link) {
        setTo(user.getEmail());
        setSubject("Reset your D11 password");
        setText(MESSAGE + String.format(link, user.getResetPasswordToken()));
    }

}
