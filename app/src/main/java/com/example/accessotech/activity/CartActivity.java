package com.example.accessotech.activity;

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
import com.example.accessotech.dao.ItemDao;
import com.example.accessotech.dao.ItemDaoImpl;
import com.example.accessotech.dao.CartDao;
import com.example.accessotech.model.CartItem;
import com.example.accessotech.model.Item;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private TextView txtViewTotalPrice;
    private RecyclerView recyclerViewCartItems;
    private CartDao cartDao;

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
        cartDao = new CartDao(this);
        setUpViews();
        populateRecyclerView();
    }

    private void setUpViews() {
        // TODO: rename components in xml
        txtViewTotalPrice = findViewById(R.id.txtTotalPrice);
        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems);
        Button btnCheckout = findViewById(R.id.btnCheckout);
        if (cartDao.isEmpty())
            btnCheckout.setEnabled(false);
        updateTextViewTotalPrice();
    }

    private void populateRecyclerView() {
        CartItemAdapter adapter = new CartItemAdapter(this, cartDao.findAllCartItems());
        recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCartItems.setAdapter(adapter);
    }

    public void updateTextViewTotalPrice() {
        txtViewTotalPrice.setText(String.format("%.2f", cartDao.getUpdatedTotalPrice()));
    }

    public void checkoutItems(View view) {
        ItemDao itemDao = new ItemDaoImpl(this);
        List<CartItem> cartItems = cartDao.findAllCartItems();
        List<Item> items = itemDao.findAllItems();

        for (CartItem cartItem : cartItems) {
            int itemIdx = items.indexOf(cartItem.getItem());
            Item item = items.get(itemIdx);
            item.setQuantityInStock(item.getQuantityInStock() - cartItem.getQuantityInCart());
        }
        itemDao.saveAllItems();
        cartDao.clear();

        recyclerViewCartItems.getAdapter().notifyDataSetChanged();
        txtViewTotalPrice.setText("0.0");
        Toast.makeText(this, "Checkout successful! Thank you for your purchase", Toast.LENGTH_SHORT).show();
    }

    public void clearCart(View view) {
        cartDao.clear();
        recyclerViewCartItems.getAdapter().notifyDataSetChanged();
        txtViewTotalPrice.setText("0.0");
    }

    @Override
    protected void onStop() {
        super.onStop();
        cartDao.saveAllCartItems();
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

}

