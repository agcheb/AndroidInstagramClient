package com.agcheb.instagramclient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.agcheb.instagramclient.Pojo.Picture;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.Holder> {

    private List<Picture> mPictures;
    public ImageAdapter(List<Picture> pictures){
        mPictures = pictures;


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        Picture currPic = mPictures.get(i);
        Picasso.with(holder.itemView.getContext()).load(currPic.getURL()).into(holder.mPhoto1);

    }

    @Override
    public int getItemCount() {
        return mPictures.size();

    }

    public Picture getSelectedPicture(int position) {
        return mPictures.get(position);
    }




    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mPhoto1;
        public Holder(View itemView) {
            super(itemView);

            mPhoto1 = (ImageView)itemView.findViewById(R.id.image1);
        }

        @Override
        public void onClick(View v) {


        }
    }


}
