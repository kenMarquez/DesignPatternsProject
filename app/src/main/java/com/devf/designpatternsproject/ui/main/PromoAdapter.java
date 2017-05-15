package com.devf.designpatternsproject.ui.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devf.designpatternsproject.R;
import com.devf.designpatternsproject.models.Promotion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ken on 26/04/17.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoViewHolder> {

    private List<Promotion> mPromotions;
    private OnItemSelected mListener;

    public PromoAdapter(List<Promotion> elements, OnItemSelected listener) {
        this.mPromotions = elements;
        this.mListener = listener;
    }

    @Override
    public PromoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promo, parent, false);
        PromoViewHolder promoViewHolder = new PromoViewHolder(itemView);
        return promoViewHolder;
    }

    @Override
    public void onBindViewHolder(PromoViewHolder holder, final int position) {
        holder.bindPromo(mPromotions.get(position));
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemSelected(mPromotions.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPromotions.size();
    }

    public class PromoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_promo_tv_title)
        TextView tvTitle;

        @BindView(R.id.item_promo_tv_promo)
        TextView tvPromo;

        View rootView;

        public PromoViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bindPromo(Promotion promo) {
            tvTitle.setText(promo.getTitle());
            tvPromo.setText(promo.getDiscount() + "%");
        }


    }
}
