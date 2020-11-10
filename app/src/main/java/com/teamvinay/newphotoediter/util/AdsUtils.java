package com.teamvinay.newphotoediter.util;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AdsUtils {
    public static boolean isNetworkAvailabel(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}