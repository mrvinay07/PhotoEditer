package com.teamvinay.newphotoediter.featuresfoto.picker.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.Scroller;

public class TouchImageView extends ImageView {
    private static final String DEBUG = "DEBUG";
    private static final float SUPER_MAX_MULTIPLIER = 1.25f;
    private static final float SUPER_MIN_MULTIPLIER = 0.75f;
    /* access modifiers changed from: private */
    public Context context;
    private ZoomVariables delayedZoomVariables;
    /* access modifiers changed from: private */
    public GestureDetector.OnDoubleTapListener doubleTapListener = null;
    /* access modifiers changed from: private */
    public Fling fling;
    private boolean imageRenderedAtLeastOnce;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public float[] f624m;
    /* access modifiers changed from: private */
    public GestureDetector mGestureDetector;
    /* access modifiers changed from: private */
    public ScaleGestureDetector mScaleDetector;
    private ImageView.ScaleType mScaleType;
    private float matchViewHeight;
    private float matchViewWidth;
    /* access modifiers changed from: private */
    public Matrix matrix;
    /* access modifiers changed from: private */
    public float maxScale;
    /* access modifiers changed from: private */
    public float minScale;
    /* access modifiers changed from: private */
    public float normalizedScale;
    private boolean onDrawReady;
    private float prevMatchViewHeight;
    private float prevMatchViewWidth;
    private Matrix prevMatrix;
    private int prevViewHeight;
    private int prevViewWidth;
    /* access modifiers changed from: private */
    public State state;
    private float superMaxScale;
    private float superMinScale;
    /* access modifiers changed from: private */
    public OnTouchImageViewListener touchImageViewListener = null;
    /* access modifiers changed from: private */
    public View.OnTouchListener userTouchListener = null;
    /* access modifiers changed from: private */
    public int viewHeight;
    /* access modifiers changed from: private */
    public int viewWidth;

    public interface OnTouchImageViewListener {
        void onMove();
    }

    private enum State {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    /* access modifiers changed from: private */
    public float getFixDragTrans(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    private float getFixTrans(float f, float f2, float f3) {
        float f4;
        float f5;
        if (f3 <= f2) {
            f4 = f2 - f3;
            f5 = 0.0f;
        } else {
            f5 = f2 - f3;
            f4 = 0.0f;
        }
        if (f < f5) {
            return (-f) + f5;
        }
        if (f > f4) {
            return (-f) + f4;
        }
        return 0.0f;
    }

    public TouchImageView(Context context2) {
        super(context2);
        sharedConstructing(context2);
    }

    public TouchImageView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        sharedConstructing(context2);
    }

    public TouchImageView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        sharedConstructing(context2);
    }

