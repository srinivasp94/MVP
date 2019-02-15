package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;

public class LocalServiceActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    LinearLayout ll_local_service;
    ImageView iv_back;
    private ImageView imgClose;
    private TextView txtitle;
    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_service:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CHOOSE_LOCAL_SERVICE_SCREEN);
                break;
            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_local_service.setOnClickListener(this);
        imgClose.setOnClickListener(this);
       // iv_back.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ll_local_service = findViewById(R.id.ll_service);
        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Local Services");
        imgClose.setOnClickListener(this);
       // iv_back = findViewById(R.id.iv_back);

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_local_service, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }
}
