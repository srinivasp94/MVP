package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.pojo.StandardMaidTimingPojo;
import com.iprismech.alertnikkiresidence.utilities.Common;

import java.util.ArrayList;

public class TimeSlotAdapter extends BaseAdapter {

    private Context context;
    private StandardMaidTimingPojo pojo;
    LayoutInflater layoutInflater;


    public TimeSlotAdapter(Context context, StandardMaidTimingPojo pojo) {
        this.context = context;
        this.pojo = pojo;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pojo.getResponse().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
//        if (holder == null) {
        if (pojo.getResponse().get(position).getBooked_status() == 1) {
            //booked
            view = layoutInflater.inflate(R.layout.item_slot_not_available, null);
            holder = new ViewHolder();
            holder.txttimeSlots = view.findViewById(R.id.txttimeSlots);
            holder.txttimeSlots.setText(pojo.getResponse().get(position).getTitle());
            view.setTag(holder);
        } else if (pojo.getResponse().get(position).getBooked_status() == 0) {
            view = layoutInflater.inflate(R.layout.item_slot_available, null);
            holder = new ViewHolder();
            holder.txttimeSlots = view.findViewById(R.id.txttimeSlots);
            holder.txttimeSlots.setText(pojo.getResponse().get(position).getTitle());
            view.setTag(holder);
        }
        final ViewHolder finalHolder = holder;
        holder.txttimeSlots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pojo.getResponse().get(position).getBooked_status() == 0) {
                    if (pojo.getResponse().get(position).isSelectitem() == false) {
                        pojo.getResponse().get(position).setSelectitem(true);
                        finalHolder.txttimeSlots.setBackground(context.getResources().getDrawable(R.drawable.black_bgr_corners));
                    } else {
                        pojo.getResponse().get(position).setSelectitem(false);
                        finalHolder.txttimeSlots.setBackground(context.getResources().getDrawable(R.drawable.green_line_bgr));

                    }
                } else {
                    Common.showToast(context, "Please select availabel slots");
                }
            }
        });
//        } else {
//            holder = (ViewHolder) view.getTag();
//        }

//        holder.txtTimeNotAvailable.setText(pojo.getResponse().get(position).getTitle());
        return view;
    }

    private class ViewHolder {
        TextView txttimeSlots;

    }

    public ArrayList<StandardMaidTimingPojo.ResponseBean> getSelectedSlots() {
        ArrayList<StandardMaidTimingPojo.ResponseBean> list = new ArrayList<>();

        for (int i = 0; i < pojo.getResponse().size(); i++) {
            if (pojo.getResponse().get(i).isSelectitem()) {
                list.add(pojo.getResponse().get(i));
            }
        }

        return list;
    }
}
