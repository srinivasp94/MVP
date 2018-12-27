package com.iprismech.alertnikki.app.factories.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismech.alertnikki.app.factories.constants.AppConstants.EventIds.LAUNCH_CAMERA_SCREEN;
import static com.iprismech.alertnikki.app.factories.constants.AppConstants.EventIds.LAUNCH_DELIVERY_BOY_SCREEN;
import static com.iprismech.alertnikki.app.factories.constants.AppConstants.EventIds.LAUNCH_GUEST_DETAILS_SCREEN;
import static com.iprismech.alertnikki.app.factories.constants.AppConstants.EventIds.LAUNCH_MAIN_SCREEN;
import static com.iprismech.alertnikki.app.factories.constants.AppConstants.EventIds.LAUNCH_QRCODE;
import static com.iprismech.alertnikki.app.factories.constants.AppConstants.EventIds.LAUNCH_SECURITY_LOGIN_SCREEN;
import static com.iprismech.alertnikki.app.factories.constants.AppConstants.EventIds.LAUNCH_SPLASH_SCREEN;
import static com.iprismech.alertnikki.app.factories.constants.AppConstants.EventIds.LAUNCH_THROUGH_VEHICLE_SCREEN;
import static com.iprismech.alertnikki.app.factories.constants.AppConstants.EventIds.LAUNCH_WELCOME_SCREEN;


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
            LAUNCH_SPLASH_SCREEN,
            LAUNCH_SECURITY_LOGIN_SCREEN,
            LAUNCH_WELCOME_SCREEN,
            LAUNCH_MAIN_SCREEN,
            LAUNCH_CAMERA_SCREEN,
            LAUNCH_QRCODE, LAUNCH_DELIVERY_BOY_SCREEN,
            LAUNCH_THROUGH_VEHICLE_SCREEN,
            LAUNCH_GUEST_DETAILS_SCREEN

    })
    @interface EventIds {

        int LAUNCH_SPLASH_SCREEN = 101;
        int LAUNCH_SECURITY_LOGIN_SCREEN = 102;
        int LAUNCH_CAMERA_SCREEN = 103;
        int LAUNCH_WELCOME_SCREEN = 104;
        int LAUNCH_MAIN_SCREEN = 105;
        int LAUNCH_QRCODE = 106;
        int LAUNCH_DELIVERY_BOY_SCREEN = 107;
        int LAUNCH_THROUGH_VEHICLE_SCREEN = 108;
        int LAUNCH_GUEST_DETAILS_SCREEN = 109;

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
