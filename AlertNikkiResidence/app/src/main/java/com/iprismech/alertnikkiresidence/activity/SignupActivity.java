package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.iprismech.alertnikkiresidence.request.SignupReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private EditText edtSignupName, edtSignupPhone, edtSignupEmail, edtSignupBloodGrp, edtSignupPassword, edtSignupCnfPassword;
    private ImageView imgSignup;
    private TextView txtLogin;
    private Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup_screen);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_signup_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        imgSignup.setOnClickListener(this);
        txtLogin.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        edtSignupName = findViewById(R.id.edtSignupName);
        edtSignupPhone = findViewById(R.id.edtSignupPhone);
        edtSignupEmail = findViewById(R.id.edtSignupEmail);
        edtSignupBloodGrp = findViewById(R.id.edtSignupBloodGrp);
        edtSignupPassword = findViewById(R.id.edtSignupPassword);
        edtSignupCnfPassword = findViewById(R.id.edtSignupCnfPassword);
        imgSignup = findViewById(R.id.imgSignup);
        txtLogin = findViewById(R.id.txtLogin);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtLogin:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                finish();
                break;
            case R.id.imgSignup:

                if (edtSignupName.getText().toString().length() == 0) {
                    Common.showToast(SignupActivity.this, "Please Enter Name");
                } else if (edtSignupPhone.getText().toString().length() == 0 || edtSignupPhone.getText().toString().length() < 10) {
                    Common.showToast(SignupActivity.this, "Please Enter Phone");
                } else if (edtSignupEmail.getText().toString().length() == 0 || !isValidEmail(edtSignupEmail.getText().toString().trim())) {
                    Common.showToast(SignupActivity.this, "Please Enter valid Email");
                } else if (edtSignupBloodGrp.getText().toString().length() == 0) {
                    Common.showToast(SignupActivity.this, "Please Enter Blood Group");
                } else if (edtSignupPassword.getText().toString().length() == 0) {
                    Common.showToast(SignupActivity.this, "Please Enter Password");
                } else if (edtSignupCnfPassword.getText().toString().length() == 0) {
                    Common.showToast(SignupActivity.this, "Please Enter Confirm Password");
                } else if (!edtSignupPassword.getText().toString().equalsIgnoreCase(edtSignupCnfPassword.getText().toString())) {
                    Common.showToast(SignupActivity.this, "Make sure password and confirm password same");
                } else if (edtSignupPassword.getText().toString().equalsIgnoreCase(edtSignupCnfPassword.getText().toString())) {
                    if (isValidPassword(edtSignupPassword.getText().toString())) {
                        SignupReq req = new SignupReq();
                        req.name = edtSignupName.getText().toString();
                        req.mobile = edtSignupPhone.getText().toString();
                        req.emailId = edtSignupEmail.getText().toString();
                        req.password = edtSignupPassword.getText().toString();
                        req.otpConfirmed = "No";
                        try {
                            obj = Class.forName(SignupReq.class.getName()).cast(req);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        new RetrofitRequester(this).callPostServices(obj, 1, "register_user", true);

                    } else {
                        Toast.makeText(SignupActivity.this, R.string.password_note, Toast.LENGTH_LONG).show();
                    }
                } else {
                    //call service here
                    Toast.makeText(this, "Something went wrong in Registration", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    public boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidEmail(String email) {
        // return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(SignupActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Common.showToast(SignupActivity.this, jsonObject.optString("message"));
                            Bundle bundle = new Bundle();
                            bundle.putString("Key_otp", jsonObject.optString("response"));
                            bundle.putString("Key_Name", edtSignupName.getText().toString());
                            bundle.putString("Key_Mobile", edtSignupPhone.getText().toString());
                            bundle.putString("Key_Email", edtSignupEmail.getText().toString());
                            bundle.putString("Key_Password", edtSignupPassword.getText().toString());
                            bundle.putString("Key_Blood", edtSignupBloodGrp.getText().toString());
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_OTPVERIFICATION_SCREEN, bundle);
//                            finish();
                            break;
                    }
                } else {
                    Common.showToast(SignupActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
        finish();
    }
}
