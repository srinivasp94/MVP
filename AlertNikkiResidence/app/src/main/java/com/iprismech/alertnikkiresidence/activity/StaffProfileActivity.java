package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.StaffProfilePojo;
import com.iprismech.alertnikkiresidence.request.ChooseMaidRequest;
import com.iprismech.alertnikkiresidence.request.DeleteStaffRequest;
import com.iprismech.alertnikkiresidence.request.GiveStaffRatingRequest;
import com.iprismech.alertnikkiresidence.request.StaffProfileRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.Constants;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class StaffProfileActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private LinearLayout ll_make_call, ll_gate_pass, ll_staff_delete, ll_attendance_history, ll_give_rating, ll_available_slots;
    private TextView tv_satff_name, tv_staff_type, tv_working_flats, tv_staff_rating;
    private EditText et_staff_descrption;
    private ImageView iv_staff;

    String rating = "0.0", user_id, user_type;

    private RetrofitResponseListener retrofitResponseListener;
    String maid_id;
    private Object obj;
    private StaffProfilePojo staffprofilePojo;
    private AlertDialog alertDialog;
    private String user_maid_id;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_make_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + staffprofilePojo.getResponse().getMobile()));
                startActivity(intent);
                break;
            case R.id.ll_gate_pass:

                break;
            case R.id.ll_staff_delete:
                DeleteStaffRequest deleteStaffRequest = new DeleteStaffRequest();
                deleteStaffRequest.user_maid_id = user_maid_id;
                try {
                    obj = Class.forName(DeleteStaffRequest.class.getName()).cast(deleteStaffRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 3, "delete_user_maid", true);
                break;
            case R.id.ll_attendance_history:

                break;
            case R.id.ll_give_rating:
                LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
                View view1 = inflater.inflate(R.layout.dialog_alert_rating, null);

                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setView(view1);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(true);
                final RatingBar give_rating;
                TextView tv_not_now, tv_rating_submit;


                give_rating = view1.findViewById(R.id.give_staff_rating);
                tv_not_now = view1.findViewById(R.id.tv_not_now_rating);
                tv_rating_submit = view1.findViewById(R.id.tv_submit_rating);
                et_staff_descrption = view1.findViewById(R.id.et_staff_description);

                tv_rating_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rating = String.valueOf(give_rating.getRating());
                        if (rating.isEmpty() || rating.equalsIgnoreCase("") || rating.equalsIgnoreCase("0.0")) {
                            Toast.makeText(StaffProfileActivity.this, "Please give rating to Staff", Toast.LENGTH_SHORT).show();
                        } else {
                            // Toast.makeText(StaffProfileActivity.this, "Given", Toast.LENGTH_SHORT).show();
                            GiveStaffRatingRequest giveStaffRatingRequest = new GiveStaffRatingRequest();
                            giveStaffRatingRequest.user_id = user_id;
                            //   staffProfileRequest.maidId = maid_id;
                            giveStaffRatingRequest.user_type = user_type;
                            giveStaffRatingRequest.maid_id = maid_id;
                            giveStaffRatingRequest.description = et_staff_descrption.getText().toString() + "";
                            giveStaffRatingRequest.rating = rating;
                            try {
                                obj = Class.forName(GiveStaffRatingRequest.class.getName()).cast(giveStaffRatingRequest);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "give_rating_to_maid", true);


                        }
                    }
                });

                tv_not_now.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
            case R.id.ll_available_slots:

                break;
        }
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
        user_maid_id = getIntent().getExtras().getString("user_maid_id");

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


        user_id = SharedPrefsUtils.getInstance(StaffProfileActivity.this).getId();
        user_type = SharedPrefsUtils.getInstance(StaffProfileActivity.this).getuserType();


        StaffProfileRequest staffProfileRequest = new StaffProfileRequest();
        staffProfileRequest.adminId = "2";
        staffProfileRequest.maidId = maid_id;
        //staffProfileRequest.maidId = "2";
        try {
            obj = Class.forName(StaffProfileRequest.class.getName()).cast(staffProfileRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "maid_view", true);

        ll_make_call.setOnClickListener(this);
        ll_gate_pass.setOnClickListener(this);
        ll_staff_delete.setOnClickListener(this);
        ll_attendance_history.setOnClickListener(this);
        ll_give_rating.setOnClickListener(this);
        ll_available_slots.setOnClickListener(this);
    }


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();

    }


    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(StaffProfileActivity.this, "Please Try Again");
        } else {
            try {

                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);

                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            staffprofilePojo = gson.fromJson(jsonString, StaffProfilePojo.class);
                            Picasso.with(StaffProfileActivity.this).load(Constants.BASE_IMAGE_URL + staffprofilePojo.getResponse().getImage())
                                    .error(R.drawable.dummy)
                                    .into(iv_staff);
                            tv_satff_name.setText(staffprofilePojo.getResponse().getTitle());
                            tv_staff_type.setText(staffprofilePojo.getResponse().getDesignation());
                            tv_staff_rating.setText(staffprofilePojo.getResponse().getRating());
                            tv_working_flats.setText(staffprofilePojo.getResponse().getFlats());
                            //user_maid_id=staffprofilePojo.getResponse().getId();
                            break;
                        case 2:

                            Toast.makeText(StaffProfileActivity.this, "Thanks for Rating", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                            break;
                        case 3:
                            Toast.makeText(StaffProfileActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MYSTAFF_ALERTS);
                            finish();
                            break;
                    }

                }
            } catch (Exception e) {
            }
        }
    }
}
