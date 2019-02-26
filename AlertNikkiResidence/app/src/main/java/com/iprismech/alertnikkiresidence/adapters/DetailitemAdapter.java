package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.TypleList;

import java.util.ArrayList;

public class DetailitemAdapter extends RecyclerView.Adapter<DetailitemAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TypleList> lists;

    public DetailitemAdapter(Context context, ArrayList<TypleList> lists) {
        this.context = context;
        this.lists = lists;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TypleList model = lists.get(i);

        viewHolder.txttenent.setText(model.title);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttenent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenent = itemView.findViewById(R.id.txttenent);
            txttenent.setOnClickListener(new View.OnClickListener() {
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
