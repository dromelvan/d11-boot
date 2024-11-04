package org.d11.boot.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Contains user confirmation properties.
 */
@Data
@NoArgsConstructor
public class UserConfirmation {

    /**
     * User email.
     */
    private String email;

    /**
     * User confirm registration token.
     */
    private UUID confirmRegistrationToken;

}
