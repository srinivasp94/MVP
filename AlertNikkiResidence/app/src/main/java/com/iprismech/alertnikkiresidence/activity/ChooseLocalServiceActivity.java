package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.LocalServicesListAdapetr;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.LocalServicesListPojo;
import com.iprismech.alertnikkiresidence.pojo.SearchLocalServicePojo;
import com.iprismech.alertnikkiresidence.request.LocalServicesListRequest;
import com.iprismech.alertnikkiresidence.request.SchoolBusSearchReq;
import com.iprismech.alertnikkiresidence.request.SearchChooseServiceReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class ChooseLocalServiceActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private RecyclerView rview_choose_service;
    private LocalServicesListAdapetr localServicesListAdapetr;
    private LinearLayoutManager layoutManager;
    private Object obj;
    private LocalServicesListPojo localServicesListPojo;

    private ImageView imgClose;
    private TextView txtitle;
    private ListView searchresults;
    private SearchView et_search;
    private RetrofitResponseListener retrofitResponseListener;
    private SearchLocalServicePojo searchLocalServicePojo;


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
        retrofitResponseListener = this;
        rview_choose_service = findViewById(R.id.rview_choose_local_service);
        layoutManager = new LinearLayoutManager(ChooseLocalServiceActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Choose Services");

        searchresults = findViewById(R.id.searchresults);
        et_search = findViewById(R.id.et_search);

        imgClose.setOnClickListener(this);

        LocalServicesListRequest req = new LocalServicesListRequest();
        req.adminId = SharedPrefsUtils.getInstance(ChooseLocalServiceActivity.this).getAdminID();
        //  req.userId = 22;
        try {
            obj = Class.forName(LocalServicesListRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_local_services", true);

        et_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() > 2) {
                    SearchChooseServiceReq searchChooseServiceReq = new SearchChooseServiceReq();
                    searchChooseServiceReq.adminId = "2";
                    searchChooseServiceReq.service_name = "" + s;
                    try {
                        obj = Class.forName(SearchChooseServiceReq.class.getName()).cast(searchChooseServiceReq);
                    } catch (Exception e) {
                    }
                    new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "search_user_local_services", true);

                }
                return false;
            }
        });

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_choose_local_service, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ChooseLocalServiceActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            localServicesListPojo = gson.fromJson(jsonString, LocalServicesListPojo.class);
                            rview_choose_service.setLayoutManager(layoutManager);
                            localServicesListAdapetr = new LocalServicesListAdapetr(ChooseLocalServiceActivity.this, localServicesListPojo);
                            rview_choose_service.setAdapter(localServicesListAdapetr);
                            localServicesListAdapetr.notifyDataSetChanged();
                            break;
                        case 2:
                            et_search.setFocusable(true);
                            searchLocalServicePojo = gson.fromJson(jsonString, SearchLocalServicePojo.class);
                            String[] shops = new String[searchLocalServicePojo.getResponse().size()];
                            for (int i = 0; i < searchLocalServicePojo.getResponse().size(); i++) {
                                shops[i] = searchLocalServicePojo.getResponse().get(i).getTitle();
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ChooseLocalServiceActivity.this, R.layout.simple_list1, shops);
                            //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_list1);
                            spinnerArrayAdapter.notifyDataSetChanged();
                            searchresults.setAdapter(spinnerArrayAdapter);
                            spinnerArrayAdapter.notifyDataSetChanged();


                            searchresults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("WrongConstant")
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Bundle bundle = new Bundle();
                                    bundle.putString("service_id", searchLocalServicePojo.getResponse().get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOCAL_SERVICE_DETAILS_SCREEN,bundle);
                                }
                            });
                            break;
                    }
                } else {
                    Common.showToast(ChooseLocalServiceActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
