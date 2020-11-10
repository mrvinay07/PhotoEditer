package com.teamvinay.newphotoediter.featuresfoto.puzzle;

import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.Line;

public interface PuzzleLayout {
    PuzzleLayout clone(PuzzleLayout puzzleLayout);

    Info generateInfo();

    Area getArea(int i);

    int getAreaCount();

    int getColor();

    List<Line> getLines();

    Area getOuterArea();

    List<Line> getOuterLines();

    float getPadding();

    float getRadian();

    float height();

    void layout();

    void reset();

    void setColor(int i);

    void setOuterBounds(RectF rectF);

    void setPadding(float f);

    void setRadian(float f);

    void sortAreas();

    void update();

    float width();

    public static class Info implements Parcelable {
        public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() {
            public Info createFromParcel(Parcel parcel) {
                return new Info(parcel);
            }

            public Info[] newArray(int i) {
                return new Info[i];
            }
        };
        public static final int TYPE_SLANT = 1;
        public static final int TYPE_STRAIGHT = 0;
        public float bottom;
        public int color;
        public float left;
        public ArrayList<LineInfo> lineInfos;
        public ArrayList<Line> lines;
        public float padding;
        public float radian;
        public float right;
        public ArrayList<Step> steps;
        public float top;
        public int type;

        public int describeContents() {
            return 0;
        }

        public Info() {
        }

        protected Info(Parcel parcel) {
            this.type = parcel.readInt();
            this.steps = parcel.createTypedArrayList(Step.CREATOR);
            this.lineInfos = parcel.createTypedArrayList(LineInfo.CREATOR);
            this.padding = parcel.readFloat();
            this.radian = parcel.readFloat();
            this.color = parcel.readInt();
            this.left = parcel.readFloat();
            this.top = parcel.readFloat();
            this.right = parcel.readFloat();
            this.bottom = parcel.readFloat();
        }

        public float width() {
            return this.right - this.left;
        }

        public float height() {
            return this.bottom - this.top;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.type);
            parcel.writeTypedList(this.steps);
            parcel.writeTypedList(this.lineInfos);
            parcel.writeFloat(this.padding);
            parcel.writeFloat(this.radian);
            parcel.writeInt(this.color);
            parcel.writeFloat(this.left);
            parcel.writeFloat(this.top);
            parcel.writeFloat(this.right);
            parcel.writeFloat(this.bottom);
        }
    }

    public static class Step implements Parcelable {
        public static final int ADD_CROSS = 1;
        public static final int ADD_LINE = 0;
        public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
            public Step createFromParcel(Parcel parcel) {
                return new Step(parcel);
            }

            public Step[] newArray(int i) {
                return new Step[i];
            }
        };
        public static final int CUT_EQUAL_PART_ONE = 2;
        public static final int CUT_EQUAL_PART_TWO = 3;
        public static final int CUT_SPIRAL = 4;
        public int direction;
        public float hRatio;
        public int hSize;
        public int numOfLine = 1;
        public int part;
        public int position;
        public int type;
        public float vRatio;
        public int vSize;

        public int describeContents() {
            return 0;
        }

        public Step() {
        }

        protected Step(Parcel parcel) {
            this.type = parcel.readInt();
            this.direction = parcel.readInt();
            this.position = parcel.readInt();
            this.part = parcel.readInt();
            this.hSize = parcel.readInt();
            this.vSize = parcel.readInt();
        }

        public Line.Direction lineDirection() {
            return this.direction == 0 ? Line.Direction.HORIZONTAL : Line.Direction.VERTICAL;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.type);
            parcel.writeInt(this.direction);
            parcel.writeInt(this.position);
            parcel.writeInt(this.part);
            parcel.writeInt(this.hSize);
            parcel.writeInt(this.vSize);
        }
    }

    public static class LineInfo implements Parcelable {
        public static final Parcelable.Creator<LineInfo> CREATOR = new Parcelable.Creator<LineInfo>() {
            public LineInfo createFromParcel(Parcel parcel) {
                return new LineInfo(parcel);
            }

            public LineInfo[] newArray(int i) {
                return new LineInfo[i];
            }
        };
        public float endX;
        public float endY;
        public float startX;
        public float startY;

        public int describeContents() {
            return 0;
        }

        public LineInfo(Line line) {
            this.startX = line.startPoint().x;
            this.startY = line.startPoint().y;
            this.endX = line.endPoint().x;
            this.endY = line.endPoint().y;
        }

        protected LineInfo(Parcel parcel) {
            this.startX = parcel.readFloat();
            this.startY = parcel.readFloat();
            this.endX = parcel.readFloat();
            this.endY = parcel.readFloat();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.startX);
            parcel.writeFloat(this.startY);
            parcel.writeFloat(this.endX);
            parcel.writeFloat(this.endY);
        }
    }
}