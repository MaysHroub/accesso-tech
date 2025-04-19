package com.example.accessotech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accessotech.R;
import com.example.accessotech.activity.CartActivity;
import com.example.accessotech.model.Cart;
import com.example.accessotech.model.CartItem;
import com.example.accessotech.util.DialogManager;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ItemViewHolder> {

    private final Context context;

    public CartItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new CartItemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CartItem cartItem = Cart.getInstance().getAt(position);
        holder.txtViewItemName.setText(cartItem.getItem().getName());
        holder.txtViewItemPrice.setText(String.format("%.2f", cartItem.getItem().getDiscountedPrice()));
        holder.txtViewItemQuantity.setText(cartItem.getQuantityInCart()+"");
        holder.imgItem.setImageResource(cartItem.getItem().getImgResId());
        addButtonClickListeners(holder, cartItem);
    }

    private void addButtonClickListeners(@NonNull ItemViewHolder holder, CartItem cartItem) {
        holder.btnRemoveItem.setOnClickListener(v -> {
            DialogManager.showDialog(
                    context,
                    "Remove Item",
                    "Are you sure you want to remove this item?",
                    () -> {
                        Cart.getInstance().remove(cartItem);
                        notifyDataSetChanged();
                        if (getItemCount() == 0) {
                            ((CartActivity) context).showCartEmptyText();
                            ((CartActivity) context).disableCheckoutButton();
                        }
                        ((CartActivity) context).updateTextViewTotalPrice();
                    }
            );
        });
        holder.btnIncrementQuantity.setOnClickListener(v -> {
            cartItem.incrementQuantityInCart();
            holder.txtViewItemQuantity.setText(cartItem.getQuantityInCart()+"");
            ((CartActivity) context).updateTextViewTotalPrice();
        });
        holder.btnDecrementQuantity.setOnClickListener(v -> {
            cartItem.decrementQuantityInCart();
            holder.txtViewItemQuantity.setText(cartItem.getQuantityInCart()+"");
            ((CartActivity) context).updateTextViewTotalPrice();
        });
    }

    @Override
    public int getItemCount() {
        return Cart.getInstance().size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItem;
        ImageButton btnIncrementQuantity, btnDecrementQuantity, btnRemoveItem;
        TextView txtViewItemName, txtViewItemPrice, txtViewItemQuantity;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgCartItem);
            btnIncrementQuantity = itemView.findViewById(R.id.btnIncrementQuantity);
            btnDecrementQuantity = itemView.findViewById(R.id.btnDecrementQuantity);
            btnRemoveItem = itemView.findViewById(R.id.btnRemoveCartItem);
            txtViewItemName = itemView.findViewById(R.id.txtViewCartItemName);
            txtViewItemPrice = itemView.findViewById(R.id.txtViewCartItemPrice);
            txtViewItemQuantity = itemView.findViewById(R.id.txtViewCartItemQuantity);
        }

    }

}
