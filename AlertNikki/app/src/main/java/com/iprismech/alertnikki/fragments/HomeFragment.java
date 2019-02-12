package com.iprismech.alertnikki.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.google.gson.Gson;
import com.iprismech.alertnikki.MainActivity;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AllowAdminStaff;
import com.iprismech.alertnikki.Request.AllowDailyHelps;
import com.iprismech.alertnikki.Request.Login_model;
import com.iprismech.alertnikki.Request.Through_Otp;
import com.iprismech.alertnikki.Request.Through_Phone;
import com.iprismech.alertnikki.Request.Through_Vehicle;
import com.iprismech.alertnikki.Response.Passcodes_Info;
import com.iprismech.alertnikki.activity.ScannerActivity;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;
import com.iprismech.alertnikki.utilities.timeutilities.SlotDivision;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class HomeFragment extends BaseAbstractFragment<Class> implements View.OnClickListener, TextWatcher, RetrofitResponseListener {
    private LinearLayout qrcode, lMobile, lVehicle, lOtp, ll_edittextAll, lPasscode;
    private EditText edt_text, edt_all;
    private Object obj;
    private TextView tv_code_type;
    RetrofitResponseListener responseListener;
    MainActivity activity;
    private String tag = "";
    private ImageView car, otp, qr, mobile;
    private PinEntryEditText pinPhone, pinOTP, pinVehicleNumber;

    private ImageView imgHome, imgStaff, imgVisitor, imgBell, imgCab, imgDailyHelps, imgDelivery, imgSchool, imgMore;
    private String vehiclenumber = "";
    private LinearLayout ll_keypad;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        // qrcode.setOnClickListener(this);
        lMobile.setOnClickListener(this);
        lOtp.setOnClickListener(this);
        lVehicle.setOnClickListener(this);
        ll_keypad.setOnClickListener(this);
        edt_text.addTextChangedListener(this);
        edt_all.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tag.equalsIgnoreCase("mobile")) {
                    String phn = edt_all.getText().toString();
                    if (phn.length() == 0 || phn.length() < 10) {
//                        Common.showToast(getActivity(), "Not a valid Phone Number");
                    }
                    if (phn.length() == 10) {
                        call_PhoneWS(phn);

                    }
                } else if (tag.equalsIgnoreCase("otp")) {

                    String otp = edt_all.getText().toString();
                    if (otp.length() == 0 || otp.length() < 4) {
//                        Common.showToast(getActivity(), "Not a valid otp");
                    }
                    if (otp.length() == 4) {
                        callOTP_WS(otp);

                    }

                } else if (tag.equalsIgnoreCase("vehicle")) {

                    String vehicle = edt_all.getText().toString();
                    if (vehicle.length() == 0 || vehicle.length() < 4) {
//                        Common.showToast(getActivity(), "Not a valid Vehicle Number");
                    }
                    if (vehicle.length() == 4) {
                        call_vehicle_WS(vehicle);

                    }
                }
            }
        });
        if (pinPhone != null) {
            pinPhone.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (str.toString().length() == 10) {
                        call_PhoneWS(str.toString());
                    } else {
                        pinPhone.setText(null);
                    }
                }
            });
        }
        if (pinOTP != null) {
            pinOTP.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (str.toString().length() == 5) {
                        callOTP_WS(str.toString());
                    } else {
                        pinOTP.setText(null);
                    }
                }
            });
        }
        if (pinVehicleNumber != null) {
            pinVehicleNumber.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (str.toString().length() == 4) {
                        vehiclenumber = str.toString();
                        call_vehicle_WS(str.toString());
                    } else {
                        pinVehicleNumber.setText(null);
                    }
                }
            });
        }
    }

    private void call_PhoneWS(String phn) {
        Through_Phone req = new Through_Phone();
        req.mobileNo = phn;
        try {
            obj = Class.forName(Through_Phone.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 4, "through_mobile_no", true);
    }

    private void callOTP_WS(String otp) {
        Through_Otp req = new Through_Otp();
        req.otp = otp;
        try {
            obj = Class.forName(Through_Otp.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 5, " through_otp", true);
    }

    private void call_vehicle_WS(String vehicle) {
        Through_Vehicle req = new Through_Vehicle();
        req.vehicleNo = vehicle;
        try {
            obj = Class.forName(Through_Vehicle.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 6, "through_vehicle_no", true);
    }


    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        activity = (MainActivity) getActivity();

        //qrcode = view.findViewById(R.id.qrcode_Scan_Layout);
        lMobile = view.findViewById(R.id.ll_mobile);
        lOtp = view.findViewById(R.id.ll_otp);
        lVehicle = view.findViewById(R.id.ll_vehicle);
        tv_code_type = view.findViewById(R.id.tv_code_type);

        ll_edittextAll = view.findViewById(R.id.ll_edittext);
        lPasscode = view.findViewById(R.id.ll_passcode);
        ll_keypad = view.findViewById(R.id.ll_keypad);

        edt_all = view.findViewById(R.id.edt_all);
        edt_text = view.findViewById(R.id.edt_otp1);
        otp = view.findViewById(R.id.otp);
        car = view.findViewById(R.id.car);
        qr = view.findViewById(R.id.qr);
        mobile = view.findViewById(R.id.mobile);

        pinPhone = view.findViewById(R.id.txt_pin_entry1);
        pinOTP = view.findViewById(R.id.txt_pin_entryOtp);
        pinVehicleNumber = view.findViewById(R.id.txt_pin_entryVehicle);


        imgHome = activity.findViewById(R.id.iv_top_keypad);
        imgStaff = activity.findViewById(R.id.iv_top_admistaff);
        imgVisitor = activity.findViewById(R.id.iv_top_visitors);
        imgBell = activity.findViewById(R.id.iv_top_alerts);
        imgCab = activity.findViewById(R.id.iv_bottom_cab);
        imgDailyHelps = activity.findViewById(R.id.iv_bottom_dailyhelps);
        imgDelivery = activity.findViewById(R.id.iv_bottom_delivery);
        imgSchool = activity.findViewById(R.id.iv_bottomschoolbus);
        imgMore = activity.findViewById(R.id.iv_bottom_more);

        responseListener = this;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.qrcode_Scan_Layout:
//                Intent intent = new Intent(getActivity(), ScannerActivity.class);
//                startActivity(intent);
//                break;
            case R.id.ll_keypad:
                FragmentManager manager = activity.getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fm_container, new HomeFragment(), "Home Fragment").commit();
//
                break;
            case R.id.ll_mobile:
                try {
                    lMobile.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    lOtp.setBackgroundColor(getActivity().getResources().getColor(R.color.backgroundlight));
                    lVehicle.setBackgroundColor(getActivity().getResources().getColor(R.color.backgroundlight));
                    tag = "mobile";
                    lPasscode.setVisibility(View.GONE);
                    ll_edittextAll.setVisibility(View.VISIBLE);
                    pinPhone.setVisibility(View.VISIBLE);
                    pinOTP.setVisibility(View.GONE);
                    pinVehicleNumber.setVisibility(View.GONE);
                    edt_all.setHint("Please Enter Mobile Number");
                    edt_all.setText("");
                    edt_all.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                    tv_code_type.setText("Enter Mobile Number");
                } catch (Exception e) {
                    e.printStackTrace();
                    // Common.commonLogs(getActivity(), "123common" + e.toString());
                }

                break;
            case R.id.ll_otp:

                lOtp.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                lMobile.setBackgroundColor(getActivity().getResources().getColor(R.color.backgroundlight));
                lVehicle.setBackgroundColor(getActivity().getResources().getColor(R.color.backgroundlight));
                tag = "otp";
                lPasscode.setVisibility(View.GONE);
                ll_edittextAll.setVisibility(View.VISIBLE);
                pinPhone.setVisibility(View.GONE);
                pinOTP.setVisibility(View.VISIBLE);
                pinVehicleNumber.setVisibility(View.GONE);
                edt_all.setHint("Please Enter OTP");
                tv_code_type.setText("Enter OTP");
                edt_all.setText("");
                edt_all.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                break;
            case R.id.ll_vehicle:

                lVehicle.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                lOtp.setBackgroundColor(getActivity().getResources().getColor(R.color.backgroundlight));
                lMobile.setBackgroundColor(getActivity().getResources().getColor(R.color.backgroundlight));
                tag = "vehicle";
                lPasscode.setVisibility(View.GONE);
                ll_edittextAll.setVisibility(View.VISIBLE);
                pinPhone.setVisibility(View.GONE);
                pinOTP.setVisibility(View.GONE);
                pinVehicleNumber.setVisibility(View.VISIBLE);
                tv_code_type.setText("Enter Vehicle Number");
                edt_all.setHint("Please Enter Vehicle Number");
                edt_all.setText("");

                edt_all.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 7) {
            String passcode = edt_text.getText().toString();

            Login_model login_model = new Login_model();
            login_model.passcode = passcode;
//                                    login_model.selected_image = "base640";
            try {
                obj = Class.forName(Login_model.class.getName()).cast(login_model);

            } catch (Exception e) {
                e.printStackTrace();
            }
            new RetrofitRequester(this).callPostServices(obj, 1, "passcode", true);
        } else {
//            Common.showToast(getActivity(), "Enter Full Passcode");
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getActivity(), "Please Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            JSONObject responseObject = object.optJSONObject("response");
                            String member_Type = responseObject.optString("member_type");
                            // Member Type --> 1.family_member 2.user 3. security 4.staff 5.Miad
                            if (member_Type.equalsIgnoreCase("family_member")) {
                                showCustomDialog(getActivity(), responseObject);
                            } else if (member_Type.equalsIgnoreCase("user")) {
                                showCustomDialog(getActivity(), responseObject);
                            } else if (member_Type.equalsIgnoreCase("security")) {
                                showCustomDialog(getActivity(), responseObject);
                            }
                            //staff nothing but admin staff
                            else if (member_Type.equalsIgnoreCase("staff")) {
                                showCustomDialogForAdmin(getActivity(), responseObject);
                            } else {
                                showCustomDialogDailyHelps(getActivity(), responseObject);
                            }
                            /*Bundle bundle = new Bundle();
                            bundle.putString("Key_Security_Id", login.loginData.id);
                            bundle.putString("Key_Admin_Id", login.loginData.adminId);
                            bundle.putString("Key_Passcode", login.loginData.passcode);
                            bundle.putString("Key_Name", login.loginData.name);*/

                            break;
                        case 2:
                            JSONObject jsonhelpsResponse = object.optJSONObject("response");

                            Common.showToast(getActivity(), object.optString("message"));
                            try {
                                FragmentManager manager = activity.getSupportFragmentManager();
                                manager.beginTransaction().replace(R.id.fm_container, new DailyHelps_Fragement(), "DailyHelps").commit();
//                                activity.bottomNavigationView.setSelectedItemId(R.id.action_dailyhelps);


                                imgHome.setImageResource(R.drawable.ic_keypad);

                                imgStaff.setImageResource(R.drawable.ic_admin_staff);

                                imgVisitor.setImageResource(R.drawable.ic_visitor);

                                imgBell.setImageResource(R.drawable.ic_bell);

                                imgCab.setImageResource(R.drawable.ic_cab);

                                imgDailyHelps.setImageResource(R.drawable.ic_dailyhelps_active);

                                imgDelivery.setImageResource(R.drawable.ic_delivery);

                                imgSchool.setImageResource(R.drawable.ic_school_bus);

                                imgMore.setImageResource(R.drawable.ic_bottom_more);


                            } catch (Exception e) {
                                e.printStackTrace();
                                //  Common.commonLogs(getActivity(), "123Helps" + e.toString());
                            }
                            break;
                        case 3:
                            //admin staff

                            JSONObject jsonStaffResponse = object.optJSONObject("response");

                            Common.showToast(getActivity(), object.optString("message"));
                            try {
                                FragmentManager manager = getFragmentManager();
                                manager.beginTransaction().replace(R.id.fm_container, new AdminStaff_Fragment(), "AdminStaff").commit();
//                                MainActivity activity = (MainActivity) getActivity();
//                                activity.topnavigationview.setSelectedItemId(R.id.nav_admin_staff);

//                                activity.findViewById(R.id.nav_admin_staff).


                                imgHome.setImageResource(R.drawable.ic_keypad);

                                imgStaff.setImageResource(R.drawable.ic_admistaff_active);

                                imgVisitor.setImageResource(R.drawable.ic_visitor);

                                imgBell.setImageResource(R.drawable.ic_bell);

                                imgCab.setImageResource(R.drawable.ic_cab);

                                imgDailyHelps.setImageResource(R.drawable.ic_dailyhelps);

                                imgDelivery.setImageResource(R.drawable.ic_delivery);

                                imgSchool.setImageResource(R.drawable.ic_school_bus);

                                imgMore.setImageResource(R.drawable.ic_bottom_more);

                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                                // Common.commonLogs(getActivity(), "123Staff" + e.toString());
                            }
                            break;
                        case 4:
                            JSONObject mobileResponse = object.optJSONObject("response");

                            pinPhone.setText("");
                            pinOTP.setText("");
                            pinVehicleNumber.setText("");

                            showCustomDialogForThroug_Mobile(getActivity(), mobileResponse);


                            break;
                        case 5:
                            JSONObject otpResponse = object.optJSONObject("response");
                            pinPhone.setText("");
                            pinOTP.setText("");
                            pinVehicleNumber.setText("");
                            showCustomDialogForAdminThroughOTP(getActivity(), otpResponse);
                            break;
                        case 6:
                            JSONObject vehileResponse = object.optJSONObject("response");
                            pinPhone.setText("");
                            pinOTP.setText("");
                            pinVehicleNumber.setText("");
                            Bundle bundle = new Bundle();
                            bundle.putString("Key_vehicle", vehiclenumber);
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_THROUGH_VEHICLE_SCREEN, bundle);
                            break;

                    }
                } else {
                    //Common.showToast(getActivity(), object.optString("message"));
                    pinPhone.setText("");
                    pinOTP.setText("");
                    pinVehicleNumber.setText("");
                    Toast.makeText(getActivity(), "No data found with entered details", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void showCustomDialogDailyHelps(final FragmentActivity activity, final JSONObject responseObject) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_daily_helps_maid, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);

        TextView tv_Name, tv_Role, tv_Passcode, tv_worksin, bt_deny, btn_allow;
        ImageView HelperPic;

        tv_Name = view1.findViewById(R.id.tv_helper_name);
        tv_Role = view1.findViewById(R.id.tv_helper_desig);
        tv_Passcode = view1.findViewById(R.id.tv_helper_passcode);
        tv_worksin = view1.findViewById(R.id.tv_works_flat);
        bt_deny = view1.findViewById(R.id.tv_deny_helper);
        btn_allow = view1.findViewById(R.id.tv_allow_helper);

        HelperPic = view1.findViewById(R.id.img_helper_pic);

        tv_Name.setText("" + responseObject.optString("title"));
        tv_Role.setText("" + responseObject.optString("member_type"));
        tv_Passcode.setText("" + responseObject.optString("passcode"));
        tv_worksin.setText("" + responseObject.optString("flats"));

        bt_deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                edt_text.setText("");
            }
        });
        btn_allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                AllowDailyHelps dailyHelpsRequsest = new AllowDailyHelps();
                dailyHelpsRequsest.admin_id = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
                dailyHelpsRequsest.security_id = SharedPrefsUtils.getInstance(getActivity()).getsecurityId();
