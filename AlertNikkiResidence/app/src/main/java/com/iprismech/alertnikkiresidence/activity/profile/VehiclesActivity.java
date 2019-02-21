package com.iprismech.alertnikkiresidence.activity.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.ProfileReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class VehiclesActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private ListView txtVehicls;
    private ImageView imgClose;
    private TextView txtitle;
    FloatingActionButton fab;

    ArrayList<View> viewList = new ArrayList<>();
    private Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_vehicles);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_vehicles, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        imgClose.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Vehicles");

        txtVehicls = findViewById(R.id.txtVehicls);
        fab = findViewById(R.id.fab);

        ProfileReq req = new ProfileReq();

        req.userId = SharedPrefsUtils.getInstance(VehiclesActivity.this).getId();
        req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);

        try {
            obj = Class.forName(ProfileReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_profile", true);

     /*   String[] arrVeh;
// = mVehicls.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(VehiclesActivity.this, android.R.layout.simple_list_item_1, arrVeh);
        txtVehicls.setAdapter(adapter);*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.fab:
                Intent intent = new Intent(VehiclesActivity.this, AddVehicleActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(VehiclesActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            String vehicle = jsonObject.optJSONObject("response").optString("vehicle_numbers");
                            if (vehicle != null) {
                                String[] arrVeh = vehicle.split(",");
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(VehiclesActivity.this, android.R.layout.simple_list_item_1, arrVeh);
                                txtVehicls.setAdapter(adapter);
                            }
                            break;
                    }
                } else {
                    Common.showToast(VehiclesActivity.this, jsonObject.optString("message"));
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
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PROFILE_SCREEN);
        finish();
    }
}
