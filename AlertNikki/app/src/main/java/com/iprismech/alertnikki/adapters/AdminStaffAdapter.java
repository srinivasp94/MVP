package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iprismech.alertnikki.R;

import java.util.ArrayList;

public class AdminStaffAdapter extends RecyclerView.Adapter<AdminStaffAdapter.ViewHolder> {
    private Context context;
    private ArrayList arrayList;

    public AdminStaffAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_staff, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView guestName, type, invitedBy, address, timesince;
        Button out;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guestName = itemView.findViewById(R.id.txt_title_name);
            type = itemView.findViewById(R.id.txt_gusetType);
            invitedBy = itemView.findViewById(R.id.txt_invitedBy);
            address = itemView.findViewById(R.id.txt_AddressFrom);
            timesince = itemView.findViewById(R.id.txt_timeSince);
            out = itemView.findViewById(R.id.btn_out);
        }
    }
}
