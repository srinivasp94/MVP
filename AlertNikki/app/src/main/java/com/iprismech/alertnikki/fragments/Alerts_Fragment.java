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
import com.google.gson.reflect.TypeToken;
import com.iprismech.alertnikki.MainActivity;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AdminStaff;
import com.iprismech.alertnikki.Request.Alert;
import com.iprismech.alertnikki.Request.NotifyAlertReq;
import com.iprismech.alertnikki.Response.AlertModel;
import com.iprismech.alertnikki.Response.AlertsCommon;
import com.iprismech.alertnikki.Response.DigitalGatepassAlert;
import com.iprismech.alertnikki.Response.KidsGateAlerts;
import com.iprismech.alertnikki.Response.NotifiGateAlerts;
import com.iprismech.alertnikki.adapters.AlertsAdapter;
import com.iprismech.alertnikki.adapters.GatepassImagesAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    private AdminStaff req;
    private SharedPrefsUtils sharedPrefsUtils;

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
         req = new AdminStaff();
        
        
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
                         //   AlertModel alertModel = gson.fromJson(jsonString,AlertModel.class);

                            digital_ist = alertModel.digitalGatepassAlerts;
                            NotifyList = alertModel.notifyGateAlerts;
                            kidsList = alertModel.kidsGateAlerts;
                            AlertsCommon alertsCommon;

                            if (digital_ist != null && digital_ist.size() > 0) {
                                for (int i = 0; i < digital_ist.size(); i++) {
                                    alertsCommon = new AlertsCommon();
                                    alertsCommon.date = digital_ist.get(i).date;
                                    alertsCommon.service = digital_ist.get(i).member.memberType;
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


                                    try {
                                        alertsCommon.imagesLists = digital_ist.get(i).images;
                                    } catch (Exception e) {

                                    }
                                    commonAlertsList.add(alertsCommon);



                                }
                            }
                            if (NotifyList != null && NotifyList.size() > 0) {
                                for (int j = 0; j < NotifyList.size(); j++) {
                                    alertsCommon = new AlertsCommon();
                                    alertsCommon.date = NotifyList.get(j).fromDate;
                                    alertsCommon.service = NotifyList.get(j).service;
                                    alertsCommon.Description = "Name: " + NotifyList.get(j).personName +
                                            ", Mobile: " + NotifyList.get(j).personMobile;
                                    alertsCommon.type_alert = "Notify";
                                    alertsCommon.inTime = NotifyList.get(j).fromDateTime;
                                    alertsCommon.outTime = NotifyList.get(j).toDateTime;
                                    alertsCommon.profilePic = NotifyList.get(j).member.image;

                                    alertsCommon.id = NotifyList.get(j).member.id;
                                    alertsCommon.passcode = NotifyList.get(j).member.passcode;
//                                    alertsCommon.name = NotifyList.get(j).member.name;
//                                    alertsCommon.phone = NotifyList.get(j).member.mobile;

                                    alertsCommon.name = NotifyList.get(j).personName;
                                    alertsCommon.phone = NotifyList.get(j).personMobile;


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
                                    alertsCommon.Description = kidsList.get(j).kidGoingWithWhom +
                                            ", Mobile: " + kidsList.get(j).member.mobile;
//                                    alertsCommon.Description = "Name: " + kidsList.get(j).kidName +
//                                            ", Mobile: " + kidsList.get(j).member.mobile;
                                    alertsCommon.type_alert = "Kids";
                                    alertsCommon.inTime = kidsList.get(j).inTime;
                                    alertsCommon.outTime = kidsList.get(j).outTime;
                                    alertsCommon.profilePic = kidsList.get(j).member.image;

                                    alertsCommon.id = kidsList.get(j).member.id;
                                    alertsCommon.passcode = kidsList.get(j).member.passcode;
                                    alertsCommon.name = kidsList.get(j).kidName;
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
                                Collections.reverse(commonAlertsList);
                                alertsAdapter = new AlertsAdapter(getActivity(), commonAlertsList);
                                rv_alerts.setAdapter(alertsAdapter);
                                alertsAdapter.setOnItemClickListener(new AlertsAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
//                                        call_Nottify_AlertWS(position);
                                        if (commonAlertsList.get(position).type_alert.equalsIgnoreCase("kids")) {

                                            showAlertDialog_kid(position, object);
                                        } else if (commonAlertsList.get(position).type_alert.equalsIgnoreCase("notify")) {
                                            showAlertDialog_Notify(position, object);
                                        } else {
                                            showAlertDialog_Digital(position);
                                        }

                                    }
                                });
                            } else {
                                Common.showToast(getActivity(), "No data found");
                            }
                            break;
                        case 2:
                            Common.showToast(getActivity(), object.optString("message"));
                            break;
                        case 3:
                            Common.showToast(getActivity(), object.optString("message"));
                            break;
                        case 4:

