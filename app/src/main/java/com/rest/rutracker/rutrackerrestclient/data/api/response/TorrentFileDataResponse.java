package com.rest.rutracker.rutrackerrestclient.data.api.response;

/**
 * Created by G on 05.07.15.
 */
public class TorrentFileDataResponse extends DataResponse {
	private String torrentFile;
	public TorrentFileDataResponse(String torrentFile){
		this.torrentFile	= torrentFile;
	}

	public String getTorrentFile() {
		return torrentFile;
	}
}
