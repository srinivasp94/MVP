package com.iprismech.alertnikki;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.LoginTimePojo;
import com.iprismech.alertnikki.Request.AdminStaff;
import com.iprismech.alertnikki.Request.LoginTimeReq;
import com.iprismech.alertnikki.Request.UpdateDeviceToken;
import com.iprismech.alertnikki.activity.DummyActivity;
import com.iprismech.alertnikki.activity.ScannerActivity;
import com.iprismech.alertnikki.activity.SplashScreenActivity;
import com.iprismech.alertnikki.adapters.MoreItemAdapter;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.base.BaseAbstractActivity;
import com.iprismech.alertnikki.fragments.AddServiceFragment;
import com.iprismech.alertnikki.fragments.AdminStaff_Fragment;
import com.iprismech.alertnikki.fragments.Alerts_Fragment;
import com.iprismech.alertnikki.fragments.CabsFragment;
import com.iprismech.alertnikki.fragments.DailyHelps_Fragement;
import com.iprismech.alertnikki.fragments.Delivery_Fragment;
import com.iprismech.alertnikki.fragments.HomeFragment;
import com.iprismech.alertnikki.fragments.MoreFragment;
import com.iprismech.alertnikki.fragments.MoveInFragment;
import com.iprismech.alertnikki.fragments.MoveOutFragment;
import com.iprismech.alertnikki.fragments.SchoolBus_Fragment;
import com.iprismech.alertnikki.fragments.Visitors_Fragment;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends BaseAbstractActivity<Class> implements View.OnClickListener, RetrofitResponseListener {
    public BottomNavigationView bottomNavigationView, topnavigationview;
    private FragmentManager fragmentManager;
    private SharedPrefsUtils sharedPrefsUtils;
    private TextView txtGate, badge_alerts, badgeVisitors;
    private LinearLayout ll_top_keypad, ll_top_alerts, ll_top_visitors,
            ll_top_adminstaff, ll_top_more, ll_bottom_delivery, ll_bottom_dailt_helps, ll_bottom_schoolbus,
            ll_bottom_cab, ll_bottom_more;

    private ImageView iv_top_keypad, iv_top_alerts, iv_top_visitors,
            iv_top_adminstaff, iv_top_more, iv_bottom_delivery, iv_bottom_dailt_helps, iv_bottom_schoolbus,
            iv_bottom_cab, iv_bottom_more;
    private TextView tv_top_keypad, tv_top_alerts, tv_top_visitors,
            tv_top_adminstaff, tv_top_more, tv_bottom_delivery, tv_bottom_dailt_helps, tv_bottom_schoolbus,
            tv_bottom_cab, tv_bottom_more;
    private PopupMenu popupMenu;

    private Object obj;
    private RetrofitResponseListener retrofitResponseListener;
    private boolean selection_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        String key = getIntent().getStringExtra("key");

        if (key != null) {

            // Here we can decide what do to -- perhaps load other parameters from the intent extras such as IDs, etc
            if (key.equalsIgnoreCase("alerts") == true) {
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fm_container, new Alerts_Fragment(), "Alerts").commit();
            } else if (key.equalsIgnoreCase("visitors") == true) {
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fm_container, new Visitors_Fragment(), "Visitors").commit();
            }
        } else {
            // Activity was not launched with a menuFragment selected -- continue as if this activity was opened from a launcher (for example)

        }

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
        sharedPrefsUtils = new SharedPrefsUtils(getApplicationContext());
        fragmentManager = getSupportFragmentManager();
        // bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //topnavigationview = (BottomNavigationView) findViewById(R.id.bottom_navigationtop);

        ll_top_keypad = findViewById(R.id.ll_top_keypad);
        ll_top_adminstaff = findViewById(R.id.ll_top_adminstaff);
        ll_top_visitors = findViewById(R.id.ll_top_visitors);
        ll_top_alerts = findViewById(R.id.ll_top_alerts);
        ll_top_more = findViewById(R.id.ll_top_more);

        ll_bottom_cab = findViewById(R.id.ll_bottom_cab);
        ll_bottom_dailt_helps = findViewById(R.id.ll_bottom_dailyhelps);
        ll_bottom_delivery = findViewById(R.id.ll_bottom_delivery);
        ll_bottom_schoolbus = findViewById(R.id.ll_bottom_schoolbus);
        ll_bottom_more = findViewById(R.id.ll_bottom_more);


        iv_top_keypad = findViewById(R.id.iv_top_keypad);
        iv_top_adminstaff = findViewById(R.id.iv_top_admistaff);
        iv_top_visitors = findViewById(R.id.iv_top_visitors);
        iv_top_alerts = findViewById(R.id.iv_top_alerts);
        iv_top_more = findViewById(R.id.iv_top_more);

        iv_bottom_cab = findViewById(R.id.iv_bottom_cab);
        iv_bottom_dailt_helps = findViewById(R.id.iv_bottom_dailyhelps);
        iv_bottom_delivery = findViewById(R.id.iv_bottom_delivery);
        iv_bottom_schoolbus = findViewById(R.id.iv_bottomschoolbus);
        iv_bottom_more = findViewById(R.id.iv_bottom_more);


        tv_top_keypad = findViewById(R.id.tv_top_keypad);
        tv_top_adminstaff = findViewById(R.id.tv_top_admistaff);
        tv_top_visitors = findViewById(R.id.tv_top_visitors);
        tv_top_alerts = findViewById(R.id.tv_top_alerts);


        tv_bottom_cab = findViewById(R.id.tv_bottom_cab);
        tv_bottom_dailt_helps = findViewById(R.id.tv_bottom_dailyhelps);
        tv_bottom_delivery = findViewById(R.id.tv_bottom_delivery);
        tv_bottom_schoolbus = findViewById(R.id.tv_bottomschoolbus);
        tv_bottom_more = findViewById(R.id.tv_bottom_more);

        badgeVisitors = findViewById(R.id.badge_notification_visitors);
        badge_alerts = findViewById(R.id.badge_notification_alerts);


        ll_top_more.setOnClickListener(this);
        ll_top_alerts.setOnClickListener(this);
        ll_top_adminstaff.setOnClickListener(this);
        ll_top_visitors.setOnClickListener(this);
        ll_top_keypad.setOnClickListener(this);

        ll_bottom_cab.setOnClickListener(this);
        ll_bottom_more.setOnClickListener(this);
        ll_bottom_schoolbus.setOnClickListener(this);
        ll_bottom_delivery.setOnClickListener(this);
        ll_bottom_dailt_helps.setOnClickListener(this);


        retrofitResponseListener = this;
      /*  try {

            BottomNavigationMenuView bottomNavigationMenuView =
                    (BottomNavigationMenuView) topnavigationview.getChildAt(0);
            View v = bottomNavigationMenuView.getChildAt(3);
            BottomNavigationItemView itemView = (BottomNavigationItemView) v;

            View badge = LayoutInflater.from(this)
                    .inflate(R.layout.notification_badge, bottomNavigationMenuView, false);

            itemView.addView(badge);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        txtGate = findViewById(R.id.txtSocietyGate);
        txtGate.setText(SharedPrefsUtils.getString(SharedPrefsUtils.KEY_SOCIETY));
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();


        UpdateDeviceToken updateDeviceToken = new UpdateDeviceToken();
        updateDeviceToken.security_id = String.valueOf(SharedPrefsUtils.getInstance(getApplicationContext()).getsecurityId());
        //updateDeviceToken.security_id = "1";
        updateDeviceToken.token = refreshedToken;
        try {
            obj = Class.forName(UpdateDeviceToken.class.getName()).cast(updateDeviceToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 2, "update_security_device_token", true);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fm_container, new HomeFragment(), "").commit();


        AdminStaff req = new AdminStaff();
        req.adminId = SharedPrefsUtils.getInstance(this).getAdmin();
        try {
            obj = Class.forName(AdminStaff.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 11, "visitors_count", false);
        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 12, "alerts_count", true);
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
            case R.id.ll_top_adminstaff:
                tv_bottom_cab.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.white));


                tv_top_keypad.setTextColor(getResources().getColor(R.color.gray));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.black));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.gray));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.gray));


                iv_top_keypad.setImageResource(R.drawable.ic_keypad);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admistaff_active);
                iv_top_visitors.setImageResource(R.drawable.ic_visitor);
                iv_top_alerts.setImageResource(R.drawable.ic_bell);


                iv_bottom_cab.setImageResource(R.drawable.ic_cab);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps);
                iv_bottom_delivery.setImageResource(R.drawable.ic_delivery);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_school_bus);
                iv_bottom_more.setImageResource(R.drawable.ic_bottom_more);


                fragmentManager.beginTransaction().replace(R.id.fm_container, new AdminStaff_Fragment(), "").commit();
                break;
            case R.id.ll_top_keypad:
                tv_bottom_cab.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.white));


                tv_top_keypad.setTextColor(getResources().getColor(R.color.black));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.gray));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.gray));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.gray));


                iv_top_keypad.setImageResource(R.drawable.ic_keypad_active);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admin_staff);
                iv_top_visitors.setImageResource(R.drawable.ic_visitor);
                iv_top_alerts.setImageResource(R.drawable.ic_bell);

                iv_bottom_cab.setImageResource(R.drawable.ic_cab);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps);
                iv_bottom_delivery.setImageResource(R.drawable.ic_delivery);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_school_bus);
                iv_bottom_more.setImageResource(R.drawable.ic_bottom_more);

                fragmentManager.beginTransaction().replace(R.id.fm_container, new HomeFragment(), "").commit();
                break;
            case R.id.ll_top_more:

                tv_bottom_cab.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.white));


                tv_top_keypad.setTextColor(getResources().getColor(R.color.gray));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.gray));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.gray));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.gray));


                iv_top_keypad.setImageResource(R.drawable.ic_keypad);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admin_staff);
                iv_top_visitors.setImageResource(R.drawable.ic_visitor);
                iv_top_alerts.setImageResource(R.drawable.ic_bell);

                iv_bottom_cab.setImageResource(R.drawable.ic_cab);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps);
                iv_bottom_delivery.setImageResource(R.drawable.ic_delivery);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_school_bus);
                iv_bottom_more.setImageResource(R.drawable.ic_bottom_more);


                String[] list = {"My Login Time", "Add data", "About Us", "Logout", "SOS"};
                Integer[] imageslist = {R.drawable.ic_clock,
                        R.drawable.ic_adddata, R.drawable.ic_about, R.drawable.ic_logout, R.drawable.ic_sos};


                LayoutInflater dialoginflater = LayoutInflater.from(MainActivity.this);
                View dialogview = dialoginflater.inflate(R.layout.top_more_items_layout, null);

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setView(dialogview, 10, 10, 10, 10);
                WindowManager.LayoutParams wlmp = alertDialog.getWindow().getAttributes();
                wlmp.gravity = Gravity.TOP | Gravity.RIGHT;

                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setView(dialogview);
                alertDialog.setCancelable(true);
                LinearLayout ll_mylogintime, ll_adda_data, ll_aboutus, ll_logout, ll_sos;
                ll_mylogintime = dialogview.findViewById(R.id.ll_mylogintime);
                ll_adda_data = dialogview.findViewById(R.id.ll_add_data);
                ll_aboutus = dialogview.findViewById(R.id.ll_aboutus);
                ll_logout = dialogview.findViewById(R.id.ll_logout);
                ll_sos = dialogview.findViewById(R.id.ll_sos);
                ll_mylogintime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


                ll_mylogintime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginTimeReq loginTimeReq = new LoginTimeReq();
                        loginTimeReq.admin_id = SharedPrefsUtils.getInstance(getApplicationContext()).getAdmin();
                        loginTimeReq.security_id = String.valueOf(SharedPrefsUtils.getInstance(getApplicationContext()).getsecurityId());
                        try {
                            obj = Class.forName(LoginTimeReq.class.getName()).cast(loginTimeReq);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 1, "my_login_time", true);

                        alertDialog.dismiss();

                    }
                });


                ll_adda_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new AddServiceFragment(), "").commit();
                        alertDialog.dismiss();
                    }
                });

                ll_aboutus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                ll_logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPrefsUtils.logoutUser();
                        finish();
                        startActivity(new Intent(MainActivity.this, SplashScreenActivity.class));

                        alertDialog.dismiss();
                    }
                });

                ll_sos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


                alertDialog.show();
                break;

            case R.id.ll_top_alerts:
                tv_bottom_cab.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.white));


                tv_top_keypad.setTextColor(getResources().getColor(R.color.gray));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.gray));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.gray));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.black));


                iv_top_keypad.setImageResource(R.drawable.ic_keypad);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admin_staff);
                iv_top_visitors.setImageResource(R.drawable.ic_visitor);
                iv_top_alerts.setImageResource(R.drawable.ic_bell_active);

                iv_bottom_cab.setImageResource(R.drawable.ic_cab);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps);
                iv_bottom_delivery.setImageResource(R.drawable.ic_delivery);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_school_bus);
                iv_bottom_more.setImageResource(R.drawable.ic_bottom_more);


                fragmentManager.beginTransaction().replace(R.id.fm_container, new Alerts_Fragment(), "").commit();
                break;
            case R.id.ll_top_visitors:

                tv_bottom_cab.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.white));

                iv_top_keypad.setImageResource(R.drawable.ic_keypad);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admin_staff);
                iv_top_visitors.setImageResource(R.drawable.ic_visitors_acive);
                iv_top_alerts.setImageResource(R.drawable.ic_bell);


                iv_bottom_cab.setImageResource(R.drawable.ic_cab);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps);
                iv_bottom_delivery.setImageResource(R.drawable.ic_delivery);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_school_bus);
                iv_bottom_more.setImageResource(R.drawable.ic_bottom_more);

                tv_top_keypad.setTextColor(getResources().getColor(R.color.gray));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.gray));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.black));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.gray));
                fragmentManager.beginTransaction().replace(R.id.fm_container, new Visitors_Fragment(), "").commit();

                break;

            case R.id.ll_bottom_cab:

                //  iv_bottom_cab.setImageResource(R.drawable.bottom_nav_icon_color_selector);
                tv_bottom_cab.setTextColor(getResources().getColor(R.color.gold));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.white));


                iv_bottom_cab.setImageResource(R.drawable.ic_cab_active);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps);
                iv_bottom_delivery.setImageResource(R.drawable.ic_delivery);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_school_bus);
                iv_bottom_more.setImageResource(R.drawable.ic_bottom_more);

                tv_top_keypad.setTextColor(getResources().getColor(R.color.gray));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.gray));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.gray));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.gray));


                iv_top_keypad.setImageResource(R.drawable.ic_keypad);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admin_staff);
                iv_top_visitors.setImageResource(R.drawable.ic_visitor);
                iv_top_alerts.setImageResource(R.drawable.ic_bell);
                fragmentManager.beginTransaction().replace(R.id.fm_container, new CabsFragment(), "").commit();
                break;
            case R.id.ll_bottom_dailyhelps:
//  iv_bottom_cab.setImageResource(R.drawable.bottom_nav_icon_color_selector);
                tv_bottom_cab.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.gold));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.white));

                iv_bottom_cab.setImageResource(R.drawable.ic_cab);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps_active);
                iv_bottom_delivery.setImageResource(R.drawable.ic_delivery);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_school_bus);
                iv_bottom_more.setImageResource(R.drawable.ic_bottom_more);

                tv_top_keypad.setTextColor(getResources().getColor(R.color.gray));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.gray));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.gray));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.gray));

                iv_top_keypad.setImageResource(R.drawable.ic_keypad);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admin_staff);
                iv_top_visitors.setImageResource(R.drawable.ic_visitor);
                iv_top_alerts.setImageResource(R.drawable.ic_bell);
                fragmentManager.beginTransaction().replace(R.id.fm_container, new DailyHelps_Fragement(), "").commit();
                break;
            case R.id.ll_bottom_delivery:
//                ll_bottom_delivery.setBackgroundResource(R.drawable.bottom_nav_icon_color_selector);
                tv_bottom_cab.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.gold));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.white));

                iv_bottom_cab.setImageResource(R.drawable.ic_cab);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps);
                iv_bottom_delivery.setImageResource(R.drawable.ic_deliver_active);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_school_bus);
                iv_bottom_more.setImageResource(R.drawable.ic_bottom_more);

                tv_top_keypad.setTextColor(getResources().getColor(R.color.gray));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.gray));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.gray));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.gray));
                fragmentManager.beginTransaction().replace(R.id.fm_container, new Delivery_Fragment(), "").commit();

                iv_top_keypad.setImageResource(R.drawable.ic_keypad);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admin_staff);
                iv_top_visitors.setImageResource(R.drawable.ic_visitor);
                iv_top_alerts.setImageResource(R.drawable.ic_bell);

                break;
            case R.id.ll_bottom_more:
//  iv_bottom_cab.setImageResource(R.drawable.bottom_nav_icon_color_selector);
                tv_bottom_cab.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.gold));

                iv_bottom_cab.setImageResource(R.drawable.ic_cab);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps);
                iv_bottom_delivery.setImageResource(R.drawable.ic_delivery);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_school_bus);
                iv_bottom_more.setImageResource(R.drawable.ic_more_active);

                tv_top_keypad.setTextColor(getResources().getColor(R.color.gray));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.gray));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.gray));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.gray));


                iv_top_keypad.setImageResource(R.drawable.ic_keypad);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admin_staff);
                iv_top_visitors.setImageResource(R.drawable.ic_visitor);
                iv_top_alerts.setImageResource(R.drawable.ic_bell);
                fragmentManager.beginTransaction().replace(R.id.fm_container, new MoreFragment(), "").commit();
                break;
            case R.id.ll_bottom_schoolbus:
//  iv_bottom_cab.setImageResource(R.drawable.bottom_nav_icon_color_selector);
                tv_bottom_cab.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_dailt_helps.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_delivery.setTextColor(getResources().getColor(R.color.white));
                tv_bottom_schoolbus.setTextColor(getResources().getColor(R.color.gold));
                tv_bottom_more.setTextColor(getResources().getColor(R.color.white));

                tv_top_keypad.setTextColor(getResources().getColor(R.color.gray));
                tv_top_adminstaff.setTextColor(getResources().getColor(R.color.gray));
                tv_top_visitors.setTextColor(getResources().getColor(R.color.gray));
                tv_top_alerts.setTextColor(getResources().getColor(R.color.gray));

                iv_bottom_cab.setImageResource(R.drawable.ic_cab);
                iv_bottom_dailt_helps.setImageResource(R.drawable.ic_dailyhelps);
                iv_bottom_delivery.setImageResource(R.drawable.ic_delivery);
                iv_bottom_schoolbus.setImageResource(R.drawable.ic_schoolbus_active);
                iv_bottom_more.setImageResource(R.drawable.ic_bottom_more);

                iv_top_keypad.setImageResource(R.drawable.ic_keypad);
                iv_top_adminstaff.setImageResource(R.drawable.ic_admin_staff);
                iv_top_visitors.setImageResource(R.drawable.ic_visitor);
                iv_top_alerts.setImageResource(R.drawable.ic_bell);
                fragmentManager.beginTransaction().replace(R.id.fm_container, new SchoolBus_Fragment(), "").commit();
                break;
        }
    }

    public void showDialog(Context context, int x, int y, final int pos) {
        // x -->  X-Cordinate
        // y -->  Y-Cordinate
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.alert_edit, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        ListView listView = view.findViewById(R.id.list_popup);
        //String[] list = {"My Login Time", "Add data", "Move in", "Move out", "About Us", "Logout", "SOS"};
        String[] list = {"My Login Time", "Add data", "About Us", "Logout", "SOS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP;
        wmlp.x = x;
        wmlp.y = y;


        dialog.show();
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getApplicationContext(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);

                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            JSONObject jsonObject = object.optJSONObject("response");
                            LoginTimePojo loginTimePojo = new Gson().fromJson(jsonString, LoginTimePojo.class);
                            LayoutInflater dialoginflater = LayoutInflater.from(getApplicationContext());
                            View dialogview = dialoginflater.inflate(R.layout.alert_security, null);
                            final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            alertDialog.setView(dialogview);
                            alertDialog.setCancelable(true);
//                            alertDialog.setCancelable(false);
                            TextView ok_submit, security_name, login_time, txt_society;
                            ImageView security_img;

                            ok_submit = dialogview.findViewById(R.id.login_time_Ok);
                            security_name = dialogview.findViewById(R.id.security_name_login);
                            login_time = dialogview.findViewById(R.id.login_time);
                            txt_society = dialogview.findViewById(R.id.txt_society);
                            security_img = dialogview.findViewById(R.id.sec_img_login_time);

                            security_name.setText(loginTimePojo.getResponse().getSecurity_name());
                            login_time.setText(loginTimePojo.getResponse().getIn_time());
                            txt_society.setText(jsonObject.optString("society_name"));
                            String startTime = loginTimePojo.getResponse().getIn_time();
                            StringTokenizer tk = new StringTokenizer(startTime);
                            String time = tk.nextToken();

                            // SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                            SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
                            Date dt;
                            try {
                                dt = sdfs.parse(time);
//                                login_time.setText((CharSequence) dt);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            // login_time.setText(loginTimePojo.getResponse().getIn_time());


                            Picasso.with(getApplicationContext()).load(Constants.BASE_IMAGE_URL + loginTimePojo.getResponse().getImage()).into(security_img);
                            ok_submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });
                            alertDialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
                            alertDialog.show();
                            break;
                        case 11:

//                        {"status":true,"message":"Data fetched Successfully!","visitors_count":8}


                            int visitors_count = object.optInt("visitors_count") + object.optInt("visitors_count_inside");
                            badgeVisitors.setText("" + visitors_count);
                            sharedPrefsUtils.visitor_count(object.optInt("visitors_count_inside"), object.optInt("visitors_count"));

                            break;
                        case 12:
//                        {"status":true,"message":"Data fetched Successfully!","alerts_count":3}

                            badge_alerts.setText("" + object.optInt("alerts_count"));
                            break;

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
