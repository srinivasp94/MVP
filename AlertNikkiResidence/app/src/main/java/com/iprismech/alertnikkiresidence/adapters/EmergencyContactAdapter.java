package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.EmergencyContactActivity;
import com.iprismech.alertnikkiresidence.pojo.EmergencyConatctPojo;

public class EmergencyContactAdapter extends RecyclerView.Adapter<EmergencyContactAdapter.ViewHolder> {
    private Context context;
    private EmergencyConatctPojo emergencyConatctPojo;

    public EmergencyContactAdapter(EmergencyContactActivity emergencyContactActivity, EmergencyConatctPojo emergencyConatctPojo) {
        this.context = emergencyContactActivity;
        this.emergencyConatctPojo = emergencyConatctPojo;

    }

    @NonNull
    @Override
    public EmergencyContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emergency_contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyContactAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(emergencyConatctPojo.getResponse().get(i).getName());
        viewHolder.tv_number.setText(emergencyConatctPojo.getResponse().get(i).getContact_number());
    }

    @Override
    public int getItemCount() {
        return emergencyConatctPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_number;
        ImageView iv_call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name_emergency);
            tv_number = itemView.findViewById(R.id.tv_number_emergency);
            iv_call = itemView.findViewById(R.id.iv_call_emergency);
            iv_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" +emergencyConatctPojo.getResponse().get(getAdapterPosition()).getContact_number()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
