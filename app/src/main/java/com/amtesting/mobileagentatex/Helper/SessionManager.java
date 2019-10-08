package com.amtesting.mobileagentatex.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.amtesting.mobileagentatex.MainActivity;
import com.amtesting.mobileagentatex.Navigation.login;
import com.amtesting.mobileagentatex.R;

import java.util.HashMap;

/**
 * Created by Mr-AM on 5/26/2017.
 */

public class SessionManager {

    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // nama sharepreference
    private static final String PREF_NAME = "Sesi";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "nama";
    public static final String KEY_ID_AGENT = "id_agent";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String nama,String email, String id_agent,String Phone){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, nama);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ID_AGENT,id_agent);
        editor.putString(KEY_PHONE, Phone);
        editor.commit();
    }

//    public void createSession(String sessionName, String sessionEmail, String sessionIdAgen, String sessionPhone) {
//        // Storing login value as TRUE
//        editor.putBoolean(IS_LOGIN, true);
//        editor.putString(KEY_NAME, sessionName);
//        editor.putString(KEY_EMAIL, sessionEmail);
//        editor.putString(KEY_ID_AGENT,sessionIdAgen);
//        editor.putString(KEY_PHONE, sessionPhone);
//        editor.commit();
//    }

    public void createSession2(String sessionName, String sessionIdAgen) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, sessionName);
        editor.putString(KEY_ID_AGENT,sessionIdAgen);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_ID_AGENT, pref.getString(KEY_ID_AGENT, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        return user;
    }

    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            //((Activity)_context).finish();
        }
        else {
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }

    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

//        Intent i = new Intent(_context, login.class);
////        Intent i = new Intent(_context, MainActivity.class);
////        Intent i= new Intent(_context,LoginFragment.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

}
