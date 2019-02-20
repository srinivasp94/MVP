package com.iprismech.alertnikkiresidence.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.KidsGateAlertActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.KidsListPojo;

public class KidsListAdapter extends RecyclerView.Adapter<KidsListAdapter.ViewHolder> {
    private Context context;
    private KidsListPojo kidsListPojo;


    public KidsListAdapter(KidsGateAlertActivity kidsGateAlertActivityClass, KidsListPojo kidsListPojo) {
        this.context = kidsGateAlertActivityClass;
        this.kidsListPojo = kidsListPojo;
    }

    private StaffListAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(StaffListAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public KidsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kids_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KidsListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.kid_name.setText(kidsListPojo.getResponse().get(i).getKid_name());
        viewHolder.kid_purpose.setText(kidsListPojo.getResponse().get(i).getPurpose());
        if (kidsListPojo.getResponse().get(i).getNotification_status().equalsIgnoreCase("0")) {
            viewHolder.switch_kids_noti.setChecked(false);
        } else if (kidsListPojo.getResponse().get(i).getNotification_status().equalsIgnoreCase("1")) {
            viewHolder.switch_kids_noti.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return kidsListPojo.getResponse().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout ll_edit_kid, ll_delete, ll_send_gate_pass;
        TextView kid_name, kid_purpose;
        Switch switch_kids_noti;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_edit_kid = itemView.findViewById(R.id.ll_edit_kid);
            ll_send_gate_pass = itemView.findViewById(R.id.ll_send_gate_pass_kid);
            ll_delete = itemView.findViewById(R.id.ll_delete_kid);

            kid_name = itemView.findViewById(R.id.kid_name);
            kid_purpose = itemView.findViewById(R.id.purpose_kid);
            switch_kids_noti = itemView.findViewById(R.id.switch_kids_noti);
            switch_kids_noti.setOnClickListener(this);

            ll_delete.setOnClickListener(this);
            ll_edit_kid.setOnClickListener(this);
            ll_send_gate_pass.setOnClickListener(this);

        }

        @SuppressLint("WrongConstant")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_delete_kid:
                    if (mListner != null) {
                        mListner.onItemClick(v, getAdapterPosition());

                    }
                    break;
                case R.id.ll_edit_kid:
                    if (mListner != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("screen", "Edit Kid");
                        bundle.putString("kid_id", kidsListPojo.getResponse().get(getAdapterPosition()).getId());
                        bundle.putString("kid_name", kidsListPojo.getResponse().get(getAdapterPosition()).getKid_name());
                        bundle.putString("kid_purpose", kidsListPojo.getResponse().get(getAdapterPosition()).getPurpose());
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_KID_SCREEN, bundle);

                    }
                    break;
                case R.id.ll_send_gate_pass_kid:
                    if (mListner != null) {
                        mListner.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }
}
