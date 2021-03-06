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
import com.iprismech.alertnikkiresidence.adapters.AllowVisitrosAdapter;
import com.iprismech.alertnikkiresidence.adapters.DenyVisitrosAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractFragment;
import com.iprismech.alertnikkiresidence.pojo.VisitorsHistoryPojo;
import com.iprismech.alertnikkiresidence.request.VisitorsHistoryRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class DenyFragment extends BaseAbstractFragment<Class> implements View.OnClickListener, RetrofitResponseListener {
    private DenyVisitrosAdapter denyVisitrosAdapter;
    private RecyclerView rview_visitors_deny;
    private LinearLayoutManager manager;
    private Object obj;
    private VisitorsHistoryPojo visitorsHistoryPojo;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_visitors_deny, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();


        rview_visitors_deny = view.findViewById(R.id.rview_visitors_deny);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_visitors_deny.setLayoutManager(manager);


        VisitorsHistoryRequest req = new VisitorsHistoryRequest();
        //req.adminId = "2";
        req.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdminID();
        // req.userId=SharedPrefsUtils.getInstance(getActivity()).getId();
        req.userId = SharedPrefsUtils.getInstance(getActivity()).getId();
        req.userType = SharedPrefsUtils.getInstance(getActivity()).getuserType();

        //  req.userId = 22;
        try {
            obj = Class.forName(VisitorsHistoryRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "visitors_history", true);


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
                            visitorsHistoryPojo = gson.fromJson(jsonString, VisitorsHistoryPojo.class);
                            denyVisitrosAdapter = new DenyVisitrosAdapter(getActivity(), visitorsHistoryPojo);
                            rview_visitors_deny.setAdapter(denyVisitrosAdapter);
                            denyVisitrosAdapter.notifyDataSetChanged();
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
