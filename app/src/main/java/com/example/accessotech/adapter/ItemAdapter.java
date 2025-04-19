package com.example.accessotech.adapter;

import static android.view.View.GONE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accessotech.R;
import com.example.accessotech.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

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
        holder.txtViewPrice.setText(String.format("%.2f", item.getDiscountedPrice()));
        holder.txtViewRating.setText(item.getRating()+"");
        holder.txtViewQuantityInStock.setText(item.getQuantityInStock()+"");
        if (item.getDiscount() > 0)
            holder.txtViewDiscount.setText(String.format("%d%% OFF", item.getDiscount()));
        else
            holder.txtViewDiscount.setVisibility(GONE);

        holder.imgItem.setImageResource(item.getImgResId());

        holder.addClickListener(context, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
