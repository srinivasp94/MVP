package com.iprismech.alertnikki.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.BuildingsPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.BuildingListRequest;
import com.iprismech.alertnikki.adapters.BuildingsAdapter;
import com.iprismech.alertnikki.listeners.OnItemClickListener;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class SelectBuildingActvity extends AppCompatActivity implements RetrofitResponseListener {
    private RecyclerView rview_select_building;
    private Object obj;
    private BuildingsPojo buildingsPojo;
    private LinearLayoutManager manager;
//    private OnItemClickListener onItemClickListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_building);
        rview_select_building = findViewById(R.id.rview_selectbuilding);
        sendRequest();
    }

    private void sendRequest() {
        BuildingListRequest buildingListRequest = new BuildingListRequest();
        buildingListRequest.admin_id = SharedPrefsUtils.getInstance(getApplicationContext()).getAdmin();
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
        String title= buildingsPojo.getResponse().get(position).getTitle();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("id",id);
        returnIntent.putExtra("name",title);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

      //  Common.commonLogs(SelectBuildingActvity.this,title+ " and "+ id);

    }

    /*@Override
    public void onClick(View view, int Position) {
        switch (view.getId()) {
            case R.id.tv_item_building:
                String buildingid=buildingsPojo.getResponse().get(Position).getId();
                break;
        }
    }*/
}
