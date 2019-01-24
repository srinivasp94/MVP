package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikki.Pojo.MoreAllServicesPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.app.factories.constants.AppConstants;
import com.iprismech.alertnikki.app.factories.controllers.ApplicationController;
import com.iprismech.alertnikki.utilities.Constants;
import com.squareup.picasso.Picasso;

public class MoreAllServicesAdapter extends RecyclerView.Adapter<MoreAllServicesAdapter.ViewHolder> {
    private Context context;
    private MoreAllServicesPojo moreAllServicesPojo;

    public MoreAllServicesAdapter(FragmentActivity activity, MoreAllServicesPojo moreAllServicesPojo) {

        this.context = activity;
        this.moreAllServicesPojo = moreAllServicesPojo;
    }


    @NonNull
    @Override
    public MoreAllServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_more_all_services, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoreAllServicesAdapter.ViewHolder viewHolder, int i) {
        viewHolder.service_name.setText(moreAllServicesPojo.getResponse().get(i).getTitle());
        //Picasso.with(context).load(Constants.BASE_IMAGE_URL + moreAllServicesPojo.getResponse().get(i).into(viewHolder.service_img);

    }

    @Override
    public int getItemCount() {
        return moreAllServicesPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_more_all;
        TextView service_name;
        ImageView service_img;
        String service_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            service_name = itemView.findViewById(R.id.service_name_more);
            service_img = itemView.findViewById(R.id.service_img_more);
            ll_more_all=itemView.findViewById(R.id.ll_more_all);
            ll_more_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(moreAllServicesPojo.getResponse().get(getAdapterPosition()).getTitle().equalsIgnoreCase("Add Guest")){
                        Bundle bundle=new Bundle();
                        bundle.putString("Key_Title",moreAllServicesPojo.getResponse().get(getAdapterPosition()).getTitle());
                        // bundle.putString("Key_Image",moreAllServicesPojo.getResponse().get(getAdapterPosition()).getId());
                        bundle.putInt("Key_Image",0);
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_GUEST_SCREEN,bundle);
                    }
                    else {
                        Bundle bundle = new Bundle();
                        bundle.putString("Key_Title", moreAllServicesPojo.getResponse().get(getAdapterPosition()).getTitle());
                        // bundle.putString("Key_Image",moreAllServicesPojo.getResponse().get(getAdapterPosition()).getId());
                        bundle.putInt("Key_Image", 0);
                        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_DELIVERY_BOY_SCREEN, bundle);
                    }

                }
            });
            ;

        }
    }
}
