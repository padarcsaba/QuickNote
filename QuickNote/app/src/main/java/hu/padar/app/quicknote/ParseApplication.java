package hu.padar.app.quicknote;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Pádi on 2016. 01. 12..
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));
    }
}
