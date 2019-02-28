package com.iprismech.alertnikkiresidence.activity.notifygate;

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
import com.iprismech.alertnikkiresidence.request.EditGateReq;
import com.iprismech.alertnikkiresidence.request.NotifyGateAdd;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class EditGateAlertActivity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {
    private ImageView imgClose;
    private TextView txtitle;
    private EditText edtServiceType, edtPersonName, edtPhone;
    TextView edtValidHours;
    private TextView notifyGate;
    private Object obj;
    private String serviceName, serviceId, screen, gateId,
            Name = "", Mobile = "", Valid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_gate_alert);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_edit_gate_alert, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        notifyGate.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            serviceName = bundle.getString("Key_ServiceName", "");
            serviceId = bundle.getString("Key_ServiceId", "");
            screen = bundle.getString("Key_Screen", "");
            gateId = bundle.getString("Key_GateId", "");
            Name = bundle.getString("Key_Name", "");
            Mobile = bundle.getString("Key_Mobile", "");
            Valid = bundle.getString("Key_Valid", "");
        }
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        if (screen.equalsIgnoreCase("1"))
            txtitle.setText("Add " +
                    "Notify Gate");
        else if (screen.equalsIgnoreCase("2"))
            txtitle.setText("Edit Notify Gate");
        notifyGate = findViewById(R.id.notifyGate);
        edtServiceType = findViewById(R.id.edtServiceType);
        edtPersonName = findViewById(R.id.edtPersonName);
        edtPhone = findViewById(R.id.edtPhone);
        edtValidHours = findViewById(R.id.edtValidHours);

        edtServiceType.setText(serviceName);
        edtPersonName.setText(Name);
        edtPhone.setText(Mobile);
//        edtValidHours.setText(Valid);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notifyGate:
                String strService = edtServiceType.getText().toString();
                String strName = edtPersonName.getText().toString();
                String strPhone = edtPhone.getText().toString();
                String strValidTill = edtValidHours.getText().toString();
                if (strName.length() == 0 && strPhone.length() == 0 && strValidTill.length() == 0) {
                    Common.showToast(EditGateAlertActivity.this, "Enter All Fields");
                } else if (strName.length() == 0) {
                    Common.showToast(EditGateAlertActivity.this, "Enter Name");
                } else if (strPhone.length() == 0 || strPhone.length() < 10) {
                    Common.showToast(EditGateAlertActivity.this, "Enter Mobile");
                } else if (screen.equalsIgnoreCase("2")) {
                    //edit_notify_gate_alert
                    EditGateReq req = new EditGateReq();
                    req.gateAlertId = gateId;
                    req.serviceId = serviceId;
                    req.name = edtPersonName.getText().toString();
                    req.mobile = strPhone;
                    req.vaildTo = "3";

                    try {
                        obj = Class.forName(EditGateReq.class.getName()).cast(req);
                    } catch (Exception e) {

                    }
                    new RetrofitRequester(this).callPostServices(obj, 2, "edit_notify_gate_alert", true);
                } else if (screen.equalsIgnoreCase("1")) {
                    //notify_gate_alert
                    NotifyGateAdd req = new NotifyGateAdd();
                    req.adminId = SharedPrefsUtils.getInstance(EditGateAlertActivity.this).getAdminID();
                    req.userId = SharedPrefsUtils.getInstance(EditGateAlertActivity.this).getId();
                    req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);
                    req.serviceId = serviceId;
                    req.name = edtPersonName.getText().toString();
                    req.mobile = strPhone;
                    req.vaildTo = strValidTill;

                    try {
                        obj = Class.forName(NotifyGateAdd.class.getName()).cast(req);
                    } catch (Exception e) {

                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "notify_gate_alert", true);

                }
                break;
            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(EditGateAlertActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_NOTIFY_GATE);
                            finish();
                            break;
                        case 2:
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_NOTIFY_GATE);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(EditGateAlertActivity.this, jsonObject.optString("message"));
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
        if (screen.equalsIgnoreCase("1")) {
            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_GATE_SERVICE);
            finish();
        } else {
            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_NOTIFY_GATE);
            finish();
        }
    }
}
