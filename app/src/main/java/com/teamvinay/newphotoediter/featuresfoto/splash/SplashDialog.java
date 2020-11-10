package com.teamvinay.newphotoediter.featuresfoto.splash;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/*import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import lisa.studio.photoeditor.C1084R;
import lisa.studio.photoeditor.editor.filterscolor.FilterUtils;
import lisa.studio.photoeditor.editor.sticker.SplashSticker;
import lisa.studio.photoeditor.featuresfoto.splash.SplashAdapter;
import lisa.studio.photoeditor.util.AssetUtils;
import lisa.studio.photoeditor.util.SharePreferenceUtil;*/

public class SplashDialog {/*} extends DialogFragment implements SplashAdapter.SplashChangeListener {
    private static final String TAG = "SplashDialog";
    private ImageView backgroundView;
    *//* access modifiers changed from: private *//*
    public Bitmap bitmap;
    private Bitmap blackAndWhiteBitmap;
    private Bitmap blurBitmap;
    private ElegantNumberButton blurNumber;
    private SplashSticker blurSticker;
    private SeekBar brushIntensity;
    *//* access modifiers changed from: private *//*
    public TextView draw;
    *//* access modifiers changed from: private *//*
    public LinearLayout drawLayout;
    private FrameLayout frameLayout;
    *//* access modifiers changed from: private *//*
    public boolean isSplashView;
    private RelativeLayout loadingView;
    private ImageView redo;
    *//* access modifiers changed from: private *//*
    public RecyclerView rvSplashView;
    *//* access modifiers changed from: private *//*
    public TextView shape;
    *//* access modifiers changed from: private *//*
    public SplashDialogListener splashDialogListener;
    private SplashSticker splashSticker;
    *//* access modifiers changed from: private *//*
    public SplashView splashView;
    private ImageView undo;
    private ViewGroup viewGroup;

    public interface SplashDialogListener {
        void onSaveSplash(Bitmap bitmap);
    }

    public void setSplashView(boolean z) {
        this.isSplashView = z;
    }

    public void setBitmap(Bitmap bitmap2) {
        this.bitmap = bitmap2;
    }

    public static SplashDialog show(@NonNull AppCompatActivity appCompatActivity, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, SplashDialogListener splashDialogListener2, boolean z) {
        SplashDialog splashDialog = new SplashDialog();
        splashDialog.setBlurBitmap(bitmap3);
        splashDialog.setBitmap(bitmap2);
        splashDialog.setBlackAndWhiteBitmap(bitmap4);
        splashDialog.setSplashDialogListener(splashDialogListener2);
        splashDialog.setSplashView(z);
        splashDialog.show(appCompatActivity.getSupportFragmentManager(), TAG);
        return splashDialog;
    }

    public void setBlackAndWhiteBitmap(Bitmap bitmap2) {
        this.blackAndWhiteBitmap = bitmap2;
    }

    public void setBlurBitmap(Bitmap bitmap2) {
        this.blurBitmap = bitmap2;
    }

    public void setSplashDialogListener(SplashDialogListener splashDialogListener2) {
        this.splashDialogListener = splashDialogListener2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup2, @Nullable Bundle bundle) {
        getDialog().getWindow().requestFeature(1);
        getDialog().getWindow().setFlags(1024, 1024);
        View inflate = layoutInflater.inflate(C1084R.C1089layout.splash_layout, viewGroup2, false);
        this.backgroundView = (ImageView) inflate.findViewById(C1084R.C1087id.backgroundView);
        this.splashView = (SplashView) inflate.findViewById(C1084R.C1087id.splashView);
        this.drawLayout = (LinearLayout) inflate.findViewById(C1084R.C1087id.drawLayout);
        this.drawLayout.setVisibility(8);
        this.frameLayout = (FrameLayout) inflate.findViewById(C1084R.C1087id.frameLayout);
        this.undo = (ImageView) inflate.findViewById(C1084R.C1087id.undo);
        this.undo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SplashDialog.this.splashView.undo();
            }
        });
        this.redo = (ImageView) inflate.findViewById(C1084R.C1087id.redo);
        this.redo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SplashDialog.this.splashView.redo();
            }
        });
        this.brushIntensity = (SeekBar) inflate.findViewById(C1084R.C1087id.brushIntensity);
        this.brushIntensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                SplashDialog.this.splashView.setBrushBitmapSize(i + 25);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                SplashDialog.this.splashView.updateBrush();
            }
        });
        this.loadingView = (RelativeLayout) inflate.findViewById(C1084R.C1087id.loadingView);
        this.loadingView.setVisibility(8);
        this.blurNumber = (ElegantNumberButton) inflate.findViewById(C1084R.C1087id.blurNumber);
        this.backgroundView.setImageBitmap(this.bitmap);
        this.shape = (TextView) inflate.findViewById(C1084R.C1087id.shape);
        this.draw = (TextView) inflate.findViewById(C1084R.C1087id.draw);
        if (this.isSplashView) {
            this.splashView.setImageBitmap(this.blackAndWhiteBitmap);
            this.blurNumber.setVisibility(8);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.shape.getLayoutParams();
            layoutParams.horizontalBias = 0.3f;
            this.shape.setLayoutParams(layoutParams);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.draw.getLayoutParams();
            layoutParams2.horizontalBias = 0.7f;
            this.draw.setLayoutParams(layoutParams2);
        } else {
            this.splashView.setImageBitmap(this.blurBitmap);
            this.blurNumber.setRange(0, 10);
        }
        this.blurNumber.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            public void onValueChange(ElegantNumberButton elegantNumberButton, int i, int i2) {
                new LoadBlurBitmap((float) i2).execute(new Void[0]);
            }
        });
        this.rvSplashView = (RecyclerView) inflate.findViewById(C1084R.C1087id.rvSplashView);
        this.rvSplashView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.rvSplashView.setHasFixedSize(true);
        this.rvSplashView.setAdapter(new SplashAdapter(getContext(), this, this.isSplashView));
        if (this.isSplashView) {
            this.splashSticker = new SplashSticker(AssetUtils.loadBitmapFromAssets(getContext(), "splash/icons/mask1.png"), AssetUtils.loadBitmapFromAssets(getContext(), "splash/icons/frame1.png"));
            this.splashView.addSticker(this.splashSticker);
        } else {
            this.blurSticker = new SplashSticker(AssetUtils.loadBitmapFromAssets(getContext(), "blur/icons/blur_1_mask.png"), AssetUtils.loadBitmapFromAssets(getContext(), "blur/icons/blur_1_shadow.png"));
            this.splashView.addSticker(this.blurSticker);
        }
        this.splashView.refreshDrawableState();
        this.splashView.setLayerType(2, (Paint) null);
        this.shape.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SplashDialog.this.draw.setBackgroundResource(0);
                SplashDialog.this.draw.setTextColor(SplashDialog.this.getResources().getColor(C1084R.C1085color.text_color_edit));
                SplashDialog.this.shape.setBackgroundResource(C1084R.C1086drawable.border_bottom);
                SplashDialog.this.shape.setTextColor(SplashDialog.this.getResources().getColor(C1084R.C1085color.colorAccent));
                SplashDialog.this.splashView.setCurrentSplashMode(0);
                SplashDialog.this.drawLayout.setVisibility(8);
                SplashDialog.this.rvSplashView.setVisibility(0);
                SplashDialog.this.splashView.refreshDrawableState();
                SplashDialog.this.splashView.invalidate();
                if (SplashDialog.this.isSplashView) {
                    if (SharePreferenceUtil.isFirstShapeSplash(SplashDialog.this.getContext())) {
                        SplashDialog.this.showShapeTutorial();
                    }
                } else if (SharePreferenceUtil.isFirstShapeBlur(SplashDialog.this.getContext())) {
                    SplashDialog.this.showShapeTutorial();
                }
            }
        });
        this.draw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SplashDialog.this.splashView.refreshDrawableState();
                SplashDialog.this.splashView.setLayerType(1, (Paint) null);
                SplashDialog.this.shape.setBackgroundResource(0);
                SplashDialog.this.shape.setTextColor(SplashDialog.this.getResources().getColor(C1084R.C1085color.text_color_edit));
                SplashDialog.this.draw.setBackgroundResource(C1084R.C1086drawable.border_bottom);
                SplashDialog.this.draw.setTextColor(SplashDialog.this.getResources().getColor(C1084R.C1085color.colorAccent));
                SplashDialog.this.splashView.setCurrentSplashMode(1);
                SplashDialog.this.drawLayout.setVisibility(0);
                SplashDialog.this.rvSplashView.setVisibility(8);
                SplashDialog.this.splashView.invalidate();
                if (SplashDialog.this.isSplashView) {
                    if (SharePreferenceUtil.isFirstDrawSplash(SplashDialog.this.getContext())) {
                        SplashDialog.this.showDrawTutorial();
                    }
                } else if (SharePreferenceUtil.isFirstDrawBlur(SplashDialog.this.getContext())) {
                    SplashDialog.this.showDrawTutorial();
                }
            }
        });
        inflate.findViewById(C1084R.C1087id.imgSave).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SplashDialog.this.splashDialogListener.onSaveSplash(SplashDialog.this.splashView.getBitmap(SplashDialog.this.bitmap));
                SplashDialog.this.dismiss();
            }
        });
        inflate.findViewById(C1084R.C1087id.imgClose).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SplashDialog.this.dismiss();
            }
        });
        this.viewGroup = (ViewGroup) inflate.findViewById(16908290);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (SplashDialog.this.isSplashView) {
                    if (SharePreferenceUtil.isFirstShapeSplash(SplashDialog.this.getContext())) {
                        SplashDialog.this.showShapeTutorial();
                    }
                } else if (SharePreferenceUtil.isFirstShapeBlur(SplashDialog.this.getContext())) {
                    SplashDialog.this.showShapeTutorial();
                }
            }
        }, 1000);
        return inflate;
    }

    *//* access modifiers changed from: private *//*
    public void showDrawTutorial() {
        View inflate = LayoutInflater.from(getContext()).inflate(C1084R.C1089layout.draw_splash, this.viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        inflate.findViewById(C1084R.C1087id.btnDone).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SplashDialog.this.isSplashView) {
                    SharePreferenceUtil.setFirstDrawSplash(SplashDialog.this.getContext(), false);
                } else {
                    SharePreferenceUtil.setFirstDrawBlur(SplashDialog.this.getContext(), false);
                }
                create.dismiss();
            }
        });
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
    }

    *//* access modifiers changed from: private *//*
    public void showShapeTutorial() {
        View inflate = LayoutInflater.from(getContext()).inflate(C1084R.C1089layout.pinch_to_zoom_splash, this.viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        inflate.findViewById(C1084R.C1087id.btnDone).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (SplashDialog.this.isSplashView) {
                        SharePreferenceUtil.setFirstShapeSplash(SplashDialog.this.getContext(), false);
                    } else {
                        SharePreferenceUtil.setFirstShapeBlur(SplashDialog.this.getContext(), false);
                    }
                } catch (Exception unused) {
                }
                if (create != null && create.isShowing()) {
                    try {
                        create.dismiss();
                    } catch (Exception unused2) {
                    }
                }
            }
        });
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getDialog().getWindow().getAttributes().windowAnimations = C1084R.style.DialogAnimation;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.splashView.getSticker().release();
        if (this.blurBitmap != null) {
            this.blurBitmap.recycle();
        }
        this.blurBitmap = null;
        if (this.blackAndWhiteBitmap != null) {
            this.blackAndWhiteBitmap.recycle();
        }
        this.blackAndWhiteBitmap = null;
        this.bitmap = null;
    }

    public void onSelected(SplashSticker splashSticker2) {
        this.splashView.addSticker(splashSticker2);
    }

    class LoadBlurBitmap extends AsyncTask<Void, Bitmap, Bitmap> {
        private float intensity;

        public LoadBlurBitmap(float f) {
            this.intensity = f;
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            SplashDialog.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public Bitmap doInBackground(Void... voidArr) {
            return FilterUtils.getBlurImageFromBitmap(SplashDialog.this.bitmap, this.intensity);
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(Bitmap bitmap) {
            SplashDialog.this.showLoading(false);
            SplashDialog.this.splashView.setImageBitmap(bitmap);
        }
    }

    public void showLoading(boolean z) {
        if (z) {
            getActivity().getWindow().setFlags(16, 16);
            this.loadingView.setVisibility(0);
            return;
        }
        getActivity().getWindow().clearFlags(16);
        this.loadingView.setVisibility(8);
    }*/
}
