package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.ResetPasswordRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPasswordActivity extends BaseAbstractActivity implements RetrofitResponseListener {
    private EditText et_enterpass, et_confirmpass;
    private TextView btn_passwordSave;
    private Object obj;
    private RetrofitResponseListener retrofitResponseListener;
    private String userID;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.set_password_layout, null);
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
            userID = bundle.getString("Key_userID");
        }


        et_enterpass = findViewById(R.id.enterpass_et);
        et_confirmpass = findViewById(R.id.confirmpass_et);
        btn_passwordSave = findViewById(R.id.password_save_btn);
        retrofitResponseListener = this;
        btn_passwordSave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                String etr_pass = et_enterpass.getText().toString();
                String cnf_pass = et_confirmpass.getText().toString();
                Log.d("password_data", etr_pass + cnf_pass);
                if (et_enterpass.getText().toString().isEmpty() || et_enterpass.getText().toString() == "") {
                    //et_enterpass.setError("Please Enter Password");
                    Toast.makeText(context, "Please enter Password", Toast.LENGTH_LONG).show();
                } else if (et_enterpass.getText().toString().length() < 6) {
                    // et_enterpass.setError("Password must be min 6 letters");
                    Toast.makeText(context, "Password must be min 6 Chars", Toast.LENGTH_LONG).show();
                } else if (et_confirmpass.getText().toString().isEmpty() || et_confirmpass.getText().toString() == "") {
                    //et_confirmpass.setError("Please Confirm Password");
                    Toast.makeText(context, "Please enter Confirm Password", Toast.LENGTH_LONG).show();
                } else if (et_confirmpass.getText().toString().length() < 6) {
                    //et_confirmpass.setError("Password must be min 6 letters");
                    Toast.makeText(context, "Password must be min 6 Chars", Toast.LENGTH_LONG).show();

                } else if (etr_pass.isEmpty() && cnf_pass.isEmpty()) {
                    Toast.makeText(context, "Please Enter Password", Toast.LENGTH_LONG).show();
                } else if (!et_enterpass.getText().toString().equals(et_confirmpass.getText().toString())) {
                    Toast.makeText(ResetPasswordActivity.this, "Password Not matched", Toast.LENGTH_SHORT).show();
                } else if (et_enterpass.getText().toString().equals(et_confirmpass.getText().toString())) {
                    if (isValidPassword(et_enterpass.getText().toString())) {

                        ResetPasswordRequest req = new ResetPasswordRequest();

                        req.password = et_enterpass.getText().toString();
                        req.userId = userID;


                        try {
                            obj = Class.forName(ResetPasswordRequest.class.getName()).cast(req);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 1, "user_update_password", true);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);


//                    Toast.makeText(ResetPasswordActivity.this, "Password updated Successfully ", Toast.LENGTH_SHORT).show();
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);

                    } else {
                        Toast.makeText(ResetPasswordActivity.this, R.string.password_note, Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

    }

    public boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ResetPasswordActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Toast.makeText(ResetPasswordActivity.this, "Password updated Successfully ", Toast.LENGTH_SHORT).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(ResetPasswordActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
