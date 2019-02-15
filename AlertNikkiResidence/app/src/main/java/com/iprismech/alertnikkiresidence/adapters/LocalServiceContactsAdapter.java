package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.LocalServiceContactDetails;
import com.iprismech.alertnikkiresidence.pojo.LocalServiceContactsPojo;
import com.iprismech.alertnikkiresidence.utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LocalServiceContactsAdapter extends RecyclerView.Adapter<LocalServiceContactsAdapter.ViewHolder> implements Filterable {
    private Context context;
    private LocalServiceContactsPojo localServiceContactsPojo;
    private LocalServiceContactsPojo templocalServiceContactsPojo;

    private ArrayList<LocalServiceContactsPojo.ResponseBean> responseBeans;
    private ArrayList<LocalServiceContactsPojo.ResponseBean> tempresponseBeans;


    public LocalServiceContactsAdapter(LocalServiceContactDetails localServiceContactDetails, ArrayList<LocalServiceContactsPojo.ResponseBean> localServiceContactsPojo) {
        this.context = localServiceContactDetails;
        this.responseBeans = localServiceContactsPojo;
        this.tempresponseBeans = localServiceContactsPojo;
        //this.tempresponseBeans = localServiceContactsPojo.getResponse();


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
                        responseBeans = tempresponseBeans;

                    } else {



//                    filteredHelpsList.clear();
//                    for (ResponseVisitMember visitMember: temp) {
//                        if (visitMember.name.contains(charString.toLowerCase())) {
//                            filteredHelpsList.add(visitMember);
//                        }
//                    }
//                    filteredHelpsList = arrayList;

                        ArrayList<LocalServiceContactsPojo.ResponseBean> filteredList = new ArrayList<>();
                        for (LocalServiceContactsPojo.ResponseBean row : tempresponseBeans) {

                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (row.getName().toLowerCase().contains(charString.toLowerCase() )){

                                filteredList.add(row);
                            }
                        }

                        responseBeans = filteredList;

                    }
                    FilterResults results = new FilterResults();
                    results.values = responseBeans;
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    responseBeans = (ArrayList<LocalServiceContactsPojo.ResponseBean>) results.values;
                    notifyDataSetChanged();
                }
            };
        }

        public interface OnitemClickListener {
            void onItemClick(View view, int position,ArrayList<LocalServiceContactsPojo.ResponseBean> arrayList);
        }



    @NonNull
    @Override
    public LocalServiceContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_local_service_contacts, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalServiceContactsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_service_person_name.setText(responseBeans.get(i).getName());
        viewHolder.tv_service_person_type.setText(responseBeans.get(i).getService());
        viewHolder.tv_mobile_number.setText(responseBeans.get(i).getMobile());
        //  viewHolder.rating.setText(localServiceContactsPojo.getResponse().get(i).get());

        Picasso.with(context).load(Constants.BASE_IMAGE_URL + responseBeans.get(i).getImage())
                .error(R.drawable.dummy)
                .into(viewHolder.iv_service_person);
    }

    @Override
    public int getItemCount() {
        return responseBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout ll_service_make_call;
        ImageView iv_service_person;
        TextView tv_service_person_name, tv_service_person_type, rating, tv_mobile_number;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_service_make_call = itemView.findViewById(R.id.ll_local_service_make_call);
            iv_service_person = itemView.findViewById(R.id.iv_service_person);
            tv_service_person_name = itemView.findViewById(R.id.loca_service_staff_name);
            tv_service_person_type = itemView.findViewById(R.id.local_service_type);
            rating = itemView.findViewById(R.id.local_service_staff_rating);
            tv_mobile_number = itemView.findViewById(R.id.loca_service_mobile);
            ll_service_make_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + responseBeans.get(getAdapterPosition()).getMobile()));
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition(),responseBeans);
            }

        }
    }
}
