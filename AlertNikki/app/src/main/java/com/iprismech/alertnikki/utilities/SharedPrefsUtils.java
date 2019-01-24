package com.iprismech.alertnikki.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SharedPrefsUtils {
    private Context context;

    private static SharedPreferences preferences;


    public static SharedPreferences.Editor editor;

    public static final String PREF_NAME = "myprefs";

    public static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String KEY_SECURITY_ID = "security_id";
    public static final String KEY_ADMIN_ID = "admin_id";
    public static final String KEY_LOGIN_DATE = "login_date";
    public static final String KEY_LOGIN_TIME = "login_time";


    // User name (make variable public to access from outside)
    public static final String KEY_SOCIETY = "society";
    public static final String KEY_CITY = "city";
    public static final String KEY_WAITING_COUNT = "waiting_count";
    public static final String KEY_INSIDE_COUNT = "inside_count";

    // password (make variable public to access from outside)


    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PROFILE_PIC = "profile_image";
    public static final String KEY_TOKEN = "token_id";




     /*"admin_id": "2",
             "security_id": "1",
             "image": "storage/security_login/5c189a299f8bf.",
             "login_date": "2018-12-18",
             "login_time": "12:26:41",
             "city": "hyderabad",
             "society": "Prime Towers"*/

    private static SharedPrefsUtils prefsUtils;

    public static SharedPrefsUtils getInstance(Context context) {
        if (prefsUtils == null) {
            prefsUtils = new SharedPrefsUtils(context);
        }
        return prefsUtils;
    }

    public SharedPrefsUtils(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    //constructor

    public SharedPrefsUtils(Context context, SharedPreferences preferences) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }


    //Saving user details
    public void createUserSession(int id, String adminid, String loginDate, String logintime, String society, String city) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putInt(KEY_SECURITY_ID, id);
        editor.putString(KEY_ADMIN_ID, adminid);
        editor.putString(KEY_LOGIN_DATE, loginDate);
        editor.putString(KEY_LOGIN_TIME, logintime);
        editor.putString(KEY_SOCIETY, society);
        editor.putString(KEY_CITY, city);
        editor.commit();
    }

    public void visitor_count(int inside_count,int waiting_count){
        editor.putInt(KEY_INSIDE_COUNT, inside_count);
        editor.putInt(KEY_WAITING_COUNT, waiting_count);
        editor.commit();
    }

    //Retrive User details

  /*  public HashMap<String, String> getUserdetails() {
        HashMap<String, String> userMap = new HashMap<>();
        try {
            userMap.put(KEY_USERID, String.valueOf(preferences.getInt(KEY_USERID, 1)));
            userMap.put(KEY_Security_ID, preferences.getString(KEY_Security_ID, ""));
            userMap.put(KEY_MOBILENO, preferences.getString(KEY_MOBILENO, ""));
            userMap.put(KEY_USERNAME, preferences.getString(KEY_USERNAME, ""));
            userMap.put(KEY_GENDER, preferences.getString(KEY_GENDER, ""));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMap;
    }*/


    public static void setString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static void updateString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key) {
        return preferences.getString(key, "");
    }


    public Integer getsecurityId() {
        return preferences.getInt(KEY_SECURITY_ID, 0);
    }

    public Integer getInsdiecount() {
        return preferences.getInt(KEY_INSIDE_COUNT, 0);
    }
    public Integer getwaitingcount() {
        return preferences.getInt(KEY_WAITING_COUNT, 0);
    }
    public String getAdmin() {

        return preferences.getString(KEY_ADMIN_ID, "");
    }

    public String getLogindate() {
        return preferences.getString(KEY_LOGIN_DATE, "");
    }

    public String getLoginTime() {
        return preferences.getString(KEY_LOGIN_TIME, "");
    }

    public String getSociety() {
        return preferences.getString(KEY_SOCIETY, "");
    }

    public String getCity() {
        return preferences.getString(KEY_CITY, "");
    }

    public boolean isUserLoggedIn() {

        return preferences.getBoolean(IS_USER_LOGIN, false);
    }


    public static void logoutUser() {
        editor.clear();
        editor.commit();
    }
}
