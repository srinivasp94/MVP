package com.iprismech.alertnikkiresidence.activity.schoolbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.RequestBus;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

public class AddBusActivity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {
    private EditText edtSclName, edtSclAddress, edtLandLine;
    private TextView busRequest;
    private Object obj;
    private String sclName, address, landline;

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
//        setContentView(R.layout.activity_add_bus);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_add_bus, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        busRequest.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        edtSclName = findViewById(R.id.edtSclName);
        edtSclAddress = findViewById(R.id.edtSclAddress);
        edtLandLine = findViewById(R.id.edtLandLine);
        busRequest = findViewById(R.id.busRequest);

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Add Bus");


    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(AddBusActivity.this, "Please Try Again");
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
                    Common.showToast(AddBusActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.busRequest:

                sclName = edtSclName.getText().toString();
                address = edtSclAddress.getText().toString();
                landline = edtLandLine.getText().toString();

                if (sclName.length() == 0 && address.length() == 0 && landline.length() == 0) {
                    Common.showToast(AddBusActivity.this, "Please Enter all details");
                } else if (address.length() == 0) {
                    Common.showToast(AddBusActivity.this, "Please Enter Address");
                } else if (landline.length() == 0 || landline.length() < 10) {
                    Common.showToast(AddBusActivity.this, "Please Enter LandLine Number");
                } else {
                    //school_bus_request
                    RequestBus req = new RequestBus();
                    req.adminId = "2";
                    req.flatId = "";
                    req.schoolBusName = edtSclName.getText().toString();
                    req.address = edtSclAddress.getText().toString();
                    req.mobile = edtLandLine.getText().toString();
                    req.driverName = "";
                    req.vehicleNumber = "";
                    try {
                        obj = Class.forName(RequestBus.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "school_bus_request", true);
                }
                break;
        }
    }
}
