package com.iprismech.alertnikkiresidence.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;

public class MaidStaffAttendanceHistory extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

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
    protected void setListenerToViews() {

        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {

        super.initializeViews();
        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Maids");
        imgClose.setOnClickListener(this);
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
