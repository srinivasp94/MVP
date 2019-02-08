package com.iprismech.alertnikkiresidence.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.iprismech.alertnikkiresidence.R;

import java.util.Calendar;

/**
 * Created by kallo on 11/6/2018.
 */

public class DaysSelction extends Dialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private CheckBox alldays, sunday, monday, tuesday, wednesday, thursday, friday, saturday;
    private TextView tv_save_;
    private int mHour, mMinute;
    Context context;
    String str_open_Time_12hr, str_close_Time_12hr, str_open_Time_24hr, str_close_Time_24hr;
    String result_string = "", sund_str = "", wed_str = "", mond_str = "", tue_str = "", thur_str = "", fri_str = "", sat_str = "";
    String result_days_in_string = "";
    private boolean sunday_status, monday_status, tuesday_status, wednesday_status, thursday_status, friday_status, saturday_status, allday_status;
    private AddKidActivity addKidActivity;

    public DaysSelction(@NonNull Context context, AddKidActivity addKidActivity) {
        super(context);
        this.context = context;
        this.addKidActivity = addKidActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.days_selection_layout);

        alldays = findViewById(R.id.cb_alldays);
        sunday = findViewById(R.id.cb_sunday);
        monday = findViewById(R.id.cb_monday);
        tuesday = findViewById(R.id.cb_tuesday);
        wednesday = findViewById(R.id.cb_wednesday);
        thursday = findViewById(R.id.cb_thursday);
        friday = findViewById(R.id.cb_friday);
        saturday = findViewById(R.id.cb_satdays);

        tv_save_ = findViewById(R.id.tv_save);

        tv_save_.setOnClickListener(this);

//        tv_opening_time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar mcurrentTime = Calendar.getInstance();
//                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = mcurrentTime.get(Calendar.MINUTE);
//                TimePickerDialog mTimePicker;
//                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        int hour = selectedHour;
//                       int  minutes = selectedMinute;
//                        String timeSet = "";
//                        if (hour > 12) {
//                            hour -= 12;
//                            timeSet = "pm";
//                        } else if (hour == 0) {
//                            hour += 12;
//                            timeSet = "pm";
//                        } else if (hour == 12){
//                            timeSet = "am";
//                        }else{
//                            timeSet = "am";
//                        }
//
//                        String min = "";
//                        if (minutes < 10)
//                            min = "0" + minutes ;
//                        else
//                            min = String.valueOf(minutes);
//
//                        // Append in a StringBuilder
//                        str_open_Time_12hr = new StringBuilder().append(hour).append(':')
//                                .append(min ).append(" ").append(timeSet).toString();
//                        tv_opening_time.setText(str_open_Time_12hr);
//                      //  tv_opening_time.setText(selectedHour + ":" + selectedMinute);
//                        str_open_Time_24hr=selectedHour+":"+min+":"+"00";
//                    }
//                }, hour, minute, false);//Yes 24 hour time
//                mTimePicker.setTitle("Select Time");
//                mTimePicker.show();
//            }
//        });
//
//        tv_closing_time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
    //                final Calendar c = Calendar.getInstance();
    //                mHour = c.get(Calendar.HOUR_OF_DAY);
    //                mMinute = c.get(Calendar.MINUTE);
    //
    //                // Launch Time Picker Dialog
