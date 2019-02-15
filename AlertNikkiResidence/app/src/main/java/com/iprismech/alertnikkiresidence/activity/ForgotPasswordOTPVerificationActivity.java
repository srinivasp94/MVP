package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

public class ForgotPasswordOTPVerificationActivity extends BaseAbstractActivity {
    private PinEntryEditText txt_pin_entry;
    private TextView verifyotp_btn;
    private String sOtp;
    private String otp;
    private SharedPrefsUtils utils;
    private String userId;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_otp_verification, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sOtp = bundle.getString("Key_otp");
            userId = bundle.getString("Key_userId");

        }

        txt_pin_entry = findViewById(R.id.txt_pin_entry);
        verifyotp_btn = findViewById(R.id.verifyotp_btn);
        txt_pin_entry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                otp = str.toString();
            }
        });
        verifyotp_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (otp.length() == 0 || otp.equalsIgnoreCase("")) {
                    Common.showToast(ForgotPasswordOTPVerificationActivity.this, "Please enter Otp.");
                } else if (!otp.equalsIgnoreCase(sOtp)) {
                    Common.showToast(ForgotPasswordOTPVerificationActivity.this, "Otp Not matched.");

//                    Bundle bundle = new Bundle();
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_CITY_SCREEN, bundle);

                } else {

                    Bundle bundle=new Bundle();
                    bundle.putString("Key_userID",userId);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_RESET_PASSWORD_SCREEN,bundle);
                    finish();

                }
            }
        });
    }
}
