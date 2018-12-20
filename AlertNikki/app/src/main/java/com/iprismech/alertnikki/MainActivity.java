package com.iprismech.alertnikki;

import android.content.Intent;
import android.drm.DrmStore;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.iprismech.alertnikki.activity.ScannerActivity;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.fragments.AdminStaff_Fragment;
import com.iprismech.alertnikki.fragments.Alerts_Fragment;
import com.iprismech.alertnikki.fragments.DailyHelps_Fragement;
import com.iprismech.alertnikki.fragments.Delivery_Fragment;
import com.iprismech.alertnikki.fragments.HomeFragment;
import com.iprismech.alertnikki.fragments.SchoolBus_Fragment;
import com.iprismech.alertnikki.fragments.Visitors_Fragment;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;

public class MainActivity extends BaseAbstractActivity<Class> implements View.OnClickListener {
    private BottomNavigationView bottomNavigationView, topnavigationview;
    private FragmentManager fragmentManager;
    private TextView txtGate;

    private PopupMenu popupMenu;


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

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        topnavigationview = (BottomNavigationView) findViewById(R.id.bottom_navigationtop);

        txtGate = findViewById(R.id.txtSocietyGate);
        txtGate.setText(SharedPrefsUtils.getString(SharedPrefsUtils.KEY_SOCIETY));

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fm_container, new HomeFragment(), "").commit();


        final TextView textView = (TextView) bottomNavigationView.findViewById(R.id.action_delivery).findViewById(R.id.largeLabel);
        textView.setTextSize(12);

        final TextView textView1 = (TextView) bottomNavigationView.findViewById(R.id.action_dailyhelps).findViewById(R.id.largeLabel);
        textView1.setTextSize(12);
        final TextView textView2 = (TextView) bottomNavigationView.findViewById(R.id.action_school).findViewById(R.id.largeLabel);
        textView2.setTextSize(12);
        final TextView textView3 = (TextView) bottomNavigationView.findViewById(R.id.action_cab).findViewById(R.id.largeLabel);
        textView3.setTextSize(12);
        final TextView textView4 = (TextView) bottomNavigationView.findViewById(R.id.action_more).findViewById(R.id.largeLabel);
        textView3.setTextSize(12);
        final TextView textViewtop_alert = (TextView) topnavigationview.findViewById(R.id.nav_alert).findViewById(R.id.largeLabel);
        textView.setTextSize(12);

        final TextView textViewtop_home = (TextView) topnavigationview.findViewById(R.id.nav_home).findViewById(R.id.largeLabel);
        textView.setTextSize(12);

        final TextView textViewtop_visit = (TextView) topnavigationview.findViewById(R.id.nav_visitors).findViewById(R.id.largeLabel);
        textView.setTextSize(12);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.action_delivery:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new Delivery_Fragment(), "").commit();
                        break;
                    case R.id.action_dailyhelps:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new DailyHelps_Fragement(), "").commit();
                        break;
                    case R.id.action_school:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new SchoolBus_Fragment(), "").commit();
                        break;
                    case R.id.action_cab:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new Delivery_Fragment(), "").commit();
                        break;
                    case R.id.action_more:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new Delivery_Fragment(), "").commit();
                        break;
                }
                return true;
            }
        });
        topnavigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new HomeFragment(), "").commit();
                        break;
                    case R.id.nav_alert:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new Alerts_Fragment(), "").commit();
                        break;
                    case R.id.nav_visitors:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new Visitors_Fragment(), "").commit();
                        break;
                    case R.id.nav_admin_staff:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new AdminStaff_Fragment(), "").commit();
                        break;
                    case R.id.nav_More:

                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                popupMenu = new PopupMenu(MainActivity.this, getView(), Gravity.RIGHT);
                            }
                            popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                                @Override
                                public void onDismiss(PopupMenu menu) {

                                }
                            });
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    switch (menuItem.getItemId()) {
                                        case R.id.nav_logout:
                                            SharedPrefsUtils.logoutUser();
                                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SPLASH_SCREEN);
                                            finish();
                                    }
                                    return true;
                                }
                            });
                            popupMenu.inflate(R.menu.more_menu);
                            popupMenu.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_Scan_Layout:
                //  ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_QRCODE);
                /*FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fm_container,new QrCode_Fragment(),"").commit();*/
                Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
