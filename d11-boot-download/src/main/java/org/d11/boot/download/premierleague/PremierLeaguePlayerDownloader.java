package org.d11.boot.download.premierleague;

import org.d11.boot.download.Downloader;
import org.d11.boot.download.WebPage;
import org.d11.boot.download.browser.Browser;

import java.util.concurrent.Callable;

/**
 * Downloader that downloads a Premier League player page.
 *
 * @param <T> Browser type used by the downloader.
 */
public class PremierLeaguePlayerDownloader<T extends Browser> extends Downloader<T> {

    /**
     * Creates a new Premier League player downloader.
     *
     * @param callable Callable that should produce a properly configured Browser.
     */
    public PremierLeaguePlayerDownloader(final Callable<T> callable) {
        super(callable);
    }

    /**
     * Downloads a Premier League player page.
     *
     * @param playerId The player id.
     * @param name     The player name.
     * @return Web page with title and source of the Premier League player page.
     */
    public WebPage downloadPlayer(final int playerId, final String name) {
        return download(URL_REGISTRY.getPremierLeaguePlayerUrl(playerId, name));
    }

}
