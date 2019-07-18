package com.example.listactivitydetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.ListViewHolder> {

    private onItemClickCallback onItemClickCallback;

    public void setOnItemClickedCallback(onItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }


    private ArrayList<Food> listFood;

    public ListFoodAdapter(ArrayList<Food> list){
        this.listFood = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_food, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Food food = listFood.get(position);

        Glide.with(holder.itemView.getContext())
                .load(listFood.get(position).getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);

        holder.tvName.setText(food.getName());
        holder.tvPrice.setText("Rp."+food.getPrice());
        holder.tvAddress.setText(food.getAddress());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.OnItemClicked(listFood.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvName, tvPrice, tvAddress;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvPrice = itemView.findViewById(R.id.tv_item_price);
            tvAddress = itemView.findViewById(R.id.tv_item_address);
        }
    }

    public interface onItemClickCallback{
        void OnItemClicked(Food food);
    }
}
