package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikki.Pojo.DailyHelpsListpojo;
import com.iprismech.alertnikki.Pojo.DailyHelpsPojo;
import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddServiceAdapter extends BaseAdapter {
    Context context;
   // DailyHelpsListpojo dailyHelpsPojo;
    List<DailyHelpsListpojo.ResponseBean.SlistBean> dailyHelpsPojo;

    public AddServiceAdapter(FragmentActivity activity, List<DailyHelpsListpojo.ResponseBean.SlistBean> dailyHelpsPojo) {
        this.context=activity;
        this.dailyHelpsPojo=dailyHelpsPojo;

    }

    @Override
    public int getCount() {
        return dailyHelpsPojo.size();
    }

    @Override
    public Object getItem(int position) {

        return dailyHelpsPojo.get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView_Title;
        LinearLayout ll_item_categoty;
        ImageView iv_item_addservice;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_add_service, null);
        textView_Title=convertView.findViewById(R.id.daily_hepler_category);
        iv_item_addservice=convertView.findViewById(R.id.iv_item_addservice);
        ll_item_categoty=convertView.findViewById(R.id.ll_item_categoty);
       // imageView_icon=convertView.findViewById(R.id.iv_category_icon);
        textView_Title.setText(dailyHelpsPojo.get(position).getTitle());
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + dailyHelpsPojo.get(position).getApp_icon()).
                error(R.drawable.dummy).into(iv_item_addservice);
        return convertView;
    }

}
