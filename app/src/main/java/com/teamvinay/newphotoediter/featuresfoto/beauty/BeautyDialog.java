package com.teamvinay.newphotoediter.featuresfoto.beauty;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.editor.filterscolor.DegreeSeekBar;
import com.teamvinay.newphotoediter.editor.sticker.BeautySticker;
import com.teamvinay.newphotoediter.editor.sticker.BitmapStickerIcon;
import com.teamvinay.newphotoediter.editor.sticker.Sticker;
import com.teamvinay.newphotoediter.editor.sticker.StickerView;
import com.teamvinay.newphotoediter.editor.sticker.event.ZoomIconEvent;
import com.teamvinay.newphotoediter.featuresfoto.beauty.BeautyDialog;
import com.teamvinay.newphotoediter.ui.view.OnSaveBitmap;
import com.teamvinay.newphotoediter.ui.view.PhotoEditorView;
import com.teamvinay.newphotoediter.util.SharePreferenceUtil;
import com.teamvinay.newphotoediter.util.SystemUtil;
import org.wysaid.nativePort.CGEDeformFilterWrapper;
import org.wysaid.nativePort.CGEImageHandler;
import org.wysaid.texUtils.TextureRenderer;
import org.wysaid.view.ImageGLSurfaceView;

public class BeautyDialog {/*extends DialogFragment {
    static final int BOOB = 0;
    static final int FACE = 3;
    static final int HIP_1 = 2;
    static final int MAGIC = 4;
    static final int WAIST = 1;
    private Bitmap bitmap;
    private ImageView boobs;
    private ImageView compare;
    *//* access modifiers changed from: private *//*
    public int currentType = 7;
    private ImageView face;
    *//* access modifiers changed from: private *//*
    public ImageGLSurfaceView glSurfaceView;
    *//* access modifiers changed from: private *//*
    public SeekBar intensitySmooth;
    private DegreeSeekBar intensityTwoDirection;
    *//* access modifiers changed from: private *//*
    public RelativeLayout loadingView;
    private List<Retouch> lstRetouch;
    *//* access modifiers changed from: private *//*
    public CGEDeformFilterWrapper mDeformWrapper;
    private float mTouchRadiusForWaist;
    private LinearLayout magicLayout;
    *//* access modifiers changed from: private *//*
    public OnBeautySave onBeautySave;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case C1084R.C1087id.resetWaist:
                    BeautyDialog.this.glSurfaceView.flush(true, new Runnable() {
                        public void run() {
                            if (BeautyDialog.this.mDeformWrapper != null) {
                                BeautyDialog.this.mDeformWrapper.restore();
                                BeautyDialog.this.glSurfaceView.requestRender();
                            }
                        }
                    });
                    return;
                case C1084R.C1087id.wrapBoobs:
                    BeautyDialog.this.showAdjustBoobs();
                    return;
                case C1084R.C1087id.wrapFace:
                    BeautyDialog.this.showAdjustFace();
                    return;
                case C1084R.C1087id.wrapHip:
                    BeautyDialog.this.showAdjustHipOne();
                    return;
                case C1084R.C1087id.wrapWaist:
                    BeautyDialog.this.showWaist();
                    return;
                default:
                    return;
            }
        }
    };
    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (BeautyDialog.this.intensitySmooth.getProgress() == 0) {
                BeautyDialog.this.glSurfaceView.setFilterWithConfig("");
                return;
            }
            ImageGLSurfaceView access$100 = BeautyDialog.this.glSurfaceView;
            access$100.setFilterWithConfig(MessageFormat.format("@beautify face 1 {0} 640", new Object[]{BeautyDialog.this.intensitySmooth.getProgress() + ""}));
        }
    };
    *//* access modifiers changed from: private *//*
    public PhotoEditorView photoEditorView;
    private RelativeLayout resetWaist;
    private ImageView seat;
    *//* access modifiers changed from: private *//*
    public float startX;
    *//* access modifiers changed from: private *//*
    public float startY;
    private TextView tvBoobs;
    private TextView tvFace;
    private TextView tvSeat;
    private TextView tvWaise;
    private ViewGroup viewGroup;
    private ImageView waise;
    private RelativeLayout wrapBoobs;
    private RelativeLayout wrapFace;
    private RelativeLayout wrapHip;
    private RelativeLayout wrapWaist;

    public interface OnBeautySave {
        void onBeautySave(Bitmap bitmap);
    }

    public void setBitmap(Bitmap bitmap2) {
        this.bitmap = bitmap2;
    }

    public void showWaist() {
        if (SharePreferenceUtil.isFirstAdjustWaise(getContext())) {
            View inflate = LayoutInflater.from(getContext()).inflate(C1084R.C1089layout.waise_instruction, this.viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            inflate.findViewById(C1084R.C1087id.btnDone).setOnClickListener(new View.OnClickListener(create) {
                private final *//* synthetic *//* AlertDialog f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    BeautyDialog.lambda$showWaist$0(BeautyDialog.this, this.f$1, view);
                }
            });
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            create.show();
        }
        saveCurrentState();
        selectFunction(1);
        this.magicLayout.setVisibility(8);
        this.photoEditorView.setHandlingSticker((Sticker) null);
        this.photoEditorView.setDrawCirclePoint(true);
        this.resetWaist.setVisibility(0);
        this.intensityTwoDirection.setVisibility(8);
        this.currentType = 3;
        this.intensityTwoDirection.setCurrentDegrees(0);
        this.mTouchRadiusForWaist = (float) SystemUtil.dpToPx(getContext(), 20);
        this.photoEditorView.setCircleRadius((int) this.mTouchRadiusForWaist);
        this.photoEditorView.getStickers().clear();
    }

    public static *//* synthetic *//* void lambda$showWaist$0(BeautyDialog beautyDialog, AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
        SharePreferenceUtil.setFirstAdjustWaist(beautyDialog.getContext(), false);
    }

    private void saveCurrentState() {
        new SaveCurrentState().execute(new Void[0]);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getDialog().getWindow().getAttributes().windowAnimations = C1084R.style.DialogAnimation;
    }

    public void showAdjustBoobs() {
        if (SharePreferenceUtil.isFirstAdjustBoob(getContext())) {
            View inflate = LayoutInflater.from(getContext()).inflate(C1084R.C1089layout.boobs_instruction, this.viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(false);
            builder.setView(inflate);
            final AlertDialog create = builder.create();
            inflate.findViewById(C1084R.C1087id.btnDone).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    create.dismiss();
                    SharePreferenceUtil.setFirstAdjustBoob(BeautyDialog.this.getContext(), false);
                }
            });
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            create.show();
        }
        saveCurrentState();
        this.intensityTwoDirection.setVisibility(0);
        this.intensityTwoDirection.setDegreeRange(-30, 30);
        this.resetWaist.setVisibility(8);
        this.photoEditorView.setDrawCirclePoint(false);
        selectFunction(0);
        this.magicLayout.setVisibility(8);
        this.currentType = 7;
        this.intensityTwoDirection.setCurrentDegrees(0);
        this.photoEditorView.getStickers().clear();
        this.photoEditorView.addSticker(new BeautySticker(getContext(), 0, getResources().getDrawable(C1084R.C1086drawable.circle)));
        this.photoEditorView.addSticker(new BeautySticker(getContext(), 1, getResources().getDrawable(C1084R.C1086drawable.circle)));
    }

    public void showAdjustFace() {
        if (SharePreferenceUtil.isFirstAdjusFace(getContext())) {
            View inflate = LayoutInflater.from(getContext()).inflate(C1084R.C1089layout.chin_instruction, this.viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            inflate.findViewById(C1084R.C1087id.btnDone).setOnClickListener(new View.OnClickListener(create) {
                private final *//* synthetic *//* AlertDialog f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    BeautyDialog.lambda$showAdjustFace$1(BeautyDialog.this, this.f$1, view);
                }
            });
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            create.show();
        }
        saveCurrentState();
        this.intensityTwoDirection.setVisibility(0);
        this.intensityTwoDirection.setDegreeRange(-15, 15);
        this.resetWaist.setVisibility(8);
        this.photoEditorView.setDrawCirclePoint(false);
        selectFunction(3);
        this.currentType = 4;
        this.magicLayout.setVisibility(8);
        this.intensityTwoDirection.setCurrentDegrees(0);
        this.photoEditorView.getStickers().clear();
        this.photoEditorView.addSticker(new BeautySticker(getContext(), 4, getResources().getDrawable(C1084R.C1086drawable.chin)));
    }

    public static *//* synthetic *//* void lambda$showAdjustFace$1(BeautyDialog beautyDialog, AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
        SharePreferenceUtil.setFirstAdjustFace(beautyDialog.getContext(), false);
    }

    public void showAdjustHipOne() {
        if (SharePreferenceUtil.isFirstAdjusHip(getContext())) {
            View inflate = LayoutInflater.from(getContext()).inflate(C1084R.C1089layout.hip_instruction, this.viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            inflate.findViewById(C1084R.C1087id.btnDone).setOnClickListener(new View.OnClickListener(create) {
                private final *//* synthetic *//* AlertDialog f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    BeautyDialog.lambda$showAdjustHipOne$2(BeautyDialog.this, this.f$1, view);
                }
            });
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            create.show();
        }
        saveCurrentState();
        this.intensityTwoDirection.setVisibility(0);
        this.intensityTwoDirection.setDegreeRange(-30, 30);
        this.resetWaist.setVisibility(8);
        this.photoEditorView.setDrawCirclePoint(false);
        selectFunction(2);
        this.intensityTwoDirection.setCurrentDegrees(0);
        this.currentType = 9;
        this.photoEditorView.getStickers().clear();
        this.photoEditorView.addSticker(new BeautySticker(getContext(), 2, getResources().getDrawable(C1084R.C1086drawable.hip_1)));
    }

    public static *//* synthetic *//* void lambda$showAdjustHipOne$2(BeautyDialog beautyDialog, AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
        SharePreferenceUtil.setFirstAdjustHip(beautyDialog.getContext(), false);
    }

    public void hideAllFunction() {
        this.intensityTwoDirection.setVisibility(8);
        this.resetWaist.setVisibility(8);
        this.magicLayout.setVisibility(8);
        this.loadingView.setVisibility(8);
    }

    public void setOnBeautySave(OnBeautySave onBeautySave2) {
        this.onBeautySave = onBeautySave2;
    }

    public static BeautyDialog show(@NonNull AppCompatActivity appCompatActivity, Bitmap bitmap2, OnBeautySave onBeautySave2) {
        BeautyDialog beautyDialog = new BeautyDialog();
        beautyDialog.setBitmap(bitmap2);
        beautyDialog.setOnBeautySave(onBeautySave2);
        beautyDialog.show(appCompatActivity.getSupportFragmentManager(), "BeautyDialog");
        return beautyDialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup2, @Nullable Bundle bundle) {
        getDialog().getWindow().requestFeature(1);
        getDialog().getWindow().setFlags(1024, 1024);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().getWindow().setDimAmount(0.0f);
        View inflate = layoutInflater.inflate(C1084R.C1089layout.beauty_layout, viewGroup2, false);
        this.intensityTwoDirection = (DegreeSeekBar) inflate.findViewById(C1084R.C1087id.intensityTwoDirection);
        this.intensityTwoDirection.setDegreeRange(-20, 20);
        this.photoEditorView = (PhotoEditorView) inflate.findViewById(C1084R.C1087id.photoEditorView);
        this.glSurfaceView = this.photoEditorView.getGLSurfaceView();
        this.loadingView = (RelativeLayout) inflate.findViewById(C1084R.C1087id.loadingView);
        this.boobs = (ImageView) inflate.findViewById(C1084R.C1087id.boobs);
        this.wrapBoobs = (RelativeLayout) inflate.findViewById(C1084R.C1087id.wrapBoobs);
        this.wrapBoobs.setOnClickListener(this.onClickListener);
        this.tvBoobs = (TextView) inflate.findViewById(C1084R.C1087id.tvBoobs);
        this.waise = (ImageView) inflate.findViewById(C1084R.C1087id.waist);
        this.wrapWaist = (RelativeLayout) inflate.findViewById(C1084R.C1087id.wrapWaist);
        this.wrapWaist.setOnClickListener(this.onClickListener);
        this.tvWaise = (TextView) inflate.findViewById(C1084R.C1087id.tvWaist);
        this.resetWaist = (RelativeLayout) inflate.findViewById(C1084R.C1087id.resetWaist);
        this.resetWaist.setOnClickListener(this.onClickListener);
        this.seat = (ImageView) inflate.findViewById(C1084R.C1087id.seat);
        this.wrapHip = (RelativeLayout) inflate.findViewById(C1084R.C1087id.wrapHip);
        this.wrapHip.setOnClickListener(this.onClickListener);
        this.tvSeat = (TextView) inflate.findViewById(C1084R.C1087id.tvSeat);
        this.face = (ImageView) inflate.findViewById(C1084R.C1087id.face);
        this.wrapFace = (RelativeLayout) inflate.findViewById(C1084R.C1087id.wrapFace);
        this.wrapFace.setOnClickListener(this.onClickListener);
        this.tvFace = (TextView) inflate.findViewById(C1084R.C1087id.tvFace);
        this.magicLayout = (LinearLayout) inflate.findViewById(C1084R.C1087id.magicLayout);
        this.viewGroup = (ViewGroup) inflate.findViewById(16908290);
        this.intensitySmooth = (SeekBar) inflate.findViewById(C1084R.C1087id.intensitySmooth);
        this.intensitySmooth.setOnSeekBarChangeListener(this.onSeekBarChangeListener);
        this.lstRetouch = new ArrayList();
        this.lstRetouch.add(new Retouch(this.boobs, this.tvBoobs, C1084R.C1086drawable.boobs, C1084R.C1086drawable.boobs_selected));
        this.lstRetouch.add(new Retouch(this.waise, this.tvWaise, C1084R.C1086drawable.waist, C1084R.C1086drawable.waist_selected));
        this.lstRetouch.add(new Retouch(this.seat, this.tvSeat, C1084R.C1086drawable.seat, C1084R.C1086drawable.seat_selected));
        this.lstRetouch.add(new Retouch(this.face, this.tvFace, C1084R.C1086drawable.beauty_face, C1084R.C1086drawable.beauty_face_selected));
        this.intensityTwoDirection.setScrollingListener(new DegreeSeekBar.ScrollingListener() {
            public void onScrollStart() {
                Iterator<Sticker> it = BeautyDialog.this.photoEditorView.getStickers().iterator();
                while (it.hasNext()) {
                    ((BeautySticker) it.next()).updateRadius();
                }
            }

            public void onScroll(final int i) {
                TextureRenderer.Viewport renderViewport = BeautyDialog.this.glSurfaceView.getRenderViewport();
                final float f = (float) renderViewport.width;
                final float f2 = (float) renderViewport.height;
                if (BeautyDialog.this.currentType == 7) {
                    BeautyDialog.this.glSurfaceView.lazyFlush(true, new Runnable(i, f, f2) {
                        private final *//* synthetic *//* int f$1;
                        private final *//* synthetic *//* float f$2;
                        private final *//* synthetic *//* float f$3;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                            this.f$3 = r4;
                        }

                        public final void run() {
                            BeautyDialog.C11142.lambda$onScroll$0(BeautyDialog.C11142.this, this.f$1, this.f$2, this.f$3);
                        }
                    });
                } else if (BeautyDialog.this.currentType == 9) {
                    BeautyDialog.this.glSurfaceView.lazyFlush(true, new Runnable() {
                        public void run() {
                            if (BeautyDialog.this.mDeformWrapper != null) {
                                BeautyDialog.this.mDeformWrapper.restore();
                                Iterator<Sticker> it = BeautyDialog.this.photoEditorView.getStickers().iterator();
                                while (it.hasNext()) {
                                    BeautySticker beautySticker = (BeautySticker) it.next();
                                    PointF mappedCenterPoint2 = beautySticker.getMappedCenterPoint2();
                                    RectF mappedBound = beautySticker.getMappedBound();
                                    for (int i = 0; i < Math.abs(i); i++) {
                                        if (i > 0) {
                                            float f = (float) 20;
                                            float f2 = f;
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(mappedBound.right - f, mappedCenterPoint2.y, mappedBound.right + f, mappedCenterPoint2.y, f, f2, (float) beautySticker.getRadius(), 0.01f);
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(mappedBound.left + f2, mappedCenterPoint2.y, mappedBound.left - f2, mappedCenterPoint2.y, f, f2, (float) beautySticker.getRadius(), 0.01f);
                                        } else {
                                            float f3 = (float) 20;
                                            float f4 = f3;
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(mappedBound.right + f3, mappedCenterPoint2.y, mappedBound.right - f3, mappedCenterPoint2.y, f, f2, (float) beautySticker.getRadius(), 0.01f);
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(mappedBound.left - f4, mappedCenterPoint2.y, mappedBound.left + f4, mappedCenterPoint2.y, f, f2, (float) beautySticker.getRadius(), 0.01f);
                                        }
                                    }
                                }
                            }
                        }
                    });
                } else if (BeautyDialog.this.currentType == 4) {
                    BeautyDialog.this.glSurfaceView.lazyFlush(true, new Runnable() {
                        public void run() {
                            int i;
                            Iterator<Sticker> it;
                            int i2;
                            if (BeautyDialog.this.mDeformWrapper != null) {
                                BeautyDialog.this.mDeformWrapper.restore();
                                Iterator<Sticker> it2 = BeautyDialog.this.photoEditorView.getStickers().iterator();
                                while (it2.hasNext()) {
                                    BeautySticker beautySticker = (BeautySticker) it2.next();
                                    PointF mappedCenterPoint2 = beautySticker.getMappedCenterPoint2();
                                    RectF mappedBound = beautySticker.getMappedBound();
                                    int radius = beautySticker.getRadius() / 2;
                                    float f = (mappedBound.left + mappedCenterPoint2.x) / 2.0f;
                                    float f2 = mappedBound.left + ((f - mappedBound.left) / 2.0f);
                                    float f3 = (mappedBound.bottom + mappedBound.top) / 2.0f;
                                    float f4 = mappedBound.top + ((f3 - mappedBound.top) / 2.0f);
                                    float f5 = (mappedBound.right + mappedCenterPoint2.x) / 2.0f;
                                    float f6 = mappedBound.right - ((mappedBound.right - f5) / 2.0f);
                                    float f7 = (mappedBound.bottom + mappedBound.top) / 2.0f;
                                    float f8 = mappedBound.top + ((f7 - mappedBound.top) / 2.0f);
                                    int i3 = 0;
                                    while (i3 < Math.abs(i)) {
                                        if (i > 0) {
                                            float f9 = (float) radius;
                                            it = it2;
                                            float f10 = f9;
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(mappedBound.right, mappedBound.top, mappedBound.right - f9, mappedBound.top, f, f2, (float) beautySticker.getRadius(), 0.002f);
                                            float f11 = f6;
                                            float f12 = f8;
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(f11, f12, f6 - f10, f8, f, f2, (float) beautySticker.getRadius(), 0.005f);
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(f5, f7, f5 - f10, f7, f, f2, (float) beautySticker.getRadius(), 0.007f);
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(mappedBound.left, mappedBound.top, mappedBound.left + f10, mappedBound.top, f, f2, (float) beautySticker.getRadius(), 0.002f);
                                            float f13 = f2;
                                            float f14 = f4;
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(f13, f14, f2 + f10, f4, f, f2, (float) beautySticker.getRadius(), 0.005f);
                                            i2 = i3;
                                            float f15 = f;
                                            float f16 = f3;
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(f15, f16, f + f10, f3, f, f2, (float) beautySticker.getRadius(), 0.007f);
                                            i = radius;
                                        } else {
                                            it = it2;
                                            i2 = i3;
                                            float f17 = (float) radius;
                                            i = radius;
                                            float f18 = f17;
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(mappedBound.right, mappedBound.top, mappedBound.right + f17, mappedBound.top, f, f2, (float) beautySticker.getRadius(), 0.002f);
                                            float f19 = f6;
                                            float f20 = f8;
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(f19, f20, f6 + f18, f8, f, f2, (float) beautySticker.getRadius(), 0.005f);
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(f5, f7, f5 + f18, f7, f, f2, (float) beautySticker.getRadius(), 0.007f);
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(mappedBound.left + f18, mappedBound.top, mappedBound.left, mappedBound.top, f, f2, (float) beautySticker.getRadius(), 0.002f);
                                            float f21 = f2;
                                            float f22 = f4;
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(f21, f22, f2 - f18, f4, f, f2, (float) beautySticker.getRadius(), 0.005f);
                                            BeautyDialog.this.mDeformWrapper.forwardDeform(f, f3, f - f18, f3, f, f2, (float) beautySticker.getRadius(), 0.007f);
                                        }
                                        i3 = i2 + 1;
                                        it2 = it;
                                        radius = i;
                                    }
                                }
                            }
                        }
                    });
                }
            }

            public static *//* synthetic *//* void lambda$onScroll$0(C11142 r12, int i, float f, float f2) {
                if (BeautyDialog.this.mDeformWrapper != null) {
                    BeautyDialog.this.mDeformWrapper.restore();
                    for (Sticker next : BeautyDialog.this.photoEditorView.getStickers()) {
                        PointF mappedCenterPoint2 = ((BeautySticker) next).getMappedCenterPoint2();
                        Log.i("CURRENT", i + "");
                        for (int i2 = 0; i2 < Math.abs(i); i2++) {
                            if (i > 0) {
                                BeautyDialog.this.mDeformWrapper.bloatDeform(mappedCenterPoint2.x, mappedCenterPoint2.y, f, f2, (float) (next.getWidth() / 2), 0.03f);
                            } else if (i < 0) {
                                BeautyDialog.this.mDeformWrapper.wrinkleDeform(mappedCenterPoint2.x, mappedCenterPoint2.y, f, f2, (float) (next.getWidth() / 2), 0.03f);
                            }
                        }
                    }
                }
            }

            public void onScrollEnd() {
                BeautyDialog.this.glSurfaceView.requestRender();
            }
        });
        BitmapStickerIcon bitmapStickerIcon = new BitmapStickerIcon(ContextCompat.getDrawable(getContext(), C1084R.C1086drawable.sticker_ic_scale_white_18dp), 3, BitmapStickerIcon.ZOOM);
        bitmapStickerIcon.setIconEvent(new ZoomIconEvent());
        BitmapStickerIcon bitmapStickerIcon2 = new BitmapStickerIcon(ContextCompat.getDrawable(getContext(), C1084R.C1086drawable.sticker_ic_scale_white_2_18dp), 2, BitmapStickerIcon.ZOOM);
        bitmapStickerIcon2.setIconEvent(new ZoomIconEvent());
        this.photoEditorView.setIcons(Arrays.asList(new BitmapStickerIcon[]{bitmapStickerIcon, bitmapStickerIcon2}));
        this.photoEditorView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.photoEditorView.setLocked(false);
        this.photoEditorView.setConstrained(true);
        this.photoEditorView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            public void onStickerAdded(@NonNull Sticker sticker) {
            }

            public void onStickerClicked(@NonNull Sticker sticker) {
            }

            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
            }

            public void onStickerDragFinished(@NonNull Sticker sticker) {
            }

            public void onStickerFlipped(@NonNull Sticker sticker) {
            }

            public void onStickerTouchOutside() {
            }

            public void onStickerTouchedDown(@NonNull Sticker sticker) {
            }

            public void onStickerZoomFinished(@NonNull Sticker sticker) {
            }

            public void onTouchUpForBeauty(float f, float f2) {
            }

            public void onStickerDeleted(@NonNull Sticker sticker) {
                BeautyDialog.this.loadingView.setVisibility(8);
            }

            public void onTouchDownForBeauty(float f, float f2) {
                float unused = BeautyDialog.this.startX = f;
                float unused2 = BeautyDialog.this.startY = f2;
            }

            public void onTouchDragForBeauty(float f, float f2) {
                TextureRenderer.Viewport renderViewport = BeautyDialog.this.glSurfaceView.getRenderViewport();
                float f3 = (float) renderViewport.height;
                BeautyDialog.this.glSurfaceView.lazyFlush(true, new Runnable(BeautyDialog.this.startX, BeautyDialog.this.startY, f, f2, (float) renderViewport.width, f3) {
                    private final *//* synthetic *//* float f$1;
                    private final *//* synthetic *//* float f$2;
                    private final *//* synthetic *//* float f$3;
                    private final *//* synthetic *//* float f$4;
                    private final *//* synthetic *//* float f$5;
                    private final *//* synthetic *//* float f$6;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                        this.f$5 = r6;
                        this.f$6 = r7;
                    }

                    public final void run() {
                        BeautyDialog.C11173.lambda$onTouchDragForBeauty$0(BeautyDialog.C11173.this, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
                    }
                });
                float unused = BeautyDialog.this.startX = f;
                float unused2 = BeautyDialog.this.startY = f2;
            }

            public static *//* synthetic *//* void lambda$onTouchDragForBeauty$0(C11173 r11, float f, float f2, float f3, float f4, float f5, float f6) {
                C11173 r0 = r11;
                if (BeautyDialog.this.mDeformWrapper != null) {
                    BeautyDialog.this.mDeformWrapper.forwardDeform(f, f2, f3, f4, f5, f6, 200.0f, 0.02f);
                }
            }
        });
        this.compare = (ImageView) inflate.findViewById(C1084R.C1087id.compare);
        this.compare.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return BeautyDialog.lambda$onCreateView$3(BeautyDialog.this, view, motionEvent);
            }
        });
        inflate.findViewById(C1084R.C1087id.imgSave).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                new BeautyDialog.SaveCurrentState(true).execute(new Void[0]);
            }
        });
        inflate.findViewById(C1084R.C1087id.imgClose).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BeautyDialog.this.dismiss();
            }
        });
        this.photoEditorView.setImageSource(this.bitmap, new ImageGLSurfaceView.OnSurfaceCreatedCallback() {
            public final void surfaceCreated() {
                BeautyDialog.lambda$onCreateView$7(BeautyDialog.this);
            }
        });
        if (Build.VERSION.SDK_INT > 23) {
            this.photoEditorView.post(new Runnable() {
                public final void run() {
                    BeautyDialog.lambda$onCreateView$8(BeautyDialog.this);
                }
            });
        }
        hideAllFunction();
        return inflate;
    }

    public static *//* synthetic *//* boolean lambda$onCreateView$3(BeautyDialog beautyDialog, View view, MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                beautyDialog.photoEditorView.getGLSurfaceView().setAlpha(0.0f);
                return true;
            case 1:
                beautyDialog.photoEditorView.getGLSurfaceView().setAlpha(1.0f);
                return false;
            default:
                return true;
        }
    }

    public static *//* synthetic *//* void lambda$onCreateView$7(BeautyDialog beautyDialog) {
        beautyDialog.glSurfaceView.setImageBitmap(beautyDialog.bitmap);
        beautyDialog.glSurfaceView.queueEvent(new Runnable() {
            public final void run() {
                BeautyDialog.lambda$null$6(BeautyDialog.this);
            }
        });
    }

    public static *//* synthetic *//* void lambda$null$6(BeautyDialog beautyDialog) {
        float width = (float) beautyDialog.bitmap.getWidth();
        float height = (float) beautyDialog.bitmap.getHeight();
        float min = Math.min(((float) beautyDialog.glSurfaceView.getRenderViewport().width) / width, ((float) beautyDialog.glSurfaceView.getRenderViewport().height) / height);
        if (min < 1.0f) {
            width *= min;
            height *= min;
        }
        beautyDialog.mDeformWrapper = CGEDeformFilterWrapper.create((int) width, (int) height, 10.0f);
        beautyDialog.mDeformWrapper.setUndoSteps(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        if (beautyDialog.mDeformWrapper != null) {
            CGEImageHandler imageHandler = beautyDialog.glSurfaceView.getImageHandler();
            imageHandler.setFilterWithAddres(beautyDialog.mDeformWrapper.getNativeAddress());
            imageHandler.processFilters();
        }
    }

    public static *//* synthetic *//* void lambda$onCreateView$8(BeautyDialog beautyDialog) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(beautyDialog.glSurfaceView.getRenderViewport().width, beautyDialog.glSurfaceView.getRenderViewport().height);
        layoutParams.addRule(13);
        beautyDialog.photoEditorView.setLayoutParams(layoutParams);
    }

    private void selectFunction(int i) {
        for (int i2 = 0; i2 < this.lstRetouch.size(); i2++) {
            if (i2 == i) {
                Retouch retouch = this.lstRetouch.get(i2);
                retouch.imageView.setImageResource(retouch.drawableSelected);
                retouch.textView.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
            } else {
                Retouch retouch2 = this.lstRetouch.get(i2);
                retouch2.imageView.setImageResource(retouch2.drawable);
                retouch2.textView.setTextColor(getResources().getColor(C1084R.C1085color.white));
            }
        }
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        }
    }

    class Retouch {
        int drawable;
        int drawableSelected;
        ImageView imageView;
        TextView textView;

        Retouch(ImageView imageView2, TextView textView2, int i, int i2) {
            this.drawable = i;
            this.drawableSelected = i2;
            this.imageView = imageView2;
            this.textView = textView2;
        }
    }

    class SaveCurrentState extends AsyncTask<Void, Void, Bitmap> {
        boolean isCloseDialog;

        SaveCurrentState() {
        }

        SaveCurrentState(boolean z) {
            this.isCloseDialog = z;
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            BeautyDialog.this.getDialog().getWindow().setFlags(16, 16);
            BeautyDialog.this.loadingView.setVisibility(0);
        }

        *//* access modifiers changed from: protected *//*
        public Bitmap doInBackground(Void... voidArr) {
            final Bitmap[] bitmapArr = {null};
            BeautyDialog.this.photoEditorView.saveGLSurfaceViewAsBitmap(new OnSaveBitmap() {
                public void onFailure(Exception exc) {
                }

                public void onBitmapReady(Bitmap bitmap) {
                    bitmapArr[0] = bitmap;
                }
            });
            while (bitmapArr[0] == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return bitmapArr[0];
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(Bitmap bitmap) {
            BeautyDialog.this.photoEditorView.setImageSource(bitmap);
            BeautyDialog.this.loadingView.setVisibility(8);
            try {
                BeautyDialog.this.getDialog().getWindow().clearFlags(16);
            } catch (Exception unused) {
            }
            BeautyDialog.this.glSurfaceView.flush(true, new Runnable() {
                public final void run() {
                    BeautyDialog.SaveCurrentState.lambda$onPostExecute$0(BeautyDialog.SaveCurrentState.this);
                }
            });
            if (this.isCloseDialog) {
                BeautyDialog.this.onBeautySave.onBeautySave(bitmap);
                BeautyDialog.this.dismiss();
            }
        }

        public static *//* synthetic *//* void lambda$onPostExecute$0(SaveCurrentState saveCurrentState) {
            if (BeautyDialog.this.mDeformWrapper != null) {
                BeautyDialog.this.mDeformWrapper.restore();
                BeautyDialog.this.glSurfaceView.requestRender();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mDeformWrapper != null) {
            this.mDeformWrapper.release(true);
            this.mDeformWrapper = null;
        }
        this.glSurfaceView.release();
        this.glSurfaceView.onPause();
    }*/
}