//                dailyHelpsRequsest.maid_id = responseObject.optString("id");
                dailyHelpsRequsest.maid_id = responseObject.optString("maid_id");

                try {
                    obj = Class.forName(AllowDailyHelps.class.getName()).cast(dailyHelpsRequsest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(responseListener).callPostServices(obj, 2, "allow_daily_helps", true);
                alertDialog.dismiss();
                edt_text.setText("");
            }
        });

        alertDialog.show();
    }

    private void showCustomDialogForAdmin(FragmentActivity activity, final JSONObject responseObject) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_admin_staff, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);

        TextView entryTime, tv_staffName, tv_staffRole, tv_staffPasscode, tv_society, bt_deny, btn_allow;
        ImageView staffPic;

        entryTime = view1.findViewById(R.id.tv_admin_staff_entry_timings);
        tv_staffName = view1.findViewById(R.id.tv_admin_staff_name);
        tv_staffRole = view1.findViewById(R.id.tv_admin_staff_desig);
        tv_staffPasscode = view1.findViewById(R.id.tv_helper_passcode);
        tv_society = view1.findViewById(R.id.tv_satff_works_in);
        bt_deny = view1.findViewById(R.id.tv_deny_staff);
        btn_allow = view1.findViewById(R.id.tv_allow_staff);

        staffPic = view1.findViewById(R.id.img_helper_pic);

        entryTime.setText("Entry: " + responseObject.optString("in_time") + ", Out: "
                + responseObject.optString("out_time"));
        tv_staffName.setText(responseObject.optString("name"));
        tv_staffRole.setText(responseObject.optString("designation") + " , AdminStaff");
        tv_staffPasscode.setText(responseObject.optString("passcode"));
        tv_society.setText(responseObject.optString("society"));

        Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + responseObject.optString("image"))
                .error(R.drawable.dummy).into(staffPic);

        bt_deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                edt_text.setText("");
            }
        });

        btn_allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // admin id,id as parameters

                AllowAdminStaff adminStaffRequset = new AllowAdminStaff();
                adminStaffRequset.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
                adminStaffRequset.staffId = responseObject.optString("staff_id");
                try {
                    obj = Class.forName(AllowAdminStaff.class.getName()).cast(adminStaffRequset);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(responseListener).callPostServices(obj, 3, "allow_admin_staff", true);
                alertDialog.dismiss();

            }
        });

        alertDialog.show();

    }

    public void showCustomDialog(Context context, final JSONObject jsonObject) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_show_info, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);

        TextView tv_Name, tv_blood, tv_passcode, tv_address, tv_usertype;
        TextView bt_allow, bt_Ok;

        tv_Name = view1.findViewById(R.id.tv_helper_name);
        tv_blood = view1.findViewById(R.id.tv_Blood_Grp);
        tv_passcode = view1.findViewById(R.id.tv_helper_passcode);
        tv_address = view1.findViewById(R.id.tv_userAddress);
        tv_usertype = view1.findViewById(R.id.tv_user_Type);

        bt_allow = view1.findViewById(R.id.tv_allow_helper);
        bt_Ok = view1.findViewById(R.id.tv_ok);
        ImageView userDp = view1.findViewById(R.id.img_userDP);

        bt_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
                edt_text.setText("");
            }
        });
        if (jsonObject.optString("member_type").equalsIgnoreCase("security")) {

            tv_address.setVisibility(View.GONE);
          /*  tv_staticTitle.setText("Role: ");
            tv_blood.setText(jsonObject.optString("member_type"));*/
        }
        tv_Name.setText(jsonObject.optString("name"));
        tv_blood.setText(jsonObject.optString("blood_group"));
        tv_passcode.setText(jsonObject.optString("passcode"));
        tv_usertype.setText(jsonObject.optString("member_type"));
        tv_address.setText(jsonObject.optString("flat") + ", " + jsonObject.optString("building")
                + ", " + jsonObject.optString("society") + ", " + jsonObject.optString("city"));
