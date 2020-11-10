package com.teamvinay.newphotoediter.ui.main.activity;



import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
//import com.ads.control.AdmobHelp;
import com.hold1.keyboardheightprovider.KeyboardHeightProvider;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.teamvinay.newphotoediter.BaseActivity;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.adapter.EditingToolsAdapter;
import com.teamvinay.newphotoediter.adapter.ToolType;
import com.teamvinay.newphotoediter.editor.filterscolor.DegreeSeekBar;
import com.teamvinay.newphotoediter.editor.filterscolor.FilterListener;
import com.teamvinay.newphotoediter.editor.filterscolor.FilterUtils;
import com.teamvinay.newphotoediter.editor.filterscolor.FilterViewAdapter;
import com.teamvinay.newphotoediter.editor.sticker.BitmapStickerIcon;
import com.teamvinay.newphotoediter.editor.sticker.DrawableSticker;
import com.teamvinay.newphotoediter.editor.sticker.Sticker;
import com.teamvinay.newphotoediter.editor.sticker.StickerView;
import com.teamvinay.newphotoediter.editor.sticker.TextSticker;
import com.teamvinay.newphotoediter.editor.sticker.event.AlignHorizontallyEvent;
import com.teamvinay.newphotoediter.editor.sticker.event.DeleteIconEvent;
import com.teamvinay.newphotoediter.editor.sticker.event.EditTextIconEvent;
import com.teamvinay.newphotoediter.editor.sticker.event.FlipHorizontallyEvent;
import com.teamvinay.newphotoediter.editor.sticker.event.ZoomIconEvent;
import com.teamvinay.newphotoediter.featuresfoto.ColorSplashDialog;
import com.teamvinay.newphotoediter.featuresfoto.addtext.AddTextProperties;
import com.teamvinay.newphotoediter.featuresfoto.addtext.TextEditorDialogFragment;
import com.teamvinay.newphotoediter.featuresfoto.adjust.AdjustAdapter;
import com.teamvinay.newphotoediter.featuresfoto.adjust.AdjustListener;
import com.teamvinay.newphotoediter.featuresfoto.beauty.BeautyDialog;
import com.teamvinay.newphotoediter.featuresfoto.crop.CropDialogFragment;
import com.teamvinay.newphotoediter.featuresfoto.draw.BrushColorListener;
import com.teamvinay.newphotoediter.featuresfoto.draw.BrushMagicListener;
import com.teamvinay.newphotoediter.featuresfoto.draw.ColorAdapter;
import com.teamvinay.newphotoediter.featuresfoto.draw.MagicBrushAdapter;
import com.teamvinay.newphotoediter.featuresfoto.insta.InstaDialog;
import com.teamvinay.newphotoediter.featuresfoto.mosaic.MosaicDialog;
import com.teamvinay.newphotoediter.featuresfoto.picker.PhotoPicker;
import com.teamvinay.newphotoediter.featuresfoto.picker.utils.PermissionsUtils;
import com.teamvinay.newphotoediter.featuresfoto.splash.SplashDialog;
import com.teamvinay.newphotoediter.featuresfoto.sticker.adapter.RecyclerTabLayout;
import com.teamvinay.newphotoediter.featuresfoto.sticker.adapter.StickerAdapter;
import com.teamvinay.newphotoediter.featuresfoto.sticker.adapter.TopTabEditAdapter;
import com.teamvinay.newphotoediter.ui.view.DrawBitmapModel;
import com.teamvinay.newphotoediter.ui.view.OnPhotoEditorListener;
import com.teamvinay.newphotoediter.ui.view.OnSaveBitmap;
import com.teamvinay.newphotoediter.ui.view.PhotoEditor;
import com.teamvinay.newphotoediter.ui.view.PhotoEditorView;
import com.teamvinay.newphotoediter.ui.view.ViewType;
import com.teamvinay.newphotoediter.util.AssetUtils;
import com.teamvinay.newphotoediter.util.FileUtils;
import com.teamvinay.newphotoediter.util.SharePreferenceUtil;
import com.teamvinay.newphotoediter.util.SystemUtil;
import org.wysaid.nativePort.CGENativeLibrary;

/* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity */
public class EditImageActivity{ /*extends BaseActivity implements OnPhotoEditorListener, View.OnClickListener, StickerAdapter.OnClickStickerListener, CropDialogFragment.OnCropPhoto, BrushColorListener, BrushMagicListener, InstaDialog.InstaSaveListener, SplashDialog.SplashDialogListener, BeautyDialog.OnBeautySave, MosaicDialog.MosaicDialogListener, EditingToolsAdapter.OnItemSelected, FilterListener, AdjustListener {
    private static final String TAG = "EditImageActivity";
    private static EditImageActivity instance;
    *//* access modifiers changed from: private *//*
    public ImageView addNewSticker;
    private ImageView addNewText;
    private ConstraintLayout adjustLayout;
    private DegreeSeekBar adjustSeekBar;
    private TextView brush;
    private TextView brushBlur;
    private ConstraintLayout brushLayout;
    private SeekBar brushSize;
    private ImageView compareAdjust;
    *//* access modifiers changed from: private *//*
    public ImageView compareFilter;
    *//* access modifiers changed from: private *//*
    public ImageView compareOverlay;
    private ToolType currentMode = ToolType.NONE;
    private ImageView erase;
    private SeekBar eraseSize;
    *//* access modifiers changed from: private *//*
    public SeekBar filterIntensity;
    *//* access modifiers changed from: private *//*
    public ConstraintLayout filterLayout;
    private KeyboardHeightProvider keyboardHeightProvider;
    private RelativeLayout loadingView;
    *//* access modifiers changed from: private *//*
    public List<Bitmap> lstBitmapWithFilter = new ArrayList();
    *//* access modifiers changed from: private *//*
    public List<Bitmap> lstBitmapWithOverlay = new ArrayList();
    *//* access modifiers changed from: private *//*
    public AdjustAdapter mAdjustAdapter;
    private RecyclerView mColorBush;
    private EditingToolsAdapter mEditingToolsAdapter = new EditingToolsAdapter(this);
    public CGENativeLibrary.LoadImageCallback mLoadImageCallback = new CGENativeLibrary.LoadImageCallback() {
        public Bitmap loadImage(String str, Object obj) {
            try {
                return BitmapFactory.decodeStream(EditImageActivity.this.getAssets().open(str));
            } catch (IOException unused) {
                return null;
            }
        }

        public void loadImageOK(Bitmap bitmap, Object obj) {
            bitmap.recycle();
        }
    };
    private RecyclerView mMagicBrush;
    *//* access modifiers changed from: private *//*
    public PhotoEditor mPhotoEditor;
    *//* access modifiers changed from: private *//*
    public PhotoEditorView mPhotoEditorView;
    private ConstraintLayout mRootView;
    private RecyclerView mRvAdjust;
    *//* access modifiers changed from: private *//*
    public RecyclerView mRvFilters;
    *//* access modifiers changed from: private *//*
    public RecyclerView mRvOverlays;
    *//* access modifiers changed from: private *//*
    public RecyclerView mRvTools;
    private TextView magicBrush;
    View.OnTouchListener onCompareTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    EditImageActivity.this.mPhotoEditorView.getGLSurfaceView().setAlpha(0.0f);
                    return true;
                case 1:
                    EditImageActivity.this.mPhotoEditorView.getGLSurfaceView().setAlpha(1.0f);
                    return false;
                default:
                    return true;
            }
        }
    };
    *//* access modifiers changed from: private *//*
    public SeekBar overlayIntensity;
    *//* access modifiers changed from: private *//*
    public ConstraintLayout overlayLayout;
    private ImageView redo;
    private Button saveBitmap;
    private ConstraintLayout saveControl;
    *//* access modifiers changed from: private *//*
    public SeekBar stickerAlpha;
    private ConstraintLayout stickerLayout;
    *//* access modifiers changed from: private *//*
    public TextEditorDialogFragment.TextEditor textEditor;
    *//* access modifiers changed from: private *//*
    public TextEditorDialogFragment textEditorDialogFragment;
    private ConstraintLayout textLayout;
    private ImageView undo;
    *//* access modifiers changed from: private *//*
    public RelativeLayout wrapPhotoView;
    *//* access modifiers changed from: private *//*
    public LinearLayout wrapStickerList;

    public void onEditTextChangeListener(View view, String str, int i) {
    }

    public static EditImageActivity getInstance() {
        return instance;
    }

    *//* access modifiers changed from: protected *//*
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        makeFullScreen();
        setContentView((int) C1084R.C1089layout.activity_edit_image);
        initViews();
        CGENativeLibrary.setLoadImageCallback(this.mLoadImageCallback, (Object) null);
        if (Build.VERSION.SDK_INT < 26) {
            getWindow().setSoftInputMode(48);
        }
        this.mRvTools.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.mRvTools.setAdapter(this.mEditingToolsAdapter);
        this.mRvFilters.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.mRvFilters.setHasFixedSize(true);
        this.mRvOverlays.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.mRvOverlays.setHasFixedSize(true);
        this.mRvAdjust.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.mRvAdjust.setHasFixedSize(true);
        this.mAdjustAdapter = new AdjustAdapter(getApplicationContext(), this);
        this.mRvAdjust.setAdapter(this.mAdjustAdapter);
        this.mColorBush.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.mColorBush.setHasFixedSize(true);
        this.mColorBush.setAdapter(new ColorAdapter(getApplicationContext(), this));
        this.mMagicBrush.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.mMagicBrush.setHasFixedSize(true);
        this.mMagicBrush.setAdapter(new MagicBrushAdapter(getApplicationContext(), this));
        this.mPhotoEditor = new PhotoEditor.Builder(this, this.mPhotoEditorView).setPinchTextScalable(true).build();
        this.mPhotoEditor.setOnPhotoEditorListener(this);
        toogleDrawBottomToolbar(false);
        this.brushLayout.setAlpha(0.0f);
        this.adjustLayout.setAlpha(0.0f);
        this.filterLayout.setAlpha(0.0f);
        this.stickerLayout.setAlpha(0.0f);
        this.textLayout.setAlpha(0.0f);
        this.overlayLayout.setAlpha(0.0f);
        findViewById(C1084R.C1087id.activitylayout).post(new Runnable() {
            public final void run() {
                EditImageActivity.lambda$onCreate$0(EditImageActivity.this);
            }
        });
        new Handler().postDelayed(new Runnable() {
            public final void run() {
                EditImageActivity.lambda$onCreate$1(EditImageActivity.this);
            }
        }, 1000);
        SharePreferenceUtil.setHeightOfKeyboard(getApplicationContext(), 0);
        this.keyboardHeightProvider = new KeyboardHeightProvider(this);
        this.keyboardHeightProvider.addKeyboardListener(new KeyboardHeightProvider.KeyboardListener() {
            public final void onHeightChanged(int i) {
                EditImageActivity.lambda$onCreate$2(EditImageActivity.this, i);
            }
        });
        instance = this;
        Bundle extras = getIntent().getExtras();
        new OnLoadBitmapFromUri().execute(new String[]{extras.getString(PhotoPicker.KEY_SELECTED_PHOTOS)});
    }

    public static *//* synthetic *//* void lambda$onCreate$0(EditImageActivity editImageActivity) {
        editImageActivity.slideDown(editImageActivity.brushLayout);
        editImageActivity.slideDown(editImageActivity.adjustLayout);
        editImageActivity.slideDown(editImageActivity.filterLayout);
        editImageActivity.slideDown(editImageActivity.stickerLayout);
        editImageActivity.slideDown(editImageActivity.textLayout);
        editImageActivity.slideDown(editImageActivity.overlayLayout);
    }

    public static *//* synthetic *//* void lambda$onCreate$1(EditImageActivity editImageActivity) {
        editImageActivity.brushLayout.setAlpha(1.0f);
        editImageActivity.adjustLayout.setAlpha(1.0f);
        editImageActivity.filterLayout.setAlpha(1.0f);
        editImageActivity.stickerLayout.setAlpha(1.0f);
        editImageActivity.textLayout.setAlpha(1.0f);
        editImageActivity.overlayLayout.setAlpha(1.0f);
    }

    public static *//* synthetic *//* void lambda$onCreate$2(EditImageActivity editImageActivity, int i) {
        if (i <= 0) {
            SharePreferenceUtil.setHeightOfNotch(editImageActivity.getApplicationContext(), -i);
        } else if (editImageActivity.textEditorDialogFragment != null) {
            editImageActivity.textEditorDialogFragment.updateAddTextBottomToolbarHeight(SharePreferenceUtil.getHeightOfNotch(editImageActivity.getApplicationContext()) + i);
            SharePreferenceUtil.setHeightOfKeyboard(editImageActivity.getApplicationContext(), i + SharePreferenceUtil.getHeightOfNotch(editImageActivity.getApplicationContext()));
        }
    }

    private void toogleDrawBottomToolbar(boolean z) {
        int i = !z ? 8 : 0;
        this.brush.setVisibility(i);
        this.magicBrush.setVisibility(i);
        this.brushBlur.setVisibility(i);
        this.erase.setVisibility(i);
        this.undo.setVisibility(i);
        this.redo.setVisibility(i);
    }

    *//* access modifiers changed from: private *//*
    public void showEraseBrush() {
        this.brushSize.setVisibility(8);
        this.mColorBush.setVisibility(8);
        this.eraseSize.setVisibility(0);
        this.mMagicBrush.setVisibility(8);
        this.brush.setBackgroundResource(0);
        this.brush.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.magicBrush.setBackgroundResource(0);
        this.magicBrush.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.brushBlur.setBackgroundResource(0);
        this.brushBlur.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.erase.setImageResource(C1084R.C1086drawable.erase_selected);
        this.mPhotoEditor.brushEraser();
        this.eraseSize.setProgress(20);
    }

    *//* access modifiers changed from: private *//*
    public void showColorBlurBrush() {
        this.brushSize.setVisibility(0);
        this.mColorBush.setVisibility(0);
        ColorAdapter colorAdapter = (ColorAdapter) this.mColorBush.getAdapter();
        colorAdapter.setSelectedColorIndex(0);
        this.mColorBush.scrollToPosition(0);
        colorAdapter.notifyDataSetChanged();
        this.eraseSize.setVisibility(8);
        this.mMagicBrush.setVisibility(8);
        this.erase.setImageResource(C1084R.C1086drawable.erase);
        this.magicBrush.setBackgroundResource(0);
        this.magicBrush.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.brush.setBackgroundResource(0);
        this.brush.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.brushBlur.setBackground(getResources().getDrawable(C1084R.C1086drawable.border_bottom));
        this.brushBlur.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.mPhotoEditor.setBrushMode(2);
        this.mPhotoEditor.setBrushDrawingMode(true);
        this.brushSize.setProgress(20);
    }

    *//* access modifiers changed from: private *//*
    public void showColorBrush() {
        this.brushSize.setVisibility(0);
        this.mColorBush.setVisibility(0);
        this.mColorBush.scrollToPosition(0);
        ColorAdapter colorAdapter = (ColorAdapter) this.mColorBush.getAdapter();
        colorAdapter.setSelectedColorIndex(0);
        colorAdapter.notifyDataSetChanged();
        this.eraseSize.setVisibility(8);
        this.mMagicBrush.setVisibility(8);
        this.erase.setImageResource(C1084R.C1086drawable.erase);
        this.magicBrush.setBackgroundResource(0);
        this.magicBrush.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.brush.setBackground(getResources().getDrawable(C1084R.C1086drawable.border_bottom));
        this.brush.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.brushBlur.setBackgroundResource(0);
        this.brushBlur.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.mPhotoEditor.setBrushMode(1);
        this.mPhotoEditor.setBrushDrawingMode(true);
        this.brushSize.setProgress(20);
    }

    *//* access modifiers changed from: private *//*
    public void showMagicBrush() {
        this.brushSize.setVisibility(0);
        this.mColorBush.setVisibility(8);
        this.eraseSize.setVisibility(8);
        this.mMagicBrush.setVisibility(0);
        this.erase.setImageResource(C1084R.C1086drawable.erase);
        this.magicBrush.setBackground(getResources().getDrawable(C1084R.C1086drawable.border_bottom));
        this.magicBrush.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.brush.setBackgroundResource(0);
        this.brush.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.brushBlur.setBackgroundResource(0);
        this.brushBlur.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.mPhotoEditor.setBrushMagic(MagicBrushAdapter.lstDrawBitmapModel(getApplicationContext()).get(0));
        this.mPhotoEditor.setBrushMode(3);
        this.mPhotoEditor.setBrushDrawingMode(true);
        MagicBrushAdapter magicBrushAdapter = (MagicBrushAdapter) this.mMagicBrush.getAdapter();
        magicBrushAdapter.setSelectedColorIndex(0);
        this.mMagicBrush.scrollToPosition(0);
        magicBrushAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        this.wrapStickerList = (LinearLayout) findViewById(C1084R.C1087id.wrapStickerList);
        this.mPhotoEditorView = (PhotoEditorView) findViewById(C1084R.C1087id.photoEditorView);
        this.mPhotoEditorView.setVisibility(4);
        this.mRvTools = (RecyclerView) findViewById(C1084R.C1087id.rvConstraintTools);
        this.mRvFilters = (RecyclerView) findViewById(C1084R.C1087id.rvFilterView);
        this.mRvOverlays = (RecyclerView) findViewById(C1084R.C1087id.rvOverlayView);
        this.mRvAdjust = (RecyclerView) findViewById(C1084R.C1087id.rvAdjustView);
        this.mRootView = (ConstraintLayout) findViewById(C1084R.C1087id.rootView);
        this.filterLayout = (ConstraintLayout) findViewById(C1084R.C1087id.filterLayout);
        this.overlayLayout = (ConstraintLayout) findViewById(C1084R.C1087id.overlayLayout);
        this.adjustLayout = (ConstraintLayout) findViewById(C1084R.C1087id.adjustLayout);
        this.stickerLayout = (ConstraintLayout) findViewById(C1084R.C1087id.stickerLayout);
        this.textLayout = (ConstraintLayout) findViewById(C1084R.C1087id.textControl);
        ViewPager viewPager = (ViewPager) findViewById(C1084R.C1087id.sticker_viewpaper);
        this.filterIntensity = (SeekBar) findViewById(C1084R.C1087id.filterIntensity);
        this.overlayIntensity = (SeekBar) findViewById(C1084R.C1087id.overlayIntensity);
        this.stickerAlpha = (SeekBar) findViewById(C1084R.C1087id.stickerAlpha);
        this.stickerAlpha.setVisibility(8);
        this.brushLayout = (ConstraintLayout) findViewById(C1084R.C1087id.brushLayout);
        this.mColorBush = (RecyclerView) findViewById(C1084R.C1087id.rvColorBush);
        this.mMagicBrush = (RecyclerView) findViewById(C1084R.C1087id.rvMagicBush);
        this.wrapPhotoView = (RelativeLayout) findViewById(C1084R.C1087id.wrap_photo_view);
        this.brush = (TextView) findViewById(C1084R.C1087id.draw);
        this.magicBrush = (TextView) findViewById(C1084R.C1087id.brush_magic);
        this.erase = (ImageView) findViewById(C1084R.C1087id.erase);
        this.undo = (ImageView) findViewById(C1084R.C1087id.undo);
        this.undo.setVisibility(8);
        this.redo = (ImageView) findViewById(C1084R.C1087id.redo);
        this.redo.setVisibility(8);
        this.brushBlur = (TextView) findViewById(C1084R.C1087id.brush_blur);
        this.brushSize = (SeekBar) findViewById(C1084R.C1087id.brushSize);
        this.eraseSize = (SeekBar) findViewById(C1084R.C1087id.eraseSize);
        this.loadingView = (RelativeLayout) findViewById(C1084R.C1087id.loadingView);
        this.loadingView.setVisibility(0);
        this.saveBitmap = (Button) findViewById(C1084R.C1087id.save);
        this.saveControl = (ConstraintLayout) findViewById(C1084R.C1087id.saveControl);
        this.saveBitmap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PermissionsUtils.checkWriteStoragePermission((Activity) EditImageActivity.this)) {
                    new SaveBitmapAsFile().execute(new Void[0]);
                }
            }
        });
        this.compareAdjust = (ImageView) findViewById(C1084R.C1087id.compareAdjust);
        this.compareAdjust.setOnTouchListener(this.onCompareTouchListener);
        this.compareAdjust.setVisibility(8);
        this.compareFilter = (ImageView) findViewById(C1084R.C1087id.compareFilter);
        this.compareFilter.setOnTouchListener(this.onCompareTouchListener);
        this.compareFilter.setVisibility(8);
        this.compareOverlay = (ImageView) findViewById(C1084R.C1087id.compareOverlay);
        this.compareOverlay.setOnTouchListener(this.onCompareTouchListener);
        this.compareOverlay.setVisibility(8);
        findViewById(C1084R.C1087id.exitEditMode).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditImageActivity.super.onBackPressed();
            }
        });
        this.erase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditImageActivity.this.showEraseBrush();
            }
        });
        this.brush.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditImageActivity.this.showColorBrush();
            }
        });
        this.magicBrush.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditImageActivity.this.showMagicBrush();
            }
        });
        this.brushBlur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditImageActivity.this.showColorBlurBrush();
            }
        });
        this.eraseSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                EditImageActivity.this.mPhotoEditor.setBrushEraserSize((float) i);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                EditImageActivity.this.mPhotoEditor.brushEraser();
            }
        });
        this.brushSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                EditImageActivity.this.mPhotoEditor.setBrushSize((float) (i + 10));
            }
        });
        this.stickerAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                Sticker currentSticker = EditImageActivity.this.mPhotoEditorView.getCurrentSticker();
                if (currentSticker != null) {
                    currentSticker.setAlpha(i);
                }
            }
        });
        this.addNewSticker = (ImageView) findViewById(C1084R.C1087id.addNewSticker);
        this.addNewSticker.setVisibility(8);
        this.addNewSticker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditImageActivity.this.addNewSticker.setVisibility(8);
                EditImageActivity.this.slideUp(EditImageActivity.this.wrapStickerList);
            }
        });
        this.addNewText = (ImageView) findViewById(C1084R.C1087id.addNewText);
        this.addNewText.setVisibility(8);
        this.addNewText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditImageActivity.this.mPhotoEditorView.setHandlingSticker((Sticker) null);
                EditImageActivity.this.openTextFragment();
            }
        });
        this.adjustSeekBar = (DegreeSeekBar) findViewById(C1084R.C1087id.adjustLevel);
        this.adjustSeekBar.setDegreeRange(-50, 50);
        this.adjustSeekBar.setScrollingListener(new DegreeSeekBar.ScrollingListener() {
            public void onScrollEnd() {
            }

            public void onScrollStart() {
            }

            public void onScroll(int i) {
                AdjustAdapter.AdjustModel currentAdjustModel = EditImageActivity.this.mAdjustAdapter.getCurrentAdjustModel();
                currentAdjustModel.originValue = (((float) Math.abs(i + 50)) * ((currentAdjustModel.maxValue - ((currentAdjustModel.maxValue + currentAdjustModel.minValue) / 2.0f)) / 50.0f)) + currentAdjustModel.minValue;
                EditImageActivity.this.mPhotoEditor.setAdjustFilter(EditImageActivity.this.mAdjustAdapter.getFilterConfig());
            }
        });
        BitmapStickerIcon bitmapStickerIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this, C1084R.C1086drawable.sticker_ic_close_white_18dp), 0, BitmapStickerIcon.REMOVE);
        bitmapStickerIcon.setIconEvent(new DeleteIconEvent());
        BitmapStickerIcon bitmapStickerIcon2 = new BitmapStickerIcon(ContextCompat.getDrawable(this, C1084R.C1086drawable.sticker_ic_scale_white_18dp), 3, BitmapStickerIcon.ZOOM);
        bitmapStickerIcon2.setIconEvent(new ZoomIconEvent());
        BitmapStickerIcon bitmapStickerIcon3 = new BitmapStickerIcon(ContextCompat.getDrawable(this, C1084R.C1086drawable.sticker_ic_flip_white_18dp), 1, BitmapStickerIcon.FLIP);
        bitmapStickerIcon3.setIconEvent(new FlipHorizontallyEvent());
        BitmapStickerIcon bitmapStickerIcon4 = new BitmapStickerIcon(ContextCompat.getDrawable(this, C1084R.C1086drawable.icon_rotate), 3, BitmapStickerIcon.ROTATE);
        bitmapStickerIcon4.setIconEvent(new ZoomIconEvent());
        BitmapStickerIcon bitmapStickerIcon5 = new BitmapStickerIcon(ContextCompat.getDrawable(this, C1084R.C1086drawable.icon_edit), 1, BitmapStickerIcon.EDIT);
        bitmapStickerIcon5.setIconEvent(new EditTextIconEvent());
        BitmapStickerIcon bitmapStickerIcon6 = new BitmapStickerIcon(ContextCompat.getDrawable(this, C1084R.C1086drawable.icon_center), 2, BitmapStickerIcon.ALIGN_HORIZONTALLY);
        bitmapStickerIcon6.setIconEvent(new AlignHorizontallyEvent());
        this.mPhotoEditorView.setIcons(Arrays.asList(new BitmapStickerIcon[]{bitmapStickerIcon, bitmapStickerIcon2, bitmapStickerIcon3, bitmapStickerIcon5, bitmapStickerIcon4, bitmapStickerIcon6}));
        this.mPhotoEditorView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.mPhotoEditorView.setLocked(false);
        this.mPhotoEditorView.setConstrained(true);
        this.mPhotoEditorView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            public void onStickerDragFinished(@NonNull Sticker sticker) {
            }

            public void onStickerFlipped(@NonNull Sticker sticker) {
            }

            public void onStickerTouchedDown(@NonNull Sticker sticker) {
            }

            public void onStickerZoomFinished(@NonNull Sticker sticker) {
            }

            public void onTouchDownForBeauty(float f, float f2) {
            }

            public void onTouchDragForBeauty(float f, float f2) {
            }

            public void onTouchUpForBeauty(float f, float f2) {
            }

            public void onStickerAdded(@NonNull Sticker sticker) {
                EditImageActivity.this.stickerAlpha.setVisibility(0);
                EditImageActivity.this.stickerAlpha.setProgress(sticker.getAlpha());
            }

            public void onStickerClicked(@NonNull Sticker sticker) {
                if (sticker instanceof TextSticker) {
                    ((TextSticker) sticker).setTextColor(SupportMenu.CATEGORY_MASK);
                    EditImageActivity.this.mPhotoEditorView.replace(sticker);
                    EditImageActivity.this.mPhotoEditorView.invalidate();
                }
                EditImageActivity.this.stickerAlpha.setVisibility(0);
                EditImageActivity.this.stickerAlpha.setProgress(sticker.getAlpha());
            }

            public void onStickerDeleted(@NonNull Sticker sticker) {
                EditImageActivity.this.stickerAlpha.setVisibility(8);
            }

            public void onStickerTouchOutside() {
                EditImageActivity.this.stickerAlpha.setVisibility(8);
            }

            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
                if (sticker instanceof TextSticker) {
                    sticker.setShow(false);
                    EditImageActivity.this.mPhotoEditorView.setHandlingSticker((Sticker) null);
                    TextEditorDialogFragment unused = EditImageActivity.this.textEditorDialogFragment = TextEditorDialogFragment.show(EditImageActivity.this, ((TextSticker) sticker).getAddTextProperties());
                    TextEditorDialogFragment.TextEditor unused2 = EditImageActivity.this.textEditor = new TextEditorDialogFragment.TextEditor() {
                        public void onDone(AddTextProperties addTextProperties) {
                            EditImageActivity.this.mPhotoEditorView.getStickers().remove(EditImageActivity.this.mPhotoEditorView.getLastHandlingSticker());
                            EditImageActivity.this.mPhotoEditorView.addSticker(new TextSticker(EditImageActivity.this, addTextProperties));
                        }

                        public void onBackButton() {
                            EditImageActivity.this.mPhotoEditorView.showLastHandlingSticker();
                        }
                    };
                    EditImageActivity.this.textEditorDialogFragment.setOnTextEditorListener(EditImageActivity.this.textEditor);
                }
            }
        });
        this.filterIntensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                EditImageActivity.this.mPhotoEditorView.setFilterIntensity(((float) i) / 100.0f);
            }
        });
        this.overlayIntensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                EditImageActivity.this.mPhotoEditorView.setFilterIntensity(((float) i) / 100.0f);
            }
        });
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        final int i = point.x;
        viewPager.setAdapter(new PagerAdapter() {
            public int getCount() {
                return 13;
            }

            public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
                return view.equals(obj);
            }

            public void destroyItem(@NonNull View view, int i, @NonNull Object obj) {
                ((ViewPager) view).removeView((View) obj);
            }

            @NonNull
            public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
                View inflate = LayoutInflater.from(EditImageActivity.this.getBaseContext()).inflate(C1084R.C1089layout.sticker_items, (ViewGroup) null, false);
                RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C1084R.C1087id.f595rv);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(EditImageActivity.this.getApplicationContext(), 4));
                switch (i) {
                    case 0:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstCatFaces(), i, EditImageActivity.this));
                        break;
                    case 1:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstCkeeks(), i, EditImageActivity.this));
                        break;
                    case 2:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstDiadems(), i, EditImageActivity.this));
                        break;
                    case 3:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstEyes(), i, EditImageActivity.this));
                        break;
                    case 4:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstGiddy(), i, EditImageActivity.this));
                        break;
                    case 5:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstGlasses(), i, EditImageActivity.this));
                        break;
                    case 6:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstTies(), i, EditImageActivity.this));
                        break;
                    case 7:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstHeardes(), i, EditImageActivity.this));
                        break;
                    case 8:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstEmoj(), i, EditImageActivity.this));
                        break;
                    case 9:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstTexts(), i, EditImageActivity.this));
                        break;
                    case 10:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstOthers(), i, EditImageActivity.this));
                        break;
                    case 11:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstMuscle(), i, EditImageActivity.this));
                        break;
                    case 12:
                        recyclerView.setAdapter(new StickerAdapter(EditImageActivity.this.getApplicationContext(), AssetUtils.lstTatoos(), i, EditImageActivity.this));
                        break;
                }
                viewGroup.addView(inflate);
                return inflate;
            }
        });
        RecyclerTabLayout recyclerTabLayout = (RecyclerTabLayout) findViewById(C1084R.C1087id.recycler_tab_layout);
        recyclerTabLayout.setUpWithAdapter(new TopTabEditAdapter(viewPager, getApplicationContext()));
        recyclerTabLayout.setPositionThreshold(0.5f);
        recyclerTabLayout.setBackgroundColor(getResources().getColor(C1084R.C1085color.basic_white));
    }

    public void showLoading(boolean z) {
        if (z) {
            getWindow().setFlags(16, 16);
            this.loadingView.setVisibility(0);
            return;
        }
        getWindow().clearFlags(16);
        this.loadingView.setVisibility(8);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onAddViewListener(ViewType viewType, int i) {
        String str = TAG;
        Log.d(str, "onAddViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + i + "]");
    }

    public void onRemoveViewListener(int i) {
        String str = TAG;
        Log.d(str, "onRemoveViewListener() called with: numberOfAddedViews = [" + i + "]");
    }

    public void onRemoveViewListener(ViewType viewType, int i) {
        String str = TAG;
        Log.d(str, "onRemoveViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + i + "]");
    }

    public void onStartViewChangeListener(ViewType viewType) {
        String str = TAG;
        Log.d(str, "onStartViewChangeListener() called with: viewType = [" + viewType + "]");
    }

    public void onStopViewChangeListener(ViewType viewType) {
        String str = TAG;
        Log.d(str, "onStopViewChangeListener() called with: viewType = [" + viewType + "]");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1084R.C1087id.imgCloseAdjust:
            case C1084R.C1087id.imgCloseBrush:
            case C1084R.C1087id.imgCloseFilter:
            case C1084R.C1087id.imgCloseOverlay:
            case C1084R.C1087id.imgCloseSticker:
            case C1084R.C1087id.imgCloseText:
                slideDownSaveView();
                onBackPressed();
                return;
            case C1084R.C1087id.imgSaveAdjust:
                new SaveFilterAsBitmap().execute(new Void[0]);
                this.compareAdjust.setVisibility(8);
                slideDown(this.adjustLayout);
                slideUp(this.mRvTools);
                slideDownSaveView();
                this.currentMode = ToolType.NONE;
                return;
            case C1084R.C1087id.imgSaveBrush:
                showLoading(true);
                runOnUiThread(new Runnable() {
                    public final void run() {
                        EditImageActivity.lambda$onClick$3(EditImageActivity.this);
                    }
                });
                slideDownSaveView();
                this.currentMode = ToolType.NONE;
                return;
            case C1084R.C1087id.imgSaveFilter:
                new SaveFilterAsBitmap().execute(new Void[0]);
                this.compareFilter.setVisibility(8);
                slideDown(this.filterLayout);
                slideUp(this.mRvTools);
                slideDownSaveView();
                this.currentMode = ToolType.NONE;
                return;
            case C1084R.C1087id.imgSaveOverlay:
                new SaveFilterAsBitmap().execute(new Void[0]);
                slideDown(this.overlayLayout);
                slideUp(this.mRvTools);
                this.compareOverlay.setVisibility(8);
                slideDownSaveView();
                this.currentMode = ToolType.NONE;
                return;
            case C1084R.C1087id.imgSaveSticker:
                this.mPhotoEditorView.setHandlingSticker((Sticker) null);
                this.mPhotoEditorView.setLocked(true);
                this.stickerAlpha.setVisibility(8);
                this.addNewSticker.setVisibility(8);
                if (!this.mPhotoEditorView.getStickers().isEmpty()) {
                    new SaveStickerAsBitmap().execute(new Void[0]);
                }
                slideUp(this.wrapStickerList);
                slideDown(this.stickerLayout);
                slideUp(this.mRvTools);
                slideDownSaveView();
                this.currentMode = ToolType.NONE;
                return;
            case C1084R.C1087id.imgSaveText:
                this.mPhotoEditorView.setHandlingSticker((Sticker) null);
                this.mPhotoEditorView.setLocked(true);
                this.addNewText.setVisibility(8);
                if (!this.mPhotoEditorView.getStickers().isEmpty()) {
                    new SaveStickerAsBitmap().execute(new Void[0]);
                }
                slideDown(this.textLayout);
                slideUp(this.mRvTools);
                slideDownSaveView();
                this.currentMode = ToolType.NONE;
                return;
            case C1084R.C1087id.redo:
                this.mPhotoEditor.redoBrush();
                return;
            case C1084R.C1087id.undo:
                this.mPhotoEditor.undoBrush();
                return;
            default:
                return;
        }
    }

    public static *//* synthetic *//* void lambda$onClick$3(EditImageActivity editImageActivity) {
        editImageActivity.mPhotoEditor.setBrushDrawingMode(false);
        editImageActivity.undo.setVisibility(8);
        editImageActivity.redo.setVisibility(8);
        editImageActivity.erase.setVisibility(8);
        editImageActivity.slideDown(editImageActivity.brushLayout);
        editImageActivity.slideUp(editImageActivity.mRvTools);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(editImageActivity.mRootView);
        if (!SharePreferenceUtil.isPurchased(editImageActivity.getApplicationContext())) {
            constraintSet.connect(editImageActivity.wrapPhotoView.getId(), 3, editImageActivity.mRootView.getId(), 3, SystemUtil.dpToPx(editImageActivity.getApplicationContext(), 50));
        } else {
            constraintSet.connect(editImageActivity.wrapPhotoView.getId(), 3, editImageActivity.mRootView.getId(), 3, 0);
        }
        constraintSet.connect(editImageActivity.wrapPhotoView.getId(), 1, editImageActivity.mRootView.getId(), 1, 0);
        constraintSet.connect(editImageActivity.wrapPhotoView.getId(), 4, editImageActivity.mRvTools.getId(), 3, 0);
        constraintSet.connect(editImageActivity.wrapPhotoView.getId(), 2, editImageActivity.mRootView.getId(), 2, 0);
        constraintSet.applyTo(editImageActivity.mRootView);
        editImageActivity.mPhotoEditorView.setImageSource(editImageActivity.mPhotoEditor.getBrushDrawingView().getDrawBitmap(editImageActivity.mPhotoEditorView.getCurrentBitmap()));
        editImageActivity.mPhotoEditor.clearBrushAllViews();
        editImageActivity.showLoading(false);
        editImageActivity.updateLayout();
    }



    public void onPause() {
        super.onPause();
        this.keyboardHeightProvider.onPause();
    }

    public void onResume() {
        super.onResume();
        this.keyboardHeightProvider.onResume();
    }

    public void isPermissionGranted(boolean z, String str) {
        if (z) {
            new SaveBitmapAsFile().execute(new Void[0]);
        }
    }

    public void onFilterSelected(String str) {
        this.mPhotoEditor.setFilterEffect(str);
        this.filterIntensity.setProgress(100);
        this.overlayIntensity.setProgress(70);
        if (this.currentMode == ToolType.OVERLAY) {
            this.mPhotoEditorView.getGLSurfaceView().setFilterIntensity(0.7f);
        }
    }

    *//* access modifiers changed from: private *//*
    public void openTextFragment() {
        this.textEditorDialogFragment = TextEditorDialogFragment.show(this);
        this.textEditor = new TextEditorDialogFragment.TextEditor() {
            public void onDone(AddTextProperties addTextProperties) {
                EditImageActivity.this.mPhotoEditorView.addSticker(new TextSticker(EditImageActivity.this.getApplicationContext(), addTextProperties));
            }

            public void onBackButton() {
                if (EditImageActivity.this.mPhotoEditorView.getStickers().isEmpty()) {
                    EditImageActivity.this.onBackPressed();
                }
            }
        };
        this.textEditorDialogFragment.setOnTextEditorListener(this.textEditor);
    }

    public void onToolSelected(ToolType toolType) {
        this.currentMode = toolType;
        switch (toolType) {
            case BRUSH:
                showColorBrush();
                this.mPhotoEditor.setBrushDrawingMode(true);
                slideDown(this.mRvTools);
                slideUp(this.brushLayout);
                slideUpSaveControl();
                toogleDrawBottomToolbar(true);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(this.mRootView);
                if (!SharePreferenceUtil.isPurchased(getApplicationContext())) {
                    constraintSet.connect(this.wrapPhotoView.getId(), 3, this.mRootView.getId(), 3, SystemUtil.dpToPx(getApplicationContext(), 50));
                } else {
                    constraintSet.connect(this.wrapPhotoView.getId(), 3, this.mRootView.getId(), 3, 0);
                }
                constraintSet.connect(this.wrapPhotoView.getId(), 1, this.mRootView.getId(), 1, 0);
                constraintSet.connect(this.wrapPhotoView.getId(), 4, this.brushLayout.getId(), 3, 0);
                constraintSet.connect(this.wrapPhotoView.getId(), 2, this.mRootView.getId(), 2, 0);
                constraintSet.applyTo(this.mRootView);
                this.mPhotoEditor.setBrushMode(1);
                updateLayout();
                break;
            case TEXT:
                this.mPhotoEditorView.setLocked(false);
                openTextFragment();
                slideDown(this.mRvTools);
                slideUp(this.textLayout);
                this.addNewText.setVisibility(0);
                break;
            case ADJUST:
                slideUpSaveView();
                this.compareAdjust.setVisibility(0);
                this.adjustSeekBar.setCurrentDegrees(0);
                this.mAdjustAdapter = new AdjustAdapter(getApplicationContext(), this);
                this.mRvAdjust.setAdapter(this.mAdjustAdapter);
                this.mPhotoEditor.setAdjustFilter(this.mAdjustAdapter.getFilterConfig());
                slideUp(this.adjustLayout);
                slideDown(this.mRvTools);
                break;
            case FILTER:
                slideUpSaveView();
                new LoadFilterBitmap().execute(new Void[0]);
                break;
            case STICKER:
                this.mPhotoEditorView.setLocked(false);
                slideDown(this.mRvTools);
                slideUp(this.stickerLayout);
                break;
            case OVERLAY:
                slideUpSaveView();
                new LoadOverlayBitmap().execute(new Void[0]);
                break;
            case INSTA:
                new ShowInstaDialog().execute(new Void[0]);
                break;
            case SPLASH:
                new ShowSplashDialog(true).execute(new Void[0]);
                break;
            case BLUR:
                new ShowSplashDialog(false).execute(new Void[0]);
                break;
            case MOSAIC:
                new ShowMosaicDialog().execute(new Void[0]);
                break;
            case COLOR:
                ColorSplashDialog.show(this, this.mPhotoEditorView.getCurrentBitmap());
                break;
            case CROP:
                CropDialogFragment.show(this, this, this.mPhotoEditorView.getCurrentBitmap());
                break;
            case BEAUTY:
                try {
                    BeautyDialog.show(this, this.mPhotoEditorView.getCurrentBitmap(), this);
                    break;
                } catch (Exception unused) {
                    break;
                }
        }
        this.mPhotoEditorView.setHandlingSticker((Sticker) null);
    }

    public void slideUp(View view) {
        ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) view.getHeight(), 0.0f}).start();
    }

    public void slideUpSaveView() {
        this.saveControl.setVisibility(8);
    }

    public void slideUpSaveControl() {
        this.saveControl.setVisibility(8);
    }

    public void slideDownSaveControl() {
        this.saveControl.setVisibility(0);
    }

    public void slideDownSaveView() {
        this.saveControl.setVisibility(0);
    }

    public void slideDown(View view) {
        ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, (float) view.getHeight()}).start();
    }

    public void onBackPressed() {
        if (this.currentMode == null) {
           *//* AdmobHelp.getInstance().showInterstitialAd(new AdmobHelp.AdCloseListener() {
                public void onAdClosed() {
                    EditImageActivity.this.finish();
                }
            }, this);*//*

        super.onBackPressed();
        }
        try {
            switch (this.currentMode) {
                case BRUSH:
                    slideDown(this.brushLayout);
                    slideUp(this.mRvTools);
                    slideDownSaveControl();
                    this.undo.setVisibility(8);
                    this.redo.setVisibility(8);
                    this.erase.setVisibility(8);
                    this.mPhotoEditor.setBrushDrawingMode(false);
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(this.mRootView);
                    if (!SharePreferenceUtil.isPurchased(getApplicationContext())) {
                        constraintSet.connect(this.wrapPhotoView.getId(), 3, this.mRootView.getId(), 3, SystemUtil.dpToPx(getApplicationContext(), 50));
                    } else {
                        constraintSet.connect(this.wrapPhotoView.getId(), 3, this.mRootView.getId(), 3, 0);
                    }
                    constraintSet.connect(this.wrapPhotoView.getId(), 1, this.mRootView.getId(), 1, 0);
                    constraintSet.connect(this.wrapPhotoView.getId(), 4, this.mRvTools.getId(), 3, 0);
                    constraintSet.connect(this.wrapPhotoView.getId(), 2, this.mRootView.getId(), 2, 0);
                    constraintSet.applyTo(this.mRootView);
                    this.mPhotoEditor.clearBrushAllViews();
                    slideDownSaveView();
                    this.currentMode = ToolType.NONE;
                    updateLayout();
                    return;
                case TEXT:
                    if (!this.mPhotoEditorView.getStickers().isEmpty()) {
                        this.mPhotoEditorView.getStickers().clear();
                        this.mPhotoEditorView.setHandlingSticker((Sticker) null);
                    }
                    slideDown(this.textLayout);
                    this.addNewText.setVisibility(8);
                    this.mPhotoEditorView.setHandlingSticker((Sticker) null);
                    slideUp(this.mRvTools);
                    this.mPhotoEditorView.setLocked(true);
                    slideDownSaveView();
                    this.currentMode = ToolType.NONE;
                    return;
                case ADJUST:
                    this.mPhotoEditor.setFilterEffect("");
                    this.compareAdjust.setVisibility(8);
                    slideDown(this.adjustLayout);
                    slideUp(this.mRvTools);
                    slideDownSaveView();
                    this.currentMode = ToolType.NONE;
                    return;
                case FILTER:
                    slideDown(this.filterLayout);
                    slideUp(this.mRvTools);
                    slideDownSaveView();
                    this.mPhotoEditor.setFilterEffect("");
                    this.compareFilter.setVisibility(8);
                    this.lstBitmapWithFilter.clear();
                    this.mRvFilters.getAdapter().notifyDataSetChanged();
                    this.currentMode = ToolType.NONE;
                    return;
                case STICKER:
                    if (this.mPhotoEditorView.getStickers().size() <= 0) {
                        slideUp(this.wrapStickerList);
                        slideDown(this.stickerLayout);
                        this.addNewSticker.setVisibility(8);
                        this.mPhotoEditorView.setHandlingSticker((Sticker) null);
                        slideUp(this.mRvTools);
                        this.mPhotoEditorView.setLocked(true);
                        this.currentMode = ToolType.NONE;
                    } else if (this.addNewSticker.getVisibility() == 0) {
                        this.mPhotoEditorView.getStickers().clear();
                        this.addNewSticker.setVisibility(8);
                        this.mPhotoEditorView.setHandlingSticker((Sticker) null);
                        slideUp(this.wrapStickerList);
                        slideDown(this.stickerLayout);
                        slideUp(this.mRvTools);
                        this.currentMode = ToolType.NONE;
                    } else {
                        slideDown(this.wrapStickerList);
                        this.addNewSticker.setVisibility(0);
                    }
                    slideDownSaveView();
                    return;
                case OVERLAY:
                    this.mPhotoEditor.setFilterEffect("");
                    this.compareOverlay.setVisibility(8);
                    this.lstBitmapWithOverlay.clear();
                    slideUp(this.mRvTools);
                    slideDown(this.overlayLayout);
                    slideDownSaveView();
                    this.mRvOverlays.getAdapter().notifyDataSetChanged();
                    this.currentMode = ToolType.NONE;
                    return;
                case SPLASH:
                case BLUR:
                case MOSAIC:
                case CROP:
                case BEAUTY:
                    Toast.makeText(getApplicationContext(), getString(C1084R.string.press_one_more_time), 0).show();
                    this.currentMode = null;
                    return;
                case NONE:
                    Toast.makeText(getApplicationContext(), getString(C1084R.string.press_one_more_time), 0).show();
                    this.currentMode = null;
                    return;
                default:
                    super.onBackPressed();
                    return;
            }
        } catch (Exception unused) {
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onAdjustSelected(AdjustAdapter.AdjustModel adjustModel) {
        this.adjustSeekBar.setCurrentDegrees(((int) ((adjustModel.originValue - adjustModel.minValue) / ((adjustModel.maxValue - ((adjustModel.maxValue + adjustModel.minValue) / 2.0f)) / 50.0f))) - 50);
    }

    public void addSticker(Bitmap bitmap) {
        this.mPhotoEditorView.addSticker(new DrawableSticker(new BitmapDrawable(getResources(), bitmap)));
        slideDown(this.wrapStickerList);
        this.addNewSticker.setVisibility(0);
    }

    public void finishCrop(Bitmap bitmap) {
        this.mPhotoEditorView.setImageSource(bitmap);
        this.currentMode = ToolType.NONE;
        updateLayout();
    }

    public void onColorChanged(String str) {
        this.mPhotoEditor.setBrushColor(Color.parseColor(str));
    }

    public void instaSavedBitmap(Bitmap bitmap) {
        this.mPhotoEditorView.setImageSource(bitmap);
        this.currentMode = ToolType.NONE;
        updateLayout();
    }

    public void onMagicChanged(DrawBitmapModel drawBitmapModel) {
        this.mPhotoEditor.setBrushMagic(drawBitmapModel);
    }

    public void onSaveSplash(Bitmap bitmap) {
        this.mPhotoEditorView.setImageSource(bitmap);
        this.currentMode = ToolType.NONE;
    }

    public void onSaveMosaic(Bitmap bitmap) {
        this.mPhotoEditorView.setImageSource(bitmap);
        this.currentMode = ToolType.NONE;
    }

    public void onBeautySave(Bitmap bitmap) {
        this.mPhotoEditorView.setImageSource(bitmap);
        this.currentMode = ToolType.NONE;
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity$LoadFilterBitmap *//*
    class LoadFilterBitmap extends AsyncTask<Void, Void, Void> {
        LoadFilterBitmap() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            EditImageActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public Void doInBackground(Void... voidArr) {
            try {
                EditImageActivity.this.lstBitmapWithFilter.clear();
                EditImageActivity.this.lstBitmapWithFilter.addAll(FilterUtils.getLstBitmapWithFilter(ThumbnailUtils.extractThumbnail(EditImageActivity.this.mPhotoEditorView.getCurrentBitmap(), 150, 150)));
                return null;
            } catch (Exception unused) {
                return null;
            }
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(Void voidR) {
            try {
                if (EditImageActivity.this.lstBitmapWithFilter != null && EditImageActivity.this.lstBitmapWithFilter.size() > 0) {
                    EditImageActivity.this.mRvFilters.setAdapter(new FilterViewAdapter(EditImageActivity.this.lstBitmapWithFilter, EditImageActivity.this, EditImageActivity.this.getApplicationContext(), Arrays.asList(FilterUtils.EFFECT_CONFIGS)));
                    EditImageActivity.this.slideDown(EditImageActivity.this.mRvTools);
                    EditImageActivity.this.slideUp(EditImageActivity.this.filterLayout);
                    EditImageActivity.this.compareFilter.setVisibility(0);
                    EditImageActivity.this.filterIntensity.setProgress(100);
                }
                EditImageActivity.this.showLoading(false);
            } catch (Exception unused) {
            }
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity$ShowInstaDialog *//*
    class ShowInstaDialog extends AsyncTask<Void, Bitmap, Bitmap> {
        ShowInstaDialog() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            EditImageActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public Bitmap doInBackground(Void... voidArr) {
            return FilterUtils.getBlurImageFromBitmap(EditImageActivity.this.mPhotoEditorView.getCurrentBitmap(), 5.0f);
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(Bitmap bitmap) {
            EditImageActivity.this.showLoading(false);
            InstaDialog.show(EditImageActivity.this, EditImageActivity.this, EditImageActivity.this.mPhotoEditorView.getCurrentBitmap(), bitmap);
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity$ShowMosaicDialog *//*
    class ShowMosaicDialog extends AsyncTask<Void, List<Bitmap>, List<Bitmap>> {
        ShowMosaicDialog() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            EditImageActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public List<Bitmap> doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(FilterUtils.cloneBitmap(EditImageActivity.this.mPhotoEditorView.getCurrentBitmap()));
            arrayList.add(FilterUtils.getBlurImageFromBitmap(EditImageActivity.this.mPhotoEditorView.getCurrentBitmap(), 8.0f));
            return arrayList;
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(List<Bitmap> list) {
            EditImageActivity.this.showLoading(false);
            MosaicDialog.show(EditImageActivity.this, list.get(0), list.get(1), EditImageActivity.this);
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity$LoadOverlayBitmap *//*
    class LoadOverlayBitmap extends AsyncTask<Void, Void, Void> {
        LoadOverlayBitmap() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            EditImageActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public Void doInBackground(Void... voidArr) {
            EditImageActivity.this.lstBitmapWithOverlay.clear();
            EditImageActivity.this.lstBitmapWithOverlay.addAll(FilterUtils.getLstBitmapWithOverlay(ThumbnailUtils.extractThumbnail(EditImageActivity.this.mPhotoEditorView.getCurrentBitmap(), 150, 150)));
            return null;
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(Void voidR) {
            EditImageActivity.this.mRvOverlays.setAdapter(new FilterViewAdapter(EditImageActivity.this.lstBitmapWithOverlay, EditImageActivity.this, EditImageActivity.this.getApplicationContext(), Arrays.asList(FilterUtils.OVERLAY_CONFIG)));
            EditImageActivity.this.slideDown(EditImageActivity.this.mRvTools);
            EditImageActivity.this.slideUp(EditImageActivity.this.overlayLayout);
            EditImageActivity.this.compareOverlay.setVisibility(0);
            EditImageActivity.this.overlayIntensity.setProgress(100);
            EditImageActivity.this.showLoading(false);
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity$ShowSplashDialog *//*
    class ShowSplashDialog extends AsyncTask<Void, List<Bitmap>, List<Bitmap>> {
        boolean isSplash;

        public ShowSplashDialog(boolean z) {
            this.isSplash = z;
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            EditImageActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public List<Bitmap> doInBackground(Void... voidArr) {
            Bitmap currentBitmap = EditImageActivity.this.mPhotoEditorView.getCurrentBitmap();
            ArrayList arrayList = new ArrayList();
            arrayList.add(currentBitmap);
            if (this.isSplash) {
                arrayList.add(FilterUtils.getBlackAndWhiteImageFromBitmap(currentBitmap));
            } else {
                arrayList.add(FilterUtils.getBlurImageFromBitmap(currentBitmap, 3.0f));
            }
            return arrayList;
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(List<Bitmap> list) {
            List<Bitmap> list2 = list;
            if (this.isSplash) {
                SplashDialog.show(EditImageActivity.this, list2.get(0), (Bitmap) null, list2.get(1), EditImageActivity.this, true);
            } else {
                SplashDialog.show(EditImageActivity.this, list2.get(0), list2.get(1), (Bitmap) null, EditImageActivity.this, false);
            }
            EditImageActivity.this.showLoading(false);
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity$SaveFilterAsBitmap *//*
    class SaveFilterAsBitmap extends AsyncTask<Void, Void, Bitmap> {
        SaveFilterAsBitmap() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            EditImageActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public Bitmap doInBackground(Void... voidArr) {
            final Bitmap[] bitmapArr = {null};
            EditImageActivity.this.mPhotoEditorView.saveGLSurfaceViewAsBitmap(new OnSaveBitmap() {
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
            EditImageActivity.this.mPhotoEditorView.setImageSource(bitmap);
            EditImageActivity.this.mPhotoEditorView.setFilterEffect("");
            EditImageActivity.this.showLoading(false);
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity$SaveStickerAsBitmap *//*
    class SaveStickerAsBitmap extends AsyncTask<Void, Void, Bitmap> {
        SaveStickerAsBitmap() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            EditImageActivity.this.mPhotoEditorView.getGLSurfaceView().setAlpha(0.0f);
            EditImageActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public Bitmap doInBackground(Void... voidArr) {
            final Bitmap[] bitmapArr = {null};
            while (bitmapArr[0] == null) {
                try {
                    EditImageActivity.this.mPhotoEditor.saveStickerAsBitmap(new OnSaveBitmap() {
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
                } catch (Exception unused) {
                }
            }
            return bitmapArr[0];
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(Bitmap bitmap) {
            EditImageActivity.this.mPhotoEditorView.setImageSource(bitmap);
            EditImageActivity.this.mPhotoEditorView.getStickers().clear();
            EditImageActivity.this.mPhotoEditorView.getGLSurfaceView().setAlpha(1.0f);
            EditImageActivity.this.showLoading(false);
            EditImageActivity.this.updateLayout();
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity$OnLoadBitmapFromUri *//*
    class OnLoadBitmapFromUri extends AsyncTask<String, Bitmap, Bitmap> {
        OnLoadBitmapFromUri() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            EditImageActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public Bitmap doInBackground(String... strArr) {
            try {
                boolean z = false;
                Uri fromFile = Uri.fromFile(new File(strArr[0]));
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(EditImageActivity.this.getContentResolver(), fromFile);
                int height = bitmap.getHeight();
                int width = bitmap.getWidth();
                if (width > height) {
                    z = true;
                }
                if (1920 < Math.min(height, width)) {
                    float f = !z ? 1920.0f / ((float) width) : 1920.0f / ((float) height);
                    bitmap = FileUtils.getResizedBitmap(bitmap, (int) (((float) width) * f), (int) (((float) height) * f));
                }
                Bitmap rotateBitmap = SystemUtil.rotateBitmap(bitmap, new ExifInterface(EditImageActivity.this.getContentResolver().openInputStream(fromFile)).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1));
                if (rotateBitmap != bitmap) {
                    bitmap.recycle();
                }
                return rotateBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(Bitmap bitmap) {
            EditImageActivity.this.mPhotoEditorView.setImageSource(bitmap);
            EditImageActivity.this.updateLayout();
        }
    }

    public void updateLayout() {
        this.mPhotoEditorView.postDelayed(new Runnable() {
            public void run() {
                try {
                    Display defaultDisplay = EditImageActivity.this.getWindowManager().getDefaultDisplay();
                    Point point = new Point();
                    defaultDisplay.getSize(point);
                    int i = point.x;
                    int height = EditImageActivity.this.wrapPhotoView.getHeight();
                    int i2 = EditImageActivity.this.mPhotoEditorView.getGLSurfaceView().getRenderViewport().width;
                    float f = (float) EditImageActivity.this.mPhotoEditorView.getGLSurfaceView().getRenderViewport().height;
                    float f2 = (float) i2;
                    if (((int) ((((float) i) * f) / f2)) <= height) {
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                        layoutParams.addRule(13);
                        EditImageActivity.this.mPhotoEditorView.setLayoutParams(layoutParams);
                        EditImageActivity.this.mPhotoEditorView.setVisibility(0);
                    } else {
                        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) ((((float) height) * f2) / f), -1);
                        layoutParams2.addRule(13);
                        EditImageActivity.this.mPhotoEditorView.setLayoutParams(layoutParams2);
                        EditImageActivity.this.mPhotoEditorView.setVisibility(0);
                    }
                } catch (Exception unused) {
                }
                EditImageActivity.this.showLoading(false);
            }
        }, 300);
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.EditImageActivity$SaveBitmapAsFile *//*
    class SaveBitmapAsFile extends AsyncTask<Void, String, String> {
        static *//* synthetic *//* void lambda$doInBackground$0(String str, Uri uri) {
        }

        SaveBitmapAsFile() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            EditImageActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public String doInBackground(Void... voidArr) {
            File saveBitmapAsFile = FileUtils.saveBitmapAsFile(EditImageActivity.this.mPhotoEditorView.getCurrentBitmap());
            try {
                MediaScannerConnection.scanFile(EditImageActivity.this.getApplicationContext(), new String[]{saveBitmapAsFile.getAbsolutePath()}, (String[]) null, C1196x8340cc8e.INSTANCE);
                return saveBitmapAsFile.getAbsolutePath();
            } catch (Exception unused) {
                return null;
            }
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(String str) {
            EditImageActivity.this.showLoading(false);
            if (str == null) {
                Toast.makeText(EditImageActivity.this.getApplicationContext(), "Oop! Something went wrong", Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent(EditImageActivity.this, SaveAndShareActivity.class);
            intent.putExtra("path", str);
            EditImageActivity.this.startActivity(intent);
        }
    }
*/}