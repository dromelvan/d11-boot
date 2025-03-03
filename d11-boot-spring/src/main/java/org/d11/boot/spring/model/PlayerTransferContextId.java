package org.d11.boot.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Composite ID for PlayerTransferContext.
 */
@Embeddable
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerTransferContextId implements Serializable {

    /**
     * Serial version UID.
     */
    @Serial
    private static final long serialVersionUID = -2150557785222029784L;

    /**
     * Player id.
     */
    @Column(name = "player_id")
    private Long playerId;

    /**
     * D11 team id.
     */
    @Column(name = "d11_team_id")
    private Long d11TeamId;

}
