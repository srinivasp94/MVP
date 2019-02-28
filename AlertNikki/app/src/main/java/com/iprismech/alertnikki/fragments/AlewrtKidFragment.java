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
import com.iprismech.alertnikki.Response.KidsGateAlerts;
import com.iprismech.alertnikki.adapters.AlertKidAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

public class AlewrtKidFragment extends BaseAbstractFragment implements RetrofitResponseListener {
    AlertKidAdapter adapter;
    private Object obj;
    private RecyclerView rv_alerts;
    private LinearLayoutManager manager;
    private ArrayList<KidsGateAlerts> kidsGateList = new ArrayList<>();

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                            kidsGateList = alertModel.kidsGateAlerts;

                            if (kidsGateList != null && kidsGateList.size() > 0) {
                                adapter = new AlertKidAdapter(getActivity(), kidsGateList);
                                rv_alerts.setAdapter(adapter);
                                adapter.setOnItemClickListener(new AlertKidAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position, ArrayList<KidsGateAlerts> arrayList) {

                                    }

                                    @Override
                                    public void onItemClick(View view, int position) {
                                        showAlertDialog_kid(position, object);
                                    }
                                });
                            } else {
                                //No data
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

    private void showAlertDialog_kid(final int position, JSONObject jsonObject) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view1 = inflater.inflate(R.layout.alert_kids, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);
//        alertDialog.setCancelable(false);


        TextView tv_kid_Name, tv_purpose, tv_intime, tv_outtime, tv_gingWith, bt_deny, btn_allow;
        ImageView HelperPic, img_guardian_pic;
        tv_kid_Name = view1.findViewById(R.id.tv_alert_kid_name);
        tv_purpose = view1.findViewById(R.id.tv_helper_passcode);
        tv_intime = view1.findViewById(R.id.tv_kids_intime);
        tv_outtime = view1.findViewById(R.id.tv_kids_alert_outtime);
        tv_gingWith = view1.findViewById(R.id.tv_goingWith);
        img_guardian_pic = view1.findViewById(R.id.img_guardian_pic);


        tv_kid_Name.setText(kidsGateList.get(position).kidName);
        tv_purpose.setText(kidsGateList.get(position).purpose);
        tv_intime.setText(kidsGateList.get(position).inTime);
        tv_outtime.setText(kidsGateList.get(position).outTime);
        tv_gingWith.setText("Going with "+kidsGateList.get(position).kidGoingWithWhom+", "+ kidsGateList.get(position).member.mobile);
        Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + kidsGateList.get(position).member.image).into(img_guardian_pic);


        btn_allow = view1.findViewById(R.id.tv_allow_kids_alert);
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
        req.notify_gate_id = kidsGateList.get(position).member.id;

        try {
            obj = Class.forName(NotifyAlertReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "security_accept_notify_gate_alert", true);

    }

}