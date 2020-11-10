package com.teamvinay.newphotoediter.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import androidx.core.view.MotionEventCompat;

public class MosaicUtil {

    public enum Effect {
        MOSAIC,
        BLUR
    }

    public enum MosaicType {
        MOSAIC,
        ERASER
    }

    private static int clamp(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public static Bitmap getMosaicsBitmap(Bitmap bitmap, double d) {
        int i;
        Bitmap bitmap2 = bitmap;
        long currentTimeMillis = System.currentTimeMillis();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        double d2 = d == 0.0d ? (double) width : 1.0d / d;
        double d3 = ((double) width) / d2;
        double d4 = ((double) height) / d2;
        int i2 = 0;
        while (true) {
            double d5 = (double) i2;
            if (d5 < d4) {
                int i3 = 0;
                while (true) {
                    double d6 = (double) i3;
                    if (d6 >= d3) {
                        break;
                    }
                    long j = currentTimeMillis;
                    int i4 = (int) (d2 * (d6 + 0.5d));
                    int i5 = i2;
                    Canvas canvas2 = canvas;
                    int i6 = (int) (d2 * (d5 + 0.5d));
                    if (i4 >= width || i6 >= height) {
                        i = bitmap2.getPixel(width / 2, height / 2);
                    } else {
                        i = bitmap2.getPixel(i4, i6);
                    }
                    paint.setColor(i);
                    int i7 = i3 + 1;
                    canvas2.drawRect((float) ((int) (d6 * d2)), (float) ((int) (d2 * d5)), (float) ((int) (((double) i7) * d2)), (float) ((int) (((double) (i5 + 1)) * d2)), paint);
                    i3 = i7;
                    i2 = i5;
                    d5 = d5;
                    currentTimeMillis = j;
                    canvas = canvas2;
                }
                Canvas canvas3 = canvas;
                i2++;
                currentTimeMillis = currentTimeMillis;
            } else {
                long j2 = currentTimeMillis;
                canvas.setBitmap((Bitmap) null);
                long currentTimeMillis2 = System.currentTimeMillis();
                Log.v("HAHA", "DrawTime:" + (currentTimeMillis2 - j2));
                return createBitmap;
            }
        }
    }

    public static Bitmap getMosaicsBitmaps(Bitmap bitmap, double d) {
        int i;
        long currentTimeMillis = System.currentTimeMillis();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(height * width)];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i2 = (int) (((double) width) * d);
        if (i2 == 0) {
            i = width;
        } else {
            i = width / i2;
        }
        if (i >= width || i >= height) {
            return getMosaicsBitmap(bitmap, d);
        }
        for (int i3 = 0; i3 < height; i3 += i) {
            for (int i4 = 0; i4 < width; i4 += i) {
                int i5 = (i3 * width) + i4;
                for (int i6 = 0; i6 < i; i6++) {
                    for (int i7 = 0; i7 < i; i7++) {
                        int i8 = ((i3 + i6) * width) + i4 + i7;
                        if (i8 < iArr.length) {
                            iArr[i8] = iArr[i5];
                        }
                    }
                }
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        Log.v("HAHA", "DrawTime:" + (currentTimeMillis2 - currentTimeMillis));
        return Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap getMosaic(Bitmap bitmap) {
        int i;
        Bitmap bitmap2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int i2 = 50;
        float f = (float) 50;
        int ceil = (int) Math.ceil((double) (((float) width) / f));
        int ceil2 = (int) Math.ceil((double) (((float) height) / f));
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int i3 = 0;
        while (i3 < ceil) {
            int i4 = 0;
            while (i4 < ceil2) {
                int i5 = i2 * i3;
                int i6 = i2 * i4;
                int i7 = i5 + 50;
                if (i7 > width) {
                    i7 = width;
                }
                int i8 = i6 + 50;
                if (i8 > height) {
                    bitmap2 = bitmap;
                    i = height;
                } else {
                    i = i8;
                    bitmap2 = bitmap;
                }
                int pixel = bitmap2.getPixel(i5, i6);
                Rect rect = new Rect(i5, i6, i7, i);
                paint.setColor(pixel);
                canvas.drawRect(rect, paint);
                i4++;
                i2 = 50;
            }
            Bitmap bitmap3 = bitmap;
            i3++;
            i2 = 50;
        }
        canvas.save();
        return createBitmap;
    }

    private static int[] mosatic(int[] iArr, int i, int i2, int i3) {
        int i4 = (i3 * i2) / i;
        int[] iArr2 = new int[(i * i2)];
        for (int i5 = 0; i5 < i; i5++) {
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = (i5 - (i5 % i3)) + (i3 / 2);
                if (i7 > i) {
                    i7 = i;
                }
                int i8 = (i6 - (i6 % i4)) + (i4 / 2);
                if (i8 > i2) {
                    i8 = i2;
                }
                iArr2[(i6 * i) + i5] = iArr[i7 + (i8 * i)];
            }
        }
        return iArr2;
    }

    public static Bitmap getBlur(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < 1; i2++) {
            blur(iArr, iArr2, width, height, 40);
            blur(iArr2, iArr, height, width, 40);
        }
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    private static void blur(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        int i7 = i4 - 1;
        int i8 = (i6 * 2) + 1;
        int i9 = i8 * 256;
        int[] iArr3 = new int[i9];
        int i10 = 0;
        for (int i11 = 0; i11 < i9; i11++) {
            iArr3[i11] = i11 / i8;
        }
        int i12 = 0;
        int i13 = 0;
        while (i12 < i5) {
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            for (int i18 = -i6; i18 <= i6; i18++) {
                int i19 = iArr[clamp(i18, i10, i7) + i13];
                i14 += (i19 >> 24) & 255;
                i15 += (i19 >> 16) & 255;
                i16 += (i19 >> 8) & 255;
                i17 += i19 & 255;
            }
            int i20 = i17;
            int i21 = 0;
            int i22 = i16;
            int i23 = i15;
            int i24 = i14;
            int i25 = i12;
            while (i21 < i4) {
                iArr2[i25] = (iArr3[i24] << 24) | (iArr3[i23] << 16) | (iArr3[i22] << 8) | iArr3[i20];
                int i26 = i21 + i6 + 1;
                if (i26 > i7) {
                    i26 = i7;
                }
                int i27 = i21 - i6;
                if (i27 < 0) {
                    i27 = 0;
                }
                int i28 = iArr[i26 + i13];
                int i29 = iArr[i13 + i27];
                i24 += ((i28 >> 24) & 255) - ((i29 >> 24) & 255);
                i23 += ((i28 & 16711680) - (16711680 & i29)) >> 16;
                i22 += ((i28 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) - (65280 & i29)) >> 8;
                i20 += (i28 & 255) - (i29 & 255);
                i25 += i5;
                i21++;
                i6 = i3;
            }
            i13 += i4;
            i12++;
            i6 = i3;
            i10 = 0;
        }
    }
}