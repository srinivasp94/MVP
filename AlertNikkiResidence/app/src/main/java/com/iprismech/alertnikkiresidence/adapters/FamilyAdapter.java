package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.FamilyList;

import java.util.ArrayList;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FamilyList> familyLists;

    public FamilyAdapter(Context context, ArrayList<FamilyList> familyLists) {
        this.context = context;
        this.familyLists = familyLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_family, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FamilyList model = familyLists.get(i);
        viewHolder.txtFamilyName.setText(model.name);
        viewHolder.txtFamilyphn.setText(model.mobile);
        viewHolder.txtFamilyRelation.setText(model.relation);
    }

    @Override
    public int getItemCount() {
        return familyLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFamilyName, txtFamilyphn, txtFamilyRelation;
        ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFamilyName = itemView.findViewById(R.id.txtFamilyName);
            txtFamilyphn = itemView.findViewById(R.id.txtFamilyphn);
            txtFamilyRelation = itemView.findViewById(R.id.txtFamilyRelation);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
