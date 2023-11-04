package com.example.firebaseproject;

import android.content.Context;
import android.content.SharedPreferences;

public class DataWriter {
    private static Context context;

    public DataWriter(Context context){
        this.context = context;
    }

    public final static String PREFS_NAME = "fbProject_prefs";

    public boolean sharedPreferenceExist(String key) {
        // Test saved values
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.contains(key);
    }

    public static void setuserId (String categ) {
        // Save the String to userId
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        prefs.edit().remove("userId").apply();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userId", categ);
        editor.apply();
    }

    public static String getuserId (){
        // Load the String to userId
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString("userId", null);
    }

    public static void clearuserId (){
        // Clear the String to userId
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        prefs.edit().remove("userId").apply();
    }
}
