package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.NoticeBoardAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.NoticeBoardPojo;
import com.iprismech.alertnikkiresidence.request.NoticeBoardRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class NoticeBoardActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private NoticeBoardAdapter noticeBoardAdapter;
    private RecyclerView rview_notice_board;
    private LinearLayoutManager manager;
    private Object obj;
    private NoticeBoardPojo noticeBoardPojo;

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
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_notice_board, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Notice Board");
        imgClose.setOnClickListener(this);


        rview_notice_board = findViewById(R.id.rview_notice_board);
        manager = new LinearLayoutManager(NoticeBoardActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_notice_board.setLayoutManager(manager);


        NoticeBoardRequest req = new NoticeBoardRequest();
        req.adminId =  SharedPrefsUtils.getInstance(NoticeBoardActivity.this).getAdminID();

        //  req.userId = 22;
        try {
            obj = Class.forName(NoticeBoardRequest.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "notice_board", true);

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(NoticeBoardActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            noticeBoardPojo = gson.fromJson(jsonString, NoticeBoardPojo.class);

                            noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this, noticeBoardPojo);
                            rview_notice_board.setAdapter(noticeBoardAdapter);
                            break;
                    }
                } else {
                    Common.showToast(NoticeBoardActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
