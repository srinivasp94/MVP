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

import com.iprismech.alertnikkiresidence.adapters.MaidWeeklyAttAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractFragment;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.MaidAttendanceHistoryPojo;

import com.iprismech.alertnikkiresidence.request.MaidAttendanceHistoryReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;


import org.json.JSONObject;

public class MaidWeeklyAttendance extends BaseAbstractFragment<Class> implements View.OnClickListener, RetrofitResponseListener {

    private MaidWeeklyAttAdapter maidWeeklyAttAdapter;
    private RecyclerView rview_weekly;
    private LinearLayoutManager manager;
    MaidAttendanceHistoryPojo maidAttendanceHistoryPojo;
    private Object obj;
    private String maid_id;

    //   private VisitorsHistoryPojo visitorsHistoryPojo;
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        Bundle arguments = getArguments();
        maid_id = arguments.getString("maid_id");
        rview_weekly = view.findViewById(R.id.rview_weekly);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_weekly.setLayoutManager(manager);


        MaidAttendanceHistoryReq req = new MaidAttendanceHistoryReq();
        req.adminId = "2";
        // req.userId=SharedPrefsUtils.getInstance(getActivity()).getId();
        req.maidId = maid_id;
        // req.maidId = "1";


        //  req.userId = 22;
        try {
            obj = Class.forName(MaidAttendanceHistoryReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "maid_attendence_history", true);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_maid_weekly, null);
        return view;
    }

    @Override
    public void setPresenter() {

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
                            maidAttendanceHistoryPojo = gson.fromJson(jsonString, MaidAttendanceHistoryPojo.class);
                            maidWeeklyAttAdapter = new MaidWeeklyAttAdapter(getActivity(), maidAttendanceHistoryPojo);
                            rview_weekly.setAdapter(maidWeeklyAttAdapter);
                            maidWeeklyAttAdapter.notifyDataSetChanged();
                            maidWeeklyAttAdapter.setOnItemClickListener(new MaidWeeklyAttAdapter.OnitemClickListener() {
                                @SuppressLint("WrongConstant")
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.tv_view_all:
                                            Bundle bundle = new Bundle();
                                            bundle.putString("case", "Weekly");
                                            bundle.putInt("position", position);
                                            bundle.putString("maid_id", maid_id);
                                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIEW_ALL_MAID_ATTENDANCE_HISTORY_SCREEN, bundle);
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
}
