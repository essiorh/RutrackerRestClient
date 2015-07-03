package com.rest.rutracker.rutrackerrestclient.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rest.rutracker.rutrackerrestclient.R;
import com.rest.rutracker.rutrackerrestclient.data.api.ApiService;
import com.rest.rutracker.rutrackerrestclient.data.api.ApiServiceHelper;
import com.rest.rutracker.rutrackerrestclient.data.api.request.ViewTopicRequest;
import com.rest.rutracker.rutrackerrestclient.data.api.response.DescriptionDataResponse;
import com.rest.rutracker.rutrackerrestclient.data.containers.InfoContainer;
import com.rest.rutracker.rutrackerrestclient.data.containers.MediaContainer;
import com.squareup.picasso.Picasso;

import pct.droid.activities.StreamLoadingActivity;

public class DetailActivity extends AppCompatActivity implements Button.OnClickListener {

    public static final String TORRENT_VIEW_TOPIC_LINK = "VIEW_TOPIC_LINK";
    private static final int CODE_GET_IMAGE = 1;

    private static final String BASE_TORRENT_LINK = "http://dl.rutracker.org/forum/dl.php?t=";

    private static final String DEFAULT_TORRENT_NAME = "The best torrent of the world";
    private static final String DEFAULT_KEY_TORRENT_VIEW_TOPIC = "4869690";

    private Button buttonPlay;
    private ImageView imageFromTorrent;

    private String keyTorrentViewTopic;
    private String nameTorrent;
    private MediaContainer mediaContainer;
    private InfoContainer infoContainer;
    private String imageUrl;

    public final static String EXTRA_INFO = "mInfo";

    public static Intent startActivity(Context context, InfoContainer info) {
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(EXTRA_INFO, info);
        context.startActivity(i);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        infoContainer = null;
        infoContainer = (InfoContainer) intent.getSerializableExtra(EXTRA_INFO);
        if (infoContainer!=null) {
            keyTorrentViewTopic=infoContainer.getTorrentKey();
            nameTorrent=infoContainer.getTorrentName();
        }

        buttonPlay = (Button) findViewById(R.id.buttonLoadTorrentFile);
        buttonPlay.setOnClickListener(this);

        getImageUrl();

        imageFromTorrent = (ImageView) findViewById(R.id.image_from_torrent);
    }





    private void getImageUrl() {
        getImageUrlRequest(new MainActivity.IResponseListener() {
            @Override
            public void onResponse(Object object, int code) {
                if (code == CODE_GET_IMAGE) {
                    ///
                    imageUrl = ((DescriptionDataResponse) object).getUrlImage();
                    getImageFromUrlWithPicasso(imageUrl);
                    Log.d(imageUrl, imageUrl);
                }
            }
        }, null);
    }

    void getImageFromUrlWithPicasso(String imageUrl){
        Picasso.with(this)
                .load(imageUrl)
                .into(imageFromTorrent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLoadTorrentFile:
                if (mediaContainer==null) {
                    mediaContainer=new MediaContainer(BASE_TORRENT_LINK + keyTorrentViewTopic,
                            nameTorrent, imageUrl);
                }
                StreamLoadingActivity.startActivity(this ,mediaContainer);
                break;
        }
    }

    public void getImageUrlRequest(final MainActivity.IResponseListener responseListener
            , final MainActivity.IErrorListener errorListener) {

        ApiServiceHelper.getImageUrl(new ViewTopicRequest(keyTorrentViewTopic), new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultData.containsKey(ApiService.ERROR_KEY)) {
                    if (errorListener != null) {
                        errorListener.onError();
                    }
                } else {
                    if (responseListener != null) {
                        responseListener.onResponse(resultData.getSerializable(ApiService.RESPONSE_OBJECT_KEY), CODE_GET_IMAGE);
                    }
                }
            }
        });
    }


}
