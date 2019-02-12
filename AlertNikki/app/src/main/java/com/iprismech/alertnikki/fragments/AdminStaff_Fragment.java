package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AdminStaff;
import com.iprismech.alertnikki.Request.OutAdminStaff;
import com.iprismech.alertnikki.Response.Admin_Staff;
import com.iprismech.alertnikki.Response.ResponseVisitMember;
import com.iprismech.alertnikki.Response.StaffResponse;
import com.iprismech.alertnikki.adapters.AdminStaffAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.AlertUtils;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class AdminStaff_Fragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {

    private LinearLayout ll_noResponse, ll_search;
    private RecyclerView rv_admin;
    private AdminStaffAdapter staffAdapter;
    private LinearLayoutManager manager;
    private ArrayList<StaffResponse> arrayList = new ArrayList<>();
    private ArrayList<StaffResponse> arrayListnew = new ArrayList<>();
    private Object obj;
    RetrofitResponseListener responseListener;
    private EditText msearchView;

    private int itemPosition;

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

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        responseListener = this;

        msearchView = view.findViewById(R.id.search_admin_staff);
        rv_admin = view.findViewById(R.id.rv_adminstaff);
        ll_search = view.findViewById(R.id.ll_search);
        ll_noResponse = view.findViewById(R.id.ll_noResponse);
        rv_admin.setLayoutManager(manager);

        AdminStaff staff = new AdminStaff();
        staff.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
        staff.search = "";
        try {
            obj = Class.forName(AdminStaff.class.getName()).cast(staff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "admin_staff", true);

        msearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if (s.length()>=2)
                staffAdapter.getFilter().filter(s);
                staffAdapter.notifyDataSetChanged();
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
                            Admin_Staff admin_staff = Common.getSpecificDataObject(objectResponse, Admin_Staff.class);
                            arrayList = (ArrayList) admin_staff.response;
                            if (arrayList != null && arrayList.size() > 0) {
                                for (int i = 0; i < arrayList.size(); i++) {
                                    if (arrayList.get(i).attendence.id == null || arrayList.get(i).attendence.id.equalsIgnoreCase("")) {
                                       /* ll_search.setVisibility(View.GONE);
                                        ll_noResponse.setVisibility(View.VISIBLE);*/

                                    } else {
                                        /*ll_search.setVisibility(View.VISIBLE);
                                        ll_noResponse.setVisibility(View.GONE);
*/                                        arrayListnew.add(arrayList.get(i));
                                    }
                                }
                                if (arrayListnew != null && arrayListnew.size() > 0) {
                                    staffAdapter = new AdminStaffAdapter(getActivity(), arrayListnew);
                                    rv_admin.setAdapter(staffAdapter);
                                    staffAdapter.notifyDataSetChanged();

                                    staffAdapter.setOnItemClickListener(new AdminStaffAdapter.OnitemClickListener() {
                                        @Override
                                        public void onItemClick(View view, final int position, final ArrayList<StaffResponse> arrayList) {
//                                        out_admin_staff

                                            AlertUtils.showSimpleAlert(getActivity(), "Do you want to send him/her out", "Confirm...?", "Yes", "No", new AlertUtils.onClicklistners() {
                                                @Override
                                                public void onpositiveclick() {
                                                    call_staff_outWS(position, arrayList);
                                                }

                                                @Override
                                                public void onNegativeClick() {

                                                }
                                            });


                                        }
                                    });
                                } else {
                                    ll_noResponse.setVisibility(View.VISIBLE);
                                    ll_search.setVisibility(View.GONE);
                                }
                            } else {
                                Common.showToast(getActivity(), "Items Not Found");
                            }
                            break;
                        case 2:
                            Common.showToast(getActivity(), object.optString("message"));
                            JSONObject responseStaff = object.optJSONObject("response");
                            arrayList.remove(itemPosition);
                            staffAdapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Removed Successfully", Toast.LENGTH_SHORT).show();
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

    private void call_staff_outWS(int position, ArrayList<StaffResponse> arrayList1) {


        itemPosition = position;
        OutAdminStaff req = new OutAdminStaff();
        req.attendenceId = arrayList1.get(position).attendence.id;

        try {
            obj = Class.forName(OutAdminStaff.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(responseListener).callPostServices(obj, 2, "out_admin_staff", true);
    }
}
