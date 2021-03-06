package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.ChooseMaidAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.ChooseMaidPojo;
import com.iprismech.alertnikkiresidence.request.ChooseMaidRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class ChooseMaidActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private RecyclerView rview_choosemaid;
    private ChooseMaidAdapter chooseMaidAdapter;
    private Object obj;
    private ChooseMaidPojo chooseMaidPojo;
    private LinearLayoutManager manager;
    private RetrofitResponseListener retrofitResponseListener;

    private ImageView imgClose;
    private TextView txtitle, tv_nodata_txt;


    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_STAFF_SCREEN);
        finish();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgClose:
                //  onBackPressed();
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_STAFF_SCREEN);
                finish();
                break;
        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        retrofitResponseListener = this;
        rview_choosemaid = findViewById(R.id.rview_choose_maid);
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        tv_nodata_txt = findViewById(R.id.tv_nodata_txt);

        imgClose.setOnClickListener(this);


        String service_id = getIntent().getExtras().getString("ServiceID");
        String service_name = getIntent().getExtras().getString("ServiceName");
        txtitle.setText("Choose " + service_name);
        ChooseMaidRequest chooseMaidRequest = new ChooseMaidRequest();
        // chooseMaidRequest.adminId = SharedPrefsUtils.getInstance(ChooseMaidActivity.this).getId();
        chooseMaidRequest.adminId = SharedPrefsUtils.getInstance(ChooseMaidActivity.this).getAdminID();
        ;
        chooseMaidRequest.service_id = service_id;
        // chooseMaidRequest.service_id = "2";

        try {
            obj = Class.forName(ChooseMaidRequest.class.getName()).cast(chooseMaidRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "maids_list", true);
    }


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_choose_maid, null);
        return view;
    }


    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ChooseMaidActivity.this, "Please Try Again");
        } else {
            try {

                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {

                    chooseMaidPojo = gson.fromJson(jsonString, ChooseMaidPojo.class);
                    if (chooseMaidPojo.getResponse().size() < 1) {
                        tv_nodata_txt.setVisibility(View.VISIBLE);
                    } else {
                        tv_nodata_txt.setVisibility(View.GONE);
                        manager = new LinearLayoutManager(ChooseMaidActivity.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        rview_choosemaid.setLayoutManager(manager);
                        chooseMaidAdapter = new ChooseMaidAdapter(ChooseMaidActivity.this, chooseMaidPojo);
                        rview_choosemaid.setAdapter(chooseMaidAdapter);
                        chooseMaidAdapter.notifyDataSetChanged();
                        // finish();
//                        chooseMaidAdapter.setOnItemClickListener(new ChooseMaidAdapter.OnitemClickListener() {
//                            @Override
//                            public void onItemClick(View view, int position) {
//                                switch (view.getId()){
//                                    case R.id.
//                                }
//                            }
//                        });
                    }
                } else {
                    tv_nodata_txt.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
            }
        }
    }
}
