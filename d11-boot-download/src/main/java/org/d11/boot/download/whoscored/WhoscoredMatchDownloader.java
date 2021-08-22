package org.d11.boot.download.whoscored;

import org.d11.boot.download.Downloader;
import org.d11.boot.download.WebPage;
import org.d11.boot.download.browser.Browser;

import java.util.concurrent.Callable;

/**
 * Downloader that downloads a WhoScored match page.
 *
 * @param <T> Browser type used by the downloader.
 */
public class WhoscoredMatchDownloader<T extends Browser> extends Downloader<T> {

    /**
     * Creates a new WhoScored match downloader.
     *
     * @param callable Callable that should produce a properly configured Browser.
     */
    public WhoscoredMatchDownloader(final Callable<T> callable) {
        super(callable);
    }

    /**
     * Downloads a WhoScored match page for the match with the provided id.
     *
     * @param whoscoredId Whoscored id of the match that will be downloaded.
     * @return Web page with title and source of the WhoScored match page.
     */
    public WebPage downloadMatch(final long whoscoredId) {
        return download(URL_REGISTRY.getWhoscoredMatchUrl(whoscoredId));
    }

}
