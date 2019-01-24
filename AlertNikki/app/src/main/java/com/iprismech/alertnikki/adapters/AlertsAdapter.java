package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.AlertsCommon;

import java.util.ArrayList;
import java.util.List;

public class AlertsAdapter extends RecyclerView.Adapter<AlertsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AlertsCommon> list;

    public AlertsAdapter(Context context, ArrayList<AlertsCommon> list) {
        this.context = context;
        this.list = list;
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AlertsCommon alerts = list.get(i);
        viewHolder.date.setText(alerts.date);
        viewHolder.notification_Msg.setText("Name:"+alerts.name+"  Mobile:"+alerts.phone);
       // viewHolder.notification_Msg.setText(alerts.Description);
        viewHolder.type.setText(alerts.type_alert);

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date, notification_Msg, type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.item_notify_date);
            notification_Msg = itemView.findViewById(R.id.item_notify_message);
            type = itemView.findViewById(R.id.item_notify_type);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getPosition());
            }
        }
    }
}
