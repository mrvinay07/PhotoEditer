package com.teamvinay.newphotoediter.editor.sticker.event;

import android.view.MotionEvent;
import com.teamvinay.newphotoediter.editor.sticker.StickerView;

public interface StickerIconEvent {
    void onActionDown(StickerView stickerView, MotionEvent motionEvent);

    void onActionMove(StickerView stickerView, MotionEvent motionEvent);

    void onActionUp(StickerView stickerView, MotionEvent motionEvent);
}