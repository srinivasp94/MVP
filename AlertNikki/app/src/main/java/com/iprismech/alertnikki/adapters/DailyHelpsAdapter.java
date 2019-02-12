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
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.DailyHelpsList;
import com.iprismech.alertnikki.Response.ResponseVisitMember;
import com.iprismech.alertnikki.utilities.timeutilities.SlotDivision;

import java.util.ArrayList;

public class DailyHelpsAdapter extends RecyclerView.Adapter<DailyHelpsAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ArrayList<DailyHelpsList> arrayList;
    private ArrayList<DailyHelpsList> temp;
    //private ArrayList<DailyHelpsList> filteredHelpsList = new ArrayList<>();

    public DailyHelpsAdapter(Context context, ArrayList<DailyHelpsList> arrayList) {
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

                    ArrayList<DailyHelpsList> filteredList = new ArrayList<>();
                    for (DailyHelpsList row : temp) {

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
                arrayList = (ArrayList<DailyHelpsList>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position,ArrayList<DailyHelpsList> arrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daily_helps, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DailyHelpsList helpsList = arrayList.get(i);
        viewHolder.name.setText(helpsList.name);
        viewHolder.duty.setText(helpsList.designation);
        viewHolder.passcode.setText("Passcode: " + helpsList.passcode);
//        viewHolder.time.setText(helpsList.timings);
        try {
            String diffTime = SlotDivision.differenceTime(helpsList.attendence.inTime);
            viewHolder.time.setText(diffTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img_pic;
        private TextView name, passcode, duty, time;
        private Button btn_out;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_pic = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.txt_title_name);
            duty = itemView.findViewById(R.id.txt_gusetType);
            passcode = itemView.findViewById(R.id.txt_AddressFrom);
            time = itemView.findViewById(R.id.txt_timeSince);
            btn_out = itemView.findViewById(R.id.btn_out);

            btn_out.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null)
                mListner.onItemClick(v, getAdapterPosition(),arrayList);
        }
    }
}
