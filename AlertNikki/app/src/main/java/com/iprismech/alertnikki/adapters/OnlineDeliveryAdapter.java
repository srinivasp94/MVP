package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.OnlineModel;

import java.util.ArrayList;

public class OnlineDeliveryAdapter extends RecyclerView.Adapter<OnlineDeliveryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<OnlineModel> arrayList;

    public OnlineDeliveryAdapter(Context context, ArrayList<OnlineModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_delivery_company, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        OnlineModel onlineModel = arrayList.get(i);
//holder.img_company.
        holder.mName.setText(onlineModel.getTitle());
        holder.img_company.setImageResource(onlineModel.getImg_id());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img_company;
        private TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_company = itemView.findViewById(R.id.img_logo_company);
            mName = itemView.findViewById(R.id.txt_compamyName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getPosition());
            }
        }
    }
}
