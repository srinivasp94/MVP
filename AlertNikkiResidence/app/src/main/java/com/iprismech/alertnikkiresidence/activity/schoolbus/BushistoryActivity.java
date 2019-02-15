package com.iprismech.alertnikkiresidence.activity.schoolbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.SOSActivity;
import com.iprismech.alertnikkiresidence.adapters.HistorySchoolAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.SchoolHistoryReq;
import com.iprismech.alertnikkiresidence.response.SchoolHistory;
import com.iprismech.alertnikkiresidence.response.WeeklyHistory;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class BushistoryActivity extends BaseAbstractActivity implements RetrofitResponseListener {

    HistorySchoolAdapter historySchoolAdapter;
    RecyclerView rv_expandable;
    ArrayList<WeeklyHistory> list = new ArrayList<>();
    private Object obj;
    private ImageView imgClose;
    private TextView txtitle;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bushistory);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_bushistory, null);
        return view;
//        return getLayoutInflater().inflate(R.layout.activity_bushistory,null);
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Bus History");
        rv_expandable = findViewById(R.id.rv_expandable);

        LinearLayoutManager layoutManager = new LinearLayoutManager(BushistoryActivity.this);
        rv_expandable.setLayoutManager(layoutManager);


        //schoolbus_attendence_history
        SchoolHistoryReq req = new SchoolHistoryReq();
        req.adminId =SharedPrefsUtils.getInstance(BushistoryActivity.this).getAdminID();
        req.schoolbusId = "1";

        try {
            obj = Class.forName(SchoolHistoryReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "schoolbus_attendence_history", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(BushistoryActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            SchoolHistory history = Common.getSpecificDataObject(objectResponse, SchoolHistory.class);
                            list = (ArrayList<WeeklyHistory>) history.response.weeklyHistory;
                            if (list != null && list.size() > 0) {
                                historySchoolAdapter = new HistorySchoolAdapter(BushistoryActivity.this, list);
                                rv_expandable.setAdapter(historySchoolAdapter);
                            }
                            break;
                    }
                } else {
                    Common.showToast(BushistoryActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
