package org.d11.boot.parser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Dummy object that can be serialized/deserialized by a Jackson object mapper.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JacksonObject {

    /**
     * A string value.
     */
    private String stringValue;

    /**
     * An integer value.
     */
    private int intValue;

    /**
     * A boolean value.
     */
    private boolean booleanValue;

    /**
     * A datetime value.
     */
    private LocalDateTime dateTime;

    /**
     * A list value.
     */
    private List<String> listValue;

}
