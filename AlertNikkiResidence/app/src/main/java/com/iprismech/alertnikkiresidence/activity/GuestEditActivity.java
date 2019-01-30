package com.iprismech.alertnikkiresidence.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;

public class GuestEditActivity extends BaseAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_edit);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_guest_edit,null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ApplicationController.getInstance().setContext(context);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
    }
}
