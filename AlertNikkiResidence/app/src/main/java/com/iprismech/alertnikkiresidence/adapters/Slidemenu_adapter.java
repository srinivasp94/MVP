package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;


public class Slidemenu_adapter extends BaseAdapter {

    private Context context;
    int[] images;
    String[] text;
    LayoutInflater inflater;

    public Slidemenu_adapter(Context context, String[] text, int[] images) {
        this.context = context;
        this.images = images;
        this.text = text;
        inflater = LayoutInflater.from(context);
    }

    @Override


    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.slidemenu_layout, viewGroup, false);
        ImageView slidemenu_img = view.findViewById(R.id.ivlvnav);
        slidemenu_img.setImageResource(images[i]);
        TextView slidemenu_txt = view.findViewById(R.id.tv_slidemenu_layout);
        slidemenu_txt.setText(text[i]);

        return view;
    }
}
