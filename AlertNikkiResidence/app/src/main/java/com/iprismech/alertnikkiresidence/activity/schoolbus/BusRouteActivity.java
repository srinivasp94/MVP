package com.iprismech.alertnikkiresidence.activity.schoolbus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.BusRoutesAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.BusRouteReq;
import com.iprismech.alertnikkiresidence.request.SaveSchoolBusRoute;
import com.iprismech.alertnikkiresidence.response.BusRoute;
import com.iprismech.alertnikkiresidence.response.BusRouteList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class BusRouteActivity extends BaseAbstractActivity implements RetrofitResponseListener {

    private RecyclerView rv_busRoute;
    private LinearLayoutManager layoutManager;
    private Object obj;
    private ArrayList<BusRouteList> busList = new ArrayList();
    private BusRoutesAdapter routesAdapter;
    TextView txt_NoItems, txtSave;

    private ImageView imgClose;
    private TextView txtitle;
    private String busid;


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
        View view = getLayoutInflater().inflate(R.layout.activity_select_bus_root, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                saveBusRouteWS();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void saveBusRouteWS(String routeID) {
        SaveSchoolBusRoute req = new SaveSchoolBusRoute();
        req.adminId = SharedPrefsUtils.getInstance(BusRouteActivity.this).getAdminID();

        req.userId = SharedPrefsUtils.getInstance(BusRouteActivity.this).getId();
        req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);
        req.schoolBusId = busid;
        req.routeId = routeID;
        try {
            obj = Class.forName(SaveSchoolBusRoute.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 2, "save_user_schoolbus", true);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        Bundle bundle = getIntent().getExtras();
        busid = bundle.getString("KEY_BUS_ID", "");

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Select Bus Routes");

        layoutManager = new LinearLayoutManager(BusRouteActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        //school_bus_routes
        rv_busRoute = findViewById(R.id.rvBusRoute);
        txt_NoItems = findViewById(R.id.txt_NoItems);
        txtSave = findViewById(R.id.txtSave);
        txtSave.setVisibility(View.GONE);

        rv_busRoute.setLayoutManager(layoutManager);

        BusRouteReq req = new BusRouteReq();
        req.adminId = SharedPrefsUtils.getInstance(BusRouteActivity.this).getAdminID();

        req.schoolBusId = busid;

        try {
            obj = Class.forName(BusRouteReq.class.getName()).cast(req);
        } catch (Exception e) {
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "school_bus_routes", true);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(BusRouteActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            BusRoute response = Common.getSpecificDataObject(objectResponse, BusRoute.class);
                            busList = (ArrayList<BusRouteList>) response.response;

                            if (busList != null && busList.size() > 0) {

                                routesAdapter = new BusRoutesAdapter(BusRouteActivity.this, busList);
                                rv_busRoute.setAdapter(routesAdapter);
                                routesAdapter.setOnItemClickListener(new BusRoutesAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        switch (view.getId()) {
                                            case R.id.ll_buildingBlock:
                                                saveBusRouteWS(busList.get(position).id);
                                                break;
                                        }
                                    }
                                });
                            } else {
                                txt_NoItems.setVisibility(View.VISIBLE);
                                rv_busRoute.setVisibility(View.GONE);
                                txtSave.setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            Toast.makeText(this, "Bus route Added successfully", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SCHOOL_BUS_SCREEN);
                                    finish();
                                }
                            }, 5000);

                            break;
                    }
                } else {
                    Common.showToast(BusRouteActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
