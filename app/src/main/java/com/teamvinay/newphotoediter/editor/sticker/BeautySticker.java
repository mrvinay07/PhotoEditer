package com.teamvinay.newphotoediter.editor.sticker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.teamvinay.newphotoediter.util.SystemUtil;

public class BeautySticker extends Sticker {
    public static final int BRUST = 7;
    public static final int BUST_0 = 0;
    public static final int BUST_1 = 1;
    public static final int FACE = 4;
    public static final int HIP = 9;
    public static final int HIP_1 = 2;
    public static final int HIP_2 = 8;
    public static final int MAGIC = 6;
    public static final int TALL = 5;
    public static final int TALL_1 = 10;
    public static final int TALL_2 = 11;
    public static final int WAIST = 3;
    private Drawable drawable;
    private int drawableSizeBoobs;
    private int drawableSizeFace_Height;
    private int drawableSizeFace_Width;
    private int drawableSizeHip1_Height;
    private int drawableSizeHip1_Width;
    private int height_Width;
    private PointF mappedCenterPoint;
    private int radius;
    private Rect realBounds = new Rect(0, 0, getWidth(), getHeight());
    private int type;

    @NonNull
    public Drawable getDrawable() {
        return null;
    }

    public BeautySticker setDrawable(@NonNull Drawable drawable2) {
        return this;
    }

    public BeautySticker(Context context, int i, Drawable drawable2) {
        this.drawableSizeBoobs = SystemUtil.dpToPx(context, 50);
        this.drawableSizeHip1_Width = SystemUtil.dpToPx(context, 150);
        this.drawableSizeHip1_Height = SystemUtil.dpToPx(context, 75);
        this.drawableSizeFace_Height = SystemUtil.dpToPx(context, 50);
        this.drawableSizeFace_Width = SystemUtil.dpToPx(context, 80);
        this.type = i;
        this.drawable = drawable2;
    }

    @NonNull
    public PointF getMappedCenterPoint2() {
        return this.mappedCenterPoint;
    }

    public BeautySticker(Context context, int i, Drawable drawable2, int i2) {
        this.height_Width = i2;
        this.type = i;
        this.drawable = drawable2;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int i) {
        this.radius = i;
    }

    public void updateRadius() {
        RectF bound = getBound();
        if (this.type == 0 || this.type == 1 || this.type == 4) {
            this.radius = (int) (bound.left + bound.right);
        } else if (this.type == 2) {
            this.radius = (int) (bound.top + bound.bottom);
        }
        this.mappedCenterPoint = super.getMappedCenterPoint();
    }

    public int getType() {
        return this.type;
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.concat(getMatrix());
        this.drawable.setBounds(this.realBounds);
        this.drawable.draw(canvas);
        canvas.restore();
    }

    @NonNull
    public BeautySticker setAlpha(@IntRange(from = 0) int i) {
        this.drawable.setAlpha(i);
        return this;
    }

    public int getAlpha() {
        return this.drawable.getAlpha();
    }

    public void release() {
        super.release();
        if (this.drawable != null) {
            this.drawable = null;
        }
    }

    public int getWidth() {
        if (this.type == 1 || this.type == 0) {
            return this.drawableSizeBoobs;
        }
        if (this.type == 2) {
            return this.drawableSizeHip1_Width;
        }
        if (this.type == 4) {
            return this.drawableSizeFace_Width;
        }
        if (this.type == 10 || this.type == 11) {
            return this.height_Width;
        }
        return 0;
    }

    public int getHeight() {
        if (this.type == 1 || this.type == 0) {
            return this.drawableSizeBoobs;
        }
        if (this.type == 2) {
            return this.drawableSizeHip1_Height;
        }
        if (this.type == 4) {
            return this.drawableSizeFace_Height;
        }
        if (this.type == 10 || this.type == 11) {
            return this.drawable.getIntrinsicHeight();
        }
        return 0;
    }
}