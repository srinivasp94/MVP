package com.iprismech.alertnikkiresidence.adapters;

import android.view.View;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.Smenu;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class HistoryChildViewHolder extends ChildViewHolder {

    TextView mDate, m_in, m_Out, mSchool, mAddress;

    public HistoryChildViewHolder(View itemView) {
        super(itemView);
        mDate = itemView.findViewById(R.id.txtDate);
        m_in= itemView.findViewById(R.id.txtInOutAm);
        m_Out= itemView.findViewById(R.id.txtinoutPm);
        mSchool= itemView.findViewById(R.id.txtsclName);
        mAddress= itemView.findViewById(R.id.txtsclAddress);


    }

    public void onBind(Smenu smenu, ExpandableGroup group) {
        mDate.setText(smenu.date);
        m_in.setText(smenu.inTime);
        m_Out.setText(smenu.outTime);
        mSchool.setText(smenu.schoolbusName);
        mAddress.setText(smenu.schoolbusAddress);

    }

}
