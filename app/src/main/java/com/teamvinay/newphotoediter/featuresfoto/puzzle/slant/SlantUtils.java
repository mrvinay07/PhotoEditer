package com.teamvinay.newphotoediter.featuresfoto.puzzle.slant;

import android.graphics.PointF;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.Line;

class SlantUtils {

    /* renamed from: A */
    private static final PointF f628A = new PointF();

    /* renamed from: AB */
    private static final PointF f629AB = new PointF();

    /* renamed from: AM */
    private static final PointF f630AM = new PointF();

    /* renamed from: B */
    private static final PointF f631B = new PointF();

    /* renamed from: BC */
    private static final PointF f632BC = new PointF();

    /* renamed from: BM */
    private static final PointF f633BM = new PointF();

    /* renamed from: C */
    private static final PointF f634C = new PointF();

    /* renamed from: CD */
    private static final PointF f635CD = new PointF();

    /* renamed from: CM */
    private static final PointF f636CM = new PointF();

    /* renamed from: D */
    private static final PointF f637D = new PointF();

    /* renamed from: DA */
    private static final PointF f638DA = new PointF();

    /* renamed from: DM */
    private static final PointF f639DM = new PointF();

    private SlantUtils() {
    }

    static float distance(PointF pointF, PointF pointF2) {
        return (float) Math.sqrt(Math.pow((double) (pointF2.x - pointF.x), 2.0d) + Math.pow((double) (pointF2.y - pointF.y), 2.0d));
    }

    static List<SlantArea> cutAreaWith(SlantArea slantArea, SlantLine slantLine) {
        ArrayList arrayList = new ArrayList();
        SlantArea slantArea2 = new SlantArea(slantArea);
        SlantArea slantArea3 = new SlantArea(slantArea);
        if (slantLine.direction == Line.Direction.HORIZONTAL) {
            slantArea2.lineBottom = slantLine;
            slantArea2.leftBottom = slantLine.start;
            slantArea2.rightBottom = slantLine.end;
            slantArea3.lineTop = slantLine;
            slantArea3.leftTop = slantLine.start;
            slantArea3.rightTop = slantLine.end;
        } else {
            slantArea2.lineRight = slantLine;
            slantArea2.rightTop = slantLine.start;
            slantArea2.rightBottom = slantLine.end;
            slantArea3.lineLeft = slantLine;
            slantArea3.leftTop = slantLine.start;
            slantArea3.leftBottom = slantLine.end;
        }
        arrayList.add(slantArea2);
        arrayList.add(slantArea3);
        return arrayList;
    }

    static SlantLine createLine(SlantArea slantArea, Line.Direction direction, float f, float f2) {
        SlantLine slantLine = new SlantLine(direction);
        slantLine.setEndRatio(f2);
        slantLine.setStartRatio(f);
        if (direction == Line.Direction.HORIZONTAL) {
            slantLine.start = getPoint(slantArea.leftTop, slantArea.leftBottom, Line.Direction.VERTICAL, f);
            slantLine.end = getPoint(slantArea.rightTop, slantArea.rightBottom, Line.Direction.VERTICAL, f2);
            slantLine.attachLineStart = slantArea.lineLeft;
            slantLine.attachLineEnd = slantArea.lineRight;
            slantLine.upperLine = slantArea.lineBottom;
            slantLine.lowerLine = slantArea.lineTop;
        } else {
            slantLine.start = getPoint(slantArea.leftTop, slantArea.rightTop, Line.Direction.HORIZONTAL, f);
            slantLine.end = getPoint(slantArea.leftBottom, slantArea.rightBottom, Line.Direction.HORIZONTAL, f2);
            slantLine.attachLineStart = slantArea.lineTop;
            slantLine.attachLineEnd = slantArea.lineBottom;
            slantLine.upperLine = slantArea.lineRight;
            slantLine.lowerLine = slantArea.lineLeft;
        }
        return slantLine;
    }

