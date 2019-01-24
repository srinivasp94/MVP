package com.iprismech.alertnikki;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikki.Response.ResponseVisitMember;
import com.iprismech.alertnikki.activity.dummyresponse;

import java.util.ArrayList;

public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<dummyresponse.Responselist> arrayList;

    public DummyAdapter(Context context, ArrayList<dummyresponse.Responselist> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waiting, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        dummyresponse.Responselist list = arrayList.get(i);

        myViewHolder.roel.setText(list.userType);
        myViewHolder.name.setText(list.name);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, roel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_title_name_waiting);
            roel = itemView.findViewById(R.id.txt_gusetType_waoting);
        }
    }
}
