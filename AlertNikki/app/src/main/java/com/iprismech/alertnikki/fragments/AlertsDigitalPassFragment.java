package com.iprismech.alertnikki.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AdminStaff;
import com.iprismech.alertnikki.Request.Alert;
import com.iprismech.alertnikki.Request.NotifyAlertReq;
import com.iprismech.alertnikki.Response.AlertModel;
import com.iprismech.alertnikki.Response.DigitalGatepassAlert;
import com.iprismech.alertnikki.adapters.AlertDigitalGatepassAdapter;
import com.iprismech.alertnikki.adapters.GatepassImagesAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

public class AlertsDigitalPassFragment extends BaseAbstractFragment implements RetrofitResponseListener {
    private RecyclerView rv_alerts;
    private LinearLayoutManager manager;

    private ArrayList<DigitalGatepassAlert> digital_ist = new ArrayList<>();
    private Object obj;
    AlertDigitalGatepassAdapter adapter;

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
    public void initialiseViews() {
        super.initialiseViews();

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rv_alerts = view.findViewById(R.id.rv_alerts);

        AdminStaff req = new AdminStaff();


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
                final JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            AlertModel alertModel = Common.getSpecificDataObject(objectResponse, AlertModel.class);
                            digital_ist = alertModel.digitalGatepassAlerts;
                            adapter = new AlertDigitalGatepassAdapter(getActivity(), digital_ist);
                            if (digital_ist != null && digital_ist.size() > 0) {

                                rv_alerts.setAdapter(adapter);
                                adapter.setOnItemClickListener(new AlertDigitalGatepassAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        showAlertDialog_Digital(position);
                                    }
                                });
                            } else {
//                                No data
                            }
                            break;
                        case 2:
                            Common.showToast(getActivity(), object.optString("message"));
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void showAlertDialog_Digital(final int position) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view1 = inflater.inflate(R.layout.alert_staff_alert, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        alertDialog.setCancelable(true);

        TextView tv_Name, tv_purpose, tv_description, btn_allow;
        ImageView HelperPic;


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        RecyclerView rv_digitalImages = view1.findViewById(R.id.rv_digitalImages);
        rv_digitalImages.setLayoutManager(linearLayoutManager);

        tv_Name = view1.findViewById(R.id.tv_alert_staff_alert_name);
        tv_purpose = view1.findViewById(R.id.tv_role);
        tv_description = view1.findViewById(R.id.tv_no_response_content);
        HelperPic = view1.findViewById(R.id.img_alertPic);
        btn_allow = view1.findViewById(R.id.bt_allow);
        tv_Name.setText(digital_ist.get(position).member.name);
        tv_purpose.setText(digital_ist.get(position).member.memberType);
        tv_description.setText(digital_ist.get(position).description);

        Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + digital_ist.get(position).image)
                .error(R.drawable.dummy).into(HelperPic);


        GatepassImagesAdapter gatepassImagesAdapter = new GatepassImagesAdapter(getActivity(), digital_ist.get(position).images);
        rv_digitalImages.setAdapter(gatepassImagesAdapter);

        btn_allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_Nottify_AlertWS(position);
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void call_Nottify_AlertWS(int position) {
        NotifyAlertReq req = new NotifyAlertReq();
        req.notify_gate_id = digital_ist.get(position).member.id;

        try {
            obj = Class.forName(NotifyAlertReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "security_accept_notify_gate_alert", true);

    }


}
