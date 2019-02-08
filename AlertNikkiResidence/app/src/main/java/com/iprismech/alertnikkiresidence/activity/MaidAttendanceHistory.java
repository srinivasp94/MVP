package com.iprismech.alertnikkiresidence.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.fragments.HomeFragment;
import com.iprismech.alertnikkiresidence.fragments.MaidMonthlyAttendance;
import com.iprismech.alertnikkiresidence.fragments.MaidWeeklyAttendance;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;

public class MaidAttendanceHistory extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private FragmentManager fragmentManager;
    private TextView tv_weekly, tv_monthly;
    private MaidWeeklyAttendance fragment;
    private MaidMonthlyAttendance fragment1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_weekly:

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tv_weekly.setBackgroundColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.black));
                    tv_weekly.setTextColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.white));
                } else {
                    tv_weekly.setBackground(ContextCompat.getDrawable(MaidAttendanceHistory.this, R.color.black));
                    tv_weekly.setTextColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.white));
                }

                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tv_monthly.setBackgroundColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.white));
                    tv_monthly.setTextColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.black));
                } else {
                    tv_monthly.setBackground(ContextCompat.getDrawable(MaidAttendanceHistory.this, R.color.white));
                    tv_monthly.setTextColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.black));
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
                    tv_monthly.setBackgroundColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.black));
                    tv_monthly.setTextColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.white));
                } else {
                    tv_monthly.setBackground(ContextCompat.getDrawable(MaidAttendanceHistory.this, R.color.black));
                    tv_monthly.setTextColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.white));
                }

                if (sdk1 < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tv_weekly.setBackgroundColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.white));
                    tv_weekly.setTextColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.black));
                } else {
                    tv_weekly.setBackground(ContextCompat.getDrawable(MaidAttendanceHistory.this, R.color.white));
                    tv_weekly.setTextColor(ContextCompat.getColor(MaidAttendanceHistory.this, R.color.black));
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
    protected void initializeViews() {
        super.initializeViews();

        String maid_id = getIntent().getExtras().getString("maid_id", "");

        fragment = new MaidWeeklyAttendance();
        fragment1 = new MaidMonthlyAttendance();
        Bundle arguments = new Bundle();
        arguments.putString("maid_id", maid_id);
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

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }
}
