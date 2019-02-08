package com.iprismech.alertnikkiresidence.activity;

import android.view.View;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;

public class MaidStaffAttendanceHistory extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
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
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_my_staff_profile, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }
}
