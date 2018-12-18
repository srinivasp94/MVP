package com.iprismech.alertnikki.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.Login_model;
import com.iprismech.alertnikki.Response.Login;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.AppPermissions;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class SecurityLoginActivity extends BaseAbstractActivity<Class> implements TextWatcher, RetrofitResponseListener {

    private EditText et_otp1, et_otp2, et_otp3, et_otp4, et_otp5, et_otp6;
    private Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_security_login);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_security_login, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        et_otp1.addTextChangedListener(this);
        et_otp2.addTextChangedListener(this);
        et_otp3.addTextChangedListener(this);
        et_otp4.addTextChangedListener(this);
        et_otp5.addTextChangedListener(this);
        et_otp6.addTextChangedListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        AppPermissions.callPermissionForFiles(context);
        et_otp1 = findViewById(R.id.edt_otp1);
        et_otp2 = findViewById(R.id.edt_otp2);
        et_otp3 = findViewById(R.id.edt_otp3);
        et_otp4 = findViewById(R.id.edt_otp4);
        et_otp5 = findViewById(R.id.edt_otp5);
        et_otp6 = findViewById(R.id.edt_otp6);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_otp1.getText().toString().length() == 1) {
            et_otp2.requestFocus();
            if (et_otp2.getText().toString().length() == 1) {
                et_otp3.requestFocus();
                if (et_otp3.getText().toString().length() == 1) {
                    et_otp4.requestFocus();
                    if (et_otp4.getText().toString().length() == 1) {
                        et_otp5.requestFocus();
                        if (et_otp5.getText().toString().length() == 1) {
                            et_otp6.requestFocus();
                            if (et_otp6.getText().toString().length() == 1) {
                                Toast.makeText(context, "Text 123456 ", Toast.LENGTH_SHORT).show();
                                String passcode = et_otp1.getText().toString() + et_otp2.getText().toString() + et_otp3.getText().toString() +
                                        et_otp4.getText().toString() + et_otp5.getText().toString() + et_otp6.getText().toString();

                                Login_model login_model = new Login_model();
                                login_model.passcode = "8938960";
                                login_model.selected_image = "base640";
                                try {
                                    obj = Class.forName(Login_model.class.getName()).cast(login_model);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                new RetrofitRequester(this).callPostServices(obj, 1, "login", true);
                            }
                        }
                    }
                }
            }


//            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CAMERA_SCREEN);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (s.length() == 1) {

        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(SecurityLoginActivity.this, "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Login login = Common.getSpecificDataObject(objectResponse, Login.class);
                            SharedPrefsUtils.getInstance(this).createUserSession(login.response.securityId,
                                    login.response.adminId, login.response.loginDate, login.response.loginTime
                                    , login.response.society, login.response.city);

                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CAMERA_SCREEN);

                            break;
                    }
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
