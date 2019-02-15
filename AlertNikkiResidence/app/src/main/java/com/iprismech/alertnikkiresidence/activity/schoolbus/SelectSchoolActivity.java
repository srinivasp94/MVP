package com.iprismech.alertnikkiresidence.activity.schoolbus;

import android.annotation.SuppressLint;
import android.inputmethodservice.Keyboard;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.SelectSchoolAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.SchoolBusSearchPojo;
import com.iprismech.alertnikkiresidence.request.SchoolBusSearchReq;
import com.iprismech.alertnikkiresidence.request.SearchBusReq;
import com.iprismech.alertnikkiresidence.response.SearchBus;
import com.iprismech.alertnikkiresidence.response.SearchBusList;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectSchoolActivity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {
    private RecyclerView rv_schools;
    private TextView txt_NoItems;
    private LinearLayoutManager layoutManager;
    private Object obj;
    private ArrayList<SearchBusList> busList = new ArrayList();
    private SelectSchoolAdapter schoolAdapter;
    private ImageView fab;
    private SearchView et_search;
    private ImageView imgClose;
    private TextView txtitle;
    private RetrofitResponseListener retrofitResponseListener;
    private SchoolBusSearchPojo schoolBusSearchPojo;
    private ListView searchresults;


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
        View view = getLayoutInflater().inflate(R.layout.activity_select_school, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        fab.setOnClickListener(this);
        imgClose.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        retrofitResponseListener = this;
        ApplicationController.getInstance().setContext(context);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        et_search = findViewById(R.id.et_search);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Select School");
        searchresults = findViewById(R.id.searchresults);

        //search_school_bus
        rv_schools = findViewById(R.id.rv_schools);
        txt_NoItems = findViewById(R.id.txt_NoItems);
        fab = findViewById(R.id.fab);
        rv_schools.setLayoutManager(layoutManager);

        SearchBusReq req = new SearchBusReq();
        req.adminId = SharedPrefsUtils.getInstance(SelectSchoolActivity.this).getAdminID();
        req.schoolBusName = "";
        try {
            obj = Class.forName(SearchBusReq.class.getName()).cast(req);
        } catch (Exception e) {
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "search_school_bus", true);



        et_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() > 2) {
                    SchoolBusSearchReq req_bus_search = new SchoolBusSearchReq();
                    req_bus_search.adminId = "2";
                    req_bus_search.school_bus_name = ""+s;
                    try {
                        obj = Class.forName(SchoolBusSearchReq.class.getName()).cast(req_bus_search);
                    } catch (Exception e) {
                    }
                    new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "search_school_bus", true);

                }
                return false;
            }
        });


//        et_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() > 2) {
//                    SchoolBusSearchReq req_bus_search = new SchoolBusSearchReq();
//                    req_bus_search.adminId = "2";
//                    req_bus_search.school_bus_name = ""+s;
//                    try {
//                        obj = Class.forName(SchoolBusSearchReq.class.getName()).cast(req_bus_search);
//                    } catch (Exception e) {
//                    }
//                    new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "search_school_bus", true);
//
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//
//
//
//            }
//        });


    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(SelectSchoolActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            SearchBus bus = Common.getSpecificDataObject(objectResponse, SearchBus.class);
                            busList = (ArrayList<SearchBusList>) bus.response;
                            if (busList != null && busList.size() > 0) {
                                schoolAdapter = new SelectSchoolAdapter(SelectSchoolActivity.this, busList);
                                rv_schools.setAdapter(schoolAdapter);
                                schoolAdapter.setOnItemClickListener(new SelectSchoolAdapter.OnitemClickListener() {
                                    @SuppressLint("WrongConstant")
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        switch (view.getId()) {
                                            case R.id.rootLayout:
                                                Bundle bundle = new Bundle();
                                                bundle.putString("KEY_BUS_ID", busList.get(position).id);
                                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_BUS_ROUTE_SCREEN, bundle);
                                                break;
                                        }

                                    }
                                });
                            } else {
                                txt_NoItems.setVisibility(View.VISIBLE);
                                rv_schools.setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            et_search.setFocusable(true);
                            schoolBusSearchPojo = gson.fromJson(jsonString, SchoolBusSearchPojo.class);
                            String[] shops = new String[schoolBusSearchPojo.getResponse().size()];
                            for (int i = 0; i < schoolBusSearchPojo.getResponse().size(); i++) {
                                shops[i] = schoolBusSearchPojo.getResponse().get(i).getSchool_bus_name();
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(SelectSchoolActivity.this, R.layout.simple_list1, shops);
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
                                    bundle.putString("KEY_BUS_ID", schoolBusSearchPojo.getResponse().get(position).getId());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_BUS_ROUTE_SCREEN, bundle);
                                }
                            });
                            break;
                    }
                } else {
                    Common.showToast(SelectSchoolActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_BUS_SCREEN);
                break;

            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.et_search:

                break;
        }
    }
}
