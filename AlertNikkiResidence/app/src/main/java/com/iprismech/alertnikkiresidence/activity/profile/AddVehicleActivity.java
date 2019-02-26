package com.iprismech.alertnikkiresidence.activity.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.Vehicle;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddVehicleActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private TextView txtAddVehicle, txtsubmitVehicle;
    private ListView txtVehicls;
    private LinearLayout layoutaddVehicle;
    ArrayList<View> viewList = new ArrayList<>();
    int i = 0;
    private Object obj;
    private ImageView imgClose;
    private TextView txtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_vehicle);
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
//        layoutaddVehicle.setOnClickListener(this);
        txtAddVehicle.setOnClickListener(this);
        txtsubmitVehicle.setOnClickListener(this);
        imgClose.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
//        Bundle bundle = getIntent().getExtras();
//        String mVehicls = bundle.getString("KEY_Vehicles", "");

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Add Vehicle");

        txtAddVehicle = findViewById(R.id.txtAddVehicle);
        txtVehicls = findViewById(R.id.txtVehicls);
        txtsubmitVehicle = findViewById(R.id.txtsubmitVehicle);
        layoutaddVehicle = findViewById(R.id.layoutaddVehicle);
//        String[] arrVeh = mVehicls.split(",");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddVehicleActivity.this, android.R.layout.simple_list_item_1, arrVeh);
//        txtVehicls.setAdapter(adapter);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_add_vehicle, null);

        return view;
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
            case R.id.txtAddVehicle:
                if (i < 5 || i == 0) {
                    View child = getLayoutInflater().inflate(R.layout.ittem_vehicle, null);
                    // views[i] = child;
                    viewList.add(child);
                    layoutaddVehicle.addView(viewList.get(i));
                    i = i + 1;
                } else {
                    txtAddVehicle.setClickable(false);
                    Common.showToast(AddVehicleActivity.this, "You Added maximum vehicles");
                }
                break;
            case R.id.txtsubmitVehicle:
                JSONArray array = new JSONArray();
                if (viewList.size() == 0) {

                } else if (viewList.size() > 0) {
                    for (int i = 0; i < viewList.size(); i++) {
                        View vi = viewList.get(i);
                        EditText edtVehicle = vi.findViewById(R.id.edtVehicle);
                        if (edtVehicle.getText().toString().length() == 0) {
                            Common.showToast(AddVehicleActivity.this, "Please enter valid Vehicle Number");
                        } else {
                            /*add_vehicle_numbers
                                    user_id
                            vehicle_numbers*/
                            array.put(edtVehicle.getText().toString());
                            if (array.length() == viewList.size()) {
                                Vehicle req = new Vehicle();
                                req.userId = SharedPrefsUtils.getInstance(AddVehicleActivity.this).getId();
                                /*for (int x = 0; x < array.length(); x++) {
                                    StringBuilder sb = new StringBuilder();

                                    sb.append(""+);
                                }*/
                                req.vehicleNumbers = array.toString().
                                        replace("[", "")
                                        .replace("]", "").replace("\"", "");
                                try {
                                    obj = Class.forName(Vehicle.class.getName()).cast(req);
                                } catch (Exception e) {

                                }
                                new RetrofitRequester(this).callPostServices(obj, 1, "add_vehicle_numbers", true);
                            }
                            /*StringBuilder sb = new StringBuilder();
                            sb.append(edtVehicle.getText().toString()+"");*/
                        }
                    }

                }
                break;
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(AddVehicleActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Common.showToast(AddVehicleActivity.this, jsonObject.optString("message"));
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(AddVehicleActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddVehicleActivity.this, VehiclesActivity.class);
        startActivity(intent);
        finish();
    }
}
