package com.iprismech.alertnikki.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.Login_model;
import com.iprismech.alertnikki.Response.Login;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WelcomeActivity extends BaseAbstractActivity<Class> implements View.OnClickListener {


    private String img_path, mSecurity_Id, mAdmin_Id, mPasscode, mName, mtimeStamp, mSociety;
    private SimpleDateFormat dateFormat;
    private ImageView img_pic;
    private Button btn_Ok;
    private Object obj;
    private TextView securityName, societyGate, logintime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_welcome, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        btn_Ok.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        img_pic = findViewById(R.id.img_pic);
        securityName = findViewById(R.id.txt_securityName);
        societyGate = findViewById(R.id.txt_societyGate);
        logintime = findViewById(R.id.txt_security_loginTime);

        Bundle bundle = getIntent().getExtras();
        img_path = getIntent().getStringExtra("Key_Base");
        if (bundle != null) {
            img_path = getIntent().getStringExtra("Key_Base");
            mSecurity_Id = bundle.getString("Key_Security_Id", "");
            mAdmin_Id = bundle.getString("Key_Admin_Id", "");
            mPasscode = bundle.getString("Key_Passcode", "");
            mName = bundle.getString("Key_Name", "");
            mtimeStamp = bundle.getString("Key_timeStamp", "");
            mSociety = bundle.getString("Key_Society", "");
        }

        String loginTime = new SimpleDateFormat("HH:mm:ss a").format(new Date());

        securityName.setText(mName);
        societyGate.setText(mSociety);
        logintime.setText("" + loginTime);
        Common.commonLogs(WelcomeActivity.this, bundle.toString());

        byte[] decodedString = Base64.decode(img_path, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        img_pic.setImageBitmap(decodedByte);
        Log.d("Base64", img_path);


        btn_Ok = findViewById(R.id.btn_ok);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                finish();
                break;
        }
    }
}
