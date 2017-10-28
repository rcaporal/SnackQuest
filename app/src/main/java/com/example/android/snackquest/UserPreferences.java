package com.example.android.snackquest;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by caporal on 28/10/17.
 */

public class UserPreferences {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String FILE_NAME = "snack_quest.user_preferences";
    private int MODE = 0;

    private static final String KEY_ID = "id_logged_user";
    private static final String KEY_NAME = "name_logged_user";


    public UserPreferences(Context context) {
        this.context = context;

        preferences = context.getSharedPreferences(FILE_NAME, MODE);
        editor = preferences.edit();
    }

    public void saveUserPreferences(String userID, String userName){
        editor.putString(KEY_ID, userID);
        editor.putString(KEY_NAME, userName);
        editor.commit();
    }

    public String getUserID(){
        return preferences.getString(KEY_ID, null);
    }

    public String getUserName(){
        return preferences.getString(KEY_NAME, null);
    }

}
