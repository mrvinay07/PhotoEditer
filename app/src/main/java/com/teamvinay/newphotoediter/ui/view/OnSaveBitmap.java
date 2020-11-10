package com.teamvinay.newphotoediter.ui.view;

import android.graphics.Bitmap;

/* renamed from: lisa.studio.photoeditor.ui.view.OnSaveBitmap */
public interface OnSaveBitmap {
    void onBitmapReady(Bitmap bitmap);

    void onFailure(Exception exc);
}