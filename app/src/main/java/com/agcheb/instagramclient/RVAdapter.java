package com.agcheb.instagramclient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by agcheb on 20.03.18.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {
    private List<PhotoCard> mListCard;

    public RVAdapter(List<PhotoCard> mListCard) {
        this.mListCard = mListCard;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewphotos,null);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        PhotoCard currentPhotoCard = mListCard.get(position);
        holder.imageView.setImageResource(currentPhotoCard.getmImageID());
    }

    @Override
    public int getItemCount() {
        return mListCard.size();
    }
    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public CardViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photoview);
        }
    }
}
