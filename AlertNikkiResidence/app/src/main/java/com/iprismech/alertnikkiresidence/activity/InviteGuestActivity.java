package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.GuestsReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.AppPermissions;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class InviteGuestActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private TextView txtInviteGuests;
    private RelativeLayout RlGuestsLists;
    private LinearLayout layoutNoGuests;
    private FloatingActionButton fab;
    private RecyclerView rvGuestsLists;
    private LinearLayoutManager manager;
    private boolean iscontactsGranted;
    private Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_invite_guests, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtInviteGuests.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        manager = new LinearLayoutManager(InviteGuestActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        txtInviteGuests = findViewById(R.id.txtInviteGuests);
        layoutNoGuests = findViewById(R.id.layoutNoGuests);
        RlGuestsLists = findViewById(R.id.LayoutGuests);
        fab = findViewById(R.id.fab);
        rvGuestsLists = findViewById(R.id.rvGuestsLists);
        rvGuestsLists.setLayoutManager(manager);

        GuestsReq req = new GuestsReq();

        req.adminId = "2";
        req.otpSentType = "1";
        req.userId = SharedPrefsUtils.getInstance(InviteGuestActivity.this).getId();
        req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);
        try {
//obj = Class.forName().cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "guests", true);


    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtInviteGuests:
                iscontactsGranted = AppPermissions.callPermissionForContacts(InviteGuestActivity.this);
                if (iscontactsGranted)
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PICK_CONTACT_SCREEN);
                break;
            case R.id.fab:
                iscontactsGranted = AppPermissions.callPermissionForContacts(InviteGuestActivity.this);
                if (iscontactsGranted)
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PICK_CONTACT_SCREEN);


        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(InviteGuestActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:

                            break;
                    }
                } else {
                    Common.showToast(InviteGuestActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
