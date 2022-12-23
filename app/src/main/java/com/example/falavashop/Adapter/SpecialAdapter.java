package com.example.falavashop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.falavashop.Interface.ItemClickListener;
import com.example.falavashop.Model.Results;
import com.example.falavashop.R;

import java.util.List;

public class SpecialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    List<Results> listProduct;


    public SpecialAdapter(Context context, List<Results> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_main, parent, false);
        return new SpecialProductHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SpecialProductHolder){
            SpecialProductHolder specialProductHolder = (SpecialProductHolder) holder;
            Results pr = listProduct.get(position);
            specialProductHolder.pr_title.setText(pr.title.trim()); // Title


            Glide.with(context).load(pr.image).into(specialProductHolder.pr_image); // Image

            specialProductHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if(!isLongClick){

                    }
                }
            });

        }else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public static class SpecialProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pr_title;
        ImageView pr_image;
        private ItemClickListener itemClickListener;

        public SpecialProductHolder(@NonNull View itemView) {
            super(itemView);

            pr_title = itemView.findViewById(R.id.main_pr_title);
            pr_image = itemView.findViewById(R.id.main_pr_image);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }

}
