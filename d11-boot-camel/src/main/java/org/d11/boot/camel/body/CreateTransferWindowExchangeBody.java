package org.d11.boot.camel.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.d11.boot.spring.model.TransferWindow;

import java.time.LocalDateTime;

/**
 * Holds properties for a create transfer window Camel route.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransferWindowExchangeBody extends CamelExchangeBody {

    /**
     * Transfer window transfer listing deadline.
     */
    private LocalDateTime datetime;

    /**
     * Number of days after transfer listing deadline the first transfer day will take place.
     */
    private int transferDayDelay;

    /**
     * The transfer window that was created.
     */
    private TransferWindow transferWindow;

}
