package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.ResponseVisitMember;


import java.util.ArrayList;

public class WaitingVisitorAdapter extends RecyclerView.Adapter<WaitingVisitorAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ResponseVisitMember> arrayList;

    public WaitingVisitorAdapter(Context context, ArrayList<ResponseVisitMember> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_waiting, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ResponseVisitMember member = arrayList.get(i);

        viewHolder.name.setText(member.name);
        viewHolder.type.setText(member.type);
        viewHolder.toAddress.setText("Name: " + member.userName + "\n" +
                "Flat: " + member.flatName + "\n" + "Building: " + member.buildingName);

        //   visitor person as guest or security accept as 1 then show IN or
//      if deviryBou or security accept as 0 allow to call or message
//      if Residence not responding to call or message allow to check

        if (member.type.equalsIgnoreCase("Guest") || member.security_accept_status.equalsIgnoreCase("1")) {
            viewHolder.b_in.setVisibility(View.VISIBLE);
        } else if (member.type.equalsIgnoreCase("Delivery") && member.security_accept_status.equalsIgnoreCase("0")) {
            viewHolder.ll_calling.setVisibility(View.VISIBLE);
        } else {
            viewHolder.b_check.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, type, toAddress;
        private ImageView pic;
        private Button b_in, b_check;
        ImageView b_call_1, b_call2, b_msg;
        LinearLayout ll_calling;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            b_in = itemView.findViewById(R.id.btn_in);
            b_check = itemView.findViewById(R.id.btn_check);
            b_msg = itemView.findViewById(R.id.btn_msg);
            b_call_1 = itemView.findViewById(R.id.btn_call);
            b_call2 = itemView.findViewById(R.id.btn_callactive);
            ll_calling = itemView.findViewById(R.id.ll_calling);


            /* b_call2 = itemView.findViewById(R.id.btn_check);*/


            name = itemView.findViewById(R.id.txt_title_name);
            type = itemView.findViewById(R.id.txt_gusetType);
            toAddress = itemView.findViewById(R.id.txt_addressTo);

            pic = itemView.findViewById(R.id.img_userpic);

            b_in.setOnClickListener(this);
            b_msg.setOnClickListener(this);
            b_call_1.setOnClickListener(this);
            b_call2.setOnClickListener(this);

            b_check.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getPosition());
            }
        }
    }
}
