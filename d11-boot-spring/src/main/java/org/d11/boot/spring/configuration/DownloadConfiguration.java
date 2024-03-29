package org.d11.boot.spring.configuration;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.download.browser.htmlunit.HtmlUnitBrowser;
import org.d11.boot.download.browser.selenium.ChromeBrowser;
import org.d11.boot.download.premierleague.PremierLeagueClubsDownloader;
import org.d11.boot.download.premierleague.PremierLeaguePlayerDownloader;
import org.d11.boot.download.premierleague.PremierLeaguePlayerPhotoDownloader;
import org.d11.boot.download.premierleague.PremierLeagueSquadDownloader;
import org.d11.boot.download.whoscored.WhoscoredMatchDownloader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Configures downloader properties.
 */
@Configuration
public class DownloadConfiguration {

    /**
     * Provides the downloader we want to use to download WhoScored match files.
     *
     * @param remoteDriverUrl URL for the remote Selenium driver.
     * @param userDataDir Optional user data directory name.
     * @return The downloader we want to use to download WhoScored match files.
     */
    @Bean
    public WhoscoredMatchDownloader<ChromeBrowser> whoscoredMatchDownloader(
            @Value("${d11.download.selenium.remoteUrl}") final String remoteDriverUrl,
            @Value("${d11.download.selenium.userDataDir:#{null}}") final String userDataDir) {
        final Callable<ChromeBrowser> callable = () -> {
            final ChromeBrowser chromeBrowser = new ChromeBrowser();
            chromeBrowser.setRemoteDriverUrl(new URL(remoteDriverUrl));
            chromeBrowser.setAutoQuit(true);
            if (StringUtils.isNotBlank(userDataDir)) {
                chromeBrowser.setUserDataDir(userDataDir);
            }
            return chromeBrowser;
        };
        return new WhoscoredMatchDownloader<>(callable);
    }

    /**
     * Provides the downloader we want to use to download PremierLeague.com clubs page files.
     *
     * @return The downloader we want to use to download PremierLeague.com clubs page files.
     */
    @Bean
    public PremierLeagueClubsDownloader<HtmlUnitBrowser> premierLeagueClubsDownloader() {
        final Callable<HtmlUnitBrowser> callable = HtmlUnitBrowser::new;
        return new PremierLeagueClubsDownloader<>(callable);
    }

    /**
     * Provides the downloader we want to use to download PremierLeague.com squad page files.
     *
     * @return The downloader we want to use to download PremierLeague.com squad page files.
     */
    @Bean
    public PremierLeagueSquadDownloader<HtmlUnitBrowser> premierLeagueSquadDownloader() {
        final Callable<HtmlUnitBrowser> callable = HtmlUnitBrowser::new;
        return new PremierLeagueSquadDownloader<>(callable);
    }

    /**
     * Provides the downloader we want to use to download PremierLeague.com player page files.
     *
     * @return The downloader we want to use to download PremierLeague.com player page files.
     */
    @Bean
    public PremierLeaguePlayerDownloader<HtmlUnitBrowser> premierLeaguePlayerDownloader() {
        final Callable<HtmlUnitBrowser> callable = HtmlUnitBrowser::new;
        return new PremierLeaguePlayerDownloader<>(callable);
    }

    /**
     * Provides the downloader we want to use to download PremierLeague.com player photos.
     *
     * @return The downloader we want to use to download PremierLeague.com player photos.
     */
    @Bean
    public PremierLeaguePlayerPhotoDownloader<HtmlUnitBrowser> premierLeaguePlayerPhotoDownloader() {
        return new PremierLeaguePlayerPhotoDownloader<>(HtmlUnitBrowser::new);
    }

}
