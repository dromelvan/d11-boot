package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;

/**
 * A qualifier for an incident event. Example:
 * "qualifiers":[
 *      {"type":{"value":31,"displayName":"Yellow"}}
 * ]
 */
@Data
public class Qualifier {

    /**
     * Qualifier type.
     */
    private Type type;

}
