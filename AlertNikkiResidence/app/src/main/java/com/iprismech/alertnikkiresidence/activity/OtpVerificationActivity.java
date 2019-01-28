package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.utilities.Common;

public class OtpVerificationActivity extends BaseAbstractActivity implements View.OnClickListener {
    private PinEntryEditText txt_pin_entry;
    private TextView verifyotp_btn;
    private String otp = "";
    private String strOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_otp_verification, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ApplicationController.getInstance().setContext(context);
        verifyotp_btn.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        txt_pin_entry = findViewById(R.id.txt_pin_entry);
        verifyotp_btn = findViewById(R.id.verifyotp_btn);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            strOtp = bundle.getString("Key_OTP");
        }
        txt_pin_entry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                otp = str.toString();
            }
        });
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verifyotp_btn:
                if (otp.length() == 0 || otp.equalsIgnoreCase("")) {
                    Common.showToast(OtpVerificationActivity.this, "Please enter Otp.");
                } else if (!otp.equalsIgnoreCase(strOtp)) {
                    Common.showToast(OtpVerificationActivity.this, "Otp Not matched.");
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                } else {
                    //make service call here
                    //navigate to set resident address screens
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                }

                break;
        }
    }
}
