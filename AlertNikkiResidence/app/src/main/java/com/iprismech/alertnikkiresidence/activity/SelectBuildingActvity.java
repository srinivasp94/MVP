package com.iprismech.alertnikkiresidence.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.BuildingsAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.BuildingsPojo;
import com.iprismech.alertnikkiresidence.request.BuildingListRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class SelectBuildingActvity extends BaseAbstractActivity implements RetrofitResponseListener {
    private RecyclerView rview_select_building;
    private Object obj;
    private BuildingsPojo buildingsPojo;
    private LinearLayoutManager manager;
    //    private OnItemClickListener onItemClickListener;
    private String society_id = "", adminId = "";

    private String sOtp, sName, sMail, sPhone, sPassword, sBlood;

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

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            society_id = bundle.getString("Key_SocietyId", "");
            adminId = bundle.getString("Key_AdminId", "");

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
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getdataFromAdapter(int position) {
        String id = buildingsPojo.getResponse().get(position).getId();
        String title = buildingsPojo.getResponse().get(position).getTitle();

        Intent returnIntent = new Intent(SelectBuildingActvity.this, SelectFlatActivity.class);
        returnIntent.putExtra("Key_id", id);
        returnIntent.putExtra("Key_AdminId", adminId);
        returnIntent.putExtra("Key_SocietyId", society_id);

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

}
