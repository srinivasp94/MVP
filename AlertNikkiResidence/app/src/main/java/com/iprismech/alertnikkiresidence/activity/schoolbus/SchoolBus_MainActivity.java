package com.iprismech.alertnikkiresidence.activity.schoolbus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.SchoolBusInfoAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.NotifySchoolBus;
import com.iprismech.alertnikkiresidence.request.SchoolBusReq;
import com.iprismech.alertnikkiresidence.response.SchoolBusList;
import com.iprismech.alertnikkiresidence.response.SchoolBusRes;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class SchoolBus_MainActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private RecyclerView rvSchools;
    private ImageView fab;
    private TextView txtbusAlerts;
    private Object obj;
    private ArrayList<SchoolBusList> schoolBusLists = new ArrayList<>();
    private SchoolBusInfoAdapter busInfoAdapter;
    LinearLayoutManager layoutManager;

    private ImageView imgClose;
    private TextView txtitle;
    private boolean isActive;
    private Switch aSwitch;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_schoo_bus_alerts, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        fab.setOnClickListener(this);
        txtbusAlerts.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        layoutManager = new LinearLayoutManager(SchoolBus_MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("School Bus");

        linearLayout = findViewById(R.id.lLayout);
        relativeLayout = findViewById(R.id.rLayoutSchools);
        rvSchools = findViewById(R.id.rvSchools);
        rvSchools.setLayoutManager(layoutManager);

        fab = findViewById(R.id.fab);
        txtbusAlerts = findViewById(R.id.txtBus);

        SchoolBusReq req = new SchoolBusReq();
        req.adminId = SharedPrefsUtils.getInstance(SchoolBus_MainActivity.this).getAdminID();
        req.userId = SharedPrefsUtils.getInstance(this).getId();
        req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);

        try {
            obj = Class.forName(SchoolBusReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_schoolbus_list", true);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_BUS_SCREEN);
                break;
            case R.id.txtBus:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_BUS_SCREEN);
                break;

            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(SchoolBus_MainActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            SchoolBusRes res = Common.getSpecificDataObject(objectResponse, SchoolBusRes.class);
                            schoolBusLists = (ArrayList<SchoolBusList>) res.response;

                            if (schoolBusLists != null && schoolBusLists.size() > 0) {
                                txtitle.setText("School Bus" + " (" + schoolBusLists.size() + ")");
                                linearLayout.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.VISIBLE);
                                busInfoAdapter = new SchoolBusInfoAdapter(SchoolBus_MainActivity.this, schoolBusLists);
                                rvSchools.setAdapter(busInfoAdapter);
                                busInfoAdapter.setOnItemClickListener(new SchoolBusInfoAdapter.OnitemClickListener() {
                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        switch (view.getId()) {
                                            case R.id.rootRelative:
//                                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_BUS_HISTORY_SCREEN);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("school_bus_id", schoolBusLists.get(position).userSchoolbusId);
                                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SCHOOL_BUS_HISTORY, bundle);
                                                // startActivity(new Intent(SchoolBus_MainActivity.this, BushistoryActivity.class));
                                                break;
                                            case R.id.busSwitch:
                                                //updateuser_schoolbus_notificationstatus
                                                aSwitch = view.findViewById(R.id.busSwitch);
                                                isActive = aSwitch.isChecked();
                                                callnotifyOnOff(position, isActive);
                                                break;
                                        }
                                    }
                                });
                            } else {
                                linearLayout.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                            }
                            break;
                        case 4:
                            busInfoAdapter.notifyDataSetChanged();
                            break;
                    }
                } else {
                    Common.showToast(SchoolBus_MainActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void callnotifyOnOff(int position, boolean isActive) {
        NotifySchoolBus req = new NotifySchoolBus();
        req.userSchoolbusId = schoolBusLists.get(position).userSchoolbusId;
        if (isActive) {

            req.status = "1";
            schoolBusLists.get(position).notificationStatus = "1";
        } else if (isActive == false) {
            req.status = "0";
            schoolBusLists.get(position).notificationStatus = "0";
        }
        try {
            obj = Class.forName(NotifySchoolBus.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 4, "updateuser_schoolbus_notificationstatus", true);

    }
}
