package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

public class SplashScreenActivity extends BaseAbstractActivity<Class> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_splash_screen, null);
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
        new Handler().postDelayed(new Runnable() {

            public void run() {
                try {
                    if (SharedPrefsUtils.getInstance(SplashScreenActivity.this).isUserLoggedIn()) {
// main screem
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                        finish();
                    } else {
//IntroScreem
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_INTRO_SCREEN);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, 3000);


    }
}
