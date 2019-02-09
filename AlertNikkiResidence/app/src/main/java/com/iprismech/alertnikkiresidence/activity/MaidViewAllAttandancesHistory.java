package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.ViewAllMaidHistoryAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.MaidAttendanceHistoryPojo;
import com.iprismech.alertnikkiresidence.request.MaidAttendanceHistoryReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

public class MaidViewAllAttandancesHistory extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private ViewAllMaidHistoryAdapter viewAllMaidHistoryAdapter;
    private RecyclerView rview_viewall;
    private LinearLayoutManager manager;
    MaidAttendanceHistoryPojo maidAttendanceHistoryPojo;
    private Object obj;
    private int postion;
    private String from_case;

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
        View view = getLayoutInflater().inflate(R.layout.activity_maid_view_all_history, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(MaidViewAllAttandancesHistory.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            maidAttendanceHistoryPojo = gson.fromJson(jsonString, MaidAttendanceHistoryPojo.class);
                                viewAllMaidHistoryAdapter = new ViewAllMaidHistoryAdapter(MaidViewAllAttandancesHistory.this, maidAttendanceHistoryPojo, postion,from_case);
                                rview_viewall.setAdapter(viewAllMaidHistoryAdapter);
                                viewAllMaidHistoryAdapter.notifyDataSetChanged();

                          //  else if(from_case.equalsIgnoreCase("Weekly"))


                            break;
                    }
                } else {
                    Common.showToast(MaidViewAllAttandancesHistory.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        from_case = getIntent().getExtras().getString("case");
        postion = getIntent().getExtras().getInt("position");

        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Maids");
        imgClose.setOnClickListener(this);

        rview_viewall = findViewById(R.id.rview_viewall);
        manager = new LinearLayoutManager(MaidViewAllAttandancesHistory.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_viewall.setLayoutManager(manager);

        MaidAttendanceHistoryReq req = new MaidAttendanceHistoryReq();

        req.adminId = "2";
        // req.userId=SharedPrefsUtils.getInstance(getActivity()).getId();
        req.maidId = "1";


        //  req.userId = 22;
        try {
            obj = Class.forName(MaidAttendanceHistoryReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "maid_attendence_history", true);

    }
}
