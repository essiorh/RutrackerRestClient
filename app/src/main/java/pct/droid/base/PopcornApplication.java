package pct.droid.base;

import android.app.Application;
import android.content.Context;

import java.util.Map;

/**
 * Created by ilia on 17.06.15.
 *
 * @author ilia
 */
public class PopcornApplication extends Application {

    private static final String SHARED_PREFERENCES_KEY 			= "com.rest.rutracker.rutrackerclient";
    private static final String SHARED_PREFERENCES_COOKIE_KEY 	= SHARED_PREFERENCES_KEY +".cookie";

    private static Context appContext;
	private static PopcornApplication instance;
	private Map<String,String> cookie;
	public static Context getAppContext() {
        return appContext;
    }

		public static PopcornApplication getInstance() {
		return instance;
	}

    @Override
    public void onCreate() {
        super.onCreate();
		instance	= this;
        appContext	= getApplicationContext();
    }

	public static void setCookie(Map<String, String> cookie){
		getInstance().cookie	= cookie;
	}

	public static Map<String,String> getCookie(){
		return getInstance().cookie;
	}
}