//                        {"status":true,"message":"Data fetched Successfully!","visitors_count":8}


//                            sharedPrefsUtils.visitor_count(object.optInt("visitors_count_inside"), object.optInt("visitors_count"));
//
//                            getActivity().getSupportFragmentManager().beginTransaction().
//                                    replace(R.id.fm_container,new Visitors_Fragment(),"").commit();


                            MainActivity activity_alerts = (MainActivity) getActivity();
                            TextView badgeAlerts = activity_alerts.findViewById(R.id.badge_notification_alerts);
                            badgeAlerts.setText("" + object.optInt("alerts_count"));

                      
                    }
                    req.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
                    try {
                        obj = Class.forName(AdminStaff.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 4, "alerts_count", false);

                } else {
                    Common.showToast(getActivity(), object.optString("message"));
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
        ImageView HelperPic,img_guardian_pic;
        tv_kid_Name = view1.findViewById(R.id.tv_alert_kid_name);
        tv_purpose = view1.findViewById(R.id.tv_helper_passcode);
        tv_intime = view1.findViewById(R.id.tv_kids_intime);
        tv_outtime = view1.findViewById(R.id.tv_kids_alert_outtime);
        tv_gingWith = view1.findViewById(R.id.tv_goingWith);
        img_guardian_pic=view1.findViewById(R.id.img_guardian_pic);


        tv_kid_Name.setText(commonAlertsList.get(position).name);
        tv_purpose.setText(commonAlertsList.get(position).service);
        tv_intime.setText(commonAlertsList.get(position).inTime);
        tv_outtime.setText(commonAlertsList.get(position).outTime);
        tv_gingWith.setText(commonAlertsList.get(position).Description);
        Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL +commonAlertsList.get(position).profilePic).into(img_guardian_pic);


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
        req.notify_gate_id = commonAlertsList.get(position).id;

        try {
            obj = Class.forName(NotifyAlertReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "security_accept_notify_gate_alert", true);

    }

    private void showAlertDialog_Notify(final int position, JSONObject object) {
        {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view1 = inflater.inflate(R.layout.alert_staff_alert, null);

            final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setView(view1);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(true);

            TextView tv_Name, tv_purpose, tv_description, btn_allow;
            ImageView HelperPic;

            tv_Name = view1.findViewById(R.id.tv_alert_staff_alert_name);
            tv_purpose = view1.findViewById(R.id.tv_role);
            tv_description = view1.findViewById(R.id.tv_no_response_content);
            HelperPic = view1.findViewById(R.id.img_alertPic);
            btn_allow = view1.findViewById(R.id.bt_allow);
            tv_Name.setText(commonAlertsList.get(position).name);
            tv_purpose.setText(commonAlertsList.get(position).service);
            tv_description.setText(commonAlertsList.get(position).Description);

            Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + commonAlertsList.get(position).profilePic)
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


    private void showAlertDialog_Digital(final int position) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view1 = inflater.inflate(R.layout.alert_staff_alert, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);

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
        tv_Name.setText(commonAlertsList.get(position).name);
        tv_purpose.setText(commonAlertsList.get(position).service);
        tv_description.setText(commonAlertsList.get(position).Description);

        Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + commonAlertsList.get(position).profilePic)
                .error(R.drawable.dummy).into(HelperPic);


        GatepassImagesAdapter gatepassImagesAdapter = new GatepassImagesAdapter(getActivity(), commonAlertsList.get(position).imagesLists);
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
}

