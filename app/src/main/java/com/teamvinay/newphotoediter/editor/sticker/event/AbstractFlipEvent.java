package com.teamvinay.newphotoediter.editor.sticker.event;

import android.view.MotionEvent;
import com.teamvinay.newphotoediter.sticker.StickerView;

public abstract class AbstractFlipEvent implements StickerIconEvent {
    /* access modifiers changed from: protected */
    public abstract int getFlipDirection();

    public void onActionDown(StickerView stickerView, MotionEvent motionEvent) {
    }

    public void onActionMove(StickerView stickerView, MotionEvent motionEvent) {
    }

    public void onActionUp(StickerView stickerView, MotionEvent motionEvent) {
        stickerView.flipCurrentSticker(getFlipDirection());
    }
}