    private void sharedConstructing(Context context2) {
        super.setClickable(true);
        this.context = context2;
        this.mScaleDetector = new ScaleGestureDetector(context2, new ScaleListener(this, (C11481) null));
        this.mGestureDetector = new GestureDetector(context2, new GestureListener(this, (C11481) null));
        this.matrix = new Matrix();
        this.prevMatrix = new Matrix();
        this.f624m = new float[9];
        this.normalizedScale = 1.0f;
        if (this.mScaleType == null) {
            this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        }
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.superMinScale = this.minScale * SUPER_MIN_MULTIPLIER;
        this.superMaxScale = this.maxScale * SUPER_MAX_MULTIPLIER;
        setImageMatrix(this.matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
        setState(State.NONE);
        this.onDrawReady = false;
        super.setOnTouchListener(new PrivateOnTouchListener(this, (C11481) null));
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.userTouchListener = onTouchListener;
    }

    public void setOnTouchImageViewListener(OnTouchImageViewListener onTouchImageViewListener) {
        this.touchImageViewListener = onTouchImageViewListener;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.doubleTapListener = onDoubleTapListener;
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == ImageView.ScaleType.FIT_START || scaleType == ImageView.ScaleType.FIT_END) {
            throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
        } else if (scaleType == ImageView.ScaleType.MATRIX) {
            super.setScaleType(ImageView.ScaleType.MATRIX);
        } else {
            this.mScaleType = scaleType;
            if (this.onDrawReady) {
                setZoom(this);
            }
        }
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public boolean isZoomed() {
        return this.normalizedScale != 1.0f;
    }

    public RectF getZoomedRect() {
        if (this.mScaleType != ImageView.ScaleType.FIT_XY) {
            PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap(0.0f, 0.0f, true);
            PointF transformCoordTouchToBitmap2 = transformCoordTouchToBitmap((float) this.viewWidth, (float) this.viewHeight, true);
            float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
            float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
            return new RectF(transformCoordTouchToBitmap.x / intrinsicWidth, transformCoordTouchToBitmap.y / intrinsicHeight, transformCoordTouchToBitmap2.x / intrinsicWidth, transformCoordTouchToBitmap2.y / intrinsicHeight);
        }
        throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
    }

    private void savePreviousImageValues() {
        if (this.matrix != null && this.viewHeight != 0 && this.viewWidth != 0) {
            this.matrix.getValues(this.f624m);
            this.prevMatrix.setValues(this.f624m);
            this.prevMatchViewHeight = this.matchViewHeight;
            this.prevMatchViewWidth = this.matchViewWidth;
            this.prevViewHeight = this.viewHeight;
            this.prevViewWidth = this.viewWidth;
        }
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.normalizedScale);
        bundle.putFloat("matchViewHeight", this.matchViewHeight);
        bundle.putFloat("matchViewWidth", this.matchViewWidth);
        bundle.putInt("viewWidth", this.viewWidth);
        bundle.putInt("viewHeight", this.viewHeight);
        this.matrix.getValues(this.f624m);
        bundle.putFloatArray("matrix", this.f624m);
        bundle.putBoolean("imageRendered", this.imageRenderedAtLeastOnce);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.normalizedScale = bundle.getFloat("saveScale");
            this.f624m = bundle.getFloatArray("matrix");
            this.prevMatrix.setValues(this.f624m);
            this.prevMatchViewHeight = bundle.getFloat("matchViewHeight");
            this.prevMatchViewWidth = bundle.getFloat("matchViewWidth");
            this.prevViewHeight = bundle.getInt("viewHeight");
            this.prevViewWidth = bundle.getInt("viewWidth");
            this.imageRenderedAtLeastOnce = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.onDrawReady = true;
        this.imageRenderedAtLeastOnce = true;
        if (this.delayedZoomVariables != null) {
            setZoom(this.delayedZoomVariables.scale, this.delayedZoomVariables.focusX, this.delayedZoomVariables.focusY, this.delayedZoomVariables.scaleType);
            this.delayedZoomVariables = null;
        }
        super.onDraw(canvas);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        savePreviousImageValues();
    }

    public float getMaxZoom() {
        return this.maxScale;
    }

    public void setMaxZoom(float f) {
        this.maxScale = f;
        this.superMaxScale = this.maxScale * SUPER_MAX_MULTIPLIER;
    }

    public float getMinZoom() {
        return this.minScale;
    }

    public float getCurrentZoom() {
        return this.normalizedScale;
    }

    public void setMinZoom(float f) {
        this.minScale = f;
        this.superMinScale = this.minScale * SUPER_MIN_MULTIPLIER;
    }

    public void resetZoom() {
        this.normalizedScale = 1.0f;
        fitImageToView();
    }

    public void setZoom(float f) {
        setZoom(f, 0.5f, 0.5f);
    }

    public void setZoom(float f, float f2, float f3) {
        setZoom(f, f2, f3, this.mScaleType);
    }

    public void setZoom(float f, float f2, float f3, ImageView.ScaleType scaleType) {
        if (!this.onDrawReady) {
            this.delayedZoomVariables = new ZoomVariables(f, f2, f3, scaleType);
            return;
        }
        if (scaleType != this.mScaleType) {
            setScaleType(scaleType);
        }
        resetZoom();
        scaleImage((double) f, (float) (this.viewWidth / 2), (float) (this.viewHeight / 2), true);
        this.matrix.getValues(this.f624m);
        this.f624m[2] = -((f2 * getImageWidth()) - (((float) this.viewWidth) * 0.5f));
        this.f624m[5] = -((f3 * getImageHeight()) - (((float) this.viewHeight) * 0.5f));
        this.matrix.setValues(this.f624m);
        fixTrans();
        setImageMatrix(this.matrix);
    }

