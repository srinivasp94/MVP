package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.FlatsAdapter;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.FlatPojo;
import com.iprismech.alertnikkiresidence.request.FlatListRequest;
import com.iprismech.alertnikkiresidence.request.RegisterConfirm;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

public class SelectFlatActivity extends AppCompatActivity implements RetrofitResponseListener, View.OnClickListener {
    private RecyclerView rview;
    private Object obj;
    private FlatPojo flatPojo;
    private String adminId, buildingid, societyId, cityId,
            societyname, cityName, buildingName;

    private String sOtp, sName, sMail, sPhone, sPassword, sBlood;
    private ImageView imgClose;
    private TextView txtitle;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flat);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            buildingid = bundle.getString("Key_id", "");
            adminId = bundle.getString("Key_AdminId", "");
            societyId = bundle.getString("Key_SocietyId", "");
            cityId = bundle.getString("Key_CityId", "");

            societyname = bundle.getString("Key_SocietyName", "");
            cityName = bundle.getString("Key_CityName", "");
            buildingName = bundle.getString("Key_BuildingName", "");

            sOtp = bundle.getString("Key_otp");
            sName = bundle.getString("Key_Name");
            sPhone = bundle.getString("Key_Mobile");
            sMail = bundle.getString("Key_Email");
            sPassword = bundle.getString("Key_Password");
            sBlood = bundle.getString("Key_Blood");
        }
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Select Falt");
        imgClose.setOnClickListener(this);

        rview = findViewById(R.id.rview_selectbuilding);
//        String buildingid = (String) getIntent().getExtras().get("building_id");
//        String adminId = (String) getIntent().getExtras().get("building_id");

        sendrequest();
    }

    private void sendrequest() {

        FlatListRequest flatListRequest = new FlatListRequest();
        flatListRequest.admin_id = adminId;
        flatListRequest.building_id = buildingid;
        //flatListRequest.building_id="4";
        try {
            obj = Class.forName(FlatListRequest.class.getName()).cast(flatListRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "flats", true);
    }

    @SuppressLint("WrongConstant")
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
                            flatPojo = new Gson().fromJson(jsonString, FlatPojo.class);
//                            LinearLayoutManager manager = new LinearLayoutManager(SelectFlatActivity.this);
//                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            // LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SelectBuildingActvity.this,LinearLayoutManager.VERTICAL,false);
                            rview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
                            FlatsAdapter adapter = new FlatsAdapter(getApplicationContext(), flatPojo);
                            rview.setAdapter(adapter);

                            adapter.setOnItemClickListener(new FlatsAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.tv_item_flat_number:
                                            getdataFromAdapter(position);
                                            break;
                                    }
                                }

                            });
                            break;
                        case 2:
                            //lead to Detail page
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                            break;
                    }
                } else {
                    Common.showToast(SelectFlatActivity.this, object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @SuppressLint("WrongConstant")
    private void getdataFromAdapter(int position) {
        String id = flatPojo.getResponse().get(position).getId();
        String title = flatPojo.getResponse().get(position).getTitle();


        Bundle bundle = new Bundle();
        bundle.putString("Key_id", id);
        bundle.putString("Key_AdminId", adminId);
        bundle.putString("Key_SocietyId", societyId);
        bundle.putString("Key_CityId", cityId);
        bundle.putString("Key_BuildingId", buildingid);

        bundle.putString("Key_SocietyName", societyname);
        bundle.putString("Key_CityName", cityName);
        bundle.putString("Key_BuildingName", buildingName);
        bundle.putString("Key_FlatName", title);

        bundle.putString("Key_Name", sName);
        bundle.putString("Key_Mobile", sPhone);
        bundle.putString("Key_Email", sMail);
        bundle.putString("Key_Password", sPassword);
        bundle.putString("Key_Blood", sBlood);

        bundle.putString("Key_CityID", cityId);
        bundle.putString("Key_BuildingId", flatPojo.getResponse().get(position).getBuilding_id());
        bundle.putString("Key_FlatId", flatPojo.getResponse().get(position).getId());
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_DETAIL_SCREEN, bundle);

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
