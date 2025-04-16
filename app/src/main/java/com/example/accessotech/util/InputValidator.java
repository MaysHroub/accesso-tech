package com.example.accessotech.util;

import android.widget.EditText;

public class InputValidator {

    public static boolean validateEditTexts(EditText... editTexts) {
        for (EditText editText : editTexts)
            if (editText.getText().length() == 0)
                return false;
        return true;
    }

}
