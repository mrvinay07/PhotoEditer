package com.teamvinay.newphotoediter.ui;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

/* renamed from: lisa.studio.photoeditor.ui.PhotoApp */
public class PhotoApp extends Application {
    private static PhotoApp sPhotoApp;

    public void onCreate() {
        super.onCreate();
        sPhotoApp = this;
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                StrictMode.class.getMethod("disableDeathOnFileUriExposure", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static PhotoApp getPhotoApp() {
        return sPhotoApp;
    }

    public Context getContext() {
        return sPhotoApp.getContext();
    }
}