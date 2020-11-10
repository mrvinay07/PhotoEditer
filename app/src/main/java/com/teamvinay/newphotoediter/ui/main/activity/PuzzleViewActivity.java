package com.teamvinay.newphotoediter.ui.main.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Display;
import android.view.LayoutInflater;
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
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
/*import com.ads.control.AdmobHelp;
import com.google.android.gms.ads.InterstitialAd;
import com.hold1.keyboardheightprovider.KeyboardHeightProvider;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.steelkiwi.cropiwa.AspectRatio;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lisa.studio.photoeditor.BaseActivity;
import lisa.studio.photoeditor.C1084R;
import lisa.studio.photoeditor.adapter.EditingToolsAdapter;
import lisa.studio.photoeditor.adapter.PieceToolsAdapter;
import lisa.studio.photoeditor.adapter.ToolType;
import lisa.studio.photoeditor.editor.filterscolor.FilterDialogFragment;
import lisa.studio.photoeditor.editor.filterscolor.FilterListener;
import lisa.studio.photoeditor.editor.filterscolor.FilterUtils;
import lisa.studio.photoeditor.editor.filterscolor.FilterViewAdapter;
import lisa.studio.photoeditor.editor.sticker.BitmapStickerIcon;
import lisa.studio.photoeditor.editor.sticker.DrawableSticker;
import lisa.studio.photoeditor.editor.sticker.Sticker;
import lisa.studio.photoeditor.editor.sticker.StickerView;
import lisa.studio.photoeditor.editor.sticker.TextSticker;
import lisa.studio.photoeditor.editor.sticker.event.AlignHorizontallyEvent;
import lisa.studio.photoeditor.editor.sticker.event.DeleteIconEvent;
import lisa.studio.photoeditor.editor.sticker.event.EditTextIconEvent;
import lisa.studio.photoeditor.editor.sticker.event.FlipHorizontallyEvent;
import lisa.studio.photoeditor.editor.sticker.event.ZoomIconEvent;
import lisa.studio.photoeditor.featuresfoto.addtext.AddTextProperties;
import lisa.studio.photoeditor.featuresfoto.addtext.TextEditorDialogFragment;
import lisa.studio.photoeditor.featuresfoto.crop.CropDialogFragment;
import lisa.studio.photoeditor.featuresfoto.crop.adapter.AspectRatioPreviewAdapter;
import lisa.studio.photoeditor.featuresfoto.picker.PhotoPicker;
import lisa.studio.photoeditor.featuresfoto.picker.utils.PermissionsUtils;
import lisa.studio.photoeditor.featuresfoto.puzzle.PuzzleLayout;
import lisa.studio.photoeditor.featuresfoto.puzzle.PuzzleLayoutParser;
import lisa.studio.photoeditor.featuresfoto.puzzle.PuzzlePiece;
import lisa.studio.photoeditor.featuresfoto.puzzle.PuzzleView;
import lisa.studio.photoeditor.featuresfoto.puzzle.adapter.PuzzleAdapter;
import lisa.studio.photoeditor.featuresfoto.puzzle.adapter.PuzzleBackgroundAdapter;
import lisa.studio.photoeditor.featuresfoto.puzzle.photopicker.activity.PickImageActivity;
import lisa.studio.photoeditor.featuresfoto.sticker.adapter.RecyclerTabLayout;
import lisa.studio.photoeditor.featuresfoto.sticker.adapter.StickerAdapter;
import lisa.studio.photoeditor.featuresfoto.sticker.adapter.TopTabAdapter;
import lisa.studio.photoeditor.util.AssetUtils;
import lisa.studio.photoeditor.util.FileUtils;
import lisa.studio.photoeditor.util.PuzzleUtils;
import lisa.studio.photoeditor.util.SharePreferenceUtil;
import lisa.studio.photoeditor.util.SystemUtil;*/
import org.wysaid.nativePort.CGENativeLibrary;

