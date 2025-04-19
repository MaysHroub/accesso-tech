package com.example.accessotech.activity;

import static android.view.View.GONE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.accessotech.R;
import com.example.accessotech.dao.CartDao;
import com.example.accessotech.model.Item;
import com.google.gson.Gson;

public class ItemDetailsActivity extends AppCompatActivity {

    private TextView txtViewName, txtViewRating, txtViewPrice,
            txtViewQuantityInStock, txtViewDiscount, txtViewDescription;
    private Button btnAddToCart;
    private ImageView imgItemInDetails;
    private Item item;
    private CartDao cartDao;

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
        cartDao = new CartDao(this);

        fetchData();
        setUpViews();
        fillViews();
    }

    private void fetchData() {
        Intent intent = getIntent();
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
        imgItemInDetails = findViewById(R.id.imgItemInDetails);
    }

    private void fillViews() {
        txtViewName.setText(item.getName());
        txtViewRating.setText(item.getRating()+"");
        txtViewPrice.setText(String.format("%.2f", item.getDiscountedPrice()));
        txtViewQuantityInStock.setText(item.getQuantityInStock()+"");
        txtViewDescription.setText(item.getDescription());
        imgItemInDetails.setImageResource(item.getImgResId());

        if (item.getDiscount() > 0)
            txtViewDiscount.setText(String.format("%d%% OFF", item.getDiscount()));
        else
            txtViewDiscount.setVisibility(GONE);

        if (item.getQuantityInStock() == 0) {
            btnAddToCart.setEnabled(false);
            txtViewQuantityInStock.setText(R.string.out_of_stock);
        }
        if (cartDao.hasItem(item)) {
            btnAddToCart.setEnabled(false);
            btnAddToCart.setText(R.string.item_is_added);
        }
    }

    public void addItemToCart(View view) {
        cartDao.addItem(item);
        btnAddToCart.setEnabled(false);
        btnAddToCart.setText(R.string.item_is_added);
    }

    public void backToPreviousActivity(View view) {
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cartDao.saveAllCartItems();
    }
}
















