package com.iprismech.alertnikkiresidence.adapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.ChooseMaidActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.ChooseMaidPojo;
import com.iprismech.alertnikkiresidence.utilities.Constants;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Calendar;

public class ChooseMaidAdapter extends RecyclerView.Adapter<ChooseMaidAdapter.ViewHolder> {
    private Context context;
    private ChooseMaidPojo chooseMaidPojo;
    private boolean add_status = false;
    int mYear, mMonth, mDay;


    public ChooseMaidAdapter(ChooseMaidActivity chooseMaidActivity, ChooseMaidPojo chooseMaidPojo) {
        this.context = chooseMaidActivity;
        this.chooseMaidPojo = chooseMaidPojo;
    }

    @NonNull
    @Override
    public ChooseMaidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_choose_maid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChooseMaidAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_staff_name.setText(chooseMaidPojo.getResponse().get(i).getTitle());
        viewHolder.tv_staff_cat.setText(chooseMaidPojo.getResponse().get(i).getDesignation());
        viewHolder.tv_working_flats.setText(chooseMaidPojo.getResponse().get(i).getWorking_flats() + " flats");
        viewHolder.tv_rating.setText(chooseMaidPojo.getResponse().get(i).getRating());
        Picasso.with(context)
                .load(Constants.BASE_IMAGE_URL + chooseMaidPojo.getResponse().get(i).getImage())
                .error(R.drawable.dummy)
                .into(viewHolder.iv_staff_image);
//        viewHolder.tv_addstaff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ( chooseMaidPojo.isClicked()==false) {
//                    viewHolder.tv_addstaff.setBackgroundResource(R.drawable.black_corner_bg);
//                    // tv_addstaff.setBackgroundColor(Color.parseColor("#000"));
//                    viewHolder.tv_addstaff.setTextColor(context.getResources().getColor(R.color.white));
//                    chooseMaidPojo.setClicked(true);
//                }
//                else {
//                    viewHolder.tv_addstaff.setBackgroundResource(R.drawable.black_border_small);
//                    // tv_addstaff.setBackgroundColor(Color.parseColor("#000"));
//                    viewHolder.tv_addstaff.setTextColor(context.getResources().getColor(R.color.graydark));
//                    chooseMaidPojo.setClicked(false);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return chooseMaidPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_staff_image;
        TextView tv_staff_name, tv_staff_cat, tv_working_flats, tv_rating, tv_addstaff;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_staff_image = itemView.findViewById(R.id.iv_maid_img);
            tv_staff_name = itemView.findViewById(R.id.tv_staff_name);
            tv_staff_cat = itemView.findViewById(R.id.tv_staff_cat);
            tv_working_flats = itemView.findViewById(R.id.tv_working_flats);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            tv_addstaff = itemView.findViewById(R.id.tv_add_staff);

            tv_addstaff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = LayoutInflater.from(context);
//        getLayoutInflater().inflate(R.layout.alert_alerts,null);
                    View view1 = inflater.inflate(R.layout.dailog_alert_choose_staff_cat, null);

                    final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    alertDialog.setView(view1);
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog.setCancelable(true);
                    final RadioButton r_btn_perm, r_btn_temp;
                    final LinearLayout ll_date_picker, ll_from_date, ll_end_date;
                    final TextView tv_from_date, tv_end_date, tv_ok;
                    r_btn_perm = view1.findViewById(R.id.r_button_per);
                    r_btn_temp = view1.findViewById(R.id.r_button_temp);
                    ll_date_picker = view1.findViewById(R.id.ll_date_picker);
                    ll_from_date = view1.findViewById(R.id.ll_from_date);
                    ll_end_date = view1.findViewById(R.id.ll_end_date);
                    tv_from_date = view1.findViewById(R.id.tv_staff_from_date);
                    tv_end_date = view1.findViewById(R.id.tv_staff_end_date);
                    tv_ok = view1.findViewById(R.id.tv_btn_ok);

                    ll_from_date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Calendar myCalendar = Calendar.getInstance();
                            final Calendar c = Calendar.getInstance();
                            mYear = c.get(Calendar.YEAR);
                            mMonth = c.get(Calendar.MONTH);
                            mDay = c.get(Calendar.DAY_OF_MONTH);
                            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                                    new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {
                                            tv_from_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                        }
                                    }, mYear, mMonth, mDay);
                            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                            datePickerDialog.show();


                        }
                    });
                    ll_end_date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Calendar myCalendar = Calendar.getInstance();
                            final Calendar c = Calendar.getInstance();
                            mYear = c.get(Calendar.YEAR);
                            mMonth = c.get(Calendar.MONTH);
                            mDay = c.get(Calendar.DAY_OF_MONTH);
                            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                                    new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {
                                            tv_end_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                        }
                                    }, mYear, mMonth, mDay);
                            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                            datePickerDialog.show();


                        }
                    });

                    tv_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (tv_from_date.getText().toString().isEmpty()) {
                                Toast.makeText(context, "Please select From Date", Toast.LENGTH_SHORT).show();
                            } else if (tv_end_date.getText().toString().isEmpty()) {
                                Toast.makeText(context, "Please select End Date", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_STAFF_SCREEN);
                            }
                        }
                    });

                    r_btn_perm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                r_btn_temp.setChecked(false);
                                ll_date_picker.setVisibility(View.GONE);
                                tv_ok.setVisibility(View.GONE);

                            }


                        }
                    });

                    r_btn_temp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                r_btn_perm.setChecked(false);
                                ll_date_picker.setVisibility(View.VISIBLE);
                                tv_ok.setVisibility(View.VISIBLE);

                            }
//                            else {
//                                r_btn_temp.setChecked(true);
//                                r_btn_temp.setChecked(true);
//                            }

                        }
                    });
                    alertDialog.show();
                }
            });
        }
    }

}
