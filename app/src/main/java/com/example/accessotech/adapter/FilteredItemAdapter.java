package com.example.accessotech.adapter;

import static android.view.View.GONE;

import static com.example.accessotech.activity.SearchActivity.CATEGORY_OPTION;
import static com.example.accessotech.activity.SearchActivity.MANUFACTURER_OPTION;
import static com.example.accessotech.adapter.ItemFilter.CATEGORY;
import static com.example.accessotech.adapter.ItemFilter.FROM_PRICE;
import static com.example.accessotech.adapter.ItemFilter.MANUFACTURER;
import static com.example.accessotech.adapter.ItemFilter.RATING;
import static com.example.accessotech.adapter.ItemFilter.TO_PRICE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accessotech.R;
import com.example.accessotech.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteredItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private Context context;
    private List<Item> originalItems, filteredItems;

    public FilteredItemAdapter(Context context, List<Item> originalItems) {
        this.context = context;
        this.originalItems = originalItems;
        this.filteredItems = new ArrayList<>(originalItems);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = filteredItems.get(position);
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
        return filteredItems.size();
    }

    public void filter(String searchText, ItemFilter itemFilter) {
        filteredItems.clear();

        String category = itemFilter.getCategory(), manufacturer = itemFilter.getManufacturer();
        int rating = itemFilter.getRating(), fromPrice = itemFilter.getFromPrice(), toPrice = itemFilter.getToPrice();

        boolean[] mask = new boolean[5];

        searchText = searchText.toLowerCase();
        for (Item item : originalItems) {
            reset(mask);
            if (!item.getName().toLowerCase().contains(searchText))
                continue;
            if (!category.equals(CATEGORY_OPTION) && !item.getCategory().equals(category))
                mask[CATEGORY] = false;
            if (!manufacturer.equals(MANUFACTURER_OPTION) && !item.getManufacturer().equals(manufacturer))
                mask[MANUFACTURER] = false;
            if (rating != 0 && item.getRating() != rating)
                mask[RATING] = false;
            if (fromPrice != 0 && item.getUnitPrice() < fromPrice)
                mask[FROM_PRICE] = false;
            if (toPrice != 0 && item.getUnitPrice() > toPrice)
                mask[TO_PRICE] = false;

            boolean addItem = mask[CATEGORY] & mask[MANUFACTURER] & mask[RATING] & mask[FROM_PRICE] & mask[TO_PRICE];
            if (addItem)
                filteredItems.add(item);
        }
        notifyDataSetChanged();
    }

    private void reset(boolean[] mask) {
        Arrays.fill(mask, true);
    }
}















