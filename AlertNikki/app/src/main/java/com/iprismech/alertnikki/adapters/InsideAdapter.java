package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.ResponseVisitMember;
import com.iprismech.alertnikki.utilities.timeutilities.SlotDivision;

import java.util.ArrayList;

public class InsideAdapter extends RecyclerView.Adapter<InsideAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private ArrayList<ResponseVisitMember> arrayList;
    private ArrayList<ResponseVisitMember> temp;
    public InsideAdapter(Context context, ArrayList<ResponseVisitMember> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.temp = arrayList;
    }
    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString  = constraint.toString();
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
                        if (row.name.toLowerCase().contains(charString.toLowerCase() )){

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

    public interface OnitemClickListener {
        void onItemClick(View view, int position,ArrayList<ResponseVisitMember> arrayList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       // View view = LayoutInflater.from(context).inflate(R.layout.item_inside, viewGroup, false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_inside, null);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        ResponseVisitMember member = arrayList.get(i);
        holder.guestName.setText(member.name);
        holder.type.setText(member.type);
        holder.invitedBy.setText(member.userName+" "+member.buildingName);
        holder.address.setText(member.flatName);
//        holder.timesince.setText(member.inTime + "");
        try {
            String diffTime = SlotDivision.differenceTime(member.inTime);
            holder.timesince.setText(diffTime);
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView guestName, type, invitedBy, address, timesince;
        TextView out;
        LinearLayout ll_rootVisitor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            guestName = itemView.findViewById(R.id.txt_title_name_inside);
            type = itemView.findViewById(R.id.txt_gusetType_indise);
            invitedBy = itemView.findViewById(R.id.txt_invitedBy_inside);
            address = itemView.findViewById(R.id.txt_AddressFrom_inside);
            timesince = itemView.findViewById(R.id.txt_timeSince_inside);
            out = itemView.findViewById(R.id.btn_out_inside);
            ll_rootVisitor= itemView.findViewById(R.id.ll_rootVisitor);

            out.setOnClickListener(this);
            ll_rootVisitor.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition(),arrayList);
            }

        }
    }
}
