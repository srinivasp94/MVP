package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.pojo.Contact;
import com.iprismech.alertnikkiresidence.pojo.VisitorsHistoryPojo;

public class AllowVisitrosAdapter extends RecyclerView.Adapter<AllowVisitrosAdapter.ViewHolder> {
    private Context context;
    private VisitorsHistoryPojo visitorsHistoryPojo;

    public AllowVisitrosAdapter(FragmentActivity activity, VisitorsHistoryPojo visitorsHistoryPojo) {
        this.context=activity;
        this.visitorsHistoryPojo=visitorsHistoryPojo;
    }

    @NonNull
    @Override
    public AllowVisitrosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_visitor_history_allow, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllowVisitrosAdapter.ViewHolder viewHolder, int i) {
        viewHolder.guest_name.setText(visitorsHistoryPojo.getAllow_records().get(i).getName());
        viewHolder.guest_type.setText(visitorsHistoryPojo.getAllow_records().get(i).getService());
        viewHolder.guest_visit_date.setText(visitorsHistoryPojo.getAllow_records().get(i).getFrom_date_time());
        viewHolder.guest_in_time.setText(visitorsHistoryPojo.getAllow_records().get(i).getIn_time());
        viewHolder.guest_out_time.setText(visitorsHistoryPojo.getAllow_records().get(i).getOut_time());
    }

    @Override
    public int getItemCount() {
        return visitorsHistoryPojo.getAllow_records().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView guest_name, guest_type, guest_visit_date, guest_in_time, guest_out_time, tv_allow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guest_name = itemView.findViewById(R.id.guest_name);
            guest_type = itemView.findViewById(R.id.guest_type);
            guest_visit_date = itemView.findViewById(R.id.tv_visiting_date);
            guest_in_time = itemView.findViewById(R.id.guest_intime);
            guest_out_time = itemView.findViewById(R.id.guest_outtime);
            tv_allow = itemView.findViewById(R.id.tv_allow);

        }
    }
}
