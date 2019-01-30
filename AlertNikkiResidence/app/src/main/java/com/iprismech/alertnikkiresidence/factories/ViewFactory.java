package com.iprismech.alertnikkiresidence.factories;

import android.support.annotation.IntDef;


import com.iprismech.alertnikkiresidence.MainActivity;
import com.iprismech.alertnikkiresidence.activity.AddStaffActivity;
import com.iprismech.alertnikkiresidence.activity.ChooseMaidActivity;
import com.iprismech.alertnikkiresidence.activity.GuestEditActivity;
import com.iprismech.alertnikkiresidence.activity.IntroScreensActivity;
import com.iprismech.alertnikkiresidence.activity.InviteGuestActivity;
import com.iprismech.alertnikkiresidence.activity.LoginActivity;
import com.iprismech.alertnikkiresidence.activity.MyStaffAlerts;
import com.iprismech.alertnikkiresidence.activity.OtpVerificationActivity;
import com.iprismech.alertnikkiresidence.activity.PasscodeActivity;
import com.iprismech.alertnikkiresidence.activity.PickContactsActivity;
import com.iprismech.alertnikkiresidence.activity.SelectCityActivity;
import com.iprismech.alertnikkiresidence.activity.SelectSocietyActivity;
import com.iprismech.alertnikkiresidence.activity.SignupActivity;
import com.iprismech.alertnikkiresidence.activity.SplashScreenActivity;
import com.iprismech.alertnikkiresidence.activity.StaffProfileActivity;
import com.iprismech.alertnikkiresidence.activity.ViewInviteGuestActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.ADD_STAFF_SCREEN;

import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.CHOOSE_MAID_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.EDIT_GUEST_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.INTRO_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.INVITE_GUEST_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.LOGIN_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MAIN_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MYSTAFF_ALERTS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.OTPVERIFICATION_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.PASSCODE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.PICK_CONTACT_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_BUILDING_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_CITY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_FLAT_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_SOCIETY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SIGNUP_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SPLASH_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.STAFF_PROFILE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.VIEW_INVITE_GUEST_SCREEN;


/**
 * Created by prasad on 05/07/2017.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */


public class ViewFactory {

    @Retention(RetentionPolicy.CLASS)
    @IntDef({SPLASH_SCREEN, INTRO_SCREEN, MAIN_SCREEN, LOGIN_SCREEN, SIGNUP_SCREEN, OTPVERIFICATION_SCREEN,
            PASSCODE_SCREEN, INVITE_GUEST_SCREEN, PICK_CONTACT_SCREEN, VIEW_INVITE_GUEST_SCREEN,
            EDIT_GUEST_SCREEN, SELECT_CITY_SCREEN, SELECT_SOCIETY_SCREEN, SELECT_BUILDING_SCREEN,
            SELECT_FLAT_SCREEN, CHOOSE_MAID_SCREEN, ADD_STAFF_SCREEN, MYSTAFF_ALERTS_SCREEN, STAFF_PROFILE_SCREEN})
    public @interface ScreenIds {

        int SPLASH_SCREEN = 1001;
        int INTRO_SCREEN = 1002;
        int MAIN_SCREEN = 1003;
        int LOGIN_SCREEN = 1004;
        int SIGNUP_SCREEN = 1005;
        int OTPVERIFICATION_SCREEN = 1006;
        int PASSCODE_SCREEN = 1007;
        int INVITE_GUEST_SCREEN = 1008;
        int PICK_CONTACT_SCREEN = 1009;
        int VIEW_INVITE_GUEST_SCREEN = 1010;
        int EDIT_GUEST_SCREEN = 1011;
        int SELECT_CITY_SCREEN = 1012;
        int SELECT_SOCIETY_SCREEN = 1013;
        int SELECT_BUILDING_SCREEN = 1014;
        int SELECT_FLAT_SCREEN = 1015;


        int CHOOSE_MAID_SCREEN = 1016;
        int ADD_STAFF_SCREEN = 1017;
        int MYSTAFF_ALERTS_SCREEN = 1018;
        int STAFF_PROFILE_SCREEN = 1019;


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
                return IntroScreensActivity.class;
            case MAIN_SCREEN:
                return MainActivity.class;
            case LOGIN_SCREEN:
                return LoginActivity.class;
            case SIGNUP_SCREEN:
                return SignupActivity.class;
            case OTPVERIFICATION_SCREEN:
                return OtpVerificationActivity.class;
            case PASSCODE_SCREEN:
                return PasscodeActivity.class;
            case INVITE_GUEST_SCREEN:
                return InviteGuestActivity.class;
            case PICK_CONTACT_SCREEN:
                return PickContactsActivity.class;
            case VIEW_INVITE_GUEST_SCREEN:
                return ViewInviteGuestActivity.class;
            case EDIT_GUEST_SCREEN:
                return GuestEditActivity.class;
            case SELECT_CITY_SCREEN:
                return SelectCityActivity.class;
            case SELECT_SOCIETY_SCREEN:
                return SelectSocietyActivity.class;
            case SELECT_BUILDING_SCREEN:
                return GuestEditActivity.class;
            case SELECT_FLAT_SCREEN:
                return GuestEditActivity.class;
            case CHOOSE_MAID_SCREEN:
                return ChooseMaidActivity.class;
            case ADD_STAFF_SCREEN:
                return AddStaffActivity.class;
            case MYSTAFF_ALERTS_SCREEN:
                return MyStaffAlerts.class;
            case STAFF_PROFILE_SCREEN:
                return StaffProfileActivity.class;


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