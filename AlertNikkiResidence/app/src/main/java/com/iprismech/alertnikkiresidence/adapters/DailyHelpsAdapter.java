package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.AddStaffActivity;
import com.iprismech.alertnikkiresidence.pojo.DailyHelpsListPojo;
import com.iprismech.alertnikkiresidence.request.DailyHelpsList;
import com.iprismech.alertnikkiresidence.utilities.Constants;
import com.squareup.picasso.Picasso;

public class DailyHelpsAdapter extends BaseAdapter {

    private Context context;
    private DailyHelpsListPojo dailyHelpsListPojo;

    public DailyHelpsAdapter(AddStaffActivity addStaffActivity, DailyHelpsListPojo dailyHelpsListPojo) {
        this.context = addStaffActivity;
        this.dailyHelpsListPojo = dailyHelpsListPojo;
    }

    @Override
    public int getCount() {
        return dailyHelpsListPojo.getResponse().size();
    }

    @Override
    public Object getItem(int position) {
        return dailyHelpsListPojo.getResponse().get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView service_img;
        TextView tv_dervice_name;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_add_staff, null);
        service_img = convertView.findViewById(R.id.iv_service_img);
        tv_dervice_name = convertView.findViewById(R.id.tv_service_name);
        Picasso.with(context)
                .load(Constants.BASE_IMAGE_URL + dailyHelpsListPojo.getResponse().get(position).getApp_icon())
                .error(R.drawable.dummy)
                .into(service_img);

        tv_dervice_name.setText(dailyHelpsListPojo.getResponse().get(position).getTitle());
        return convertView;
    }
}
