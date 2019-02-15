package com.iprismech.alertnikkiresidence.activity.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.ProfileReq;
import com.iprismech.alertnikkiresidence.response.ProfileRes;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.AlertUtils;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.Constants;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class ProfileActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    ImageView imgProfile, imgQrcode;
    TextView txtName, txttypeOfOwn, txtAddress, txtPasscode;
    private Object obj;
    RecyclerView rvProfileitems;
    LinearLayoutManager manager;
    LinearLayout LinearMysocirty;
    TextView MySociety,
            Management, Emergeny, NoticeBoard,
            FamilyMembers,
            VisitorsHistory,
            MyFlat, MyVehicles,
            Digital,
            Logout;

    private ImageView imgClose;
    private TextView txtitle;
    private String mVehicles="";


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_profile, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        imgQrcode.setOnClickListener(this);
        MySociety.setOnClickListener(this);
        FamilyMembers.setOnClickListener(this);
        VisitorsHistory.setOnClickListener(this);
        MyFlat.setOnClickListener(this);
        MyVehicles.setOnClickListener(this);
        Digital.setOnClickListener(this);
        Logout.setOnClickListener(this);
        LinearMysocirty.setOnClickListener(this);
        Management.setOnClickListener(this);
        Emergeny.setOnClickListener(this);
        NoticeBoard.setOnClickListener(this);

        imgClose.setOnClickListener(this);


    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        manager = new LinearLayoutManager(ProfileActivity.this);
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Profile");
        imgProfile = findViewById(R.id.imgProfile);
        imgQrcode = findViewById(R.id.imgQrcode);
        txtName = findViewById(R.id.txtName);
        txttypeOfOwn = findViewById(R.id.txttypeOfOwn);
        txtAddress = findViewById(R.id.txtAddress);
        txtPasscode = findViewById(R.id.txtPasscode);

        MySociety = findViewById(R.id.MySociety);
        FamilyMembers = findViewById(R.id.FamilyMembers);
        VisitorsHistory = findViewById(R.id.VisitorsHistory);
        MyFlat = findViewById(R.id.MyFlat);
        MyVehicles = findViewById(R.id.MyVehicles);
        Digital = findViewById(R.id.Digital);
        Logout = findViewById(R.id.Logout);

        Management = findViewById(R.id.Management);
        Emergeny = findViewById(R.id.Emergeny);
        NoticeBoard = findViewById(R.id.NoticeBoard);
        LinearMysocirty = findViewById(R.id.LinearMysocirty);




        /*rvProfileitems = findViewById(R.id.rvProfileitems);
        rvProfileitems.setLayoutManager(manager);*/

