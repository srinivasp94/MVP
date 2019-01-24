package com.iprismech.alertnikki.app.factories;

import android.support.annotation.IntDef;


import com.iprismech.alertnikki.MainActivity;
import com.iprismech.alertnikki.activity.AddGuestActivity;
import com.iprismech.alertnikki.activity.CameraActivity;
import com.iprismech.alertnikki.activity.DeliveryActivity;
import com.iprismech.alertnikki.activity.GuestDetailsActivity;
import com.iprismech.alertnikki.activity.SecurityLoginActivity;
import com.iprismech.alertnikki.activity.ThroughVehicleActivity;
import com.iprismech.alertnikki.activity.WelcomeActivity;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.fragments.QrCode_Fragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.ADD_GUEST;
import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.CAMERA_SCREEN;
import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.DELIVERY_BOY;
import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.GUEST_DETAILS;
import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.INTROSCREEN;
import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.MAIN_SCREEN;

import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.QRCODE;
import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.SECURITY_LOGIN_SCREEN;
import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.THROUGH_VEHICLE;
import static com.iprismech.alertnikki.app.factories.ViewFactory.ScreenIds.WELCOME_SCREEN;


/**
 * Created by prasad on 05/07/2017.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */


public class ViewFactory {

    @Retention(RetentionPolicy.CLASS)
    @IntDef({ScreenIds.SPLASH_SCREEN, SECURITY_LOGIN_SCREEN, CAMERA_SCREEN,
            WELCOME_SCREEN, MAIN_SCREEN, QRCODE, INTROSCREEN, DELIVERY_BOY, THROUGH_VEHICLE, GUEST_DETAILS,ADD_GUEST})
    public @interface ScreenIds {

        int SPLASH_SCREEN = 1000;
        int SECURITY_LOGIN_SCREEN = 1001;
        int CAMERA_SCREEN = 1002;
        int WELCOME_SCREEN = 1003;
        int MAIN_SCREEN = 1004;

        int QRCODE = 1005;

        int INTROSCREEN = 1036;
        int DELIVERY_BOY = 1007;
        int GUEST_DETAILS = 1008;
        int THROUGH_VEHICLE = 1037;
        int ADD_GUEST = 1038;

    }


    /**
     * Reference of Application Controller
     */
    private ApplicationController mApplicationController = null;

    /**
     * Constructor
     */
    private ViewFactory() {
        mApplicationController = ApplicationController.getInstance();
    }

    /**
     * This function should only be used when whole application is made by
     * multiple activity.
     *
     * @param id
     * @return Activity class
     */
    public static Class getActivityClass(@ScreenIds int id) {

        switch (id) {
            case SECURITY_LOGIN_SCREEN:
                return SecurityLoginActivity.class;
            case CAMERA_SCREEN:
                return CameraActivity.class;
            case WELCOME_SCREEN:
                return WelcomeActivity.class;
            case MAIN_SCREEN:
                return MainActivity.class;
            case QRCODE:
                return QrCode_Fragment.class;
            case DELIVERY_BOY:
                return DeliveryActivity.class;
            case THROUGH_VEHICLE:
                return ThroughVehicleActivity.class;
            case GUEST_DETAILS:
                return GuestDetailsActivity.class;
            case ADD_GUEST:
                return AddGuestActivity.class;
            default:
                throw new IllegalStateException("Invalid screen id");
        }

    }


    /**
     * This function should only be used when whole application is made by
     * multiple Fragment.
     *
     * @param id
     * @return Fragment class
     */
    public static Class getFragmentClass(@ScreenIds int id) {

        switch (id) {
            //todo logic for fragments are same
//            case SPLASH_SCREEN: {
//                return SplashActivity.class;
//            }
//
//            case LOGIN_SCREEN: {
//                return DriverLoginActivity.class;
//            }
//
//            case SIGNUP_SCREEN: {
//                return HomeActivity.class;
//            }

            default:
                throw new IllegalStateException("Invalid Event id");

        }

    }
}