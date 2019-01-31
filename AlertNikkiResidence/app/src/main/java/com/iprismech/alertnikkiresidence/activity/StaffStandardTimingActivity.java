package com.iprismech.alertnikkiresidence.activity;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.MyStaffAlerts;
import com.iprismech.alertnikkiresidence.adapters.TimeSlotAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.StandardMaidTimingPojo;
import com.iprismech.alertnikkiresidence.request.StaffRequest;
import com.iprismech.alertnikkiresidence.request.StandardMaidTimingsRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class StaffStandardTimingActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private Object obj;
    private TimeSlotAdapter slotAdapter;
    GridView gv_timeslots;
    TextView txtAddSlots;
    ArrayList<StandardMaidTimingPojo.ResponseBean> list = new ArrayList<>();
//    getSelectedSlots

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtAddSlots:
                list = slotAdapter.getSelectedSlots();
                if (list != null && list.size() > 0) {
//intent pass here or service call here

                } else {
                    Common.showToast(StaffStandardTimingActivity.this, "please selelct Available time slots");
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
        gv_timeslots = findViewById(R.id.gv_timeslots);
        txtAddSlots = findViewById(R.id.txtAddSlots);

        StandardMaidTimingsRequest req = new StandardMaidTimingsRequest();
        req.adminId = "2";
        req.maid_id = "2";
        try {
            obj = Class.forName(StandardMaidTimingsRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "standard_timings", true);

    }

    @Override
    public void setPresenter() {

    }

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
