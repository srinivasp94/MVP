package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.StaffProfilePojo;
import com.iprismech.alertnikkiresidence.request.ChooseMaidRequest;
import com.iprismech.alertnikkiresidence.request.StaffProfileRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class StaffProfileActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private LinearLayout ll_make_call, ll_gate_pass, ll_staff_delete, ll_attendance_history, ll_give_rating, ll_available_slots;
    private TextView tv_satff_name, tv_staff_type, tv_working_flats, tv_staff_rating;
    private ImageView iv_staff;

    private RetrofitResponseListener retrofitResponseListener;
    String maid_id;
    private Object obj;
    private StaffProfilePojo staffprofilePojo;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_my_staff_profile, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        retrofitResponseListener = this;
        maid_id = getIntent().getExtras().getString("maid_id");
        tv_satff_name = findViewById(R.id.tv_staff_name);
        tv_staff_type = findViewById(R.id.tv_staff_type);
        tv_staff_rating = findViewById(R.id.tv_staff_rating);
        tv_working_flats = findViewById(R.id.tv_staff_working_flats);
        iv_staff = findViewById(R.id.iv_staff);


        ll_make_call = findViewById(R.id.ll_make_call);
        ll_gate_pass = findViewById(R.id.ll_gate_pass);
        ll_staff_delete = findViewById(R.id.ll_staff_delete);
        ll_attendance_history = findViewById(R.id.ll_attendance_history);
        ll_give_rating = findViewById(R.id.ll_give_rating);
        ll_available_slots = findViewById(R.id.ll_available_slots);


        StaffProfileRequest staffProfileRequest = new StaffProfileRequest();
        staffProfileRequest.adminId = "2";
        //   staffProfileRequest.maidId = maid_id;
        staffProfileRequest.maidId = "2";
        try {
            obj = Class.forName(StaffProfileRequest.class.getName()).cast(staffProfileRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "maid_view", true);

        ll_make_call .setOnClickListener(this);
        ll_gate_pass .setOnClickListener(this);
        ll_staff_delete .setOnClickListener(this);
        ll_attendance_history .setOnClickListener(this);
        ll_give_rating .setOnClickListener(this);
        ll_available_slots .setOnClickListener(this);
    }


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();

    }


    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(StaffProfileActivity.this, "Please Try Again");
        } else {
            try {

                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                staffprofilePojo = gson.fromJson(jsonString, StaffProfilePojo.class);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Picasso.with(StaffProfileActivity.this).load(Constants.BASE_IMAGE_URL + staffprofilePojo.getResponse().getImage())
                                    .error(R.drawable.dummy)
                                    .into(iv_staff);
                            tv_satff_name.setText(staffprofilePojo.getResponse().getTitle());
                            tv_staff_type.setText(staffprofilePojo.getResponse().getDesignation());
                            tv_staff_rating.setText(staffprofilePojo.getResponse().getRating());
                            tv_working_flats.setText(staffprofilePojo.getResponse().getFlats());

                            break;
                    }

                }
            } catch (Exception e) {
            }
        }
    }
}