    static Pair<List<SlantLine>, List<SlantArea>> cutAreaWith(SlantArea slantArea, int i, int i2) {
        int i3;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(i);
        SlantArea slantArea2 = new SlantArea(slantArea);
        for (int i4 = i + 1; i4 > 1; i4--) {
            float f = ((float) (i4 - 1)) / ((float) i4);
            SlantLine createLine = createLine(slantArea2, Line.Direction.HORIZONTAL, f - 0.025f, f + 0.025f);
            arrayList2.add(createLine);
            slantArea2.lineBottom = createLine;
            slantArea2.leftBottom = createLine.start;
            slantArea2.rightBottom = createLine.end;
        }
        ArrayList arrayList3 = new ArrayList();
        SlantArea slantArea3 = new SlantArea(slantArea);
        int i5 = i2 + 1;
        while (true) {
            i3 = 0;
            if (i5 <= 1) {
                break;
            }
            float f2 = ((float) (i5 - 1)) / ((float) i5);
            SlantLine createLine2 = createLine(slantArea3, Line.Direction.VERTICAL, f2 + 0.025f, f2 - 0.025f);
            arrayList3.add(createLine2);
            SlantArea slantArea4 = new SlantArea(slantArea3);
            slantArea4.lineLeft = createLine2;
            slantArea4.leftTop = createLine2.start;
            slantArea4.leftBottom = createLine2.end;
            while (i3 <= arrayList2.size()) {
                SlantArea slantArea5 = new SlantArea(slantArea4);
                if (i3 == 0) {
                    slantArea5.lineTop = (SlantLine) arrayList2.get(i3);
                } else if (i3 == arrayList2.size()) {
                    slantArea5.lineBottom = (SlantLine) arrayList2.get(i3 - 1);
                    CrossoverPointF crossoverPointF = new CrossoverPointF(slantArea5.lineBottom, slantArea5.lineLeft);
                    intersectionOfLines(crossoverPointF, slantArea5.lineBottom, slantArea5.lineLeft);
                    CrossoverPointF crossoverPointF2 = new CrossoverPointF(slantArea5.lineBottom, slantArea5.lineRight);
                    intersectionOfLines(crossoverPointF2, slantArea5.lineBottom, slantArea5.lineRight);
                    slantArea5.leftBottom = crossoverPointF;
                    slantArea5.rightBottom = crossoverPointF2;
                } else {
                    slantArea5.lineTop = (SlantLine) arrayList2.get(i3);
                    slantArea5.lineBottom = (SlantLine) arrayList2.get(i3 - 1);
                }
                CrossoverPointF crossoverPointF3 = new CrossoverPointF(slantArea5.lineTop, slantArea5.lineLeft);
                intersectionOfLines(crossoverPointF3, slantArea5.lineTop, slantArea5.lineLeft);
                CrossoverPointF crossoverPointF4 = new CrossoverPointF(slantArea5.lineTop, slantArea5.lineRight);
                intersectionOfLines(crossoverPointF4, slantArea5.lineTop, slantArea5.lineRight);
                slantArea5.leftTop = crossoverPointF3;
                slantArea5.rightTop = crossoverPointF4;
                arrayList.add(slantArea5);
                i3++;
            }
            slantArea3.lineRight = createLine2;
            slantArea3.rightTop = createLine2.start;
            slantArea3.rightBottom = createLine2.end;
            i5--;
        }
        while (i3 <= arrayList2.size()) {
            SlantArea slantArea6 = new SlantArea(slantArea3);
            if (i3 == 0) {
                slantArea6.lineTop = (SlantLine) arrayList2.get(i3);
            } else if (i3 == arrayList2.size()) {
                slantArea6.lineBottom = (SlantLine) arrayList2.get(i3 - 1);
                CrossoverPointF crossoverPointF5 = new CrossoverPointF(slantArea6.lineBottom, slantArea6.lineLeft);
                intersectionOfLines(crossoverPointF5, slantArea6.lineBottom, slantArea6.lineLeft);
                CrossoverPointF crossoverPointF6 = new CrossoverPointF(slantArea6.lineBottom, slantArea6.lineRight);
                intersectionOfLines(crossoverPointF6, slantArea6.lineBottom, slantArea6.lineRight);
                slantArea6.leftBottom = crossoverPointF5;
                slantArea6.rightBottom = crossoverPointF6;
            } else {
                slantArea6.lineTop = (SlantLine) arrayList2.get(i3);
                slantArea6.lineBottom = (SlantLine) arrayList2.get(i3 - 1);
            }
            CrossoverPointF crossoverPointF7 = new CrossoverPointF(slantArea6.lineTop, slantArea6.lineLeft);
            intersectionOfLines(crossoverPointF7, slantArea6.lineTop, slantArea6.lineLeft);
            CrossoverPointF crossoverPointF8 = new CrossoverPointF(slantArea6.lineTop, slantArea6.lineRight);
            intersectionOfLines(crossoverPointF8, slantArea6.lineTop, slantArea6.lineRight);
            slantArea6.leftTop = crossoverPointF7;
            slantArea6.rightTop = crossoverPointF8;
            arrayList.add(slantArea6);
            i3++;
        }
        ArrayList arrayList4 = new ArrayList();
        arrayList4.addAll(arrayList2);
        arrayList4.addAll(arrayList3);
        return new Pair<>(arrayList4, arrayList);
    }

