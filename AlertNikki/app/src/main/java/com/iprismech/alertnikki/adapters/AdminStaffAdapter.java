package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.StaffResponse;
import com.iprismech.alertnikki.utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminStaffAdapter extends RecyclerView.Adapter<AdminStaffAdapter.ViewHolder> {
    private Context context;
    private ArrayList<StaffResponse> arrayList;

    public AdminStaffAdapter(Context context, ArrayList<StaffResponse> arrayList) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_staff, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        StaffResponse staffResponse = arrayList.get(i);
        viewHolder.guestName.setText(staffResponse.name);
        viewHolder.type.setText(staffResponse.designation);
        viewHolder.invitedBy.setText("Passcode: " + staffResponse.passcode);
        viewHolder.address.setText("Entry Time: " + staffResponse.dateOfJoining);
        viewHolder.timesince.setText(staffResponse.timings);
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + staffResponse.image.replace("\\", "")).
                error(R.drawable.dummy).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView guestName, type, invitedBy, address, timesince;
        Button out;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guestName = itemView.findViewById(R.id.txt_title_name);
            type = itemView.findViewById(R.id.txt_gusetType);
            invitedBy = itemView.findViewById(R.id.txt_invitedBy);
            address = itemView.findViewById(R.id.txt_AddressFrom);
            timesince = itemView.findViewById(R.id.txt_timeSince);
            out = itemView.findViewById(R.id.btn_out);
            image = itemView.findViewById(R.id.image);

            out.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getPosition());
            }
        }
    }
}
