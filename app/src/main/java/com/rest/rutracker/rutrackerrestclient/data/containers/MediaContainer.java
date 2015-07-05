package com.rest.rutracker.rutrackerrestclient.data.containers;

import java.io.Serializable;

import pct.droid.activities.StreamLoadingActivity;

/**
 * Created by ilia on 03.07.15.
 *
 * @author ilia
 */
public class MediaContainer implements Serializable{
    private String torrentUrl;
    private String torrentName;
    private String imageUrl;
    private String torrentBody;

    public MediaContainer(String torrentUrl, String torrentName, String imageUrl, String torrentBody) {
        this.torrentUrl 	= torrentUrl;
        this.torrentName 	= torrentName;
        this.imageUrl 		= imageUrl;
        this.torrentBody    = torrentBody;
    }

    public String getTorrentBody() {
        return torrentBody;
    }

}
