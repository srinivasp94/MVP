package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.ServiceList;

import java.util.ArrayList;

public class GateServicesAdapter extends RecyclerView.Adapter<GateServicesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ServiceList> serviceLists;

    public GateServicesAdapter(Context context, ArrayList<ServiceList> serviceLists) {
        this.context = context;
        this.serviceLists = serviceLists;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_service_gate, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ServiceList model = serviceLists.get(i);
        viewHolder.serviceName.setText(model.title);
    }

    @Override
    public int getItemCount() {
        return serviceLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView serviceName;
        RelativeLayout rlItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.txtServiceName);
            rlItem = itemView.findViewById(R.id.rlItem);
            rlItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v,getAdapterPosition());
            }
        }
    }
}
