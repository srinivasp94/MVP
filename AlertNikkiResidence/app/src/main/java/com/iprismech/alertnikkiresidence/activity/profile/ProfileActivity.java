package com.iprismech.alertnikkiresidence.activity.profile;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.ProfileAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.User;
import com.iprismech.alertnikkiresidence.pojo.UsersData;
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

import java.util.ArrayList;
import java.util.List;

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
            MyFlat,
            Digital,
            Logout;

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
        Digital.setOnClickListener(this);
        Logout.setOnClickListener(this);
        LinearMysocirty.setOnClickListener(this);
        Management.setOnClickListener(this);
        Emergeny.setOnClickListener(this);
        NoticeBoard.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        manager = new LinearLayoutManager(ProfileActivity.this);

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

                            Picasso.with(ProfileActivity.this).load(Constants.BASE_IMAGE_URL + res.response.image).into(imgProfile);
                            Picasso.with(ProfileActivity.this).load(Constants.BASE_IMAGE_URL + res.response.qrcode).into(imgQrcode);
                            txtName.setText(res.response.name);
                            txttypeOfOwn.setText(res.response.residenceType);
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
