package com.iprismech.alertnikkiresidence.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.BuildingsAdapter;
import com.iprismech.alertnikkiresidence.adapters.FlatsAdapter;
import com.iprismech.alertnikkiresidence.pojo.FlatPojo;
import com.iprismech.alertnikkiresidence.request.FlatListRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class AddSelectFlatActivity extends AppCompatActivity implements RetrofitResponseListener {


    private RecyclerView rview;
    private Object obj;
    private FlatPojo flatPojo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flat);
        rview = findViewById(R.id.rview_selectbuilding);
        String id = (String) getIntent().getExtras().get("building_id");
        sendrequest(id);
    }

    private void sendrequest(String id) {

        FlatListRequest flatListRequest = new FlatListRequest();
        // flatListRequest.admin_id = SharedPrefsUtils.getInstance(AddSelectFlatActivity.this).getAdmin();
        flatListRequest.admin_id = "2";
        flatListRequest.building_id = id;
        //flatListRequest.building_id="4";
        try {
            obj = Class.forName(FlatListRequest.class.getName()).cast(flatListRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "available_flats", true);
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

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void getdataFromAdapter(int position) {
        String id = flatPojo.getResponse().get(position).getId();
        String title = flatPojo.getResponse().get(position).getTitle();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("id", id);
        returnIntent.putExtra("name", title);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

        //  Common.commonLogs(SelectBuildingActvity.this,title+ " and "+ id);

    }
}
