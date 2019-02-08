package com.iprismech.alertnikkiresidence.fragments;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.Slidemenu_adapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractFragment;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;

public class HomeFragment extends BaseAbstractFragment<Class> implements View.OnClickListener {
    private LinearLayout layoutInviteGuests, layoutMyStaff, layoutGateAlerts,
            layoutSchoolBus, layoutKidsGate, LayoutLocalServices;
    private String[] slide_menu_txt = {"Profile", "Notifications", "Contact Us", "Alert Security", "Privacy Policy"};
    private int[] icons = {R.drawable.ic_profile, R.drawable.ic_notofocation, R.drawable.ic_notofocation, R.drawable.ic_notofocation, R.drawable.ic_privacy_policy};
    private Slidemenu_adapter slidemenu_adapter;
    private ListView slidemenulistview;
    private DrawerLayout drawer_layout;
    private ImageView menu_icon;

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


        drawer_layout = view.findViewById(R.id.drawer_layout);

        menu_icon = view.findViewById(R.id.iv_memu);
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                    getActivity().getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                } else {
                    drawer_layout.openDrawer(Gravity.LEFT);

                }
            }
        });
        slidemenulistview = view.findViewById(R.id.slidemenulistview);

        slidemenu_adapter = new Slidemenu_adapter(getActivity(), slide_menu_txt, icons);

        slidemenulistview.setAdapter(slidemenu_adapter);

        slidemenulistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {

                    drawer_layout.closeDrawer(Gravity.LEFT);
                } else if (i == 1) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                    //ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_NOTICE_BOARD_SCREEN);
                    //ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_EMERGENCY_CONTACT_SCREEN);
                    //ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MANAGEMENT_COMMITTE_SCREEN);
                    //  ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_DIGITAL_INTERCOM_SCREEN);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VISITORS_HISTORY_SCREEN);

                } else if (i == 2) {

                    drawer_layout.closeDrawer(Gravity.LEFT);
                } else if (i == 3) {

                    drawer_layout.closeDrawer(Gravity.LEFT);
                } else if (i == 4) {

                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutInviteGuests:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_INVITE_GUEST_SCREEN);
                break;
            case R.id.layoutMyStaff:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MYSTAFF_ALERTS);
                break;
            case R.id.layoutGateAlerts:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                break;
            case R.id.layoutSchoolBus:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                break;
            case R.id.layoutKidsGate:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_KIDS_NOTIFY_ALERTS);
                break;
            case R.id.LayoutLocalServices:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOCAL_SERVICE_SCREEN);
                break;
        }
    }
}
