package com.iprismech.alertnikkiresidence.factories;

import android.support.annotation.IntDef;


import com.iprismech.alertnikkiresidence.MainActivity;
import com.iprismech.alertnikkiresidence.activity.SplashScreenActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.INTRO_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.LOGIN_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MAIN_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SIGNUP_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SPLASH_SCREEN;


/**
 * Created by prasad on 05/07/2017.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */


public class ViewFactory {

    @Retention(RetentionPolicy.CLASS)
    @IntDef({SPLASH_SCREEN, INTRO_SCREEN,MAIN_SCREEN,LOGIN_SCREEN,SIGNUP_SCREEN})
    public @interface ScreenIds {

        int SPLASH_SCREEN = 1001;
        int INTRO_SCREEN = 1002;
        int MAIN_SCREEN = 1003;
        int LOGIN_SCREEN = 1004;
        int SIGNUP_SCREEN = 1005;


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
            case SPLASH_SCREEN: {
                return SplashScreenActivity.class;
            }
            case INTRO_SCREEN:
                return MainActivity.class;
            case MAIN_SCREEN:
                return MainActivity.class;
            case LOGIN_SCREEN:
                return SplashScreenActivity.class;
            case SIGNUP_SCREEN:
                return SplashScreenActivity.class;
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

            default:
                throw new IllegalStateException("Invalid Event id");

        }

    }
}