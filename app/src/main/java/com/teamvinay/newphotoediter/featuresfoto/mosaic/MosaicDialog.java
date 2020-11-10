package com.teamvinay.newphotoediter.featuresfoto.mosaic;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.editor.filterscolor.FilterUtils;
import com.teamvinay.newphotoediter.featuresfoto.mosaic.MosaicAdapter;
import com.teamvinay.newphotoediter.util.MosaicUtil;

public class MosaicDialog extends DialogFragment implements MosaicAdapter.MosaicChangeListener {
    private static final String TAG = "SplashDialog";
    /* access modifiers changed from: private */
    public Bitmap adjustBitmap;
    private ImageView backgroundView;
    /* access modifiers changed from: private */
    public Bitmap bitmap;
    private SeekBar eraseSize;
    private RelativeLayout loadingView;
    /* access modifiers changed from: private */
    public MosaicDialogListener mosaicDialogListener;
    private SeekBar mosaicSize;
    /* access modifiers changed from: private */
    public MosaicView mosaicView;
    private RecyclerView rvMosaic;

    public interface MosaicDialogListener {
        void onSaveMosaic(Bitmap bitmap);
    }

    public void setBitmap(Bitmap bitmap2) {
        this.bitmap = bitmap2;
    }

    public static MosaicDialog show(@NonNull AppCompatActivity appCompatActivity, Bitmap bitmap2, Bitmap bitmap3, MosaicDialogListener mosaicDialogListener2) {
        MosaicDialog mosaicDialog = new MosaicDialog();
        mosaicDialog.setBitmap(bitmap2);
        mosaicDialog.setAdjustBitmap(bitmap3);
        mosaicDialog.setMosaicListener(mosaicDialogListener2);
        mosaicDialog.show(appCompatActivity.getSupportFragmentManager(), TAG);
        return mosaicDialog;
    }

    public void setMosaicListener(MosaicDialogListener mosaicDialogListener2) {
        this.mosaicDialogListener = mosaicDialogListener2;
    }

    public void setAdjustBitmap(Bitmap bitmap2) {
        this.adjustBitmap = bitmap2;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getDialog().getWindow().getAttributes().windowAnimations = C1084R.style.DialogAnimation;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        getDialog().getWindow().requestFeature(1);
        getDialog().getWindow().setFlags(1024, 1024);
        View inflate = layoutInflater.inflate(C1084R.C1089layout.mosaic_layout, viewGroup, false);
        this.mosaicView = (MosaicView) inflate.findViewById(C1084R.C1087id.mosaicView);
        this.mosaicView.setImageBitmap(this.bitmap);
        this.mosaicView.setMosaicItem(new MosaicAdapter.MosaicItem(C1084R.C1086drawable.blue_mosoic, 0, MosaicAdapter.Mode.BLUR));
        this.loadingView = (RelativeLayout) inflate.findViewById(C1084R.C1087id.loadingView);
        this.loadingView.setVisibility(8);
        this.backgroundView = (ImageView) inflate.findViewById(C1084R.C1087id.backgroundView);
        this.adjustBitmap = FilterUtils.getBlurImageFromBitmap(this.bitmap);
        this.backgroundView.setImageBitmap(this.adjustBitmap);
        this.eraseSize = (SeekBar) inflate.findViewById(C1084R.C1087id.eraseSize);
        this.eraseSize.setVisibility(8);
        this.mosaicSize = (SeekBar) inflate.findViewById(C1084R.C1087id.mosaicSize);
        this.rvMosaic = (RecyclerView) inflate.findViewById(C1084R.C1087id.rvMosaic);
        this.rvMosaic.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.rvMosaic.setHasFixedSize(true);
        this.rvMosaic.setAdapter(new MosaicAdapter(getContext(), this));
        inflate.findViewById(C1084R.C1087id.imgSave).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new SaveMosaicView().execute(new Void[0]);
            }
        });
        inflate.findViewById(C1084R.C1087id.imgClose).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MosaicDialog.this.dismiss();
            }
        });
        this.mosaicSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                MosaicDialog.this.mosaicView.setBrushBitmapSize(i + 25);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                MosaicDialog.this.mosaicView.updateBrush();
            }
        });
        inflate.findViewById(C1084R.C1087id.undo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MosaicDialog.this.mosaicView.undo();
            }
        });
        inflate.findViewById(C1084R.C1087id.redo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MosaicDialog.this.mosaicView.redo();
            }
        });
        return inflate;
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
        this.bitmap.recycle();
        this.bitmap = null;
        this.adjustBitmap.recycle();
        this.adjustBitmap = null;
    }

    public void onStop() {
        super.onStop();
    }

    public void onSelected(MosaicAdapter.MosaicItem mosaicItem) {
        if (mosaicItem.mode == MosaicAdapter.Mode.BLUR) {
            this.adjustBitmap = FilterUtils.getBlurImageFromBitmap(this.bitmap);
            this.backgroundView.setImageBitmap(this.adjustBitmap);
        } else if (mosaicItem.mode == MosaicAdapter.Mode.MOSAIC) {
            this.adjustBitmap = MosaicUtil.getMosaic(this.bitmap);
            this.backgroundView.setImageBitmap(this.adjustBitmap);
        }
        this.mosaicView.setMosaicItem(mosaicItem);
    }

    public void showLoading(boolean z) {
        if (z) {
            getActivity().getWindow().setFlags(16, 16);
            this.loadingView.setVisibility(0);
            return;
        }
        getActivity().getWindow().clearFlags(16);
        this.loadingView.setVisibility(8);
    }

    class SaveMosaicView extends AsyncTask<Void, Bitmap, Bitmap> {
        SaveMosaicView() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            MosaicDialog.this.showLoading(true);
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(Void... voidArr) {
            return MosaicDialog.this.mosaicView.getBitmap(MosaicDialog.this.bitmap, MosaicDialog.this.adjustBitmap);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            MosaicDialog.this.showLoading(false);
            MosaicDialog.this.mosaicDialogListener.onSaveMosaic(bitmap);
            MosaicDialog.this.dismiss();
        }
    }
}