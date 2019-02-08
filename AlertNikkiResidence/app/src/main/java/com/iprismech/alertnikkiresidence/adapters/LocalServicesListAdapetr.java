package com.iprismech.alertnikkiresidence.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.ChooseLocalServiceActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.LocalServicesListPojo;
import com.iprismech.alertnikkiresidence.utilities.Constants;
import com.squareup.picasso.Picasso;

public class LocalServicesListAdapetr extends RecyclerView.Adapter<LocalServicesListAdapetr.ViewHolder> {
    private Context context;
    private LocalServicesListPojo localServicesListPojo;

    public LocalServicesListAdapetr(ChooseLocalServiceActivity chooseLocalServiceActivity, LocalServicesListPojo localServicesListPojo) {
        this.context = chooseLocalServiceActivity;
        this.localServicesListPojo = localServicesListPojo;
    }

    @NonNull
    @Override
    public LocalServicesListAdapetr.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_local_services_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalServicesListAdapetr.ViewHolder viewHolder, int i) {

        viewHolder.tv_service_name.setText(localServicesListPojo.getResponse().get(i).getTitle());
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + localServicesListPojo.getResponse().get(i).getApp_icon())
                .error(R.drawable.dummy)
                .into(viewHolder.iv_service);
    }

    @Override
    public int getItemCount() {
        return localServicesListPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_service_type;
        ImageView iv_service;
        TextView tv_service_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_service_type = itemView.findViewById(R.id.ll_service_type);
            iv_service = itemView.findViewById(R.id.iv_service_img);
            tv_service_name = itemView.findViewById(R.id.tv_service_name);
            ll_service_type.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("WrongConstant")
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("service_id", localServicesListPojo.getResponse().get(getAdapterPosition()).getId());
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOCAL_SERVICE_DETAILS_SCREEN,bundle);
                }
            });

        }
    }
}
