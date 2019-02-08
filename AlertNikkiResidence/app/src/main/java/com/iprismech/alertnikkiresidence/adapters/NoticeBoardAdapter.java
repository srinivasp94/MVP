package com.iprismech.alertnikkiresidence.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.activity.NoticeBoardActivity;
import com.iprismech.alertnikkiresidence.pojo.NoticeBoardPojo;

public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.ViewHolder> {
    private Context context;
    private NoticeBoardPojo noticeBoardPojo;

    public NoticeBoardAdapter(NoticeBoardActivity noticeBoardActivity, NoticeBoardPojo noticeBoardPojo) {
        this.context = noticeBoardActivity;
        this.noticeBoardPojo = noticeBoardPojo;
    }

    @NonNull
    @Override
    public NoticeBoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notice_board, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeBoardAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_date.setText(noticeBoardPojo.getResponse().get(i).getModified_on());
        viewHolder.tv_content_disc.setText(noticeBoardPojo.getResponse().get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return noticeBoardPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date, tv_content_disc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_content_disc = itemView.findViewById(R.id.tv_content_disc);
        }
    }
}
