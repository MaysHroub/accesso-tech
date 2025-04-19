package com.example.accessotech.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accessotech.R;
import com.example.accessotech.activity.ItemDetailsActivity;
import com.example.accessotech.model.Item;
import com.google.gson.Gson;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView txtViewName, txtViewPrice, txtViewRating, txtViewQuantityInStock, txtViewDiscount;
    ImageView imgItem;
    private final View itemView;

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
