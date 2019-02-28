package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.SchoolBusList;

import java.util.ArrayList;

public class SchoolBusInfoAdapter extends RecyclerView.Adapter<SchoolBusInfoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SchoolBusList> lists;

    public SchoolBusInfoAdapter(Context context, ArrayList<SchoolBusList> lists) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_school_bus, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SchoolBusList model = lists.get(i);
        viewHolder.txtBusName.setText(model.schoolBusName);
        viewHolder.txtLocation.setText(model.address);
        viewHolder.txtIntime.setText(model.entryTime);
        viewHolder.txtOuttime.setText(model.exitTime);
        if (model.notificationStatus.equalsIgnoreCase("0"))
            viewHolder.busSwitch.setChecked(false);
        else if (model.notificationStatus.equalsIgnoreCase("1"))
            viewHolder.busSwitch.setChecked(true);

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtBusName, txtLocation, txtIntime, txtOuttime;
        Switch busSwitch;
        RelativeLayout rootRelative;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBusName = itemView.findViewById(R.id.txtBusName);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtIntime = itemView.findViewById(R.id.txtIntime);
            txtOuttime = itemView.findViewById(R.id.txtOuttime);
            busSwitch = itemView.findViewById(R.id.busSwitch);
            rootRelative = itemView.findViewById(R.id.rootRelative);
            busSwitch.setClickable(false);
            busSwitch.setOnClickListener(this);
            busSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mListner.onItemClick(buttonView,getAdapterPosition());
                }
            });

//            itemView.setOnClickListener(this);
            rootRelative.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
