package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.pojo.ContactModel;

import java.util.ArrayList;

public class ContactViewAdapter extends RecyclerView.Adapter<ContactViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ContactModel> contactList;

    public ContactViewAdapter(Context context, ArrayList<ContactModel> contactList) {
        this.context = context;
        this.contactList = contactList;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_contact, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ContactModel model = contactList.get(i);
        if (!model.getContactName().equals("") || model.getContactName() != null) {
            viewHolder.contactName.setText(model.getContactName());
        } else {
            viewHolder.contactName.setText("No Name");
        }
        if (!model.getContactNumber().equals("") || model.getContactNumber() != null) {
            viewHolder.contactPhone.setText(model.getContactNumber());
        } else {
            viewHolder.contactPhone.setText("No Contact");
        }

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView contactName, contactPhone;
        ImageView contactPic, imgDelete, imgEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactPic = itemView.findViewById(R.id.imgContact);
            contactName = itemView.findViewById(R.id.txtContactName);
            contactPhone = itemView.findViewById(R.id.txtContactNumber);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);

            imgDelete.setOnClickListener(this);
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
