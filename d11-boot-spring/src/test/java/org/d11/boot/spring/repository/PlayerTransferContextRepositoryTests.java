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
@SuppressWarnings("checkstyle:ClassFanOutComplexity")
class PlayerTransferContextRepositoryTests {

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
    @SuppressWarnings({
        "checkstyle:JavaNCSS",
        "checkstyle:ExecutableStatementCount",
        "PMD.ExcessiveClassLength",
        "PMD.ExcessiveMethodLength",
        "PMD.NcssCount",
        "PMD.CognitiveComplexity"
    })
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
                    assertTrue(contextOptional.isEmpty());
                } else {
                    ++contextCount;

                    assertTrue(contextOptional.isPresent());

                    final PlayerTransferContext context = contextOptional.orElseThrow(InvalidTestSetupException::new);

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

                    assertEquals(player, context.getPlayer());
                    assertEquals(playerSeasonStat.getPosition(), context.getPosition());
                    assertEquals(playerSeasonStat.getD11Team(), context.getPlayerD11Team());
                    assertEquals(season, context.getSeason());
                    assertEquals(transferDay, context.getTransferDay());
                    if (transferListing == null) {
                        assertNull(context.getTransferListing());
                    } else {
                        ++transferListingCount;
                        assertEquals(transferListing, context.getTransferListing());
                    }
                    if (transferBid == null) {
                        assertNull(context.getTransferBid());
                    } else {
                        ++transferBidCount;
                        assertEquals(transferBid, context.getTransferBid());
                    }
                    assertEquals(d11Team, context.getD11Team());
                    assertEquals(d11Team.getOwner().getId(), context.getOwnerId());
                    if (d11Team.getCoOwner() == null) {
                        assertNull(context.getCoOwnerId());
                    } else {
                        ++coOwnerCount;
                        assertEquals(d11Team.getCoOwner().getId(), context.getCoOwnerId());
                    }
                    assertEquals(d11TeamSeasonStat.getRanking(), context.getRanking());
                    assertEquals(playerCount, context.getPlayerCount());
                    assertEquals(feeSum, context.getFeeSum());
                    assertEquals(transferCount, context.getTransferCount());
                    assertEquals(positionCount, context.getPositionCount());
                }
            }
        }

        // These are only to check that the test data is set up to provide the cases we'd like it to
        assertNotEquals(0, contextCount);
        assertNotEquals(0, transferListingCount);
        assertNotEquals(0, transferBidCount);
        assertNotEquals(0, coOwnerCount);
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

        assertEquals(player.getId(), context.getPlayer().getId());
        assertEquals(2L, context.getPosition().getId());
        assertEquals(3L, context.getPlayerD11Team().getId());
        assertEquals(2L, context.getSeason().getId());
        assertEquals(8L, context.getTransferDay().getId());
        assertNull(context.getTransferListing());
        assertNull(context.getTransferBid());
        assertEquals(2L, context.getD11Team().getId());
        assertEquals(1L, context.getOwnerId());
        assertEquals(4L, context.getCoOwnerId());
        assertEquals(2, context.getRanking());
        assertEquals(1, context.getPlayerCount());
        assertEquals(50, context.getFeeSum());
        assertEquals(0, context.getTransferCount());
        assertEquals(0, context.getPositionCount());

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

        final PlayerTransferContext updatedContext = this.repository.findByPlayerIdAndOwnerId(player.getId(),
                                                                                              owner.getId())
                .orElseThrow(InvalidTestSetupException::new);

        assertEquals(4L, updatedContext.getTransferListing().getId());
        assertEquals(13L, updatedContext.getTransferBid().getId());
    }

}
