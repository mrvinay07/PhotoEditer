package com.teamvinay.newphotoediter.editor.sticker;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

public class DrawableSticker extends Sticker {
    private Drawable drawable;
    private Rect realBounds = new Rect(0, 0, getWidth(), getHeight());

    public DrawableSticker(Drawable drawable2) {
        this.drawable = drawable2;
    }

    @NonNull
    public Drawable getDrawable() {
        return this.drawable;
    }

    public DrawableSticker setDrawable(@NonNull Drawable drawable2) {
        this.drawable = drawable2;
        return this;
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.save();
        canvas.concat(getMatrix());
        this.drawable.setBounds(this.realBounds);
        this.drawable.draw(canvas);
        canvas.restore();
    }

    @NonNull
    public DrawableSticker setAlpha(@IntRange(from = 0, mo93to = 255) int i) {
        this.drawable.setAlpha(i);
        return this;
    }

    public int getAlpha() {
        return this.drawable.getAlpha();
    }

    public int getWidth() {
        return this.drawable.getIntrinsicWidth();
    }

    public int getHeight() {
        return this.drawable.getIntrinsicHeight();
    }

    public void release() {
        super.release();
        if (this.drawable != null) {
            this.drawable = null;
        }
    }
}