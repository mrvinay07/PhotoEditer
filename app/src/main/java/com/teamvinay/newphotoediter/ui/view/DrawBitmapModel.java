package com.teamvinay.newphotoediter.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.ui.view.BrushDrawingView;

/* renamed from: lisa.studio.photoeditor.ui.view.DrawBitmapModel */
public class DrawBitmapModel {
    private Context context;
    private int from;
    private boolean isLoadBitmap;
    private boolean keepExactPosition;
    private List<Bitmap> lstBitmaps;
    private List<Integer> lstIconWhenDrawing;
    private List<BrushDrawingView.Vector2> mPositions = new ArrayList(100);
    private int mainIcon;

    /* renamed from: to */
    private int f645to;

    public DrawBitmapModel(int i, List<Integer> list, boolean z, Context context2) {
        this.mainIcon = i;
        this.lstIconWhenDrawing = list;
        this.keepExactPosition = z;
        this.context = context2;
    }

    public void clearBitmap() {
        if (this.lstBitmaps != null && !this.lstBitmaps.isEmpty()) {
            this.lstBitmaps.clear();
        }
    }

    public int getMainIcon() {
        return this.mainIcon;
    }

    public void setMainIcon(int i) {
        this.mainIcon = i;
    }

    public List<Integer> getLstIconWhenDrawing() {
        return this.lstIconWhenDrawing;
    }

    public void setLstIconWhenDrawing(List<Integer> list) {
        this.lstIconWhenDrawing = list;
    }

    public boolean isLoadBitmap() {
        return this.isLoadBitmap;
    }

    public void setLoadBitmap(boolean z) {
        this.isLoadBitmap = z;
    }

    public boolean isKeepExactPosition() {
        return this.keepExactPosition;
    }

    public void setKeepExactPosition(boolean z) {
        this.keepExactPosition = z;
    }

    public List<BrushDrawingView.Vector2> getmPositions() {
        return this.mPositions;
    }

    public Bitmap getBitmapByIndex(int i) {
        if (this.lstBitmaps == null || this.lstBitmaps.isEmpty()) {
            init();
        }
        return this.lstBitmaps.get(i);
    }

    public void init() {
        if (this.lstBitmaps == null || this.lstBitmaps.isEmpty()) {
            this.lstBitmaps = new ArrayList();
            for (Integer intValue : this.lstIconWhenDrawing) {
                this.lstBitmaps.add(BitmapFactory.decodeResource(this.context.getResources(), intValue.intValue()));
            }
        }
    }

    public int getFrom() {
        return this.from;
    }

    public void setFrom(int i) {
        this.from = i;
    }

    public int getTo() {
        return this.f645to;
    }

    public void setTo(int i) {
        this.f645to = i;
    }
}
