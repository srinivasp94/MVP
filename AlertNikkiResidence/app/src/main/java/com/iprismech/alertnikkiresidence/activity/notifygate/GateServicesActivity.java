package com.iprismech.alertnikkiresidence.activity.notifygate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.GateServicesAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.response.ServiceGateRes;
import com.iprismech.alertnikkiresidence.response.ServiceList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

import java.util.ArrayList;

public class GateServicesActivity extends BaseAbstractActivity implements RetrofitResponseListener {
    private ImageView imgClose;
    private TextView txtitle;

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_NOTIFY_GATE);
        finish();
    }

    private RecyclerView rv_services;
    private LinearLayoutManager manager;
    private Object obj;
    private GateServicesAdapter servicesAdapter;
    private ArrayList<ServiceList> serviceList = new ArrayList<>();
    private TextView txt_NoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_gate_service, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Notify Gate Services");

        rv_services = findViewById(R.id.rv_services);
        txt_NoItems = findViewById(R.id.txt_NoItems);
        manager = new LinearLayoutManager(GateServicesActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rv_services.setLayoutManager(manager);

        try {
            obj = new Object();
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "notify_gate_services", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(GateServicesActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            ServiceGateRes gateRes = Common.getSpecificDataObject(objectResponse, ServiceGateRes.class);
                            serviceList = (ArrayList<ServiceList>) gateRes.response;
                            if (serviceList != null && serviceList.size() > 0) {
                                servicesAdapter = new GateServicesAdapter(GateServicesActivity.this, serviceList);
                                rv_services.setAdapter(servicesAdapter);
                                servicesAdapter.setOnItemClickListener(new GateServicesAdapter.OnitemClickListener() {
                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        switch (view.getId()) {
                                            case R.id.rlItem:
                                                Bundle bundle = new Bundle();
                                                bundle.putString("Key_ServiceName", serviceList.get(position).title);
                                                bundle.putString("Key_ServiceId", serviceList.get(position).id);
                                                bundle.putString("Key_Screen", "1");
                                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_EDIT_GATE_SERVICE, bundle);
                                                finish();
                                                break;
                                        }
                                    }
                                });
                            } else {
                                txt_NoItems.setVisibility(View.VISIBLE);
                                rv_services.setVisibility(View.GONE);
                            }

                            break;
                    }
                } else {
                    Common.showToast(GateServicesActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
