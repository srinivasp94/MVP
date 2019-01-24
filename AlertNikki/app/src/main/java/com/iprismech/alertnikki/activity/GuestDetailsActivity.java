package com.iprismech.alertnikki.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.Visitors_Common_Req;
import com.iprismech.alertnikki.Response.ResponseVisitMember;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class GuestDetailsActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {
    private ImageView img_pic, back;
    private TextView mName, mVehicle, mType, mCheckin, mAllowed, mApproved;
    private Object obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guest_details);
    }
    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_guest_details, null);
        return view;
    }
    @Override
    public void setPresenter() {

    }
    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        back.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        img_pic = findViewById(R.id.image_pics);
        mName = findViewById(R.id.txt_nameOfUser);
        mType = findViewById(R.id.txt_gust_type);
        mVehicle = findViewById(R.id.txt_vehicleNumber);
        mCheckin = findViewById(R.id.visitor_tv_chech_in);
        mAllowed = findViewById(R.id.tv_allowed_by);
        mApproved = findViewById(R.id.tv_approved_by);
        back = findViewById(R.id.iv_navigate_back);
        Bundle bundle = getIntent().getExtras();
        String visitor_id = bundle.getString("Key_Visitor_id");
        String type_id = bundle.getString("Key_Type_id");


        Visitors_Common_Req visitors_common_req = new Visitors_Common_Req();
        visitors_common_req.visitorId = visitor_id;
        if (type_id.equalsIgnoreCase("Guest")) {
            visitors_common_req.type = "1";
        } else {
            visitors_common_req.type = "2";
        }


        try {
            obj = Class.forName(Visitors_Common_Req.class.getName()).cast(visitors_common_req);
        } catch (Exception e) {
            e.printStackTrace();
        }
            new RetrofitRequester(this).callPostServices(obj, 1, "after_in_visitor_data", true);

//        back = findViewById(R.id.image_pics);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(GuestDetailsActivity.this, "PLease Try again");
        }
        else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            JSONObject jsonObject = object.optJSONObject("result");

//                            ResponseVisitMember responseVisitMember = Common.getSpecificDataObject(objectResponse, ResponseVisitMember.class);
                            mName.setText(jsonObject.optString("name"));
                            mType.setText(jsonObject.optString("type"));
                            mCheckin.setText(jsonObject.optString("in_time"));
                            mAllowed.setText(jsonObject.optString("security_name"));
                            mApproved.setText(jsonObject.optString("user_name"));
                            mVehicle.setText("Vehicle Number: " + jsonObject.optString("vehicle_no"));
                          //  Picasso.with(context).load(Constants.BASE_IMAGE_URL + jsonObject.optString().into(viewHolder.service_img);
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_navigate_back:
                onBackPressed();
                break;

        }
    }
}
