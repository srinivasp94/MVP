package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.LoginReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class PasscodeActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private TextView title, btnLoginWithPasscode;
    private ImageView imgBack;
    private EditText edtPasscode;
    private Object obj;
    private SharedPrefsUtils utils;
    private ImageView imgClose;
    private TextView txtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.passcode_login_enter, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        imgBack.setOnClickListener(this);
        btnLoginWithPasscode.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {

        super.initializeViews();
        utils = new SharedPrefsUtils(PasscodeActivity.this);
        title = findViewById(R.id.txtitle);
        imgBack = findViewById(R.id.imgClose);
        title.setText("Passcode");
        btnLoginWithPasscode = findViewById(R.id.btnLoginWithPasscode);
        edtPasscode = findViewById(R.id.edtPasscode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLoginWithPasscode:
                if (edtPasscode.getText().toString().length() == 0) {
                    Common.showToast(PasscodeActivity.this, "Please Enter Passcode");
                } else if (edtPasscode.getText().toString().length() < 7) {
                    Common.showToast(PasscodeActivity.this, "Passcode must be 7 numbers");
                } else {
                    //make service call here

                    LoginReq req = new LoginReq();
                    req.mobile = "";
                    req.password = "";
                    req.passcode = edtPasscode.getText().toString();

                    try {
                        obj = Class.forName(LoginReq.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "login", true);
//                    new RetrofitRequester(this).callPostServices(obj, 1, "userdata_with_passcode", true);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                }

                break;
            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
        finish();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(PasscodeActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            JSONObject responseObj = jsonObject.optJSONObject("response");
                            String status = responseObj.optString("user_type");
                            if (responseObj.optInt("user_type") == 1) {
                                //Resident
                                String userid = responseObj.optString("id");
                                utils.setString(SharedPrefsUtils.KEY_ADMIN_ID, responseObj.optString("admin_id"));
                                String prefPhone = responseObj.optString("mobile");
                                String prefEmail = responseObj.optString("email_id");
                                String prefSociety = responseObj.optString("society_id");
                                String prefCity = responseObj.optString("city_id");
                                String prefFlat = responseObj.optString("flat_id");
                                String prefBuilding = responseObj.optString("building_id");
                                String prefUserType = responseObj.optString("user_type");

                                utils.createUserSession(userid, prefPhone, prefEmail
                                        , prefSociety, prefCity, prefFlat, prefBuilding, prefUserType);


                                utils.setString(SharedPrefsUtils.KEY_SOCIETY_NAME, responseObj.optString("society_name"));
                                utils.setString(SharedPrefsUtils.KEY_FLAT_NAME, responseObj.optString("flat_name"));
                                utils.setString(SharedPrefsUtils.KEY_BUILDING_NAME, responseObj.optString("building_name"));

                                utils.setString(SharedPrefsUtils.KEY_NAME, responseObj.optString("name"));
                                utils.setString(SharedPrefsUtils.KEY_SOCIETY_NAME, responseObj.optString("society_name"));
                                utils.setString(SharedPrefsUtils.KEY_BUILDING_NAME, responseObj.optString("building_name"));
                                utils.setString(SharedPrefsUtils.KEY_FLAT_NAME, responseObj.optString("flat_name"));
                                utils.setString(SharedPrefsUtils.KEY_PASSCODE, responseObj.optString("passcode"));
                                utils.setString(SharedPrefsUtils.KEY_PROFILE_PIC, responseObj.optString("image"));
                                utils.setString(SharedPrefsUtils.KEY_QRCODE, responseObj.optString("qrcode"));
                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                                finish();
                            } else if (responseObj.optInt("user_type") == 2) {
                                //Family Member

                                utils.createUserSession(
                                        responseObj.optString("id"),
                                        responseObj.optString("mobile"),
                                        responseObj.optString("email_id"),
                                        responseObj.optString("society_id"),
                                        responseObj.optString("city_id"),
                                        responseObj.optString("flat_id"),
                                        responseObj.optString("building_id"),
                                        responseObj.optString("user_type")
                                );

                                utils.setString(SharedPrefsUtils.KEY_QRCODE, responseObj.optString("qrcode"));
                                utils.setString(SharedPrefsUtils.KEY_ADMIN_ID, responseObj.optString("admin_id"));
                                utils.setString(SharedPrefsUtils.KEY_ID, responseObj.optString("id"));
                                utils.setString(SharedPrefsUtils.KEY_PASSCODE, responseObj.optString("passcode"));
                                utils.setString(SharedPrefsUtils.KEY_NAME, responseObj.optString("name"));
                                utils.setString(SharedPrefsUtils.KEY_PHONE, responseObj.optString("mobile"));
                                utils.setString(SharedPrefsUtils.KEY_EMAIL, responseObj.optString("email_id"));
                                utils.setString(SharedPrefsUtils.KEY_USER_TYPE, responseObj.optString("user_type"));
                                utils.setString(SharedPrefsUtils.KEY_SOCIETY_NAME, responseObj.optString("society_name"));
                                utils.setString(SharedPrefsUtils.KEY_FLAT_NAME, responseObj.optString("flat_name"));
                                utils.setString(SharedPrefsUtils.KEY_BUILDING_NAME, responseObj.optString("building_name"));
                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                                finish();
                            }


                            break;
                    }
                } else {
                    Common.showToast(PasscodeActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
