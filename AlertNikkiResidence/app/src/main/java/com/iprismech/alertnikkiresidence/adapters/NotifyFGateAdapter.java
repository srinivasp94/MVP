package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.NotifyGateList;

import java.util.ArrayList;

public class NotifyFGateAdapter extends RecyclerView.Adapter<NotifyFGateAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NotifyGateList> gateLists;

    public NotifyFGateAdapter(Context context, ArrayList<NotifyGateList> gateLists) {
        this.context = context;
        this.gateLists = gateLists;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_notify_gate, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NotifyGateList model = gateLists.get(i);
        viewHolder.name.setText(model.name);
        viewHolder.phone.setText(model.mobile);
        viewHolder.validtill.setText("Notification Valid - " + model.vaildTo + " Hour");
        if (model.alertStatus.equalsIgnoreCase("0"))
            viewHolder.aSwitch.setChecked(false);
        else if (model.alertStatus.equalsIgnoreCase("1"))
            viewHolder.aSwitch.setChecked(true);

    }

    @Override
    public int getItemCount() {
        return gateLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Switch aSwitch;
        TextView name, phone, validtill;
        ImageView imgEditopt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aSwitch = itemView.findViewById(R.id.switc);
            name = itemView.findViewById(R.id.txtServicepersonName);
            phone = itemView.findViewById(R.id.txtServicepersonPhone);
            validtill = itemView.findViewById(R.id.txtServicevalid);
            imgEditopt = itemView.findViewById(R.id.imgEditOpt);

            imgEditopt.setOnClickListener(this);
            aSwitch.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
