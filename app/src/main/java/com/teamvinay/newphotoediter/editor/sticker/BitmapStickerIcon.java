package com.teamvinay.newphotoediter.editor.sticker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.teamvinay.newphotoediter.editor.sticker.event.StickerIconEvent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public  class BitmapStickerIcon extends DrawableSticker implements StickerIconEvent {
    public static final String ALIGN_HORIZONTALLY = "ALIGN_HORIZONTALLY";
    public static final float DEFAULT_ICON_EXTRA_RADIUS = 10.0f;
    public static final float DEFAULT_ICON_RADIUS = 30.0f;
    public static final String EDIT = "EDIT";
    public static final String FLIP = "FLIP";
    public static final int LEFT_BOTTOM = 2;
    public static final int LEFT_TOP = 0;
    public static final String REMOVE = "REMOVE";
    public static final int RIGHT_BOTOM = 3;
    public static final int RIGHT_TOP = 1;
    public static final String ROTATE = "ROTATE";
    public static final String ZOOM = "ZOOM";
    private StickerIconEvent iconEvent;
    private float iconExtraRadius = 10.0f;
    private float iconRadius = 30.0f;
    private int position = 0;
    private String tag;

    /* renamed from: x */
    private float f618x;

    /* renamed from: y */
    private float f619y;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Gravity {
    }

    public BitmapStickerIcon(Drawable drawable, int i, String str) {
        super(drawable);
        this.position = i;
        this.tag = str;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(this.f618x, this.f619y, this.iconRadius, paint);
        super.draw(canvas);
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public float getX() {
        return this.f618x;
    }

    public void setX(float f) {
        this.f618x = f;
    }

    public float getY() {
        return this.f619y;
    }

    public void setY(float f) {
        this.f619y = f;
    }

    public float getIconRadius() {
        return this.iconRadius;
    }

    public void setIconRadius(float f) {
        this.iconRadius = f;
    }

    public float getIconExtraRadius() {
        return this.iconExtraRadius;
    }

    public void setIconExtraRadius(float f) {
        this.iconExtraRadius = f;
    }

    public void onActionDown(StickerView stickerView, MotionEvent motionEvent) {
        if (this.iconEvent != null) {
            this.iconEvent.onActionDown(stickerView, motionEvent);
        }
    }

    public void onActionMove(StickerView stickerView, MotionEvent motionEvent) {
        if (this.iconEvent != null) {
            this.iconEvent.onActionMove(stickerView, motionEvent);
        }
    }

    public void onActionUp(StickerView stickerView, MotionEvent motionEvent) {
        if (this.iconEvent != null) {
            this.iconEvent.onActionUp(stickerView, motionEvent);
        }
    }

    public StickerIconEvent getIconEvent() {
        return this.iconEvent;
    }

    public void setIconEvent(StickerIconEvent stickerIconEvent) {
        this.iconEvent = stickerIconEvent;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }
}