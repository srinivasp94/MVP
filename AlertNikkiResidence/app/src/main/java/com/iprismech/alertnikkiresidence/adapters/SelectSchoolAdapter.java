package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.SearchBusList;

import java.util.ArrayList;

public class SelectSchoolAdapter extends RecyclerView.Adapter<SelectSchoolAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchBusList> searchBusLists;

    public SelectSchoolAdapter(Context context, ArrayList<SearchBusList> searchBusLists) {
        this.context = context;
        this.searchBusLists = searchBusLists;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_school, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SearchBusList model = searchBusLists.get(i);
        viewHolder.txtSchool.setText(model.schoolBusName);
        viewHolder.txtAddress.setText(model.address);

    }

    @Override
    public int getItemCount() {
        return searchBusLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSchool, txtAddress;
        LinearLayout rootLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSchool = itemView.findViewById(R.id.txtSchool);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            rootLayout = itemView.findViewById(R.id.rootLayout);

            rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListner != null) {
                        mListner.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }
}
