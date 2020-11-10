package com.teamvinay.newphotoediter.featuresfoto.picker.event;

import com.teamvinay.newphotoediter.featuresfoto.picker.entity.Photo;

public interface Selectable {
    void clearSelection();

    int getSelectedItemCount();

    boolean isSelected(Photo photo);

    void toggleSelection(Photo photo);
}