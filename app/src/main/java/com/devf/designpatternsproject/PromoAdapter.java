package com.devf.designpatternsproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Ken on 26/04/17.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoViewHolder> {

    private List<String> mElements;

    public PromoAdapter(List<String> elements) {
        this.mElements = elements;
    }

    @Override
    public PromoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promo, parent, false);
        PromoViewHolder promoViewHolder = new PromoViewHolder(itemView);
        return promoViewHolder;
    }

    @Override
    public void onBindViewHolder(PromoViewHolder holder, int position) {
        holder.bindPromo();
    }

    @Override
    public int getItemCount() {
        return mElements.size();
    }

    public class PromoViewHolder extends RecyclerView.ViewHolder {

        public PromoViewHolder(View itemView) {
            super(itemView);
        }

        public void bindPromo() {

        }
    }
}
