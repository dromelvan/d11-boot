package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.spring.model.converter.StatusConverter;
import org.d11.boot.util.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A transfer window. Contains a number of transfer days.
 */
@Data
@Entity
public class TransferWindow extends D11Entity implements Comparable<TransferWindow> {

    /**
     * Transfer window comparator.
     */
    private static final Comparator<TransferWindow> COMPARATOR =
            Comparator.comparing(TransferWindow::getDatetime).reversed();

    /**
     * The nth transfer window of the season this transfer window is.
     */
    @PositiveOrZero
    private int transferWindowNumber;

    /**
     * Indicates if the transfer window is a draft window or not.
     */
    private boolean draft;

    /**
     * The status of the transfer window.
     */
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    /**
     * The date and time the window closes and after which players can no longer be transfer listed.
     */
    @NotNull
    private LocalDateTime datetime;

    /**
     * The match week the transfers made in this transfer window will be active from.
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;

    /**
     * List of transfer days in this transfer window.
     */
    @OneToMany(mappedBy = "transferWindow", cascade = CascadeType.PERSIST)
    @OrderBy("transferDayNumber")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TransferDay> transferDays = new ArrayList<>();

    @Override
    public int compareTo(final TransferWindow transferWindow) {
        return COMPARATOR.compare(this, transferWindow);
    }

}
