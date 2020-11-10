package com.teamvinay.newphotoediter.ui.view;

import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.teamvinay.newphotoediter.ui.view.ScaleGestureDetector;

/* renamed from: lisa.studio.photoeditor.ui.view.MultiTouchListener */
class MultiTouchListener implements View.OnTouchListener {
    private static final int INVALID_POINTER_ID = -1;
    private View deleteView;
    /* access modifiers changed from: private */
    public boolean isRotateEnabled = true;
    /* access modifiers changed from: private */
    public boolean isScaleEnabled = true;
    /* access modifiers changed from: private */
    public boolean isTranslateEnabled = true;
    private int[] location = new int[2];
    private int mActivePointerId = -1;
    private final GestureDetector mGestureListener;
    /* access modifiers changed from: private */
    public boolean mIsTextPinchZoomable;
    /* access modifiers changed from: private */
    public OnGestureControl mOnGestureControl;
    private OnPhotoEditorListener mOnPhotoEditorListener;
    private float mPrevRawX;
    private float mPrevRawY;
    private float mPrevX;
    private float mPrevY;
    private ScaleGestureDetector mScaleGestureDetector;
    /* access modifiers changed from: private */
    public float maximumScale = 10.0f;
    /* access modifiers changed from: private */
    public float minimumScale = 0.5f;
    private OnMultiTouchListener onMultiTouchListener;
    private Rect outRect;
    private RelativeLayout parentView;
    private ImageView photoEditImageView;

    /* renamed from: lisa.studio.photoeditor.ui.view.MultiTouchListener$OnGestureControl */
    interface OnGestureControl {
        void onClick();

        void onLongClick();
    }

    /* renamed from: lisa.studio.photoeditor.ui.view.MultiTouchListener$OnMultiTouchListener */
    interface OnMultiTouchListener {
        void onEditTextClickListener(String str, int i);

        void onRemoveViewListener(View view);
    }

    private static float adjustAngle(float f) {
        return f > 180.0f ? f - 360.0f : f < -180.0f ? f + 360.0f : f;
    }

    MultiTouchListener(@Nullable View view, RelativeLayout relativeLayout, ImageView imageView, boolean z, OnPhotoEditorListener onPhotoEditorListener) {
        this.mIsTextPinchZoomable = z;
        this.mScaleGestureDetector = new ScaleGestureDetector(new ScaleGestureListener());
        this.mGestureListener = new GestureDetector(new GestureListener());
        this.deleteView = view;
        this.parentView = relativeLayout;
        this.photoEditImageView = imageView;
        this.mOnPhotoEditorListener = onPhotoEditorListener;
        if (view != null) {
            this.outRect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        } else {
            this.outRect = new Rect(0, 0, 0, 0);
        }
    }

    /* access modifiers changed from: private */
    public static void move(View view, TransformInfo transformInfo) {
        computeRenderOffset(view, transformInfo.pivotX, transformInfo.pivotY);
        adjustTranslation(view, transformInfo.deltaX, transformInfo.deltaY);
        float max = Math.max(transformInfo.minimumScale, Math.min(transformInfo.maximumScale, view.getScaleX() * transformInfo.deltaScale));
        view.setScaleX(max);
        view.setScaleY(max);
        view.setRotation(adjustAngle(view.getRotation() + transformInfo.deltaAngle));
    }

    private static void adjustTranslation(View view, float f, float f2) {
        float[] fArr = {f, f2};
        view.getMatrix().mapVectors(fArr);
        view.setTranslationX(view.getTranslationX() + fArr[0]);
        view.setTranslationY(view.getTranslationY() + fArr[1]);
    }

    private static void computeRenderOffset(View view, float f, float f2) {
        if (view.getPivotX() != f || view.getPivotY() != f2) {
            float[] fArr = {0.0f, 0.0f};
            view.getMatrix().mapPoints(fArr);
            view.setPivotX(f);
            view.setPivotY(f2);
            float[] fArr2 = {0.0f, 0.0f};
            view.getMatrix().mapPoints(fArr2);
            float f3 = fArr2[0] - fArr[0];
            float f4 = fArr2[1] - fArr[1];
            view.setTranslationX(view.getTranslationX() - f3);
            view.setTranslationY(view.getTranslationY() - f4);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.mScaleGestureDetector.onTouchEvent(view, motionEvent);
        this.mGestureListener.onTouchEvent(motionEvent);
        if (!this.isTranslateEnabled) {
            return true;
        }
        int action = motionEvent.getAction();
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int actionMasked = motionEvent.getActionMasked() & action;
        int i = 0;
        if (actionMasked != 6) {
            switch (actionMasked) {
                case 0:
                    this.mPrevX = motionEvent.getX();
                    this.mPrevY = motionEvent.getY();
                    this.mPrevRawX = motionEvent.getRawX();
                    this.mPrevRawY = motionEvent.getRawY();
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    if (this.deleteView != null) {
                        this.deleteView.setVisibility(0);
                    }
                    view.bringToFront();
                    firePhotoEditorSDKListener(view, true);
                    break;
                case 1:
                    this.mActivePointerId = -1;
                    if (this.deleteView == null || !isViewInBounds(this.deleteView, rawX, rawY)) {
                        if (!isViewInBounds(this.photoEditImageView, rawX, rawY)) {
                            view.animate().translationY(0.0f).translationY(0.0f);
                        }
                    } else if (this.onMultiTouchListener != null) {
                        this.onMultiTouchListener.onRemoveViewListener(view);
                    }
                    if (this.deleteView != null) {
                        this.deleteView.setVisibility(8);
                    }
                    firePhotoEditorSDKListener(view, false);
                    break;
                case 2:
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex != -1) {
                        float x = motionEvent.getX(findPointerIndex);
                        float y = motionEvent.getY(findPointerIndex);
                        if (!this.mScaleGestureDetector.isInProgress()) {
                            adjustTranslation(view, x - this.mPrevX, y - this.mPrevY);
                            break;
                        }
                    }
                    break;
                case 3:
                    this.mActivePointerId = -1;
                    break;
            }
        } else {
            int i2 = (65280 & action) >> 8;
            if (motionEvent.getPointerId(i2) == this.mActivePointerId) {
                if (i2 == 0) {
                    i = 1;
                }
                this.mPrevX = motionEvent.getX(i);
                this.mPrevY = motionEvent.getY(i);
                this.mActivePointerId = motionEvent.getPointerId(i);
            }
        }
        return true;
    }

