package com.example.accessotech.activity;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.accessotech.R;
import com.example.accessotech.util.InputValidator;
import com.example.accessotech.util.PrefsKeys;

public class LoginActivity extends AppCompatActivity {

    private EditText edtTxtUsername, edtTxtPassword;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        prefs = getSharedPreferences(PrefsKeys.ACCOUNT_INFO_SHARED_PREF.getKey(), Context.MODE_PRIVATE);
        setUpViews();
    }

    private void setUpViews() {
        edtTxtUsername = findViewById(R.id.edtTxtUsernameLogin);
        edtTxtPassword = findViewById(R.id.edtTxtPasswordLogin);
    }

    public void login(View view) {
        String toastMessage;
        if (!InputValidator.validateEditTexts(edtTxtUsername, edtTxtPassword))
            toastMessage = "Some fields are still empty";
        else {
            boolean isUserAuthenticated = authenticateUser();
            if (isUserAuthenticated) {
                toastMessage = "Welcome " + edtTxtUsername.getText().toString();
                launchHomeActivity();
            }
            else
                toastMessage = "Wrong username or password";
        }
        Toast.makeText(this, toastMessage, LENGTH_SHORT).show();
    }

    private boolean authenticateUser() {
        String username = prefs.getString(PrefsKeys.USERNAME.getKey(), "null");
        String password = prefs.getString(PrefsKeys.PASSWORD.getKey(), "null");
        return username.equals(edtTxtUsername.getText().toString()) &&
                password.equals(edtTxtPassword.getText().toString());
    }

    private void launchHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}