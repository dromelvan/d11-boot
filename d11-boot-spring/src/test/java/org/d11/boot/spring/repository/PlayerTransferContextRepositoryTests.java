package org.d11.boot.spring.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.util.InvalidTestSetupException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player transfer context repository tests.
 */
@DataJpaTest
@ActiveProfiles("test")
public class PlayerTransferContextRepositoryTests {

    /**
     * Player transfer context repository.
     */
    @Autowired
    private PlayerTransferContextRepository repository;

    /**
     * User repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Season repository.
     */
    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Player repository.
     */
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Player season stat repository.
     */
    @Autowired
    private PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * D11 team repository.
     */
    @Autowired
    private D11TeamRepository d11TeamRepository;

    /**
     * D11 team season stat repository.
     */
    @Autowired
    private D11TeamSeasonStatRepository d11TeamSeasonStatRepository;

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Transfer listing repository.
     */
    @Autowired
    private TransferListingRepository transferListingRepository;

    /**
     * Transfer bid repository.
     */
    @Autowired
    private TransferBidRepository transferBidRepository;

    /**
     * Transfer repository.
     */
    @Autowired
    private TransferRepository transferRepository;

    /**
     * Entity manager for clearing context.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Tests PlayerTransferContextRepository::findByPlayerIdAndOwnerId.
     */
    @Test
    void testFindByPlayerIdAndOwnerIdTransferDay() {
        final List<Player> players = this.playerRepository.findAll();
        final Season season = this.seasonRepository.findFirstByOrderByDateDesc()
                .orElseThrow(InvalidTestSetupException::new);
        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc()
                .orElseThrow(InvalidTestSetupException::new);
        final List<D11TeamSeasonStat> d11TeamSeasonStats = this.d11TeamSeasonStatRepository
                .findBySeasonIdOrderByRanking(season.getId());

        int contextCount = 0;
        int transferListingCount = 0;
        int transferBidCount = 0;
        int coOwnerCount = 0;

        for (final Player player : players) {
            for (final D11TeamSeasonStat d11TeamSeasonStat : d11TeamSeasonStats) {
                final D11Team d11Team = d11TeamSeasonStat.getD11Team();
                final Optional<PlayerTransferContext> contextOptional =
                        this.repository.findByPlayerIdAndOwnerId(player.getId(), d11Team.getOwner().getId());
                final Optional<PlayerSeasonStat> playerSeasonStatOptional =
                        this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(player.getId(), season.getId());

                final List<PlayerSeasonStat> playerSeasonStats = this.playerSeasonStatRepository
                        .findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(d11Team.getId(), season.getId());
                final int playerCount = playerSeasonStats.size();
                final int feeSum = playerSeasonStats.stream().mapToInt(PlayerSeasonStat::getFee).sum();

                if (playerSeasonStatOptional.isEmpty()) {
                    assertTrue(contextOptional.isEmpty(),
                               "PlayerTransferContextRepository::findByPlayerIdAndOwnerId playerSeasonStat empty");
                } else {
                    ++contextCount;

                    assertTrue(contextOptional.isPresent(),
                               "PlayerTransferContextRepository::findByPlayerIdAndOwnerId playerSeasonStat present");

                    final PlayerTransferContext context = contextOptional.orElseThrow(InvalidTestSetupException::new);
                    System.out.println(context);
                    final PlayerSeasonStat playerSeasonStat =
                            playerSeasonStatOptional.orElseThrow(InvalidTestSetupException::new);
                    final TransferListing transferListing = transferListingRepository
                            .findByTransferDayIdAndPlayerId(transferDay.getId(), player.getId())
                            .orElse(null);
                    final TransferBid transferBid = transferBidRepository
                            .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(
                                    transferDay.getId()
                            ).stream()
                            .filter(bid -> bid.getPlayer().equals(player) && bid.getD11Team().equals(d11Team))
                            .findFirst()
                            .orElse(null);
                    final int transferCount = this.transferRepository
                            .findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(season.getId(),
                                                                                          d11Team.getId()).size();
                    final int positionCount = (int) playerSeasonStats.stream()
                            .filter(pss -> pss.getPosition().equals(playerSeasonStat.getPosition()))
                            .count();

                    assertEquals(player, context.getPlayer(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId player equals");
                    assertEquals(playerSeasonStat.getPosition(), context.getPosition(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId position equals");
                    assertEquals(playerSeasonStat.getD11Team(), context.getPlayerD11Team(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId playerD11Team equals");
                    assertEquals(season, context.getSeason(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId season equals");
                    assertEquals(transferDay, context.getTransferDay(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferDay equals");
                    if (transferListing == null) {
                        assertNull(context.getTransferListing(),
                                   "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferListing null");
                    } else {
                        ++transferListingCount;
                        assertEquals(transferListing, context.getTransferListing(),
                                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferList equals");
                    }
                    if (transferBid == null) {
                        assertNull(context.getTransferBid(),
                                   "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferBid null");
                    } else {
                        ++transferBidCount;
                        assertEquals(transferBid, context.getTransferBid(),
                                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferBid equals");
                    }
                    assertEquals(d11Team, context.getD11Team(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId d11Team equals");
                    assertEquals(d11Team.getOwner().getId(), context.getOwnerId(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId ownerId equals");
                    if (d11Team.getCoOwner() == null) {
                        assertNull(context.getCoOwnerId(),
                                   "PlayerTransferContextRepository::findByPlayerIdAndOwnerId coOwnerId null");
                    } else {
                        ++coOwnerCount;
                        assertEquals(d11Team.getCoOwner().getId(), context.getCoOwnerId(),
                                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId coOwnerId equals");
                    }
                    assertEquals(d11TeamSeasonStat.getRanking(), context.getRanking(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId ranking equals");
                    assertEquals(playerCount, context.getPlayerCount(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId playerCount equals");
                    assertEquals(feeSum, context.getFeeSum(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId feeSum equals");
                    assertEquals(transferCount, context.getTransferCount(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferCount equals");
                    assertEquals(positionCount, context.getPositionCount(),
                                 "PlayerTransferContextRepository::findByPlayerIdAndOwnerId positionCount equals");
                }
            }
        }

        // These are only to check that the test data is set up to provide the cases we'd like it to
        assertNotEquals(0, contextCount,
                        "PlayerTransferContextRepository::findByPlayerIdAndOwnerId contextCount not equals");
        assertNotEquals(0, transferListingCount,
                        "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferListingCount not equals");
        assertNotEquals(0, transferBidCount,
                        "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferBudCount not equals");
        assertNotEquals(0, coOwnerCount,
                        "PlayerTransferContextRepository::findByPlayerIdAndOwnerId coOwnerCount not equals");
    }

    /**
     * Tests PlayerTransferContextRepository::findByPlayerIdAndOwnerId for a specific player and D11 team.
     */
    @Test
    @DirtiesContext
    void testFindByPlayerIdAndOwnerIdTransferDaySpecific() {
        final Player player = this.playerRepository.findById(1L).orElseThrow(InvalidTestSetupException::new);
        final User owner = this.userRepository.findById(1L).orElseThrow(InvalidTestSetupException::new);

        final PlayerTransferContext context = this.repository.findByPlayerIdAndOwnerId(player.getId(), owner.getId())
                .orElseThrow(InvalidTestSetupException::new);

        assertEquals(player.getId(), context.getPlayer().getId(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId player id equals");
        assertEquals(2L, context.getPosition().getId(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId position id equals");
        assertEquals(3L, context.getPlayerD11Team().getId(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId playerD11Team id equals");
        assertEquals(2L, context.getSeason().getId(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId season id equals");
        assertEquals(8L, context.getTransferDay().getId(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferDay id equals");
        assertNull(context.getTransferListing(),
                   "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferListing not exists null");
        assertNull(context.getTransferBid(),
                   "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferBid not exists null");
        assertEquals(2L, context.getD11Team().getId(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId d11Team id equals");
        assertEquals(1L, context.getOwnerId(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId owner id equals");
        assertEquals(4L, context.getCoOwnerId(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId coOwner id equals");
        assertEquals(2, context.getRanking(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId ranking value equals");
        assertEquals(1, context.getPlayerCount(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId playerCount value equals");
        assertEquals(50, context.getFeeSum(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId feeSum value equals");
        assertEquals(0, context.getTransferCount(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferCount not exists equals");
        assertEquals(0, context.getPositionCount(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId positionCount value equals");

        final TransferListing transferListing = this.transferListingRepository.findById(4L)
                .orElseThrow(InvalidTestSetupException::new);
        transferListing.setPlayer(context.getPlayer());
        this.transferListingRepository.save(transferListing);

        final TransferBid transferBid = this.transferBidRepository.findById(13L)
                .orElseThrow(InvalidTestSetupException::new);
        transferBid.setPlayer(context.getPlayer());
        transferBid.setD11Team(context.getD11Team());
        this.transferBidRepository.save(transferBid);

        // Flush and clear to make PlayerTransferContextRepository query the database again instead of using cache
        this.transferListingRepository.flush();
        this.transferBidRepository.flush();
        this.entityManager.clear();

        final PlayerTransferContext updatedContext = this.repository.findByPlayerIdAndOwnerId(player.getId(), owner.getId())
                .orElseThrow(InvalidTestSetupException::new);

        assertEquals(4L, updatedContext.getTransferListing().getId(),
                   "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferListing id equals");
        assertEquals(13L, updatedContext.getTransferBid().getId(),
                     "PlayerTransferContextRepository::findByPlayerIdAndOwnerId transferBid id equals");
    }

}
