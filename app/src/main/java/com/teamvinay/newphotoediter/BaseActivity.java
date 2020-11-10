package com.teamvinay.newphotoediter;

import android.app.ProgressDialog;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BaseActivity extends AppCompatActivity {
    public static final int READ_WRITE_STORAGE = 52;
    private ProgressDialog mProgressDialog;


    public void isPermissionGranted(boolean z, String str) {
    }

    public boolean requestPermission(String str) {
        boolean z = ContextCompat.checkSelfPermission(this, str) == 0;
        if (!z) {
            ActivityCompat.requestPermissions(this, new String[]{str}, 52);
        }
        return z;
    }

    public void makeFullScreen() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 52) {
            isPermissionGranted(iArr[0] == 0, strArr[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void showLoading(@NonNull String str) {
        this.mProgressDialog = new ProgressDialog(this);
        this.mProgressDialog.setMessage(str);
        this.mProgressDialog.setProgressStyle(0);
        this.mProgressDialog.setCancelable(false);
        this.mProgressDialog.show();
    }

    /* access modifiers changed from: protected */
    public void hideLoading() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
    }
}