    private void firePhotoEditorSDKListener(View view, boolean z) {
        Object tag = view.getTag();
        if (this.mOnPhotoEditorListener != null && tag != null && (tag instanceof ViewType)) {
            if (z) {
                this.mOnPhotoEditorListener.onStartViewChangeListener((ViewType) view.getTag());
            } else {
                this.mOnPhotoEditorListener.onStopViewChangeListener((ViewType) view.getTag());
            }
        }
    }

    private boolean isViewInBounds(View view, int i, int i2) {
        view.getDrawingRect(this.outRect);
        view.getLocationOnScreen(this.location);
        this.outRect.offset(this.location[0], this.location[1]);
        return this.outRect.contains(i, i2);
    }

    /* access modifiers changed from: package-private */
    public void setOnMultiTouchListener(OnMultiTouchListener onMultiTouchListener2) {
        this.onMultiTouchListener = onMultiTouchListener2;
    }

    /* renamed from: lisa.studio.photoeditor.ui.view.MultiTouchListener$ScaleGestureListener */
    private class ScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private float mPivotX;
        private float mPivotY;
        private Vector2D mPrevSpanVector;

        private ScaleGestureListener() {
            this.mPrevSpanVector = new Vector2D();
        }

        public boolean onScaleBegin(View view, ScaleGestureDetector scaleGestureDetector) {
            this.mPivotX = scaleGestureDetector.getFocusX();
            this.mPivotY = scaleGestureDetector.getFocusY();
            this.mPrevSpanVector.set(scaleGestureDetector.getCurrentSpanVector());
            return MultiTouchListener.this.mIsTextPinchZoomable;
        }

        public boolean onScale(View view, ScaleGestureDetector scaleGestureDetector) {
            TransformInfo transformInfo = new TransformInfo();
            transformInfo.deltaScale = MultiTouchListener.this.isScaleEnabled ? scaleGestureDetector.getScaleFactor() : 1.0f;
            float f = 0.0f;
            transformInfo.deltaAngle = MultiTouchListener.this.isRotateEnabled ? Vector2D.getAngle(this.mPrevSpanVector, scaleGestureDetector.getCurrentSpanVector()) : 0.0f;
            transformInfo.deltaX = MultiTouchListener.this.isTranslateEnabled ? scaleGestureDetector.getFocusX() - this.mPivotX : 0.0f;
            if (MultiTouchListener.this.isTranslateEnabled) {
                f = scaleGestureDetector.getFocusY() - this.mPivotY;
            }
            transformInfo.deltaY = f;
            transformInfo.pivotX = this.mPivotX;
            transformInfo.pivotY = this.mPivotY;
            transformInfo.minimumScale = MultiTouchListener.this.minimumScale;
            transformInfo.maximumScale = MultiTouchListener.this.maximumScale;
            MultiTouchListener.move(view, transformInfo);
            return !MultiTouchListener.this.mIsTextPinchZoomable;
        }
    }

    /* renamed from: lisa.studio.photoeditor.ui.view.MultiTouchListener$TransformInfo */
    private class TransformInfo {
        float deltaAngle;
        float deltaScale;
        float deltaX;
        float deltaY;
        float maximumScale;
        float minimumScale;
        float pivotX;
        float pivotY;

        private TransformInfo() {
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnGestureControl(OnGestureControl onGestureControl) {
        this.mOnGestureControl = onGestureControl;
    }

    /* renamed from: lisa.studio.photoeditor.ui.view.MultiTouchListener$GestureListener */
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (MultiTouchListener.this.mOnGestureControl == null) {
                return true;
            }
            MultiTouchListener.this.mOnGestureControl.onClick();
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            super.onLongPress(motionEvent);
            if (MultiTouchListener.this.mOnGestureControl != null) {
                MultiTouchListener.this.mOnGestureControl.onLongClick();
            }
        }
    }
}