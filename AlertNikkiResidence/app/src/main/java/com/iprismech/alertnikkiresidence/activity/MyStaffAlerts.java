package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.StaffListAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.MyStaff_Maids_List_Pojo;
import com.iprismech.alertnikkiresidence.request.GuestsReq;
import com.iprismech.alertnikkiresidence.request.StaffRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class MyStaffAlerts extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private RelativeLayout RlStaffsLists;
    private LinearLayout layoutNoStaff;
    private FloatingActionButton fab;
    private RecyclerView rvSatffLists;
    private LinearLayoutManager manager;
    private RetrofitResponseListener retrofitResponseListener;
    private TextView txtAddStaff;
    private StaffListAdapter staffListAdapter;
    private Object obj;
    ImageView add_staff;
    private MyStaff_Maids_List_Pojo myStaff_maids_list_pojo;


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtAddStaff:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_STAFF_SCREEN);
                break;
            case R.id.fab_add_staff:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_STAFF_SCREEN);
                break;
        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        retrofitResponseListener = this;
        ApplicationController.getInstance().setContext(context);
        manager = new LinearLayoutManager(MyStaffAlerts.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        txtAddStaff = findViewById(R.id.txtAddStaff);
        layoutNoStaff = findViewById(R.id.layoutNoStaff);
        RlStaffsLists = findViewById(R.id.LayoutStaff);
        add_staff = findViewById(R.id.fab_add_staff);
        rvSatffLists = findViewById(R.id.rvStaffLists);

        String user_id = SharedPrefsUtils.getInstance(MyStaffAlerts.this).getId();

        rvSatffLists.setLayoutManager(manager);

        StaffRequest req = new StaffRequest();
        req.adminId = "2";
        req.userId = SharedPrefsUtils.getInstance(MyStaffAlerts.this).getId();
        //  req.userId = 22;
        try {
            obj = Class.forName(StaffRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_maids", true);


    }


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_my_staff, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtAddStaff.setOnClickListener(this);
        add_staff.setOnClickListener(this);
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(MyStaffAlerts.this, "Please Try Again");
        } else {
            try {

                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                myStaff_maids_list_pojo = gson.fromJson(jsonString, MyStaff_Maids_List_Pojo.class);

                JSONObject jsonObject = new JSONObject(jsonString);


                if (jsonObject.optBoolean("status")) {
                    layoutNoStaff.setVisibility(View.GONE);
                    RlStaffsLists.setVisibility(View.VISIBLE);


                    switch (requestId) {
                        case 1:
                            staffListAdapter = new StaffListAdapter(MyStaffAlerts.this, myStaff_maids_list_pojo);
                            rvSatffLists.setAdapter(staffListAdapter);

                            break;
                    }
                } else {
                    Common.showToast(MyStaffAlerts.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

