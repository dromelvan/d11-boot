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
 * Composite ID for TransferWindowPositionCount.
 */
@Embeddable
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferWindowPositionCountId implements Serializable {

    /**
     * Serial version UID.
     */
    @Serial
    private static final long serialVersionUID = -2150557785222029784L;

    /**
     * Transfer window id.
     */
    @Column(name = "transfer_window_id")
    private Long transferWindowId;

    /**
     * Position id.
     */
    @Column(name = "position_id")
    private Long positionId;

}
