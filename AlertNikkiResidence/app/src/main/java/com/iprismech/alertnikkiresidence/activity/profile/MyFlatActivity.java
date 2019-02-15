package com.iprismech.alertnikkiresidence.activity.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.AddSelectFlatActivity;
import com.iprismech.alertnikkiresidence.activity.SelectFlatActivity;
import com.iprismech.alertnikkiresidence.adapters.MyFlatsAdapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.MyFlatsPojo;
import com.iprismech.alertnikkiresidence.request.MyFlatsReq;
import com.iprismech.alertnikkiresidence.request.UpdateFlatReq;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class MyFlatActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private RecyclerView rview_myflats;
    private MyFlatsAdapter myFlatsAdapter;
    private LinearLayoutManager manager;
    private Object obj;
    private ImageView imgClose;
    private TextView txtitle, tv_add_flat;
    private MyFlatsPojo myFlatsPojo;
    private String flat_id;
    private String name;
    private RetrofitResponseListener retrofitResponseListener;


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.tv_add_flat:

                Intent intent_flat = new Intent(MyFlatActivity.this, AddSelectFlatActivity.class);
                intent_flat.putExtra("building_id", SharedPrefsUtils.getString(SharedPrefsUtils.KEY_BUILDING_ID));
                // intent_flat.putExtra("building_id", "2");
                startActivityForResult(intent_flat, 2);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                flat_id = data.getStringExtra("id");
                name = data.getStringExtra("name");
                // tv_flat_number.setText(name);
                Toast.makeText(MyFlatActivity.this, name, Toast.LENGTH_SHORT).show();


                UpdateFlatReq req = new UpdateFlatReq();

                req.userId = SharedPrefsUtils.getInstance(MyFlatActivity.this).getId();
                req.flat_id = flat_id;


                // req.user_id = "24";
                try {
                    obj = Class.forName(UpdateFlatReq.class.getName()).cast(req);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "update_flat", true);


            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_my_flats, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(MyFlatActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            rview_myflats.setLayoutManager(manager);
                            myFlatsPojo = gson.fromJson(jsonString, MyFlatsPojo.class);
                            myFlatsAdapter = new MyFlatsAdapter(MyFlatActivity.this, myFlatsPojo);
                            rview_myflats.setAdapter(myFlatsAdapter);

                            myFlatsAdapter.notifyDataSetChanged();
                            myFlatsAdapter.setOnItemClickListener(new MyFlatsAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {

                                }
                            });
                            break;
                        case 2:
                            Toast.makeText(this, "Flat Added Successfully", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        imgClose.setOnClickListener(this);
        tv_add_flat.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        retrofitResponseListener = this;
        rview_myflats = findViewById(R.id.rview_myflats);
        tv_add_flat = findViewById(R.id.tv_add_flat);
        manager = new LinearLayoutManager(MyFlatActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("My Flats");


        MyFlatsReq req = new MyFlatsReq();

        req.user_id = SharedPrefsUtils.getInstance(MyFlatActivity.this).getId();


        // req.user_id = "24";
        try {
            obj = Class.forName(MyFlatsReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "my_flat", true);

    }
}
