package org.d11.boot.download.premierleague;

import org.d11.boot.download.Downloader;
import org.d11.boot.download.WebPage;
import org.d11.boot.download.browser.Browser;

import java.util.concurrent.Callable;

/**
 * Downloader that downloads a Premier League team squad page.
 *
 * @param <T> Browser type used by the downloader.
 */
public class PremierLeagueSquadDownloader<T extends Browser> extends Downloader<T> {

    /**
     * Creates a new Premier League squad downloader.
     *
     * @param callable Callable that should produce a properly configured Browser.
     */
    public PremierLeagueSquadDownloader(final Callable<T> callable) {
        super(callable);
    }

    /**
     * Downloads a Premier League squad page.
     *
     * @param teamId The team id.
     * @param name   The team name.
     * @return Web page with title and source of the Premier League squad page.
     */
    public WebPage downloadSquad(final int teamId, final String name) {
        return download(URL_REGISTRY.getPremierLeagueSquadUrl(teamId, name));
    }

}
