package com.example.accessotech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accessotech.R;
import com.example.accessotech.model.Cart;
import com.example.accessotech.model.CartItem;
import com.example.accessotech.util.AlertManager;

import java.util.List;
import java.util.Set;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ItemViewHolder> {

    private final Context context;
    private final List<CartItem> cartItems;

    public CartItemAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new CartItemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.txtViewItemName.setText(cartItem.getItem().getName());
        // TODO: replace it with discounted price
        holder.txtViewItemPrice.setText(cartItem.getItem().getUnitPrice()+"");
        holder.txtViewItemQuantity.setText(cartItem.getQuantityInCart());
        // TODO: connect imgItem with item resource id image

        addButtonClickListeners(holder, cartItem);
    }

    private void addButtonClickListeners(@NonNull ItemViewHolder holder, CartItem cartItem) {
        holder.btnDeleteItem.setOnClickListener(v -> {
            AlertManager.showDialog(
                    context,
                    "Remove Item",
                    "Are you sure you want to remove this item?",
                    () -> {
                        Cart.getInstance().removeItem(cartItem.getItem());
                        notifyDataSetChanged();
                    }
            );
        });
        holder.btnIncrementQuantity.setOnClickListener(v -> {
            boolean incremented = Cart.getInstance().incrementQuantity(cartItem);
            holder.txtViewItemQuantity.setText(cartItem.getQuantityInCart());
//            if (!incremented)
//                Toast.makeText(context, "You can't add more", Toast.LENGTH_SHORT).show();
        });
        holder.btnDecrementQuantity.setOnClickListener(v -> {
            Cart.getInstance().decrementQuantity(cartItem);
            holder.txtViewItemQuantity.setText(cartItem.getQuantityInCart());
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItem;
        ImageButton btnIncrementQuantity, btnDecrementQuantity, btnDeleteItem;
        TextView txtViewItemName, txtViewItemPrice, txtViewItemQuantity;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgCartItem);
            btnIncrementQuantity = itemView.findViewById(R.id.btnIncrementQuantity);
            btnDecrementQuantity = itemView.findViewById(R.id.btnDecrementQuantity);
            btnDeleteItem = itemView.findViewById(R.id.btnRemoveCartItem);
            txtViewItemName = itemView.findViewById(R.id.txtViewCartItemName);
            txtViewItemPrice = itemView.findViewById(R.id.txtViewCartItemPrice);
            txtViewItemQuantity = itemView.findViewById(R.id.txtViewCartItemQuantity);
        }

    }

}
