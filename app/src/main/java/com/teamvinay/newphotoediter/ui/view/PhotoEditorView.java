package com.teamvinay.newphotoediter.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.teamvinay.newphotoediter.editor.sticker.Sticker;

import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.editor.sticker.StickerView;
import org.wysaid.view.ImageGLSurfaceView;

/* renamed from: lisa.studio.photoeditor.ui.view.PhotoEditorView */
public class PhotoEditorView extends StickerView {
    private static final String TAG = "PhotoEditorView";
    private static final int brushSrcId = 2;
    private static final int glFilterId = 3;
    private static final int imgSrcId = 1;
    private Bitmap currentBitmap;
    private BrushDrawingView mBrushDrawingView;
    /* access modifiers changed from: private */
    public ImageGLSurfaceView mGLSurfaceView;
    private FilterImageView mImgSource;

    public PhotoEditorView(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public PhotoEditorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public PhotoEditorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    @RequiresApi(api = 21)
    public PhotoEditorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet);
    }

    @SuppressLint({"Recycle"})
    private void init(@Nullable AttributeSet attributeSet) {
        this.mImgSource = new FilterImageView(getContext());
        this.mImgSource.setId(1);
        this.mImgSource.setAdjustViewBounds(true);
        this.mImgSource.setBackgroundColor(getResources().getColor(C1084R.C1085color.collage_bg));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13, -1);
        this.mBrushDrawingView = new BrushDrawingView(getContext());
        this.mBrushDrawingView.setVisibility(8);
        this.mBrushDrawingView.setId(2);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(13, -1);
        layoutParams2.addRule(6, 1);
        layoutParams2.addRule(8, 1);
        this.mGLSurfaceView = new ImageGLSurfaceView(getContext(), attributeSet);
        this.mGLSurfaceView.setId(3);
        this.mGLSurfaceView.setVisibility(0);
        this.mGLSurfaceView.setAlpha(1.0f);
        this.mGLSurfaceView.setDisplayMode(ImageGLSurfaceView.DisplayMode.DISPLAY_ASPECT_FIT);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(13, -1);
        layoutParams3.addRule(6, 1);
        layoutParams3.addRule(8, 1);
        addView(this.mImgSource, layoutParams);
        addView(this.mGLSurfaceView, layoutParams3);
        addView(this.mBrushDrawingView, layoutParams2);
        setDrawingCacheEnabled(true);
    }

    public ImageView getImageSource() {
        return this.mImgSource;
    }

    public void setImageSource(final Bitmap bitmap) {
        this.mImgSource.setImageBitmap(bitmap);
        if (this.mGLSurfaceView.getImageHandler() != null) {
            this.mGLSurfaceView.setImageBitmap(bitmap);
        } else {
            this.mGLSurfaceView.setSurfaceCreatedCallback(new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
                public void surfaceCreated() {
                    PhotoEditorView.this.mGLSurfaceView.setImageBitmap(bitmap);
                }
            });
        }
        this.currentBitmap = bitmap;
    }

    public void setImageSource(Bitmap bitmap, ImageGLSurfaceView.OnSurfaceCreatedCallback onSurfaceCreatedCallback) {
        this.mImgSource.setImageBitmap(bitmap);
        if (this.mGLSurfaceView.getImageHandler() != null) {
            this.mGLSurfaceView.setImageBitmap(bitmap);
        } else {
            this.mGLSurfaceView.setSurfaceCreatedCallback(onSurfaceCreatedCallback);
        }
        this.currentBitmap = bitmap;
    }

    public Bitmap getCurrentBitmap() {
        return this.currentBitmap;
    }

    public void setCurrentBitmap(Bitmap bitmap) {
        this.currentBitmap = bitmap;
    }

    /* access modifiers changed from: package-private */
    public BrushDrawingView getBrushDrawingView() {
        return this.mBrushDrawingView;
    }

    public ImageGLSurfaceView getGLSurfaceView() {
        return this.mGLSurfaceView;
    }

    public void saveGLSurfaceViewAsBitmap(@NonNull final OnSaveBitmap onSaveBitmap) {
        if (this.mGLSurfaceView.getVisibility() == 0) {
            this.mGLSurfaceView.getResultBitmap(new ImageGLSurfaceView.QueryResultBitmapCallback() {
                public void get(Bitmap bitmap) {
                    onSaveBitmap.onBitmapReady(bitmap);
                }
            });
        }
    }

    public void setFilterEffect(String str) {
        this.mGLSurfaceView.setFilterWithConfig(str);
    }

    public void setFilterIntensity(float f) {
        this.mGLSurfaceView.setFilterIntensity(f);
    }

    public void setHandlingSticker(Sticker sticker) {
    }
}