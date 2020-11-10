package com.teamvinay.newphotoediter.featuresfoto.puzzle;

import android.graphics.PointF;

public interface Line {

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }

    Line attachEndLine();

    Line attachStartLine();

    boolean contains(float f, float f2, float f3);

    Direction direction();

    PointF endPoint();

    float getEndRatio();

    float getStartRatio();

    float length();

    Line lowerLine();

    float maxX();

    float maxY();

    float minX();

    float minY();

    boolean move(float f, float f2);

    void offset(float f, float f2);

    void prepareMove();

    void setEndRatio(float f);

    void setLowerLine(Line line);

    void setStartRatio(float f);

    void setUpperLine(Line line);

    float slope();

    PointF startPoint();

    void update(float f, float f2);

    Line upperLine();
}