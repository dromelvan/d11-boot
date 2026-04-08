package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.spring.model.validation.TransferFee;

/**
 * A transfer in a transfer day.
 */
@Data
@Entity
@NamedEntityGraph(name = Transfer.TRANSFER_ASSOCIATIONS,
        attributeNodes = {
            @NamedAttributeNode("transferDay"),
            @NamedAttributeNode("player"),
            @NamedAttributeNode("d11Team"),
            @NamedAttributeNode("transferListing"),
            @NamedAttributeNode(value = "transferListing", subgraph = "transfer-listing-subgraph")
        },
        subgraphs = {
            @NamedSubgraph(
                        name = "transfer-listing-subgraph",
                        attributeNodes = {
                            @NamedAttributeNode("team"),
                            @NamedAttributeNode("position")
                        }
                )
        }
)
public class Transfer extends D11Entity {

    /**
     * Name of the entity graph that includes everything.
     */
    public static final String TRANSFER_ASSOCIATIONS = "Transfer.associations";

    /**
     * Number the fee has to be evenly dividable with.
     */
    public static final int FEE_DIVISOR = 5;

    /**
     * The fee of the transfer.
     */
    @TransferFee
    private int fee;

    /**
     * The transfer day the transfer is for.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "transfer_day_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TransferDay transferDay;

    /**
     * The player the transfer is for.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Player player;

    /**
     * The D11 team the transfer is from.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The transfer listing for the same transfer day and player, if any.
     */
    @OneToOne
    @JoinColumn(name = "transfer_listing_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TransferListing transferListing;

}
