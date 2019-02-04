package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.LocalServicesListAdapetr;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.LocalServicesListPojo;
import com.iprismech.alertnikkiresidence.request.LocalServicesListRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

public class ChooseLocalServiceActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private RecyclerView rview_choose_service;
    private LocalServicesListAdapetr localServicesListAdapetr;
    private LinearLayoutManager layoutManager;
    private Object obj;
    private LocalServicesListPojo localServicesListPojo;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        rview_choose_service = findViewById(R.id.rview_choose_local_service);
        layoutManager = new LinearLayoutManager(ChooseLocalServiceActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        LocalServicesListRequest req = new LocalServicesListRequest();
        req.adminId = "2";
        //  req.userId = 22;
        try {
            obj = Class.forName(LocalServicesListRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_local_services", true);
    }
    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_choose_local_service, null);
        return view;
    }
    @Override
    public void setPresenter() {

    }
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ChooseLocalServiceActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            localServicesListPojo = gson.fromJson(jsonString, LocalServicesListPojo.class);
                            rview_choose_service.setLayoutManager(layoutManager);
                            localServicesListAdapetr = new LocalServicesListAdapetr(ChooseLocalServiceActivity.this, localServicesListPojo);
                            rview_choose_service.setAdapter(localServicesListAdapetr);
                            localServicesListAdapetr.notifyDataSetChanged();
                            break;
                    }
                } else {
                    Common.showToast(ChooseLocalServiceActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}