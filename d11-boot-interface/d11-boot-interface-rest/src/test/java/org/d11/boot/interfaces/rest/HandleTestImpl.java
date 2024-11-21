package org.d11.boot.interfaces.rest;

import org.d11.boot.camel.bean.HandleTest;
import org.springframework.stereotype.Component;

/**
 * Dummy implementation of HandleTest to make test context load with camel module in the runtime.
 */
@Component
public class HandleTestImpl implements HandleTest {

    @Override
    public void handle(final Object body) {
        // NOOP
    }

}
