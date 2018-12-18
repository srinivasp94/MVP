package com.iprismech.alertnikki.activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;

public class GuestDetailsActivity extends BaseAbstractActivity<Class> {
    private ImageView img_pic, back;
    private TextView mName, mVehicle, mType, mCheckin, mAllowed, mApproved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_details);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_guest_details, null);
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

        img_pic = findViewById(R.id.image_pics);

        mName = findViewById(R.id.txt_nameOfUser);
        mType = findViewById(R.id.txt_gust_type);
        mVehicle = findViewById(R.id.txt_vehicleNumber);
        mCheckin = findViewById(R.id.image_pics);
        mAllowed = findViewById(R.id.image_pics);
        mApproved = findViewById(R.id.image_pics);
//        back = findViewById(R.id.image_pics);
    }
}
