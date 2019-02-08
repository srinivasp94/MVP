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
import com.iprismech.alertnikkiresidence.pojo.VisitorsHistoryPojo;

public class DenyVisitrosAdapter extends RecyclerView.Adapter<DenyVisitrosAdapter.ViewHolder> {
    private Context context;
    private VisitorsHistoryPojo visitorsHistoryPojo;

    public DenyVisitrosAdapter(FragmentActivity activity, VisitorsHistoryPojo visitorsHistoryPojo) {
        this.context = activity;
        this.visitorsHistoryPojo = visitorsHistoryPojo;
    }

    @NonNull
    @Override
    public DenyVisitrosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_visitor_deny, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DenyVisitrosAdapter.ViewHolder viewHolder, int i) {
        viewHolder.guest_name.setText(visitorsHistoryPojo.getAllow_records().get(i).getName());
        viewHolder.guest_type.setText(visitorsHistoryPojo.getAllow_records().get(i).getService());
        viewHolder.guest_visit_date.setText(visitorsHistoryPojo.getAllow_records().get(i).getFrom_date_time());
        viewHolder.tv_reason_deny.setText(visitorsHistoryPojo.getAllow_records().get(i).getReson_for_deny());

    }

    @Override
    public int getItemCount() {

        return visitorsHistoryPojo.getDeny_records().size();
        //return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView guest_name, guest_type, guest_visit_date, tv_deny, tv_reason_deny;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guest_name = itemView.findViewById(R.id.guest_name);
            guest_type = itemView.findViewById(R.id.guest_type);
            guest_visit_date = itemView.findViewById(R.id.tv_denied_date);
            tv_reason_deny = itemView.findViewById(R.id.tv_reason_deny);

        }
    }
}
