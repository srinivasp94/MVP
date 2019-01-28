package com.iprismech.alertnikkiresidence.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractFragment;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;

public class HomeFragment extends BaseAbstractFragment<Class> implements View.OnClickListener {
    private LinearLayout layoutInviteGuests, layoutMyStaff, layoutGateAlerts,
            layoutSchoolBus, layoutKidsGate, LayoutLocalServices;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_home_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        layoutInviteGuests.setOnClickListener(this);
        layoutMyStaff.setOnClickListener(this);
                layoutGateAlerts.setOnClickListener(this);
        layoutSchoolBus.setOnClickListener(this);
                layoutKidsGate.setOnClickListener(this);
        LayoutLocalServices.setOnClickListener(this);
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        layoutInviteGuests = view.findViewById(R.id.layoutInviteGuests);
        layoutMyStaff = view.findViewById(R.id.layoutMyStaff);
        layoutGateAlerts = view.findViewById(R.id.layoutGateAlerts);
        layoutSchoolBus = view.findViewById(R.id.layoutSchoolBus);
        layoutKidsGate = view.findViewById(R.id.layoutKidsGate);
        LayoutLocalServices = view.findViewById(R.id.LayoutLocalServices);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutInviteGuests:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_INVITE_GUEST_SCREEN);
                break;
            case R.id.layoutMyStaff:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                break;
            case R.id.layoutGateAlerts:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                break;
            case R.id.layoutSchoolBus:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                break;
            case R.id.layoutKidsGate:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                break;
            case R.id.LayoutLocalServices:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                break;
        }
    }
}
