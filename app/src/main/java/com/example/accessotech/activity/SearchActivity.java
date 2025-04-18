package com.example.accessotech.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText edtTxtSearch, edtTxtFrom, edtTxtTo;
    private Spinner spnrCategory, spnrManufacturer, spnrRating;
    private RecyclerView recyclerViewFilteredItems;
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
        itemDao = new ItemDaoImpl();
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
    }

    private void fillViewsWithData() {
        fillSpinnersWithData();
        fillRecyclerViewWithData();
    }

    private void fillSpinnersWithData() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                itemDao.findAllCategories()
        );
        spnrCategory.setAdapter(categoryAdapter);

        ArrayAdapter<String> manufacturerAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                itemDao.findAllManufacturers()
        );
        spnrManufacturer.setAdapter(manufacturerAdapter);

        Integer[] ratings = {1, 2, 3, 4, 5};
        ArrayAdapter<Integer> ratingAdapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                ratings
        );
        spnrRating.setAdapter(ratingAdapter);
    }

    private void fillRecyclerViewWithData() {
        adapter = new FilteredItemAdapter(this, itemDao.findAllItems());
        recyclerViewFilteredItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFilteredItems.setAdapter(adapter);
    }

    public void navigateBackToHomeActivity(View view) {
        Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void searchForItemAndUpdateRecyclerView(View view) {
        ItemFilter itemFilter = new ItemFilter(
                spnrCategory.getSelectedItem().toString(),
                spnrManufacturer.getSelectedItem().toString(),
                (spnrRating.getSelectedItem() == null) ? 0 : Integer.parseInt(spnrRating.getSelectedItem().toString()),
                (edtTxtFrom.getText().toString().isEmpty()) ? 0 : Integer.parseInt(edtTxtFrom.getText().toString()),
                (edtTxtTo.getText().toString().isEmpty()) ? 0 : Integer.parseInt(edtTxtTo.getText().toString())
        );

        adapter.filter(edtTxtSearch.getText().toString(), itemFilter);
    }

}















