package com.example.accessotech.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.accessotech.R;
import com.example.accessotech.model.UserInfo;
import com.example.accessotech.util.PrefsKeys;
import com.example.accessotech.util.SharedPrefsManager;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUpViews();
    }

    private void setUpViews() {
        TextView txtViewUsername = findViewById(R.id.txtViewUsername),
                txtViewEmail = findViewById(R.id.txtViewEmail),
                txtViewPassword = findViewById(R.id.txtViewPassword),
                txtViewAddress = findViewById(R.id.txtViewAddress),
                txtViewPhoneNumber = findViewById(R.id.txtViewPhoneNumber),
                txtViewCardNumber = findViewById(R.id.txtViewCardNumber);

        UserInfo userInfo = SharedPrefsManager.loadUserInfo(this);

        txtViewUsername.setText(userInfo.getName());
        txtViewEmail.setText(userInfo.getEmail());
        txtViewPassword.setText(userInfo.getPassword());
        txtViewAddress.setText(userInfo.getAddress());
        txtViewPhoneNumber.setText(userInfo.getPhoneNumber());
        txtViewCardNumber.setText(userInfo.getCardNumber());
    }

    public void logout(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
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

    public void navigateToCartActivity(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
        finish();
    }
}