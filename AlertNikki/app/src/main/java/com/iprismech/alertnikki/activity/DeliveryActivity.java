package com.iprismech.alertnikki.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.utilities.Common;

public class DeliveryActivity extends BaseAbstractActivity<Class> implements View.OnClickListener {

    private ImageView close, boy_pic, companyPic;
    private TextView title;
    private EditText mName, mMobile, mCompany, mVehicle1, mVehicle2, mVehicle3, mVehicle4, mResidentMobile;
    private Button sendnotify;
    private Spinner spin_Building, spin_Flat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_delivery);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_delivery, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        sendnotify.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        close = findViewById(R.id.img_Close);
        boy_pic = findViewById(R.id.img_deliveryBoy_pic);
        companyPic = findViewById(R.id.img_deliverycompany);

        mName = findViewById(R.id.edt_Name);
        mMobile = findViewById(R.id.edt_Mobile);
        mCompany = findViewById(R.id.edt_DeliveryFrom);
        mVehicle1 = findViewById(R.id.edt_vehicle_first);
        mVehicle2 = findViewById(R.id.edt_vehicle_one);
        mVehicle3 = findViewById(R.id.edt_vehicle_one);
        mVehicle4 = findViewById(R.id.edt_vehicle_two);
        mResidentMobile = findViewById(R.id.edt_vehicle_three);

        spin_Building = findViewById(R.id.spinner_building);
        spin_Flat = findViewById(R.id.spinner_flat);
        sendnotify = findViewById(R.id.btn_sendnotify);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendnotify:
                String sName = mName.getText().toString();
                String smobile = mMobile.getText().toString();
                String scompany = mCompany.getText().toString();
                String sv1 = mVehicle1.getText().toString();
                String sv2 = mVehicle2.getText().toString();
                String sv3 = mVehicle3.getText().toString();
                String sv4 = mVehicle4.getText().toString();
                String sResident = mResidentMobile.getText().toString();

                if (sName.length() == 0 || smobile.length() == 0 || scompany.length() == 0 || sv1.length() == 0 ||
                        sv2.length() == 0 || sv3.length() == 0 || sv4.length() == 0 ||
                        sResident.length() == 0
                        ) {
                    Common.showToast(DeliveryActivity.this, "Please enter all fields");
                } else if (sName.length() == 0 || sName.length() < 6) {
                    Common.showToast(DeliveryActivity.this, "Please enter Name");
                } else if (smobile.length() == 0 || smobile.length() < 10) {
                    Common.showToast(DeliveryActivity.this, "Please enter Valid 10digit Mobile");
                } else if (scompany.length() == 0) {
                    Common.showToast(DeliveryActivity.this, "Please enter Delivery From");
                } else if (sv1.length() == 0 || sv1.length() < 2) {
                    Common.showToast(DeliveryActivity.this, "Please enter vehicle Number");
                } else if (sv2.length() == 0 || sv2.length() < 2) {
                    Common.showToast(DeliveryActivity.this, "Please enter vehicle Number");
                } else if (sv3.length() == 0 || sv3.length() < 2) {
                    Common.showToast(DeliveryActivity.this, "Please enter vehicle Number");
                } else if (sv4.length() == 0 || sv4.length() < 4) {
                    Common.showToast(DeliveryActivity.this, "Please enter vehicle Number");
                } else if (mResidentMobile.length() == 0 || mResidentMobile.length() < 10) {
                    Common.showToast(DeliveryActivity.this, "Please enter Valid 10digit Mobile");
                } else {
                    callWsforSendNotify();
                }


                break;
        }
    }

    private void callWsforSendNotify() {
        /*mName.getText().toString();
        mMobile.getText().toString();
        mCompany.getText().toString();
        mVehicle1.getText().toString();
        mVehicle2.getText().toString();
        mVehicle3.getText().toString();
        mVehicle4.getText().toString();
        mResidentMobile.getText().toString();*/
    }
}

