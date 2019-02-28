package com.iprismech.alertnikkiresidence.activity.profile;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.FamilyAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.FamilyDelete;
import com.iprismech.alertnikkiresidence.request.ProfileReq;
import com.iprismech.alertnikkiresidence.response.Family;
import com.iprismech.alertnikkiresidence.response.FamilyList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.AlertUtils;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class FamilyMembersActivity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {

    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private RecyclerView rvSchools;
    private ImageView fab;
    private TextView txtAddFamily;
    private Object obj;
    LinearLayoutManager layoutManager;
    ArrayList<FamilyList> familyLists = new ArrayList<>();
    private FamilyAdapter familyAdapter;

    private ImageView imgClose;
    private TextView txtitle;
    int itemPOsition = 0;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_family_member);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_add_family_member, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtAddFamily.setOnClickListener(this);
        fab.setOnClickListener(this);
        imgClose.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        layoutManager = new LinearLayoutManager(FamilyMembersActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Family Members");

        linearLayout = findViewById(R.id.lLayout);
        relativeLayout = findViewById(R.id.rLayoutSchools);
        rvSchools = findViewById(R.id.rvSchools);
        rvSchools.setLayoutManager(layoutManager);

        fab = findViewById(R.id.fab);
        txtAddFamily = findViewById(R.id.txtAddFamily);

        ProfileReq req = new ProfileReq();
        req.userId = SharedPrefsUtils.getInstance(FamilyMembersActivity.this).getId();
        try {
            obj = Class.forName(ProfileReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, " family_member_list", true);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(FamilyMembersActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")==true) {
                    switch (requestId) {
                        case 1:
                            Family family = Common.getSpecificDataObject(objectResponse, Family.class);
                            familyLists = (ArrayList<FamilyList>) family.response;
                            if (familyLists != null && familyLists.size() > 0) {
                                relativeLayout.setVisibility(View.VISIBLE);
                                linearLayout.setVisibility(View.GONE);
                                familyAdapter = new FamilyAdapter(FamilyMembersActivity.this, familyLists);
                                rvSchools.setAdapter(familyAdapter);
                                familyAdapter.setOnItemClickListener(new FamilyAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, final int position) {
                                        AlertUtils.showSimpleAlert(FamilyMembersActivity.this, "Are You sure want to delete Family Member ?"
                                                , "Alert Message", "Delete", "cancel", new AlertUtils.onClicklistners() {
                                                    @Override
                                                    public void onpositiveclick() {
                                                        deleteFamilyWS(position);
                                                    }

                                                    @Override
                                                    public void onNegativeClick() {

                                                    }
                                                });

                                    }
                                });
                            } else {
                                relativeLayout.setVisibility(View.VISIBLE);
                                linearLayout.setVisibility(View.GONE);
                            }
                            break;
                        case 2:
//                            familyAdapter.notifyDataSetChanged();
                            if (familyLists.size() > 1) {
                                familyLists.remove(itemPOsition);
                                familyAdapter.notifyDataSetChanged();
                            } else {
                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FAMILY_SCREEN);
                                finish();
                            }
                    }
                } else {
                    Common.showToast(FamilyMembersActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void deleteFamilyWS(int position) {
        itemPOsition = position;
        FamilyDelete req = new FamilyDelete();
        req.member_id = familyLists.get(position).id;
        try {
            obj = Class.forName(FamilyDelete.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 2, "family_member_delete", true);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtAddFamily:
                Bundle bundle = new Bundle();
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_FAMILY_SCREEN, bundle);
                finish();
                break;
            case R.id.fab:
                Bundle bundle1 = new Bundle();
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_FAMILY_SCREEN, bundle1);
                finish();
                break;
            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }
}
