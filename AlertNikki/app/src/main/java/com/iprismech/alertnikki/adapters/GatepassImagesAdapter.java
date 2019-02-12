package com.iprismech.alertnikki.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iprismech.alertnikki.R;

import com.iprismech.alertnikki.Response.DigitalGateImagesList;
import com.iprismech.alertnikki.utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GatepassImagesAdapter extends RecyclerView.Adapter<GatepassImagesAdapter.ViewHolder> {
    private Context context;
    private List<DigitalGateImagesList> imagesLists;

    public GatepassImagesAdapter(Context context, List<DigitalGateImagesList> imagesLists) {
        this.context = context;
        this.imagesLists = imagesLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DigitalGateImagesList images = imagesLists.get(i);
        Picasso.with(context).load(Constants.BASE_IMAGE_URL+ images.image).error(R.drawable.dummy).into(viewHolder.img_maiditem);
    }

    @Override
    public int getItemCount() {
        return imagesLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_maiditem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_maiditem = itemView.findViewById(R.id.img_maiditem);
        }
    }
}
