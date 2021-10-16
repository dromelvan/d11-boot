package org.d11.boot.application.mock;

import org.d11.boot.D11BootApplication;
import org.jeasy.random.EasyRandom;

/**
 * EasyRandom for generating D11 entities.
 */
public class D11EasyRandom extends EasyRandom {

    public static final long serialVersionUID = D11BootApplication.VERSION;

    /**
     * Creates a new D11EasyRandom.
     */
    public D11EasyRandom() {
        super(new D11EntityRandomParameters());
    }

}
