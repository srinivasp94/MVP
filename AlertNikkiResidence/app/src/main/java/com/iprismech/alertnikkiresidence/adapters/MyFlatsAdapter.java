package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.profile.MyFlatActivity;
import com.iprismech.alertnikkiresidence.pojo.MyFlatsPojo;

import org.w3c.dom.Text;

public class MyFlatsAdapter extends RecyclerView.Adapter<MyFlatsAdapter.ViewHolder> {
    private Context context;
    private MyFlatsPojo myFlatsPojo;

    public MyFlatsAdapter(MyFlatActivity myFlatActivity, MyFlatsPojo myFlatsPojo) {
        this.context = myFlatActivity;
        this.myFlatsPojo = myFlatsPojo;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_flats, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_flat_name.setText(myFlatsPojo.getResponse().get(i).getFlat() + "," + myFlatsPojo.getResponse().get(i).getSociety());
        viewHolder.tv_tenant.setText(myFlatsPojo.getResponse().get(i).getResidence_type());
    }

    @Override
    public int getItemCount() {
        return myFlatsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_flat_name, tv_tenant;
        ImageView iv_flat_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_flat_name = itemView.findViewById(R.id.tv_flat_name);
            tv_tenant = itemView.findViewById(R.id.tv_tenant);
            iv_flat_delete = itemView.findViewById(R.id.iv_flat_delete);
            iv_flat_delete.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
