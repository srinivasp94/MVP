package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.pojo.DailyHelpsListPojo;
import com.iprismech.alertnikkiresidence.response.GuestsList;
import com.iprismech.alertnikkiresidence.utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StaffRecyclerGridAdapter extends RecyclerView.Adapter<StaffRecyclerGridAdapter.Viewholder> {
    private Context context;
    private DailyHelpsListPojo guestList;

    public StaffRecyclerGridAdapter(Context context, DailyHelpsListPojo guestList) {
        this.context = context;
        this.guestList = guestList;
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
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_staff, null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        Picasso.with(context)
                .load(Constants.BASE_IMAGE_URL + guestList.getResponse().get(i).getApp_icon())
                .error(R.drawable.dummy)
                .into(viewholder.service_img);

        viewholder.tv_dervice_name.setText(guestList.getResponse().get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return guestList.getResponse().size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView service_img;
        TextView tv_dervice_name;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            service_img = itemView.findViewById(R.id.iv_service_img);
            tv_dervice_name = itemView.findViewById(R.id.tv_service_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
