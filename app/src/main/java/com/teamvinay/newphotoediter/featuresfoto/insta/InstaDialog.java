package com.teamvinay.newphotoediter.featuresfoto.insta;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.steelkiwi.cropiwa.AspectRatio;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.editor.filterscolor.FilterUtils;
import com.teamvinay.newphotoediter.featuresfoto.crop.adapter.AspectRatioPreviewAdapter;
import com.teamvinay.newphotoediter.featuresfoto.draw.BrushColorListener;
import com.teamvinay.newphotoediter.featuresfoto.draw.ColorAdapter;
import com.teamvinay.newphotoediter.featuresfoto.insta.InstaAdapter;
import com.teamvinay.newphotoediter.util.SystemUtil;

public class InstaDialog extends DialogFragment implements AspectRatioPreviewAdapter.OnNewSelectedListener, InstaAdapter.BackgroundInstaListener, BrushColorListener {
    private static final String TAG = "InstaDialog";
    /* access modifiers changed from: private */
    public TextView background;
    private Bitmap bitmap;
    private Bitmap blurBitmap;
    private ImageView blurView;
    /* access modifiers changed from: private */
    public TextView border;
    private AspectRatio currentAspect;
    /* access modifiers changed from: private */
    public RecyclerView fixedRatioList;
    /* access modifiers changed from: private */
    public InstaSaveListener instaSaveListener;
    /* access modifiers changed from: private */
    public LinearLayout instagramPadding;
    /* access modifiers changed from: private */
    public ImageView instagramPhoto;
    private RelativeLayout loadingView;
    /* access modifiers changed from: private */
    public TextView ratio;
    private ConstraintLayout ratioLayout;
    /* access modifiers changed from: private */
    public RecyclerView rvBackground;
    /* access modifiers changed from: private */
    public FrameLayout wrapInstagram;

    public interface InstaSaveListener {
        void instaSavedBitmap(Bitmap bitmap);
    }

    public void setBitmap(Bitmap bitmap2) {
        this.bitmap = bitmap2;
    }

    public static InstaDialog show(@NonNull AppCompatActivity appCompatActivity, InstaSaveListener instaSaveListener2, Bitmap bitmap2, Bitmap bitmap3) {
        InstaDialog instaDialog = new InstaDialog();
        instaDialog.setBitmap(bitmap2);
        instaDialog.setBlurBitmap(bitmap3);
        instaDialog.setInstaSaveListener(instaSaveListener2);
        instaDialog.show(appCompatActivity.getSupportFragmentManager(), TAG);
        return instaDialog;
    }

