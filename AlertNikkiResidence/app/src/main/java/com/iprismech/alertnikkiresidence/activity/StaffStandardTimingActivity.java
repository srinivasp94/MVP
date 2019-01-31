package com.iprismech.alertnikkiresidence.activity;

import android.view.View;

import com.iprismech.alertnikkiresidence.activity.MyStaffAlerts;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.request.StaffRequest;
import com.iprismech.alertnikkiresidence.request.StandardMaidTimingsRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

public class StaffStandardTimingActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private RetrofitResponseListener retrofitResponseListener;
    private Object obj;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected View getView() {
        return null;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        retrofitResponseListener = this;

          StandardMaidTimingsRequest req = new StandardMaidTimingsRequest();
        req.adminId = "2";
        //  req.userId = 22;
        try {
            obj = Class.forName(StandardMaidTimingsRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "standard_timings", true);

    }

    @Override
    public void setPresenter() {

    }
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }
}
