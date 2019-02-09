package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.EmergencyContactAdapter;
import com.iprismech.alertnikkiresidence.adapters.NoticeBoardAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.EmergencyConatctPojo;
import com.iprismech.alertnikkiresidence.pojo.NoticeBoardPojo;
import com.iprismech.alertnikkiresidence.request.NoticeBoardRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

public class EmergencyContactActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private EmergencyContactAdapter emergencyContactAdapter;
    private RecyclerView rview_emergency_conatct;
    private LinearLayoutManager manager;
    private Object obj;
    private EmergencyConatctPojo emergencyConatctPojo;

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
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_emergency_contact, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Emergenct Contact");
        imgClose.setOnClickListener(this);
        rview_emergency_conatct = findViewById(R.id.rview_emergency_contact);
        manager = new LinearLayoutManager(EmergencyContactActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_emergency_conatct.setLayoutManager(manager);


        NoticeBoardRequest req = new NoticeBoardRequest();
        req.adminId = "2";

        //  req.userId = 22;
        try {
            obj = Class.forName(NoticeBoardRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "emergency_contacts", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(EmergencyContactActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            emergencyConatctPojo = gson.fromJson(jsonString, EmergencyConatctPojo.class);
                            emergencyContactAdapter = new EmergencyContactAdapter(EmergencyContactActivity.this, emergencyConatctPojo);
                            rview_emergency_conatct.setAdapter(emergencyContactAdapter);
                            break;

                    }
                } else {
                    Common.showToast(EmergencyContactActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
