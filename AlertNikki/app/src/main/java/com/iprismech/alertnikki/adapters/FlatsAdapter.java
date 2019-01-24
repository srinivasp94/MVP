package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikki.Pojo.FlatPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.activity.SelectFlatActivity;

public class FlatsAdapter extends RecyclerView.Adapter<FlatsAdapter.ViewHolder> {
    private Context context;
    FlatPojo flatPojo;

    public FlatsAdapter(Context context, FlatPojo flatPojo) {
        this.context = context;
        this.flatPojo = flatPojo;
    }
    private BuildingsAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(BuildingsAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_flat_no, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_flatname.setText(flatPojo.getResponse().get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return flatPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_flatname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_flatname = itemView.findViewById(R.id.tv_item_flat_number);
            tv_flatname.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v,getAdapterPosition());
            }
        }
    }
}
