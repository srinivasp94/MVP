package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.schoolbus.SelectSchoolActivity;
import com.iprismech.alertnikkiresidence.adapters.DailyHelpsAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.DailyHelpsListPojo;
import com.iprismech.alertnikkiresidence.pojo.MyStaff_Maids_List_Pojo;
import com.iprismech.alertnikkiresidence.pojo.SchoolBusSearchPojo;
import com.iprismech.alertnikkiresidence.pojo.SearchDailyHelpsPojo;
import com.iprismech.alertnikkiresidence.request.DailyHelpsList;
import com.iprismech.alertnikkiresidence.request.SchoolBusSearchReq;
import com.iprismech.alertnikkiresidence.request.SearchDailyHelps;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class AddStaffActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private GridView gv_daily_helps;
    private Object obj;
    private DailyHelpsListPojo dailyHelpsListPojo;
    private DailyHelpsAdapter daily_helps_adapter;
    private RetrofitResponseListener retrofitResponseListener;

    private ImageView imgClose;
    private TextView txtitle;

    private ListView searchresults;
    private SearchView et_search;
    private SearchDailyHelpsPojo searchDailyHelpsPojo;

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
        View view = getLayoutInflater().inflate(R.layout.activity_addstaff, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(AddStaffActivity.this, "Please Try Again");
        } else {
            try {

                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                switch (requestId) {
                    case 1:
                        dailyHelpsListPojo = gson.fromJson(jsonString, DailyHelpsListPojo.class);
                        JSONObject jsonObject = new JSONObject(jsonString);
                        if (jsonObject.optBoolean("status")) {
                            daily_helps_adapter = new DailyHelpsAdapter(AddStaffActivity.this, dailyHelpsListPojo);
                            gv_daily_helps.setAdapter(daily_helps_adapter);
                            gv_daily_helps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("WrongConstant")
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String service_id = dailyHelpsListPojo.getResponse().get(position).getId();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("ServiceID", service_id);
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CHOOSE_MAID, bundle);
                                    //finish();
                                }
                            });

                        } else {
                            Common.showToast(AddStaffActivity.this, jsonObject.optString("message"));
                        }
                        break;
                    case 2:
                        et_search.setFocusable(true);
                        searchDailyHelpsPojo = gson.fromJson(jsonString, SearchDailyHelpsPojo.class);
                        String[] shops = new String[searchDailyHelpsPojo.getResponse().size()];
                        for (int i = 0; i < searchDailyHelpsPojo.getResponse().size(); i++) {
                            shops[i] = searchDailyHelpsPojo.getResponse().get(i).getTitle();
                        }
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddStaffActivity.this, R.layout.simple_list1, shops);
                        //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_list1);
                        spinnerArrayAdapter.notifyDataSetChanged();
                        searchresults.setAdapter(spinnerArrayAdapter);
                        spinnerArrayAdapter.notifyDataSetChanged();
                        searchresults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @SuppressLint("WrongConstant")
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String service_id = searchDailyHelpsPojo.getResponse().get(position).getId();
                                Bundle bundle = new Bundle();
                                bundle.putString("ServiceID", service_id);
                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CHOOSE_MAID, bundle);
                            }
                        });
                        break;
                }
            } catch (Exception e) {
            }
        }
    }

    protected void initializeViews() {
        super.initializeViews();
        retrofitResponseListener = this;
        ApplicationController.getInstance().setContext(context);

        gv_daily_helps = findViewById(R.id.gv_daily_helps_list);
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Add Staff");

        imgClose.setOnClickListener(this);
        et_search = findViewById(R.id.et_search);
        searchresults = findViewById(R.id.searchresults);

        DailyHelpsList dailyHelpsReq = new DailyHelpsList();

        dailyHelpsReq.adminId = SharedPrefsUtils.getInstance(AddStaffActivity.this).getAdminID();
        //flatListRequest.building_id="4";
        try {
            obj = Class.forName(DailyHelpsList.class.getName()).cast(dailyHelpsReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "dailyhelps", true);

        et_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() > 2) {
                    SearchDailyHelps searchDailyHelps = new SearchDailyHelps();
                    searchDailyHelps.adminId = "2";
                    searchDailyHelps.name = "" + s;
                    try {
                        obj = Class.forName(SearchDailyHelps.class.getName()).cast(searchDailyHelps);
                    } catch (Exception e) {
                    }
                    new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "search_dailyhelps", true);

                }
                return false;
            }
        });
    }
}
