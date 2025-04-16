package com.example.accessotech.adapter;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accessotech.R;
import com.example.accessotech.activity.ItemDetailsActivity;
import com.example.accessotech.model.Item;
import com.google.gson.Gson;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> items;
    private Context context;

    public ItemAdapter(Context context, List<Item> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.txtViewName.setText(item.getName());
        holder.txtViewPrice.setText(item.getUnitPrice()+"");
        holder.txtViewRating.setText(item.getRating()+"");
        holder.txtViewQuantityInStock.setText(item.getQuantityInStock()+"");
        if (item.getDiscount() > 0)
            holder.txtViewDiscount.setText(String.format("%d%% OFF", item.getDiscount()));
        else
            holder.txtViewDiscount.setVisibility(GONE);
        // TODO: Fix the image path and replace it with resource id
        // holder.imgItem.setImageURI(Uri.parse(item.getImgUrl()));

        holder.addClickListener(context, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewName, txtViewPrice, txtViewRating, txtViewQuantityInStock, txtViewDiscount;
        ImageView imgItem;
        private View itemView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            txtViewName = itemView.findViewById(R.id.txtViewItemName);
            txtViewPrice = itemView.findViewById(R.id.txtViewItemPrice);
            txtViewRating = itemView.findViewById(R.id.txtViewItemRating);
            txtViewQuantityInStock = itemView.findViewById(R.id.txtViewItemQuantityInStock);
            txtViewDiscount = itemView.findViewById(R.id.txtViewItemDiscount);
            imgItem = itemView.findViewById(R.id.imgItem);
        }

        public void addClickListener(Context context, Item item) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), ItemDetailsActivity.class);
                Gson gson = new Gson();
                String json = gson.toJson(item);
                intent.putExtra("item", json);
                context.startActivity(intent);
            });
        }

    }

}
