package com.teamvinay.newphotoediter.ui.view;

import android.view.View;

/* renamed from: lisa.studio.photoeditor.ui.view.OnPhotoEditorListener */
public interface OnPhotoEditorListener {
    void onAddViewListener(ViewType viewType, int i);

    void onEditTextChangeListener(View view, String str, int i);

    @Deprecated
    void onRemoveViewListener(int i);

    void onRemoveViewListener(ViewType viewType, int i);

    void onStartViewChangeListener(ViewType viewType);

    void onStopViewChangeListener(ViewType viewType);
}