//        building  society        city

        Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + jsonObject.optString("image")).error(R.drawable.dummy).into(userDp);


        alertDialog.show();

    }

    private void showCustomDialogForThroug_Mobile(FragmentActivity activity, JSONObject mobileResponse) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_through_mobile, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);

        TextView tv_Name, tv_Passcode, tv_mobile, tv_flat, tv_building, tv_society, tv_city, tv_residency, btn_tv_allow_ok;
        ImageView img_through_mobile_pic;

        tv_Name = view1.findViewById(R.id.tv_through_mobile_name);
        tv_mobile = view1.findViewById(R.id.mobile_nuber);
        tv_Passcode = view1.findViewById(R.id.tv_through_passcode);
        // tv_worksin = view1.findViewById(R.id.tv_works_flat);
        tv_flat = view1.findViewById(R.id.through_mobile_flat);
        tv_society = view1.findViewById(R.id.through_mobile_society);
        tv_residency = view1.findViewById(R.id.through_mobile_residency);
        btn_tv_allow_ok = view1.findViewById(R.id.tv_allow_ok);
        tv_building = view1.findViewById(R.id.through_mobile_building);
        tv_city = view1.findViewById(R.id.through_mobile_city);
        img_through_mobile_pic = view1.findViewById(R.id.img_through_mobile_pic);
        if (!mobileResponse.optString("image").equalsIgnoreCase("")) {
            Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + mobileResponse.optString("image")).into(img_through_mobile_pic);
        }
        tv_Name.setText(mobileResponse.optString("name"));
        tv_mobile.setText("" + mobileResponse.optString("mobile"));
        tv_Passcode.setText("" + mobileResponse.optString("passcode"));
        tv_flat.setText(mobileResponse.optString("flat"));
        tv_building.setText(mobileResponse.optString("building"));
        tv_city.setText(mobileResponse.optString("city"));
        tv_society.setText(mobileResponse.optString("society"));
        tv_residency.setText("" + mobileResponse.optString("residence_type"));

        //  tv_worksin.setText("" + responseObject.optString("flats"));

        btn_tv_allow_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                pinPhone.setText(null);
            }
        });
        alertDialog.show();

    }

    private void showCustomDialogForAdminThroughOTP(FragmentActivity activity, JSONObject otpResponse) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_through_otp, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);

        TextView tv_Name, tv_Passcode, tv_mobile, tv_flat, tv_building, tv_society, tv_city, tv_residency, btn_tv_allow_ok;
        ImageView img_through_otp_pic;

        tv_Name = view1.findViewById(R.id.tv_through_otp_name);
        tv_mobile = view1.findViewById(R.id.throuh_otp_mobile_nuber);
        tv_Passcode = view1.findViewById(R.id.tv_through_otp_passcode);
        // tv_worksin = view1.findViewById(R.id.tv_works_flat);
        tv_flat = view1.findViewById(R.id.through_otp_flat);
        tv_society = view1.findViewById(R.id.through_otp_society);
        tv_residency = view1.findViewById(R.id.through_otp_residency);
        btn_tv_allow_ok = view1.findViewById(R.id.tv_allow_ok_through_otp);
        img_through_otp_pic = view1.findViewById(R.id.img_through_otp_pic);
        if (!otpResponse.optString("image").equalsIgnoreCase("")) {
            Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + otpResponse.optString("image")).into(img_through_otp_pic);
        }
        tv_Name.setText(otpResponse.optString("name"));
        tv_mobile.setText("" + otpResponse.optString("mobile"));
        tv_Passcode.setText("" + otpResponse.optString("passcode"));
        tv_flat.setText("Flat No:" + otpResponse.optString("flat") + "  " + "Building: " + otpResponse.optString("building"));
        tv_society.setText("Society: " + otpResponse.optString("society"));
        tv_residency.setText("" + otpResponse.optString("residence_type") + " " + "City:" + otpResponse.optString("city"));


        //  tv_worksin.setText("" + responseObject.optString("flats"));

        btn_tv_allow_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


}
