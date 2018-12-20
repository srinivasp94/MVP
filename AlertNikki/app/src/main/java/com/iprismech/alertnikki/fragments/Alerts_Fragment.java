package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.Alert;
import com.iprismech.alertnikki.Response.AlertModel;
import com.iprismech.alertnikki.Response.DigitalGatepassAlert;
import com.iprismech.alertnikki.adapters.AlertsAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Alerts_Fragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {
    private RecyclerView rv_alerts;
    private LinearLayoutManager manager;
    private List<DigitalGatepassAlert> digital_ist = new ArrayList<>();
    private AlertsAdapter alertsAdapter;
    private Object obj;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragments_alerts, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();


    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_alerts = view.findViewById(R.id.rv_alerts);
        Alert alert = new Alert();
        alert.admin_id = "2";
        try {
            obj = Class.forName(Alert.class.getName()).cast(alert);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "alerts", true);


        rv_alerts.setLayoutManager(manager);


    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getActivity(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            AlertModel alertModel = Common.getSpecificDataObject(objectResponse, AlertModel.class);
                            digital_ist = alertModel.digitalGatepassAlerts;

                            alertsAdapter = new AlertsAdapter(getActivity(), digital_ist);
                            if (digital_ist != null && digital_ist.size() > 0) {
                                rv_alerts.setAdapter(alertsAdapter);
                            } else {
                                Common.showToast(getActivity(), "No data found");
                            }
                            break;
                    }
                } else {
                    Common.showToast(getActivity(), object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