    public void setBlurBitmap(Bitmap bitmap2) {
        this.blurBitmap = bitmap2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void setInstaSaveListener(InstaSaveListener instaSaveListener2) {
        this.instaSaveListener = instaSaveListener2;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        getDialog().getWindow().requestFeature(1);
        getDialog().getWindow().setFlags(1024, 1024);
        View inflate = layoutInflater.inflate(C1084R.C1089layout.insta_layout, viewGroup, false);
        AspectRatioPreviewAdapter aspectRatioPreviewAdapter = new AspectRatioPreviewAdapter(true);
        aspectRatioPreviewAdapter.setListener(this);
        this.loadingView = (RelativeLayout) inflate.findViewById(C1084R.C1087id.loadingView);
        this.loadingView.setVisibility(8);
        this.fixedRatioList = (RecyclerView) inflate.findViewById(C1084R.C1087id.fixed_ratio_list);
        this.fixedRatioList.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.fixedRatioList.setAdapter(aspectRatioPreviewAdapter);
        this.currentAspect = new AspectRatio(1, 1);
        this.rvBackground = (RecyclerView) inflate.findViewById(C1084R.C1087id.rv_background);
        this.rvBackground.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.rvBackground.setAdapter(new InstaAdapter(getContext(), this));
        this.rvBackground.setVisibility(8);
        this.ratio = (TextView) inflate.findViewById(C1084R.C1087id.ratio);
        this.ratio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InstaDialog.this.fixedRatioList.setVisibility(0);
                InstaDialog.this.rvBackground.setVisibility(8);
                InstaDialog.this.instagramPadding.setVisibility(8);
                InstaDialog.this.ratio.setTextColor(InstaDialog.this.getResources().getColor(C1084R.C1085color.colorAccent));
                InstaDialog.this.ratio.setBackground(InstaDialog.this.getResources().getDrawable(C1084R.C1086drawable.border_bottom));
                InstaDialog.this.background.setTextColor(InstaDialog.this.getResources().getColor(C1084R.C1085color.text_color_edit));
                InstaDialog.this.border.setTextColor(InstaDialog.this.getResources().getColor(C1084R.C1085color.text_color_edit));
                InstaDialog.this.background.setBackgroundResource(0);
                InstaDialog.this.border.setBackgroundResource(0);
            }
        });
        this.background = (TextView) inflate.findViewById(C1084R.C1087id.background);
        this.background.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InstaDialog.this.fixedRatioList.setVisibility(8);
                InstaDialog.this.rvBackground.setVisibility(0);
                InstaDialog.this.instagramPadding.setVisibility(8);
                InstaDialog.this.background.setTextColor(InstaDialog.this.getResources().getColor(C1084R.C1085color.colorAccent));
                InstaDialog.this.background.setBackground(InstaDialog.this.getResources().getDrawable(C1084R.C1086drawable.border_bottom));
                InstaDialog.this.ratio.setTextColor(InstaDialog.this.getResources().getColor(C1084R.C1085color.text_color_edit));
                InstaDialog.this.border.setTextColor(InstaDialog.this.getResources().getColor(C1084R.C1085color.text_color_edit));
                InstaDialog.this.ratio.setBackgroundResource(0);
                InstaDialog.this.border.setBackgroundResource(0);
            }
        });
        this.instagramPadding = (LinearLayout) inflate.findViewById(C1084R.C1087id.instagramPadding);
        this.instagramPadding.setVisibility(8);
        this.border = (TextView) inflate.findViewById(C1084R.C1087id.border);
        this.border.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InstaDialog.this.instagramPadding.setVisibility(0);
                InstaDialog.this.fixedRatioList.setVisibility(8);
                InstaDialog.this.rvBackground.setVisibility(8);
                InstaDialog.this.border.setTextColor(InstaDialog.this.getResources().getColor(C1084R.C1085color.colorAccent));
                InstaDialog.this.border.setBackground(InstaDialog.this.getResources().getDrawable(C1084R.C1086drawable.border_bottom));
                InstaDialog.this.background.setTextColor(InstaDialog.this.getResources().getColor(C1084R.C1085color.text_color_edit));
                InstaDialog.this.ratio.setTextColor(InstaDialog.this.getResources().getColor(C1084R.C1085color.text_color_edit));
                InstaDialog.this.background.setBackgroundResource(0);
                InstaDialog.this.ratio.setBackgroundResource(0);
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C1084R.C1087id.rv_colors);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ColorAdapter(getContext(), this, true));
        ((SeekBar) inflate.findViewById(C1084R.C1087id.paddingInsta)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int dpToPx = SystemUtil.dpToPx(InstaDialog.this.getContext(), i);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) InstaDialog.this.instagramPhoto.getLayoutParams();
                layoutParams.setMargins(dpToPx, dpToPx, dpToPx, dpToPx);
                InstaDialog.this.instagramPhoto.setLayoutParams(layoutParams);
            }
        });
        this.instagramPhoto = (ImageView) inflate.findViewById(C1084R.C1087id.instagramPhoto);
        this.instagramPhoto.setImageBitmap(this.bitmap);
        this.instagramPhoto.setAdjustViewBounds(true);
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.ratioLayout = (ConstraintLayout) inflate.findViewById(C1084R.C1087id.ratioLayout);
        this.blurView = (ImageView) inflate.findViewById(C1084R.C1087id.blurView);
        this.blurView.setImageBitmap(this.blurBitmap);
        this.wrapInstagram = (FrameLayout) inflate.findViewById(C1084R.C1087id.wrapInstagram);
        this.wrapInstagram.setLayoutParams(new ConstraintLayout.LayoutParams(point.x, point.x));
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.ratioLayout);
        ConstraintSet constraintSet2 = constraintSet;
        constraintSet2.connect(this.wrapInstagram.getId(), 3, this.ratioLayout.getId(), 3, 0);
        constraintSet2.connect(this.wrapInstagram.getId(), 1, this.ratioLayout.getId(), 1, 0);
        constraintSet2.connect(this.wrapInstagram.getId(), 4, this.ratioLayout.getId(), 4, 0);
        constraintSet2.connect(this.wrapInstagram.getId(), 2, this.ratioLayout.getId(), 2, 0);
        constraintSet.applyTo(this.ratioLayout);
        inflate.findViewById(C1084R.C1087id.imgClose).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InstaDialog.this.dismiss();
            }
        });
        inflate.findViewById(C1084R.C1087id.imgSave).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InstaDialog.this.wrapInstagram.setDrawingCacheEnabled(false);
                InstaDialog.this.wrapInstagram.setDrawingCacheEnabled(true);
                new SaveInstaView().execute(new Bitmap[]{InstaDialog.this.wrapInstagram.getDrawingCache(false)});
            }
        });
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getDialog().getWindow().getAttributes().windowAnimations = C1084R.style.DialogAnimation;
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        }
    }

    public void onStop() {
        super.onStop();
    }

    private int[] calculateWidthAndHeight(AspectRatio aspectRatio, Point point) {
        int height = this.ratioLayout.getHeight();
        if (aspectRatio.getHeight() > aspectRatio.getWidth()) {
            int ratio2 = (int) (aspectRatio.getRatio() * ((float) height));
            if (ratio2 < point.x) {
                return new int[]{ratio2, height};
            }
            return new int[]{point.x, (int) (((float) point.x) / aspectRatio.getRatio())};
        }
        int ratio3 = (int) (((float) point.x) / aspectRatio.getRatio());
        if (ratio3 > height) {
            return new int[]{(int) (((float) height) * aspectRatio.getRatio()), height};
        }
        return new int[]{point.x, ratio3};
    }

    private int[] calculateWidthAndHeightReal(AspectRatio aspectRatio, Point point) {
        int height = this.bitmap.getHeight();
        if (aspectRatio.getHeight() > aspectRatio.getWidth()) {
            int ratio2 = (int) (aspectRatio.getRatio() * ((float) height));
            if (ratio2 < point.x) {
                return new int[]{ratio2, height};
            }
            return new int[]{point.x, (int) (((float) point.x) / aspectRatio.getRatio())};
        }
        int ratio3 = (int) (((float) point.x) / aspectRatio.getRatio());
        if (ratio3 > height) {
            return new int[]{(int) (((float) height) * aspectRatio.getRatio()), height};
        }
        return new int[]{point.x, ratio3};
    }

    public void onNewAspectRatioSelected(AspectRatio aspectRatio) {
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.currentAspect = aspectRatio;
        int[] calculateWidthAndHeight = calculateWidthAndHeight(aspectRatio, point);
        this.wrapInstagram.setLayoutParams(new ConstraintLayout.LayoutParams(calculateWidthAndHeight[0], calculateWidthAndHeight[1]));
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.ratioLayout);
        ConstraintSet constraintSet2 = constraintSet;
        constraintSet2.connect(this.wrapInstagram.getId(), 3, this.ratioLayout.getId(), 3, 0);
        constraintSet2.connect(this.wrapInstagram.getId(), 1, this.ratioLayout.getId(), 1, 0);
        constraintSet2.connect(this.wrapInstagram.getId(), 4, this.ratioLayout.getId(), 4, 0);
        constraintSet2.connect(this.wrapInstagram.getId(), 2, this.ratioLayout.getId(), 2, 0);
        constraintSet.applyTo(this.ratioLayout);
    }

    public void showLoading(boolean z) {
        if (z) {
            try {
                getActivity().getWindow().setFlags(16, 16);
                this.loadingView.setVisibility(0);
            } catch (Exception unused) {
            }
        } else {
            getActivity().getWindow().clearFlags(16);
            this.loadingView.setVisibility(8);
        }
    }

    class SaveInstaView extends AsyncTask<Bitmap, Bitmap, Bitmap> {
        SaveInstaView() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            InstaDialog.this.showLoading(true);
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(Bitmap... bitmapArr) {
            Bitmap cloneBitmap = FilterUtils.cloneBitmap(bitmapArr[0]);
            bitmapArr[0].recycle();
            bitmapArr[0] = null;
            return cloneBitmap;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            InstaDialog.this.showLoading(false);
            InstaDialog.this.instaSaveListener.instaSavedBitmap(bitmap);
            InstaDialog.this.wrapInstagram.destroyDrawingCache();
            InstaDialog.this.dismiss();
        }
    }

    public void onBackgroundSelected(InstaAdapter.SquareView squareView) {
        if (squareView.isColor) {
            this.wrapInstagram.setBackgroundColor(squareView.drawableId);
        } else if (squareView.text.equals("Blur")) {
            this.blurView.setVisibility(0);
        } else {
            this.wrapInstagram.setBackgroundResource(squareView.drawableId);
            this.blurView.setVisibility(8);
        }
        this.wrapInstagram.invalidate();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.blurBitmap.recycle();
        this.blurBitmap = null;
        this.bitmap = null;
    }

    public void onColorChanged(String str) {
        this.instagramPhoto.setBackgroundColor(Color.parseColor(str));
        if (!str.equals("#00000000")) {
            int dpToPx = SystemUtil.dpToPx(getContext(), 3);
            this.instagramPhoto.setPadding(dpToPx, dpToPx, dpToPx, dpToPx);
            return;
        }
        this.instagramPhoto.setPadding(0, 0, 0, 0);
    }
}