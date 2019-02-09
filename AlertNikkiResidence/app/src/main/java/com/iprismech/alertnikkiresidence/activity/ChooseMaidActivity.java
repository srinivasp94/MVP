package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.ChooseMaidAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.ChooseMaidPojo;
import com.iprismech.alertnikkiresidence.request.ChooseMaidRequest;
import com.iprismech.alertnikkiresidence.request.StaffRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class ChooseMaidActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private RecyclerView rview_choosemaid;
    private ChooseMaidAdapter chooseMaidAdapter;
    private Object obj;
    private ChooseMaidPojo chooseMaidPojo;
    private LinearLayoutManager manager;
    private RetrofitResponseListener retrofitResponseListener;

    private ImageView imgClose;
    private TextView txtitle;



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        retrofitResponseListener=this;
        rview_choosemaid = findViewById(R.id.rview_choose_maid);
        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Choose Maid");
        imgClose.setOnClickListener(this);

        String service_id = getIntent().getExtras().getString("ServiceID");

        ChooseMaidRequest chooseMaidRequest = new ChooseMaidRequest();
       // chooseMaidRequest.adminId = SharedPrefsUtils.getInstance(ChooseMaidActivity.this).getId();
        chooseMaidRequest.adminId = "2";
        chooseMaidRequest.service_id = service_id;
       // chooseMaidRequest.service_id = "2";

        try {
            obj = Class.forName(ChooseMaidRequest.class.getName()).cast(chooseMaidRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "maids_list", true);
    }


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_choose_maid, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ChooseMaidActivity.this, "Please Try Again");
        } else {
            try {

                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                chooseMaidPojo = gson.fromJson(jsonString, ChooseMaidPojo.class);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    manager = new LinearLayoutManager(ChooseMaidActivity.this);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    rview_choosemaid.setLayoutManager(manager);
                    chooseMaidAdapter=new ChooseMaidAdapter(ChooseMaidActivity.this,chooseMaidPojo);
                    rview_choosemaid.setAdapter(chooseMaidAdapter);
                    chooseMaidAdapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
            }
        }
    }
}
