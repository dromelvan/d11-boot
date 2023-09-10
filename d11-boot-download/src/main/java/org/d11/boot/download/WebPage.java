package org.d11.boot.download;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a downloaded web page.
 */
@Data
@AllArgsConstructor
public class WebPage {

    /**
     * The title of the downloaded web page.
     */
    private String title;

    /**
     * The page source of the downloaded web page.
     */
    private String pageSource;

    @Override
    public String toString() {
        return this.pageSource;
    }

}
