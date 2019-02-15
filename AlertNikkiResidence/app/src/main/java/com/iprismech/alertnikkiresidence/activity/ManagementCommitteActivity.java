package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.EmergencyContactAdapter;
import com.iprismech.alertnikkiresidence.adapters.ManagementCommitteAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.EmergencyConatctPojo;
import com.iprismech.alertnikkiresidence.pojo.ManagementCommittePojo;
import com.iprismech.alertnikkiresidence.request.NoticeBoardRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class ManagementCommitteActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private ManagementCommitteAdapter managementCommitteAdapter;
    private RecyclerView rview_managament_committe;
    private LinearLayoutManager manager;
    private Object obj;
    private ManagementCommittePojo managementCommittePojo;

    private ImageView imgClose;
    private TextView txtitle;



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Management Committee");
        imgClose.setOnClickListener(this);

        rview_managament_committe = findViewById(R.id.rview_management_committe);
        manager = new LinearLayoutManager(ManagementCommitteActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_managament_committe.setLayoutManager(manager);


        NoticeBoardRequest req = new NoticeBoardRequest();
        req.adminId = SharedPrefsUtils.getInstance(ManagementCommitteActivity.this).getAdminID();

        //  req.userId = 22;
        try {
            obj = Class.forName(NoticeBoardRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "managment_committiee", true);
    }


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_management_committe, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ManagementCommitteActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            managementCommittePojo = gson.fromJson(jsonString, ManagementCommittePojo.class);
                            managementCommitteAdapter = new ManagementCommitteAdapter(ManagementCommitteActivity.this, managementCommittePojo);
                            rview_managament_committe.setAdapter(managementCommitteAdapter);
                            break;

                    }
                } else {
                    Common.showToast(ManagementCommitteActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