/* renamed from: lisa.studio.photoeditor.ui.main.activity.PuzzleViewActivity */
public class PuzzleViewActivity { /*extends BaseActivity implements EditingToolsAdapter.OnItemSelected, AspectRatioPreviewAdapter.OnNewSelectedListener, StickerAdapter.OnClickStickerListener, PuzzleBackgroundAdapter.BackgroundChangeListener, FilterListener, CropDialogFragment.OnCropPhoto, FilterDialogFragment.OnFilterSavePhoto, PieceToolsAdapter.OnPieceFuncItemSelected, PuzzleAdapter.OnItemClickListener {
    private static PuzzleViewActivity instance;
    public static PuzzleViewActivity puzzle;
    *//* access modifiers changed from: private *//*
    public ImageView addNewSticker;
    *//* access modifiers changed from: private *//*
    public ImageView addNewText;
    *//* access modifiers changed from: private *//*
    public ConstraintLayout changeBackgroundLayout;
    private LinearLayout changeBorder;
    *//* access modifiers changed from: private *//*
    public ConstraintLayout changeLayoutLayout;
    *//* access modifiers changed from: private *//*
    public AspectRatio currentAspect;
    *//* access modifiers changed from: private *//*
    public PuzzleBackgroundAdapter.SquareView currentBackgroundState;
    *//* access modifiers changed from: private *//*
    public ToolType currentMode;
    private int deviceHeight = 0;
    *//* access modifiers changed from: private *//*
    public int deviceWidth = 0;
    *//* access modifiers changed from: private *//*
    public ConstraintLayout filterLayout;
    private KeyboardHeightProvider keyboardHeightProvider;
    private RelativeLayout loadingView;
    *//* access modifiers changed from: private *//*
    public List<Bitmap> lstBitmapWithFilter = new ArrayList();
    *//* access modifiers changed from: private *//*
    public List<Drawable> lstOriginalDrawable = new ArrayList();
    *//* access modifiers changed from: private *//*
    public List<String> lstPaths;
    private EditingToolsAdapter mEditingToolsAdapter = new EditingToolsAdapter(this, true);
    private InterstitialAd mInterstitialAd;
    public CGENativeLibrary.LoadImageCallback mLoadImageCallback = new CGENativeLibrary.LoadImageCallback() {
        public Bitmap loadImage(String str, Object obj) {
            try {
                return BitmapFactory.decodeStream(PuzzleViewActivity.this.getAssets().open(str));
            } catch (IOException unused) {
                return null;
            }
        }

        public void loadImageOK(Bitmap bitmap, Object obj) {
            bitmap.recycle();
        }
    };
    *//* access modifiers changed from: private *//*
    public RecyclerView mRvTools;
    private ConstraintLayout mainActivity;
    public View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case C1084R.C1087id.imgCloseBackground:
                case C1084R.C1087id.imgCloseFilter:
                case C1084R.C1087id.imgCloseLayout:
                case C1084R.C1087id.imgCloseSticker:
                case C1084R.C1087id.imgCloseText:
                    PuzzleViewActivity.this.onBackPressed();
                    return;
                case C1084R.C1087id.imgSaveBackground:
                    PuzzleViewActivity.this.slideDown(PuzzleViewActivity.this.changeBackgroundLayout);
                    PuzzleViewActivity.this.slideUp(PuzzleViewActivity.this.mRvTools);
                    PuzzleViewActivity.this.slideDownSaveView();
                    PuzzleViewActivity.this.showDownFunction();
                    PuzzleViewActivity.this.puzzleView.setLocked(true);
                    PuzzleViewActivity.this.puzzleView.setTouchEnable(true);
                    if (PuzzleViewActivity.this.puzzleView.getBackgroundResourceMode() == 0) {
                        PuzzleViewActivity.this.currentBackgroundState.isColor = true;
                        PuzzleViewActivity.this.currentBackgroundState.isBitmap = false;
                        PuzzleViewActivity.this.currentBackgroundState.drawableId = ((ColorDrawable) PuzzleViewActivity.this.puzzleView.getBackground()).getColor();
                        PuzzleViewActivity.this.currentBackgroundState.drawable = null;
                    } else if (PuzzleViewActivity.this.puzzleView.getBackgroundResourceMode() == 1) {
                        PuzzleViewActivity.this.currentBackgroundState.isColor = false;
                        PuzzleViewActivity.this.currentBackgroundState.isBitmap = false;
                        PuzzleViewActivity.this.currentBackgroundState.drawable = PuzzleViewActivity.this.puzzleView.getBackground();
                    } else {
                        PuzzleViewActivity.this.currentBackgroundState.isColor = false;
                        PuzzleViewActivity.this.currentBackgroundState.isBitmap = true;
                        PuzzleViewActivity.this.currentBackgroundState.drawable = PuzzleViewActivity.this.puzzleView.getBackground();
                    }
                    ToolType unused = PuzzleViewActivity.this.currentMode = ToolType.NONE;
                    return;
                case C1084R.C1087id.imgSaveFilter:
                    PuzzleViewActivity.this.slideDown(PuzzleViewActivity.this.filterLayout);
                    PuzzleViewActivity.this.slideUp(PuzzleViewActivity.this.mRvTools);
                    ToolType unused2 = PuzzleViewActivity.this.currentMode = ToolType.NONE;
                    PuzzleViewActivity.this.slideDownSaveView();
                    return;
                case C1084R.C1087id.imgSaveLayout:
                    PuzzleViewActivity.this.slideUp(PuzzleViewActivity.this.mRvTools);
                    PuzzleViewActivity.this.slideDown(PuzzleViewActivity.this.changeLayoutLayout);
                    PuzzleViewActivity.this.slideDownSaveView();
                    PuzzleViewActivity.this.showDownFunction();
                    PuzzleLayout unused3 = PuzzleViewActivity.this.puzzleLayout = PuzzleViewActivity.this.puzzleView.getPuzzleLayout();
                    float unused4 = PuzzleViewActivity.this.pieceBorderRadius = PuzzleViewActivity.this.puzzleView.getPieceRadian();
                    float unused5 = PuzzleViewActivity.this.piecePadding = PuzzleViewActivity.this.puzzleView.getPiecePadding();
                    PuzzleViewActivity.this.puzzleView.setLocked(true);
                    PuzzleViewActivity.this.puzzleView.setTouchEnable(true);
                    AspectRatio unused6 = PuzzleViewActivity.this.currentAspect = PuzzleViewActivity.this.puzzleView.getAspectRatio();
                    ToolType unused7 = PuzzleViewActivity.this.currentMode = ToolType.NONE;
                    return;
                case C1084R.C1087id.imgSaveSticker:
                    PuzzleViewActivity.this.puzzleView.setHandlingSticker((Sticker) null);
                    PuzzleViewActivity.this.stickerAlpha.setVisibility(8);
                    PuzzleViewActivity.this.addNewSticker.setVisibility(8);
                    PuzzleViewActivity.this.slideUp(PuzzleViewActivity.this.wrapStickerList);
                    PuzzleViewActivity.this.slideDown(PuzzleViewActivity.this.stickerLayout);
                    PuzzleViewActivity.this.slideUp(PuzzleViewActivity.this.mRvTools);
                    PuzzleViewActivity.this.slideDownSaveView();
                    PuzzleViewActivity.this.puzzleView.setLocked(true);
                    PuzzleViewActivity.this.puzzleView.setTouchEnable(true);
                    ToolType unused8 = PuzzleViewActivity.this.currentMode = ToolType.NONE;
                    return;
                case C1084R.C1087id.imgSaveText:
                    PuzzleViewActivity.this.puzzleView.setHandlingSticker((Sticker) null);
                    PuzzleViewActivity.this.puzzleView.setLocked(true);
                    PuzzleViewActivity.this.addNewText.setVisibility(8);
                    PuzzleViewActivity.this.slideDown(PuzzleViewActivity.this.textLayout);
                    PuzzleViewActivity.this.slideUp(PuzzleViewActivity.this.mRvTools);
                    PuzzleViewActivity.this.slideDownSaveView();
                    PuzzleViewActivity.this.puzzleView.setLocked(true);
                    PuzzleViewActivity.this.puzzleView.setTouchEnable(true);
                    ToolType unused9 = PuzzleViewActivity.this.currentMode = ToolType.NONE;
                    return;
                case C1084R.C1087id.tv_blur:
                    PuzzleViewActivity.this.selectBackgroundBlur();
                    return;
                case C1084R.C1087id.tv_change_border:
                    PuzzleViewActivity.this.selectBorderTool();
                    return;
                case C1084R.C1087id.tv_change_layout:
                    PuzzleViewActivity.this.selectLayoutTool();
                    return;
                case C1084R.C1087id.tv_change_ratio:
                    PuzzleViewActivity.this.selectRadiusTool();
                    return;
                case C1084R.C1087id.tv_color:
                    PuzzleViewActivity.this.selectBackgroundColorTab();
                    return;
                case C1084R.C1087id.tv_radian:
                    PuzzleViewActivity.this.selectBackgroundGradientTab();
                    return;
                default:
                    return;
            }
        }
    };
    public SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            switch (seekBar.getId()) {
                case C1084R.C1087id.sk_border:
                    PuzzleViewActivity.this.puzzleView.setPiecePadding((float) i);
                    break;
                case C1084R.C1087id.sk_border_radius:
                    PuzzleViewActivity.this.puzzleView.setPieceRadian((float) i);
                    break;
            }
            PuzzleViewActivity.this.puzzleView.invalidate();
        }
    };
    StickerView.OnStickerOperationListener onStickerOperationListener = new StickerView.OnStickerOperationListener() {
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
            PuzzleViewActivity.this.stickerAlpha.setVisibility(0);
            PuzzleViewActivity.this.stickerAlpha.setProgress(sticker.getAlpha());
        }

        public void onStickerClicked(@NonNull Sticker sticker) {
            PuzzleViewActivity.this.stickerAlpha.setVisibility(0);
            PuzzleViewActivity.this.stickerAlpha.setProgress(sticker.getAlpha());
        }

        public void onStickerDeleted(@NonNull Sticker sticker) {
            PuzzleViewActivity.this.stickerAlpha.setVisibility(8);
        }

        public void onStickerTouchOutside() {
            PuzzleViewActivity.this.stickerAlpha.setVisibility(8);
        }

        public void onStickerDoubleTapped(@NonNull Sticker sticker) {
            if (sticker instanceof TextSticker) {
                sticker.setShow(false);
                PuzzleViewActivity.this.puzzleView.setHandlingSticker((Sticker) null);
                TextEditorDialogFragment unused = PuzzleViewActivity.this.textEditorDialogFragment = TextEditorDialogFragment.show(PuzzleViewActivity.this, ((TextSticker) sticker).getAddTextProperties());
                TextEditorDialogFragment.TextEditor unused2 = PuzzleViewActivity.this.textEditor = new TextEditorDialogFragment.TextEditor() {
                    public void onDone(AddTextProperties addTextProperties) {
                        PuzzleViewActivity.this.puzzleView.getStickers().remove(PuzzleViewActivity.this.puzzleView.getLastHandlingSticker());
                        PuzzleViewActivity.this.puzzleView.addSticker(new TextSticker(PuzzleViewActivity.this, addTextProperties));
                    }

                    public void onBackButton() {
                        PuzzleViewActivity.this.puzzleView.showLastHandlingSticker();
                    }
                };
                PuzzleViewActivity.this.textEditorDialogFragment.setOnTextEditorListener(PuzzleViewActivity.this.textEditor);
            }
        }
    };
    *//* access modifiers changed from: private *//*
    public float pieceBorderRadius;
    *//* access modifiers changed from: private *//*
    public float piecePadding;
    private PieceToolsAdapter pieceToolsAdapter = new PieceToolsAdapter(this);
    *//* access modifiers changed from: private *//*
    public PuzzleLayout puzzleLayout;
    private RecyclerView puzzleList;
    *//* access modifiers changed from: private *//*
    public PuzzleView puzzleView;
    private RecyclerView radiusLayout;
    private RecyclerView rvBackgroundBlur;
    private RecyclerView rvBackgroundColor;
    private RecyclerView rvBackgroundGradient;
    *//* access modifiers changed from: private *//*
    public RecyclerView rvFilterView;
    *//* access modifiers changed from: private *//*
    public RecyclerView rvPieceControl;
    private Button saveBitmap;
    private ConstraintLayout saveControl;
    private SeekBar sbChangeBorderRadius;
    private SeekBar sbChangeBorderSize;
    *//* access modifiers changed from: private *//*
    public SeekBar stickerAlpha;
    *//* access modifiers changed from: private *//*
    public ConstraintLayout stickerLayout;
    *//* access modifiers changed from: private *//*
    public List<Target> targets = new ArrayList();
    *//* access modifiers changed from: private *//*
    public TextEditorDialogFragment.TextEditor textEditor;
    *//* access modifiers changed from: private *//*
    public TextEditorDialogFragment textEditorDialogFragment;
    *//* access modifiers changed from: private *//*
    public ConstraintLayout textLayout;
    private TextView tvChangeBackgroundBlur;
    private TextView tvChangeBackgroundColor;
    private TextView tvChangeBackgroundGradient;
    private TextView tvChangeBorder;
    private TextView tvChangeLayout;
    private TextView tvChangeRatio;
    private ConstraintLayout wrapPuzzleView;
    *//* access modifiers changed from: private *//*
    public LinearLayout wrapStickerList;

    public static PuzzleViewActivity getInstance() {
        return instance;
    }

    *//* access modifiers changed from: protected *//*
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) C1084R.C1089layout.puzzle_layout);
        if (Build.VERSION.SDK_INT < 26) {
            getWindow().setSoftInputMode(48);
        }
        this.deviceWidth = getResources().getDisplayMetrics().widthPixels;
        this.deviceHeight = getResources().getDisplayMetrics().heightPixels;
        this.loadingView = (RelativeLayout) findViewById(C1084R.C1087id.loadingView);
        this.puzzleView = (PuzzleView) findViewById(C1084R.C1087id.puzzle_view);
        this.wrapPuzzleView = (ConstraintLayout) findViewById(C1084R.C1087id.wrapPuzzleView);
        this.filterLayout = (ConstraintLayout) findViewById(C1084R.C1087id.filterLayout);
        this.rvFilterView = (RecyclerView) findViewById(C1084R.C1087id.rvFilterView);
        this.mRvTools = (RecyclerView) findViewById(C1084R.C1087id.rvConstraintTools);
        this.mRvTools.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.mRvTools.setAdapter(this.mEditingToolsAdapter);
        this.rvPieceControl = (RecyclerView) findViewById(C1084R.C1087id.rvPieceControl);
        this.rvPieceControl.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.rvPieceControl.setAdapter(this.pieceToolsAdapter);
        this.sbChangeBorderSize = (SeekBar) findViewById(C1084R.C1087id.sk_border);
        this.sbChangeBorderSize.setOnSeekBarChangeListener(this.onSeekBarChangeListener);
        this.sbChangeBorderRadius = (SeekBar) findViewById(C1084R.C1087id.sk_border_radius);
        this.sbChangeBorderRadius.setOnSeekBarChangeListener(this.onSeekBarChangeListener);
        this.lstPaths = getIntent().getStringArrayListExtra(PickImageActivity.KEY_DATA_RESULT);
        this.puzzleLayout = PuzzleUtils.getPuzzleLayouts(this.lstPaths.size()).get(0);
        this.puzzleView.setPuzzleLayout(this.puzzleLayout);
        this.puzzleView.setTouchEnable(true);
        this.puzzleView.setNeedDrawLine(false);
        this.puzzleView.setNeedDrawOuterLine(false);
        this.puzzleView.setLineSize(4);
        this.puzzleView.setPiecePadding(6.0f);
        this.puzzleView.setPieceRadian(15.0f);
        this.puzzleView.setLineColor(getResources().getColor(C1084R.C1085color.white));
        this.puzzleView.setSelectedLineColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.puzzleView.setHandleBarColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.puzzleView.setAnimateDuration(300);
        this.puzzleView.setOnPieceSelectedListener(new PuzzleView.OnPieceSelectedListener() {
            public void onPieceSelected(PuzzlePiece puzzlePiece, int i) {
                PuzzleViewActivity.this.slideDown(PuzzleViewActivity.this.mRvTools);
                PuzzleViewActivity.this.slideUp(PuzzleViewActivity.this.rvPieceControl);
                PuzzleViewActivity.this.slideUpSaveView();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) PuzzleViewActivity.this.rvPieceControl.getLayoutParams();
                layoutParams.bottomMargin = SystemUtil.dpToPx(PuzzleViewActivity.this.getApplicationContext(), 10);
                PuzzleViewActivity.this.rvPieceControl.setLayoutParams(layoutParams);
                ToolType unused = PuzzleViewActivity.this.currentMode = ToolType.PIECE;
            }
        });
        this.puzzleView.setOnPieceUnSelectedListener(new PuzzleView.OnPieceUnSelectedListener() {
            public final void onPieceUnSelected() {
                PuzzleViewActivity.lambda$onCreate$0(PuzzleViewActivity.this);
            }
        });
        this.saveControl = (ConstraintLayout) findViewById(C1084R.C1087id.saveControl);
        this.puzzleView.post(new Runnable() {
            public final void run() {
                PuzzleViewActivity.this.loadPhoto();
            }
        });
        findViewById(C1084R.C1087id.exitEditMode).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AdmobHelp.getInstance().showInterstitialAd(new AdmobHelp.AdCloseListener() {
                    public void onAdClosed() {
                        PuzzleViewActivity.this.finish();
                    }
                }, PuzzleViewActivity.this);
            }
        });
        findViewById(C1084R.C1087id.imgCloseLayout).setOnClickListener(this.onClickListener);
        findViewById(C1084R.C1087id.imgSaveLayout).setOnClickListener(this.onClickListener);
        findViewById(C1084R.C1087id.imgCloseSticker).setOnClickListener(this.onClickListener);
        findViewById(C1084R.C1087id.imgCloseFilter).setOnClickListener(this.onClickListener);
        findViewById(C1084R.C1087id.imgCloseBackground).setOnClickListener(this.onClickListener);
        findViewById(C1084R.C1087id.imgSaveSticker).setOnClickListener(this.onClickListener);
        findViewById(C1084R.C1087id.imgCloseText).setOnClickListener(this.onClickListener);
        findViewById(C1084R.C1087id.imgSaveText).setOnClickListener(this.onClickListener);
        findViewById(C1084R.C1087id.imgSaveFilter).setOnClickListener(this.onClickListener);
        findViewById(C1084R.C1087id.imgSaveBackground).setOnClickListener(this.onClickListener);
        this.changeLayoutLayout = (ConstraintLayout) findViewById(C1084R.C1087id.changeLayoutLayout);
        this.changeBorder = (LinearLayout) findViewById(C1084R.C1087id.change_border);
        this.tvChangeLayout = (TextView) findViewById(C1084R.C1087id.tv_change_layout);
        this.tvChangeLayout.setOnClickListener(this.onClickListener);
        this.tvChangeBorder = (TextView) findViewById(C1084R.C1087id.tv_change_border);
        this.tvChangeBorder.setOnClickListener(this.onClickListener);
        this.tvChangeRatio = (TextView) findViewById(C1084R.C1087id.tv_change_ratio);
        this.tvChangeRatio.setOnClickListener(this.onClickListener);
        this.tvChangeBackgroundColor = (TextView) findViewById(C1084R.C1087id.tv_color);
        this.tvChangeBackgroundColor.setOnClickListener(this.onClickListener);
        this.tvChangeBackgroundGradient = (TextView) findViewById(C1084R.C1087id.tv_radian);
        this.tvChangeBackgroundGradient.setOnClickListener(this.onClickListener);
        this.tvChangeBackgroundBlur = (TextView) findViewById(C1084R.C1087id.tv_blur);
        this.tvChangeBackgroundBlur.setOnClickListener(this.onClickListener);
        PuzzleAdapter puzzleAdapter = new PuzzleAdapter();
        this.puzzleList = (RecyclerView) findViewById(C1084R.C1087id.puzzleList);
        this.puzzleList.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.puzzleList.setAdapter(puzzleAdapter);
        puzzleAdapter.refreshData(PuzzleUtils.getPuzzleLayouts(this.lstPaths.size()), (List<Bitmap>) null);
        puzzleAdapter.setOnItemClickListener(this);
        AspectRatioPreviewAdapter aspectRatioPreviewAdapter = new AspectRatioPreviewAdapter(true);
        aspectRatioPreviewAdapter.setListener(this);
        this.radiusLayout = (RecyclerView) findViewById(C1084R.C1087id.radioLayout);
        this.radiusLayout.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.radiusLayout.setAdapter(aspectRatioPreviewAdapter);
        this.textLayout = (ConstraintLayout) findViewById(C1084R.C1087id.textControl);
        this.addNewText = (ImageView) findViewById(C1084R.C1087id.addNewText);
        this.addNewText.setVisibility(8);
        this.addNewText.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PuzzleViewActivity.lambda$onCreate$2(PuzzleViewActivity.this, view);
            }
        });
        this.wrapStickerList = (LinearLayout) findViewById(C1084R.C1087id.wrapStickerList);
        ViewPager viewPager = (ViewPager) findViewById(C1084R.C1087id.sticker_viewpaper);
        this.stickerLayout = (ConstraintLayout) findViewById(C1084R.C1087id.stickerLayout);
        this.stickerAlpha = (SeekBar) findViewById(C1084R.C1087id.stickerAlpha);
        this.stickerAlpha.setVisibility(8);
        this.stickerAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                Sticker currentSticker = PuzzleViewActivity.this.puzzleView.getCurrentSticker();
                if (currentSticker != null) {
                    currentSticker.setAlpha(i);
                }
            }
        });
        this.saveBitmap = (Button) findViewById(C1084R.C1087id.save);
        this.saveBitmap.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PuzzleViewActivity.lambda$onCreate$3(PuzzleViewActivity.this, view);
            }
        });
        this.addNewSticker = (ImageView) findViewById(C1084R.C1087id.addNewSticker);
        this.addNewSticker.setVisibility(8);
        this.addNewSticker.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PuzzleViewActivity.lambda$onCreate$4(PuzzleViewActivity.this, view);
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
        this.puzzleView.setIcons(Arrays.asList(new BitmapStickerIcon[]{bitmapStickerIcon, bitmapStickerIcon2, bitmapStickerIcon3, bitmapStickerIcon5, bitmapStickerIcon4, bitmapStickerIcon6}));
        this.puzzleView.setConstrained(true);
        this.puzzleView.setOnStickerOperationListener(this.onStickerOperationListener);
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
                View inflate = LayoutInflater.from(PuzzleViewActivity.this.getBaseContext()).inflate(C1084R.C1089layout.sticker_items, (ViewGroup) null, false);
                RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C1084R.C1087id.f595rv);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(PuzzleViewActivity.this.getApplicationContext(), 4));
                switch (i) {
                    case 0:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstHeardes(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 1:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstEmoj(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 2:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstTexts(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 3:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstOthers(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 4:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstGiddy(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 5:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstGlasses(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 6:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstTies(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 7:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstCatFaces(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 8:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstCkeeks(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 9:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstDiadems(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 10:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstEyes(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 11:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstMuscle(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                    case 12:
                        recyclerView.setAdapter(new StickerAdapter(PuzzleViewActivity.this.getApplicationContext(), AssetUtils.lstTatoos(), PuzzleViewActivity.this.deviceWidth, PuzzleViewActivity.this));
                        break;
                }
                viewGroup.addView(inflate);
                return inflate;
            }
        });
        RecyclerTabLayout recyclerTabLayout = (RecyclerTabLayout) findViewById(C1084R.C1087id.recycler_tab_layout);
        recyclerTabLayout.setUpWithAdapter(new TopTabAdapter(viewPager, getApplicationContext()));
        recyclerTabLayout.setPositionThreshold(0.5f);
        recyclerTabLayout.setBackgroundColor(getResources().getColor(C1084R.C1085color.basic_white));
        this.changeBackgroundLayout = (ConstraintLayout) findViewById(C1084R.C1087id.changeBackgroundLayout);
        this.mainActivity = (ConstraintLayout) findViewById(C1084R.C1087id.puzzle_layout);
        this.changeLayoutLayout.setAlpha(0.0f);
        this.stickerLayout.setAlpha(0.0f);
        this.textLayout.setAlpha(0.0f);
        this.filterLayout.setAlpha(0.0f);
        this.changeBackgroundLayout.setAlpha(0.0f);
        this.rvPieceControl.setAlpha(0.0f);
        this.mainActivity.post(new Runnable() {
            public final void run() {
                PuzzleViewActivity.lambda$onCreate$5(PuzzleViewActivity.this);
            }
        });
        new Handler().postDelayed(new Runnable() {
            public final void run() {
                PuzzleViewActivity.lambda$onCreate$6(PuzzleViewActivity.this);
            }
        }, 1000);
        SharePreferenceUtil.setHeightOfKeyboard(getApplicationContext(), 0);
        this.keyboardHeightProvider = new KeyboardHeightProvider(this);
        this.keyboardHeightProvider.addKeyboardListener(new KeyboardHeightProvider.KeyboardListener() {
            public final void onHeightChanged(int i) {
                PuzzleViewActivity.lambda$onCreate$7(PuzzleViewActivity.this, i);
            }
        });
        showLoading(false);
        this.currentBackgroundState = new PuzzleBackgroundAdapter.SquareView(Color.parseColor("#ffffff"), "", true);
        this.rvBackgroundColor = (RecyclerView) findViewById(C1084R.C1087id.colorList);
        this.rvBackgroundColor.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        this.rvBackgroundColor.setHasFixedSize(true);
        this.rvBackgroundColor.setAdapter(new PuzzleBackgroundAdapter(getApplicationContext(), this));
        this.rvBackgroundGradient = (RecyclerView) findViewById(C1084R.C1087id.radianList);
        this.rvBackgroundGradient.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        this.rvBackgroundGradient.setHasFixedSize(true);
        this.rvBackgroundGradient.setAdapter(new PuzzleBackgroundAdapter(getApplicationContext(), (PuzzleBackgroundAdapter.BackgroundChangeListener) this, true));
        this.rvBackgroundBlur = (RecyclerView) findViewById(C1084R.C1087id.backgroundList);
        this.rvBackgroundBlur.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        this.rvBackgroundBlur.setHasFixedSize(true);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.puzzleView.getLayoutParams();
        layoutParams.height = point.x;
        layoutParams.width = point.x;
        this.puzzleView.setLayoutParams(layoutParams);
        this.currentAspect = new AspectRatio(1, 1);
        this.puzzleView.setAspectRatio(new AspectRatio(1, 1));
        puzzle = this;
        this.currentMode = ToolType.NONE;
        CGENativeLibrary.setLoadImageCallback(this.mLoadImageCallback, (Object) null);
        instance = this;
    }

    public static *//* synthetic *//* void lambda$onCreate$0(PuzzleViewActivity puzzleViewActivity) {
        puzzleViewActivity.slideDown(puzzleViewActivity.rvPieceControl);
        puzzleViewActivity.slideUp(puzzleViewActivity.mRvTools);
        puzzleViewActivity.slideDownSaveView();
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) puzzleViewActivity.rvPieceControl.getLayoutParams();
        layoutParams.bottomMargin = 0;
        puzzleViewActivity.rvPieceControl.setLayoutParams(layoutParams);
        puzzleViewActivity.currentMode = ToolType.NONE;
    }

    public static *//* synthetic *//* void lambda$onCreate$2(PuzzleViewActivity puzzleViewActivity, View view) {
        puzzleViewActivity.puzzleView.setHandlingSticker((Sticker) null);
        puzzleViewActivity.openTextFragment();
    }

    public static *//* synthetic *//* void lambda$onCreate$3(PuzzleViewActivity puzzleViewActivity, View view) {
        if (PermissionsUtils.checkWriteStoragePermission((Activity) puzzleViewActivity)) {
            Bitmap createBitmap = FileUtils.createBitmap(puzzleViewActivity.puzzleView, 1920);
            Bitmap createBitmap2 = puzzleViewActivity.puzzleView.createBitmap();
            new SavePuzzleAsFile().execute(new Bitmap[]{createBitmap, createBitmap2});
        }
    }

    public static *//* synthetic *//* void lambda$onCreate$4(PuzzleViewActivity puzzleViewActivity, View view) {
        puzzleViewActivity.addNewSticker.setVisibility(8);
        puzzleViewActivity.slideUp(puzzleViewActivity.wrapStickerList);
    }

    public static *//* synthetic *//* void lambda$onCreate$5(PuzzleViewActivity puzzleViewActivity) {
        puzzleViewActivity.slideDown(puzzleViewActivity.changeLayoutLayout);
        puzzleViewActivity.slideDown(puzzleViewActivity.stickerLayout);
        puzzleViewActivity.slideDown(puzzleViewActivity.textLayout);
        puzzleViewActivity.slideDown(puzzleViewActivity.changeBackgroundLayout);
        puzzleViewActivity.slideDown(puzzleViewActivity.filterLayout);
        puzzleViewActivity.slideDown(puzzleViewActivity.rvPieceControl);
    }

    public static *//* synthetic *//* void lambda$onCreate$6(PuzzleViewActivity puzzleViewActivity) {
        puzzleViewActivity.changeLayoutLayout.setAlpha(1.0f);
        puzzleViewActivity.stickerLayout.setAlpha(1.0f);
        puzzleViewActivity.textLayout.setAlpha(1.0f);
        puzzleViewActivity.filterLayout.setAlpha(1.0f);
        puzzleViewActivity.changeBackgroundLayout.setAlpha(1.0f);
        puzzleViewActivity.rvPieceControl.setAlpha(1.0f);
    }

    public static *//* synthetic *//* void lambda$onCreate$7(PuzzleViewActivity puzzleViewActivity, int i) {
        if (i <= 0) {
            SharePreferenceUtil.setHeightOfNotch(puzzleViewActivity.getApplicationContext(), -i);
        } else if (puzzleViewActivity.textEditorDialogFragment != null) {
            puzzleViewActivity.textEditorDialogFragment.updateAddTextBottomToolbarHeight(SharePreferenceUtil.getHeightOfNotch(puzzleViewActivity.getApplicationContext()) + i);
            SharePreferenceUtil.setHeightOfKeyboard(puzzleViewActivity.getApplicationContext(), i + SharePreferenceUtil.getHeightOfNotch(puzzleViewActivity.getApplicationContext()));
        }
    }

    private void openTextFragment() {
        this.textEditorDialogFragment = TextEditorDialogFragment.show(this);
        this.textEditor = new TextEditorDialogFragment.TextEditor() {
            public void onDone(AddTextProperties addTextProperties) {
                PuzzleViewActivity.this.puzzleView.addSticker(new TextSticker(PuzzleViewActivity.this.getApplicationContext(), addTextProperties));
            }

            public void onBackButton() {
                if (PuzzleViewActivity.this.puzzleView.getStickers().isEmpty()) {
                    PuzzleViewActivity.this.onBackPressed();
                }
            }
        };
        this.textEditorDialogFragment.setOnTextEditorListener(this.textEditor);
    }

    public void isPermissionGranted(boolean z, String str) {
        if (z) {
            Bitmap createBitmap = FileUtils.createBitmap(this.puzzleView, 1920);
            Bitmap createBitmap2 = this.puzzleView.createBitmap();
            new SavePuzzleAsFile().execute(new Bitmap[]{createBitmap, createBitmap2});
        }
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

    public void selectBackgroundBlur() {
        ArrayList arrayList = new ArrayList();
        for (PuzzlePiece drawable : this.puzzleView.getPuzzlePieces()) {
            arrayList.add(drawable.getDrawable());
        }
        PuzzleBackgroundAdapter puzzleBackgroundAdapter = new PuzzleBackgroundAdapter(getApplicationContext(), (PuzzleBackgroundAdapter.BackgroundChangeListener) this, (List<Drawable>) arrayList);
        puzzleBackgroundAdapter.setSelectedSquareIndex(-1);
        this.rvBackgroundBlur.setAdapter(puzzleBackgroundAdapter);
        this.rvBackgroundBlur.setVisibility(0);
        this.tvChangeBackgroundBlur.setBackgroundResource(C1084R.C1086drawable.border_bottom);
        this.tvChangeBackgroundBlur.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.rvBackgroundGradient.setVisibility(8);
        this.tvChangeBackgroundGradient.setBackgroundResource(0);
        this.tvChangeBackgroundGradient.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.rvBackgroundColor.setVisibility(8);
        this.tvChangeBackgroundColor.setBackgroundResource(0);
        this.tvChangeBackgroundColor.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
    }

    public void selectBackgroundColorTab() {
        this.rvBackgroundColor.setVisibility(0);
        this.tvChangeBackgroundColor.setBackgroundResource(C1084R.C1086drawable.border_bottom);
        this.tvChangeBackgroundColor.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.rvBackgroundColor.scrollToPosition(0);
        ((PuzzleBackgroundAdapter) this.rvBackgroundColor.getAdapter()).setSelectedSquareIndex(-1);
        this.rvBackgroundColor.getAdapter().notifyDataSetChanged();
        this.rvBackgroundGradient.setVisibility(8);
        this.tvChangeBackgroundGradient.setBackgroundResource(0);
        this.tvChangeBackgroundGradient.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.rvBackgroundBlur.setVisibility(8);
        this.tvChangeBackgroundBlur.setBackgroundResource(0);
        this.tvChangeBackgroundBlur.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
    }

    public void selectBackgroundGradientTab() {
        this.rvBackgroundGradient.setVisibility(0);
        this.tvChangeBackgroundGradient.setBackgroundResource(C1084R.C1086drawable.border_bottom);
        this.tvChangeBackgroundGradient.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.rvBackgroundGradient.scrollToPosition(0);
        ((PuzzleBackgroundAdapter) this.rvBackgroundGradient.getAdapter()).setSelectedSquareIndex(-1);
        this.rvBackgroundGradient.getAdapter().notifyDataSetChanged();
        this.rvBackgroundColor.setVisibility(8);
        this.tvChangeBackgroundColor.setBackgroundResource(0);
        this.tvChangeBackgroundColor.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.rvBackgroundBlur.setVisibility(8);
        this.tvChangeBackgroundBlur.setBackgroundResource(0);
        this.tvChangeBackgroundBlur.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
    }

    *//* access modifiers changed from: private *//*
    public void selectLayoutTool() {
        this.puzzleList.setVisibility(0);
        this.tvChangeLayout.setBackgroundResource(C1084R.C1086drawable.border_bottom);
        this.tvChangeLayout.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.changeBorder.setVisibility(8);
        this.tvChangeBorder.setBackgroundResource(0);
        this.tvChangeBorder.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.radiusLayout.setVisibility(8);
        this.tvChangeRatio.setBackgroundResource(0);
        this.tvChangeRatio.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
    }

    *//* access modifiers changed from: private *//*
    public void selectRadiusTool() {
        this.radiusLayout.setVisibility(0);
        this.tvChangeRatio.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.tvChangeRatio.setBackgroundResource(C1084R.C1086drawable.border_bottom);
        this.puzzleList.setVisibility(8);
        this.tvChangeLayout.setBackgroundResource(0);
        this.tvChangeLayout.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.changeBorder.setVisibility(8);
        this.tvChangeBorder.setBackgroundResource(0);
        this.tvChangeBorder.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
    }

    *//* access modifiers changed from: private *//*
    public void selectBorderTool() {
        this.changeBorder.setVisibility(0);
        this.tvChangeBorder.setBackgroundResource(C1084R.C1086drawable.border_bottom);
        this.tvChangeBorder.setTextColor(getResources().getColor(C1084R.C1085color.colorAccent));
        this.puzzleList.setVisibility(8);
        this.tvChangeLayout.setBackgroundResource(0);
        this.tvChangeLayout.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.radiusLayout.setVisibility(8);
        this.tvChangeRatio.setBackgroundResource(0);
        this.tvChangeRatio.setTextColor(getResources().getColor(C1084R.C1085color.text_color_edit));
        this.sbChangeBorderRadius.setProgress((int) this.puzzleView.getPieceRadian());
        this.sbChangeBorderSize.setProgress((int) this.puzzleView.getPiecePadding());
    }

    private void showUpFunction(View view) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.mainActivity);
        constraintSet.connect(this.wrapPuzzleView.getId(), 3, 0, 3, 0);
        constraintSet.connect(this.wrapPuzzleView.getId(), 1, this.mainActivity.getId(), 1, 0);
        ConstraintSet constraintSet2 = constraintSet;
        constraintSet2.connect(this.wrapPuzzleView.getId(), 4, view.getId(), 3, 0);
        constraintSet2.connect(this.wrapPuzzleView.getId(), 2, this.mainActivity.getId(), 2, 0);
        constraintSet.applyTo(this.mainActivity);
    }

    *//* access modifiers changed from: private *//*
    public void showDownFunction() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.mainActivity);
        constraintSet.connect(this.wrapPuzzleView.getId(), 3, 0, 3, 0);
        constraintSet.connect(this.wrapPuzzleView.getId(), 1, this.mainActivity.getId(), 1, 0);
        constraintSet.connect(this.wrapPuzzleView.getId(), 4, this.mRvTools.getId(), 3, 0);
        constraintSet.connect(this.wrapPuzzleView.getId(), 2, this.mainActivity.getId(), 2, 0);
        constraintSet.applyTo(this.mainActivity);
    }

    public void onToolSelected(ToolType toolType) {
        this.currentMode = toolType;
        switch (toolType) {
            case LAYOUT:
                this.puzzleLayout = this.puzzleView.getPuzzleLayout();
                this.currentAspect = this.puzzleView.getAspectRatio();
                this.pieceBorderRadius = this.puzzleView.getPieceRadian();
                this.piecePadding = this.puzzleView.getPiecePadding();
                this.puzzleList.scrollToPosition(0);
                ((PuzzleAdapter) this.puzzleList.getAdapter()).setSelectedIndex(-1);
                this.puzzleList.getAdapter().notifyDataSetChanged();
                this.radiusLayout.scrollToPosition(0);
                ((AspectRatioPreviewAdapter) this.radiusLayout.getAdapter()).setLastSelectedView(-1);
                this.radiusLayout.getAdapter().notifyDataSetChanged();
                selectLayoutTool();
                slideUpSaveView();
                slideUp(this.changeLayoutLayout);
                slideDown(this.mRvTools);
                showUpFunction(this.changeLayoutLayout);
                this.puzzleView.setLocked(false);
                this.puzzleView.setTouchEnable(false);
                return;
            case BORDER:
                this.puzzleLayout = this.puzzleView.getPuzzleLayout();
                this.currentAspect = this.puzzleView.getAspectRatio();
                this.pieceBorderRadius = this.puzzleView.getPieceRadian();
                this.piecePadding = this.puzzleView.getPiecePadding();
                this.puzzleList.scrollToPosition(0);
                ((PuzzleAdapter) this.puzzleList.getAdapter()).setSelectedIndex(-1);
                this.puzzleList.getAdapter().notifyDataSetChanged();
                this.radiusLayout.scrollToPosition(0);
                ((AspectRatioPreviewAdapter) this.radiusLayout.getAdapter()).setLastSelectedView(-1);
                this.radiusLayout.getAdapter().notifyDataSetChanged();
                selectBorderTool();
                slideUpSaveView();
                slideUp(this.changeLayoutLayout);
                slideDown(this.mRvTools);
                showUpFunction(this.changeLayoutLayout);
                this.puzzleView.setLocked(false);
                this.puzzleView.setTouchEnable(false);
                return;
            case RATIO:
                this.puzzleLayout = this.puzzleView.getPuzzleLayout();
                this.currentAspect = this.puzzleView.getAspectRatio();
                this.pieceBorderRadius = this.puzzleView.getPieceRadian();
                this.piecePadding = this.puzzleView.getPiecePadding();
                this.puzzleList.scrollToPosition(0);
                ((PuzzleAdapter) this.puzzleList.getAdapter()).setSelectedIndex(-1);
                this.puzzleList.getAdapter().notifyDataSetChanged();
                this.radiusLayout.scrollToPosition(0);
                ((AspectRatioPreviewAdapter) this.radiusLayout.getAdapter()).setLastSelectedView(-1);
                this.radiusLayout.getAdapter().notifyDataSetChanged();
                selectRadiusTool();
                slideUpSaveView();
                slideUp(this.changeLayoutLayout);
                slideDown(this.mRvTools);
                showUpFunction(this.changeLayoutLayout);
                this.puzzleView.setLocked(false);
                this.puzzleView.setTouchEnable(false);
                return;
            case FILTER:
                if (this.lstOriginalDrawable.isEmpty()) {
                    for (PuzzlePiece drawable : this.puzzleView.getPuzzlePieces()) {
                        this.lstOriginalDrawable.add(drawable.getDrawable());
                    }
                }
                new LoadFilterBitmap().execute(new Void[0]);
                slideDown(this.mRvTools);
                slideUp(this.filterLayout);
                slideUpSaveView();
                return;
            case STICKER:
                this.puzzleView.setTouchEnable(false);
                slideUpSaveView();
                slideDown(this.mRvTools);
                slideUp(this.stickerLayout);
                this.puzzleView.setLocked(false);
                this.puzzleView.setTouchEnable(false);
                return;
            case TEXT:
                this.puzzleView.setTouchEnable(false);
                slideUpSaveView();
                this.puzzleView.setLocked(false);
                openTextFragment();
                slideDown(this.mRvTools);
                slideUp(this.textLayout);
                this.addNewText.setVisibility(0);
                return;
            case BACKGROUND:
                this.puzzleView.setLocked(false);
                this.puzzleView.setTouchEnable(false);
                slideUpSaveView();
                selectBackgroundColorTab();
                slideDown(this.mRvTools);
                slideUp(this.changeBackgroundLayout);
                showUpFunction(this.changeBackgroundLayout);
                if (this.puzzleView.getBackgroundResourceMode() == 0) {
                    this.currentBackgroundState.isColor = true;
                    this.currentBackgroundState.isBitmap = false;
                    this.currentBackgroundState.drawableId = ((ColorDrawable) this.puzzleView.getBackground()).getColor();
                    return;
                } else if (this.puzzleView.getBackgroundResourceMode() == 2 || (this.puzzleView.getBackground() instanceof ColorDrawable)) {
                    this.currentBackgroundState.isBitmap = true;
                    this.currentBackgroundState.isColor = false;
                    this.currentBackgroundState.drawable = this.puzzleView.getBackground();
                    return;
                } else if (this.puzzleView.getBackground() instanceof GradientDrawable) {
                    this.currentBackgroundState.isBitmap = false;
                    this.currentBackgroundState.isColor = false;
                    this.currentBackgroundState.drawable = (GradientDrawable) this.puzzleView.getBackground();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    *//* access modifiers changed from: private *//*
    public void loadPhoto() {
        final int i;
        final ArrayList arrayList = new ArrayList();
        if (this.lstPaths.size() > this.puzzleLayout.getAreaCount()) {
            i = this.puzzleLayout.getAreaCount();
        } else {
            i = this.lstPaths.size();
        }
        for (int i2 = 0; i2 < i; i2++) {
            C12346 r3 = new Target() {
                public void onBitmapFailed(Exception exc, Drawable drawable) {
                }

                public void onPrepareLoad(Drawable drawable) {
                }

                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                    int height = bitmap.getHeight();
                    int width = bitmap.getWidth();
                    boolean z = width > height;
                    if (1920 < Math.min(height, width)) {
                        float f = !z ? 1920.0f / ((float) width) : 1920.0f / ((float) height);
                        bitmap = FileUtils.getResizedBitmap(bitmap, (int) (((float) width) * f), (int) (((float) height) * f));
                    }
                    arrayList.add(bitmap);
                    if (arrayList.size() == i) {
                        if (PuzzleViewActivity.this.lstPaths.size() < PuzzleViewActivity.this.puzzleLayout.getAreaCount()) {
                            for (int i = 0; i < PuzzleViewActivity.this.puzzleLayout.getAreaCount(); i++) {
                                PuzzleViewActivity.this.puzzleView.addPiece((Bitmap) arrayList.get(i % i));
                            }
                        } else {
                            PuzzleViewActivity.this.puzzleView.addPieces(arrayList);
                        }
                    }
                    PuzzleViewActivity.this.targets.remove(this);
                }
            };
            Picasso picasso = Picasso.get();
            picasso.load("file:///" + this.lstPaths.get(i2)).resize(this.deviceWidth, this.deviceWidth).centerInside().config(Bitmap.Config.RGB_565).into((Target) r3);
            this.targets.add(r3);
        }
    }

    public void slideUp(View view) {
        ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) view.getHeight(), 0.0f}).start();
    }

    *//* access modifiers changed from: protected *//*
    public void onDestroy() {
        super.onDestroy();
        try {
            this.puzzleView.reset();
        } catch (Exception unused) {
        }
    }

    public void slideUpSaveView() {
        this.saveControl.setVisibility(8);
    }

    public void slideDownSaveView() {
        this.saveControl.setVisibility(0);
    }

    public void slideDown(View view) {
        ObjectAnimator.ofFloat(view, "translationY", new float[]{0.0f, (float) view.getHeight()}).start();
    }

    public void onBackPressed() {
        if (this.currentMode == null) {
            AdmobHelp.getInstance().showInterstitialAd(new AdmobHelp.AdCloseListener() {
                public void onAdClosed() {
                    PuzzleViewActivity.this.finish();
                }
            }, this);
            return;
        }
        try {
            switch (this.currentMode) {
                case LAYOUT:
                case BORDER:
                case RATIO:
                    slideDown(this.changeLayoutLayout);
                    slideUp(this.mRvTools);
                    slideDownSaveView();
                    showDownFunction();
                    this.puzzleView.updateLayout(this.puzzleLayout);
                    this.puzzleView.setPiecePadding(this.piecePadding);
                    this.puzzleView.setPieceRadian(this.pieceBorderRadius);
                    this.currentMode = ToolType.NONE;
                    getWindowManager().getDefaultDisplay().getSize(new Point());
                    onNewAspectRatioSelected(this.currentAspect);
                    this.puzzleView.setAspectRatio(this.currentAspect);
                    this.puzzleView.setLocked(true);
                    this.puzzleView.setTouchEnable(true);
                    return;
                case FILTER:
                    slideUp(this.mRvTools);
                    slideDown(this.filterLayout);
                    for (int i = 0; i < this.lstOriginalDrawable.size(); i++) {
                        this.puzzleView.getPuzzlePieces().get(i).setDrawable(this.lstOriginalDrawable.get(i));
                    }
                    this.puzzleView.invalidate();
                    slideDownSaveView();
                    this.currentMode = ToolType.NONE;
                    return;
                case STICKER:
                    if (this.puzzleView.getStickers().size() <= 0) {
                        slideUp(this.wrapStickerList);
                        slideDown(this.stickerLayout);
                        this.addNewSticker.setVisibility(8);
                        this.puzzleView.setHandlingSticker((Sticker) null);
                        slideUp(this.mRvTools);
                        this.puzzleView.setLocked(true);
                        this.currentMode = ToolType.NONE;
                    } else if (this.addNewSticker.getVisibility() == 0) {
                        this.puzzleView.getStickers().clear();
                        this.addNewSticker.setVisibility(8);
                        this.puzzleView.setHandlingSticker((Sticker) null);
                        slideUp(this.wrapStickerList);
                        slideDown(this.stickerLayout);
                        slideUp(this.mRvTools);
                        this.puzzleView.setLocked(true);
                        this.puzzleView.setTouchEnable(true);
                        this.currentMode = ToolType.NONE;
                    } else {
                        slideDown(this.wrapStickerList);
                        this.addNewSticker.setVisibility(0);
                    }
                    slideDownSaveView();
                    return;
                case TEXT:
                    if (!this.puzzleView.getStickers().isEmpty()) {
                        this.puzzleView.getStickers().clear();
                        this.puzzleView.setHandlingSticker((Sticker) null);
                    }
                    slideDown(this.textLayout);
                    this.addNewText.setVisibility(8);
                    this.puzzleView.setHandlingSticker((Sticker) null);
                    slideUp(this.mRvTools);
                    slideDownSaveView();
                    this.puzzleView.setLocked(true);
                    this.currentMode = ToolType.NONE;
                    this.puzzleView.setTouchEnable(true);
                    return;
                case BACKGROUND:
                    slideUp(this.mRvTools);
                    slideDown(this.changeBackgroundLayout);
                    this.puzzleView.setLocked(true);
                    this.puzzleView.setTouchEnable(true);
                    if (this.currentBackgroundState.isColor) {
                        this.puzzleView.setBackgroundResourceMode(0);
                        this.puzzleView.setBackgroundColor(this.currentBackgroundState.drawableId);
                    } else if (this.currentBackgroundState.isBitmap) {
                        this.puzzleView.setBackgroundResourceMode(2);
                        this.puzzleView.setBackground(this.currentBackgroundState.drawable);
                    } else {
                        this.puzzleView.setBackgroundResourceMode(1);
                        if (this.currentBackgroundState.drawable != null) {
                            this.puzzleView.setBackground(this.currentBackgroundState.drawable);
                        } else {
                            this.puzzleView.setBackgroundResource(this.currentBackgroundState.drawableId);
                        }
                    }
                    slideDownSaveView();
                    showDownFunction();
                    this.currentMode = ToolType.NONE;
                    return;
                case PIECE:
                    slideDown(this.rvPieceControl);
                    slideUp(this.mRvTools);
                    slideDownSaveView();
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.rvPieceControl.getLayoutParams();
                    layoutParams.bottomMargin = 0;
                    this.rvPieceControl.setLayoutParams(layoutParams);
                    this.currentMode = ToolType.NONE;
                    this.puzzleView.setHandlingPiece((PuzzlePiece) null);
                    this.puzzleView.setPreviousHandlingPiece((PuzzlePiece) null);
                    this.puzzleView.invalidate();
                    this.currentMode = ToolType.NONE;
                    return;
                case NONE:
                    Toast.makeText(getApplicationContext(), getString(C1084R.string.press_one_more_time), 0).show();
                    this.currentMode = null;
                    return;
                default:
                    super.onBackPressed();
                    if (this.mInterstitialAd.isLoaded()) {
                        this.mInterstitialAd.show();
                        return;
                    }
                    return;
            }
        } catch (Exception unused) {
        }
    }

    public void onItemClick(PuzzleLayout puzzleLayout2, int i) {
        PuzzleLayout parse = PuzzleLayoutParser.parse(puzzleLayout2.generateInfo());
        puzzleLayout2.setRadian(this.puzzleView.getPieceRadian());
        puzzleLayout2.setPadding(this.puzzleView.getPiecePadding());
        this.puzzleView.updateLayout(parse);
    }

    public void onNewAspectRatioSelected(AspectRatio aspectRatio) {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int[] calculateWidthAndHeight = calculateWidthAndHeight(aspectRatio, point);
        this.puzzleView.setLayoutParams(new ConstraintLayout.LayoutParams(calculateWidthAndHeight[0], calculateWidthAndHeight[1]));
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.wrapPuzzleView);
        ConstraintSet constraintSet2 = constraintSet;
        constraintSet2.connect(this.puzzleView.getId(), 3, this.wrapPuzzleView.getId(), 3, 0);
        constraintSet2.connect(this.puzzleView.getId(), 1, this.wrapPuzzleView.getId(), 1, 0);
        constraintSet2.connect(this.puzzleView.getId(), 4, this.wrapPuzzleView.getId(), 4, 0);
        constraintSet2.connect(this.puzzleView.getId(), 2, this.wrapPuzzleView.getId(), 2, 0);
        constraintSet.applyTo(this.wrapPuzzleView);
        this.puzzleView.setAspectRatio(aspectRatio);
    }

    public void replaceCurrentPiece(String str) {
        new OnLoadBitmapFromUri().execute(new String[]{str});
    }

    private int[] calculateWidthAndHeight(AspectRatio aspectRatio, Point point) {
        int height = this.wrapPuzzleView.getHeight();
        if (aspectRatio.getHeight() > aspectRatio.getWidth()) {
            int ratio = (int) (aspectRatio.getRatio() * ((float) height));
            if (ratio < point.x) {
                return new int[]{ratio, height};
            }
            return new int[]{point.x, (int) (((float) point.x) / aspectRatio.getRatio())};
        }
        int ratio2 = (int) (((float) point.x) / aspectRatio.getRatio());
        if (ratio2 > height) {
            return new int[]{(int) (((float) height) * aspectRatio.getRatio()), height};
        }
        return new int[]{point.x, ratio2};
    }

    public void onPause() {
        super.onPause();
        this.keyboardHeightProvider.onPause();
    }

    public void onResume() {
        super.onResume();
        this.keyboardHeightProvider.onResume();
    }

    public void addSticker(Bitmap bitmap) {
        this.puzzleView.addSticker(new DrawableSticker(new BitmapDrawable(getResources(), bitmap)));
        slideDown(this.wrapStickerList);
        this.addNewSticker.setVisibility(0);
    }

    public void onBackgroundSelected(final PuzzleBackgroundAdapter.SquareView squareView) {
        if (squareView.isColor) {
            this.puzzleView.setBackgroundColor(squareView.drawableId);
            this.puzzleView.setBackgroundResourceMode(0);
        } else if (squareView.drawable != null) {
            this.puzzleView.setBackgroundResourceMode(2);
            new AsyncTask<Void, Bitmap, Bitmap>() {
                *//* access modifiers changed from: protected *//*
                public void onPreExecute() {
                    PuzzleViewActivity.this.showLoading(true);
                }

                *//* access modifiers changed from: protected *//*
                public Bitmap doInBackground(Void... voidArr) {
                    return FilterUtils.getBlurImageFromBitmap(((BitmapDrawable) squareView.drawable).getBitmap(), 5.0f);
                }

                *//* access modifiers changed from: protected *//*
                public void onPostExecute(Bitmap bitmap) {
                    PuzzleViewActivity.this.showLoading(false);
                    PuzzleViewActivity.this.puzzleView.setBackground(new BitmapDrawable(PuzzleViewActivity.this.getResources(), bitmap));
                }
            }.execute(new Void[0]);
        } else {
            this.puzzleView.setBackgroundResource(squareView.drawableId);
            this.puzzleView.setBackgroundResourceMode(1);
        }
    }

    public void onFilterSelected(String str) {
        new LoadBitmapWithFilter().execute(new String[]{str});
    }

    public void onPieceFuncSelected(ToolType toolType) {
        int i = C122813.$SwitchMap$lisa$studio$photoeditor$adapter$ToolType[toolType.ordinal()];
        if (i != 4) {
            switch (i) {
                case 12:
                    PhotoPicker.builder().setPhotoCount(1).setPreviewEnabled(false).setShowCamera(false).setForwardMain(true).start(this);
                    return;
                case 13:
                    this.puzzleView.flipHorizontally();
                    return;
                case 14:
                    this.puzzleView.flipVertically();
                    return;
                case 15:
                    this.puzzleView.rotate(90.0f);
                    return;
                case 16:
                    CropDialogFragment.show(this, this, ((BitmapDrawable) this.puzzleView.getHandlingPiece().getDrawable()).getBitmap());
                    return;
                default:
                    return;
            }
        } else {
            new LoadFilterBitmapForCurrentPiece().execute(new Void[0]);
        }
    }

    public void finishCrop(Bitmap bitmap) {
        this.puzzleView.replace(bitmap, "");
    }

    public void onSaveFilter(Bitmap bitmap) {
        this.puzzleView.replace(bitmap, "");
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.PuzzleViewActivity$LoadFilterBitmap *//*
    class LoadFilterBitmap extends AsyncTask<Void, Void, Void> {
        LoadFilterBitmap() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            PuzzleViewActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public Void doInBackground(Void... voidArr) {
            PuzzleViewActivity.this.lstBitmapWithFilter.clear();
            PuzzleViewActivity.this.lstBitmapWithFilter.addAll(FilterUtils.getLstBitmapWithFilter(ThumbnailUtils.extractThumbnail(((BitmapDrawable) PuzzleViewActivity.this.puzzleView.getPuzzlePieces().get(0).getDrawable()).getBitmap(), 150, 150)));
            return null;
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(Void voidR) {
            PuzzleViewActivity.this.rvFilterView.setAdapter(new FilterViewAdapter(PuzzleViewActivity.this.lstBitmapWithFilter, PuzzleViewActivity.this, PuzzleViewActivity.this.getApplicationContext(), Arrays.asList(FilterUtils.EFFECT_CONFIGS)));
            PuzzleViewActivity.this.slideDown(PuzzleViewActivity.this.mRvTools);
            PuzzleViewActivity.this.slideUp(PuzzleViewActivity.this.filterLayout);
            PuzzleViewActivity.this.showLoading(false);
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.PuzzleViewActivity$LoadFilterBitmapForCurrentPiece *//*
    class LoadFilterBitmapForCurrentPiece extends AsyncTask<Void, List<Bitmap>, List<Bitmap>> {
        LoadFilterBitmapForCurrentPiece() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            PuzzleViewActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public List<Bitmap> doInBackground(Void... voidArr) {
            return FilterUtils.getLstBitmapWithFilter(ThumbnailUtils.extractThumbnail(((BitmapDrawable) PuzzleViewActivity.this.puzzleView.getHandlingPiece().getDrawable()).getBitmap(), 150, 150));
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(List<Bitmap> list) {
            PuzzleViewActivity.this.showLoading(false);
            try {
                FilterDialogFragment.show(PuzzleViewActivity.this, PuzzleViewActivity.this, ((BitmapDrawable) PuzzleViewActivity.this.puzzleView.getHandlingPiece().getDrawable()).getBitmap(), list);
            } catch (Exception unused) {
            }
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.PuzzleViewActivity$LoadBitmapWithFilter *//*
    class LoadBitmapWithFilter extends AsyncTask<String, List<Bitmap>, List<Bitmap>> {
        LoadBitmapWithFilter() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            PuzzleViewActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public List<Bitmap> doInBackground(String... strArr) {
            ArrayList arrayList = new ArrayList();
            for (Drawable drawable : PuzzleViewActivity.this.lstOriginalDrawable) {
                arrayList.add(FilterUtils.getBitmapWithFilter(((BitmapDrawable) drawable).getBitmap(), strArr[0]));
            }
            return arrayList;
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(List<Bitmap> list) {
            for (int i = 0; i < list.size(); i++) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(PuzzleViewActivity.this.getResources(), list.get(i));
                bitmapDrawable.setAntiAlias(true);
                bitmapDrawable.setFilterBitmap(true);
                PuzzleViewActivity.this.puzzleView.getPuzzlePieces().get(i).setDrawable(bitmapDrawable);
            }
            PuzzleViewActivity.this.puzzleView.invalidate();
            PuzzleViewActivity.this.showLoading(false);
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.PuzzleViewActivity$OnLoadBitmapFromUri *//*
    class OnLoadBitmapFromUri extends AsyncTask<String, Bitmap, Bitmap> {
        OnLoadBitmapFromUri() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            PuzzleViewActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public Bitmap doInBackground(String... strArr) {
            try {
                Uri fromFile = Uri.fromFile(new File(strArr[0]));
                return SystemUtil.rotateBitmap(MediaStore.Images.Media.getBitmap(PuzzleViewActivity.this.getContentResolver(), fromFile), new ExifInterface(PuzzleViewActivity.this.getContentResolver().openInputStream(fromFile)).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(Bitmap bitmap) {
            PuzzleViewActivity.this.showLoading(false);
            PuzzleViewActivity.this.puzzleView.replace(bitmap, "");
        }
    }

    *//* renamed from: lisa.studio.photoeditor.ui.main.activity.PuzzleViewActivity$SavePuzzleAsFile *//*
    class SavePuzzleAsFile extends AsyncTask<Bitmap, String, String> {
        SavePuzzleAsFile() {
        }

        *//* access modifiers changed from: protected *//*
        public void onPreExecute() {
            PuzzleViewActivity.this.showLoading(true);
        }

        *//* access modifiers changed from: protected *//*
        public String doInBackground(Bitmap... bitmapArr) {
            Bitmap bitmap = bitmapArr[0];
            Bitmap bitmap2 = bitmapArr[1];
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, (Rect) null, new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight()), (Paint) null);
            canvas.drawBitmap(bitmap2, (Rect) null, new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight()), (Paint) null);
            bitmap.recycle();
            bitmap2.recycle();
            File saveBitmapAsFile = FileUtils.saveBitmapAsFile(createBitmap);
            if (saveBitmapAsFile == null) {
                return null;
            }
            try {
                MediaScannerConnection.scanFile(PuzzleViewActivity.this.getApplicationContext(), new String[]{saveBitmapAsFile.getAbsolutePath()}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String str, Uri uri) {
                    }
                });
                createBitmap.recycle();
                return saveBitmapAsFile.getAbsolutePath();
            } catch (Exception unused) {
                createBitmap.recycle();
                return null;
            } catch (Throwable th) {
                createBitmap.recycle();
                throw th;
            }
        }

        *//* access modifiers changed from: protected *//*
        public void onPostExecute(String str) {
            PuzzleViewActivity.this.showLoading(false);
            Intent intent = new Intent(PuzzleViewActivity.this, SaveAndShareActivity.class);
            intent.putExtra("path", str);
            PuzzleViewActivity.this.startActivity(intent);
        }
    }*/
}
