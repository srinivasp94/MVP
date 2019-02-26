package com.iprismech.alertnikkiresidence.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.MyStaffAlerts;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.MyStaff_Maids_List_Pojo;

public class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.ViewHolder> {
    private Context context;
    private MyStaff_Maids_List_Pojo myStaff_maids_list_pojo;

    public StaffListAdapter(MyStaffAlerts myStaffAlerts, MyStaff_Maids_List_Pojo myStaff_maids_list_pojo) {
        this.context = myStaffAlerts;
        this.myStaff_maids_list_pojo = myStaff_maids_list_pojo;
    }

    @NonNull
    @Override
    public StaffListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_my_staff_list, viewGroup, false);
        return new ViewHolder(view);
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_no_of_flats.setText(myStaff_maids_list_pojo.getResponse().get(i).getWorking_flats() + " flats");
        viewHolder.tv_staff_name.setText(myStaff_maids_list_pojo.getResponse().get(i).getMaid_name());
        viewHolder.tv_maid_type.setText(myStaff_maids_list_pojo.getResponse().get(i).getMaid_designation());
        viewHolder.tv_staff_rating.setText(myStaff_maids_list_pojo.getResponse().get(i).getRating());

        if (myStaff_maids_list_pojo.getResponse().get(i).getRating().equalsIgnoreCase("")
                || myStaff_maids_list_pojo.getResponse().get(i).getRating().isEmpty()
                || myStaff_maids_list_pojo.getResponse().get(i).getRating() == null) {
            viewHolder.rating_staff.setRating(Float.parseFloat("0.0"));

        } else {
            viewHolder.rating_staff.setRating(Float.parseFloat(myStaff_maids_list_pojo.getResponse().get(i).getRating()));
        }
        if (myStaff_maids_list_pojo.getResponse().get(i).getNotification_status().equalsIgnoreCase("0")) {
//            0 as FALSE and 1 as TRUE
            viewHolder.switch_noti.setChecked(false);
        } else {
            viewHolder.switch_noti.setChecked(true);
        }
        //  viewHolder.rating_staff.setRating(Float.parseFloat("4.5"));


    }

    @Override
    public int getItemCount() {
        return myStaff_maids_list_pojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout ll_make_call, ll_delete, ll_send_gate_pass, ll_item_staff_list;
        TextView tv_no_of_flats, tv_staff_name, tv_maid_type, tv_staff_rating;
        RatingBar rating_staff;
        Switch switch_noti;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_make_call = itemView.findViewById(R.id.ll_make_call);
            ll_send_gate_pass = itemView.findViewById(R.id.ll_send_gate_pass);
            ll_delete = itemView.findViewById(R.id.ll_delete_maid);
            ll_item_staff_list = itemView.findViewById(R.id.ll_item_staff_list);
            switch_noti = itemView.findViewById(R.id.switch_noti);


            tv_no_of_flats = itemView.findViewById(R.id.no_flats);
            tv_staff_name = itemView.findViewById(R.id.staff_name);
            tv_maid_type = itemView.findViewById(R.id.maid_type);
            tv_staff_rating = itemView.findViewById(R.id.staff_rating);
            rating_staff = itemView.findViewById(R.id.rating_staff_bar);

            switch_noti.setOnClickListener(this);
            ll_make_call.setOnClickListener(this);
            ll_send_gate_pass.setOnClickListener(this);
            ll_delete.setOnClickListener(this);

            ll_item_staff_list.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("maid_id", myStaff_maids_list_pojo.getResponse().get(getAdapterPosition()).getMaid_id());
                    bundle.putString("user_maid_id", myStaff_maids_list_pojo.getResponse().get(getAdapterPosition()).getId());
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_STAFF_PROFILE, bundle);
                }
            });

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
