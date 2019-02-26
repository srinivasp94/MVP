package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.ForgotPasswordPojo;
import com.iprismech.alertnikkiresidence.request.ForgotPasswordRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPasswordActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private EditText et_mobile_number;
    private TextView tv_btn_next;
    private Object obj;
    private ImageView imgClose;
    private TextView txtitle;

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.tv_btn_next:
                if (et_mobile_number.getText().toString().isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter Mobile number", Toast.LENGTH_SHORT).show();
                } else if (et_mobile_number.getText().length() < 10) {
                    Toast.makeText(ForgotPasswordActivity.this, "Mobile Number must be 10 digits", Toast.LENGTH_SHORT).show();
                } else {
                    ForgotPasswordRequest req = new ForgotPasswordRequest();
                    req.mobile = et_mobile_number.getText().toString();

                    try {
                        obj = Class.forName(ForgotPasswordRequest.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "user_forgot_password", true);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                }

                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_forgot_password, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        et_mobile_number.setOnClickListener(this);
        tv_btn_next.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        et_mobile_number = findViewById(R.id.et_mobile_number);
        tv_btn_next = findViewById(R.id.tv_btn_next);
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Forgot Password");

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ForgotPasswordActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            ForgotPasswordPojo forgotPasswordPojo = gson.fromJson(jsonString, ForgotPasswordPojo.class);

                            Bundle bundle = new Bundle();
                            bundle.putString("Key_otp", String.valueOf(forgotPasswordPojo.getOTP().getOtp()));
                            bundle.putString("Key_userId", forgotPasswordPojo.getResponse().getId());

                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FORGOT_PASSWORD_OTP_VERIFICATIONSCREEN, bundle);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(ForgotPasswordActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
        finish();
    }
}
