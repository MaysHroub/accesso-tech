package com.example.accessotech.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accessotech.R;
import com.example.accessotech.model.Item;

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
        holder.txtViewDiscount.setText(String.format("%d%% OFF", item.getDiscount()));
        // TODO: Fix the image path
        holder.imgItem.setImageURI(Uri.parse(item.getImgUrl()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewName, txtViewPrice, txtViewRating, txtViewQuantityInStock, txtViewDiscount;
        ImageView imgItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewName = itemView.findViewById(R.id.txtViewItemName);
            txtViewPrice = itemView.findViewById(R.id.txtViewItemPrice);
            txtViewRating = itemView.findViewById(R.id.txtViewItemRating);
            txtViewQuantityInStock = itemView.findViewById(R.id.txtViewItemQuantityInStock);
            txtViewDiscount = itemView.findViewById(R.id.txtViewItemDiscount);
            imgItem = itemView.findViewById(R.id.imgItem);
        }
    }

}
