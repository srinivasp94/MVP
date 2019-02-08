package com.iprismech.alertnikkiresidence.adapters;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class Date_ViewHolder extends GroupViewHolder {
    TextView mDate;

    public Date_ViewHolder(View itemView) {
        super(itemView);
        mDate = itemView.findViewById(R.id.tv_item_building);
    }

    @Override
    public void expand() {
        mDate.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.bg_close, 0);
        Log.i("Adapter", "expand");
    }

    @Override
    public void collapse() {
        Log.i("Adapter", "collapse");
        mDate.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow, 0);
    }

    public void setGroupName(ExpandableGroup group) {
        mDate.setText(group.getTitle());
    }
}
