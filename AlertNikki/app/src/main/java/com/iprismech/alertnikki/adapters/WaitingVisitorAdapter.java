package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.DailyHelpsList;
import com.iprismech.alertnikki.Response.ResponseVisitMember;
import com.iprismech.alertnikki.fragments.WaitingVisitorFragment;


import java.util.ArrayList;
import java.util.List;

public class WaitingVisitorAdapter extends RecyclerView.Adapter<WaitingVisitorAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<ResponseVisitMember> arrayList;
    private ArrayList<ResponseVisitMember> temp;

    public WaitingVisitorAdapter(Context context, ArrayList<ResponseVisitMember> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.temp = arrayList;
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position, ArrayList<ResponseVisitMember> arrayList);
        // void onItemClickEvent(View view, int position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //View view = LayoutInflater.from(context).inflate(R.layout.item_waiting, viewGroup, false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waiting, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        ResponseVisitMember member = arrayList.get(i);

        viewHolder.name.setText(member.name);
        viewHolder.type1.setText(member.type);
        viewHolder.toAddress.setText(member.flatName + "," + member.buildingName);

        //   visitor person as guest or security accept as 1 then show IN or
//      if deviryBou or security accept as 0 allow to call or message
//      if Residence not responding to call or message allow to check
        String type = member.type;
        try {
            if (member.type.equalsIgnoreCase("Guest")
                    || member.security_accept_status.equalsIgnoreCase("1")
                    ) {
                viewHolder.b_in.setVisibility(View.VISIBLE);
                viewHolder.ll_calling.setVisibility(View.GONE);
                viewHolder.b_check.setVisibility(View.GONE);

            } else if (member.type.equalsIgnoreCase("Delivery")
                    || member.type.equalsIgnoreCase("Cab")
                    || member.type.equalsIgnoreCase("Repair & Maintenance")
                    || member.type.equalsIgnoreCase("Others")
                    && member.security_accept_status.equalsIgnoreCase("0")) {
                viewHolder.ll_calling.setVisibility(View.VISIBLE);
                viewHolder.b_check.setVisibility(View.GONE);
                viewHolder.b_in.setVisibility(View.GONE);
            } else {
                viewHolder.b_check.setVisibility(View.VISIBLE);
                viewHolder.b_in.setVisibility(View.GONE);
                viewHolder.ll_calling.setVisibility(View.GONE);

            }
        } catch (Exception e) {
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
        //  return 3;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    arrayList = temp;

                } else {


//                    filteredHelpsList.clear();
//                    for (ResponseVisitMember visitMember: temp) {
//                        if (visitMember.name.contains(charString.toLowerCase())) {
//                            filteredHelpsList.add(visitMember);
//                        }
//                    }
//                    filteredHelpsList = arrayList;

                    ArrayList<ResponseVisitMember> filteredList = new ArrayList<>();
                    for (ResponseVisitMember row : temp) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.name.toLowerCase().contains(charString.toLowerCase())) {

                            filteredList.add(row);
                        }
                    }

                    arrayList = filteredList;

                }
                FilterResults results = new FilterResults();
                results.values = arrayList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayList = (ArrayList<ResponseVisitMember>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, type1, toAddress;
        ImageView pic;
        Button b_in, b_check;
        ImageView b_call_1, b_call2, b_msg;
        LinearLayout ll_calling;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            b_in = itemView.findViewById(R.id.btn_in);
            b_check = itemView.findViewById(R.id.btn_check);
            b_msg = itemView.findViewById(R.id.btn_msg);
            b_call_1 = itemView.findViewById(R.id.btn_call);
            b_call2 = itemView.findViewById(R.id.btn_callactive);
            ll_calling = itemView.findViewById(R.id.ll_calling);


            /* b_call2 = itemView.findViewById(R.id.btn_check);*/


            name = itemView.findViewById(R.id.txt_title_name_waiting);
            type1 = itemView.findViewById(R.id.txt_gusetType_waoting);
            toAddress = itemView.findViewById(R.id.txt_addressTo_waiting);

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
                mListner.onItemClick(v, getAdapterPosition(), arrayList);
                //mListner.onItemClickEvent(v, getAdapterPosition());

            }
        }
    }
}
