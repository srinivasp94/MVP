package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.AllGuestsAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.GuestEditReq;
import com.iprismech.alertnikkiresidence.request.GuestsReq;
import com.iprismech.alertnikkiresidence.response.Guests;
import com.iprismech.alertnikkiresidence.response.GuestsList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.AppPermissions;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class InviteGuestActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private TextView txtInviteGuests;
    private RelativeLayout RlGuestsLists;
    private LinearLayout layoutNoGuests;
    private FloatingActionButton fab;
    private RecyclerView rvGuestsLists;
    private LinearLayoutManager manager;
    private boolean iscontactsGranted;
    private Object obj;
    private AllGuestsAdapter guestsAdapter;
    private ArrayList<GuestsList> guestsLists = new ArrayList<>();
    private int itemPosition;

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
            obj = Class.forName(GuestsReq.class.getName()).cast(req);
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
                /*iscontactsGranted = AppPermissions.callPermissionForContacts(InviteGuestActivity.this);
                if (iscontactsGranted)
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PICK_CONTACT_SCREEN);*/
                showAlertForAddGuests();
                break;
            case R.id.fab:
                showAlertForAddGuests();
                /*iscontactsGranted = AppPermissions.callPermissionForContacts(InviteGuestActivity.this);
                if (iscontactsGranted)
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PICK_CONTACT_SCREEN);*/


        }
    }

    @SuppressLint("WrongConstant")
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
                            Guests response = Common.getSpecificDataObject(objectResponse, Guests.class);
                            guestsLists = (ArrayList<GuestsList>) response.response;
                            if (guestsLists != null && guestsLists.size() > 0) {
                                layoutNoGuests.setVisibility(View.GONE);
                                RlGuestsLists.setVisibility(View.VISIBLE);
                                guestsAdapter = new AllGuestsAdapter(InviteGuestActivity.this, guestsLists);
                                rvGuestsLists.setAdapter(guestsAdapter);
                                guestsAdapter.setOnItemClickListener(new AllGuestsAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        switch (view.getId()) {
                                            case R.id.imgCallGuest:
                                                callToGuest(position);
                                                break;
                                            case R.id.imgDeleteGuest:
                                                callDeleteWS(position);
                                                break;
                                            case R.id.imgGuestEdit:
                                                callEditGuestDetailsWS(position);
                                                break;
                                        }
                                    }
                                });

                            } else {
                                layoutNoGuests.setVisibility(View.VISIBLE);
                                RlGuestsLists.setVisibility(View.GONE);
                            }
                            break;
                        case 2:

                            break;
                        case 3:
                            if (guestsLists.size() > 1) {
                                guestsLists.remove(itemPosition);
                                guestsAdapter.notifyDataSetChanged();
                            } else {
                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_INVITE_GUEST_SCREEN);
                                finish();
                            }
                            break;
                    }
                } else {
                    layoutNoGuests.setVisibility(View.VISIBLE);
                    RlGuestsLists.setVisibility(View.GONE);
                    Common.showToast(InviteGuestActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void callEditGuestDetailsWS(int pos) {

        Intent intent = new Intent(InviteGuestActivity.this, GuestEditActivity.class);
        intent.putExtra("Key_id", "1");
        intent.putExtra("Key_GuestId", guestsLists.get(pos).id);
        intent.putExtra("Key_Name", guestsLists.get(pos).name);
        intent.putExtra("Key_Mobile", guestsLists.get(pos).mobile);
        intent.putExtra("Key_Vehicle", guestsLists.get(pos).vehicleNo);
        startActivity(intent);
        finish();

       /* GuestEditReq req = new GuestEditReq();
        req.guestId = guestsLists.get(pos).id;
        req.name = guestsLists.get(pos).name;
        req.vehicleNo = guestsLists.get(pos).vehicleNo;
        try {
            obj = Class.forName(GuestEditReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, " edit_guest", true);*/

    }

    private void callDeleteWS(int position) {
// remove_guest
        itemPosition = position;
        GuestEditReq req = new GuestEditReq();
        req.guestId = guestsLists.get(position).id;

        try {
            obj = Class.forName(GuestEditReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 3, "remove_guest", true);
    }

    private void callToGuest(int position) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + guestsLists.get(position).mobile));
        startActivity(callIntent);

    }

    private void showAlertForAddGuests() {
        LayoutInflater inflater = LayoutInflater.from(InviteGuestActivity.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_guests, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(InviteGuestActivity.this).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(true);

        TextView contact, others;
        contact = view1.findViewById(R.id.viewContact);
        others = view1.findViewById(R.id.viewOthers);

        contact.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                iscontactsGranted = AppPermissions.callPermissionForContacts(InviteGuestActivity.this);
                if (iscontactsGranted)
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PICK_CONTACT_SCREEN);
                alertDialog.dismiss();
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_EDIT_GUEST_SCREEN, bundle);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        GuestsReq req = new GuestsReq();

        req.adminId = "2";
        req.otpSentType = "1";
        req.userId = SharedPrefsUtils.getInstance(InviteGuestActivity.this).getId();
        req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);
        try {
            obj = Class.forName(GuestsReq.class.getName()).cast(req);
        } catch (Exception e) {

            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "guests", true);

    }
}
