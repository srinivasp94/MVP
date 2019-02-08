package com.iprismech.alertnikkiresidence.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.Smenu;
import com.iprismech.alertnikkiresidence.response.WeeklyHistory;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class HistorySchoolAdapter extends ExpandableRecyclerViewAdapter<Date_ViewHolder, HistoryChildViewHolder> {

    private Activity activity;
    private ArrayList<WeeklyHistory> weeklyList;

    public HistorySchoolAdapter(Activity activity, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
//        this.weeklyList = groups;
    }

    @Override
    public Date_ViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_select_city, parent, false);

        return new Date_ViewHolder(view);
    }

    @Override
    public HistoryChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_history, parent, false);

        return new HistoryChildViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(HistoryChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Smenu smenu= ((WeeklyHistory) group).getItems().get(childIndex);
        holder.onBind(smenu,group);
    }

    @Override
    public void onBindGroupViewHolder(Date_ViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupName(group);
    }
}
