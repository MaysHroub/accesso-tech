package com.example.accessotech.activity;

import android.content.Context;
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

public class CreateAccountActivity extends AppCompatActivity {
    
    private EditText edtTxtUsername, edtTxtEmail, edtTxtPassword,
            edtTxtConfirmPassword, edtTxtAddress, edtTxtPhone, edtTxtCardNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUpViews();
    }

    private void setUpViews() {
        edtTxtUsername = findViewById(R.id.edtTxtUsername);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtConfirmPassword = findViewById(R.id.edtTxtConfirmPassword);
        edtTxtAddress = findViewById(R.id.edtTxtAddress);
        edtTxtPhone = findViewById(R.id.edtTxtPhone);
        edtTxtCardNumber = findViewById(R.id.edtTxtCardNumber);
    }

    public void createAccount(View view) {
        String toastMessage;
        if (!InputValidator.validateEditTexts(edtTxtUsername, edtTxtEmail, edtTxtPassword,
                edtTxtConfirmPassword, edtTxtAddress, edtTxtPhone, edtTxtCardNumber))
            toastMessage = "Some fields are still empty";
        else if (!edtTxtPassword.getText().equals(edtTxtConfirmPassword.getText()))
            toastMessage = "Password doesn't match";
        else if (edtTxtCardNumber.length() != 16)
            toastMessage = "Card number must consist of 16 numbers";
        else {
            toastMessage = "Account created successfully";
            saveUserAccountCreation();
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    private void saveUserAccountCreation() {
        SharedPreferences prefs = getSharedPreferences(PrefsKeys.MY_SHARED_PREFS.getKey(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PrefsKeys.HAS_ACCOUNT.getKey(), true);
        editor.apply();
    }
}