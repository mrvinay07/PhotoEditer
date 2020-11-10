package com.teamvinay.newphotoediter.featuresfoto.puzzle.slant;

import android.graphics.PointF;

class CrossoverPointF extends PointF {
    SlantLine horizontal;
    SlantLine vertical;

    CrossoverPointF() {
    }

    CrossoverPointF(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    CrossoverPointF(SlantLine slantLine, SlantLine slantLine2) {
        this.horizontal = slantLine;
        this.vertical = slantLine2;
    }

    /* access modifiers changed from: package-private */
    public void update() {
        if (this.horizontal != null && this.vertical != null) {
            SlantUtils.intersectionOfLines(this, this.horizontal, this.vertical);
        }
    }
}