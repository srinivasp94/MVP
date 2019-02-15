package com.iprismech.alertnikkiresidence.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.BusMonthlyAttAdapter;
import com.iprismech.alertnikkiresidence.adapters.BusWeeklyAttAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractFragment;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.SchoolBusHistoryPojo;
import com.iprismech.alertnikkiresidence.request.BusAttendanceHistoryRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

public class BusMonthlyAttendance extends BaseAbstractFragment<Class> implements View.OnClickListener, RetrofitResponseListener {

    private BusMonthlyAttAdapter busMonthlyAttAdapter;
    private RecyclerView rview_monthly;
    private LinearLayoutManager manager;
    private SchoolBusHistoryPojo schoolBusHistoryPojo;
    private Object obj;
    private String school_bus_id;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_maid_monthly, null);
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
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(getActivity(), "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            schoolBusHistoryPojo = gson.fromJson(jsonString, SchoolBusHistoryPojo.class);
                            busMonthlyAttAdapter = new BusMonthlyAttAdapter(getActivity(), schoolBusHistoryPojo);
                            rview_monthly.setAdapter(busMonthlyAttAdapter);
                            busMonthlyAttAdapter.notifyDataSetChanged();
                            busMonthlyAttAdapter.setOnItemClickListener(new BusMonthlyAttAdapter.OnitemClickListener() {
                                @SuppressLint("WrongConstant")
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.tv_view_all:
                                            Bundle bundle = new Bundle();
                                            bundle.putString("case", "Monthly");
                                            bundle.putInt("position", position);
                                            bundle.putString("school_bus_id", school_bus_id);
                                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIEW_ALL_BUS_ATTENDANCE_HISTORY_SCREEN, bundle);
                                            break;
                                    }
                                }
                            });
                            break;
                    }
                } else {
                    Common.showToast(getActivity(), jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();


        Bundle arguments = getArguments();
        school_bus_id = arguments.getString("school_bus_id");
        rview_monthly = view.findViewById(R.id.rview_monthly);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_monthly.setLayoutManager(manager);


        BusAttendanceHistoryRequest req = new BusAttendanceHistoryRequest();
        req.adminId = "2";
        // req.userId=SharedPrefsUtils.getInstance(getActivity()).getId();
        req.schoolbus_id = school_bus_id;
        // req.schoolbus_id = "1";
        // req.maidId = "1";


        //  req.userId = 22;
        try {
            obj = Class.forName(BusAttendanceHistoryRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "schoolbus_attendence_history", true);

    }
}
