package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikki.Pojo.DailyHelpsPojo;
import com.iprismech.alertnikki.R;

public class AddServiceAdapter extends BaseAdapter {
    Context context;
    DailyHelpsPojo dailyHelpsPojo;


    public AddServiceAdapter(FragmentActivity activity, DailyHelpsPojo dailyHelpsPojo) {
        this.context=activity;
        this.dailyHelpsPojo=dailyHelpsPojo;

    }

    @Override
    public int getCount() {
        return dailyHelpsPojo.getResponse().size();
    }

    @Override
    public Object getItem(int position) {
        return dailyHelpsPojo.getResponse().get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView_Title;
        LinearLayout ll_item_categoty;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_add_service, null);
        textView_Title=convertView.findViewById(R.id.daily_hepler_category);
        ll_item_categoty=convertView.findViewById(R.id.ll_item_categoty);
       // imageView_icon=convertView.findViewById(R.id.iv_category_icon);
        textView_Title.setText(dailyHelpsPojo.getResponse().get(position).getTitle());

        return convertView;
    }

}