/*
        UsersData usersData = new UsersData();
        List<User> usersList = usersData.getUsersList();
        List<String> userTypeList = usersData.getUserTypeList();
        ProfileAdapter adapter = new ProfileAdapter();
        adapter.setUserListAndType(usersList, userTypeList);
        rvProfileitems.setAdapter(adapter);*/

        ProfileReq req = new ProfileReq();
        req.userId = SharedPrefsUtils.getInstance(ProfileActivity.this).getId();
        req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);

        try {
            obj = Class.forName(ProfileReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_profile", true);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.Management:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MANAGEMENT_COMMITTE_SCREEN);
                break;
            case R.id.Emergeny:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_EMERGENCY_CONTACT_SCREEN);
                break;
            case R.id.NoticeBoard:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTICE_BOARD_SCREEN);
                break;
            case R.id.imgQrcode:
                showcustomAlert();
                break;
            case R.id.MySociety:
                boolean isvisible = false;
                if (isvisible == false) {
                    LinearMysocirty.setVisibility(View.VISIBLE);
                    isvisible = true;
                } else {
                    LinearMysocirty.setVisibility(View.GONE);
                }
                break;
            case R.id.FamilyMembers:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAMILY_SCREEN);
                break;
            case R.id.VisitorsHistory:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VISITORS_HISTORY_SCREEN);
                break;
            case R.id.MyFlat:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MY_FLATS_SCREEN);
                break;
            case R.id.MyVehicles:
                Intent intent = new Intent(ProfileActivity.this, AddVehicleActivity.class);
                intent.putExtra("KEY_Vehicles",mVehicles);
                startActivity(intent);
                break;
            case R.id.Digital:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_DIGITAL_INTERCOM_SCREEN);
                break;
            case R.id.Logout:
                AlertUtils.showSimpleAlert(ProfileActivity.this, "Are You sure to Logout...?", "Logout", "Logout", "Cancel", new AlertUtils.onClicklistners() {
                    @Override
                    public void onpositiveclick() {
                        SharedPrefsUtils.logoutUser();
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                        finish();
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });

                break;
        }
    }

    private void showcustomAlert() {
        LayoutInflater inflater = LayoutInflater.from(ProfileActivity.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_view_profile, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(ProfileActivity.this).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);

        ImageView icon, Qrcode;
        TextView txtName, txtPasscode, txtBlood, txtAddress;

        icon = view1.findViewById(R.id.kid_icon);
        Qrcode = view1.findViewById(R.id.imgQrcode);
        txtName = view1.findViewById(R.id.tzxtName);
        txtPasscode = view1.findViewById(R.id.txtPasscode);
        txtBlood = view1.findViewById(R.id.txtBlood);
        txtAddress = view1.findViewById(R.id.txtAddress);

        txtName.setText(SharedPrefsUtils.getString(SharedPrefsUtils.KEY_NAME));
        txtPasscode.setText(SharedPrefsUtils.getString(SharedPrefsUtils.KEY_PASSCODE));
//        txtBlood.setText(SharedPrefsUtils.getString(SharedPrefsUtils.KEY_));
        txtAddress.setText(SharedPrefsUtils.getString(SharedPrefsUtils.KEY_FLAT_NAME + ", "
                + SharedPrefsUtils.getString(SharedPrefsUtils.KEY_BUILDING_NAME) + ", "
                + SharedPrefsUtils.getString(SharedPrefsUtils.KEY_SOCIETY_NAME)
        ));
        Picasso.with(ProfileActivity.this).load(Constants.BASE_IMAGE_URL + SharedPrefsUtils.getString(SharedPrefsUtils.KEY_QRCODE)).error(R.drawable.app_logo).into(Qrcode);
        Picasso.with(ProfileActivity.this).load(Constants.BASE_IMAGE_URL + SharedPrefsUtils.getString(SharedPrefsUtils.KEY_PROFILE_PIC)).error(R.drawable.app_logo).into(icon);
        alertDialog.show();

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ProfileActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            ProfileRes res = Common.getSpecificDataObject(objectResponse, ProfileRes.class);
//                            "image":"storage\/users\/5c542debc2e9c.jpeg",
//                             "qrcode":"storage\/qrcode\/2865097.png"
                            SharedPrefsUtils.updateString(SharedPrefsUtils.KEY_QRCODE, res.response.qrcode.replace("\\", ""));
                            SharedPrefsUtils.updateString(SharedPrefsUtils.KEY_PROFILE_PIC, res.response.image.replace("\\", ""));
                            Common.commonLogs(ProfileActivity.this, res.response.qrcode.replace("\\", ""));
                            Common.commonLogs(ProfileActivity.this, res.response.image.replace("\\", ""));
                            Picasso.with(ProfileActivity.this).load(Constants.BASE_IMAGE_URL + res.response.image).into(imgProfile);
                            Picasso.with(ProfileActivity.this).load(Constants.BASE_IMAGE_URL + res.response.qrcode).into(imgQrcode);
                            txtName.setText(res.response.name);
                            txttypeOfOwn.setText(res.response.residenceType);
                            mVehicles = res.response.vehicle_numbers;
                            txtAddress.setText(res.response.flat + ", " + res.response.building + ", " + res.response.city);
                            txtPasscode.setText("Passcode:- " + res.response.passcode);
                            break;
                    }
                } else {
                    Common.showToast(ProfileActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
