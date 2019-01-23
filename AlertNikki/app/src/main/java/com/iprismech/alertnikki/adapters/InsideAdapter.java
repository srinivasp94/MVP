package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismech.alertnikki.R;
import com.iprismech.alertnikki.Response.ResponseVisitMember;

import java.util.ArrayList;

public class InsideAdapter extends RecyclerView.Adapter<InsideAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ResponseVisitMember> visitMembers;

    public InsideAdapter(Context context, ArrayList<ResponseVisitMember> visitMembers) {
        this.context = context;
        this.visitMembers = visitMembers;
    }

    @NonNull
    @Override
    public InsideAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_inside, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InsideAdapter.ViewHolder holder, int i) {
        ResponseVisitMember member = visitMembers.get(i);

        holder.guestName.setText(member.name);
        holder.type.setText(member.type);
        holder.invitedBy.setText(member.userName);
        holder.address.setText(member.flatName);
        holder.timesince.setText(member.inTime + "");
    }

    @Override
    public int getItemCount() {
        return visitMembers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView guestName, type, invitedBy, address, timesince;
        TextView out;
        LinearLayout ll_rootVisitor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            guestName = itemView.findViewById(R.id.txt_title_name);
            type = itemView.findViewById(R.id.txt_gusetType);
            invitedBy = itemView.findViewById(R.id.txt_invitedBy);
            address = itemView.findViewById(R.id.txt_AddressFrom);
            timesince = itemView.findViewById(R.id.txt_timeSince);
            out = itemView.findViewById(R.id.btn_out);
            ll_rootVisitor= itemView.findViewById(R.id.ll_rootVisitor);

            out.setOnClickListener(this);
            ll_rootVisitor.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}