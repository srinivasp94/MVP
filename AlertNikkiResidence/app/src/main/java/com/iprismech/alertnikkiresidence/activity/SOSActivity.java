package com.iprismech.alertnikkiresidence.activity;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.Guestinvite;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class SOSActivity extends BaseAbstractActivity implements RetrofitResponseListener {
    private TextView txtCountdown, txtcallActive;
    private Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sos);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_sos, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ApplicationController.getInstance().setContext(context);

        txtCountdown = findViewById(R.id.txtCountdown);
        txtcallActive = findViewById(R.id.txtcallActive);

        CountDownTimer countDownTimer = new CountDownTimer(11000, 1000) {

            public void onTick(long millisUntilFinished) {

                txtCountdown.setText(" " + millisUntilFinished / 1000);
            }

            public void onFinish() {

                txtCountdown.setText("✓");
                txtCountdown.setTextColor(Color.parseColor("#ffffff"));
                txtcallActive.setVisibility(View.VISIBLE);

//                sos
//                        user_id
//                user_type
//                        admin_id
                Guestinvite req = new Guestinvite();
                req.userId = SharedPrefsUtils.getInstance(SOSActivity.this).getId();
                req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);
                req.adminId = SharedPrefsUtils.getInstance(SOSActivity.this).getAdminID();
                try {
                    obj = Class.forName(Guestinvite.class.getName()).cast(req);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(SOSActivity.this).callPostServices(obj, 1, "sos", false);
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(SOSActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(SOSActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
