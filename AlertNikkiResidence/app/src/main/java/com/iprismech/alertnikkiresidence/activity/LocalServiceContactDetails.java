package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.LocalServiceContactsAdapter;
import com.iprismech.alertnikkiresidence.adapters.LocalServicesListAdapetr;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.LocalServiceContactsPojo;
import com.iprismech.alertnikkiresidence.pojo.LocalServicesListPojo;
import com.iprismech.alertnikkiresidence.request.LocalServiceContactsRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;

import org.json.JSONObject;

public class LocalServiceContactDetails extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private RecyclerView rview_local_service_contact;
    private LocalServiceContactDetails localServiceContactDetails;
    private LinearLayoutManager layoutManager;
    private Object obj;
    private LocalServiceContactsPojo localServiceContactsPojo;
    private String service_id;
    private LocalServiceContactsAdapter localServiceContactsAdapter;


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        service_id = getIntent().getExtras().getString("service_id");
        rview_local_service_contact = findViewById(R.id.rview_local_service_conts);
        layoutManager = new LinearLayoutManager(LocalServiceContactDetails.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        LocalServiceContactsRequest req = new LocalServiceContactsRequest();
        req.adminId = "2";
        req.service_id = service_id;
        try {
            obj = Class.forName(LocalServiceContactsRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "service_persons", true);
    }

    @Override
    protected View getView() {

        View view = getLayoutInflater().inflate(R.layout.activity_local_service_contacts, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(LocalServiceContactDetails.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:

                            localServiceContactsPojo = gson.fromJson(jsonString, LocalServiceContactsPojo.class);
                            rview_local_service_contact.setLayoutManager(layoutManager);
                            localServiceContactsAdapter = new LocalServiceContactsAdapter(LocalServiceContactDetails.this, localServiceContactsPojo);
                            rview_local_service_contact.setAdapter(localServiceContactsAdapter);
                            localServiceContactsAdapter.notifyDataSetChanged();
                            break;
                    }
                } else {
                    Common.showToast(LocalServiceContactDetails.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
