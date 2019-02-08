package com.iprismech.alertnikkiresidence.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    ImageView userAvatar;
    TextView username;

    public UserViewHolder(View itemView) {
        super(itemView);

        userAvatar = itemView.findViewById(R.id.imageview_profile);
        username = itemView.findViewById(R.id.textview_name);
    }
}
