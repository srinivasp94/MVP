package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.pojo.ContactModel;

import java.util.ArrayList;

public class ConrtactSingleAdapter extends RecyclerView.Adapter<ConrtactSingleAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ContactModel> contactList;

    public ConrtactSingleAdapter(Context context, ArrayList<ContactModel> contactList) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final ContactModel model = contactList.get(i);

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

        viewHolder.selectCheckBox.setChecked(model.isContactChecked());

        viewHolder.selectCheckBox.setTag(model);

        viewHolder.selectCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                ContactModel contact = (ContactModel) cb.getTag();

                contact.setContactChecked(cb.isChecked());
                contactList.get(i).setContactChecked(cb.isChecked());

              /*  Toast.makeText(
                        v.getContext(),
                        "Clicked on Checkbox: " + cb.getText() + " is "
                                + cb.isChecked(), Toast.LENGTH_LONG).show();*/
            }
        });


/*
        viewHolder.selectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (model.isContactChecked()==false) {
                    viewHold
                } else {

                }
            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatCheckBox selectCheckBox;
        TextView contactName, contactPhone;
        ImageView contactPic;
        RelativeLayout rlContact;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactPic = itemView.findViewById(R.id.imgContact);
            contactName = itemView.findViewById(R.id.txtContactName);
            contactPhone = itemView.findViewById(R.id.txtContactNumber);
            selectCheckBox = itemView.findViewById(R.id.checkContact);
            rlContact = itemView.findViewById(R.id.rlContact);
            selectCheckBox.setVisibility(View.GONE);

            rlContact.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
