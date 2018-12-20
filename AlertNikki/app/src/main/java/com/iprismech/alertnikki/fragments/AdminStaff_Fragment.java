package com.iprismech.alertnikki.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AdminStaff;
import com.iprismech.alertnikki.Response.Admin_Staff;
import com.iprismech.alertnikki.Response.StaffResponse;
import com.iprismech.alertnikki.adapters.AdminStaffAdapter;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class AdminStaff_Fragment extends BaseAbstractFragment<Class> implements RetrofitResponseListener {
    private SearchView msearchView;
    private RecyclerView rv_admin;
    private AdminStaffAdapter staffAdapter;
    private LinearLayoutManager manager;
    private ArrayList<StaffResponse> arrayList = new ArrayList<>();
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

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        msearchView = view.findViewById(R.id.searchview_admin);
        rv_admin = view.findViewById(R.id.rv_adminstaff);
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
                                staffAdapter = new AdminStaffAdapter(getActivity(), arrayList);
                                rv_admin.setAdapter(staffAdapter);
                            } else {
                                Common.showToast(getActivity(), "Items Not Found");
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
