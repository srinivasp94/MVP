package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.ManagementCommitteActivity;
import com.iprismech.alertnikkiresidence.pojo.ManagementCommittePojo;

public class ManagementCommitteAdapter extends RecyclerView.Adapter<ManagementCommitteAdapter.ViewHolder> {
    private Context context;
    private ManagementCommittePojo managementCommittePojo;

    public ManagementCommitteAdapter(ManagementCommitteActivity managementCommitteActivity, ManagementCommittePojo managementCommittePojo) {
        this.context = managementCommitteActivity;
        this.managementCommittePojo = managementCommittePojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_management_committe, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(managementCommittePojo.getResponse().get(i).getName());
        viewHolder.desig.setText(managementCommittePojo.getResponse().get(i).getDesignation());
        viewHolder.number.setText(managementCommittePojo.getResponse().get(i).getMobile());
        viewHolder.email.setText(managementCommittePojo.getResponse().get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        return managementCommittePojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, desig, number, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name_committe);
            desig = itemView.findViewById(R.id.tv_desig_committe);
            number = itemView.findViewById(R.id.tv_number_committe);
            email = itemView.findViewById(R.id.mail_committe);
        }
    }
}
