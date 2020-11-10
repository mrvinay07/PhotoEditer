package com.teamvinay.newphotoediter.featuresfoto.addtext.color;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import com.teamvinay.newphotoediter.C1084R;

public class ColorSlider extends View {
    private Rect[] mColorFullRects = new Rect[0];
    private Rect[] mColorRects = new Rect[0];
    private int[] mColors = new int[0];
    private OnColorSelectedListener mListener;
    private Paint mPaint;
    private int mSelectedItem;
    private Paint mSelectorPaint;

    public interface OnColorSelectedListener {
        void onColorChanged(int i, @ColorInt int i2);
    }

    public ColorSlider(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public ColorSlider(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public ColorSlider(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public void setHexColors(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            convertToColors(strArr);
            calculateRectangles();
            invalidate();
        }
    }

    public void setColors(@ColorInt int[] iArr) {
        if (iArr != null && iArr.length > 0) {
            this.mColors = iArr;
            calculateRectangles();
            invalidate();
        }
    }

    public void setGradient(@ColorInt int i, @ColorInt int i2, int i3) {
        if (i != 0 && i2 != 0 && i3 != 0) {
            calculateColors(i, i2, i3);
            calculateRectangles();
            invalidate();
        }
    }

    public void setGradient(@ColorInt int[] iArr, int i) {
        if (iArr == null || iArr.length < 2) {
            throw new IllegalArgumentException("Colors array must contain 2 or more color.");
        } else if (iArr.length == 2) {
            setGradient(iArr[0], iArr[1], i);
        } else {
            calculateColors(iArr, i);
            calculateRectangles();
            invalidate();
        }
    }

    public void selectColor(@ColorInt int i) {
        for (int i2 = 0; i2 < this.mColors.length; i2++) {
            if (this.mColors[i2] == i) {
                this.mSelectedItem = i2;
                invalidate();
                return;
            }
        }
    }

    public void setSelection(int i) {
        if (i < this.mColors.length) {
            this.mSelectedItem = i;
            invalidate();
        }
    }

    public int getSelectedItem() {
        return this.mSelectedItem;
    }

    @ColorInt
    public int getSelectedColor() {
        return this.mColors[this.mSelectedItem];
    }

    public void setListener(OnColorSelectedListener onColorSelectedListener) {
        this.mListener = onColorSelectedListener;
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mPaint = new Paint();
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mSelectorPaint = new Paint();
        this.mSelectorPaint.setStyle(Paint.Style.STROKE);
        this.mSelectorPaint.setColor(getResources().getColor(17170446));
        this.mSelectorPaint.setStrokeWidth(2.0f);
        setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return ColorSlider.this.processTouch(motionEvent);
            }
        });
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C1084R.styleable.ColorSlider, 0, 0);
            try {
                int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                int resourceId2 = obtainStyledAttributes.getResourceId(2, 0);
                if (resourceId != 0) {
                    int[] intArray = getResources().getIntArray(resourceId);
                    if (intArray.length > 0) {
                        this.mColors = new int[intArray.length];
                        System.arraycopy(intArray, 0, this.mColors, 0, intArray.length);
                    }
                } else if (resourceId2 != 0) {
                    String[] stringArray = getResources().getStringArray(resourceId2);
                    if (stringArray.length > 0) {
                        convertToColors(stringArray);
                    }
                }
                if (this.mColors.length == 0) {
                    int color = obtainStyledAttributes.getColor(1, 0);
                    int color2 = obtainStyledAttributes.getColor(4, 0);
                    int i = obtainStyledAttributes.getInt(3, 21);
                    if (!(color == 0 || color2 == 0 || i == 0)) {
                        calculateColors(color, color2, i);
                    }
                }
            } catch (Exception e) {
                Log.d("ColorSlider", "init: " + e.getLocalizedMessage());
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
            obtainStyledAttributes.recycle();
        }
        if (this.mColors.length == 0) {
            initDefaultColors();
        }
        this.mColorRects = new Rect[this.mColors.length];
        this.mColorFullRects = new Rect[this.mColors.length];
    }

    private void initDefaultColors() {
        this.mColors = new int[]{Color.parseColor("#F44336"), Color.parseColor("#E91E63"), Color.parseColor("#9C27B0"), Color.parseColor("#673AB7"), Color.parseColor("#3F51B5"), Color.parseColor("#2196F3"), Color.parseColor("#03A9F4"), Color.parseColor("#00BCD4"), Color.parseColor("#009688"), Color.parseColor("#4CAF50"), Color.parseColor("#8BC34A"), Color.parseColor("#CDDC39"), Color.parseColor("#FFEB3B"), Color.parseColor("#FFC107"), Color.parseColor("#FF9800"), Color.parseColor("#FF5722"), Color.parseColor("#795548"), Color.parseColor("#9E9E9E"), Color.parseColor("#607D8B"), Color.parseColor("#FFFFFF")};
    }

    private void convertToColors(String[] strArr) {
        this.mColors = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            this.mColors[i] = Color.parseColor(strArr[i]);
        }
    }

    private void calculateColors(@ColorInt int[] iArr, int i) {
        ColorSlider colorSlider = this;
        int[] iArr2 = iArr;
        int i2 = i;
        int length = iArr2.length;
        int i3 = length - 1;
        int i4 = i2 / i3;
        int i5 = i2 % i4;
        if (i5 == 0) {
            i5 = 0;
        }
        colorSlider.mColors = new int[i2];
        int i6 = 1;
        while (i6 < length) {
            int i7 = i6 - 1;
            int i8 = iArr2[i7];
            int i9 = iArr2[i6];
            int i10 = i7 * i4;
            int i11 = i6 * i4;
            if (i6 == i3) {
                i11 += i5;
            }
            float alpha = (float) Color.alpha(i8);
            float red = (float) Color.red(i8);
            float green = (float) Color.green(i8);
            float blue = (float) Color.blue(i8);
            int i12 = length;
            float f = (float) (i11 - i10);
            float alpha2 = (((float) Color.alpha(i9)) - alpha) / f;
            float red2 = (((float) Color.red(i9)) - red) / f;
            float green2 = (((float) Color.green(i9)) - green) / f;
            float blue2 = (((float) Color.blue(i9)) - blue) / f;
            int i13 = 0;
            while (i10 < i11) {
                int i14 = i3;
                int[] iArr3 = colorSlider.mColors;
                float f2 = (float) i13;
                iArr3[i10] = Color.argb((int) (alpha + (alpha2 * f2)), (int) (red + (red2 * f2)), (int) (green + (green2 * f2)), (int) ((f2 * blue2) + blue));
                i13++;
                i10++;
                i3 = i14;
                alpha2 = alpha2;
                red2 = red2;
                green2 = green2;
                colorSlider = this;
            }
            int i15 = i3;
            i6++;
            length = i12;
            colorSlider = this;
            iArr2 = iArr;
        }
    }

    private void calculateColors(@ColorInt int i, @ColorInt int i2, int i3) {
        float alpha = (float) Color.alpha(i);
        float red = (float) Color.red(i);
        float green = (float) Color.green(i);
        float blue = (float) Color.blue(i);
        float f = (float) i3;
        float alpha2 = (((float) Color.alpha(i2)) - alpha) / f;
        float red2 = (((float) Color.red(i2)) - red) / f;
        float green2 = (((float) Color.green(i2)) - green) / f;
        float blue2 = (((float) Color.blue(i2)) - blue) / f;
        this.mColors = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            float f2 = (float) i4;
            this.mColors[i4] = Color.argb((int) ((alpha2 * f2) + alpha), (int) ((red2 * f2) + red), (int) ((green2 * f2) + green), (int) ((f2 * blue2) + blue));
        }
    }

    /* access modifiers changed from: private */
    public boolean processTouch(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            return true;
        }
        if (motionEvent.getAction() != 2 && motionEvent.getAction() != 1) {
            return false;
        }
        updateView(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    private void updateView(float f, float f2) {
        int i = 0;
        while (i < this.mColorFullRects.length) {
            Rect rect = this.mColorFullRects[i];
            if (rect == null || !rect.contains((int) f, (int) f2) || i == this.mSelectedItem) {
                i++;
            } else {
                this.mSelectedItem = i;
                notifyChanged();
                invalidate();
                return;
            }
        }
    }

    private void notifyChanged() {
        if (this.mListener != null) {
            this.mListener.onColorChanged(this.mSelectedItem, this.mColors[this.mSelectedItem]);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mColorRects.length > 0) {
            drawSlider(canvas);
        }
    }

    private void drawSlider(Canvas canvas) {
        for (int i = 0; i < this.mColorRects.length; i++) {
            this.mPaint.setColor(this.mColors[i]);
            if (i == this.mSelectedItem) {
                canvas.drawRect(this.mColorFullRects[i], this.mPaint);
                canvas.drawRect(this.mColorFullRects[i], this.mSelectorPaint);
            } else {
                canvas.drawRect(this.mColorRects[i], this.mPaint);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        calculateRectangles();
    }

    private void calculateRectangles() {
        float measuredHeight = (float) getMeasuredHeight();
        float measuredWidth = ((float) getMeasuredWidth()) / ((float) this.mColors.length);
        this.mColorRects = new Rect[this.mColors.length];
        this.mColorFullRects = new Rect[this.mColors.length];
        float f = 0.1f * measuredHeight;
        int i = 0;
        while (i < this.mColors.length) {
            int i2 = (int) (((float) i) * measuredWidth);
            int i3 = i + 1;
            int i4 = (int) (((float) i3) * measuredWidth);
            this.mColorRects[i] = new Rect(i2, (int) f, i4, (int) (measuredHeight - f));
            this.mColorFullRects[i] = new Rect(i2, 0, i4, (int) measuredHeight);
            i = i3;
        }
    }
}