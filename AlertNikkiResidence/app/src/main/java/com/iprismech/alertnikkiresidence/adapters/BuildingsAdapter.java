package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.pojo.BuildingsPojo;

public class BuildingsAdapter extends RecyclerView.Adapter<BuildingsAdapter.ViewHolder> {
    private Context context;
    private BuildingsPojo buildingsPojo;
//    private OnItemClickListener onItemClickListener;

    public BuildingsAdapter(Context applicationContext, BuildingsPojo buildingsPojo) {
        this.buildingsPojo = buildingsPojo;
        this.context = applicationContext;
//        this.onItemClickListener=onItemClickListener;

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
        View view = LayoutInflater.from(context).inflate(R.layout.item_building, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_building.setText(buildingsPojo.getResponse().get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return buildingsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_building;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_building = itemView.findViewById(R.id.tv_item_building);
            tv_building.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v,getAdapterPosition());
            }

        }
    }
}
