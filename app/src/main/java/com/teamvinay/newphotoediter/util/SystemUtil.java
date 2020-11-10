package com.teamvinay.newphotoediter.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;

public class SystemUtil {
    public static int dpToPx(Context context, int i) {
        return (int) (((float) i) * context.getResources().getDisplayMetrics().density);
    }

    public static Bitmap blurfast(Bitmap bitmap, int i) {
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        int width = copy.getWidth();
        int height = copy.getHeight();
        int[] iArr = new int[(width * height)];
        copy.getPixels(iArr, 0, width, 0, 0, width, height);
        int i2 = i;
        for (int i3 = 1; i2 >= i3; i3 = 1) {
            for (int i4 = i2; i4 < height - i2; i4++) {
                int i5 = i2;
                while (i5 < width - i2) {
                    int i6 = ((i4 - i2) * width) + i5;
                    int i7 = iArr[i6 - i2];
                    int i8 = iArr[i6 + i2];
                    int i9 = iArr[i6];
                    int i10 = ((i4 + i2) * width) + i5;
                    int i11 = iArr[i10 - i2];
                    int i12 = iArr[i10 + i2];
                    int i13 = iArr[i10];
                    int i14 = (i4 * width) + i5;
                    int i15 = iArr[i14 - i2];
                    int i16 = iArr[i14 + i2];
                    iArr[i14] = ((((((((((i7 & 16711680) + (i8 & 16711680)) + (i9 & 16711680)) + (i11 & 16711680)) + (i12 & 16711680)) + (i13 & 16711680)) + (i15 & 16711680)) + (i16 & 16711680)) >> 3) & 16711680) | ((((((((((i7 & 255) + (i8 & 255)) + (i9 & 255)) + (i11 & 255)) + (i12 & 255)) + (i13 & 255)) + (i15 & 255)) + (i16 & 255)) >> 3) & 255) | ViewCompat.MEASURED_STATE_MASK | ((((((((((i7 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (i8 & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) + (i9 & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) + (i11 & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) + (i12 & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) + (i13 & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) + (i15 & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) + (i16 & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) >> 3) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
                    i5++;
                    height = height;
                    width = width;
                }
                int i17 = width;
                int i18 = height;
            }
            int i19 = width;
            int i20 = height;
            i2 /= 2;
        }
        int i21 = width;
        copy.setPixels(iArr, 0, i21, 0, 0, i21, height);
        return copy;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        switch (i) {
            case 1:
                return bitmap;
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.setRotate(180.0f);
                break;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                break;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
}