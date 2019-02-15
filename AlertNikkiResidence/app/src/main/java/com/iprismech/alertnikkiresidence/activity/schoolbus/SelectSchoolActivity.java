package com.iprismech.alertnikkiresidence.activity.schoolbus;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.SelectSchoolAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.SearchBusReq;
import com.iprismech.alertnikkiresidence.response.SearchBus;
import com.iprismech.alertnikkiresidence.response.SearchBusList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class SelectSchoolActivity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {
    RecyclerView rv_schools;
    TextView txt_NoItems;
    LinearLayoutManager layoutManager;
    private Object obj;
    private ArrayList<SearchBusList> busList = new ArrayList();
    private SelectSchoolAdapter schoolAdapter;
    FloatingActionButton fab;

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
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_select_school, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        fab.setOnClickListener(this);

        imgClose.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Select School");


        //search_school_bus
        rv_schools = findViewById(R.id.rv_schools);
        txt_NoItems = findViewById(R.id.txt_NoItems);
        fab = findViewById(R.id.fab);
        rv_schools.setLayoutManager(layoutManager);

        SearchBusReq req = new SearchBusReq();
        req.adminId = SharedPrefsUtils.getInstance(SelectSchoolActivity.this).getAdminID();
        req.schoolBusName = "";
        try {
            obj = Class.forName(SearchBusReq.class.getName()).cast(req);
        } catch (Exception e) {
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "search_school_bus", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(SelectSchoolActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            SearchBus bus = Common.getSpecificDataObject(objectResponse, SearchBus.class);
                            busList = (ArrayList<SearchBusList>) bus.response;
                            if (busList != null && busList.size() > 0) {
                                schoolAdapter = new SelectSchoolAdapter(SelectSchoolActivity.this, busList);
                                rv_schools.setAdapter(schoolAdapter);
                                schoolAdapter.setOnItemClickListener(new SelectSchoolAdapter.OnitemClickListener() {
                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        switch (view.getId()) {
                                            case R.id.rootLayout:
                                                Bundle bundle = new Bundle();
                                                bundle.putString("KEY_BUS_ID", busList.get(position).id);
                                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_BUS_ROUTE_SCREEN, bundle);
                                                break;
                                        }

                                    }
                                });
                            } else {
                                txt_NoItems.setVisibility(View.VISIBLE);
                                rv_schools.setVisibility(View.GONE);
                            }
                            break;
                    }
                } else {
                    Common.showToast(SelectSchoolActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_BUS_SCREEN);
                break;

            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }
}