//                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//
//
//                                int hour = hourOfDay;
//                                int  minutes = minute;
//                                String timeSet = "";
//                                if (hour > 12) {
//                                    hour -= 12;
//                                    timeSet = "pm";
//                                } else if (hour == 0) {
//                                    hour += 12;
//                                    timeSet = "pm";
//                                } else if (hour == 12){
//                                    timeSet = "am";
//                                }else{
//                                    timeSet = "am";
//                                }
//
//                                String min = "";
//                                if (minutes < 10)
//                                    min = "0" + minutes ;
//                                else
//                                    min = String.valueOf(minutes);
//
//                                // Append in a StringBuilder
//                                str_close_Time_12hr = new StringBuilder().append(hour).append(':')
//                                        .append(min ).append(" ").append(timeSet).toString();
//                                tv_closing_time.setText(str_close_Time_12hr);
//                              //  tv_closing_time.setText(hourOfDay + ":" + minute);
//                                str_close_Time_24hr=hourOfDay+":"+min+":"+"00";
//                            }
//                        }, mHour, mMinute, false);
//                timePickerDialog.show();
//            }
//        });


        alldays.setChecked(false);
        sunday.setChecked(false);
        monday.setChecked(false);
        tuesday.setChecked(false);
        wednesday.setChecked(false);
        thursday.setChecked(false);
        friday.setChecked(false);
        saturday.setChecked(false);

        alldays.setOnCheckedChangeListener(this);
        sunday.setOnCheckedChangeListener(this);
        monday.setOnCheckedChangeListener(this);
        tuesday.setOnCheckedChangeListener(this);
        wednesday.setOnCheckedChangeListener(this);
        thursday.setOnCheckedChangeListener(this);
        friday.setOnCheckedChangeListener(this);
        saturday.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save: {
                if (allday_status == false && sunday_status == false && monday_status == false && tuesday_status == false
                        && wednesday_status == false && thursday_status == false && friday_status == false && saturday_status == false) {
                    Toast.makeText(context, "Please select working days", Toast.LENGTH_LONG).show();
                } else {
                    addKidActivity.resultDialogTimings(result_string);
                    //  vendorShopEditActivity.resultDialogTimings(result_string, tv_opening_time.getText().toString(), tv_closing_time.getText().toString(),str_open_Time_24hr,str_close_Time_24hr);
                    dismiss();
                }
            }
            break;
        }
        Log.d("result_string", result_string);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.cb_alldays: {
                StringBuilder stringBuilder = new StringBuilder(result_string);

                if (b == true) {
                    if (stringBuilder.length() == 0) {
                        stringBuilder.append("All");
                    } else {
                        stringBuilder.delete(0, result_string.length());
                        allday_status = false;

                    }
                    sunday.setChecked(true);
                    monday.setChecked(true);
                    tuesday.setChecked(true);
                    wednesday.setChecked(true);
                    thursday.setChecked(true);
                    friday.setChecked(true);
                    saturday.setChecked(true);
                } else {
                    sunday.setChecked(false);
                    monday.setChecked(false);
                    tuesday.setChecked(false);
                    wednesday.setChecked(false);
                    thursday.setChecked(false);
                    friday.setChecked(false);
                    saturday.setChecked(false);
                    stringBuilder.delete(0, result_string.length());
                }
                result_string = stringBuilder.toString();
            }
            break;
            case R.id.cb_sunday: {
                StringBuilder stringBuilder = new StringBuilder(result_string);
                StringBuilder stringBuilder_int = new StringBuilder(result_days_in_string);

                if (b == true) {
                    sunday_status = true;
                    if (stringBuilder.length() == 0) {
                        stringBuilder.append("Sun");


                    } else {
                        stringBuilder.append(", Sun");
                    }
                } else {
                    sunday_status = false;
                    stringBuilder.toString().replace("Sun", "");
                }
                result_string = stringBuilder.toString();

            }
            break;
            case R.id.cb_monday: {
                StringBuilder stringBuilder = new StringBuilder(result_string);

                if (b == true) {
                    monday_status = true;
                    if (stringBuilder.length() == 0) {
                        stringBuilder.append("Mon");
                    } else {
                        stringBuilder.append(", Mon");
                    }
                } else {
                    monday_status = false;
                    stringBuilder.toString().replace("Mon", "");

                }
                result_string = stringBuilder.toString();

            }
            break;
            case R.id.cb_tuesday: {
                StringBuilder stringBuilder = new StringBuilder(result_string);

                if (b == true) {
                    tuesday_status = true;
                    if (stringBuilder.length() == 0) {
                        stringBuilder.append("Tue");
                    } else {
                        stringBuilder.append(", Tue");
                    }
                } else {
                    tuesday_status = false;
                    stringBuilder.toString().replace("Tue", "");

                }
                result_string = stringBuilder.toString();

            }
            break;
            case R.id.cb_wednesday: {
                StringBuilder stringBuilder = new StringBuilder(result_string);

                if (b == true) {
                    wednesday_status = true;
                    if (stringBuilder.length() == 0) {
                        stringBuilder.append("Wed");
                    } else {
                        stringBuilder.append(", Wed");
                    }
                } else {
                    wednesday_status = false;
                    stringBuilder.toString().replace("Wed", "");


                }
                result_string = stringBuilder.toString();

            }
            break;
            case R.id.cb_thursday: {
                StringBuilder stringBuilder = new StringBuilder(result_string);

                if (b == true) {
                    thursday_status = true;
                    if (stringBuilder.length() == 0) {
                        stringBuilder.append("Thu");
                    } else {
                        stringBuilder.append(", Thu");
                    }
                } else {
                    thursday_status = false;
                    stringBuilder.toString().replace("Thu", "");

                }
                result_string = stringBuilder.toString();

            }
            break;
            case R.id.cb_friday: {
                StringBuilder stringBuilder = new StringBuilder(result_string);

                if (b == true) {
                    friday_status = true;
                    if (stringBuilder.length() == 0) {
                        stringBuilder.append("Fri");
                    } else {
                        stringBuilder.append(", Fri");
                    }
                } else {
                    friday_status = false;
                    stringBuilder.toString().replace("Fri", "");

                }
                result_string = stringBuilder.toString();

            }
            break;
            case R.id.cb_satdays: {
                StringBuilder stringBuilder = new StringBuilder(result_string);

                if (b == true) {
                    friday_status = true;
                    if (stringBuilder.length() == 0) {
                        stringBuilder.append("Sat");
                    } else {
                        stringBuilder.append(", Sat");
                    }
                } else {
                    saturday_status = false;
                    stringBuilder.toString().replace("Sat", "");

                }
                result_string = stringBuilder.toString();

            }
            break;
        }


    }

}
