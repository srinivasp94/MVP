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
import com.iprismech.alertnikki.Response.NotifiGateAlerts;
import com.iprismech.alertnikki.adapters.AlertsAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class AlertsNotifyFragment extends BaseAbstractFragment implements RetrofitResponseListener {

    private RecyclerView rv_alerts;
    private LinearLayoutManager manager;
    private ArrayList<NotifiGateAlerts> NotifyList = new ArrayList<>();
    private Object obj;
    private AlertsAdapter alertsAdapter;

    @Override
    protected View getFragmentView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragments_alerts, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                            NotifyList = alertModel.notifyGateAlerts;
                            if (NotifyList != null && NotifyList.size() > 0) {
//                                Collections.reverse(NotifyList);
                                alertsAdapter = new AlertsAdapter(getActivity(), NotifyList);
                                if (NotifyList != null && NotifyList.size() > 0) {
                                    rv_alerts.setAdapter(alertsAdapter);
                                    alertsAdapter.setOnItemClickListener(new AlertsAdapter.OnitemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            showAlertDialog_Notify(position, object);
                                        }
                                    });
                                }
                            } else {
                                Common.showToast(getActivity(), "No data found");
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

    private void showAlertDialog_Notify(final int position, JSONObject object) {
        {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view1 = inflater.inflate(R.layout.alert_staff_alert, null);

            final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setView(view1);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            alertDialog.setCancelable(true);

            TextView tv_Name, tv_purpose, tv_description, btn_allow;
            ImageView HelperPic;

            tv_Name = view1.findViewById(R.id.tv_alert_staff_alert_name);
            tv_purpose = view1.findViewById(R.id.tv_role);
            tv_description = view1.findViewById(R.id.tv_no_response_content);
            HelperPic = view1.findViewById(R.id.img_alertPic);
            btn_allow = view1.findViewById(R.id.bt_allow);
            tv_Name.setText(NotifyList.get(position).personName);
            tv_purpose.setText(NotifyList.get(position).service);
            tv_description.setText("Name: " + NotifyList.get(position).personName +
                    ", Mobile: " + NotifyList.get(position).personMobile + " \n Coming To " +
                    NotifyList.get(position).member.flat + ", " + NotifyList.get(position).member.building + ", " + NotifyList.get(position).member.city);

            Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + NotifyList.get(position).member.image)
                    .error(R.drawable.dummy).into(HelperPic);

            btn_allow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call_Nottify_AlertWS(position);
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();
        }
    }

    private void call_Nottify_AlertWS(int position) {
        NotifyAlertReq req = new NotifyAlertReq();
        req.notify_gate_id = NotifyList.get(position).notifyGateId;
//        req.notify_gate_id = NotifyList.get(position).member.id;

        try {
            obj = Class.forName(NotifyAlertReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "security_accept_notify_gate_alert", true);

    }


}
