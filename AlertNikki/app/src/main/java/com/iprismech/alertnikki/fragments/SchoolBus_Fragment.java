package com.iprismech.alertnikki.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AdminStaff;
import com.iprismech.alertnikki.Request.SchoolBusNotify;
import com.iprismech.alertnikki.Request.SchoolBus_Out;
import com.iprismech.alertnikki.Response.SchoolBus;
import com.iprismech.alertnikki.Response.SchoolBusesList;
import com.iprismech.alertnikki.adapters.AdminStaffAdapter;
import com.iprismech.alertnikki.adapters.SchoolAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.AlertUtils;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

public class SchoolBus_Fragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {

    private EditText msearchView;
    private RecyclerView rv_admin;
    private SchoolAdapter busAdapter;
    private LinearLayoutManager manager;
    private ArrayList<SchoolBusesList> arrayList = new ArrayList<>();
    private Object obj;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragments_admin_staff, null);
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

        msearchView = view.findViewById(R.id.searchview_admin);
        rv_admin = view.findViewById(R.id.rv_adminstaff);

        msearchView = view.findViewById(R.id.search_admin_staff);

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_admin.setLayoutManager(manager);

        AdminStaff staff = new AdminStaff();
        staff.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        staff.search = "";
        try {
            obj = Class.forName(AdminStaff.class.getName()).cast(staff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "school_buses", true);


        msearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if (s.length()>=2)
                busAdapter.getFilter().filter(s);
                busAdapter.notifyDataSetChanged();
                // return false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            SchoolBus bus = Common.getSpecificDataObject(objectResponse, SchoolBus.class);
                            arrayList = (ArrayList<SchoolBusesList>) bus.response;
                            if (arrayList != null && arrayList.size() > 0) {
                                busAdapter = new SchoolAdapter(getActivity(), arrayList);
                                rv_admin.setLayoutManager(manager);
                                rv_admin.setAdapter(busAdapter);
                                busAdapter.setOnItemClickListener(new SchoolAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, final int position, final ArrayList<SchoolBusesList> busesLists) {
                                        switch (view.getId()) {
                                            case R.id.btn_out:
                                                AlertUtils.showSimpleAlert(getActivity(), "Do you want to send bus out", "Confirm...?", "Yes", "No", new AlertUtils.onClicklistners() {
                                                    @Override
                                                    public void onpositiveclick() {
                                                        call_bus_out_WS(position,busesLists);
                                                    }

                                                    @Override
                                                    public void onNegativeClick() {

                                                    }
                                                });


                                                break;
                                            case R.id.img_notify:
                                                //school_bus_notify
                                                showAlertFornotift(position,busesLists);
//                                                call_Notify_all_WS(position);
                                                break;
                                        }
                                    }
                                });
                            } else {
                                Common.showToast(getActivity(), "No Data Found");
                            }
                            break;
                        case 2:
                            Common.showToast(getActivity(), object.optString("message"));
                            recall_Schooladaptere();
                            break;
                        case 3:
//                            showAlertFornotift(object);
                            Common.showToast(getActivity(), object.optString("message"));
                            recall_Schooladaptere();
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

    private void recall_Schooladaptere() {
        AdminStaff staff = new AdminStaff();
        staff.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        staff.search = "";

        try {
            obj = Class.forName(AdminStaff.class.getName()).cast(staff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "school_buses", true);

    }

    private void showAlertFornotift(final int i, final ArrayList<SchoolBusesList> busesLists) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_school_bus, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);

        TextView tv_schoolName, tv_address, tv_busno;
        TextView btn_allow, bt_deny;

        ImageView staffPic;


        tv_schoolName = view1.findViewById(R.id.tv_school_name);
        tv_address = view1.findViewById(R.id.tv_school_location);
        tv_busno = view1.findViewById(R.id.tv_school_bus_number);

        bt_deny = view1.findViewById(R.id.tv_cance_bus);
        btn_allow = view1.findViewById(R.id.tv_send_notify_bus);

        staffPic = view1.findViewById(R.id.img_helper_pic);
        try {

            tv_schoolName.setText(busesLists.get(i).schoolBusName);
            tv_address.setText(busesLists.get(i).address);
            tv_busno.setText(busesLists.get(i).vehicleNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + responseObject.optString("image"))
                .error(R.drawable.dummy).into(staffPic);*/

        bt_deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                call_Notify_all_WS(i,busesLists);
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void call_Notify_all_WS(int position, ArrayList<SchoolBusesList> busesLists) {
        SchoolBusNotify req = new SchoolBusNotify();
        req.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        req.schoolBusId = busesLists.get(position).id;
        req.route_id=busesLists.get(position).route_id;

        try {
            obj = Class.forName(SchoolBusNotify.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 3, "school_bus_notify", true);


    }

    private void call_bus_out_WS(int position,ArrayList<SchoolBusesList> busesLists) {




        SchoolBus_Out req = new SchoolBus_Out();
        req.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        req.attendenceId = busesLists.get(position).attendence.id;
        req.schoolBusId = busesLists.get(position).id;
        req.route_id=busesLists.get(position).route_id;

        try {
            obj = Class.forName(SchoolBus_Out.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "out_school_bus", true);

    }
}
