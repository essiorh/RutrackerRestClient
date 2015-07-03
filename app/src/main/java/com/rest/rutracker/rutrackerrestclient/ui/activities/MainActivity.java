package com.rest.rutracker.rutrackerrestclient.ui.activities;

import android.net.Uri;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rest.rutracker.rutrackerrestclient.R;
import com.rest.rutracker.rutrackerrestclient.data.api.ApiService;
import com.rest.rutracker.rutrackerrestclient.data.api.ApiServiceHelper;
import com.rest.rutracker.rutrackerrestclient.data.api.request.ViewTopicRequest;
import com.rest.rutracker.rutrackerrestclient.data.api.response.DescriptionDataResponse;
import com.rest.rutracker.rutrackerrestclient.ui.fragment.VideoListFragment;


public class MainActivity extends AppCompatActivity implements VideoListFragment.OnFragmentInteractionListener {
    /**
     * USed in {@link IResponseListener} for detect that we received LIST OF XML ENTRY
     */
    public static final int CODE_GET_TORRENT_FEED=0;
    private static final int CODE_GET_IMAGE = 1;
    private static final String KEY_TORRENT_VIEW_TOPIC = "4869690";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setup custom action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        VideoListFragment videoListFragment=VideoListFragment.newInstance("name","name2");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FragmentContainer, videoListFragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            getTorrent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void getTorrent() {
        getImageUrlRequest(new IResponseListener() {
            @Override
            public void onResponse(Object object, int code) {
                if(code == CODE_GET_IMAGE){
                    ///
                    String imageUrl = ((DescriptionDataResponse)object).getUrlImage();
                    Log.d(imageUrl,imageUrl);
                }
            }
        }, null);

    }

    public void getImageUrlRequest(final IResponseListener responseListener
            , final IErrorListener errorListener) {

        ApiServiceHelper.getImageUrl(new ViewTopicRequest(KEY_TORRENT_VIEW_TOPIC),new ResultReceiver(new Handler()) {
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface IErrorListener {
        void onError();
    }

    public interface IResponseListener {
        void onResponse(Object id, int code);
    }
}
