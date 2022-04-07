package org.d11.boot.cli.command;

import feign.FeignException;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.d11.boot.api.model.AuthenticationDTO;
import org.d11.boot.api.model.AuthenticationResultDTO;
import org.d11.boot.client.ApiClient;
import org.d11.boot.client.api.AdministrationApi;
import org.d11.boot.client.api.AuthenticationApi;
import org.d11.boot.client.auth.HttpBearerAuth;
import picocli.CommandLine.Option;

import java.net.ConnectException;

/**
 * Base class for commands that use Feign client to call application services.
 */
@Slf4j
public abstract class AbstractFeignCommand extends AbstractCliCommand {

    /**
     * Password prompt string.
     */
    private static final String PASSWORD_PROMPT = "D11 admin password: ";
    /**
     * Authorization name/scheme string.
     */
    private static final String BEARER = "Bearer";
    /**
     * The username that will be used when authenticating.
     */
    @Option(names = {"-u", "--username"}, description = "The username that will be used when authenticating.")
    private String username;
    /**
     * The password that will be used when authenticating.
     */
    @Option(names = {"-p", "--password"}, description = "The password that will be used when authenticating.",
            interactive = true, prompt = PASSWORD_PROMPT)
    private String password;
    /**
     * Api client.
     */
    private final ApiClient apiClient;

    /**
     * Creates a new Feign client command.
     */
    public AbstractFeignCommand() {
        this.apiClient = new ApiClient();
        this.apiClient.addAuthorization(BEARER, new HttpBearerAuth(BEARER));
    }

    @Override
    public Integer call() {
        try {
            final AuthenticationApi authenticationApi = getClient(AuthenticationApi.class);
            final AuthenticationDTO authenticationDTO = new AuthenticationDTO()
                    .username(this.username)
                    .password(this.password);
            final AuthenticationResultDTO authenticationResultDTO = authenticationApi.authenticate(authenticationDTO);

            this.apiClient.setBearerToken(authenticationResultDTO.getJwt());
            call(getClient(AdministrationApi.class));
        } catch(RetryableException e) {
            if(e.getCause() instanceof ConnectException) {
                log.error("Could not connect to {}.", this.apiClient.getBasePath());
            } else {
                log.error("Unexpected exception {} ", e.getCause().getClass());
            }
        } catch(FeignException.Unauthorized | FeignException.Forbidden e) {
            log.error("Authentication failed.");
        }
        return 0;
    }

    /**
     * Override to perform the call to the administration api.
     *
     * @param administrationApi Administration api that will be used in the call.
     */
    protected abstract void call(AdministrationApi administrationApi);

    /**
     * Gets a Feign client of a specific class.
     *
     * @param apiClass Feign client api class.
     * @param <T>      Feign client api class.
     * @return Feign client of the provided class.
     */
    protected <T extends ApiClient.Api> T getClient(final Class<T> apiClass) {
        return this.apiClient.buildClient(apiClass);
    }

}
