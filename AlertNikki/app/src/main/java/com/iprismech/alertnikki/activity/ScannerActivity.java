package com.iprismech.alertnikki.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.Result;
import com.iprismech.alertnikki.MainActivity;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AllowAdminStaff;
import com.iprismech.alertnikki.Request.AllowDailyHelps;
import com.iprismech.alertnikki.Request.Login_model;
import com.iprismech.alertnikki.Response.AlertsCommon;
import com.iprismech.alertnikki.fragments.AdminStaff_Fragment;
import com.iprismech.alertnikki.fragments.DailyHelps_Fragement;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;


import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, RetrofitResponseListener {

    private Button button;
    private ZXingScannerView mScannerView;
    private Object obj;
    private RetrofitResponseListener responseListener;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mScannerView = new ZXingScannerView(ScannerActivity.this);
        setContentView(mScannerView);
        responseListener = this;
        activity = new MainActivity();

    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        String qr_result = result.getText();
        if (isInteger(qr_result)) {
            Login_model login_model = new Login_model();
            login_model.passcode = qr_result;
//                                    login_model.selected_image = "base640";
            try {
                obj = Class.forName(Login_model.class.getName()).cast(login_model);

            } catch (Exception e) {
                e.printStackTrace();
            }
            new RetrofitRequester(this).callPostServices(obj, 1, "passcode", true);
        } else {
            Common.showToast(ScannerActivity.this, "No Passcode Found");
        }


        AlertDialog alert1 = builder.create();
//        alert1.show();

        // If you would like to resume scanning, call this method below:
        mScannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(ScannerActivity.this, "Please Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            JSONObject responseObject = object.optJSONObject("response");
                            String member_Type = responseObject.optString("member_type");

// Member Type --> 1.family_member 2.user 3. security 4.staff 5.Miad

                            if (member_Type.equalsIgnoreCase("family_member")) {
                                showCustomDialog(ScannerActivity.this, responseObject);
                            } else if (member_Type.equalsIgnoreCase("user")) {
                                showCustomDialog(ScannerActivity.this, responseObject);
                            } else if (member_Type.equalsIgnoreCase("security")) {
                                showCustomDialog(ScannerActivity.this, responseObject);
                            } else if (member_Type.equalsIgnoreCase("staff")) {
//                                showCustomDialogForAdmin(ScannerActivity.this, responseObject);
                                Common.showToast(ScannerActivity.this, "Staff member");
                                finish();
                            } else {
//                                showCustomDialogDailyHelps(ScannerActivity.this, responseObject);
                                Common.showToast(ScannerActivity.this, "Daily Helps");
                                finish();
                            }
                            break;

                        case 2:
                            JSONObject jsonhelpsResponse = object.optJSONObject("response");

                            Common.showToast(ScannerActivity.this, object.optString("message"));
                            try {
                                finish();
                                FragmentManager manager = getSupportFragmentManager();
                                manager.beginTransaction().replace(R.id.fm_container, new DailyHelps_Fragement(), "DailyHelps").commit();
                                activity.bottomNavigationView.setSelectedItemId(R.id.action_dailyhelps);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Common.commonLogs(ScannerActivity.this, "123Helps" + e.toString());
                            }
                            break;
                        case 3:
                            JSONObject jsonStaffResponse = object.optJSONObject("response");

                            Common.showToast(ScannerActivity.this, object.optString("message"));
                            try {
                                finish();
                                FragmentManager manager = getSupportFragmentManager();
                                manager.beginTransaction().replace(R.id.fm_container, new AdminStaff_Fragment(), "AdminStaff").commit();
//                                MainActivity activity = (MainActivity) ScannerActivity.this;
                                // activity.topnavigationview.setSelectedItemId(R.id.nav_admin_staff);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Common.commonLogs(ScannerActivity.this, "123Staff" + e.toString());
                            }
                            break;

                    }

                } else {
                    Common.showToast(ScannerActivity.this, object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showCustomDialog(Context context, final JSONObject jsonObject) {
        LayoutInflater inflater = LayoutInflater.from(ScannerActivity.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_show_info, null);

        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ScannerActivity.this).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);

        TextView tv_Name, tv_blood, tv_passcode, tv_address, tv_usertype;
        TextView bt_allow, bt_Ok;

        tv_Name = view1.findViewById(R.id.tv_helper_name);
        tv_blood = view1.findViewById(R.id.tv_Blood_Grp);
        tv_passcode = view1.findViewById(R.id.tv_helper_passcode);
        tv_address = view1.findViewById(R.id.tv_userAddress);
        tv_usertype = view1.findViewById(R.id.tv_user_Type);

        bt_allow = view1.findViewById(R.id.tv_allow_helper);
        bt_Ok = view1.findViewById(R.id.tv_ok);
        ImageView userDp = view1.findViewById(R.id.img_userDP);

        bt_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
                finish();
            }
        });
        if (jsonObject.optString("member_type").equalsIgnoreCase("security")) {

            tv_address.setVisibility(View.GONE);
          /*  tv_staticTitle.setText("Role: ");
            tv_blood.setText(jsonObject.optString("member_type"));*/
        }
        tv_Name.setText(jsonObject.optString("name"));
        tv_blood.setText(jsonObject.optString("blood_group"));
        tv_passcode.setText(jsonObject.optString("passcode"));
        tv_usertype.setText(jsonObject.optString("member_type"));
        tv_address.setText(jsonObject.optString("flat") + ", " + jsonObject.optString("building")
                + ", " + jsonObject.optString("society") + ", " + jsonObject.optString("city"));