    public void setZoom(TouchImageView touchImageView) {
        PointF scrollPosition = touchImageView.getScrollPosition();
        setZoom(touchImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, touchImageView.getScaleType());
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap((float) (this.viewWidth / 2), (float) (this.viewHeight / 2), true);
        transformCoordTouchToBitmap.x /= (float) intrinsicWidth;
        transformCoordTouchToBitmap.y /= (float) intrinsicHeight;
        return transformCoordTouchToBitmap;
    }

    public void setScrollPosition(float f, float f2) {
        setZoom(this.normalizedScale, f, f2);
    }

    /* access modifiers changed from: private */
    public void fixTrans() {
        this.matrix.getValues(this.f624m);
        float f = this.f624m[2];
        float f2 = this.f624m[5];
        float fixTrans = getFixTrans(f, (float) this.viewWidth, getImageWidth());
        float fixTrans2 = getFixTrans(f2, (float) this.viewHeight, getImageHeight());
        if (fixTrans != 0.0f || fixTrans2 != 0.0f) {
            this.matrix.postTranslate(fixTrans, fixTrans2);
        }
    }

    /* access modifiers changed from: private */
    public void fixScaleTrans() {
        fixTrans();
        this.matrix.getValues(this.f624m);
        if (getImageWidth() < ((float) this.viewWidth)) {
            this.f624m[2] = (((float) this.viewWidth) - getImageWidth()) / 2.0f;
        }
        if (getImageHeight() < ((float) this.viewHeight)) {
            this.f624m[5] = (((float) this.viewHeight) - getImageHeight()) / 2.0f;
        }
        this.matrix.setValues(this.f624m);
    }

    /* access modifiers changed from: private */
    public float getImageWidth() {
        return this.matchViewWidth * this.normalizedScale;
    }

