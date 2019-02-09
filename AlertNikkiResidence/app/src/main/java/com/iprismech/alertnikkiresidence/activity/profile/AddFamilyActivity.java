package com.iprismech.alertnikkiresidence.activity.profile;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.request.AddFamily;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class AddFamilyActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private TextView selectContact, txtaddsave;
    private EditText edtBloodGrp, edtRelation, edtfamilyPhn, edtfamilyName;
    private String name, phn, realtion, bldGrp;
    private Object obj;
    private String strName = "", strNumber = "";

    private ImageView imgClose;
    private 	TextView txtitle;






    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_family);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_add_family, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        selectContact.setOnClickListener(this);
        txtaddsave.setOnClickListener(this);
        imgClose.setOnClickListener(this);


    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            strName = bundle.getString("Key_Name", "");
            strNumber = bundle.getString("Key_Number", "");

        }
        txtitle = findViewById(R.id.txtitle);
        imgClose= findViewById(R.id.imgClose);
        txtitle.setText("Add Fammily ");

        selectContact = findViewById(R.id.selectContact);
        txtaddsave = findViewById(R.id.txtaddsave);
        edtfamilyName = findViewById(R.id.edtfamilyName);
        edtfamilyPhn = findViewById(R.id.edtfamilyPhn);
        edtRelation = findViewById(R.id.edtRelation);
        edtBloodGrp = findViewById(R.id.edtBloodGrp);
        if (!TextUtils.isEmpty(strName))
            edtfamilyName.setText(strName);
        if (!TextUtils.isEmpty(strNumber))
            edtfamilyPhn.setText(strNumber);


    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.txtaddsave:
                name = edtfamilyName.getText().toString();
                phn = edtfamilyPhn.getText().toString();
                realtion = edtRelation.getText().toString();
                bldGrp = edtBloodGrp.getText().toString();
                if (name.length() == 0 && phn.length() == 0 && realtion.length() == 0 && bldGrp.length() == 0) {
                    Common.showToast(AddFamilyActivity.this, "Please Enter all Details");
                } else if (phn.length() < 10) {
                    Common.showToast(AddFamilyActivity.this, "Please Enter Mobile");
                } else if (realtion.length() == 0) {
                    Common.showToast(AddFamilyActivity.this, "Please Enter Relation");
                } else if (bldGrp.length() == 0) {
                    Common.showToast(AddFamilyActivity.this, "Please Enter Blood Group");
                } else {
                    AddFamily req = new AddFamily();
                    req.userId = SharedPrefsUtils.getInstance(AddFamilyActivity.this).getId();
                    req.name = name;
                    req.mobile = phn;
                    req.relation = realtion;
                    req.bloodGroup = bldGrp;
                    try {
                        obj = Class.forName(AddFamily.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "register_family_member", true);
                }

                break;
            case R.id.selectContact:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_OONTACT_SINGLE_SCREEN);
                break;
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(AddFamilyActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Common.showToast(AddFamilyActivity.this, jsonObject.optString("message"));
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(AddFamilyActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
