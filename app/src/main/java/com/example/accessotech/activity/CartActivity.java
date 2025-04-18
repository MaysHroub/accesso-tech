package com.example.accessotech.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ImageView imgBack, imgClearCart;
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
        imgBack = findViewById(R.id.imgBack);
        imgClearCart = findViewById(R.id.imgRemoveCart);
        btnCheckout = findViewById(R.id.btnCheckout);
        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems);
    }

    private void populateRecyclerView() {
        CartItemAdapter adapter = new CartItemAdapter(this, Cart.getInstance().getCartItems());
        recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCartItems.setAdapter(adapter);
    }

}

