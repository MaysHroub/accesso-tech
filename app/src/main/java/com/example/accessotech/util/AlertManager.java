package com.example.accessotech.util;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class AlertManager {

    public static void showDialog(Context context, String title, String message, Runnable onConfirm) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes", ((dialog, which) -> onConfirm.run()))
                .setNegativeButton("No", ((dialog, which) -> dialog.dismiss()))
                .show();
    }

}
