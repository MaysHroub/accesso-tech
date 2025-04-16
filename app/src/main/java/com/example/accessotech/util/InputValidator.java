package com.example.accessotech.util;

import android.widget.EditText;

public class InputValidator {

    public static boolean validateEditTexts(EditText... editTexts) {
        for (EditText editText : editTexts)
            if (editText.getText().length() == 0)
                return false;
        return true;
    }

    public static boolean validateUserPassword(String password) {
        if (password.length() < 8)
            return false;
        return password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }

    public static boolean validateUserCardNumber(String cardNumber) {
        return cardNumber.length() == 16;
    }

    public static boolean validateUserPhoneNumber(String phoneNumber) {
        return phoneNumber.length() > 9;
    }

    public static boolean validateUserEmail(String email) {
        return email.matches(".*[@.].*");
    }

}
