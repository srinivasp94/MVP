package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.SocietyListAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.SocietyReq;
import com.iprismech.alertnikkiresidence.response.Society;
import com.iprismech.alertnikkiresidence.response.SocietyList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

import java.util.ArrayList;

public class SelectSocietyActivity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {

    private ListView listViewCity;
    private SocietyListAdapter adapter;
    private Object obj;
    private ArrayList<SocietyList> list = new ArrayList<>();
    private TextView txtNoItems;
    private String cityid;
    private String sOtp, sName, sMail, sPhone, sPassword, sBlood;
    private ImageView imgClose;
    private TextView txtitle;
    private String cityName;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_select_city, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            cityid = bundle.getString("Key_CityId", "");
            cityName= bundle.getString("Key_CityName", "");
//            bundle.putString("Key_CityName", cityLists.get(position).title);

            sOtp = bundle.getString("Key_otp");
            sName = bundle.getString("Key_Name");
            sPhone = bundle.getString("Key_Mobile");
            sMail = bundle.getString("Key_Email");
            sPassword = bundle.getString("Key_Password");
            sBlood = bundle.getString("Key_Blood");
        }
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Select Society");
        imgClose.setOnClickListener(this);

        txtNoItems = findViewById(R.id.txtNoItems);
        listViewCity = findViewById(R.id.listview_city);

        SocietyReq req = new SocietyReq();
        req.cityId = cityid;

        try {
            obj = Class.forName(SocietyReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "societies", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(SelectSocietyActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Society Society = Common.getSpecificDataObject(objectResponse, Society.class);
                            list = (ArrayList<SocietyList>) Society.response;
                            if (list != null && list.size() > 0) {
                                adapter = new SocietyListAdapter(SelectSocietyActivity.this, list);
                                listViewCity.setAdapter(adapter);
                                listViewCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Key_SocietyId", list.get(position).id);
                                        bundle.putString("Key_CityId", cityid);
                                        bundle.putString("Key_AdminId", list.get(position).adminId);
                                        bundle.putString("Key_CityName",cityName);
                                        bundle.putString("Key_SocietyName", list.get(position).title);

                                        bundle.putString("Key_Name", sName);
                                        bundle.putString("Key_Mobile", sPhone);
                                        bundle.putString("Key_Email", sMail);
                                        bundle.putString("Key_Password", sPassword);
                                        bundle.putString("Key_Blood", sBlood);
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_BUILDING_SCREEN, bundle);
                                    }
                                });
                            } else {
                                txtNoItems.setVisibility(View.VISIBLE);
                                listViewCity.setVisibility(View.GONE);
                            }

                            break;
                    }
                } else {
                    Common.showToast(SelectSocietyActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }
}
