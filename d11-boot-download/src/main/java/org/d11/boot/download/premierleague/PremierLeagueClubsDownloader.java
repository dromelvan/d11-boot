package org.d11.boot.download.premierleague;

import org.d11.boot.download.Downloader;
import org.d11.boot.download.WebPage;
import org.d11.boot.download.browser.Browser;

import java.util.concurrent.Callable;

/**
 * Downloader that downloads a Premier League clubs page.
 *
 * @param <T> Browser type used by the downloader.
 */
public class PremierLeagueClubsDownloader<T extends Browser> extends Downloader<T> {

    /**
     * Creates a new Premier League clubs downloader.
     *
     * @param callable Callable that should produce a properly configured Browser.
     */
    public PremierLeagueClubsDownloader(final Callable<T> callable) {
        super(callable);
    }

    /**
     * Downloads a Premier League clubs page.
     *
     * @return Web page with title and source of the Premier League clubs page.
     */
    public WebPage downloadClubs() {
        return download(URL_REGISTRY.getPremierLeagueClubsUrl());
    }

}
