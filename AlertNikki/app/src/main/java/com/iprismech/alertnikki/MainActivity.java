package com.iprismech.alertnikki;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikki.activity.ScannerActivity;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.fragments.QrCode_Fragment;

public class MainActivity extends BaseAbstractActivity<Class> implements View.OnClickListener {
    BottomNavigationView bottomNavigationView,topnavigationview;
    private LinearLayout qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }
    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }
    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        qrcode.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        topnavigationview = (BottomNavigationView)findViewById(R.id.bottom_navigationtop);

        qrcode= findViewById(R.id.qrcode_Scan_Layout);
     //   bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        final TextView textView = (TextView) bottomNavigationView.findViewById(R.id.action_home).findViewById(R.id.largeLabel);
        textView.setTextSize(12);
        final TextView textView1 = (TextView) bottomNavigationView.findViewById(R.id.action_news).findViewById(R.id.largeLabel);
        textView1.setTextSize(12);
        final TextView textView2 = (TextView) bottomNavigationView.findViewById(R.id.action_profile).findViewById(R.id.largeLabel);
        textView2.setTextSize(12);
        final TextView textView3 = (TextView) bottomNavigationView.findViewById(R.id.action_notifications).findViewById(R.id.largeLabel);
        textView3.setTextSize(12);
        final TextView textView4 = (TextView) bottomNavigationView.findViewById(R.id.action_settings).findViewById(R.id.largeLabel);
        textView3.setTextSize(12);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_Scan_Layout:
              //  ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_QRCODE);
                /*FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fm_container,new QrCode_Fragment(),"").commit();*/
                Intent intent = new Intent(MainActivity.this,ScannerActivity.class);
               startActivity(intent);
                break;
        }
    }
}
