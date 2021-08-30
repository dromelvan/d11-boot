package org.d11.boot.application.model.jms;

import lombok.Data;

/**
 * JMS message to put on the download WhoScored match queue to trigger a download.
 */
@Data
public class DownloadWhoscoredMatchMessage extends JmsMessage {

    /**
     * D11 id of the match that will be downloaded.
     */
    private long id;
    /**
     * WhoScored id of the match that will be downloaded.
     */
    private long whoscoredId;
    /**
     * Match week number of the match that will be downloaded.
     */
    private int matchWeekNumber;
    /**
     * Name of the season the match match week belongs to.
     */
    private String seasonName;
    /**
     * The match status should/should not be set as finished after update.
     */
    private Boolean finish;

}
