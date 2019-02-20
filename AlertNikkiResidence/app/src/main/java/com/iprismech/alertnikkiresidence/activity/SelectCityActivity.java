package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.CityListAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.SearchDailyHelpsPojo;
import com.iprismech.alertnikkiresidence.pojo.SelectCityPojo;
import com.iprismech.alertnikkiresidence.request.SearchDailyHelps;
import com.iprismech.alertnikkiresidence.response.City;
import com.iprismech.alertnikkiresidence.response.CityList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

import java.util.ArrayList;

public class SelectCityActivity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {

    private ListView listViewCity;
    private CityListAdapter adapter;
    private ArrayList<CityList> cityLists = new ArrayList<>();
    private Object obj;
    private TextView txtNoItems;
    private String sOtp, sName, sMail, sPhone, sPassword, sBlood;
    private ImageView imgClose;
    private TextView txtitle;
    private ListView searchresults;
    private RetrofitResponseListener retrofitResponseListener;

    private SearchView et_search;
    private SelectCityPojo selectCityPojo;


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
        retrofitResponseListener = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sOtp = bundle.getString("Key_otp");
            sName = bundle.getString("Key_Name");
            sPhone = bundle.getString("Key_Mobile");
            sMail = bundle.getString("Key_Email");
            sPassword = bundle.getString("Key_Password");
            sBlood = bundle.getString("Key_Blood");
        }
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Select City");
        imgClose.setOnClickListener(this);

        et_search = findViewById(R.id.et_search);
        searchresults = findViewById(R.id.searchresults);

        txtNoItems = findViewById(R.id.txtNoItems);
        listViewCity = findViewById(R.id.listview_city);

        try {
            obj = new Object();
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "cities", true);


        et_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() > 2) {
                    SearchDailyHelps searchDailyHelps = new SearchDailyHelps();
                    searchDailyHelps.adminId = "2";
                    searchDailyHelps.name = "" + s;
                    try {
                        obj = Class.forName(SearchDailyHelps.class.getName()).cast(searchDailyHelps);
                    } catch (Exception e) {
                    }
                    new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "cities_search", true);

                }
                return false;
            }
        });
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
                                listViewCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("Key_CityId", cityLists.get(position).id);
                                        bundle.putString("Key_CityName", cityLists.get(position).title);

                                        bundle.putString("Key_Name", sName);
                                        bundle.putString("Key_Mobile", sPhone);
                                        bundle.putString("Key_Email", sMail);
                                        bundle.putString("Key_Password", sPassword);
                                        bundle.putString("Key_Blood", sBlood);
                                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_SOCIETY_SCREEN, bundle);

                                    }
                                });
                            } else {
                                txtNoItems.setVisibility(View.VISIBLE);
                                listViewCity.setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            selectCityPojo = gson.fromJson(jsonString, SelectCityPojo.class);
                            String[] shops = new String[selectCityPojo.getResponse().size()];
                            for (int i = 0; i < selectCityPojo.getResponse().size(); i++) {
                                shops[i] = selectCityPojo.getResponse().get(i).getTitle();
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(SelectCityActivity.this, R.layout.simple_list1, shops);
                            //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_list1);
                            spinnerArrayAdapter.notifyDataSetChanged();
                            searchresults.setAdapter(spinnerArrayAdapter);
                            spinnerArrayAdapter.notifyDataSetChanged();
                            searchresults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @SuppressLint("WrongConstant")
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Key_CityId", cityLists.get(position).id);
                                    bundle.putString("Key_CityName", cityLists.get(position).title);

                                    bundle.putString("Key_Name", sName);
                                    bundle.putString("Key_Mobile", sPhone);
                                    bundle.putString("Key_Email", sMail);
                                    bundle.putString("Key_Password", sPassword);
                                    bundle.putString("Key_Blood", sBlood);
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SELECT_SOCIETY_SCREEN, bundle);

                                }
                            });

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }
}
