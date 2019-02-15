package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.MyStaffAlerts;
import com.iprismech.alertnikkiresidence.adapters.TimeSlotAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.StandardMaidTimingPojo;
import com.iprismech.alertnikkiresidence.request.AddStaffMaidRequest;
import com.iprismech.alertnikkiresidence.request.StaffRequest;
import com.iprismech.alertnikkiresidence.request.StandardMaidTimingsRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StaffStandardTimingActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private Object obj;
    private TimeSlotAdapter slotAdapter;
    GridView gv_timeslots;
    TextView txtAddSlots;
    // ArrayList<StandardMaidTimingPojo.ResponseBean> list = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    String working_type, maid_id, start_date, end_date;
    //    getSelectedSlots
    private ImageView imgClose;
    private TextView txtitle;
    private String screentype;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.txtAddSlots:
                list = slotAdapter.getSelectedSlots();
                if (list != null && list.size() > 0) {
//intent pass here or service call here

                    AddStaffMaidRequest req = new AddStaffMaidRequest();
                    req.adminId = "2";
                    req.userType = SharedPrefsUtils.getInstance(StaffStandardTimingActivity.this).getuserType();
                    req.flatId = SharedPrefsUtils.getInstance(StaffStandardTimingActivity.this).getFlatId();
                    req.maidId = maid_id;
                    req.availableTimes = list;
                    req.workingType = working_type;
                    req.fromDate = start_date;
                    req.toDate = end_date;
                    req.userId = SharedPrefsUtils.getInstance(StaffStandardTimingActivity.this).getId();

                    try {
                        obj = Class.forName(AddStaffMaidRequest.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 2, "add_maidto_user", true);


                } else {
                    Common.showToast(StaffStandardTimingActivity.this, "please select Available time slots");
                }
                break;
        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtAddSlots.setOnClickListener(this);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_standard_timings, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Staff Timings");
        imgClose.setOnClickListener(this);
        gv_timeslots = findViewById(R.id.gv_timeslots);
        txtAddSlots = findViewById(R.id.txtAddSlots);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            maid_id = getIntent().getExtras().getString("Key_MaidId", "");
            working_type = getIntent().getExtras().getString("WorkingType", "");
            start_date = getIntent().getExtras().getString("From_Date", "");
            end_date = getIntent().getExtras().getString("ToDate", "");
            screentype = getIntent().getExtras().getString("Key_screen", "");
        }
        if (screentype.equalsIgnoreCase("Choose Maid")) {
            txtAddSlots.setVisibility(View.VISIBLE);
        } else if (screentype.equalsIgnoreCase("Avalable")) {
            txtAddSlots.setVisibility(View.GONE);
        }
        StandardMaidTimingsRequest req = new StandardMaidTimingsRequest();
        req.adminId = "2";
        req.maid_id = maid_id;
        try {
            obj = Class.forName(StandardMaidTimingsRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "standard_timings", true);


        //        ArrayList<StandardMaidTimingPojo.ResponseBean> list=slotAdapter.getSelectedSlots();

    }

    @Override
    public void setPresenter() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(StaffStandardTimingActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            StandardMaidTimingPojo response = Common.getSpecificDataObject(objectResponse, StandardMaidTimingPojo.class);
                            slotAdapter = new TimeSlotAdapter(StaffStandardTimingActivity.this, response);
                            gv_timeslots.setAdapter(slotAdapter);
//                            if (screentype.equalsIgnoreCase("Avalable")) {
//                                txtAddSlots.setVisibility(View.GONE);
//                            }
                            break;
                        case 2:
                            Toast.makeText(StaffStandardTimingActivity.this, "Satff added sucessfully", Toast.LENGTH_SHORT).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MYSTAFF_ALERTS);
                            break;
                    }
                } else {
                    Common.showToast(StaffStandardTimingActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