    /* access modifiers changed from: private */
    public float getImageHeight() {
        return this.matchViewHeight * this.normalizedScale;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.viewWidth = setViewSize(mode, size, intrinsicWidth);
        this.viewHeight = setViewSize(mode2, size2, intrinsicHeight);
        setMeasuredDimension(this.viewWidth, this.viewHeight);
        fitImageToView();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
        r1 = java.lang.Math.min(r1, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        r5 = ((float) r11.viewWidth) - (r1 * r2);
        r7 = ((float) r11.viewHeight) - (r3 * r4);
        r11.matchViewWidth = ((float) r11.viewWidth) - r5;
        r11.matchViewHeight = ((float) r11.viewHeight) - r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007b, code lost:
        if (isZoomed() != false) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007f, code lost:
        if (r11.imageRenderedAtLeastOnce != false) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0081, code lost:
        r11.matrix.setScale(r1, r3);
        r11.matrix.postTranslate(r5 / 2.0f, r7 / 2.0f);
        r11.normalizedScale = 1.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0097, code lost:
        if (r11.prevMatchViewWidth == 0.0f) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009d, code lost:
        if (r11.prevMatchViewHeight != 0.0f) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009f, code lost:
        savePreviousImageValues();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a2, code lost:
        r11.prevMatrix.getValues(r11.f624m);
        r11.f624m[0] = (r11.matchViewWidth / r2) * r11.normalizedScale;
        r11.f624m[4] = (r11.matchViewHeight / r4) * r11.normalizedScale;
        r4 = r11.f624m[2];
        r10 = r11.f624m[5];
        translateMatrixAfterRotate(2, r4, r11.prevMatchViewWidth * r11.normalizedScale, getImageWidth(), r11.prevViewWidth, r11.viewWidth, r9);
        translateMatrixAfterRotate(5, r10, r11.prevMatchViewHeight * r11.normalizedScale, getImageHeight(), r11.prevViewHeight, r11.viewHeight, r0);
        r11.matrix.setValues(r11.f624m);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00fa, code lost:
        fixTrans();
        setImageMatrix(r11.matrix);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0102, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fitImageToView() {
        /*
            r11 = this;
            android.graphics.drawable.Drawable r0 = r11.getDrawable()
            if (r0 == 0) goto L_0x0104
            int r1 = r0.getIntrinsicWidth()
            if (r1 == 0) goto L_0x0104
            int r1 = r0.getIntrinsicHeight()
            if (r1 != 0) goto L_0x0014
            goto L_0x0104
        L_0x0014:
            android.graphics.Matrix r1 = r11.matrix
            if (r1 == 0) goto L_0x0103
            android.graphics.Matrix r1 = r11.prevMatrix
            if (r1 != 0) goto L_0x001e
            goto L_0x0103
        L_0x001e:
            int r9 = r0.getIntrinsicWidth()
            int r0 = r0.getIntrinsicHeight()
            int r1 = r11.viewWidth
            float r1 = (float) r1
            float r2 = (float) r9
            float r1 = r1 / r2
            int r3 = r11.viewHeight
            float r3 = (float) r3
            float r4 = (float) r0
            float r3 = r3 / r4
            int[] r5 = lisa.studio.photoeditor.featuresfoto.picker.widget.TouchImageView.C11481.$SwitchMap$android$widget$ImageView$ScaleType
            android.widget.ImageView$ScaleType r6 = r11.mScaleType
            int r6 = r6.ordinal()
            r5 = r5[r6]
            r6 = 1065353216(0x3f800000, float:1.0)
            switch(r5) {
                case 1: goto L_0x005b;
                case 2: goto L_0x0055;
                case 3: goto L_0x0047;
                case 4: goto L_0x0050;
                case 5: goto L_0x005f;
                default: goto L_0x003f;
            }
        L_0x003f:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "TouchImageView does not support FIT_START or FIT_END"
            r0.<init>(r1)
            throw r0
        L_0x0047:
            float r1 = java.lang.Math.min(r1, r3)
            float r1 = java.lang.Math.min(r6, r1)
            r3 = r1
        L_0x0050:
            float r1 = java.lang.Math.min(r1, r3)
            goto L_0x0059
        L_0x0055:
            float r1 = java.lang.Math.max(r1, r3)
        L_0x0059:
            r3 = r1
            goto L_0x005f
        L_0x005b:
            r1 = 1065353216(0x3f800000, float:1.0)
            r3 = 1065353216(0x3f800000, float:1.0)
        L_0x005f:
            int r5 = r11.viewWidth
            float r5 = (float) r5
            float r7 = r1 * r2
            float r5 = r5 - r7
            int r7 = r11.viewHeight
            float r7 = (float) r7
            float r8 = r3 * r4
            float r7 = r7 - r8
            int r8 = r11.viewWidth
            float r8 = (float) r8
            float r8 = r8 - r5
            r11.matchViewWidth = r8
            int r8 = r11.viewHeight
            float r8 = (float) r8
            float r8 = r8 - r7
            r11.matchViewHeight = r8
            boolean r8 = r11.isZoomed()
            if (r8 != 0) goto L_0x0092
            boolean r8 = r11.imageRenderedAtLeastOnce
            if (r8 != 0) goto L_0x0092
            android.graphics.Matrix r0 = r11.matrix
            r0.setScale(r1, r3)
            android.graphics.Matrix r0 = r11.matrix
            r1 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 / r1
            float r7 = r7 / r1
            r0.postTranslate(r5, r7)
            r11.normalizedScale = r6
            goto L_0x00fa
        L_0x0092:
            float r1 = r11.prevMatchViewWidth
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x009f
            float r1 = r11.prevMatchViewHeight
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x00a2
        L_0x009f:
            r11.savePreviousImageValues()
        L_0x00a2:
            android.graphics.Matrix r1 = r11.prevMatrix
            float[] r3 = r11.f624m
            r1.getValues(r3)
            float[] r1 = r11.f624m
            r3 = 0
            float r5 = r11.matchViewWidth
            float r5 = r5 / r2
            float r2 = r11.normalizedScale
            float r5 = r5 * r2
            r1[r3] = r5
            float[] r1 = r11.f624m
            r2 = 4
            float r3 = r11.matchViewHeight
            float r3 = r3 / r4
            float r4 = r11.normalizedScale
            float r3 = r3 * r4
            r1[r2] = r3
            float[] r1 = r11.f624m
            r2 = 2
            r4 = r1[r2]
            float[] r1 = r11.f624m
            r2 = 5
            r10 = r1[r2]
            float r1 = r11.prevMatchViewWidth
            float r2 = r11.normalizedScale
            float r5 = r1 * r2
            float r6 = r11.getImageWidth()
            r3 = 2
            int r7 = r11.prevViewWidth
            int r8 = r11.viewWidth
            r2 = r11
            r2.translateMatrixAfterRotate(r3, r4, r5, r6, r7, r8, r9)
            float r1 = r11.prevMatchViewHeight
            float r2 = r11.normalizedScale
            float r4 = r1 * r2
            float r5 = r11.getImageHeight()
            r2 = 5
            int r6 = r11.prevViewHeight
            int r7 = r11.viewHeight
            r1 = r11
            r3 = r10
            r8 = r0
            r1.translateMatrixAfterRotate(r2, r3, r4, r5, r6, r7, r8)
            android.graphics.Matrix r0 = r11.matrix
            float[] r1 = r11.f624m
            r0.setValues(r1)
        L_0x00fa:
            r11.fixTrans()
            android.graphics.Matrix r0 = r11.matrix
            r11.setImageMatrix(r0)
            return
        L_0x0103:
            return
        L_0x0104:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lisa.studio.photoeditor.featuresfoto.picker.widget.TouchImageView.fitImageToView():void");
    }

    /* renamed from: lisa.studio.photoeditor.featuresfoto.picker.widget.TouchImageView$1 */
    static /* synthetic */ class C11481 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                android.widget.ImageView$ScaleType[] r0 = android.widget.ImageView.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$widget$ImageView$ScaleType = r0
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x001f }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x002a }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.teamvinay.newphotoediter.featuresfoto.picker.widget.TouchImageView.C11481.<clinit>():void");
        }
    }

    private int setViewSize(int i, int i2, int i3) {
        if (i != Integer.MIN_VALUE) {
            return i != 0 ? i2 : i3;
        }
        return Math.min(i3, i2);
    }

    private void translateMatrixAfterRotate(int i, float f, float f2, float f3, int i2, int i3, int i4) {
        float f4 = (float) i3;
        if (f3 < f4) {
            this.f624m[i] = (f4 - (((float) i4) * this.f624m[0])) * 0.5f;
        } else if (f > 0.0f) {
            this.f624m[i] = -((f3 - f4) * 0.5f);
        } else {
            this.f624m[i] = -((((Math.abs(f) + (((float) i2) * 0.5f)) / f2) * f3) - (f4 * 0.5f));
        }
    }

    /* access modifiers changed from: private */
    public void setState(State state2) {
        this.state = state2;
    }

    public boolean canScrollHorizontallyFroyo(int i) {
        return canScrollHorizontally(i);
    }

    public boolean canScrollHorizontally(int i) {
        this.matrix.getValues(this.f624m);
        float f = this.f624m[2];
        if (getImageWidth() < ((float) this.viewWidth)) {
            return false;
        }
        if (f >= -1.0f && i < 0) {
            return false;
        }
        if (Math.abs(f) + ((float) this.viewWidth) + 1.0f < getImageWidth() || i <= 0) {
            return true;
        }
        return false;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        /* synthetic */ GestureListener(TouchImageView touchImageView, C11481 r2) {
            this();
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (TouchImageView.this.doubleTapListener != null) {
                return TouchImageView.this.doubleTapListener.onSingleTapConfirmed(motionEvent);
            }
            return TouchImageView.this.performClick();
        }

        public void onLongPress(MotionEvent motionEvent) {
            TouchImageView.this.performLongClick();
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (TouchImageView.this.fling != null) {
                TouchImageView.this.fling.cancelFling();
            }
            Fling unused = TouchImageView.this.fling = new Fling((int) f, (int) f2);
            TouchImageView.this.compatPostOnAnimation(TouchImageView.this.fling);
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap = TouchImageView.this.doubleTapListener != null ? TouchImageView.this.doubleTapListener.onDoubleTap(motionEvent) : false;
            if (TouchImageView.this.state != State.NONE) {
                return onDoubleTap;
            }
            TouchImageView.this.compatPostOnAnimation(new DoubleTapZoom(TouchImageView.this.normalizedScale == TouchImageView.this.minScale ? TouchImageView.this.maxScale : TouchImageView.this.minScale, motionEvent.getX(), motionEvent.getY(), false));
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (TouchImageView.this.doubleTapListener != null) {
                return TouchImageView.this.doubleTapListener.onDoubleTapEvent(motionEvent);
            }
            return false;
        }
    }

    private class PrivateOnTouchListener implements View.OnTouchListener {
        private PointF last;

        private PrivateOnTouchListener() {
            this.last = new PointF();
        }

        /* synthetic */ PrivateOnTouchListener(TouchImageView touchImageView, C11481 r2) {
            this();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            TouchImageView.this.mScaleDetector.onTouchEvent(motionEvent);
            TouchImageView.this.mGestureDetector.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (TouchImageView.this.state == State.NONE || TouchImageView.this.state == State.DRAG || TouchImageView.this.state == State.FLING) {
                int action = motionEvent.getAction();
                if (action != 6) {
                    switch (action) {
                        case 0:
                            this.last.set(pointF);
                            if (TouchImageView.this.fling != null) {
                                TouchImageView.this.fling.cancelFling();
                            }
                            TouchImageView.this.setState(State.DRAG);
                            break;
                        case 1:
                            break;
                        case 2:
                            if (TouchImageView.this.state == State.DRAG) {
                                float f = pointF.x - this.last.x;
                                float f2 = pointF.y - this.last.y;
                                TouchImageView.this.matrix.postTranslate(TouchImageView.this.getFixDragTrans(f, (float) TouchImageView.this.viewWidth, TouchImageView.this.getImageWidth()), TouchImageView.this.getFixDragTrans(f2, (float) TouchImageView.this.viewHeight, TouchImageView.this.getImageHeight()));
                                TouchImageView.this.fixTrans();
                                this.last.set(pointF.x, pointF.y);
                                break;
                            }
                            break;
                    }
                }
                TouchImageView.this.setState(State.NONE);
            }
            TouchImageView.this.setImageMatrix(TouchImageView.this.matrix);
            if (TouchImageView.this.userTouchListener != null) {
                TouchImageView.this.userTouchListener.onTouch(view, motionEvent);
            }
            if (TouchImageView.this.touchImageViewListener == null) {
                return true;
            }
            TouchImageView.this.touchImageViewListener.onMove();
            return true;
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        /* synthetic */ ScaleListener(TouchImageView touchImageView, C11481 r2) {
            this();
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            TouchImageView.this.setState(State.ZOOM);
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            TouchImageView.this.scaleImage((double) scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            if (TouchImageView.this.touchImageViewListener == null) {
                return true;
            }
            TouchImageView.this.touchImageViewListener.onMove();
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            TouchImageView.this.setState(State.NONE);
            float access$700 = TouchImageView.this.normalizedScale;
            boolean z = true;
            if (TouchImageView.this.normalizedScale > TouchImageView.this.maxScale) {
                access$700 = TouchImageView.this.maxScale;
            } else if (TouchImageView.this.normalizedScale < TouchImageView.this.minScale) {
                access$700 = TouchImageView.this.minScale;
            } else {
                z = false;
            }
            float f = access$700;
            if (z) {
                TouchImageView.this.compatPostOnAnimation(new DoubleTapZoom(f, (float) (TouchImageView.this.viewWidth / 2), (float) (TouchImageView.this.viewHeight / 2), true));
            }
        }
    }

    /* access modifiers changed from: private */
    public void scaleImage(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.superMinScale;
            f4 = this.superMaxScale;
        } else {
            f3 = this.minScale;
            f4 = this.maxScale;
        }
        float f5 = this.normalizedScale;
        this.normalizedScale = (float) (((double) this.normalizedScale) * d);
        if (this.normalizedScale > f4) {
            this.normalizedScale = f4;
            d = (double) (f4 / f5);
        } else if (this.normalizedScale < f3) {
            this.normalizedScale = f3;
            d = (double) (f3 / f5);
        }
        float f6 = (float) d;
        this.matrix.postScale(f6, f6, f, f2);
        fixScaleTrans();
    }

    private class DoubleTapZoom implements Runnable {
        private static final float ZOOM_TIME = 500.0f;
        private float bitmapX;
        private float bitmapY;
        private PointF endTouch;
        private AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        private long startTime;
        private PointF startTouch;
        private float startZoom;
        private boolean stretchImageToSuper;
        private float targetZoom;

        DoubleTapZoom(float f, float f2, float f3, boolean z) {
            TouchImageView.this.setState(State.ANIMATE_ZOOM);
            this.startTime = System.currentTimeMillis();
            this.startZoom = TouchImageView.this.normalizedScale;
            this.targetZoom = f;
            this.stretchImageToSuper = z;
            PointF access$2300 = TouchImageView.this.transformCoordTouchToBitmap(f2, f3, false);
            this.bitmapX = access$2300.x;
            this.bitmapY = access$2300.y;
            this.startTouch = TouchImageView.this.transformCoordBitmapToTouch(this.bitmapX, this.bitmapY);
            this.endTouch = new PointF((float) (TouchImageView.this.viewWidth / 2), (float) (TouchImageView.this.viewHeight / 2));
        }

        public void run() {
            float interpolate = interpolate();
            TouchImageView.this.scaleImage(calculateDeltaScale(interpolate), this.bitmapX, this.bitmapY, this.stretchImageToSuper);
            translateImageToCenterTouchPosition(interpolate);
            TouchImageView.this.fixScaleTrans();
            TouchImageView.this.setImageMatrix(TouchImageView.this.matrix);
            if (TouchImageView.this.touchImageViewListener != null) {
                TouchImageView.this.touchImageViewListener.onMove();
            }
            if (interpolate < 1.0f) {
                TouchImageView.this.compatPostOnAnimation(this);
            } else {
                TouchImageView.this.setState(State.NONE);
            }
        }

        private void translateImageToCenterTouchPosition(float f) {
            float f2 = this.startTouch.x + ((this.endTouch.x - this.startTouch.x) * f);
            float f3 = this.startTouch.y + (f * (this.endTouch.y - this.startTouch.y));
            PointF access$2400 = TouchImageView.this.transformCoordBitmapToTouch(this.bitmapX, this.bitmapY);
            TouchImageView.this.matrix.postTranslate(f2 - access$2400.x, f3 - access$2400.y);
        }

        private float interpolate() {
            return this.interpolator.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.startTime)) / ZOOM_TIME));
        }

        private double calculateDeltaScale(float f) {
            return ((double) (this.startZoom + (f * (this.targetZoom - this.startZoom)))) / ((double) TouchImageView.this.normalizedScale);
        }
    }

    /* access modifiers changed from: private */
    public PointF transformCoordTouchToBitmap(float f, float f2, boolean z) {
        this.matrix.getValues(this.f624m);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        float f3 = this.f624m[2];
        float f4 = this.f624m[5];
        float imageWidth = ((f - f3) * intrinsicWidth) / getImageWidth();
        float imageHeight = ((f2 - f4) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            imageHeight = Math.min(Math.max(imageHeight, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, imageHeight);
    }

    /* access modifiers changed from: private */
    public PointF transformCoordBitmapToTouch(float f, float f2) {
        this.matrix.getValues(this.f624m);
        return new PointF(this.f624m[2] + (getImageWidth() * (f / ((float) getDrawable().getIntrinsicWidth()))), this.f624m[5] + (getImageHeight() * (f2 / ((float) getDrawable().getIntrinsicHeight()))));
    }

    private class Fling implements Runnable {
        int currX;
        int currY;
        CompatScroller scroller;

        Fling(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            TouchImageView.this.setState(State.FLING);
            this.scroller = new CompatScroller(TouchImageView.this.context);
            TouchImageView.this.matrix.getValues(TouchImageView.this.f624m);
            int i7 = (int) TouchImageView.this.f624m[2];
            int i8 = (int) TouchImageView.this.f624m[5];
            if (TouchImageView.this.getImageWidth() > ((float) TouchImageView.this.viewWidth)) {
                i4 = TouchImageView.this.viewWidth - ((int) TouchImageView.this.getImageWidth());
                i3 = 0;
            } else {
                i4 = i7;
                i3 = i4;
            }
            if (TouchImageView.this.getImageHeight() > ((float) TouchImageView.this.viewHeight)) {
                i6 = TouchImageView.this.viewHeight - ((int) TouchImageView.this.getImageHeight());
                i5 = 0;
            } else {
                i6 = i8;
                i5 = i6;
            }
            this.scroller.fling(i7, i8, i, i2, i4, i3, i6, i5);
            this.currX = i7;
            this.currY = i8;
        }

        public void cancelFling() {
            if (this.scroller != null) {
                TouchImageView.this.setState(State.NONE);
                this.scroller.forceFinished(true);
            }
        }

        public void run() {
            if (TouchImageView.this.touchImageViewListener != null) {
                TouchImageView.this.touchImageViewListener.onMove();
            }
            if (this.scroller.isFinished()) {
                this.scroller = null;
            } else if (this.scroller.computeScrollOffset()) {
                int currX2 = this.scroller.getCurrX();
                int currY2 = this.scroller.getCurrY();
                int i = currX2 - this.currX;
                int i2 = currY2 - this.currY;
                this.currX = currX2;
                this.currY = currY2;
                TouchImageView.this.matrix.postTranslate((float) i, (float) i2);
                TouchImageView.this.fixTrans();
                TouchImageView.this.setImageMatrix(TouchImageView.this.matrix);
                TouchImageView.this.compatPostOnAnimation(this);
            }
        }
    }

    @TargetApi(9)
    private class CompatScroller {
        boolean isPreGingerbread;
        OverScroller overScroller;
        Scroller scroller;

        public CompatScroller(Context context) {
            if (Build.VERSION.SDK_INT < 9) {
                this.isPreGingerbread = true;
                this.scroller = new Scroller(context);
                return;
            }
            this.isPreGingerbread = false;
            this.overScroller = new OverScroller(context);
        }

        public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.isPreGingerbread) {
                this.scroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
            } else {
                this.overScroller.fling(i, i2, i3, i4, i5, i6, i7, i8);
            }
        }

        public void forceFinished(boolean z) {
            if (this.isPreGingerbread) {
                this.scroller.forceFinished(z);
            } else {
                this.overScroller.forceFinished(z);
            }
        }

        public boolean isFinished() {
            if (this.isPreGingerbread) {
                return this.scroller.isFinished();
            }
            return this.overScroller.isFinished();
        }

        public boolean computeScrollOffset() {
            if (this.isPreGingerbread) {
                return this.scroller.computeScrollOffset();
            }
            this.overScroller.computeScrollOffset();
            return this.overScroller.computeScrollOffset();
        }

        public int getCurrX() {
            if (this.isPreGingerbread) {
                return this.scroller.getCurrX();
            }
            return this.overScroller.getCurrX();
        }

        public int getCurrY() {
            if (this.isPreGingerbread) {
                return this.scroller.getCurrY();
            }
            return this.overScroller.getCurrY();
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(16)
    public void compatPostOnAnimation(Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16);
        }
    }

    private class ZoomVariables {
        public float focusX;
        public float focusY;
        public float scale;
        public ImageView.ScaleType scaleType;

        public ZoomVariables(float f, float f2, float f3, ImageView.ScaleType scaleType2) {
            this.scale = f;
            this.focusX = f2;
            this.focusY = f3;
            this.scaleType = scaleType2;
        }
    }

    private void printMatrixInfo() {
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        Log.d(DEBUG, "Scale: " + fArr[0] + " TransX: " + fArr[2] + " TransY: " + fArr[5]);
    }
}
