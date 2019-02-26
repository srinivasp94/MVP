package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.MaidViewAllAttandancesHistory;
import com.iprismech.alertnikkiresidence.pojo.MaidAttendanceHistoryPojo;

public class ViewAllMaidHistoryAdapter extends RecyclerView.Adapter<ViewAllMaidHistoryAdapter.ViewHolder> {
    private Context context;
    private MaidAttendanceHistoryPojo maidAttendanceHistoryPojo;
    private int count;
    private String from_case;

    public ViewAllMaidHistoryAdapter(MaidViewAllAttandancesHistory maidViewAllAttandancesHistory, MaidAttendanceHistoryPojo maidAttendanceHistoryPojo, int postion, String from_case) {
        this.context = maidViewAllAttandancesHistory;
        this.count = postion;
        this.maidAttendanceHistoryPojo = maidAttendanceHistoryPojo;
        this.from_case = from_case;
    }

    @NonNull
    @Override
    public ViewAllMaidHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_all_maid_history, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllMaidHistoryAdapter.ViewHolder viewHolder, int i) {
        try {
            if (from_case.equalsIgnoreCase("Monthly")) {
                viewHolder.txtDate.setText(maidAttendanceHistoryPojo.getResponse().getMonthly_history().get(count).getSmenu().get(i).getDate());
                viewHolder.txtInOutAm.setText(maidAttendanceHistoryPojo.getResponse().getMonthly_history().get(count).getSmenu().get(i).getIn_time());
                viewHolder.txtinoutPm.setText(maidAttendanceHistoryPojo.getResponse().getMonthly_history().get(count).getSmenu().get(i).getOut_time());
            } else {
                viewHolder.txtDate.setText(maidAttendanceHistoryPojo.getResponse().getWeekly_history().get(count).getSmenu().get(i).getDate());
                viewHolder.txtInOutAm.setText(maidAttendanceHistoryPojo.getResponse().getWeekly_history().get(count).getSmenu().get(i).getIn_time());
                viewHolder.txtinoutPm.setText(maidAttendanceHistoryPojo.getResponse().getWeekly_history().get(count).getSmenu().get(i).getOut_time());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (from_case.equalsIgnoreCase("Monthly")) {
            return maidAttendanceHistoryPojo.getResponse().getMonthly_history().get(count).getSmenu().size();
        } else
            return maidAttendanceHistoryPojo.getResponse().getWeekly_history().get(count).getSmenu().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtInOutAm, txtinoutPm, tv_view_all;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtInOutAm = itemView.findViewById(R.id.txtInOutAm);
            txtinoutPm = itemView.findViewById(R.id.txtinoutPm);
            tv_view_all = itemView.findViewById(R.id.tv_view_all);

        }
    }
}
