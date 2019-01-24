package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.SchoolBusesList;
import com.iprismech.alertnikki.utilities.timeutilities.SlotDivision;

import java.util.ArrayList;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SchoolBusesList> busesLists;

    public SchoolAdapter(Context context, ArrayList<SchoolBusesList> busesLists) {
        this.context = context;
        this.busesLists = busesLists;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_school, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SchoolBusesList buses = busesLists.get(i);
        viewHolder.schoolName.setText(buses.schoolBusName);
        viewHolder.schoolAddress.setText(buses.address);
        viewHolder.route.setText("Bus No: " + buses.vehicleNumber);

        if (buses.attendence.inTime == null) {
            viewHolder.notify.setVisibility(View.VISIBLE);
        } else {
            viewHolder.in_time.setVisibility(View.VISIBLE);

            try {
                String diffTime = SlotDivision.differenceTime(buses.attendence.inTime);
                viewHolder.in_time.setText(diffTime);

            } catch (Exception e) {

            }

//            viewHolder.in_time.setText(buses.attendence.inTime);
            viewHolder.bt_OUT.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return busesLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView schoolName, schoolAddress, route, in_time;
        private ImageView schoolPic, notify;
        private Button bt_OUT;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolName = itemView.findViewById(R.id.txt_title_name);
            schoolAddress = itemView.findViewById(R.id.txt_addressSchool);
            route = itemView.findViewById(R.id.txt_Route);
            in_time = itemView.findViewById(R.id.txt_timeSince);
            schoolPic = itemView.findViewById(R.id.image_school);
            bt_OUT = itemView.findViewById(R.id.btn_out);
            notify = itemView.findViewById(R.id.img_notify);

            notify.setOnClickListener(this);
            bt_OUT.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getPosition());
            }
        }
    }
}
