package com.iprismech.alertnikkiresidence.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtils {
    private Context context;

    private static SharedPreferences preferences;


    public static SharedPreferences.Editor editor;

    public static final String PREF_NAME = "myprefs";

    public static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String KEY_ID = "id";
    public static final String KEY_ADMIN_ID = "admin_id";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";

    // User name (make variable public to access from outside)
    public static final String KEY_SOCIETY_ID = "society_id";
    public static final String KEY_CITY_ID = "city-id";
    public static final String KEY_FLAT_ID = "flat-id";
    public static final String KEY_BUILDING_ID = "building-id";

    // password (make variable public to access from outside)


    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PROFILE_PIC = "profile_image";
    public static final String KEY_TOKEN = "token_id";

    //user type =1/2
    //residence =1      family member = 2
    public static final String KEY_USER_TYPE = "user_type";


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
        editor.putInt(KEY_ID, id);
        editor.putString(KEY_ADMIN_ID, adminid);
        editor.putString(KEY_PHONE, loginDate);
        editor.putString(KEY_EMAIL, logintime);
        editor.putString(KEY_SOCIETY_ID, society);
        editor.putString(KEY_CITY_ID, city);
        editor.putString(KEY_FLAT_ID, city);
        editor.putString(KEY_BUILDING_ID, city);
        editor.putString(KEY_PASSWORD, city);
        editor.putString(KEY_USER_TYPE, city);
        editor.commit();
    }

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


    public Integer getId() {
        return preferences.getInt(KEY_ID, 0);
    }

    public String getAdminID() {
        return preferences.getString(KEY_ADMIN_ID, "");
    }

    public String getEmial() {
        return preferences.getString(KEY_EMAIL, "");
    }

    public String getPhoneNumber() {
        return preferences.getString(KEY_PHONE, "");
    }

    public String getSocietyid() {
        return preferences.getString(KEY_SOCIETY_ID, "");
    }

    public String getCityId() {
        return preferences.getString(KEY_CITY_ID, "");
    }

    public String getFlatId() {
        return preferences.getString(KEY_FLAT_ID, "");
    }

    public String getBuildingId() {
        return preferences.getString(KEY_BUILDING_ID, "");
    }

    public String getPasscode() {
        return preferences.getString(KEY_PASSWORD, "");
    }

    public String getuserType() {
        //residence =1
        //family member = 2
        return preferences.getString(KEY_USER_TYPE, "");
    }

    public boolean isUserLoggedIn() {
        return preferences.getBoolean(IS_USER_LOGIN, false);
    }


    public static void logoutUser() {
        editor.clear();
        editor.commit();
    }
}
