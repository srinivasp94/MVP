package com.iprismech.alertnikkiresidence.factories;

import android.support.annotation.IntDef;


import com.iprismech.alertnikkiresidence.MainActivity;
import com.iprismech.alertnikkiresidence.activity.AddKidActivity;
import com.iprismech.alertnikkiresidence.activity.AddStaffActivity;
import com.iprismech.alertnikkiresidence.activity.ChooseLocalServiceActivity;
import com.iprismech.alertnikkiresidence.activity.ChooseMaidActivity;
import com.iprismech.alertnikkiresidence.activity.ContactUsActivtiy;
import com.iprismech.alertnikkiresidence.activity.DigitalInterComSettingsActivity;
import com.iprismech.alertnikkiresidence.activity.EmergencyContactActivity;
import com.iprismech.alertnikkiresidence.activity.ForgotPasswordActivity;
import com.iprismech.alertnikkiresidence.activity.ForgotPasswordOTPVerificationActivity;
import com.iprismech.alertnikkiresidence.activity.GuestEditActivity;
import com.iprismech.alertnikkiresidence.activity.IntroScreensActivity;
import com.iprismech.alertnikkiresidence.activity.InviteGuestActivity;
import com.iprismech.alertnikkiresidence.activity.KidsGateAlertActivity;
import com.iprismech.alertnikkiresidence.activity.LocalServiceActivity;
import com.iprismech.alertnikkiresidence.activity.LocalServiceContactDetails;
import com.iprismech.alertnikkiresidence.activity.LoginActivity;
import com.iprismech.alertnikkiresidence.activity.MaidAttendanceHistory;
import com.iprismech.alertnikkiresidence.activity.MaidStaffAttendanceHistory;
import com.iprismech.alertnikkiresidence.activity.MaidViewAllAttandancesHistory;
import com.iprismech.alertnikkiresidence.activity.ManagementCommitteActivity;
import com.iprismech.alertnikkiresidence.activity.MyStaffAlerts;
import com.iprismech.alertnikkiresidence.activity.NoticeBoardActivity;
import com.iprismech.alertnikkiresidence.activity.OtpVerificationActivity;
import com.iprismech.alertnikkiresidence.activity.PasscodeActivity;
import com.iprismech.alertnikkiresidence.activity.PickContactsActivity;
import com.iprismech.alertnikkiresidence.activity.ResetPasswordActivity;
import com.iprismech.alertnikkiresidence.activity.SelectBuildingActvity;
import com.iprismech.alertnikkiresidence.activity.SelectCityActivity;
import com.iprismech.alertnikkiresidence.activity.SelectFlatActivity;
import com.iprismech.alertnikkiresidence.activity.SelectSocietyActivity;
import com.iprismech.alertnikkiresidence.activity.SignupActivity;
import com.iprismech.alertnikkiresidence.activity.SplashScreenActivity;
import com.iprismech.alertnikkiresidence.activity.StaffProfileActivity;
import com.iprismech.alertnikkiresidence.activity.StaffStandardTimingActivity;
import com.iprismech.alertnikkiresidence.activity.ViewInviteGuestActivity;
import com.iprismech.alertnikkiresidence.activity.VisitorsHistoryActivity;
import com.iprismech.alertnikkiresidence.activity.notifygate.EditGateAlertActivity;
import com.iprismech.alertnikkiresidence.activity.notifygate.GateServicesActivity;
import com.iprismech.alertnikkiresidence.activity.notifygate.NotifyGateMainActivity;
import com.iprismech.alertnikkiresidence.activity.profile.AddFamilyActivity;
import com.iprismech.alertnikkiresidence.activity.profile.ContactSingleActivity;
import com.iprismech.alertnikkiresidence.activity.profile.FamilyMembersActivity;
import com.iprismech.alertnikkiresidence.activity.profile.ProfileActivity;
import com.iprismech.alertnikkiresidence.activity.schoolbus.AddBusActivity;
import com.iprismech.alertnikkiresidence.activity.schoolbus.BusRouteActivity;
import com.iprismech.alertnikkiresidence.activity.schoolbus.BushistoryActivity;
import com.iprismech.alertnikkiresidence.activity.schoolbus.SchoolBus_MainActivity;
import com.iprismech.alertnikkiresidence.activity.schoolbus.SelectSchoolActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.ADD_BUS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.ADD_FAMILY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.ADD_BUS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.ADD_FAMILY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.ADD_KID_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.ADD_STAFF_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.BUS_HISTORY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.BUS_ROUTE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.BUS_HISTORY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.BUS_ROUTE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.CHOOSE_LOCAL_SERVICE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.CHOOSE_MAID_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.CONTACT_US_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.DIGITAL_INTERCOM_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.EDIT_GATE_SERVICE;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.EDIT_GATE_SERVICE;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.EDIT_GUEST_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.EMERGENCY_CONTACT_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.FAMILY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.FORGOT_PASSWORD_OTP_VERIFICATION_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.FORGOT_PASSWORD_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.GATE_SERVICE;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.FAMILY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.GATE_SERVICE;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.INTRO_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.INVITE_GUEST_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.KIDS_NOTIFY_GATE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.LOCAL_SERVICE_CONTACTS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.LOCAL_SERVICE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.LOGIN_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MAID_ATTENDANCE_HISTORY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MAID_STAFF_ATTENDANCE_HISTORY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MAIN_NOTIFY_GATE;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MAID_VIEW_ALL_ATTENDANCE_HISTORY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MAID_VIEW_ALL_ATTENDANCE_HISTORY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MAIN_NOTIFY_GATE;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MAIN_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MANAGEMENT_COMMITTE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.MYSTAFF_ALERTS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.NOTICE_BOARD_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.OONTACT_SINGLE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.OONTACT_SINGLE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.OTPVERIFICATION_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.PASSCODE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.PICK_CONTACT_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.PROFILE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.RESET_PASSWORD_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SCHOOL_BUS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.PROFILE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SCHOOL_BUS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_BUILDING_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_BUS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_BUS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_CITY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_FLAT_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SELECT_SOCIETY_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SIGNUP_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.SPLASH_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.STAFF_PROFILE_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.STANDARD_TIMINGS_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.VIEW_INVITE_GUEST_SCREEN;
import static com.iprismech.alertnikkiresidence.factories.ViewFactory.ScreenIds.VISITORS_HISTORY_SCREEN;