    static List<SlantArea> cutAreaCross(SlantArea slantArea, SlantLine slantLine, SlantLine slantLine2) {
        ArrayList arrayList = new ArrayList();
        CrossoverPointF crossoverPointF = new CrossoverPointF(slantLine, slantLine2);
        intersectionOfLines(crossoverPointF, slantLine, slantLine2);
        SlantArea slantArea2 = new SlantArea(slantArea);
        slantArea2.lineBottom = slantLine;
        slantArea2.lineRight = slantLine2;
        slantArea2.rightTop = slantLine2.start;
        slantArea2.rightBottom = crossoverPointF;
        slantArea2.leftBottom = slantLine.start;
        arrayList.add(slantArea2);
        SlantArea slantArea3 = new SlantArea(slantArea);
        slantArea3.lineBottom = slantLine;
        slantArea3.lineLeft = slantLine2;
        slantArea3.leftTop = slantLine2.start;
        slantArea3.rightBottom = slantLine.end;
        slantArea3.leftBottom = crossoverPointF;
        arrayList.add(slantArea3);
        SlantArea slantArea4 = new SlantArea(slantArea);
        slantArea4.lineTop = slantLine;
        slantArea4.lineRight = slantLine2;
        slantArea4.leftTop = slantLine.start;
        slantArea4.rightTop = crossoverPointF;
        slantArea4.rightBottom = slantLine2.end;
        arrayList.add(slantArea4);
        SlantArea slantArea5 = new SlantArea(slantArea);
        slantArea5.lineTop = slantLine;
        slantArea5.lineLeft = slantLine2;
        slantArea5.leftTop = crossoverPointF;
        slantArea5.rightTop = slantLine.end;
        slantArea5.leftBottom = slantLine2.end;
        arrayList.add(slantArea5);
        return arrayList;
    }

    private static CrossoverPointF getPoint(PointF pointF, PointF pointF2, Line.Direction direction, float f) {
        CrossoverPointF crossoverPointF = new CrossoverPointF();
        getPoint(crossoverPointF, pointF, pointF2, direction, f);
        return crossoverPointF;
    }

    static void getPoint(PointF pointF, PointF pointF2, PointF pointF3, Line.Direction direction, float f) {
        float abs = Math.abs(pointF2.y - pointF3.y);
        float abs2 = Math.abs(pointF2.x - pointF3.x);
        float max = Math.max(pointF2.y, pointF3.y);
        float min = Math.min(pointF2.y, pointF3.y);
        float max2 = Math.max(pointF2.x, pointF3.x);
        float min2 = Math.min(pointF2.x, pointF3.x);
        if (direction == Line.Direction.HORIZONTAL) {
            pointF.x = min2 + (abs2 * f);
            if (pointF2.y < pointF3.y) {
                pointF.y = min + (f * abs);
            } else {
                pointF.y = max - (f * abs);
            }
        } else {
            pointF.y = min + (abs * f);
            if (pointF2.x < pointF3.x) {
                pointF.x = min2 + (f * abs2);
            } else {
                pointF.x = max2 - (f * abs2);
            }
        }
    }