//        building  society        city

        Picasso.with(ScannerActivity.this).load(Constants.BASE_IMAGE_URL + jsonObject.optString("image")).error(R.drawable.dummy).into(userDp);


        alertDialog.show();

    }

/*    private void showCustomDialogForAdmin(FragmentActivity activity, final JSONObject responseObject) {

        LayoutInflater inflater = LayoutInflater.from(ScannerActivity.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_admin_staff, null);

        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ScannerActivity.this).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);

        TextView entryTime, tv_staffName, tv_staffRole, tv_staffPasscode, tv_society, bt_deny, btn_allow;
        ImageView staffPic;

        entryTime = view1.findViewById(R.id.tv_admin_staff_entry_timings);
        tv_staffName = view1.findViewById(R.id.tv_admin_staff_name);
        tv_staffRole = view1.findViewById(R.id.tv_admin_staff_desig);
        tv_staffPasscode = view1.findViewById(R.id.tv_helper_passcode);
        tv_society = view1.findViewById(R.id.tv_satff_works_in);
        bt_deny = view1.findViewById(R.id.tv_deny_staff);
        btn_allow = view1.findViewById(R.id.tv_allow_staff);

        staffPic = view1.findViewById(R.id.img_helper_pic);

        entryTime.setText("Entry: " + responseObject.optString("in_time") + ", "
                + responseObject.optString("out_time"));
        tv_staffName.setText(responseObject.optString("name"));
        tv_staffRole.setText(responseObject.optString("designation"));
        tv_staffPasscode.setText(responseObject.optString("passcode"));
        tv_society.setText(responseObject.optString("society"));

        Picasso.with(ScannerActivity.this).load(Constants.BASE_IMAGE_URL + responseObject.optString("image"))
                .error(R.drawable.dummy).into(staffPic);

        bt_deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // admin id,id as parameters

                AllowAdminStaff adminStaffRequset = new AllowAdminStaff();
                adminStaffRequset.adminId = SharedPrefsUtils.getInstance(ScannerActivity.this).getAdmin();
                adminStaffRequset.staffId = responseObject.optString("id");
                try {
                    obj = Class.forName(AllowAdminStaff.class.getName()).cast(adminStaffRequset);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(responseListener).callPostServices(obj, 3, "allow_admin_staff", true);
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void showCustomDialogDailyHelps(final FragmentActivity activity, final JSONObject responseObject) {
        LayoutInflater inflater = LayoutInflater.from(ScannerActivity.this);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_daily_helps_maid, null);

        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ScannerActivity.this).create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setView(view1);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);

        TextView tv_Name, tv_Role, tv_Passcode, tv_worksin, bt_deny, btn_allow;
        ImageView HelperPic;

        tv_Name = view1.findViewById(R.id.tv_helper_name);
        tv_Role = view1.findViewById(R.id.tv_helper_desig);
        tv_Passcode = view1.findViewById(R.id.tv_helper_passcode);
        tv_worksin = view1.findViewById(R.id.tv_works_flat);
        bt_deny = view1.findViewById(R.id.tv_deny_helper);
        btn_allow = view1.findViewById(R.id.tv_allow_helper);

        HelperPic = view1.findViewById(R.id.img_helper_pic);

        tv_Name.setText("" + responseObject.optString("title"));
        tv_Role.setText("" + responseObject.optString("member_type"));
        tv_Passcode.setText("" + responseObject.optString("passcode"));
        tv_worksin.setText("" + responseObject.optString("flats"));

        bt_deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                AllowDailyHelps dailyHelpsRequsest = new AllowDailyHelps();
                dailyHelpsRequsest.admin_id = SharedPrefsUtils.getInstance(ScannerActivity.this).getAdmin();
                dailyHelpsRequsest.security_id = SharedPrefsUtils.getInstance(ScannerActivity.this).getsecurityId();
                dailyHelpsRequsest.maid_id = responseObject.optString("maid_id");

                try {
                    obj = Class.forName(AllowDailyHelps.class.getName()).cast(dailyHelpsRequsest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(responseListener).callPostServices(obj, 2, "allow_daily_helps", true);
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }*/


}
