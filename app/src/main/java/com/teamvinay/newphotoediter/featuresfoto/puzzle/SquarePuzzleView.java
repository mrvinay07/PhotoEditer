package com.teamvinay.newphotoediter.featuresfoto.puzzle;

import android.content.Context;
import android.util.AttributeSet;

public class SquarePuzzleView extends PuzzleView {
    public SquarePuzzleView(Context context) {
        super(context);
    }

    public SquarePuzzleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquarePuzzleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

   /* *//* access modifiers changed from: protected *//*
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth > measuredHeight) {
            measuredWidth = measuredHeight;
        }
        setMeasuredDimension(measuredWidth, measuredWidth);
    }*/
}