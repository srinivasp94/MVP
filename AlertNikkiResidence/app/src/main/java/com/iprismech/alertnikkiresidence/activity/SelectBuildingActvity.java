package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import com.iprismech.alertnikkiresidence.adapters.BuildingsAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.BuildingsPojo;
import com.iprismech.alertnikkiresidence.pojo.SearchBuidingPojo;
import com.iprismech.alertnikkiresidence.pojo.SearchSocietyPojo;
import com.iprismech.alertnikkiresidence.request.BuildingListRequest;
import com.iprismech.alertnikkiresidence.request.SearchDailyHelps;
import com.iprismech.alertnikkiresidence.request.SearchSocietyReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class SelectBuildingActvity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {
    private RecyclerView rview_select_building;
    private Object obj;
    private BuildingsPojo buildingsPojo;
    private LinearLayoutManager manager;
    //    private OnItemClickListener onItemClickListener;
    private String society_id = "", adminId = "", cityId = "", cityName, societyname;

    private String sOtp, sName, sMail, sPhone, sPassword, sBlood;
    private ImageView imgClose;
    private TextView txtitle;

    private ListView searchresults;
    private RetrofitResponseListener retrofitResponseListener;
    private SearchView et_search;
    SearchBuidingPojo searchBuidingPojo;
    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bundle bundle = new Bundle();
        bundle.putString("Key_CityId", cityId);
        bundle.putString("Key_CityName", cityName);

        bundle.putString("Key_Name", sName);
        bundle.putString("Key_Mobile", sPhone);
        bundle.putString("Key_Email", sMail);
        bundle.putString("Key_Password", sPassword);
        bundle.putString("Key_Blood", sBlood);
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_SOCIETY_SCREEN, bundle);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_select_building);

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_select_building, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        retrofitResponseListener = this;
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Select Building");
        imgClose.setOnClickListener(this);
        et_search = findViewById(R.id.et_search);
        searchresults = findViewById(R.id.searchresults);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            adminId = bundle.getString("Key_AdminId", "");
            society_id = bundle.getString("Key_SocietyId", "");
            cityId = bundle.getString("Key_CityId", "");

            societyname = bundle.getString("Key_SocietyName", "");
            cityName = bundle.getString("Key_CityName", "");


            sOtp = bundle.getString("Key_otp");
            sName = bundle.getString("Key_Name");
            sPhone = bundle.getString("Key_Mobile");
            sMail = bundle.getString("Key_Email");
            sPassword = bundle.getString("Key_Password");
            sBlood = bundle.getString("Key_Blood");
        }
        rview_select_building = findViewById(R.id.rview_selectbuilding);
        sendRequest();
    }

    private void sendRequest() {
        BuildingListRequest buildingListRequest = new BuildingListRequest();
        buildingListRequest.admin_id = adminId;
        try {
            obj = Class.forName(BuildingListRequest.class.getName()).cast(buildingListRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "buildings", true);


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
                    new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "building_search", true);

                }
                return false;
            }
        });

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getApplicationContext(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);

                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            buildingsPojo = new Gson().fromJson(jsonString, BuildingsPojo.class);
                            manager = new LinearLayoutManager(SelectBuildingActvity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            // LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SelectBuildingActvity.this,LinearLayoutManager.VERTICAL,false);
                            rview_select_building.setLayoutManager(manager);
                            BuildingsAdapter adapter = new BuildingsAdapter(SelectBuildingActvity.this, buildingsPojo);
                            rview_select_building.setAdapter(adapter);
                            adapter.setOnItemClickListener(new BuildingsAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.tv_item_building:
                                            getdataFromAdapter(position);
                                            break;
                                    }
                                }
                            });

                            break;
                        case 2:
                            searchBuidingPojo = gson.fromJson(jsonString, SearchBuidingPojo.class);
                            String[] shops = new String[buildingsPojo.getResponse().size()];
                            for (int i = 0; i < buildingsPojo.getResponse().size(); i++) {
                                shops[i] = buildingsPojo.getResponse().get(i).getTitle();
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(SelectBuildingActvity.this, R.layout.simple_list1, shops);
                            //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_list1);
                            spinnerArrayAdapter.notifyDataSetChanged();
                            searchresults.setAdapter(spinnerArrayAdapter);
                            spinnerArrayAdapter.notifyDataSetChanged();
                            searchresults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("WrongConstant")
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    getdataFromAdapter(position);

                                }
                            });

                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getdataFromAdapter(int position) {
        String buildingid = buildingsPojo.getResponse().get(position).getId();
        String title = buildingsPojo.getResponse().get(position).getTitle();

        Intent returnIntent = new Intent(SelectBuildingActvity.this, SelectFlatActivity.class);
        returnIntent.putExtra("Key_id", buildingid);
        returnIntent.putExtra("Key_AdminId", adminId);
        returnIntent.putExtra("Key_SocietyId", society_id);
        returnIntent.putExtra("Key_CityId", cityId);

        returnIntent.putExtra("Key_SocietyName", societyname);
        returnIntent.putExtra("Key_CityName", cityName);
        returnIntent.putExtra("Key_BuildingName", title);

        returnIntent.putExtra("Key_Name", sName);
        returnIntent.putExtra("Key_Mobile", sPhone);
        returnIntent.putExtra("Key_Email", sMail);
        returnIntent.putExtra("Key_Password", sPassword);
        returnIntent.putExtra("Key_Blood", sBlood);
        startActivity(returnIntent);
//        setResult(Activity.RESULT_OK, returnIntent);
//        finish();

        //  Common.commonLogs(SelectBuildingActvity.this,title+ " and "+ id);

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }
}
