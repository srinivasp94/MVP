package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.BusRouteList;

import java.util.ArrayList;

public class BusRoutesAdapter extends RecyclerView.Adapter<BusRoutesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BusRouteList> routeLists;

    public BusRoutesAdapter(Context context, ArrayList<BusRouteList> routeLists) {
        this.context = context;
        this.routeLists = routeLists;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_city, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final BusRouteList model = routeLists.get(i);
        viewHolder.textView.setText(model.route);

//        viewHolder.itemView.setBackgroundColor(model.isSelected() ? Color.CYAN : Color.WHITE);
/*        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setSelected(!model.isSelected());
                viewHolder.itemView.setBackgroundColor(model.isSelected() ? Color.CYAN : Color.WHITE);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return routeLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout ll_buildingBlock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item_building);
            ll_buildingBlock = itemView.findViewById(R.id.ll_buildingBlock);

            ll_buildingBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListner != null) {
                        mListner.onItemClick(v, getAdapterPosition());
                    }
                }
            });

        }
    }
}
