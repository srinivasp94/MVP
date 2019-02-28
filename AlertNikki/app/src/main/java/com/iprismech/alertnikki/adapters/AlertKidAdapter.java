package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.KidsGateAlerts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlertKidAdapter extends RecyclerView.Adapter<AlertKidAdapter.ViewHolder> {
    private Context context;
    private ArrayList<KidsGateAlerts> list;

    public AlertKidAdapter(Context context, ArrayList<KidsGateAlerts> list) {
        this.context = context;
        this.list = list;
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position, ArrayList<KidsGateAlerts> arrayList);

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
        KidsGateAlerts model = list.get(i);
        viewHolder.date.setText(model.fromDate);
        viewHolder.notification_Msg.setText("Kid Name: " + model.kidName + " is going with " + model.kidGoingWithWhom + "(Guardian)");
        viewHolder.type.setText("Out Time :"+model.outTime);
        Picasso.with(context).load(R.drawable.ic_kid_alert).into(viewHolder.icon_alert);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date, notification_Msg, type;
        ImageView icon_alert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.item_notify_date);
            notification_Msg = itemView.findViewById(R.id.item_notify_message);
            type = itemView.findViewById(R.id.item_notify_type);
            icon_alert = itemView.findViewById(R.id.icon_alert);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
