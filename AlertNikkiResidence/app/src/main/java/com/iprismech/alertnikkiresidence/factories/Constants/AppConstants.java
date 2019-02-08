package com.iprismech.alertnikkiresidence.factories.Constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by Prasad on 7/5/2017.
 */
public interface AppConstants {

    String LOG_CAT = ">>logs : ";

    /**
     * Application Controller events ids
     * Maintain all app level event ids and def of that event ids
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({


    })
    @interface EventIds {

        int LAUNCH_SPLASH_SCREEN = 101;
        int LAUNCH_INTRO_SCREEN = 102;
        int LAUNCH_MAIN_SCREEN = 103;
        int LAUNCH_LOGIN_SCREEN = 104;
        int LAUNCH_SIGNUP_SCREEN = 105;
        int LAUNCH_OTPVERIFICATION_SCREEN = 106;
        int LAUNCH_PASSCODE_SCREEN = 107;
        int LAUNCH_INVITE_GUEST_SCREEN = 108;
        int LAUNCH_PICK_CONTACT_SCREEN = 109;
        int LAUNCH_VIEW_INVITE_GUEST_SCREEN = 110;
        int LAUNCH_EDIT_GUEST_SCREEN = 111;

        int LAUNCH_SELECT_CITY_SCREEN = 112;
        int LAUNCH_SELECT_SOCIETY_SCREEN = 113;
        int LAUNCH_SELECT_BUILDING_SCREEN = 114;
        int LAUNCH_SELECT_FLAT_SCREEN = 115;

        int LAUNCH_CHOOSE_MAID = 120;
        int LAUNCH_ADD_STAFF_SCREEN = 121;
        int LAUNCH_MYSTAFF_ALERTS = 122;
        int LAUNCH_STAFF_PROFILE = 123;
        int LAUNCH_STANDARD_TIMINGS = 124;


        int LAUNCH_KIDS_NOTIFY_ALERTS = 140;
        int LAUNCH_ADD_KID_SCREEN = 141;
        int LAUNCH_LOCAL_SERVICE_SCREEN = 142;
        int LAUNCH_CHOOSE_LOCAL_SERVICE_SCREEN = 143;
        int LAUNCH_LOCAL_SERVICE_DETAILS_SCREEN = 144;
        int LAUNCH_MAID_STAFF_ATTENDANCE_HISTORY_SCREEN = 145;
        int LAUNCH_NOTICE_BOARD_SCREEN=146;
        int LAUNCH_EMERGENCY_CONTACT_SCREEN=147;
        int LAUNCH_MANAGEMENT_COMMITTE_SCREEN=148;
        int LAUNCH_DIGITAL_INTERCOM_SCREEN=149;
        int LAUNCH_CONTACT_US_SCREEN=150;
        int LAUNCH_VISITORS_HISTORY_SCREEN=151;
        int LAUNCH_MAID_ATTENDANCE_HISTORY_SCREEN=152;


    }

    public static final long INTERVAL = 1000 * 1;
    public static final long FASTEST_INTERVAL = 500 * 1;


    /**
     * Maintain the PREFERENCES Keys and constant
     */
    interface PREFERENCES {
        String IS_LAUNCH_HOME_SCREEN = "isLaunchHomeScreen";
        String KEY_PREF_X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String KEY_HOST_URL = "hostUrl";
    }

}
