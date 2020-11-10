package com.teamvinay.newphotoediter.featuresfoto.picker.event;

import com.teamvinay.newphotoediter.featuresfoto.picker.entity.Photo;

public interface OnItemCheckListener {
    boolean onItemCheck(int i, Photo photo, int i2);
}