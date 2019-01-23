package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikki.R;

public class MoreItemAdapter extends BaseAdapter {

    private Context context;
    private String[] list;
    private Integer[] imageList;
    LayoutInflater inflater;

    public MoreItemAdapter(Context context, String[] list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MoreItemAdapter(Context context, String[] list, Integer[] imageList) {
        this.context = context;
        this.list = list;
        this.imageList = imageList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.length;
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
        Holder holder = null;
        if (view == null) {
//                grid = new View(context);
            view = inflater.inflate(R.layout.simple_list_item, parent, false);
            holder = new Holder();
            holder.textView = view.findViewById(R.id.txt_popupitem);
            holder.imageView = view.findViewById(R.id.image_moredata);
            view.setTag(holder);

        } else {
            holder = (Holder) view.getTag();
        }

        holder.textView.setText(list[position]);
        holder.imageView.setImageResource(imageList[position]);
        return view;

    }

    public class Holder {
        TextView textView;
        ImageView imageView;
    }
}
