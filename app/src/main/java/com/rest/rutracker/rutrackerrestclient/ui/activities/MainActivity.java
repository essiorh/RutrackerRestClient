package com.rest.rutracker.rutrackerrestclient.ui.activities;

import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rest.rutracker.rutrackerrestclient.R;
import com.rest.rutracker.rutrackerrestclient.data.api.ApiService;
import com.rest.rutracker.rutrackerrestclient.data.api.ApiServiceHelper;


public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private Button buttonGetTorrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGetTorrent = (Button) findViewById(R.id.get_torrent);
        buttonGetTorrent.setOnClickListener(this);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_torrent:
                getTorrent();
        }
    }

    private void getTorrent() {
        getCategoriesRequest(new IResponseListener() {
            @Override
            public void onResponse(long id) {
                //getCategoriesFromDb();
            }
        }, null);
    }

    public void getCategoriesRequest(final IResponseListener responseListener
            , final IErrorListener errorListener) {

        ApiServiceHelper.getCategories(new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultData.containsKey(ApiService.ERROR_KEY)) {
                    if (errorListener != null) {
                        errorListener.onError();
                    }
                } else {
                    if (responseListener != null) {
                        responseListener.onResponse(0L);
                    }
                }
            }
        });
    }

    protected interface IErrorListener {
        void onError();
    }

    protected interface IResponseListener {
        void onResponse(long id);
    }
}
