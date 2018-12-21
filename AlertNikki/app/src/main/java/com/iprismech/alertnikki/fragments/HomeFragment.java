package com.iprismech.alertnikki.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismech.alertnikki.MainActivity;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Request.AllowAdminStaff;
import com.iprismech.alertnikki.Request.AllowDailyHelps;
import com.iprismech.alertnikki.Request.Login_model;
import com.iprismech.alertnikki.Response.Passcodes_Info;
import com.iprismech.alertnikki.activity.ScannerActivity;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikki.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikki.utilities.Common;
import com.iprismech.alertnikki.utilities.Constants;
import com.iprismech.alertnikki.utilities.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class HomeFragment extends BaseAbstractFragment<Class> implements View.OnClickListener, TextWatcher, RetrofitResponseListener {
    private LinearLayout qrcode;
    private EditText edt_text;
    private Object obj;
    RetrofitResponseListener responseListener;
    MainActivity activity;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, null);
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
        qrcode.setOnClickListener(this);
        edt_text.addTextChangedListener(this);
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        qrcode = view.findViewById(R.id.qrcode_Scan_Layout);
        edt_text = view.findViewById(R.id.edt_otp1);
        responseListener = this;
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_Scan_Layout:
                Intent intent = new Intent(getActivity(), ScannerActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 7) {
            String passcode = edt_text.getText().toString();

            Login_model login_model = new Login_model();
            login_model.passcode = passcode;
//                                    login_model.selected_image = "base640";
            try {
                obj = Class.forName(Login_model.class.getName()).cast(login_model);

            } catch (Exception e) {
                e.printStackTrace();
            }
            new RetrofitRequester(this).callPostServices(obj, 1, "passcode", true);
        } else {
            Common.showToast(getActivity(), "Enter Full Passcode");
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getActivity(), "Please Try again");
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
                                showCustomDialog(getActivity(), responseObject);
                            } else if (member_Type.equalsIgnoreCase("user")) {
                                showCustomDialog(getActivity(), responseObject);
                            } else if (member_Type.equalsIgnoreCase("security")) {
                                showCustomDialog(getActivity(), responseObject);
                            } else if (member_Type.equalsIgnoreCase("staff")) {
                                showCustomDialogForAdmin(getActivity(), responseObject);
                            } else {
                                showCustomDialogDailyHelps(getActivity(), responseObject);
                            }
                            /*Bundle bundle = new Bundle();
                            bundle.putString("Key_Security_Id", login.loginData.id);
                            bundle.putString("Key_Admin_Id", login.loginData.adminId);
                            bundle.putString("Key_Passcode", login.loginData.passcode);
                            bundle.putString("Key_Name", login.loginData.name);*/

                            break;
                        case 2:
                            JSONObject jsonhelpsResponse = object.optJSONObject("response");

                            Common.showToast(getActivity(), object.optString("message"));
                            try {
                                FragmentManager manager = getChildFragmentManager();
                                manager.beginTransaction().replace(R.id.fragment_container_layout, new AdminStaff_Fragment(), "AdminStaff").commit();
                                activity.topnavigationview.setSelectedItemId(R.id.nav_admin_staff);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Common.commonLogs(getActivity(), "123Helps" + e.toString());
                            }
                            break;
                        case 3:
                            JSONObject jsonStaffResponse = object.optJSONObject("response");

                            Common.showToast(getActivity(), object.optString("message"));
                            try {
                                FragmentManager manager = getFragmentManager();
                                manager.beginTransaction().replace(R.id.fm_container, new AdminStaff_Fragment(), "AdminStaff").commit();
//                                MainActivity activity = (MainActivity) getActivity();
                                activity.topnavigationview.setSelectedItemId(R.id.nav_admin_staff);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Common.commonLogs(getActivity(), "123Staff" + e.toString());
                            }
                            break;
                    }
                } else {
                    Common.showToast(getActivity(), object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void showCustomDialogDailyHelps(final FragmentActivity activity, final JSONObject responseObject) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_daily_helps_maid, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
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

        tv_Name.setText("" + responseObject.optString(""));
        tv_Role.setText("" + responseObject.optString(""));
        tv_Passcode.setText("" + responseObject.optString(""));
        tv_worksin.setText("" + responseObject.optString(""));

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
                dailyHelpsRequsest.admin_id = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
                dailyHelpsRequsest.security_id = SharedPrefsUtils.getInstance(getActivity()).getsecurityId();
                dailyHelpsRequsest.maid_id = responseObject.optString("id");

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
    }

    private void showCustomDialogForAdmin(FragmentActivity activity, final JSONObject responseObject) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_admin_staff, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
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

        Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + responseObject.optString("image"))
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
                adminStaffRequset.adminId = SharedPrefsUtils.getInstance(getActivity()).getAdmin();
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

    public void showCustomDialog(Context context, final JSONObject jsonObject) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
        View view1 = inflater.inflate(R.layout.alert_show_info, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
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

        Picasso.with(getActivity()).load(Constants.BASE_IMAGE_URL + jsonObject.optString("image")).error(R.drawable.dummy).into(userDp);


        alertDialog.show();

    }
}
