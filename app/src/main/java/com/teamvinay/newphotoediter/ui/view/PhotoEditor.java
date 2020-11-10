package com.teamvinay.newphotoediter.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.annotation.UiThread;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.ui.view.SaveSettings;
import org.wysaid.view.ImageGLSurfaceView;

/* renamed from: lisa.studio.photoeditor.ui.view.PhotoEditor */
public class PhotoEditor {/*implements BrushViewChangeListener {
    private static final String TAG = "PhotoEditor";
    private List<View> addedViews;
    private BrushDrawingView brushDrawingView;
    private Context context;
    private View deleteView;
    private ImageGLSurfaceView glSurfaceView;
    private boolean isTextPinchZoomable;
    private Typeface mDefaultEmojiTypeface;
    private Typeface mDefaultTextTypeface;
    private final LayoutInflater mLayoutInflater;
    private OnPhotoEditorListener mOnPhotoEditorListener;
    *//* access modifiers changed from: private *//*
    public PhotoEditorView parentView;
    private List<View> redoViews;

    *//* renamed from: lisa.studio.photoeditor.ui.view.PhotoEditor$OnSaveListener *//*
    public interface OnSaveListener {
        void onFailure(@NonNull Exception exc);

        void onSuccess(@NonNull String str);
    }

    private PhotoEditor(Builder builder) {
        this.context = builder.context;
        this.parentView = builder.parentView;
        this.deleteView = builder.deleteView;
        this.brushDrawingView = builder.brushDrawingView;
        this.glSurfaceView = builder.glSurfaceView;
        this.isTextPinchZoomable = builder.isTextPinchZoomable;
        this.mDefaultTextTypeface = builder.textTypeface;
        this.mDefaultEmojiTypeface = builder.emojiTypeface;
        this.mLayoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        this.brushDrawingView.setBrushViewChangeListener(this);
        this.addedViews = new ArrayList();
        this.redoViews = new ArrayList();
    }

    public BrushDrawingView getBrushDrawingView() {
        return this.brushDrawingView;
    }

    private void addViewToParent(View view, ViewType viewType) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.parentView.addView(view, layoutParams);
        this.addedViews.add(view);
        if (this.mOnPhotoEditorListener != null) {
            this.mOnPhotoEditorListener.onAddViewListener(viewType, this.addedViews.size());
        }
    }

    public void setBrushDrawingMode(boolean z) {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.setBrushDrawingMode(z);
        }
    }

    public void setAdjustFilter(String str) {
        this.glSurfaceView.setFilterWithConfig(str);
    }

    public void setBrushMode(int i) {
        this.brushDrawingView.setDrawMode(i);
    }

    public void setBrushMagic(DrawBitmapModel drawBitmapModel) {
        this.brushDrawingView.setCurrentMagicBrush(drawBitmapModel);
    }

    public Boolean getBrushDrawableMode() {
        return Boolean.valueOf(this.brushDrawingView != null && this.brushDrawingView.getBrushDrawingMode());
    }

    public void setBrushSize(float f) {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.setBrushSize(f);
        }
    }

    public void setOpacity(@IntRange(from = 0, mo93to = 100) int i) {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.setOpacity((int) ((((double) i) / 100.0d) * 255.0d));
        }
    }

    public void setBrushColor(@ColorInt int i) {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.setBrushColor(i);
        }
    }

    public void setBrushEraserSize(float f) {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.setBrushEraserSize(f);
        }
    }

    *//* access modifiers changed from: package-private *//*
    public void setBrushEraserColor(@ColorInt int i) {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.setBrushEraserColor(i);
        }
    }

    public float getEraserSize() {
        if (this.brushDrawingView != null) {
            return this.brushDrawingView.getEraserSize();
        }
        return 0.0f;
    }

    public float getBrushSize() {
        if (this.brushDrawingView != null) {
            return this.brushDrawingView.getBrushSize();
        }
        return 0.0f;
    }

    public int getBrushColor() {
        if (this.brushDrawingView != null) {
            return this.brushDrawingView.getBrushColor();
        }
        return 0;
    }

    public void brushEraser() {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.brushEraser();
        }
    }

    private void viewUndo() {
        if (this.addedViews.size() > 0) {
            this.parentView.removeView(this.addedViews.remove(this.addedViews.size() - 1));
            if (this.mOnPhotoEditorListener != null) {
                this.mOnPhotoEditorListener.onRemoveViewListener(this.addedViews.size());
            }
        }
    }

    private void viewUndo(View view, ViewType viewType) {
        if (this.addedViews.size() > 0 && this.addedViews.contains(view)) {
            this.parentView.removeView(view);
            this.addedViews.remove(view);
            this.redoViews.add(view);
            if (this.mOnPhotoEditorListener != null) {
                this.mOnPhotoEditorListener.onRemoveViewListener(this.addedViews.size());
                this.mOnPhotoEditorListener.onRemoveViewListener(viewType, this.addedViews.size());
            }
        }
    }

    public boolean undo() {
        if (this.addedViews.size() > 0) {
            View view = this.addedViews.get(this.addedViews.size() - 1);
            if (!(view instanceof BrushDrawingView)) {
                this.addedViews.remove(this.addedViews.size() - 1);
                this.parentView.removeView(view);
                this.redoViews.add(view);
                if (this.mOnPhotoEditorListener != null) {
                    this.mOnPhotoEditorListener.onRemoveViewListener(this.addedViews.size());
                    Object tag = view.getTag();
                    if (tag != null && (tag instanceof ViewType)) {
                        this.mOnPhotoEditorListener.onRemoveViewListener((ViewType) tag, this.addedViews.size());
                    }
                }
            } else if (this.brushDrawingView == null || !this.brushDrawingView.undo()) {
                return false;
            } else {
                return true;
            }
        }
        if (this.addedViews.size() != 0) {
            return true;
        }
        return false;
    }

    public boolean redo() {
        if (this.redoViews.size() > 0) {
            View view = this.redoViews.get(this.redoViews.size() - 1);
            if (!(view instanceof BrushDrawingView)) {
                this.redoViews.remove(this.redoViews.size() - 1);
                this.parentView.addView(view);
                this.addedViews.add(view);
                Object tag = view.getTag();
                if (!(this.mOnPhotoEditorListener == null || tag == null || !(tag instanceof ViewType))) {
                    this.mOnPhotoEditorListener.onAddViewListener((ViewType) tag, this.addedViews.size());
                }
            } else if (this.brushDrawingView == null || !this.brushDrawingView.redo()) {
                return false;
            } else {
                return true;
            }
        }
        if (this.redoViews.size() != 0) {
            return true;
        }
        return false;
    }

    public void redoBrush() {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.redo();
        }
    }

    public void undoBrush() {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.undo();
        }
    }

    public void clearBrushAllViews() {
        if (this.brushDrawingView != null) {
            this.brushDrawingView.clearAll();
        }
    }

    public void clearAllViews() {
        for (int i = 0; i < this.addedViews.size(); i++) {
            this.parentView.removeView(this.addedViews.get(i));
        }
        if (this.addedViews.contains(this.brushDrawingView)) {
            this.parentView.addView(this.brushDrawingView);
        }
        this.addedViews.clear();
        this.redoViews.clear();
        clearBrushAllViews();
    }

    @UiThread
    public void clearHelperBox() {
        for (int i = 0; i < this.parentView.getChildCount(); i++) {
            View childAt = this.parentView.getChildAt(i);
            FrameLayout frameLayout = (FrameLayout) childAt.findViewById(C1084R.C1087id.frmBorder);
            if (frameLayout != null) {
                frameLayout.setBackgroundResource(0);
            }
            ImageView imageView = (ImageView) childAt.findViewById(C1084R.C1087id.imgPhotoEditorClose);
            if (imageView != null) {
                imageView.setVisibility(8);
            }
        }
    }

    public void setFilterEffect(String str) {
        this.parentView.setFilterEffect(str);
    }

    @RequiresPermission(allOf = {"android.permission.WRITE_EXTERNAL_STORAGE"})
    @SuppressLint({"StaticFieldLeak"})
    @Deprecated
    public void saveImage(@NonNull String str, @NonNull OnSaveListener onSaveListener) {
        saveAsFile(str, onSaveListener);
    }

    @RequiresPermission(allOf = {"android.permission.WRITE_EXTERNAL_STORAGE"})
    public void saveAsFile(@NonNull String str, @NonNull OnSaveListener onSaveListener) {
        saveAsFile(str, new SaveSettings.Builder().build(), onSaveListener);
    }

    @RequiresPermission(allOf = {"android.permission.WRITE_EXTERNAL_STORAGE"})
    @SuppressLint({"StaticFieldLeak"})
    public void saveAsFile(@NonNull final String str, @NonNull final SaveSettings saveSettings, @NonNull final OnSaveListener onSaveListener) {
        this.parentView.saveGLSurfaceViewAsBitmap(new OnSaveBitmap() {
            public void onBitmapReady(Bitmap bitmap) {
                new AsyncTask<String, String, Exception>() {
                    *//* access modifiers changed from: protected *//*
                    public void onPreExecute() {
                        super.onPreExecute();
                        PhotoEditor.this.clearHelperBox();
                        PhotoEditor.this.parentView.setDrawingCacheEnabled(false);
                    }

                    *//* access modifiers changed from: protected *//*
                    @SuppressLint({"MissingPermission"})
                    public Exception doInBackground(String... strArr) {
                        Bitmap bitmap;
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(str), false);
                            if (PhotoEditor.this.parentView != null) {
                                PhotoEditor.this.parentView.setDrawingCacheEnabled(true);
                                if (saveSettings.isTransparencyEnabled()) {
                                    bitmap = BitmapUtil.removeTransparency(PhotoEditor.this.parentView.getDrawingCache());
                                } else {
                                    bitmap = PhotoEditor.this.parentView.getDrawingCache();
                                }
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            Log.d(PhotoEditor.TAG, "Filed Saved Successfully");
                            return null;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(PhotoEditor.TAG, "Failed to save File");
                            return e;
                        }
                    }

                    *//* access modifiers changed from: protected *//*
                    public void onPostExecute(Exception exc) {
                        super.onPostExecute(exc);
                        if (exc == null) {
                            if (saveSettings.isClearViewsEnabled()) {
                                PhotoEditor.this.clearAllViews();
                            }
                            onSaveListener.onSuccess(str);
                            return;
                        }
                        onSaveListener.onFailure(exc);
                    }
                }.execute(new String[0]);
            }

            public void onFailure(Exception exc) {
                onSaveListener.onFailure(exc);
            }
        });
    }

    @SuppressLint({"StaticFieldLeak"})
    public void saveStickerAsBitmap(@NonNull OnSaveBitmap onSaveBitmap) {
        saveStickerAsBitmap(new SaveSettings.Builder().build(), onSaveBitmap);
    }

    @SuppressLint({"StaticFieldLeak"})
    public void saveStickerAsBitmap(@NonNull SaveSettings saveSettings, @NonNull OnSaveBitmap onSaveBitmap) {
        Bitmap bitmap;
        this.parentView.setDrawingCacheEnabled(false);
        this.parentView.setDrawingCacheEnabled(true);
        this.parentView.setDrawingCacheQuality(1048576);
        if (saveSettings.isTransparencyEnabled()) {
            bitmap = BitmapUtil.removeTransparency(this.parentView.getDrawingCache());
        } else {
            bitmap = this.parentView.getDrawingCache();
        }
        onSaveBitmap.onBitmapReady(bitmap);
    }

    private static String convertEmoji(String str) {
        try {
            return new String(Character.toChars(Integer.parseInt(str.substring(2), 16)));
        } catch (NumberFormatException unused) {
            return "";
        }
    }

    public void setOnPhotoEditorListener(@NonNull OnPhotoEditorListener onPhotoEditorListener) {
        this.mOnPhotoEditorListener = onPhotoEditorListener;
    }

    public boolean isCacheEmpty() {
        return this.addedViews.size() == 0 && this.redoViews.size() == 0;
    }

    public void onViewAdd(BrushDrawingView brushDrawingView2) {
        if (this.redoViews.size() > 0) {
            this.redoViews.remove(this.redoViews.size() - 1);
        }
        this.addedViews.add(brushDrawingView2);
        if (this.mOnPhotoEditorListener != null) {
            this.mOnPhotoEditorListener.onAddViewListener(ViewType.BRUSH_DRAWING, this.addedViews.size());
        }
    }

    public void onViewRemoved(BrushDrawingView brushDrawingView2) {
        if (this.addedViews.size() > 0) {
            View remove = this.addedViews.remove(this.addedViews.size() - 1);
            if (!(remove instanceof BrushDrawingView)) {
                this.parentView.removeView(remove);
            }
            this.redoViews.add(remove);
        }
        if (this.mOnPhotoEditorListener != null) {
            this.mOnPhotoEditorListener.onRemoveViewListener(this.addedViews.size());
            this.mOnPhotoEditorListener.onRemoveViewListener(ViewType.BRUSH_DRAWING, this.addedViews.size());
        }
    }

    public void onStartDrawing() {
        if (this.mOnPhotoEditorListener != null) {
            this.mOnPhotoEditorListener.onStartViewChangeListener(ViewType.BRUSH_DRAWING);
        }
    }

    public void onStopDrawing() {
        if (this.mOnPhotoEditorListener != null) {
            this.mOnPhotoEditorListener.onStopViewChangeListener(ViewType.BRUSH_DRAWING);
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.view.PhotoEditor$Builder *//*
    public static class Builder {
        *//* access modifiers changed from: private *//*
        public BrushDrawingView brushDrawingView;
        *//* access modifiers changed from: private *//*
        public Context context;
        *//* access modifiers changed from: private *//*
        public View deleteView;
        *//* access modifiers changed from: private *//*
        public Typeface emojiTypeface;
        *//* access modifiers changed from: private *//*
        public ImageGLSurfaceView glSurfaceView;
        *//* access modifiers changed from: private *//*
        public boolean isTextPinchZoomable = true;
        *//* access modifiers changed from: private *//*
        public PhotoEditorView parentView;
        *//* access modifiers changed from: private *//*
        public Typeface textTypeface;

        public Builder(Context context2, PhotoEditorView photoEditorView) {
            this.context = context2;
            this.parentView = photoEditorView;
            this.brushDrawingView = photoEditorView.getBrushDrawingView();
            this.glSurfaceView = photoEditorView.getGLSurfaceView();
        }

        *//* access modifiers changed from: package-private *//*
        public Builder setDeleteView(View view) {
            this.deleteView = view;
            return this;
        }

        public Builder setDefaultTextTypeface(Typeface typeface) {
            this.textTypeface = typeface;
            return this;
        }

        public Builder setDefaultEmojiTypeface(Typeface typeface) {
            this.emojiTypeface = typeface;
            return this;
        }

        public Builder setPinchTextScalable(boolean z) {
            this.isTextPinchZoomable = z;
            return this;
        }

        public PhotoEditor build() {
            return new PhotoEditor(this);
        }
    }

    public static ArrayList<String> getEmojis(Context context2) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String convertEmoji : context2.getResources().getStringArray(C1084R.array.photo_editor_emoji)) {
            arrayList.add(convertEmoji(convertEmoji));
        }
        return arrayList;
    }*/
}