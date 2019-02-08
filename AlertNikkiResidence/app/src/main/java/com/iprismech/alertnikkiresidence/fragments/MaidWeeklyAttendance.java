package com.iprismech.alertnikkiresidence.fragments;

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

    //   private VisitorsHistoryPojo visitorsHistoryPojo;
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();


        rview_weekly = view.findViewById(R.id.rview_weekly);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_weekly.setLayoutManager(manager);


        MaidAttendanceHistoryReq req = new MaidAttendanceHistoryReq();
        req.adminId = "2";
        // req.userId=SharedPrefsUtils.getInstance(getActivity()).getId();
        req.maidId = "1";


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
