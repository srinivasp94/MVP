package com.iprismech.alertnikkiresidence.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.MaidAttendanceHistoryPojo;

public class MaidMonthlyAttAdapter extends RecyclerView.Adapter<MaidMonthlyAttAdapter.ViewHolder> {
    private Context context;
    private MaidAttendanceHistoryPojo maidAttendanceHistoryPojo;

    public MaidMonthlyAttAdapter(FragmentActivity activity, MaidAttendanceHistoryPojo maidAttendanceHistoryPojo) {
        this.context = activity;
        this.maidAttendanceHistoryPojo = maidAttendanceHistoryPojo;
    }

    @NonNull
    @Override
    public MaidMonthlyAttAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_histroy_maid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaidMonthlyAttAdapter.ViewHolder viewHolder, int i) {
      //  int count = maidAttendanceHistoryPojo.getResponse().getWeekly_history().get(i).getSmenu().size();
        int count = maidAttendanceHistoryPojo.getResponse().getMonthly_history().get(i).getSmenu().size();
        viewHolder.txtDate.setText(maidAttendanceHistoryPojo.getResponse().getMonthly_history().get(i).getSmenu().get(count - 1).getDate());
        viewHolder.txtInOutAm.setText(maidAttendanceHistoryPojo.getResponse().getMonthly_history().get(i).getSmenu().get(count - 1).getIn_time());
        viewHolder.txtinoutPm.setText(maidAttendanceHistoryPojo.getResponse().getMonthly_history().get(i).getSmenu().get(count - 1).getOut_time());
    }

    @Override
    public int getItemCount() {
        return maidAttendanceHistoryPojo.getResponse().getMonthly_history().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtInOutAm, txtinoutPm, tv_view_all;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtInOutAm = itemView.findViewById(R.id.txtInOutAm);
            txtinoutPm = itemView.findViewById(R.id.txtinoutPm);
            tv_view_all = itemView.findViewById(R.id.tv_view_all);
            tv_view_all.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("case", "Monthly");
                    bundle.putInt("position", getAdapterPosition());
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIEW_ALL_MAID_ATTENDANCE_HISTORY_SCREEN, bundle);

                }
            });

        }
    }
}
