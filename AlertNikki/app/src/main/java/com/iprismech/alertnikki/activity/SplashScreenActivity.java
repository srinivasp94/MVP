package com.iprismech.alertnikki.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

public class SplashScreenActivity<Class> extends BaseAbstractActivity {

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
            @Override
            public void run() {

                if (SharedPrefsUtils.getInstance(SplashScreenActivity.this).isUserLoggedIn()) {
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SECURITY_LOGIN_SCREEN);
                } else {
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                }

            }

        }, 2000);
    }
}
