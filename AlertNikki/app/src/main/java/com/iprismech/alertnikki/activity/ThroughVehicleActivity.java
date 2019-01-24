package com.iprismech.alertnikki.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.ThroughVehiclePojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.Through_Vehicle;
import com.iprismech.alertnikki.adapters.ThroughVehicleAdapter;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;

import org.json.JSONObject;

public class ThroughVehicleActivity extends BaseAbstractActivity<Class> implements View.OnClickListener, RetrofitResponseListener {

    private RecyclerView recyclerView;
    private TextView btn_tv_ok;
    private TextView tv_vehicle_no;
    private Object obj;
    private ThroughVehicleAdapter throughVehicleAdapter;
    private ImageView throuh_vehicle_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_delivery);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_through_vehicle, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        Bundle bundle = getIntent().getExtras();
        String vehicle_no = bundle.getString("Key_vehicle");
        recyclerView = findViewById(R.id.rview_through_vehicle);
        //   btn_tv_ok = findViewById(R.id.ok_submit_vehicle);
        tv_vehicle_no = findViewById(R.id.search_vehicle);
        throuh_vehicle_close = findViewById(R.id.throuh_vehicle_close);
        tv_vehicle_no.setText("List of Vehicles Contains: " + vehicle_no);
        throuh_vehicle_close.setOnClickListener(this);
        Through_Vehicle req = new Through_Vehicle();
        req.vehicleNo = vehicle_no;
        try {
            obj = Class.forName(Through_Vehicle.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "through_vehicle_no", true);


    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getApplicationContext(), "Please Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            ThroughVehiclePojo throughVehiclePojo = null;
                            throughVehiclePojo = gson.fromJson(jsonString, ThroughVehiclePojo.class);
                            LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(manager);
                            throughVehicleAdapter = new ThroughVehicleAdapter(getApplicationContext(), throughVehiclePojo);
                            recyclerView.setAdapter(throughVehicleAdapter);
                            break;
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
