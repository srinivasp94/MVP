package com.iprismech.alertnikki;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
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
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikki.Pojo.LoginTimePojo;
import com.iprismech.alertnikki.Request.LoginTimeReq;
import com.iprismech.alertnikki.activity.ScannerActivity;
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
import java.util.StringTokenizer;

public class MainActivity extends BaseAbstractActivity<Class> implements View.OnClickListener, RetrofitResponseListener {
    public BottomNavigationView bottomNavigationView, topnavigationview;
    private FragmentManager fragmentManager;
    private TextView txtGate;

    private PopupMenu popupMenu;

    private Object obj;
    private RetrofitResponseListener retrofitResponseListener;


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
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new CabsFragment(), "").commit();
                        break;
                    case R.id.action_more:
                        fragmentManager.beginTransaction().replace(R.id.fm_container, new MoreFragment(), "").commit();
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
                       /* listPopupWindow.show();
                        String[] list = {"My Login Time", "Add data", "Move in", "Move out", "About Us", "Logout", "SOS"};

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
*/
//                        showDialog(MainActivity.this, topnavigationview.getLeft() - (topnavigationview.getRight() * 2), topnavigationview.getTop() + (topnavigationview.getHeight() * 2 * (0 + 2)),0);

                        String[] list = {"My Login Time", "Add data", "Move in", "Move out", "About Us", "Logout", "SOS"};
                        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                                R.layout.simple_list_item, R.id.txt_popupitem, list);*/
                        MoreItemAdapter adapter = new MoreItemAdapter(MainActivity.this, list);

                        DialogPlus dialog = DialogPlus.newDialog(MainActivity.this)
                                .setAdapter(adapter)
                                .setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                        switch (position) {
                                            case 5:
                                                SharedPrefsUtils.logoutUser();
                                                finish();
                                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SPLASH_SCREEN);
                                                dialog.dismiss();
                                                break;
                                            case 0:

                                                LoginTimeReq loginTimeReq = new LoginTimeReq();
                                                loginTimeReq.admin_id = SharedPrefsUtils.getInstance(getApplicationContext()).getAdmin();
                                                loginTimeReq.security_id = String.valueOf(SharedPrefsUtils.getInstance(getApplicationContext()).getsecurityId());
                                                try {
                                                    obj = Class.forName(LoginTimeReq.class.getName()).cast(loginTimeReq);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 1, "my_login_time", true);
                                                dialog.dismiss();
                                                break;
                                            case 2:
                                                fragmentManager.beginTransaction().replace(R.id.fm_container, new MoveInFragment(), "").commit();

                                                break;
                                            case 1:
                                                fragmentManager.beginTransaction().replace(R.id.fm_container, new AddServiceFragment(), "").commit();
                                                dialog.dismiss();
                                                break;
                                            case 4:
//about us
                                                dialog.dismiss();
                                                break;
                                            case 6:
//SOS
                                                dialog.dismiss();
                                                break;
                                            case 3:
                                                fragmentManager.beginTransaction().replace(R.id.fm_container, new MoveOutFragment(), "").commit();
                                                dialog.dismiss();
                                                break;
                                        }
                                    }
                                })
                                .setExpanded(true)
                                .setGravity(Gravity.CENTER)// This will enable the expand feature, (similar to android L share dialog)
                                .create();
                        dialog.show();

/*
                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                popupMenu = new PopupMenu(MainActivity.this, getView(), Gravity.LEFT);
                            }
                            popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                                @Override
                                public void onDismiss(PopupMenu menu) {

                                }
                            });
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    switch (item.getItemId()) {
                                                                   popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    switch (item.getItemId()) {
                                        case R.id.more_logout:
                                            SharedPrefsUtils.logoutUser();
                                            finish();
                                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SPLASH_SCREEN);

                                            break;
                                        case R.id.more_login_time:

                                            LoginTimeReq loginTimeReq=new LoginTimeReq();
                                            loginTimeReq.admin_id=SharedPrefsUtils.getInstance(getApplicationContext()).getAdmin();
                                            loginTimeReq.security_id = String.valueOf(SharedPrefsUtils.getInstance(getApplicationContext()).getsecurityId());
                                            try {
                                                obj = Class.forName(LoginTimeReq.class.getName()).cast(loginTimeReq);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 1, "my_login_time", true);
                                            break;
                                        case R.id.more_move_in:
                                            fragmentManager.beginTransaction().replace(R.id.fm_container, new MoveInFragment(), "").commit();

                                            break;
                                        case R.id.more_add_data:
                                            fragmentManager.beginTransaction().replace(R.id.fm_container, new AddServiceFragment(), "").commit();

                                            break;
                                        case R.id.more_about_us:

                                            break;
                                        case R.id.more_sos:

                                            break;
                                        case R.id.more_move_out:
                                            fragmentManager.beginTransaction().replace(R.id.fm_container, new MoveOutFragment(), "").commit();
                                            break;
                                    }
                                    return true;
                                }
                            });


                                    }
                                    return true;
                                }
                            });
                            popupMenu.inflate(R.menu.more_menu);
                            popupMenu.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
*/

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
        String[] list = {"My Login Time", "Add data", "Move in", "Move out", "About Us", "Logout", "SOS"};
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
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
