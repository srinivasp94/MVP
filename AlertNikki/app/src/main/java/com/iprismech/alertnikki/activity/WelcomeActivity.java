package com.iprismech.alertnikki.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;

public class WelcomeActivity extends BaseAbstractActivity<Class> implements View.OnClickListener {

    private String img_path;
    private ImageView img_pic;
    private Button btn_Ok;

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
        img_path = getIntent().getStringExtra("Key_Base");
//        Bitmap bitmap = ;
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
                break;
        }
    }
}
