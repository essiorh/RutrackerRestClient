package pct.droid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pct.droid.rutrackerrestclient.R;
import pct.droid.rutrackerrestclient.data.containers.MediaContainer;

public class StreamLoadingActivity extends AppCompatActivity {

    public final static String EXTRA_INFO = "mInfo";
    private MediaContainer mediaContainer;

    public static Intent startActivity(Activity activity, MediaContainer mediaContainer) {
        Intent i = new Intent(activity, StreamLoadingActivity.class);
        i.putExtra(EXTRA_INFO, mediaContainer);
        activity.startActivity(i);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_loading);
        Intent intent = getIntent();
        mediaContainer = (MediaContainer) intent.getSerializableExtra(EXTRA_INFO);


    }
}
