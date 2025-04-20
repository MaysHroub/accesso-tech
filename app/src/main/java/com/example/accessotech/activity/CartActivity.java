package com.example.accessotech.activity;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accessotech.R;
import com.example.accessotech.adapter.CartItemAdapter;
import com.example.accessotech.model.Cart;
import com.example.accessotech.dao.ItemDao;
import com.example.accessotech.dao.ItemDaoImpl;
import com.example.accessotech.model.CartItem;
import com.example.accessotech.model.Item;
import com.example.accessotech.util.DialogManager;
import com.example.accessotech.util.SharedPrefsManager;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private TextView txtViewTotalPrice, txtViewEmptyCart;
    private Button btnCheckout;
    private RecyclerView recyclerViewCartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUpViews();
        populateRecyclerView();

        SharedPrefsManager.loadCart(this);
    }

    private void setUpViews() {
        txtViewTotalPrice = findViewById(R.id.txtViewTotalPrice);
        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems);
        txtViewEmptyCart = findViewById(R.id.txtViewEmptyCart);
        btnCheckout = findViewById(R.id.btnCheckout);
        if (Cart.getInstance().isEmpty()) {
            disableCheckoutButton();
            showCartEmptyText();
        }
        updateTextViewTotalPrice();
    }

    private void populateRecyclerView() {
        CartItemAdapter adapter = new CartItemAdapter(this);
        recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCartItems.setAdapter(adapter);
    }

    public void checkoutItems(View view) {
        ItemDao itemDao = new ItemDaoImpl(this);
        List<Item> items = itemDao.findAllItems();

        for (Item item : items) {
            int idx = Cart.getInstance().indexOf(new CartItem(item));
            if (idx != -1) {
                CartItem cartItem = Cart.getInstance().getAt(idx);
                item.setQuantityInStock(item.getQuantityInStock() - cartItem.getQuantityInCart());
            }
        }
        itemDao.saveAllItems(items);

        int cartSize = Cart.getInstance().size();
        Cart.getInstance().clear();
        recyclerViewCartItems.getAdapter().notifyItemRangeRemoved(0, cartSize);
        updateTextViewTotalPrice();
        showCartEmptyText();
        disableCheckoutButton();
        Toast.makeText(this, "Checkout successful! Thank you for your purchase", Toast.LENGTH_SHORT).show();
    }

    public void clearCart(View view) {
        if (Cart.getInstance().isEmpty()) return;
        DialogManager.showDialog(this, "Clear Cart", "Are you sure you want to clear your cart?", () -> {
            int cartSize = Cart.getInstance().size();
            Cart.getInstance().clear();
            recyclerViewCartItems.getAdapter().notifyItemRangeRemoved(0, cartSize);
            updateTextViewTotalPrice();
            showCartEmptyText();
            disableCheckoutButton();
        });
    }

    public void updateTextViewTotalPrice() {
        txtViewTotalPrice.setText(String.format("%.2f", Cart.getInstance().getTotalPrice()));
    }

    public void disableCheckoutButton() {
        btnCheckout.setEnabled(false);
        btnCheckout.setText("x");
    }

    public void showCartEmptyText() {
        txtViewEmptyCart.setVisibility(VISIBLE);
    }

    public void navigateToHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigateToSearchActivity(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigateToProfileActivity(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPrefsManager.saveCart(this);
    }

}

