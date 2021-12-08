package org.d11.boot.download.premierleague;

import org.d11.boot.download.Downloader;
import org.d11.boot.download.browser.Browser;

import java.util.concurrent.Callable;

/**
 * Downloader that downloads a Premier League player photo.
 *
 * @param <T> Browser type used by the downloader.
 */
public class PremierLeaguePlayerPhotoDownloader<T extends Browser> extends Downloader<T> {

    /**
     * Creates a new Premier League player photo downloader.
     *
     * @param callable Callable that should produce a properly configured Browser.
     */
    public PremierLeaguePlayerPhotoDownloader(final Callable<T> callable) {
        super(callable);
    }

    /**
     * Downloads a Premier League player photo.
     *
     * @param photoId   Id of the Premier League player photo.
     * @return Web page with title and source of the Premier League player page.
     */
    public byte[] downloadPlayerPhoto(final String photoId) {
        return downloadBytes(URL_REGISTRY.getPremierLeaguePlayerPhotoUrl(photoId));
    }

}
