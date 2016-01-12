package hu.padar.app.quicknote.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Pádi on 2016. 01. 12..
 */
public class StorageHelper {
    private static final String SHARED_PREFERENCES_NAME = "QUICKNOTE_ANDROID_SHARED_PREFERENCES";
    private static final String KEY_CURRENT_USER = "NO_USER_LOGGED_IN_CURRENTLY";
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public StorageHelper(Context context) {
        this.context = context;
        sharedPreferences = getSharedPref(context);
        editor = sharedPreferences.edit();
    }

    private SharedPreferences getSharedPref(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public String getCurrentUser() {
        return sharedPreferences.getString(KEY_CURRENT_USER, KEY_CURRENT_USER);
    }

    public void setCurrentUser(String newName) {
        editor.putString(KEY_CURRENT_USER, newName);
        editor.commit();
    }

}
