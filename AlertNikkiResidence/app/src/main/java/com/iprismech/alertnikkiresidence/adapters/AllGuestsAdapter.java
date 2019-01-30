package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.response.GuestsList;

import java.util.ArrayList;

public class AllGuestsAdapter extends RecyclerView.Adapter<AllGuestsAdapter.Viewholder> {
    private Context context;
    private ArrayList<GuestsList> guestList;

    public AllGuestsAdapter(Context context, ArrayList<GuestsList> guestList) {
        this.context = context;
        this.guestList = guestList;
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
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_guest_list, null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        GuestsList model = guestList.get(i);
        viewholder.txtGuestName.setText(model.name);
        viewholder.txtArrivingOnDate.setText("Arrving On " + model.fromDateTime + ", " + model.day);
        if (model.vehicleNo != null) {
            viewholder.txtVehicleNumber.setVisibility(View.VISIBLE);
            viewholder.txtVehicleNumber.setText(model.vehicleNo);

        }
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgGuestPic;
        LinearLayout imgDelete, imgEdit, imgCall;
        TextView txtGuestName, txtArrivingOnDate, txtVehicleNumber;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imgGuestPic = itemView.findViewById(R.id.imgGuestPic);
            imgDelete = itemView.findViewById(R.id.imgDeleteGuest);
            imgEdit = itemView.findViewById(R.id.imgGuestEdit);
            imgCall = itemView.findViewById(R.id.imgCallGuest);

            txtGuestName = itemView.findViewById(R.id.txtGuestName);
            txtArrivingOnDate = itemView.findViewById(R.id.txtGuestArrivingDate);
            txtVehicleNumber = itemView.findViewById(R.id.txtVehicleNumber);

            imgDelete.setOnClickListener(this);
            imgCall.setOnClickListener(this);
            imgEdit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
