package com.iprismech.alertnikkiresidence.factories.controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.iprismech.alertnikkiresidence.MyApplication;
import com.iprismech.alertnikkiresidence.factories.ViewFactory;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;


/**
 * Created by prasad on 05-07-17.
 * ApplicationController.java
 * <p/>
 * The ApplicationController Class, which helps to manage different Controllers,
 * Models, Views. This Class will be initialize as the platform requirement.
 */

public class ApplicationController {

    /**
     * private instance of ApplicationController for singleton Design Pattern
     */
    private static ApplicationController instance;

    /**
     * private instance of UIController for managing different AbstractViews
     */
    public UiController uiController;

    /**
     * private instance of ViewFactory for fast accessing.
     */
    public ViewFactory viewFactory;

    public Activity mActivity;
    public Context mContext;

    private MyApplication application;

    /**
     * Constructor of ApplicationController
     */
    private ApplicationController() {
        uiController = UiController.getInstance();
    }

    /**
     * Get Single instance of ApplicationController
     *
     * @return ApplicationController single instance
     */
    public static ApplicationController getInstance() {
        if (instance == null) {
            // creating new instance of application controller
            instance = new ApplicationController();
        }
        return instance;
    }
//
//    /**
//     * This Function will get called from DriverLoginActivity or Any Activity which is
//     * going to display first screen after launching this application
//     */
//    public void initialize() {
//
//        // initialize the ModelFacade
//        modelFacade.initialize();
//
//        // set the reference for UI Controller
//        uiController = UIController.getInstance();
//
//        // initialize the UIController
//        uiController.initialize();
//
//        // set the viewFactory reference for further use in code.
//        viewFactory = ViewFactory.getInstance();
//
//    }

    /**
     * returns the current mActivity
     *
     * @return
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * sets the reference of current mActivity
     *
     * @param mActivity
     */
    public void setActivity(@NonNull Activity mActivity) {
        this.mActivity = mActivity;
    }


    /**
     * @return the application mContext
     */
    public MyApplication getApplication() {
        return application;
    }

    /**
     * sets the reference of application
     *
     * @param application
     */
    public void setApplication(MyApplication application) {
        this.application = application;
    }

    /**
     * @return the mContext of current mActivity
     */
    public Context getContext() {
        return mContext;
    }


    /**
     * sets the reference of mContext
     *
     * @param
     */
    public void setContext(@NonNull Context mContext) {
        this.mContext = mContext;
    }


    /**
     * Handle Event Based on Event ID
     *
     * @param eventId Event raised by View
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void handleEvent(@AppConstants.EventIds int eventId) {
        handleEvent(eventId, null);
    }


    /**
     * Handle Event Based on Event ID and Object
     *
     * @param eventId      Event Id based on user actions
     * @param eventObjects It stores the data for the given Event. so it can forward to
     *                     other events
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint({"WrongConstant", "SwitchIntDef"})
    public void handleEvent(@AppConstants.EventIds int eventId, Object eventObjects) {
        Log.d(AppConstants.LOG_CAT, "handleEvent : " + eventId);

        switch (eventId) {
            case AppConstants.EventIds.LAUNCH_SPLASH_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SPLASH_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_INTRO_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.INTRO_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_MAIN_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.MAIN_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_LOGIN_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.LOGIN_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SIGNUP_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_OTPVERIFICATION_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.OTPVERIFICATION_SCREEN,(Bundle)eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_PASSCODE_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.PASSCODE_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_INVITE_GUEST_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.INVITE_GUEST_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_PICK_CONTACT_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.PICK_CONTACT_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_VIEW_INVITE_GUEST_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.VIEW_INVITE_GUEST_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_EDIT_GUEST_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.EDIT_GUEST_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_SELECT_CITY_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SELECT_CITY_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_SELECT_SOCIETY_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SELECT_SOCIETY_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_SELECT_BUILDING_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SELECT_BUILDING_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_SELECT_FLAT_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SELECT_FLAT_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_CHOOSE_MAID:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.CHOOSE_MAID_SCREEN, (Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_ADD_STAFF_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.ADD_STAFF_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_MYSTAFF_ALERTS:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.MYSTAFF_ALERTS_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_STAFF_PROFILE:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.STAFF_PROFILE_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_STANDARD_TIMINGS:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.STANDARD_TIMINGS_SCREEN, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_MAIN_NOTIFY_GATE:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.MAIN_NOTIFY_GATE);
                break;
            case AppConstants.EventIds.LAUNCH_GATE_SERVICE:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.GATE_SERVICE);
                break;
            case AppConstants.EventIds.LAUNCH_EDIT_GATE_SERVICE:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.EDIT_GATE_SERVICE, (Bundle) eventObjects);
                break;

            case AppConstants.EventIds.LAUNCH_SCHOOL_BUS_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SCHOOL_BUS_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_SELECT_BUS_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SELECT_BUS_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_BUS_ROUTE_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.BUS_ROUTE_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_ADD_BUS_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.ADD_BUS_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_BUS_HISTORY_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.BUS_HISTORY_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_PROFILE_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.PROFILE_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_FAMILY_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.FAMILY_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_ADD_FAMILY_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.ADD_FAMILY_SCREEN,(Bundle) eventObjects);
                break;
            case AppConstants.EventIds.LAUNCH_OONTACT_SINGLE_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.OONTACT_SINGLE_SCREEN);
                break;
            default:
                throw new IllegalStateException("Invalid Event id");
        }
    }
}
