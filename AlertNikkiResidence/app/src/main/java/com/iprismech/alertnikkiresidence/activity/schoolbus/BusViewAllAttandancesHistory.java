package com.iprismech.alertnikkiresidence.activity.schoolbus;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.MaidViewAllAttandancesHistory;
import com.iprismech.alertnikkiresidence.adapters.ViewAllBusHistoryAdapter;
import com.iprismech.alertnikkiresidence.adapters.ViewAllMaidHistoryAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.MaidAttendanceHistoryPojo;
import com.iprismech.alertnikkiresidence.pojo.SchoolBusHistoryPojo;
import com.iprismech.alertnikkiresidence.request.BusAttendanceHistoryRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class BusViewAllAttandancesHistory extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private ViewAllBusHistoryAdapter viewAllBusHistoryAdapter;
    private RecyclerView rview_viewall;
    private LinearLayoutManager manager;
    private SchoolBusHistoryPojo schoolBusHistoryPojo;
    private Object obj;
    private int postion;
    private String from_case;

    private ImageView imgClose;
    private TextView txtitle;
    private String school_bus_id;

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_maid_view_all_history, null);
        return view;
    }
    @Override
    protected void initializeViews() {
        super.initializeViews();
        from_case = getIntent().getExtras().getString("case");
        postion = getIntent().getExtras().getInt("position");
        school_bus_id = getIntent().getExtras().getString("school_bus_id");


        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Bus History");
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        rview_viewall = findViewById(R.id.rview_viewall);
        manager = new LinearLayoutManager(BusViewAllAttandancesHistory.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_viewall.setLayoutManager(manager);

        BusAttendanceHistoryRequest req = new BusAttendanceHistoryRequest();

        req.adminId = "2";
       //  req.userId=SharedPrefsUtils.getInstance(getActivity()).getId();
        req.schoolbus_id = school_bus_id;


        //  req.userId = 22;
        try {
            obj = Class.forName(BusAttendanceHistoryRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "schoolbus_attendence_history", true);
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(BusViewAllAttandancesHistory.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            schoolBusHistoryPojo = gson.fromJson(jsonString, SchoolBusHistoryPojo.class);
                            viewAllBusHistoryAdapter = new ViewAllBusHistoryAdapter(BusViewAllAttandancesHistory.this, schoolBusHistoryPojo, postion,from_case);
                            rview_viewall.setAdapter(viewAllBusHistoryAdapter);
                            viewAllBusHistoryAdapter.notifyDataSetChanged();

                            //  else if(from_case.equalsIgnoreCase("Weekly"))


                            break;
                    }
                } else {
                    Common.showToast(BusViewAllAttandancesHistory.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
