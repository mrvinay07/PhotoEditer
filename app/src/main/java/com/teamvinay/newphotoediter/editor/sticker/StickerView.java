package com.teamvinay.newphotoediter.editor.sticker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lisa.studio.photoeditor.C1084R;
import lisa.studio.photoeditor.editor.sticker.event.DeleteIconEvent;
import lisa.studio.photoeditor.editor.sticker.event.FlipHorizontallyEvent;
import lisa.studio.photoeditor.editor.sticker.event.ZoomIconEvent;

public class StickerView extends RelativeLayout {
    private static final int DEFAULT_MIN_CLICK_DELAY_TIME = 200;
    public static final int FLIP_HORIZONTALLY = 1;
    public static final int FLIP_VERTICALLY = 2;
    private static final String TAG = "StickerView";
    private final float[] bitmapPoints;
    private final Paint borderPaint;
    private final float[] bounds;
    private boolean bringToFrontCurrentSticker;
    private int circleRadius;
    private boolean constrained;
    private final PointF currentCenterPoint;
    private BitmapStickerIcon currentIcon;
    private int currentMode;
    private float currentMoveingX;
    private float currentMoveingY;
    private final Matrix downMatrix;
    private float downX;
    private float downY;
    private boolean drawCirclePoint;
    private Sticker handlingSticker;
    private final List<BitmapStickerIcon> icons;
    private long lastClickTime;
    private Sticker lastHandlingSticker;
    private final Paint linePaint;
    private boolean locked;
    private PointF midPoint;
    private int minClickDelayTime;
    private final Matrix moveMatrix;
    private float oldDistance;
    private float oldRotation;
    private boolean onMoving;
    private OnStickerOperationListener onStickerOperationListener;
    private Paint paintCircle;
    private final float[] point;
    private boolean showBorder;
    private boolean showIcons;
    private final Matrix sizeMatrix;
    private final RectF stickerRect;
    private final List<Sticker> stickers;
    private final float[] tmp;
    private int touchSlop;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActionMode {
        public static final int CLICK = 4;
        public static final int DRAG = 1;
        public static final int ICON = 3;
        public static final int NONE = 0;
        public static final int ZOOM_WITH_TWO_FINGER = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flip {
    }

    public interface OnStickerOperationListener {
        void onStickerAdded(@NonNull Sticker sticker);

        void onStickerClicked(@NonNull Sticker sticker);

        void onStickerDeleted(@NonNull Sticker sticker);

        void onStickerDoubleTapped(@NonNull Sticker sticker);

        void onStickerDragFinished(@NonNull Sticker sticker);

        void onStickerFlipped(@NonNull Sticker sticker);

        void onStickerTouchOutside();

        void onStickerTouchedDown(@NonNull Sticker sticker);

        void onStickerZoomFinished(@NonNull Sticker sticker);

        void onTouchDownForBeauty(float f, float f2);

        void onTouchDragForBeauty(float f, float f2);

        void onTouchUpForBeauty(float f, float f2);
    }

    public StickerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x00fe  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StickerView(android.content.Context r7, android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            r6.<init>(r7, r8, r9)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            r6.stickers = r9
            java.util.ArrayList r9 = new java.util.ArrayList
            r0 = 4
            r9.<init>(r0)
            r6.icons = r9
            android.graphics.Paint r9 = new android.graphics.Paint
            r9.<init>()
            r6.borderPaint = r9
            android.graphics.Paint r9 = new android.graphics.Paint
            r9.<init>()
            r6.linePaint = r9
            android.graphics.RectF r9 = new android.graphics.RectF
            r9.<init>()
            r6.stickerRect = r9
            android.graphics.Matrix r9 = new android.graphics.Matrix
            r9.<init>()
            r6.sizeMatrix = r9
            android.graphics.Matrix r9 = new android.graphics.Matrix
            r9.<init>()
            r6.downMatrix = r9
            android.graphics.Matrix r9 = new android.graphics.Matrix
            r9.<init>()
            r6.moveMatrix = r9
            r9 = 8
            float[] r1 = new float[r9]
            r6.bitmapPoints = r1
            float[] r9 = new float[r9]
            r6.bounds = r9
            r9 = 2
            float[] r1 = new float[r9]
            r6.point = r1
            android.graphics.PointF r1 = new android.graphics.PointF
            r1.<init>()
            r6.currentCenterPoint = r1
            float[] r1 = new float[r9]
            r6.tmp = r1
            android.graphics.PointF r1 = new android.graphics.PointF
            r1.<init>()
            r6.midPoint = r1
            r1 = 0
            r6.drawCirclePoint = r1
            r6.onMoving = r1
            r2 = 0
            r6.oldDistance = r2
            r6.oldRotation = r2
            r6.currentMode = r1
            r2 = 0
            r6.lastClickTime = r2
            r2 = 200(0xc8, float:2.8E-43)
            r6.minClickDelayTime = r2
            android.graphics.Paint r2 = new android.graphics.Paint
            r2.<init>()
            r6.paintCircle = r2
            android.graphics.Paint r2 = r6.paintCircle
            r3 = 1
            r2.setAntiAlias(r3)
            android.graphics.Paint r2 = r6.paintCircle
            r2.setDither(r3)
            android.graphics.Paint r2 = r6.paintCircle
            android.content.Context r4 = r6.getContext()
            android.content.res.Resources r4 = r4.getResources()
            r5 = 2131099707(0x7f06003b, float:1.7811775E38)
            int r4 = r4.getColor(r5)
            r2.setColor(r4)
            android.graphics.Paint r2 = r6.paintCircle
            android.content.Context r4 = r6.getContext()
            int r4 = lisa.studio.photoeditor.util.SystemUtil.dpToPx(r4, r9)
            float r4 = (float) r4
            r2.setStrokeWidth(r4)
            android.graphics.Paint r2 = r6.paintCircle
            android.graphics.Paint$Style r4 = android.graphics.Paint.Style.STROKE
            r2.setStyle(r4)
            android.view.ViewConfiguration r2 = android.view.ViewConfiguration.get(r7)
            int r2 = r2.getScaledTouchSlop()
            r6.touchSlop = r2
            r2 = 0
            int[] r4 = lisa.studio.photoeditor.C1084R.styleable.StickerView     // Catch:{ all -> 0x00fa }
            android.content.res.TypedArray r7 = r7.obtainStyledAttributes(r8, r4)     // Catch:{ all -> 0x00fa }
            boolean r8 = r7.getBoolean(r0, r1)     // Catch:{ all -> 0x00f8 }
            r6.showIcons = r8     // Catch:{ all -> 0x00f8 }
            r8 = 3
            boolean r8 = r7.getBoolean(r8, r1)     // Catch:{ all -> 0x00f8 }
            r6.showBorder = r8     // Catch:{ all -> 0x00f8 }
            boolean r8 = r7.getBoolean(r9, r1)     // Catch:{ all -> 0x00f8 }
            r6.bringToFrontCurrentSticker = r8     // Catch:{ all -> 0x00f8 }
            android.graphics.Paint r8 = r6.borderPaint     // Catch:{ all -> 0x00f8 }
            r8.setAntiAlias(r3)     // Catch:{ all -> 0x00f8 }
            android.graphics.Paint r8 = r6.borderPaint     // Catch:{ all -> 0x00f8 }
            java.lang.String r9 = "#ff4d6a"
            int r9 = android.graphics.Color.parseColor(r9)     // Catch:{ all -> 0x00f8 }
            int r9 = r7.getColor(r3, r9)     // Catch:{ all -> 0x00f8 }
            r8.setColor(r9)     // Catch:{ all -> 0x00f8 }
            android.graphics.Paint r8 = r6.borderPaint     // Catch:{ all -> 0x00f8 }
            r9 = 255(0xff, float:3.57E-43)
            int r9 = r7.getInteger(r1, r9)     // Catch:{ all -> 0x00f8 }
            r8.setAlpha(r9)     // Catch:{ all -> 0x00f8 }
            r6.configDefaultIcons()     // Catch:{ all -> 0x00f8 }
            if (r7 == 0) goto L_0x00f7
            r7.recycle()
        L_0x00f7:
            return
        L_0x00f8:
            r8 = move-exception
            goto L_0x00fc
        L_0x00fa:
            r8 = move-exception
            r7 = r2
        L_0x00fc:
            if (r7 == 0) goto L_0x0101
            r7.recycle()
        L_0x0101:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: lisa.studio.photoeditor.editor.sticker.StickerView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @RequiresApi(api = 21)
    public StickerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.stickers = new ArrayList();
        this.icons = new ArrayList(4);
        this.borderPaint = new Paint();
        this.linePaint = new Paint();
        this.stickerRect = new RectF();
        this.sizeMatrix = new Matrix();
        this.downMatrix = new Matrix();
        this.moveMatrix = new Matrix();
        this.bitmapPoints = new float[8];
        this.bounds = new float[8];
        this.point = new float[2];
        this.currentCenterPoint = new PointF();
        this.tmp = new float[2];
        this.midPoint = new PointF();
        this.drawCirclePoint = false;
        this.onMoving = false;
        this.oldDistance = 0.0f;
        this.oldRotation = 0.0f;
        this.currentMode = 0;
        this.lastClickTime = 0;
        this.minClickDelayTime = 200;
    }

    public Matrix getSizeMatrix() {
        return this.sizeMatrix;
    }

    public Matrix getDownMatrix() {
        return this.downMatrix;
    }

    public Matrix getMoveMatrix() {
        return this.moveMatrix;
    }

    public List<Sticker> getStickers() {
        return this.stickers;
    }

    public void configDefaultIcons() {
        BitmapStickerIcon bitmapStickerIcon = new BitmapStickerIcon(ContextCompat.getDrawable(getContext(), C1084R.C1086drawable.sticker_ic_close_white_18dp), 0, BitmapStickerIcon.REMOVE);
        bitmapStickerIcon.setIconEvent(new DeleteIconEvent());
        BitmapStickerIcon bitmapStickerIcon2 = new BitmapStickerIcon(ContextCompat.getDrawable(getContext(), C1084R.C1086drawable.sticker_ic_scale_white_18dp), 3, BitmapStickerIcon.ZOOM);
        bitmapStickerIcon2.setIconEvent(new ZoomIconEvent());
        BitmapStickerIcon bitmapStickerIcon3 = new BitmapStickerIcon(ContextCompat.getDrawable(getContext(), C1084R.C1086drawable.sticker_ic_flip_white_18dp), 1, BitmapStickerIcon.FLIP);
        bitmapStickerIcon3.setIconEvent(new FlipHorizontallyEvent());
        BitmapStickerIcon bitmapStickerIcon4 = new BitmapStickerIcon(ContextCompat.getDrawable(getContext(), C1084R.C1086drawable.icon_edit), 2, BitmapStickerIcon.EDIT);
        bitmapStickerIcon4.setIconEvent(new FlipHorizontallyEvent());
        this.icons.clear();
        this.icons.add(bitmapStickerIcon);
        this.icons.add(bitmapStickerIcon2);
        this.icons.add(bitmapStickerIcon3);
        this.icons.add(bitmapStickerIcon4);
    }

    public void swapLayers(int i, int i2) {
        if (this.stickers.size() >= i && this.stickers.size() >= i2) {
            Collections.swap(this.stickers, i, i2);
            invalidate();
        }
    }

    public void setHandlingSticker(Sticker sticker) {
        this.lastHandlingSticker = this.handlingSticker;
        this.handlingSticker = sticker;
        invalidate();
    }

    public void showLastHandlingSticker() {
        if (this.lastHandlingSticker != null && !this.lastHandlingSticker.isShow()) {
            this.lastHandlingSticker.setShow(true);
            invalidate();
        }
    }

    public void sendToLayer(int i, int i2) {
        if (this.stickers.size() >= i && this.stickers.size() >= i2) {
            this.stickers.remove(i);
            this.stickers.add(i2, this.stickers.get(i));
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.stickerRect.left = (float) i;
            this.stickerRect.top = (float) i2;
            this.stickerRect.right = (float) i3;
            this.stickerRect.bottom = (float) i4;
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.drawCirclePoint && this.onMoving) {
            canvas.drawCircle(this.downX, this.downY, (float) this.circleRadius, this.paintCircle);
            canvas.drawLine(this.downX, this.downY, this.currentMoveingX, this.currentMoveingY, this.paintCircle);
        }
        drawStickers(canvas);
    }

    public void setCircleRadius(int i) {
        this.circleRadius = i;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drawStickers(android.graphics.Canvas r22) {
        /*
            r21 = this;
            r0 = r21
            r7 = r22
            r8 = 0
            r1 = 0
        L_0x0006:
            java.util.List<lisa.studio.photoeditor.editor.sticker.Sticker> r2 = r0.stickers
            int r2 = r2.size()
            if (r1 >= r2) goto L_0x0024
            java.util.List<lisa.studio.photoeditor.editor.sticker.Sticker> r2 = r0.stickers
            java.lang.Object r2 = r2.get(r1)
            lisa.studio.photoeditor.editor.sticker.Sticker r2 = (lisa.studio.photoeditor.editor.sticker.Sticker) r2
            if (r2 == 0) goto L_0x0021
            boolean r3 = r2.isShow()
            if (r3 == 0) goto L_0x0021
            r2.draw(r7)
        L_0x0021:
            int r1 = r1 + 1
            goto L_0x0006
        L_0x0024:
            lisa.studio.photoeditor.editor.sticker.Sticker r1 = r0.handlingSticker
            if (r1 == 0) goto L_0x0199
            boolean r1 = r0.locked
            if (r1 != 0) goto L_0x0199
            boolean r1 = r0.showBorder
            if (r1 != 0) goto L_0x0034
            boolean r1 = r0.showIcons
            if (r1 == 0) goto L_0x0199
        L_0x0034:
            lisa.studio.photoeditor.editor.sticker.Sticker r1 = r0.handlingSticker
            float[] r2 = r0.bitmapPoints
            r0.getStickerPoints(r1, r2)
            float[] r1 = r0.bitmapPoints
            r9 = r1[r8]
            float[] r1 = r0.bitmapPoints
            r10 = 1
            r11 = r1[r10]
            float[] r1 = r0.bitmapPoints
            r12 = 2
            r13 = r1[r12]
            float[] r1 = r0.bitmapPoints
            r2 = 3
            r14 = r1[r2]
            float[] r1 = r0.bitmapPoints
            r15 = 4
            r6 = r1[r15]
            float[] r1 = r0.bitmapPoints
            r2 = 5
            r5 = r1[r2]
            float[] r1 = r0.bitmapPoints
            r2 = 6
            r4 = r1[r2]
            float[] r1 = r0.bitmapPoints
            r2 = 7
            r3 = r1[r2]
            boolean r1 = r0.showBorder
            if (r1 == 0) goto L_0x009f
            android.graphics.Paint r2 = r0.borderPaint
            r1 = r22
            r16 = r2
            r2 = r9
            r17 = r3
            r3 = r11
            r18 = r4
            r4 = r13
            r19 = r5
            r5 = r14
            r20 = r6
            r6 = r16
            r1.drawLine(r2, r3, r4, r5, r6)
            android.graphics.Paint r6 = r0.borderPaint
            r4 = r20
            r5 = r19
            r1.drawLine(r2, r3, r4, r5, r6)
            android.graphics.Paint r6 = r0.borderPaint
            r2 = r13
            r3 = r14
            r4 = r18
            r5 = r17
            r1.drawLine(r2, r3, r4, r5, r6)
            android.graphics.Paint r6 = r0.borderPaint
            r2 = r18
            r3 = r17
            r4 = r20
            r5 = r19
            r1.drawLine(r2, r3, r4, r5, r6)
            goto L_0x00a7
        L_0x009f:
            r17 = r3
            r18 = r4
            r19 = r5
            r20 = r6
        L_0x00a7:
            boolean r1 = r0.showIcons
            if (r1 == 0) goto L_0x0199
            r4 = r17
            r3 = r18
            r2 = r19
            r1 = r20
            float r5 = r0.calculateRotation(r3, r4, r1, r2)
        L_0x00b7:
            java.util.List<lisa.studio.photoeditor.editor.sticker.BitmapStickerIcon> r6 = r0.icons
            int r6 = r6.size()
            if (r8 >= r6) goto L_0x0199
            java.util.List<lisa.studio.photoeditor.editor.sticker.BitmapStickerIcon> r6 = r0.icons
            java.lang.Object r6 = r6.get(r8)
            lisa.studio.photoeditor.editor.sticker.BitmapStickerIcon r6 = (lisa.studio.photoeditor.editor.sticker.BitmapStickerIcon) r6
            int r16 = r6.getPosition()
            switch(r16) {
                case 0: goto L_0x0189;
                case 1: goto L_0x015b;
                case 2: goto L_0x0138;
                case 3: goto L_0x00d1;
                default: goto L_0x00ce;
            }
        L_0x00ce:
            r12 = 4
            goto L_0x0192
        L_0x00d1:
            lisa.studio.photoeditor.editor.sticker.Sticker r15 = r0.handlingSticker
            boolean r15 = r15 instanceof lisa.studio.photoeditor.editor.sticker.TextSticker
            if (r15 == 0) goto L_0x00e3
            java.lang.String r15 = r6.getTag()
            java.lang.String r12 = "ROTATE"
            boolean r12 = r15.equals(r12)
            if (r12 != 0) goto L_0x00f5
        L_0x00e3:
            lisa.studio.photoeditor.editor.sticker.Sticker r12 = r0.handlingSticker
            boolean r12 = r12 instanceof lisa.studio.photoeditor.editor.sticker.DrawableSticker
            if (r12 == 0) goto L_0x00fe
            java.lang.String r12 = r6.getTag()
            java.lang.String r15 = "ZOOM"
            boolean r12 = r12.equals(r15)
            if (r12 == 0) goto L_0x00fe
        L_0x00f5:
            r0.configIconMatrix(r6, r3, r4, r5)
            android.graphics.Paint r12 = r0.borderPaint
            r6.draw(r7, r12)
            goto L_0x00ce
        L_0x00fe:
            lisa.studio.photoeditor.editor.sticker.Sticker r12 = r0.handlingSticker
            boolean r12 = r12 instanceof lisa.studio.photoeditor.editor.sticker.BeautySticker
            if (r12 == 0) goto L_0x00ce
            lisa.studio.photoeditor.editor.sticker.Sticker r12 = r0.handlingSticker
            lisa.studio.photoeditor.editor.sticker.BeautySticker r12 = (lisa.studio.photoeditor.editor.sticker.BeautySticker) r12
            int r15 = r12.getType()
            if (r15 != r10) goto L_0x0117
            r0.configIconMatrix(r6, r3, r4, r5)
            android.graphics.Paint r12 = r0.borderPaint
            r6.draw(r7, r12)
            goto L_0x00ce
        L_0x0117:
            int r15 = r12.getType()
            r10 = 2
            if (r15 == r10) goto L_0x012e
            int r15 = r12.getType()
            r10 = 8
            if (r15 == r10) goto L_0x012e
            int r10 = r12.getType()
            r12 = 4
            if (r10 != r12) goto L_0x0192
            goto L_0x012f
        L_0x012e:
            r12 = 4
        L_0x012f:
            r0.configIconMatrix(r6, r3, r4, r5)
            android.graphics.Paint r10 = r0.borderPaint
            r6.draw(r7, r10)
            goto L_0x0192
        L_0x0138:
            r12 = 4
            lisa.studio.photoeditor.editor.sticker.Sticker r10 = r0.handlingSticker
            boolean r10 = r10 instanceof lisa.studio.photoeditor.editor.sticker.BeautySticker
            if (r10 == 0) goto L_0x0152
            lisa.studio.photoeditor.editor.sticker.Sticker r10 = r0.handlingSticker
            lisa.studio.photoeditor.editor.sticker.BeautySticker r10 = (lisa.studio.photoeditor.editor.sticker.BeautySticker) r10
            int r10 = r10.getType()
            if (r10 != 0) goto L_0x0192
            r0.configIconMatrix(r6, r1, r2, r5)
            android.graphics.Paint r10 = r0.borderPaint
            r6.draw(r7, r10)
            goto L_0x0192
        L_0x0152:
            r0.configIconMatrix(r6, r1, r2, r5)
            android.graphics.Paint r10 = r0.borderPaint
            r6.draw(r7, r10)
            goto L_0x0192
        L_0x015b:
            r12 = 4
            lisa.studio.photoeditor.editor.sticker.Sticker r10 = r0.handlingSticker
            boolean r10 = r10 instanceof lisa.studio.photoeditor.editor.sticker.TextSticker
            if (r10 == 0) goto L_0x016e
            java.lang.String r10 = r6.getTag()
            java.lang.String r15 = "EDIT"
            boolean r10 = r10.equals(r15)
            if (r10 != 0) goto L_0x0180
        L_0x016e:
            lisa.studio.photoeditor.editor.sticker.Sticker r10 = r0.handlingSticker
            boolean r10 = r10 instanceof lisa.studio.photoeditor.editor.sticker.DrawableSticker
            if (r10 == 0) goto L_0x0192
            java.lang.String r10 = r6.getTag()
            java.lang.String r15 = "FLIP"
            boolean r10 = r10.equals(r15)
            if (r10 == 0) goto L_0x0192
        L_0x0180:
            r0.configIconMatrix(r6, r13, r14, r5)
            android.graphics.Paint r10 = r0.borderPaint
            r6.draw(r7, r10)
            goto L_0x0192
        L_0x0189:
            r12 = 4
            r0.configIconMatrix(r6, r9, r11, r5)
            android.graphics.Paint r10 = r0.borderPaint
            r6.draw(r7, r10)
        L_0x0192:
            int r8 = r8 + 1
            r10 = 1
            r12 = 2
            r15 = 4
            goto L_0x00b7
        L_0x0199:
            r21.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lisa.studio.photoeditor.editor.sticker.StickerView.drawStickers(android.graphics.Canvas):void");
    }

    /* access modifiers changed from: protected */
    public void configIconMatrix(@NonNull BitmapStickerIcon bitmapStickerIcon, float f, float f2, float f3) {
        bitmapStickerIcon.setX(f);
        bitmapStickerIcon.setY(f2);
        bitmapStickerIcon.getMatrix().reset();
        bitmapStickerIcon.getMatrix().postRotate(f3, (float) (bitmapStickerIcon.getWidth() / 2), (float) (bitmapStickerIcon.getHeight() / 2));
        bitmapStickerIcon.getMatrix().postTranslate(f - ((float) (bitmapStickerIcon.getWidth() / 2)), f2 - ((float) (bitmapStickerIcon.getHeight() / 2)));
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.locked) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() != 0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        this.downX = motionEvent.getX();
        this.downY = motionEvent.getY();
        return (findCurrentIconTouched() == null && findHandlingSticker() == null) ? false : true;
    }

    public void setDrawCirclePoint(boolean z) {
        this.drawCirclePoint = z;
        this.onMoving = false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.locked) {
            return super.onTouchEvent(motionEvent);
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0:
                if (!onTouchDown(motionEvent)) {
                    if (this.onStickerOperationListener == null) {
                        return false;
                    }
                    this.onStickerOperationListener.onStickerTouchOutside();
                    invalidate();
                    if (!this.drawCirclePoint) {
                        return false;
                    }
                }
                break;
            case 1:
                onTouchUp(motionEvent);
                break;
            case 2:
                handleCurrentMode(motionEvent);
                invalidate();
                break;
            case 5:
                this.oldDistance = calculateDistance(motionEvent);
                this.oldRotation = calculateRotation(motionEvent);
                this.midPoint = calculateMidPoint(motionEvent);
                if (this.handlingSticker != null && isInStickerArea(this.handlingSticker, motionEvent.getX(1), motionEvent.getY(1)) && findCurrentIconTouched() == null) {
                    this.currentMode = 2;
                    break;
                }
            case 6:
                if (!(this.currentMode != 2 || this.handlingSticker == null || this.onStickerOperationListener == null)) {
                    this.onStickerOperationListener.onStickerZoomFinished(this.handlingSticker);
                }
                this.currentMode = 0;
                break;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean onTouchDown(@NonNull MotionEvent motionEvent) {
        this.currentMode = 1;
        this.downX = motionEvent.getX();
        this.downY = motionEvent.getY();
        this.onMoving = true;
        this.currentMoveingX = motionEvent.getX();
        this.currentMoveingY = motionEvent.getY();
        this.midPoint = calculateMidPoint();
        this.oldDistance = calculateDistance(this.midPoint.x, this.midPoint.y, this.downX, this.downY);
        this.oldRotation = calculateRotation(this.midPoint.x, this.midPoint.y, this.downX, this.downY);
        this.currentIcon = findCurrentIconTouched();
        if (this.currentIcon != null) {
            this.currentMode = 3;
            this.currentIcon.onActionDown(this, motionEvent);
        } else {
            this.handlingSticker = findHandlingSticker();
        }
        if (this.handlingSticker != null) {
            this.downMatrix.set(this.handlingSticker.getMatrix());
            if (this.bringToFrontCurrentSticker) {
                this.stickers.remove(this.handlingSticker);
                this.stickers.add(this.handlingSticker);
            }
            if (this.onStickerOperationListener != null) {
                this.onStickerOperationListener.onStickerTouchedDown(this.handlingSticker);
            }
        }
        if (this.drawCirclePoint) {
            this.onStickerOperationListener.onTouchDownForBeauty(this.currentMoveingX, this.currentMoveingY);
            invalidate();
            return true;
        } else if (this.currentIcon == null && this.handlingSticker == null) {
            return false;
        } else {
            invalidate();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void onTouchUp(@NonNull MotionEvent motionEvent) {
        long uptimeMillis = SystemClock.uptimeMillis();
        this.onMoving = false;
        if (this.drawCirclePoint) {
            this.onStickerOperationListener.onTouchUpForBeauty(motionEvent.getX(), motionEvent.getY());
        }
        if (!(this.currentMode != 3 || this.currentIcon == null || this.handlingSticker == null)) {
            this.currentIcon.onActionUp(this, motionEvent);
        }
        if (this.currentMode == 1 && Math.abs(motionEvent.getX() - this.downX) < ((float) this.touchSlop) && Math.abs(motionEvent.getY() - this.downY) < ((float) this.touchSlop) && this.handlingSticker != null) {
            this.currentMode = 4;
            if (this.onStickerOperationListener != null) {
                this.onStickerOperationListener.onStickerClicked(this.handlingSticker);
            }
            if (uptimeMillis - this.lastClickTime < ((long) this.minClickDelayTime) && this.onStickerOperationListener != null) {
                this.onStickerOperationListener.onStickerDoubleTapped(this.handlingSticker);
            }
        }
        if (!(this.currentMode != 1 || this.handlingSticker == null || this.onStickerOperationListener == null)) {
            this.onStickerOperationListener.onStickerDragFinished(this.handlingSticker);
        }
        this.currentMode = 0;
        this.lastClickTime = uptimeMillis;
    }

    /* access modifiers changed from: protected */
    public void handleCurrentMode(@NonNull MotionEvent motionEvent) {
        switch (this.currentMode) {
            case 1:
                this.currentMoveingX = motionEvent.getX();
                this.currentMoveingY = motionEvent.getY();
                if (this.drawCirclePoint) {
                    this.onStickerOperationListener.onTouchDragForBeauty(this.currentMoveingX, this.currentMoveingY);
                }
                if (this.handlingSticker != null) {
                    this.moveMatrix.set(this.downMatrix);
                    if (this.handlingSticker instanceof BeautySticker) {
                        BeautySticker beautySticker = (BeautySticker) this.handlingSticker;
                        if (beautySticker.getType() == 10 || beautySticker.getType() == 11) {
                            this.moveMatrix.postTranslate(0.0f, motionEvent.getY() - this.downY);
                        } else {
                            this.moveMatrix.postTranslate(motionEvent.getX() - this.downX, motionEvent.getY() - this.downY);
                        }
                    } else {
                        this.moveMatrix.postTranslate(motionEvent.getX() - this.downX, motionEvent.getY() - this.downY);
                    }
                    this.handlingSticker.setMatrix(this.moveMatrix);
                    if (this.constrained) {
                        constrainSticker(this.handlingSticker);
                        return;
                    }
                    return;
                }
                return;
            case 2:
                if (this.handlingSticker != null) {
                    float calculateDistance = calculateDistance(motionEvent);
                    float calculateRotation = calculateRotation(motionEvent);
                    this.moveMatrix.set(this.downMatrix);
                    this.moveMatrix.postScale(calculateDistance / this.oldDistance, calculateDistance / this.oldDistance, this.midPoint.x, this.midPoint.y);
                    this.moveMatrix.postRotate(calculateRotation - this.oldRotation, this.midPoint.x, this.midPoint.y);
                    this.handlingSticker.setMatrix(this.moveMatrix);
                    return;
                }
                return;
            case 3:
                if (this.handlingSticker != null && this.currentIcon != null) {
                    this.currentIcon.onActionMove(this, motionEvent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void zoomAndRotateCurrentSticker(@NonNull MotionEvent motionEvent) {
        zoomAndRotateSticker(this.handlingSticker, motionEvent);
    }

    public void alignHorizontally() {
        this.moveMatrix.set(this.downMatrix);
        this.moveMatrix.postRotate(-getCurrentSticker().getCurrentAngle(), this.midPoint.x, this.midPoint.y);
        this.handlingSticker.setMatrix(this.moveMatrix);
    }

    public void zoomAndRotateSticker(@Nullable Sticker sticker, @NonNull MotionEvent motionEvent) {
        float f;
        if (sticker != null) {
            boolean z = sticker instanceof BeautySticker;
            if (z) {
                BeautySticker beautySticker = (BeautySticker) sticker;
                if (beautySticker.getType() == 10 || beautySticker.getType() == 11) {
                    return;
                }
            }
            if (sticker instanceof TextSticker) {
                f = this.oldDistance;
            } else {
                f = calculateDistance(this.midPoint.x, this.midPoint.y, motionEvent.getX(), motionEvent.getY());
            }
            float calculateRotation = calculateRotation(this.midPoint.x, this.midPoint.y, motionEvent.getX(), motionEvent.getY());
            this.moveMatrix.set(this.downMatrix);
            this.moveMatrix.postScale(f / this.oldDistance, f / this.oldDistance, this.midPoint.x, this.midPoint.y);
            if (!z) {
                this.moveMatrix.postRotate(calculateRotation - this.oldRotation, this.midPoint.x, this.midPoint.y);
            }
            this.handlingSticker.setMatrix(this.moveMatrix);
        }
    }

    /* access modifiers changed from: protected */
    public void constrainSticker(@NonNull Sticker sticker) {
        int width = getWidth();
        int height = getHeight();
        sticker.getMappedCenterPoint(this.currentCenterPoint, this.point, this.tmp);
        float f = 0.0f;
        float f2 = this.currentCenterPoint.x < 0.0f ? -this.currentCenterPoint.x : 0.0f;
        float f3 = (float) width;
        if (this.currentCenterPoint.x > f3) {
            f2 = f3 - this.currentCenterPoint.x;
        }
        if (this.currentCenterPoint.y < 0.0f) {
            f = -this.currentCenterPoint.y;
        }
        float f4 = (float) height;
        if (this.currentCenterPoint.y > f4) {
            f = f4 - this.currentCenterPoint.y;
        }
        sticker.getMatrix().postTranslate(f2, f);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public BitmapStickerIcon findCurrentIconTouched() {
        for (BitmapStickerIcon next : this.icons) {
            float x = next.getX() - this.downX;
            float y = next.getY() - this.downY;
            if (((double) ((x * x) + (y * y))) <= Math.pow((double) (next.getIconRadius() + next.getIconRadius()), 2.0d)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Sticker findHandlingSticker() {
        for (int size = this.stickers.size() - 1; size >= 0; size--) {
            if (isInStickerArea(this.stickers.get(size), this.downX, this.downY)) {
                return this.stickers.get(size);
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isInStickerArea(@NonNull Sticker sticker, float f, float f2) {
        this.tmp[0] = f;
        this.tmp[1] = f2;
        return sticker.contains(this.tmp);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public PointF calculateMidPoint(@Nullable MotionEvent motionEvent) {
        if (motionEvent == null || motionEvent.getPointerCount() < 2) {
            this.midPoint.set(0.0f, 0.0f);
            return this.midPoint;
        }
        this.midPoint.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
        return this.midPoint;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public PointF calculateMidPoint() {
        if (this.handlingSticker == null) {
            this.midPoint.set(0.0f, 0.0f);
            return this.midPoint;
        }
        this.handlingSticker.getMappedCenterPoint(this.midPoint, this.point, this.tmp);
        return this.midPoint;
    }

    /* access modifiers changed from: protected */
    public float calculateRotation(@Nullable MotionEvent motionEvent) {
        if (motionEvent == null || motionEvent.getPointerCount() < 2) {
            return 0.0f;
        }
        return calculateRotation(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1));
    }

    /* access modifiers changed from: protected */
    public float calculateRotation(float f, float f2, float f3, float f4) {
        return (float) Math.toDegrees(Math.atan2((double) (f2 - f4), (double) (f - f3)));
    }

    /* access modifiers changed from: protected */
    public float calculateDistance(@Nullable MotionEvent motionEvent) {
        if (motionEvent == null || motionEvent.getPointerCount() < 2) {
            return 0.0f;
        }
        return calculateDistance(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1));
    }

    /* access modifiers changed from: protected */
    public float calculateDistance(float f, float f2, float f3, float f4) {
        double d = (double) (f - f3);
        double d2 = (double) (f2 - f4);
        return (float) Math.sqrt((d * d) + (d2 * d2));
    }

    /* access modifiers changed from: protected */
    public void transformSticker(@Nullable Sticker sticker) {
        if (sticker == null) {
            Log.e(TAG, "transformSticker: the bitmapSticker is null or the bitmapSticker bitmap is null");
            return;
        }
        this.sizeMatrix.reset();
        float width = (float) getWidth();
        float height = (float) getHeight();
        float width2 = (float) sticker.getWidth();
        float height2 = (float) sticker.getHeight();
        this.sizeMatrix.postTranslate((width - width2) / 2.0f, (height - height2) / 2.0f);
        float f = (width < height ? width / width2 : height / height2) / 2.0f;
        this.sizeMatrix.postScale(f, f, width / 2.0f, height / 2.0f);
        sticker.getMatrix().reset();
        sticker.setMatrix(this.sizeMatrix);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        for (int i5 = 0; i5 < this.stickers.size(); i5++) {
            Sticker sticker = this.stickers.get(i5);
            if (sticker != null) {
                transformSticker(sticker);
            }
        }
    }

    public void flipCurrentSticker(int i) {
        flip(this.handlingSticker, i);
    }

    public void flip(@Nullable Sticker sticker, int i) {
        if (sticker != null) {
            sticker.getCenterPoint(this.midPoint);
            if ((i & 1) > 0) {
                sticker.getMatrix().preScale(-1.0f, 1.0f, this.midPoint.x, this.midPoint.y);
                sticker.setFlippedHorizontally(!sticker.isFlippedHorizontally());
            }
            if ((i & 2) > 0) {
                sticker.getMatrix().preScale(1.0f, -1.0f, this.midPoint.x, this.midPoint.y);
                sticker.setFlippedVertically(!sticker.isFlippedVertically());
            }
            if (this.onStickerOperationListener != null) {
                this.onStickerOperationListener.onStickerFlipped(sticker);
            }
            invalidate();
        }
    }

    public boolean replace(@Nullable Sticker sticker) {
        return replace(sticker, true);
    }

    public Sticker getLastHandlingSticker() {
        return this.lastHandlingSticker;
    }

    public boolean replace(@Nullable Sticker sticker, boolean z) {
        float f;
        if (this.handlingSticker == null) {
            this.handlingSticker = this.lastHandlingSticker;
        }
        if (this.handlingSticker == null || sticker == null) {
            return false;
        }
        float width = (float) getWidth();
        float height = (float) getHeight();
        if (z) {
            sticker.setMatrix(this.handlingSticker.getMatrix());
            sticker.setFlippedVertically(this.handlingSticker.isFlippedVertically());
            sticker.setFlippedHorizontally(this.handlingSticker.isFlippedHorizontally());
        } else {
            this.handlingSticker.getMatrix().reset();
            sticker.getMatrix().postTranslate((width - ((float) this.handlingSticker.getWidth())) / 2.0f, (height - ((float) this.handlingSticker.getHeight())) / 2.0f);
            if (width < height) {
                if (this.handlingSticker instanceof TextSticker) {
                    f = width / ((float) this.handlingSticker.getWidth());
                } else {
                    f = width / ((float) this.handlingSticker.getDrawable().getIntrinsicWidth());
                }
            } else if (this.handlingSticker instanceof TextSticker) {
                f = height / ((float) this.handlingSticker.getHeight());
            } else {
                f = height / ((float) this.handlingSticker.getDrawable().getIntrinsicHeight());
            }
            float f2 = f / 2.0f;
            sticker.getMatrix().postScale(f2, f2, width / 2.0f, height / 2.0f);
        }
        this.stickers.set(this.stickers.indexOf(this.handlingSticker), sticker);
        this.handlingSticker = sticker;
        invalidate();
        return true;
    }

    public boolean remove(@Nullable Sticker sticker) {
        if (this.stickers.contains(sticker)) {
            this.stickers.remove(sticker);
            if (this.onStickerOperationListener != null) {
                this.onStickerOperationListener.onStickerDeleted(sticker);
            }
            if (this.handlingSticker == sticker) {
                this.handlingSticker = null;
            }
            invalidate();
            return true;
        }
        Log.d(TAG, "remove: the sticker is not in this StickerView");
        return false;
    }

    public boolean removeCurrentSticker() {
        return remove(this.handlingSticker);
    }

    public void removeAllStickers() {
        this.stickers.clear();
        if (this.handlingSticker != null) {
            this.handlingSticker.release();
            this.handlingSticker = null;
        }
        invalidate();
    }

    @NonNull
    public StickerView addSticker(@NonNull Sticker sticker) {
        return addSticker(sticker, 1);
    }

    public StickerView addSticker(@NonNull final Sticker sticker, final int i) {
        if (ViewCompat.isLaidOut(this)) {
            addStickerImmediately(sticker, i);
        } else {
            post(new Runnable() {
                public void run() {
                    StickerView.this.addStickerImmediately(sticker, i);
                }
            });
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void addStickerImmediately(@NonNull Sticker sticker, int i) {
        setStickerPosition(sticker, i);
        sticker.getMatrix().postScale(1.0f, 1.0f, (float) getWidth(), (float) getHeight());
        this.handlingSticker = sticker;
        this.stickers.add(sticker);
        if (this.onStickerOperationListener != null) {
            this.onStickerOperationListener.onStickerAdded(sticker);
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void setStickerPosition(@NonNull Sticker sticker, int i) {
        float f;
        float width = ((float) getWidth()) - ((float) sticker.getWidth());
        float height = ((float) getHeight()) - ((float) sticker.getHeight());
        if (sticker instanceof BeautySticker) {
            BeautySticker beautySticker = (BeautySticker) sticker;
            f = height / 2.0f;
            if (beautySticker.getType() == 0) {
                width /= 3.0f;
            } else if (beautySticker.getType() == 1) {
                width = (width * 2.0f) / 3.0f;
            } else if (beautySticker.getType() == 2) {
                width /= 2.0f;
            } else if (beautySticker.getType() == 4) {
                width /= 2.0f;
            } else if (beautySticker.getType() == 10) {
                width /= 2.0f;
                f = (f * 2.0f) / 3.0f;
            } else if (beautySticker.getType() == 11) {
                width /= 2.0f;
                f = (f * 3.0f) / 2.0f;
            }
        } else {
            f = (i & 2) > 0 ? height / 4.0f : (i & 16) > 0 ? height * 0.75f : height / 2.0f;
            width = (i & 4) > 0 ? width / 4.0f : (i & 8) > 0 ? width * 0.75f : width / 2.0f;
        }
        sticker.getMatrix().postTranslate(width, f);
    }

    public void editTextSticker() {
        this.onStickerOperationListener.onStickerDoubleTapped(this.handlingSticker);
    }

    @NonNull
    public float[] getStickerPoints(@Nullable Sticker sticker) {
        float[] fArr = new float[8];
        getStickerPoints(sticker, fArr);
        return fArr;
    }

    public void getStickerPoints(@Nullable Sticker sticker, @NonNull float[] fArr) {
        if (sticker == null) {
            Arrays.fill(fArr, 0.0f);
            return;
        }
        sticker.getBoundPoints(this.bounds);
        sticker.getMappedPoints(fArr, this.bounds);
    }

    public void save(@NonNull File file) {
        try {
            StickerUtils.saveImageToGallery(file, createBitmap());
            StickerUtils.notifySystemGallery(getContext(), file);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
    }

    @NonNull
    public Bitmap createBitmap() throws OutOfMemoryError {
        this.handlingSticker = null;
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public int getStickerCount() {
        return this.stickers.size();
    }

    public boolean isNoneSticker() {
        return getStickerCount() == 0;
    }

    @NonNull
    public StickerView setLocked(boolean z) {
        this.locked = z;
        invalidate();
        return this;
    }

    @NonNull
    public StickerView setMinClickDelayTime(int i) {
        this.minClickDelayTime = i;
        return this;
    }

    public int getMinClickDelayTime() {
        return this.minClickDelayTime;
    }

    public boolean isConstrained() {
        return this.constrained;
    }

    @NonNull
    public StickerView setConstrained(boolean z) {
        this.constrained = z;
        postInvalidate();
        return this;
    }

    @NonNull
    public StickerView setOnStickerOperationListener(@Nullable OnStickerOperationListener onStickerOperationListener2) {
        this.onStickerOperationListener = onStickerOperationListener2;
        return this;
    }

    @Nullable
    public OnStickerOperationListener getOnStickerOperationListener() {
        return this.onStickerOperationListener;
    }

    @Nullable
    public Sticker getCurrentSticker() {
        return this.handlingSticker;
    }

    @NonNull
    public List<BitmapStickerIcon> getIcons() {
        return this.icons;
    }

    public void setIcons(@NonNull List<BitmapStickerIcon> list) {
        this.icons.clear();
        this.icons.addAll(list);
        invalidate();
    }
}