/**
 * Created by prasad on 05/07/2017.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */


public class ViewFactory {

    @Retention(RetentionPolicy.CLASS)
    @IntDef({SPLASH_SCREEN, INTRO_SCREEN, MAIN_SCREEN, LOGIN_SCREEN, SIGNUP_SCREEN,
            OTPVERIFICATION_SCREEN,
            PASSCODE_SCREEN, INVITE_GUEST_SCREEN, PICK_CONTACT_SCREEN, VIEW_INVITE_GUEST_SCREEN,
            EDIT_GUEST_SCREEN, SELECT_CITY_SCREEN, SELECT_SOCIETY_SCREEN, SELECT_BUILDING_SCREEN,
            SELECT_FLAT_SCREEN, CHOOSE_MAID_SCREEN, ADD_STAFF_SCREEN, MYSTAFF_ALERTS_SCREEN,
            STAFF_PROFILE_SCREEN, STANDARD_TIMINGS_SCREEN, KIDS_NOTIFY_GATE_SCREEN, ADD_KID_SCREEN,
            LOCAL_SERVICE_SCREEN, CHOOSE_LOCAL_SERVICE_SCREEN, LOCAL_SERVICE_CONTACTS_SCREEN,
            MAID_STAFF_ATTENDANCE_HISTORY_SCREEN, NOTICE_BOARD_SCREEN, EMERGENCY_CONTACT_SCREEN,
            MANAGEMENT_COMMITTE_SCREEN, DIGITAL_INTERCOM_SCREEN, CONTACT_US_SCREEN,
            VISITORS_HISTORY_SCREEN, MAID_ATTENDANCE_HISTORY_SCREEN, MAID_VIEW_ALL_ATTENDANCE_HISTORY_SCREEN, FORGOT_PASSWORD_SCREEN,
            FORGOT_PASSWORD_OTP_VERIFICATION_SCREEN, RESET_PASSWORD_SCREEN})
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
        int STANDARD_TIMINGS_SCREEN = 1020;

        int MAIN_NOTIFY_GATE = 1090;
        int GATE_SERVICE = 1091;
        int EDIT_GATE_SERVICE = 1092;


        int SCHOOL_BUS_SCREEN = 1094;
        int SELECT_BUS_SCREEN = 1095;
        int BUS_ROUTE_SCREEN = 1096;
        int ADD_BUS_SCREEN = 1097;
        int BUS_HISTORY_SCREEN = 1098;

