package com.example.accessotech.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
import com.example.accessotech.adapter.ItemAdapter;
import com.example.accessotech.dao.ItemDao;
import com.example.accessotech.dao.ItemDaoImpl;
import com.example.accessotech.util.PrefsKeys;

public class HomeActivity extends AppCompatActivity {

    private TextView txtHello;
    private RecyclerView recyclerViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUpViews();
        loadDataInRecyclerView();
    }

    private void setUpViews() {
        txtHello = findViewById(R.id.txtHello);
        recyclerViewItems = findViewById(R.id.recyclerViewItems);
        SharedPreferences prefs = getSharedPreferences(PrefsKeys.ACCOUNT_INFO_SHARED_PREF.getKey(), Context.MODE_PRIVATE);
        txtHello.setText(String.format("Hello\n%s!", prefs.getString(PrefsKeys.USERNAME.getKey(), "Lovely Person")));
    }

    private void loadDataInRecyclerView() {
        ItemDao itemDao = new ItemDaoImpl();
        ItemAdapter adapter = new ItemAdapter(this, itemDao.findAllItems());
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewItems.setAdapter(adapter);
    }

    public void launchCartActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, CartActivity.class);
        startActivity(intent);
        finish();
    }

    public void launchSearchActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
        startActivity(intent);
        finish();
    }
}