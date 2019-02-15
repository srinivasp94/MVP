package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.schoolbus.BusViewAllAttandancesHistory;
import com.iprismech.alertnikkiresidence.pojo.SchoolBusHistoryPojo;

public class ViewAllBusHistoryAdapter extends RecyclerView.Adapter<ViewAllBusHistoryAdapter.ViewHolder> {
    private Context context;
    private SchoolBusHistoryPojo schoolBusHistoryPojo;
    private int count;
    private String from_case;

    public ViewAllBusHistoryAdapter(BusViewAllAttandancesHistory busViewAllAttandancesHistory, SchoolBusHistoryPojo schoolBusHistoryPojo, int postion, String from_case) {
        this.schoolBusHistoryPojo = schoolBusHistoryPojo;
        this.context = busViewAllAttandancesHistory;
        this.count = postion;
        this.from_case = from_case;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_all_maid_history, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (from_case.equalsIgnoreCase("Monthly")) {
            viewHolder.txtDate.setText(schoolBusHistoryPojo.getResponse().getMonthly_history().get(count).getSmenu().get(i).getSchoolbus_name() + '\n' + schoolBusHistoryPojo.getResponse().getMonthly_history().get(count).getSmenu().get(i).getDate());
            viewHolder.txtInOutAm.setText(schoolBusHistoryPojo.getResponse().getMonthly_history().get(count).getSmenu().get(i).getIn_time());
            viewHolder.txtinoutPm.setText(schoolBusHistoryPojo.getResponse().getMonthly_history().get(count).getSmenu().get(i).getOut_time());
        } else {
            viewHolder.txtDate.setText(schoolBusHistoryPojo.getResponse().getWeekly_history().get(count).getSmenu().get(i).getSchoolbus_name()+'\n'+schoolBusHistoryPojo.getResponse().getWeekly_history().get(count).getSmenu().get(i).getDate());
            viewHolder.txtInOutAm.setText(schoolBusHistoryPojo.getResponse().getWeekly_history().get(count).getSmenu().get(i).getIn_time());
            viewHolder.txtinoutPm.setText(schoolBusHistoryPojo.getResponse().getWeekly_history().get(count).getSmenu().get(i).getOut_time());
        }
    }

    @Override
    public int getItemCount() {
        if (from_case.equalsIgnoreCase("Monthly")) {
            return schoolBusHistoryPojo.getResponse().getMonthly_history().get(count).getSmenu().size();
        } else
            return schoolBusHistoryPojo.getResponse().getWeekly_history().get(count).getSmenu().size();
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
