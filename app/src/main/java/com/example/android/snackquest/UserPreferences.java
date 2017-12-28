package com.example.android.snackquest;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by caporal on 28/10/17.
 */

public class UserPreferences {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final int MODE = 0;
    private static final String FILE_NAME = "snack_quest.user_preferences";
    private static final String KEY_USER = "id_logged_user";


    public UserPreferences(Context context) {
        this.context = context;

        preferences = context.getSharedPreferences(FILE_NAME, MODE);
        editor = preferences.edit();
    }

    public void saveUserPreferences(User user){
        String userJson = new Gson().toJson(user);
        editor.putString(KEY_USER, userJson);
        editor.apply();
    }

    public void removeUserPreferences(){
        editor.clear();
        editor.apply();
    }

    public String getUserName(){
        String string = preferences.getString(KEY_USER, null);
        User user = new Gson().fromJson(string, User.class);
        return user.getName();
    }

    public User getUser(){
        String string = preferences.getString(KEY_USER, null);
        User user = new Gson().fromJson(string, User.class);
        return user;
    }

    public boolean hasLoggedUser(){
        return preferences.getString(KEY_USER, null) != null;
    }

}
