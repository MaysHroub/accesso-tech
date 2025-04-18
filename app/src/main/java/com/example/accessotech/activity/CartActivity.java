package com.example.accessotech.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class CartActivity extends AppCompatActivity {

    private TextView txtViewTotalPrice;
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
    }

    private void setUpViews() {
        // TODO: rename components in xml
        txtViewTotalPrice = findViewById(R.id.txtTotalPrice);
        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems);
        btnCheckout = findViewById(R.id.btnCheckout);
        if (Cart.getInstance().isEmpty())
            btnCheckout.setEnabled(false);
        updateTextViewTotalPrice();
    }

    private void populateRecyclerView() {
        CartItemAdapter adapter = new CartItemAdapter(this, Cart.getInstance().getCartItems());
        recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCartItems.setAdapter(adapter);
    }

    public void updateTextViewTotalPrice() {
        txtViewTotalPrice.setText(Cart.getInstance().getTotalPrice()+"");
    }

    public void checkoutItems(View view) {
        Cart.getInstance().clear();
        recyclerViewCartItems.getAdapter().notifyDataSetChanged();
        txtViewTotalPrice.setText("0.0");
        Toast.makeText(this, "Checkout successful! Thank you for your purchase", Toast.LENGTH_SHORT).show();
    }

    public void clearCart(View view) {
        Cart.getInstance().reset();
        recyclerViewCartItems.getAdapter().notifyDataSetChanged();
        txtViewTotalPrice.setText("0.0");
    }

    public void backToPrevActivity(View view) {
        finish();
    }

}

