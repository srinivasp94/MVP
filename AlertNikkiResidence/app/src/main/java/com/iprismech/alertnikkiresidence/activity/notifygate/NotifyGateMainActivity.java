package com.iprismech.alertnikkiresidence.activity.notifygate;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.profile.NotificationActivity;
import com.iprismech.alertnikkiresidence.adapters.NotifyFGateAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.Delete_notifygate_alert;
import com.iprismech.alertnikkiresidence.request.NotifyGate;
import com.iprismech.alertnikkiresidence.request.NotifyGateNotify;
import com.iprismech.alertnikkiresidence.response.NotifyGateList;
import com.iprismech.alertnikkiresidence.response.NotifyGateRes;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.AlertUtils;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class NotifyGateMainActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private LinearLayout ll_gateAlerts;
    private RelativeLayout rl_listItems;
    private RecyclerView rvGateAlerts;
    private ImageView fab;
    private TextView txtNotify;
    private Object obj;
    private LinearLayoutManager manager;

    private ArrayList<NotifyGateList> gateLists = new ArrayList<>();
    private NotifyFGateAdapter notifyFGateAdapter;

    private ImageView imgClose;
    private TextView txtitle;
    private boolean alertStatus = false;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gate_service);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_notify_gate_alerts, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtNotify.setOnClickListener(this);
        fab.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        manager = new LinearLayoutManager(NotifyGateMainActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        txtitle = findViewById(R.id.txtitle);

        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Notify Gate");

        ll_gateAlerts = findViewById(R.id.llGateAlerts);
        rl_listItems = findViewById(R.id.relativeListitems);
        rvGateAlerts = findViewById(R.id.rvGateAlerts);
        txtNotify = findViewById(R.id.txtNotify);
        fab = findViewById(R.id.fab);
        rvGateAlerts.setLayoutManager(manager);
        NotifyGate req = new NotifyGate();
        req.adminId = SharedPrefsUtils.getInstance(NotifyGateMainActivity.this).getAdminID();
        req.userId = SharedPrefsUtils.getInstance(this).getId();
        req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);

//        notify_gate_alert_list
        try {
            obj = Class.forName(NotifyGate.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "notify_gate_alert_list", true);

    }

    @Override
    public void setPresenter() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtNotify:
                //Navigete to services
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_GATE_SERVICE);
                finish();
                break;
            case R.id.fab:
                //Navigete to services
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_GATE_SERVICE);
                finish();
                break;
            case R.id.imgClose:

                onBackPressed();
                break;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(NotifyGateMainActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            NotifyGateRes response = Common.getSpecificDataObject(objectResponse, NotifyGateRes.class);

                            gateLists = (ArrayList<NotifyGateList>) response.response;
                            if (gateLists != null && gateLists.size() > 0) {
                                txtitle.setText("Notify Gate"+" ("+response.response.size()+")");
                                rl_listItems.setVisibility(View.VISIBLE);
                                ll_gateAlerts.setVisibility(View.GONE);
                                notifyFGateAdapter = new NotifyFGateAdapter(NotifyGateMainActivity.this, gateLists);
                                rvGateAlerts.setAdapter(notifyFGateAdapter);
                                notifyFGateAdapter.setOnItemClickListener(new NotifyFGateAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        switch (view.getId()) {
                                            case R.id.switc:
                                                Switch aSwitch = view.findViewById(R.id.switc);
                                                alertStatus = aSwitch.isChecked();
                                                callOnOffWs(position, alertStatus);
                                                break;
                                            case R.id.imgEditOpt:
                                                showDialogalert(NotifyGateMainActivity.this, view.getLeft() - (view.getRight() * 2),
                                                        view.getTop() + (view.getHeight() * 2 * (position + 2)), position);
                                                //swhow alert for edit and delete opts
/*                                                delete_notifygate_alert					Screen No:26
                                                Request:
                                                {
                                                    "gate_alert_id":"6",
                                                }*/

                                                break;
                                        }
                                    }
                                });
                            } else {
                                rl_listItems.setVisibility(View.GONE);
                                ll_gateAlerts.setVisibility(View.VISIBLE);
                            }
                            break;
                        case 3:
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_NOTIFY_GATE);
                            finish();
                            break;
                        case 4:
                            notifyFGateAdapter.notifyDataSetChanged();
                            break;
                    }
                } else {
                    Common.showToast(NotifyGateMainActivity.this, jsonObject.optString("message"));
                    rl_listItems.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void callOnOffWs(int position, boolean alertStatus) {
//        update_notifygate_alert_status
        /*{
            "gate_alert_id":"7",
                "alert_status":"0"
        }*/
        NotifyGateNotify req = new NotifyGateNotify();
        req.gateAlertId = gateLists.get(position).id;
        if (alertStatus) {

            req.alertStatus = "1";
            gateLists.get(position).alertStatus = "1";
        } else if (alertStatus == false) {
            req.alertStatus = "0";
            gateLists.get(position).alertStatus = "0";
        }
        try {
            obj = Class.forName(NotifyGateNotify.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 4, "update_notifygate_alert_status", true);
    }

    @SuppressLint("WrongConstant")
    private void callEditServiceWS(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("Key_ServiceName", gateLists.get(position).service);
        bundle.putString("Key_ServiceId", gateLists.get(position).id);
        bundle.putString("Key_GateId", gateLists.get(position).allowSecurityId);
        bundle.putString("Key_Name", gateLists.get(position).name);
        bundle.putString("Key_Mobile", gateLists.get(position).mobile);
        bundle.putString("Key_Valid", gateLists.get(position).vaildTo);
        bundle.putString("Key_Screen", "2");
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_EDIT_GATE_SERVICE, bundle);
        finish();
    }

    public void showDialogalert(Context context, int x, int y, final int pos) {
        // x -->  X-Cordinate
        // y -->  Y-Cordinate
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View view = LayoutInflater.from(NotifyGateMainActivity.this).inflate(R.layout.alert_edit, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        LinearLayout patientEdit = view.findViewById(R.id.edit);
        LinearLayout patientDelete = view.findViewById(R.id.delete);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wmlp.x = x;
        wmlp.y = y;
        patientEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEditServiceWS(pos);
                dialog.dismiss();
            }
        });
        patientDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertUtils.showSimpleAlert(NotifyGateMainActivity.this, "Are you sure want to delete ", "Alert", "Delete", "Cancel", new AlertUtils.onClicklistners() {
                    @Override
                    public void onpositiveclick() {
                        Delete_notifygate_alert req = new Delete_notifygate_alert();
                        req.gate_alert_id = gateLists.get(pos).id;
                        try {
                            obj = Class.forName(Delete_notifygate_alert.class.getName()).cast(req);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        new RetrofitRequester(NotifyGateMainActivity.this).callPostServices(obj, 3, "delete_notifygate_alert", true);
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });

            }
        });
        dialog.show();
    }

}