    private static float crossProduct(PointF pointF, PointF pointF2) {
        return (pointF.x * pointF2.y) - (pointF2.x * pointF.y);
    }

    static boolean contains(SlantArea slantArea, float f, float f2) {
        f629AB.x = slantArea.rightTop.x - slantArea.leftTop.x;
        f629AB.y = slantArea.rightTop.y - slantArea.leftTop.y;
        f630AM.x = f - slantArea.leftTop.x;
        f630AM.y = f2 - slantArea.leftTop.y;
        f632BC.x = slantArea.rightBottom.x - slantArea.rightTop.x;
        f632BC.y = slantArea.rightBottom.y - slantArea.rightTop.y;
        f633BM.x = f - slantArea.rightTop.x;
        f633BM.y = f2 - slantArea.rightTop.y;
        f635CD.x = slantArea.leftBottom.x - slantArea.rightBottom.x;
        f635CD.y = slantArea.leftBottom.y - slantArea.rightBottom.y;
        f636CM.x = f - slantArea.rightBottom.x;
        f636CM.y = f2 - slantArea.rightBottom.y;
        f638DA.x = slantArea.leftTop.x - slantArea.leftBottom.x;
        f638DA.y = slantArea.leftTop.y - slantArea.leftBottom.y;
        f639DM.x = f - slantArea.leftBottom.x;
        f639DM.y = f2 - slantArea.leftBottom.y;
        return crossProduct(f629AB, f630AM) > 0.0f && crossProduct(f632BC, f633BM) > 0.0f && crossProduct(f635CD, f636CM) > 0.0f && crossProduct(f638DA, f639DM) > 0.0f;
    }

    static boolean contains(SlantLine slantLine, float f, float f2, float f3) {
        CrossoverPointF crossoverPointF = slantLine.start;
        CrossoverPointF crossoverPointF2 = slantLine.end;
        if (slantLine.direction == Line.Direction.VERTICAL) {
            f628A.x = crossoverPointF.x - f3;
            f628A.y = crossoverPointF.y;
            f631B.x = crossoverPointF.x + f3;
            f631B.y = crossoverPointF.y;
            f634C.x = crossoverPointF2.x + f3;
            f634C.y = crossoverPointF2.y;
            f637D.x = crossoverPointF2.x - f3;
            f637D.y = crossoverPointF2.y;
        } else {
            f628A.x = crossoverPointF.x;
            f628A.y = crossoverPointF.y - f3;
            f631B.x = crossoverPointF2.x;
            f631B.y = crossoverPointF2.y - f3;
            f634C.x = crossoverPointF2.x;
            f634C.y = crossoverPointF2.y + f3;
            f637D.x = crossoverPointF.x;
            f637D.y = crossoverPointF.y + f3;
        }
        f629AB.x = f631B.x - f628A.x;
        f629AB.y = f631B.y - f628A.y;
        f630AM.x = f - f628A.x;
        f630AM.y = f2 - f628A.y;
        f632BC.x = f634C.x - f631B.x;
        f632BC.y = f634C.y - f631B.y;
        f633BM.x = f - f631B.x;
        f633BM.y = f2 - f631B.y;
        f635CD.x = f637D.x - f634C.x;
        f635CD.y = f637D.y - f634C.y;
        f636CM.x = f - f634C.x;
        f636CM.y = f2 - f634C.y;
        f638DA.x = f628A.x - f637D.x;
        f638DA.y = f628A.y - f637D.y;
        f639DM.x = f - f637D.x;
        f639DM.y = f2 - f637D.y;
        return crossProduct(f629AB, f630AM) > 0.0f && crossProduct(f632BC, f633BM) > 0.0f && crossProduct(f635CD, f636CM) > 0.0f && crossProduct(f638DA, f639DM) > 0.0f;
    }

