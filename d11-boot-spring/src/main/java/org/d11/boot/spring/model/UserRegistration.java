package org.d11.boot.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contains user registration properties.
 */
@Data
@NoArgsConstructor
public class UserRegistration {

    /**
     * User name.
     */
    private String name;

    /**
     * User email.
     */
    private String email;

    /**
     * User password.
     */
    private String password;

    /**
     * User password confirmation.
     */
    private String confirmPassword;

}
