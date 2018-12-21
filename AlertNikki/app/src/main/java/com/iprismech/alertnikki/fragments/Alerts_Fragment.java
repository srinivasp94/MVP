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
import com.iprismech.alertnikki.Request.NotifyAlertReq;
import com.iprismech.alertnikki.Response.AlertModel;
import com.iprismech.alertnikki.Response.AlertsCommon;
import com.iprismech.alertnikki.Response.DigitalGatepassAlert;
import com.iprismech.alertnikki.Response.KidsGateAlerts;
import com.iprismech.alertnikki.Response.NotifiGateAlerts;
import com.iprismech.alertnikki.adapters.AlertsAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Alerts_Fragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {
    private RecyclerView rv_alerts;
    private LinearLayoutManager manager;

    private ArrayList<DigitalGatepassAlert> digital_ist = new ArrayList<>();
    private ArrayList<NotifiGateAlerts> NotifyList = new ArrayList<>();
    private ArrayList<KidsGateAlerts> kidsList = new ArrayList<>();

    private ArrayList<AlertsCommon> commonAlertsList = new ArrayList<>();
    private AlertsAdapter alertsAdapter;
    private Object obj;
    RetrofitResponseListener responseListener;

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
        responseListener = this;
        Alert alert = new Alert();
        alert.admin_id = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
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
                            NotifyList = alertModel.notifyGateAlerts;
                            kidsList = alertModel.kidsGateAlerts;
                            AlertsCommon alertsCommon;

                            if (digital_ist != null && digital_ist.size() > 0) {
                                for (int i = 0; i < digital_ist.size(); i++) {
                                    alertsCommon = new AlertsCommon();
                                    alertsCommon.date = digital_ist.get(i).date;
                                    alertsCommon.service = "";
                                    alertsCommon.Description = digital_ist.get(i).description;
                                    alertsCommon.type_alert = "digital_alerts";
                                    alertsCommon.inTime = digital_ist.get(i).entryIn;
                                    alertsCommon.outTime = digital_ist.get(i).entryOut;
                                    alertsCommon.profilePic = digital_ist.get(i).image;

                                    alertsCommon.id = digital_ist.get(i).member.id;
                                    alertsCommon.passcode = digital_ist.get(i).member.passcode;
                                    alertsCommon.name = digital_ist.get(i).member.name;
                                    alertsCommon.phone = digital_ist.get(i).member.mobile;
                                    alertsCommon.society = digital_ist.get(i).member.society;
                                    alertsCommon.flat = digital_ist.get(i).member.flat;
                                    alertsCommon.residence_type = " ";
                                    alertsCommon.Building = digital_ist.get(i).member.building;
                                    alertsCommon.memberType = digital_ist.get(i).member.memberType;
                                    commonAlertsList.add(alertsCommon);
                                }
                            }

                            if (NotifyList != null && NotifyList.size() > 0) {
                                for (int j = 0; j < NotifyList.size(); j++) {
                                    alertsCommon = new AlertsCommon();
                                    alertsCommon.date = NotifyList.get(j).fromDate;
                                    alertsCommon.service = NotifyList.get(j).service;
                                    alertsCommon.Description = "Name: " + NotifyList.get(j).personName +
                                            ", Mobile" + NotifyList.get(j).personMobile;
                                    alertsCommon.type_alert = "Notify";
                                    alertsCommon.inTime = NotifyList.get(j).fromDateTime;
                                    alertsCommon.outTime = NotifyList.get(j).toDateTime;
                                    alertsCommon.profilePic = NotifyList.get(j).member.image;

                                    alertsCommon.id = NotifyList.get(j).member.id;
                                    alertsCommon.passcode = NotifyList.get(j).member.passcode;
                                    alertsCommon.name = NotifyList.get(j).member.name;
                                    alertsCommon.phone = NotifyList.get(j).member.mobile;
                                    alertsCommon.society = NotifyList.get(j).member.society;
                                    alertsCommon.flat = NotifyList.get(j).member.flat;
                                    alertsCommon.residence_type = NotifyList.get(j).member.residence_type;
                                    alertsCommon.Building = NotifyList.get(j).member.building;
                                    alertsCommon.memberType = NotifyList.get(j).member.memberType;
                                    commonAlertsList.add(alertsCommon);
                                }
                            }

                            if (kidsList != null && kidsList.size() > 0) {
                                for (int j = 0; j < kidsList.size(); j++) {
                                    alertsCommon = new AlertsCommon();
                                    alertsCommon.date = kidsList.get(j).fromDate;
                                    alertsCommon.service = kidsList.get(j).purpose;
                                    alertsCommon.Description = "Name: " + kidsList.get(j).kidGoingWithWhom +
                                            ", Mobile" + NotifyList.get(j).personMobile;
                                    alertsCommon.type_alert = "Kids";
                                    alertsCommon.inTime = kidsList.get(j).inTime;
                                    alertsCommon.outTime = kidsList.get(j).outTime;
                                    alertsCommon.profilePic = kidsList.get(j).member.image;

                                    alertsCommon.id = kidsList.get(j).member.id;
                                    alertsCommon.passcode = kidsList.get(j).member.passcode;
                                    alertsCommon.name = kidsList.get(j).member.name;
                                    alertsCommon.phone = kidsList.get(j).member.mobile;
                                    alertsCommon.society = kidsList.get(j).member.society;
                                    alertsCommon.flat = kidsList.get(j).member.flat;
                                    alertsCommon.residence_type = kidsList.get(j).member.residence_type;
                                    alertsCommon.Building = kidsList.get(j).member.building;
                                    alertsCommon.memberType = kidsList.get(j).member.memberType;
                                    commonAlertsList.add(alertsCommon);
                                }
                            }

                            if (commonAlertsList != null && commonAlertsList.size() > 0) {
                                alertsAdapter = new AlertsAdapter(getActivity(), commonAlertsList);
                                rv_alerts.setAdapter(alertsAdapter);
                                alertsAdapter.setOnItemClickListener(new AlertsAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        NotifyAlertReq req = new NotifyAlertReq();
                                        req.notify_gate_id = commonAlertsList.get(position).id;

                                        try {
                                            obj = Class.forName(NotifyAlertReq.class.getName()).cast(req);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        new RetrofitRequester(responseListener).callPostServices(obj, 2, "security_accept_notify_gate_alert", true);


                                    }
                                });
                            } else {
                                Common.showToast(getActivity(), "No data found");
                            }
                            break;
                        case 2:
                            Common.showToast(getActivity(), object.optString("message"));
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