    static void intersectionOfLines(CrossoverPointF crossoverPointF, SlantLine slantLine, SlantLine slantLine2) {
        crossoverPointF.horizontal = slantLine;
        crossoverPointF.vertical = slantLine2;
        if (isParallel(slantLine, slantLine2)) {
            crossoverPointF.set(0.0f, 0.0f);
        } else if (isHorizontalLine(slantLine) && isVerticalLine(slantLine2)) {
            crossoverPointF.set(slantLine2.start.x, slantLine.start.y);
        } else if (isVerticalLine(slantLine) && isHorizontalLine(slantLine2)) {
            crossoverPointF.set(slantLine.start.x, slantLine2.start.y);
        } else if (isHorizontalLine(slantLine) && !isVerticalLine(slantLine2)) {
            float calculateSlope = calculateSlope(slantLine2);
            float calculateVerticalIntercept = calculateVerticalIntercept(slantLine2);
            crossoverPointF.y = slantLine.start.y;
            crossoverPointF.x = (crossoverPointF.y - calculateVerticalIntercept) / calculateSlope;
        } else if (isVerticalLine(slantLine) && !isHorizontalLine(slantLine2)) {
            float calculateSlope2 = calculateSlope(slantLine2);
            float calculateVerticalIntercept2 = calculateVerticalIntercept(slantLine2);
            crossoverPointF.x = slantLine.start.x;
            crossoverPointF.y = (calculateSlope2 * crossoverPointF.x) + calculateVerticalIntercept2;
        } else if (isHorizontalLine(slantLine2) && !isVerticalLine(slantLine)) {
            float calculateSlope3 = calculateSlope(slantLine);
            float calculateVerticalIntercept3 = calculateVerticalIntercept(slantLine);
            crossoverPointF.y = slantLine2.start.y;
            crossoverPointF.x = (crossoverPointF.y - calculateVerticalIntercept3) / calculateSlope3;
        } else if (!isVerticalLine(slantLine2) || isHorizontalLine(slantLine)) {
            float calculateSlope4 = calculateSlope(slantLine);
            float calculateVerticalIntercept4 = calculateVerticalIntercept(slantLine);
            crossoverPointF.x = (calculateVerticalIntercept(slantLine2) - calculateVerticalIntercept4) / (calculateSlope4 - calculateSlope(slantLine2));
            crossoverPointF.y = (crossoverPointF.x * calculateSlope4) + calculateVerticalIntercept4;
        } else {
            float calculateSlope5 = calculateSlope(slantLine);
            float calculateVerticalIntercept5 = calculateVerticalIntercept(slantLine);
            crossoverPointF.x = slantLine2.start.x;
            crossoverPointF.y = (calculateSlope5 * crossoverPointF.x) + calculateVerticalIntercept5;
        }
    }

    private static boolean isHorizontalLine(SlantLine slantLine) {
        return slantLine.start.y == slantLine.end.y;
    }

    private static boolean isVerticalLine(SlantLine slantLine) {
        return slantLine.start.x == slantLine.end.x;
    }

    private static boolean isParallel(SlantLine slantLine, SlantLine slantLine2) {
        return calculateSlope(slantLine) == calculateSlope(slantLine2);
    }

    static float calculateSlope(SlantLine slantLine) {
        if (isHorizontalLine(slantLine)) {
            return 0.0f;
        }
        if (isVerticalLine(slantLine)) {
            return Float.POSITIVE_INFINITY;
        }
        return (slantLine.start.y - slantLine.end.y) / (slantLine.start.x - slantLine.end.x);
    }

    private static float calculateVerticalIntercept(SlantLine slantLine) {
        if (isHorizontalLine(slantLine)) {
            return slantLine.start.y;
        }
        if (isVerticalLine(slantLine)) {
            return Float.POSITIVE_INFINITY;
        }
        return slantLine.start.y - (calculateSlope(slantLine) * slantLine.start.x);
    }
}