        int PROFILE_SCREEN = 1200;
        int FAMILY_SCREEN = 1201;
        int ADD_FAMILY_SCREEN = 1202;
        int OONTACT_SINGLE_SCREEN = 1203;


        int KIDS_NOTIFY_GATE_SCREEN = 1040;
        int ADD_KID_SCREEN = 1041;
        int LOCAL_SERVICE_SCREEN = 1042;
        int CHOOSE_LOCAL_SERVICE_SCREEN = 1043;
        int LOCAL_SERVICE_CONTACTS_SCREEN = 1044;
        int MAID_STAFF_ATTENDANCE_HISTORY_SCREEN = 1045;
        int NOTICE_BOARD_SCREEN = 1046;
        int EMERGENCY_CONTACT_SCREEN = 1047;
        int MANAGEMENT_COMMITTE_SCREEN = 1048;
        int DIGITAL_INTERCOM_SCREEN = 1049;
        int CONTACT_US_SCREEN = 1050;
        int VISITORS_HISTORY_SCREEN = 1051;
        int MAID_ATTENDANCE_HISTORY_SCREEN = 1052;
        int MAID_VIEW_ALL_ATTENDANCE_HISTORY_SCREEN = 1053;
        int FORGOT_PASSWORD_SCREEN = 1054;
        int FORGOT_PASSWORD_OTP_VERIFICATION_SCREEN = 1055;
        int RESET_PASSWORD_SCREEN = 1056;


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

            case STANDARD_TIMINGS_SCREEN:
                return StaffStandardTimingActivity.class;

            case MAIN_NOTIFY_GATE:
                return NotifyGateMainActivity.class;
            case GATE_SERVICE:
                return GateServicesActivity.class;
            case EDIT_GATE_SERVICE:
                return EditGateAlertActivity.class;

            case SCHOOL_BUS_SCREEN:
                return SchoolBus_MainActivity.class;
            case SELECT_BUS_SCREEN:
                return SelectSchoolActivity.class;
            case BUS_ROUTE_SCREEN:
                return BusRouteActivity.class;
            case ADD_BUS_SCREEN:
                return AddBusActivity.class;
            case BUS_HISTORY_SCREEN:
                return BushistoryActivity.class;
            case KIDS_NOTIFY_GATE_SCREEN:
                return KidsGateAlertActivity.class;

            case LOCAL_SERVICE_SCREEN:
                return LocalServiceActivity.class;
            case PROFILE_SCREEN:
                return ProfileActivity.class;
            case FAMILY_SCREEN:
                return FamilyMembersActivity.class;
            case ADD_FAMILY_SCREEN:
                return AddFamilyActivity.class;
            case OONTACT_SINGLE_SCREEN:
                return ContactSingleActivity.class;

            case CHOOSE_LOCAL_SERVICE_SCREEN:
                return ChooseLocalServiceActivity.class;
            case LOCAL_SERVICE_CONTACTS_SCREEN:
                return LocalServiceContactDetails.class;
            case ADD_KID_SCREEN:
                return AddKidActivity.class;
            case MAID_STAFF_ATTENDANCE_HISTORY_SCREEN:
                return MaidStaffAttendanceHistory.class;

            case NOTICE_BOARD_SCREEN:
                return NoticeBoardActivity.class;
            case EMERGENCY_CONTACT_SCREEN:
                return EmergencyContactActivity.class;
            case MANAGEMENT_COMMITTE_SCREEN:
                return ManagementCommitteActivity.class;
            case DIGITAL_INTERCOM_SCREEN:
                return DigitalInterComSettingsActivity.class;
            case CONTACT_US_SCREEN:
                return ContactUsActivtiy.class;

            case VISITORS_HISTORY_SCREEN:
                return VisitorsHistoryActivity.class;
            case MAID_ATTENDANCE_HISTORY_SCREEN:
                return MaidAttendanceHistory.class;
            case FORGOT_PASSWORD_SCREEN:
                return ForgotPasswordActivity.class;
            case FORGOT_PASSWORD_OTP_VERIFICATION_SCREEN:
                return ForgotPasswordOTPVerificationActivity.class;
            case RESET_PASSWORD_SCREEN:
                return ResetPasswordActivity.class;

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