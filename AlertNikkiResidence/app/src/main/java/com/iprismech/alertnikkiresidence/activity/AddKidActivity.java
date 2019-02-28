package com.iprismech.alertnikkiresidence.activity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.AddKidPojo;
import com.iprismech.alertnikkiresidence.pojo.Purpose;
import com.iprismech.alertnikkiresidence.request.EditKidRequest;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddKidActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private EditText kid_name, et_purpose;
    private TextView btn_add_save, tv_selected_days, tv_kid_out_time, tv_kid_in_tim;
    private String str_in_time, str_out_time;
    private Spinner sp_purpse;
    LinearLayout ll_select_days, ll_kid_out_time, ll_kid_in_time;
    //private List<String> mdays = new ArrayList<>();
    private List<Purpose> purposearray = new ArrayList<>();
    private String str_out_time_12hr, str_kid_out_Time_24hr, str_in_time_12hr, str_kid_in_Time_24hr;
    private String replacedString = new String();
    Calendar mcurrentTime = Calendar.getInstance();
    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
    int minute = mcurrentTime.get(Calendar.MINUTE);
    TimePickerDialog mTimePicker;
    private int selected_in_time, selected_out_time;
    private String str_selected_days, screen_type, kid_id = "";
    private Object obj;
    List<String> stringList = new ArrayList<>();
    private Switch sw_kids_pass_days;

    private ImageView imgClose;
    private TextView txtitle;
    private String kid_name_purpose, kid_name_edit, kid_name_days, kid_name_intime, kid_name_outtime;

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_KIDS_NOTIFY_ALERTS);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                break;
            case R.id.ll_select_days:
                DaysSelction cdd = new DaysSelction(context, this);
                cdd.show();
                break;
            case R.id.ll_kid_out_time:
                mTimePicker = new TimePickerDialog(AddKidActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hour = selectedHour;
                        int minutes = selectedMinute;
                        String timeSet = "";
                        if (hour > 12) {
                            hour -= 12;
                            timeSet = "pm";
                        } else if (hour == 0) {
                            hour += 12;
                            timeSet = "pm";
                        } else if (hour == 12) {
                            timeSet = "am";
                        } else {
                            timeSet = "am";
                        }

                        String min = "";
                        if (minutes < 10)
                            min = "0" + minutes;
                        else
                            min = String.valueOf(minutes);

                        // Append in a StringBuilder
                        str_out_time_12hr = new StringBuilder().append(hour).append(':')
                                .append(min).append(" ").append(timeSet).toString();
                        // tv_kid_out_time.setText(str_out_time_12hr);
                        //  tv_opening_time.setText(selectedHour + ":" + selectedMinute);

                        selected_out_time = selectedHour;
                        str_kid_out_Time_24hr = selectedHour + ":" + min + ":" + "00";
                        tv_kid_out_time.setText(str_kid_out_Time_24hr);

                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;
            case R.id.ll_kid_in_time:
                mTimePicker = new TimePickerDialog(AddKidActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hour = selectedHour;
                        int minutes = selectedMinute;
                        String timeSet = "";
                        if (hour > 12) {
                            hour -= 12;
                            timeSet = "pm";
                        } else if (hour == 0) {
                            hour += 12;
                            timeSet = "pm";
                        } else if (hour == 12) {
                            timeSet = "am";
                        } else {
                            timeSet = "am";
                        }

                        String min = "";
                        if (minutes < 10)
                            min = "0" + minutes;
                        else
                            min = String.valueOf(minutes);

                        // Append in a StringBuilder
                        str_in_time_12hr = new StringBuilder().append(hour).append(':')
                                .append(min).append(" ").append(timeSet).toString();
                        //  tv_kid_in_tim.setText(str_in_time_12hr);

                        //  tv_opening_time.setText(selectedHour + ":" + selectedMinute);
                        selected_in_time = selectedHour;
                        str_kid_in_Time_24hr = selectedHour + ":" + min + ":" + "00";
                        tv_kid_in_tim.setText(str_kid_in_Time_24hr);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;
            case R.id.btn_add_save:
                if (kid_name.getText().toString().equalsIgnoreCase("") || kid_name.getText().toString().isEmpty()) {
                    Toast.makeText(AddKidActivity.this, "Please Enter Kid Name", Toast.LENGTH_SHORT).show();
                } else if (et_purpose.getText().toString().equalsIgnoreCase("") || et_purpose.getText().toString().isEmpty()) {
                    Toast.makeText(AddKidActivity.this, "Please Enter Purpose", Toast.LENGTH_SHORT).show();
                } else if (tv_selected_days.getText().toString().equalsIgnoreCase("") || tv_selected_days.getText().toString().isEmpty()) {
                    Toast.makeText(AddKidActivity.this, "Please Add days to send out side", Toast.LENGTH_SHORT).show();
                } else if (tv_kid_out_time.getText().toString().equalsIgnoreCase("") || tv_kid_out_time.getText().toString().isEmpty()) {
                    Toast.makeText(AddKidActivity.this, "Please Select out time", Toast.LENGTH_SHORT).show();
                } else if (tv_kid_in_tim.getText().toString().equalsIgnoreCase("") || tv_kid_in_tim.getText().toString().isEmpty()) {
                    Toast.makeText(AddKidActivity.this, "Please Select in time", Toast.LENGTH_SHORT).show();
                }
//                else if (selected_in_time <= selected_out_time) {
//                    Toast.makeText(AddKidActivity.this, "In time must be greater than out time", Toast.LENGTH_SHORT).show();
//                }
                else {


                    if (screen_type.equalsIgnoreCase("Add kid")) {
                        //  Toast.makeText(AddKidActivity.this, "every thing ok", Toast.LENGTH_SHORT).show();
//                    String replacedString = str_selected_days.replace("Sun", "1");
//                    replacedString.replace("Mon", "2");
//                    replacedString.replace("Tue", "3");
//                    replacedString.replace("Wed", "4");
//                    replacedString.replace("Thu", "5");
//                    replacedString.replace("Fri", "6");
//                    replacedString.replace("Sat", "7");
//                        String replacedString = new String();
//                        replacedString = str_selected_days.replace("Sun", "1").replace("Mon", "2").replace("Tue", "3")
//                                .replace("Wed", "4")
//                                .replace("Thu", "5")
//                                .replace("Fri", "6")
//                                .replace("Sat", "7");
//
//                        String[] mdays = replacedString.split(", ");
//                    Toast.makeText(AddKidActivity.this, replacedString, Toast.LENGTH_SHORT).show();
                        //char[] ggg = replacedString.replace(", ", "").toCharArray();
                        //  Common.commonLogs(AddKidActivity.this, mdays.toString());


                        replacedString = str_selected_days.replace("Sunday", "1").replace("Monday", "2").replace("Tuesday", "3")
                                .replace("Wednesday", "4")
                                .replace("Thursday", "5")
                                .replace("Friday", "6")
                                .replace("Saturday", "7");

                        String[] mdays = replacedString.split(",");
                        stringList = Arrays.asList(mdays);


                        AddKidPojo kidPojo = new AddKidPojo();
                        try {

                            kidPojo.adminId = SharedPrefsUtils.getInstance(AddKidActivity.this).getAdminID();
                            kidPojo.kidName = kid_name.getText().toString();
                            kidPojo.userId = SharedPrefsUtils.getInstance(AddKidActivity.this).getId();

                            kidPojo.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);


                            stringList = Arrays.asList(mdays);
                            Purpose purpose = new Purpose();

                            purpose.inTime = str_kid_in_Time_24hr;
                            purpose.outTime = str_kid_out_Time_24hr;
                            purpose.purpose = et_purpose.getText().toString();
                            purpose.days = stringList;
                            purposearray.add(purpose);

                            kidPojo.purpose = purposearray;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            obj = Class.forName(AddKidPojo.class.getName()).cast(kidPojo);
                        } catch (Exception e) {

                        }
                        new RetrofitRequester(this).callPostServices(obj, 1, "add_kid", true);
//                        for(int i=0;i<mdays.length;i++){
//                            days.add();
//                        }
                        //days.add()
                    } else if (screen_type.equalsIgnoreCase("Edit Kid")) {
                        replacedString = tv_selected_days.getText().toString();
                        replacedString = replacedString.replace("Sunday", "1").replace("Monday", "2").replace("Tuesday", "3")
                                .replace("Wednesday", "4")
                                .replace("Thursday", "5")
                                .replace("Friday", "6")
                                .replace("Saturday", "7");

                        String[] mdays = replacedString.split(",");
                        stringList = Arrays.asList(mdays);

                        EditKidRequest editKidRequest = new EditKidRequest();

                        editKidRequest.in_time = tv_kid_in_tim.getText().toString();
                        editKidRequest.Out_time = tv_kid_out_time.getText().toString();
                        editKidRequest.user_kid_id = kid_id;
                        editKidRequest.days = stringList;
                        editKidRequest.kid_name = kid_name.getText().toString();
                        editKidRequest.purpose = et_purpose.getText().toString();
                        try {
                            obj = Class.forName(EditKidRequest.class.getName()).cast(editKidRequest);
                        } catch (Exception e) {

                        }
                        new RetrofitRequester(this).callPostServices(obj, 2, "edit_kid", true);
                    }
                }
                break;
        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Add Kid");


        kid_name = findViewById(R.id.kid_name_add_kid);
        et_purpose = findViewById(R.id.kid_purpose_add);
        ll_select_days = findViewById(R.id.ll_select_days);
        ll_kid_out_time = findViewById(R.id.ll_kid_out_time);
        ll_kid_in_time = findViewById(R.id.ll_kid_in_time);
        tv_selected_days = findViewById(R.id.tv_selected_days);
        tv_kid_out_time = findViewById(R.id.tv_kid_out_time);
        tv_kid_in_tim = findViewById(R.id.tv_kid_in_time);
        btn_add_save = findViewById(R.id.btn_add_save);
        sw_kids_pass_days = findViewById(R.id.sw_kids_pass_days);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            screen_type = getIntent().getExtras().getString("screen", "");
            kid_id = getIntent().getExtras().getString("kid_id", "");
            kid_name_edit = getIntent().getExtras().getString("kid_name", "");
            kid_name_purpose = getIntent().getExtras().getString("kid_purpose", "");
            kid_name_days = getIntent().getExtras().getString("days", "");
            kid_name_intime = getIntent().getExtras().getString("intime", "");
            kid_name_outtime = getIntent().getExtras().getString("outtime", "");
        }

        if (screen_type.equalsIgnoreCase("Edit Kid")) {
            txtitle.setText("Edit Kid");
            kid_name.setText(kid_name_edit);
            et_purpose.setText(kid_name_purpose);
            tv_selected_days.setText(kid_name_days);
            tv_kid_out_time.setText(kid_name_outtime);
            tv_kid_in_tim.setText(kid_name_intime);
            sw_kids_pass_days.setChecked(true);
        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_select_days.setOnClickListener(this);
        ll_kid_out_time.setOnClickListener(this);
        ll_kid_in_time.setOnClickListener(this);
        btn_add_save.setOnClickListener(this);
        imgClose.setOnClickListener(this);

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_add_kid_details, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(AddKidActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Toast.makeText(AddKidActivity.this, "Kid Added Sucessfully", Toast.LENGTH_LONG).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_KIDS_NOTIFY_ALERTS);
                            finish();
                            break;
                        case 2: {
                            Toast.makeText(AddKidActivity.this, "Kid Data Edited successfully", Toast.LENGTH_LONG).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_KIDS_NOTIFY_ALERTS);
                            finish();
                        }
                    }
                } else {
                    Common.showToast(AddKidActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void resultDialogTimings(String result_string) {
        if (result_string.equalsIgnoreCase("All")) {
            str_selected_days = "Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday";
            tv_selected_days.setText("" + str_selected_days);
        } else {
            str_selected_days = result_string;
            tv_selected_days.setText("" + result_string);
        }



        sw_kids_pass_days.setChecked(true);
    }

}
