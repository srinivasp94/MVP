package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.DailyHelpsAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.DailyHelpsListPojo;
import com.iprismech.alertnikkiresidence.pojo.MyStaff_Maids_List_Pojo;
import com.iprismech.alertnikkiresidence.request.DailyHelpsList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class AddStaffActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private GridView gv_daily_helps;
    private Object obj;
    private DailyHelpsListPojo dailyHelpsListPojo;
    private DailyHelpsAdapter daily_helps_adapter;
    private RetrofitResponseListener retrofitResponseListener;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_addstaff, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(AddStaffActivity.this, "Please Try Again");
        } else {
            try {

                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                dailyHelpsListPojo = gson.fromJson(jsonString, DailyHelpsListPojo.class);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    daily_helps_adapter = new DailyHelpsAdapter(AddStaffActivity.this, dailyHelpsListPojo);
                    gv_daily_helps.setAdapter(daily_helps_adapter);
                    gv_daily_helps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String service_id = dailyHelpsListPojo.getResponse().get(position).getId();
                            Bundle bundle = new Bundle();
                            bundle.putString("ServiceID", service_id);
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CHOOSE_MAID, bundle);
                            //finish();
                        }
                    });

                } else {
                    Common.showToast(AddStaffActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
            }
        }
    }

    protected void initializeViews() {
        super.initializeViews();
        retrofitResponseListener = this;
        ApplicationController.getInstance().setContext(context);

        gv_daily_helps = findViewById(R.id.gv_daily_helps_list);


        DailyHelpsList dailyHelpsReq = new DailyHelpsList();

        dailyHelpsReq.adminId = "2";
        //flatListRequest.building_id="4";
        try {
            obj = Class.forName(DailyHelpsList.class.getName()).cast(dailyHelpsReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "dailyhelps", true);


    }
}
