package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.CityListAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.response.City;
import com.iprismech.alertnikkiresidence.response.CityList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

import java.util.ArrayList;

public class SelectCityActivity extends BaseAbstractActivity implements RetrofitResponseListener {

    private ListView listViewCity;
    private CityListAdapter adapter;
    private ArrayList<CityList> cityLists = new ArrayList<>();
    private Object obj;

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
        try {
            obj = new Object();
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "cities", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(SelectCityActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            City cityresponse = Common.getSpecificDataObject(objectResponse, City.class);
                            cityLists = (ArrayList<CityList>) cityresponse.response;
                            if (cityLists != null && cityLists.size() > 0) {
                                adapter = new CityListAdapter(SelectCityActivity.this, cityLists);
                                listViewCity.setAdapter(adapter);
                            }
                            break;
                    }
                } else {
                    Common.showToast(SelectCityActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
