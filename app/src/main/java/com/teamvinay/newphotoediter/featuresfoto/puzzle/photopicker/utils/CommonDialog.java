package com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.utils;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

public class CommonDialog {
    public static void showDialogConfirm(Activity activity, int i, String str, String str2, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(true);
        builder.setMessage(i);
        builder.setPositiveButton((CharSequence) str, onClickListener);
        builder.setNegativeButton((CharSequence) str2, onClickListener2);
        builder.create().show();
    }

    public static void showInfoDialog(Activity activity, int i, String str, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(true);
        builder.setMessage(i);
        builder.setPositiveButton((CharSequence) str, onClickListener);
        builder.create().show();
    }
}