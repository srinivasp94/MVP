package com.iprismech.alertnikkiresidence;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.fragments.HomeFragment;
import com.iprismech.alertnikkiresidence.request.FamilydeviceToken;
import com.iprismech.alertnikkiresidence.request.GuestDeliveryAllowReq;
import com.iprismech.alertnikkiresidence.request.UpdateDeviceToken;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class MainActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener {
    FragmentManager fragmentManager;
    private Object obj;
    private AlertDialog alertDialog, alertDialog_deny;
    private String notification_id, notification_name, notification_service_from;
    private String str_reason_for_deny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_screen);
        FirebaseApp.initializeApp(MainActivity.this);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        Intent intent = getIntent();
        notification_id = intent.getStringExtra("id");
        notification_name = intent.getStringExtra("person_name");
        notification_service_from = intent.getStringExtra("service_type");
        String notification_from = intent.getStringExtra("key");

        if (notification_from != null && notification_id != null) {
            if (notification_from.equalsIgnoreCase("GuestDelivery")) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
                View view1 = inflater.inflate(R.layout.dailog_residance_alow_deny, null);
                alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setView(view1);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(true);

                TextView tv_btn_allow, tv_btn_deny, name, service_from;

                tv_btn_deny = view1.findViewById(R.id.tv_btn_deny);
                tv_btn_allow = view1.findViewById(R.id.tv_btn_allow);
                name = view1.findViewById(R.id.tv_notification_name);
                service_from = view1.findViewById(R.id.tv_service_type);
                name.setText(notification_name);
                if (notification_service_from.equalsIgnoreCase("invite_guest")) {
                    service_from.setText("Guest");
                } else {
                    service_from.setText(notification_service_from);
                }

                tv_btn_allow.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onClick(View v) {
                        callGuestallowReqWS();
                        alertDialog.dismiss();
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VISITORS_HISTORY_SCREEN);
                    }
                });
                tv_btn_deny.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDenyAlertDialog();
                    }
                });

                alertDialog.show();

            }
        }
        // ApplicationController.getInstance().setContext(context);
        try {

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.fm_container, new HomeFragment(), "").commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//user
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        if (SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE).equalsIgnoreCase("1.0")) {

            UpdateDeviceToken updateDeviceToken = new UpdateDeviceToken();
            updateDeviceToken.user_id = SharedPrefsUtils.getInstance(MainActivity.this).getId();
            //updateDeviceToken.security_id = "1";
            updateDeviceToken.token = refreshedToken;
            try {
                obj = Class.forName(UpdateDeviceToken.class.getName()).cast(updateDeviceToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new RetrofitRequester(this).callPostServices(obj, 1, "update_user_device_token", true);


        } else if (SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE).equalsIgnoreCase("2.0")) {

            FamilydeviceToken req = new FamilydeviceToken();
            req.familyMemberId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
            req.token = refreshedToken;

            try {
                obj = Class.forName(FamilydeviceToken.class.getName()).cast(req);
            } catch (Exception e) {

            }
            new RetrofitRequester(this).callPostServices(obj, 2, "update_family_member_device_token", true);
        }


    }

    private void openDenyAlertDialog() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.dailog_notification_deny, null);
        alertDialog_deny = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog_deny.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog_deny.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog_deny.setCancelable(true);
        TextView tv_back, tv_submit;
        final EditText et_reason;
        tv_back = view1.findViewById(R.id.tv_btn_pass_back);
        tv_submit = view1.findViewById(R.id.tv_btn_pass_submit);
        et_reason = view1.findViewById(R.id.et_comment_deny);
        str_reason_for_deny = et_reason.getText().toString();
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog_deny.dismiss();
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (et_reason.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please write Reason for deny", Toast.LENGTH_SHORT).show();
                } else {
                    callGuestDeliveryDenyWS();
                    alertDialog.dismiss();
                    alertDialog_deny.dismiss();
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VISITORS_HISTORY_SCREEN);
                }
            }
        });
        alertDialog_deny.show();

    }

    private void callGuestDeliveryDenyWS() {
        GuestDeliveryAllowReq req = new GuestDeliveryAllowReq();
        req.id = notification_id;
        req.allow_deny_status = "no";
        req.reson_for_deny = str_reason_for_deny;

        try {
            obj = Class.forName(GuestDeliveryAllowReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 3, "allow_deny_status", true);
    }

    private void callGuestallowReqWS() {
        GuestDeliveryAllowReq req = new GuestDeliveryAllowReq();
        req.id = notification_id;
        req.allow_deny_status = "yes";
        req.reson_for_deny = "";

        try {
            obj = Class.forName(GuestDeliveryAllowReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 3, "allow_deny_status", true);

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getApplicationContext(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);

                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            JSONObject jsonObject = object.optJSONObject("response");
                            break;
                        case 2:
//                            JSONObject jsonObject = object.optJSONObject("response");
                            break;
                        case 3:
//                            JSONObject jsonObject = object.optJSONObject("response");
                            Toast.makeText(this, "Confirmation status sent to Security Successfully", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
