package com.example.accessotech.activity;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accessotech.R;
import com.example.accessotech.adapter.FilteredItemAdapter;
import com.example.accessotech.adapter.ItemFilter;
import com.example.accessotech.dao.ItemDao;
import com.example.accessotech.dao.ItemDaoImpl;
import com.example.accessotech.util.SharedPrefsManager;

public class SearchActivity extends AppCompatActivity {

    public static final String CATEGORY_OPTION = "Category", MANUFACTURER_OPTION = "Manufacturer", RATING_OPTION = "Rating";

    private EditText edtTxtSearch, edtTxtFrom, edtTxtTo;
    private Spinner spnrCategory, spnrManufacturer, spnrRating;
    private RecyclerView recyclerViewFilteredItems;
    private LinearLayoutCompat filterLayout;
    private FilteredItemAdapter adapter;
    private ItemDao itemDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        itemDao = new ItemDaoImpl(this);
        setUpViews();
        fillViewsWithData();
    }

    private void setUpViews() {
        edtTxtSearch = findViewById(R.id.edtTxtSearch);
        edtTxtFrom = findViewById(R.id.edtTxtFromPrice);
        edtTxtTo = findViewById(R.id.edtTxtToPrice);
        spnrCategory = findViewById(R.id.spinnerCategory);
        spnrManufacturer = findViewById(R.id.spinnerCompany);
        spnrRating = findViewById(R.id.spinnerRating);
        recyclerViewFilteredItems = findViewById(R.id.recyclerViewFilteredItems);
        filterLayout = findViewById(R.id.filterLayout);
    }

    private void fillViewsWithData() {
        populateSpinners();
        populateRecyclerView();
    }

    private void populateSpinners() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                itemDao.findAllCategories()
        );
        categoryAdapter.insert(CATEGORY_OPTION, 0);
        spnrCategory.setAdapter(categoryAdapter);

        ArrayAdapter<String> manufacturerAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                itemDao.findAllManufacturers()
        );
        manufacturerAdapter.insert(MANUFACTURER_OPTION, 0);
        spnrManufacturer.setAdapter(manufacturerAdapter);

        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                itemDao.findAllRatings()
        );
        ratingAdapter.insert(RATING_OPTION, 0);
        spnrRating.setAdapter(ratingAdapter);
    }

    private void populateRecyclerView() {
        adapter = new FilteredItemAdapter(this, itemDao.findAllItems());
        recyclerViewFilteredItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFilteredItems.setAdapter(adapter);
    }

    public void searchForItemAndUpdateRecyclerView(View view) {
        ItemFilter itemFilter = new ItemFilter(
                spnrCategory.getSelectedItem().toString(),
                spnrManufacturer.getSelectedItem().toString(),
                (spnrRating.getSelectedItem().toString().equals(RATING_OPTION)) ? 0 : Integer.parseInt(spnrRating.getSelectedItem().toString()),
                (edtTxtFrom.getText().toString().isEmpty()) ? 0 : Integer.parseInt(edtTxtFrom.getText().toString()),
                (edtTxtTo.getText().toString().isEmpty()) ? 0 : Integer.parseInt(edtTxtTo.getText().toString())
        );

        adapter.filter(edtTxtSearch.getText().toString(), itemFilter);
    }

    public void navigateToHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigateToCartActivity(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
        finish();
    }

    public void navigateToProfileActivity(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
        finish();
    }

    public void showFilterOptions(View view) {
        filterLayout.setVisibility(VISIBLE);
    }
}















