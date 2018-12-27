package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikki.Pojo.ThroughVehiclePojo;
import com.iprismech.alertnikki.R;

public class ThroughVehicleAdapter extends RecyclerView.Adapter<ThroughVehicleAdapter.ViewHolder> {

    private Context context;
    private ThroughVehiclePojo throughVehiclePojo;

    public ThroughVehicleAdapter(Context applicationContext, ThroughVehiclePojo throughVehiclePojo) {
        this.throughVehiclePojo = throughVehiclePojo;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public ThroughVehicleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_through_vehicle, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText("Name: " + throughVehiclePojo.getResponse().get(i).getName());
        viewHolder.mobile.setText(throughVehiclePojo.getResponse().get(i).getMobile());
        if (throughVehiclePojo.getResponse().get(i).getService_from().equalsIgnoreCase("")) {
            viewHolder.itemView.findViewById(R.id.item_service_from_through_vehicle).setVisibility(View.GONE);
            viewHolder.itemView.findViewById(R.id.text_srvi).setVisibility(View.GONE);
        } else {
            viewHolder.itemView.findViewById(R.id.text_srvi).setVisibility(View.VISIBLE);
            viewHolder.itemView.findViewById(R.id.item_service_from_through_vehicle).setVisibility(View.VISIBLE);
            viewHolder.service_from.setText(throughVehiclePojo.getResponse().get(i).getService_from());
        }
        if (throughVehiclePojo.getResponse().get(i).getDelivery_from().equalsIgnoreCase("")) {
            viewHolder.itemView.findViewById(R.id.item_delivery_from_through_vehicle).setVisibility(View.GONE);
            viewHolder.itemView.findViewById(R.id.text_sdeli).setVisibility(View.GONE);
        } else {
            viewHolder.itemView.findViewById(R.id.text_sdeli).setVisibility(View.VISIBLE);
            viewHolder.itemView.findViewById(R.id.item_delivery_from_through_vehicle).setVisibility(View.VISIBLE);
            viewHolder.delivery_from.setText(throughVehiclePojo.getResponse().get(i).getDelivery_from());
        }


        viewHolder.vehicle_number.setText(throughVehiclePojo.getResponse().get(i).getVehicle_no());
    }

    @Override
    public int getItemCount() {
        return throughVehiclePojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, mobile, delivery_from, service_from, vehicle_number,text_delfrom,txt_servfrom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_through_name);
            mobile = itemView.findViewById(R.id.item_mobile_no_through_vehicle);
            delivery_from = itemView.findViewById(R.id.item_delivery_from_through_vehicle);
            service_from = itemView.findViewById(R.id.item_service_from_through_vehicle);
            vehicle_number = itemView.findViewById(R.id.item_through_vehicle_number);
            text_delfrom=itemView.findViewById(R.id.text_sdeli);
            txt_servfrom=itemView.findViewById(R.id.text_srvi);

        }
    }
}
