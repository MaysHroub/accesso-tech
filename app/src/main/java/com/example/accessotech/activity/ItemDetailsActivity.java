package com.example.accessotech.activity;

import static android.view.View.GONE;

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

import com.example.accessotech.R;
import com.example.accessotech.model.Cart;
import com.example.accessotech.model.Item;
import com.google.gson.Gson;

public class ItemDetailsActivity extends AppCompatActivity {

    // TODO: add + - buttons for specifying quantity added to cart

    private TextView txtViewName, txtViewRating, txtViewPrice,
            txtViewQuantityInStock, txtViewDiscount, txtViewDescription;
    private Button btnAddToCart;
    private Item item;
    private String previousActivityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fetchData();
        setUpViews();
        fillViews();
    }

    private void fetchData() {
        Intent intent = getIntent();
        previousActivityName = intent.getStringExtra("activity");
        String json = intent.getStringExtra("item");
        Gson gson = new Gson();
        item = gson.fromJson(json, Item.class);
    }

    private void setUpViews() {
        txtViewName = findViewById(R.id.txtViewName);
        txtViewRating = findViewById(R.id.txtViewRating);
        txtViewPrice = findViewById(R.id.txtViewPrice);
        txtViewQuantityInStock = findViewById(R.id.txtViewQuantityInStock);
        txtViewDiscount = findViewById(R.id.txtViewDiscount);
        txtViewDescription = findViewById(R.id.txtViewDescription);
        btnAddToCart = findViewById(R.id.btnAddToCart);
    }

    private void fillViews() {
        // TODO: link the imageview with the resource id in item object
        // TODO: show the price with discount instead of original price

        txtViewName.setText(item.getName());
        txtViewRating.setText(item.getRating()+"");
        txtViewPrice.setText(item.getUnitPrice()+"");
        txtViewQuantityInStock.setText(item.getQuantityInStock()+"");
        txtViewDescription.setText(item.getDescription());

        if (item.getDiscount() > 0)
            txtViewDiscount.setText(String.format("%d%% OFF", item.getDiscount()));
        else
            txtViewDiscount.setVisibility(GONE);

        if (item.getQuantityInStock() == 0)
            btnAddToCart.setEnabled(false);

        if (Cart.getInstance().hasItem(item)) {
            btnAddToCart.setEnabled(false);
            btnAddToCart.setText(R.string.item_is_added);
        }
    }

    public void addItemToCart(View view) {
        Cart.getInstance().addItem(item);
        btnAddToCart.setEnabled(false);
        btnAddToCart.setText(R.string.item_is_added);
    }

    public void backToPreviousActivity(View view) {
        Intent intent;
        if (previousActivityName.equals(HomeActivity.class.getName()))
            intent = new Intent(ItemDetailsActivity.this, HomeActivity.class);
        else
            intent = new Intent(ItemDetailsActivity.this, SearchActivity.class);
        startActivity(intent);
        finish();
    }
}
















