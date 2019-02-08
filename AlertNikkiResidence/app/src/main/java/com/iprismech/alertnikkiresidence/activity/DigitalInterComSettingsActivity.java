package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.GetDigitalInterComPojo;
import com.iprismech.alertnikkiresidence.request.DigitalIntercomRequest;
import com.iprismech.alertnikkiresidence.request.DigitalntercomSettingsRequest;
import com.iprismech.alertnikkiresidence.request.StandardMaidTimingsRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class DigitalInterComSettingsActivity extends BaseAbstractActivity implements RetrofitResponseListener {
    private Object obj;
    private GetDigitalInterComPojo getDigitalInterComPojo;
    private EditText et_primary_number, et_sec_number;
    private TextView tv_save;
    private RetrofitResponseListener retrofitResponseListener;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_digital_intercom, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        retrofitResponseListener = this;
        et_primary_number = findViewById(R.id.et_primary);
        et_sec_number = findViewById(R.id.et_sec_number);
        tv_save = findViewById(R.id.save_intercom);


        DigitalIntercomRequest req = new DigitalIntercomRequest();
        req.digital_intercom_id = "2";

        try {
            obj = Class.forName(DigitalIntercomRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "get_digital_intercom_settings", true);

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DigitalntercomSettingsRequest req = new DigitalntercomSettingsRequest();
                req.adminId = "2";
                req.userId = SharedPrefsUtils.getInstance(DigitalInterComSettingsActivity.this).getId();
                req.userType = SharedPrefsUtils.getInstance(DigitalInterComSettingsActivity.this).getuserType();
                req.primaryNumber = et_primary_number.getText().toString();
                req.secondaryNumber = et_primary_number.getText().toString();

                try {
                    obj = Class.forName(DigitalntercomSettingsRequest.class.getName()).cast(req);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "digital_intercom_settings", true);
            }
        });

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(DigitalInterComSettingsActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:

                            getDigitalInterComPojo = gson.fromJson(jsonString, GetDigitalInterComPojo.class);

                            et_primary_number.setText("" + getDigitalInterComPojo.getResponse().get(0).getPrimary_number());
                            et_sec_number.setText("" + getDigitalInterComPojo.getResponse().get(0).getSecondary_number());

                            break;
                        case 2:
                            Common.showToast(DigitalInterComSettingsActivity.this, jsonObject.optString("message"));
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_DIGITAL_INTERCOM_SCREEN);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(DigitalInterComSettingsActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
