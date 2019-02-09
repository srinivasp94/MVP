package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
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

public class LoginActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private EditText edtPhone, edtPassword;
    private ImageView imgLogin, imgshowPassword;
    private TextView txtForgotpassword, txtHavepasscode, btnSignup;
    private Object obj;
    SharedPrefsUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.contact_pick);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_login_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();

        imgLogin.setOnClickListener(this);
        imgshowPassword.setOnClickListener(this);
        txtForgotpassword.setOnClickListener(this);
        txtHavepasscode.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        utils = new SharedPrefsUtils(LoginActivity.this);
        edtPhone = findViewById(R.id.edt_loginMobile);
        edtPassword = findViewById(R.id.edt_loginPasscode);
        imgLogin = findViewById(R.id.imgLogin);
        imgshowPassword = findViewById(R.id.imgShowPassword);
        txtForgotpassword = findViewById(R.id.txtForgotpassword);
        txtHavepasscode = findViewById(R.id.txtHavePasscode);
        btnSignup = findViewById(R.id.btnSignup);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLogin:
                if (edtPhone.getText().toString().length() == 0 && edtPassword.getText().toString().length() == 0) {
                    Common.showToast(LoginActivity.this, "Please enter all fields");
                } else if (edtPhone.getText().toString().length() < 10) {
                    Common.showToast(LoginActivity.this, "Phone must be 10 numbers");
                } else if (edtPassword.getText().toString().length() < 5) {
                    Common.showToast(LoginActivity.this, "Password must be 6 chars");
                } else {
                    //make service call here
                    LoginReq req = new LoginReq();
                    req.mobile = edtPhone.getText().toString();
                    req.password = edtPassword.getText().toString();
                    req.passcode = "";
                    try {
                        obj = Class.forName(LoginReq.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "login", true);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                }
                break;
            case R.id.imgShowPassword:
                break;
            case R.id.txtForgotpassword:
                break;
            case R.id.txtHavePasscode:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PASSCODE_SCREEN);
                break;
            case R.id.btnSignup:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SIGNUP_SCREEN);
                break;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(LoginActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            //userType =1 --> Resident
                            //userType =2 --> family Member
                            JSONObject responseObj = jsonObject.optJSONObject("response");
                            String status = responseObj.optString("user_type");
                            if (responseObj.optInt("user_type") == 1) {
                                //Resident
                                String userid = responseObj.optString("id");
                                String prefPhone = responseObj.optString("mobile");
                                String prefEmail = responseObj.optString("email_id");
                                String prefSociety = responseObj.optString("society_id");
                                String prefCity = responseObj.optString("city_id");
                                String prefFlat = responseObj.optString("flat_id");
                                String prefBuilding = responseObj.optString("building_id");
                                String prefUserType = responseObj.optString("user_type");

                                utils.createUserSession(userid, prefPhone, prefEmail
                                        , prefSociety, prefCity, prefFlat, prefBuilding, prefUserType);

                                utils.setString(SharedPrefsUtils.KEY_NAME, responseObj.optString("name"));
                                utils.setString(SharedPrefsUtils.KEY_SOCIETY_NAME, responseObj.optString("society_name"));
                                utils.setString(SharedPrefsUtils.KEY_BUILDING_NAME, responseObj.optString("building_name"));
                                utils.setString(SharedPrefsUtils.KEY_FLAT_NAME, responseObj.optString("flat_name"));
                                utils.setString(SharedPrefsUtils.KEY_PASSCODE, responseObj.optString("passcode"));
                                utils.setString(SharedPrefsUtils.KEY_PROFILE_PIC, responseObj.optString("image"));
                                utils.setString(SharedPrefsUtils.KEY_QRCODE, responseObj.optString("qrcode"));
                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                            } else if (responseObj.optInt("user_type") == 2) {
                                //Family Member
                                utils.setString(SharedPrefsUtils.KEY_ID, responseObj.optString("user_id"));
                                utils.setString(SharedPrefsUtils.KEY_PASSCODE, responseObj.optString("passcode"));
                                utils.setString(SharedPrefsUtils.KEY_NAME, responseObj.optString("name"));
                                utils.setString(SharedPrefsUtils.KEY_PHONE, responseObj.optString("mobile"));
                                utils.setString(SharedPrefsUtils.KEY_EMAIL, responseObj.optString("email_id"));
                                utils.setString(SharedPrefsUtils.KEY_USER_TYPE, responseObj.optString("user_type"));
                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                            }

                            break;
                    }
                } else {
                    Common.showToast(LoginActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
