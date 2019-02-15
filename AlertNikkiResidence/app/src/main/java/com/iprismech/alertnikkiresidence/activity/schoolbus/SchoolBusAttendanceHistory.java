package com.iprismech.alertnikkiresidence.activity.schoolbus;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.fragments.BusMonthlyAttendance;
import com.iprismech.alertnikkiresidence.fragments.BusWeeklyAttendance;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;

public class SchoolBusAttendanceHistory extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private TextView tv_weekly, tv_monthly;
    private BusWeeklyAttendance fragment;
    private BusMonthlyAttendance fragment1;
    private ImageView imgClose;
    private TextView txtitle;
    private String school_bus_id;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgClose:
                onBackPressed();
                finish();
                break;
            case R.id.tv_weekly:

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tv_weekly.setBackgroundColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.black));
                    tv_weekly.setTextColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.white));
                } else {
                    tv_weekly.setBackground(ContextCompat.getDrawable(SchoolBusAttendanceHistory.this, R.color.black));
                    tv_weekly.setTextColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.white));
                }

                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tv_monthly.setBackgroundColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.white));
                    tv_monthly.setTextColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.black));
                } else {
                    tv_monthly.setBackground(ContextCompat.getDrawable(SchoolBusAttendanceHistory.this, R.color.white));
                    tv_monthly.setTextColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.black));
                }
//                tv_weekly.setBackgroundColor(Color.parseColor("#fff"));
//                tv_monthly.setBackgroundColor(Color.parseColor("#000"));
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_maid_att, fragment, "").commit();
                break;
            case R.id.tv_monthly:
//                tv_weekly.setBackgroundColor(Color.parseColor("#000"));
//                tv_monthly.setBackgroundColor(Color.parseColor("#fff"));

                final int sdk1 = android.os.Build.VERSION.SDK_INT;
                if (sdk1 < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tv_monthly.setBackgroundColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.black));
                    tv_monthly.setTextColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.white));
                } else {
                    tv_monthly.setBackground(ContextCompat.getDrawable(SchoolBusAttendanceHistory.this, R.color.black));
                    tv_monthly.setTextColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.white));
                }

                if (sdk1 < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tv_weekly.setBackgroundColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.white));
                    tv_weekly.setTextColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.black));
                } else {
                    tv_weekly.setBackground(ContextCompat.getDrawable(SchoolBusAttendanceHistory.this, R.color.white));
                    tv_weekly.setTextColor(ContextCompat.getColor(SchoolBusAttendanceHistory.this, R.color.black));
                }

                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_maid_att, fragment1, "").commit();
                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_maid_attendance_history, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }
    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_weekly.setOnClickListener(this);
        tv_monthly.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected void initializeViews() {
        super.initializeViews();

        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Bus Attendence History");
        imgClose.setOnClickListener(this);

         school_bus_id = getIntent().getExtras().getString("school_bus_id", "");

        fragment = new BusWeeklyAttendance();
        fragment1 = new BusMonthlyAttendance();
        Bundle arguments = new Bundle();
        arguments.putString("school_bus_id", school_bus_id);
        fragment.setArguments(arguments);
        fragment1.setArguments(arguments);


        tv_weekly = findViewById(R.id.tv_weekly);
        tv_monthly = findViewById(R.id.tv_monthly);

        tv_weekly.setOnClickListener(this);
        tv_monthly.setOnClickListener(this);
        try {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.frame_maid_att, fragment, "").commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
