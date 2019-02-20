package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.utilities.Common;

public class OtpVerificationActivity extends BaseAbstractActivity implements View.OnClickListener {
    private PinEntryEditText txt_pin_entry;
    private TextView verifyotp_btn, txtResendcode;
    private String otp = "";
    private String sOtp, sName, sMail, sPhone, sPassword, sBlood;

    private ImageView imgClose;
    private TextView txtitle;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

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
        txtResendcode.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
            txt_pin_entry = findViewById(R.id.txt_pin_entry);
            txtResendcode = findViewById(R.id.txtResendcode);
            verifyotp_btn = findViewById(R.id.verifyotp_btn);

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("OTP Verification");
        imgClose.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sOtp = bundle.getString("Key_otp");
            sName = bundle.getString("Key_Name");
            sPhone = bundle.getString("Key_Mobile");
            sMail = bundle.getString("Key_Email");
            sPassword = bundle.getString("Key_Password");
            sBlood = bundle.getString("Key_Blood");
        }
        txt_pin_entry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                otp = str.toString();
            }
        });

        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {

                txtResendcode.setText(" Resend Code in " + millisUntilFinished / 1000);
            }

            public void onFinish() {

                txtResendcode.setText("Resend OTP");

//                sos
//                        user_id
//                user_type
//                        admin_id
            }
        };
        countDownTimer.start();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.txtResendcode:
                if (txtResendcode.getText().toString().equalsIgnoreCase("Resend OTP")) {

                }
                break;
            case R.id.verifyotp_btn:
                if (otp.length() == 0 || otp.equalsIgnoreCase("")) {
                    Common.showToast(OtpVerificationActivity.this, "Please enter Otp.");
                } else if (!otp.equalsIgnoreCase(sOtp)) {
                    Common.showToast(OtpVerificationActivity.this, "Otp Not matched.");
//                    Bundle bundle = new Bundle();
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_CITY_SCREEN, bundle);
                } else {
                    //make service call here
                    //navigate to set resident address screens
                    Bundle bundle = new Bundle();
                    bundle.putString("Key_otp", otp);
                    bundle.putString("Key_Name", sName);
                    bundle.putString("Key_Mobile", sPhone);
                    bundle.putString("Key_Email", sMail);
                    bundle.putString("Key_Password", sPassword);
                    bundle.putString("Key_Blood", sBlood);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_CITY_SCREEN, bundle);
                }

                break;
        }
    }
}
