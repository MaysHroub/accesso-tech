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
import com.example.accessotech.util.PrefsKeys;

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

        SharedPreferences prefs = getSharedPreferences(PrefsKeys.ACCOUNT_INFO_SHARED_PREF.getKey(), MODE_PRIVATE);
        txtViewUsername.setText(prefs.getString(PrefsKeys.USERNAME.getKey(), "None"));
        txtViewEmail.setText(prefs.getString(PrefsKeys.EMAIL.getKey(), "None"));
        txtViewPassword.setText(prefs.getString(PrefsKeys.PASSWORD.getKey(), "None"));
        txtViewAddress.setText(prefs.getString(PrefsKeys.ADDRESS.getKey(), "None"));
        txtViewPhoneNumber.setText(prefs.getString(PrefsKeys.PHONE_NUMBER.getKey(), "None"));
        txtViewCardNumber.setText(prefs.getString(PrefsKeys.CARD_NUMBER.getKey(), "None"));
    }

    public void logout(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}