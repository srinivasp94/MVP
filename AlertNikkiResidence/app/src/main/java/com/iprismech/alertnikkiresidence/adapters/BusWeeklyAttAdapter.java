package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.pojo.SchoolBusHistoryPojo;

public class BusWeeklyAttAdapter extends RecyclerView.Adapter<BusWeeklyAttAdapter.ViewHolder> {
    private Context context;
    private SchoolBusHistoryPojo schoolBusHistoryPojo;

    public BusWeeklyAttAdapter(FragmentActivity activity, SchoolBusHistoryPojo schoolBusHistoryPojo) {
        this.context = activity;
        this.schoolBusHistoryPojo = schoolBusHistoryPojo;
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
    public BusWeeklyAttAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_histroy_maid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusWeeklyAttAdapter.ViewHolder viewHolder, int i) {
        for (int j = 0; j < schoolBusHistoryPojo.getResponse().getWeekly_history().get(i).getSmenu().size(); j++) {
            viewHolder.txtDate.setText(schoolBusHistoryPojo.getResponse().getWeekly_history().get(i).getSmenu().get(j).getSchoolbus_name() +'\n'+ schoolBusHistoryPojo.getResponse().getWeekly_history().get(i).getSmenu().get(j).getDate());
            viewHolder.txtInOutAm.setText(schoolBusHistoryPojo.getResponse().getWeekly_history().get(i).getSmenu().get(j).getIn_time());
            viewHolder.txtinoutPm.setText(schoolBusHistoryPojo.getResponse().getWeekly_history().get(i).getSmenu().get(j).getOut_time());

        }
    }

    @Override
    public int getItemCount() {
        return schoolBusHistoryPojo.getResponse().getWeekly_history().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtDate, txtInOutAm, txtinoutPm, tv_view_all;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtInOutAm = itemView.findViewById(R.id.txtInOutAm);
            txtinoutPm = itemView.findViewById(R.id.txtinoutPm);
            tv_view_all = itemView.findViewById(R.id.tv_view_all);

            tv_view_all.setOnClickListener(this);

//            tv_view_all.setOnClickListener(new View.OnClickListener() {
//                @SuppressLint("WrongConstant")
//                @Override
//                public void onClick(View v) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("case", "Weekly");
//                    bundle.putInt("position", getAdapterPosition());
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VIEW_ALL_BUS_ATTENDANCE_HISTORY_SCREEN, bundle);
//
//                }
//            });

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
