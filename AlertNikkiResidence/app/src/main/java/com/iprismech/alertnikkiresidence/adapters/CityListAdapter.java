package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.CityList;

import java.util.ArrayList;

public class CityListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CityList> list;
    LayoutInflater layoutInflater;

    public CityListAdapter(Context context, ArrayList<CityList> list) {
        this.context = context;
        this.list = list;
        layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (holder == null) {
            view = layoutInflater.inflate(R.layout.item_select_city, parent, false);
            holder = new ViewHolder();
            holder.tv_item_building = view.findViewById(R.id.tv_item_building);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_item_building.setText(list.get(position).title);
        return view;
    }

    private class ViewHolder {
        TextView tv_item_building;
    }
}
