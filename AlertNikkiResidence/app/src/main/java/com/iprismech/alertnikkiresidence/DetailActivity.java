package com.iprismech.alertnikkiresidence;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.adapters.DetailitemAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.AdminReq;
import com.iprismech.alertnikkiresidence.request.RegisterConfirm;
import com.iprismech.alertnikkiresidence.response.OwnTypeRes;
import com.iprismech.alertnikkiresidence.response.TypleList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private TextView txtCity, txtSociety, txtBlock, txtFlat,
            txttypeowner, txttypeTenent, txtMultitenent, btnAdd;
    private CheckBox checkBox;
    private ImageView imgClose, clsFlat, clsSociety, clsBuilding, clsCity;
    private TextView txtitle;
    private Object obj;
    private String adminId, cityId, societyId, buildingid, flatId;
    private String societyName, cityName, buildingName, flatName;

    private String sOtp, sName, sMail, sPhone, sPassword, sBlood;
    DetailitemAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    private ArrayList<TypleList> list = new ArrayList<>();
    private String typeId, typename = "";

    int nPrevSelGridItem = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_details, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        btnAdd.setOnClickListener(this);
        imgClose.setOnClickListener(this);
        clsCity.setOnClickListener(this);
        clsSociety.setOnClickListener(this);
        clsBuilding.setOnClickListener(this);
        clsFlat.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            adminId = bundle.getString("Key_AdminId", "");
            cityId = bundle.getString("Key_CityID", "");
            societyId = bundle.getString("Key_SocietyId", "");
            buildingid = bundle.getString("Key_BuildingId", "");
            flatId = bundle.getString("Key_id", "");

            societyName = bundle.getString("Key_SocietyName", "");
            cityName = bundle.getString("Key_CityName", "");
            buildingName = bundle.getString("Key_BuildingName", "");
            flatName = bundle.getString("Key_FlatName", "");

            sOtp = bundle.getString("Key_otp");
            sName = bundle.getString("Key_Name");
            sPhone = bundle.getString("Key_Mobile");
            sMail = bundle.getString("Key_Email");
            sPassword = bundle.getString("Key_Password");
            sBlood = bundle.getString("Key_Blood");
        }


        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Details");

        recyclerView = findViewById(R.id.rv_ownTypes);
        manager = new LinearLayoutManager(DetailActivity.this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        AdminReq req = new AdminReq();
        req.adminId = adminId;
        try {
            obj = Class.forName(AdminReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(DetailActivity.this).callPostServices(obj, 1, "residence_types", true);

        txtCity = findViewById(R.id.txtCity);
        txtSociety = findViewById(R.id.txtSociety);
        txtBlock = findViewById(R.id.txtBlock);
        txtFlat = findViewById(R.id.txtFlat);
        txttypeowner = findViewById(R.id.txtOwner);
        txttypeTenent = findViewById(R.id.txttenent);
        txtMultitenent = findViewById(R.id.txtMultiTenent);
        btnAdd = findViewById(R.id.btn_add);
        checkBox = findViewById(R.id.checkboxPrivacy);

        clsCity = findViewById(R.id.imgCloseCity);
        clsSociety = findViewById(R.id.imgClosesociety);
        clsBuilding = findViewById(R.id.imgCloseVlock);
        clsFlat = findViewById(R.id.imgCloseFlat);

        txtCity.setText(cityName);
        txtSociety.setText(societyName);
        txtBlock.setText(buildingName);
        txtFlat.setText(flatName);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                if (checkBox.isChecked()) {
                    getdataFromAdapter(0);
                } else {
                    Toast.makeText(DetailActivity.this, "Please Accept terms & Conditions", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imgClose:
                onBackPressed();
            case R.id.imgCloseCity:
                Bundle bundle = new Bundle();
                bundle.putString("Key_otp", sOtp);
                bundle.putString("Key_Name", sName);
                bundle.putString("Key_Mobile", sPhone);
                bundle.putString("Key_Email", sMail);
                bundle.putString("Key_Password", sPassword);
                bundle.putString("Key_Blood", sBlood);
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_CITY_SCREEN, bundle);
                finish();
                break;
            case R.id.imgClosesociety:
                Bundle bundle1 = new Bundle();
                bundle1.putString("Key_CityId", cityId);
                bundle1.putString("Key_CityName", cityName);

                bundle1.putString("Key_Name", sName);
                bundle1.putString("Key_Mobile", sPhone);
                bundle1.putString("Key_Email", sMail);
                bundle1.putString("Key_Password", sPassword);
                bundle1.putString("Key_Blood", sBlood);
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_SOCIETY_SCREEN, bundle1);
                finish();
                break;
            case R.id.imgCloseVlock:
                Bundle bundle2 = new Bundle();
                bundle2.putString("Key_SocietyId", societyId);
                bundle2.putString("Key_CityId", cityId);
                bundle2.putString("Key_AdminId", adminId);
                bundle2.putString("Key_CityName", cityName);
                bundle2.putString("Key_SocietyName", societyName);

                bundle2.putString("Key_Name", sName);
                bundle2.putString("Key_Mobile", sPhone);
                bundle2.putString("Key_Email", sMail);
                bundle2.putString("Key_Password", sPassword);
                bundle2.putString("Key_Blood", sBlood);
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_BUILDING_SCREEN, bundle2);
                finish();
                break;
            case R.id.imgCloseFlat:
                onBackPressed();
                break;
        }
    }

    private void getdataFromAdapter(int position) {

        RegisterConfirm req = new RegisterConfirm();

        req.name = sName;
        req.mobile = sPhone;
        req.emailId = sMail;
        req.password = sPassword;
        req.otpConfirmed = "Yes";
        req.bloodGroup = sBlood;
        req.adminId = adminId;
        req.cityId = cityId;
        req.societyId = societyId;
        req.buildingId = buildingid;
        req.flatId = flatId;
        req.residenceTypeId = typeId;

        try {
            obj = Class.forName(RegisterConfirm.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "register_user", true);

        //  Common.commonLogs(SelectBuildingActvity.this,title+ " and "+ buildingid);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getApplicationContext(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);

                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            OwnTypeRes res = Common.getSpecificDataObject(objectResponse, OwnTypeRes.class);
                            list = (ArrayList<TypleList>) res.response;
                            adapter = new DetailitemAdapter(DetailActivity.this, list);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new DetailitemAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.txttenent:
                                            View previousCiew;
                                            TextView textView = view.findViewById(R.id.txttenent);

                                            if (list.get(position).isSelect()) {
                                                textView.setBackgroundColor(getResources().getColor(R.color.mainbackgroundhome));
                                            }
//                    adapter.notifyDataSetChanged();
                                            try {
                                                if (nPrevSelGridItem != -1) {
                                                    previousCiew = recyclerView.getChildAt(nPrevSelGridItem);
                                                    previousCiew.findViewById(R.id.txttenent).setBackground(getResources().getDrawable(R.drawable.black_border));
                                                }
                                                nPrevSelGridItem = position;
                                                if (nPrevSelGridItem == position) {
//                                                    textView.setBackground(getResources().getDrawable(R.drawable.black_border));
                                                    textView.setBackgroundColor(getResources().getColor(R.color.mainbackgroundhome));
                                                }

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

//                                            textView.setBackgroundColor(getResources().getColor(R.color.mainbackgroundhome));
                                            typeId = list.get(position).id;
                                            typename = list.get(position).title;
                                            break;
                                    }
                                }
                            });
                            break;
                        case 2:
                            //lead to Detail page
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(DetailActivity.this, object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bundle bundle = new Bundle();
        bundle.putString("Key_id", flatId);
        bundle.putString("Key_AdminId", adminId);
        bundle.putString("Key_SocietyId", societyId);
        bundle.putString("Key_CityId", cityId);
        bundle.putString("Key_BuildingId", buildingid);

        bundle.putString("Key_SocietyName", societyName);
        bundle.putString("Key_CityName", cityName);
        bundle.putString("Key_BuildingName", buildingName);
        bundle.putString("Key_FlatName", flatName);

        bundle.putString("Key_Name", sName);
        bundle.putString("Key_Mobile", sPhone);
        bundle.putString("Key_Email", sMail);
        bundle.putString("Key_Password", sPassword);
        bundle.putString("Key_Blood", sBlood);

        bundle.putString("Key_CityID", cityId);
        bundle.putString("Key_BuildingId", buildingid);
        bundle.putString("Key_FlatId", flatId);
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_FLAT_SCREEN, bundle);
        finish();
    }
}
