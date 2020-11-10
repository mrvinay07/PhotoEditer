package com.teamvinay.newphotoediter.util;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class FontUtils {
    public static void gothamFont(Context context, TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/gotham.otf"));
    }

    public static void setFontByName(Context context, TextView textView, String str) {
        AssetManager assets = context.getAssets();
        textView.setTypeface(Typeface.createFromAsset(assets, "fonts/" + str));
    }

    public static void defaultFont(Context context, TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/gotham.otf"));
    }

    public static List<String> getListFonts() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("36.ttf");
        arrayList.add("1.ttf");
        arrayList.add("7.ttf");
        arrayList.add("8.ttf");
        arrayList.add("14.ttf");
        arrayList.add("17.ttf");
        arrayList.add("24.ttf");
        arrayList.add("25.ttf");
        arrayList.add("29.ttf");
        arrayList.add("35.ttf");
        arrayList.add("23.ttf");
        arrayList.add("30.ttf");
        arrayList.add("18.ttf");
        arrayList.add("19.ttf");
        arrayList.add("21.ttf");
        arrayList.add("00.ttf");
        return arrayList;
    }
}