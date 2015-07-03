package com.rest.rutracker.rutrackerrestclient.data.api.response;

/**
 * Created by ilia on 03.07.15.
 *
 * @author ilia
 */
public class DescriptionDataResponse extends DataResponse {

    private String urlImage;

    public DescriptionDataResponse(String urlImage) {
        super();
        this.urlImage = urlImage;
    }


    public String getUrlImage() {
        return urlImage;
    }
}
