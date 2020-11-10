package com.teamvinay.newphotoediter.featuresfoto.addtext;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.CarouselPicker;
import com.teamvinay.newphotoediter.featuresfoto.addtext.AddTextProperties;
import com.teamvinay.newphotoediter.featuresfoto.addtext.adapter.FontAdapter;
import com.teamvinay.newphotoediter.featuresfoto.addtext.adapter.ShadowAdapter;
import com.teamvinay.newphotoediter.util.FontUtils;
import com.teamvinay.newphotoediter.util.SharePreferenceUtil;
import com.teamvinay.newphotoediter.util.SystemUtil;

public class TextEditorDialogFragment extends DialogFragment implements View.OnClickListener, FontAdapter.ItemClickListener, ShadowAdapter.ShadowItemClickListener {
    public static final String EXTRA_COLOR_CODE = "extra_color_code";
    public static final String EXTRA_INPUT_TEXT = "extra_input_text";
    public static final String TAG = "TextEditorDialogFragment";
    LinearLayout addTextBottomToolbar;
    /* access modifiers changed from: private */
    public AddTextProperties addTextProperties;
    ImageView arrowBackgroundColorDown;
    ImageView arrowTextTexture;
    SeekBar backgroundBorder;
    CarouselPicker backgroundColorCarousel;
    AppCompatCheckBox backgroundFullScreen;
    SeekBar backgroundHeight;
    SeekBar backgroundTransparent;
    SeekBar backgroundWidth;
    ImageView changeAlign;
    ImageView changeColor;
    ScrollView changeColorLayout;
    ImageView changeFont;
    ScrollView changeFontLayout;
    ImageView colorArrow;
    /* access modifiers changed from: private */
    public List<CarouselPicker.PickerItem> colorItems;
    CarouselPicker colorPicker;
    private FontAdapter fontAdapter;
    View highlightBackgroundColor;
    View highlightColor;
    View highlightTextTexture;
    LinearLayout layoutPreview;
    RecyclerView lstFonts;
    RecyclerView lstShadows;
    CustomEditText mAddTextEditText;
    private InputMethodManager mInputMethodManager;
    TextView previewText;
    ImageView saveChange;
    private ShadowAdapter shadowAdapter;
    ImageView showKeyboard;
    SwitchCompat switchBackgroundTexture;
    private TextEditor textEditor;
    private List<ImageView> textFunctions;
    SeekBar textSize;
    /* access modifiers changed from: private */
    public List<CarouselPicker.PickerItem> textTextureItems;
    CarouselPicker textTexturePicker;
    SeekBar textTransparent;

    public interface TextEditor {
        void onBackButton();

        void onDone(AddTextProperties addTextProperties);
    }

    public void initView(View view) {
        this.mAddTextEditText = (CustomEditText) view.findViewById(C1084R.C1087id.add_text_edit_text);
        this.showKeyboard = (ImageView) view.findViewById(C1084R.C1087id.showKeyboard);
        this.changeFont = (ImageView) view.findViewById(C1084R.C1087id.changeFont);
        this.changeColor = (ImageView) view.findViewById(C1084R.C1087id.changeColor);
        this.changeAlign = (ImageView) view.findViewById(C1084R.C1087id.changeAlign);
        this.saveChange = (ImageView) view.findViewById(C1084R.C1087id.saveChange);
        this.changeFontLayout = (ScrollView) view.findViewById(C1084R.C1087id.change_font_layout);
        this.addTextBottomToolbar = (LinearLayout) view.findViewById(C1084R.C1087id.add_text_toolbar);
        this.lstFonts = (RecyclerView) view.findViewById(C1084R.C1087id.fonts);
        this.lstShadows = (RecyclerView) view.findViewById(C1084R.C1087id.shadows);
        this.changeColorLayout = (ScrollView) view.findViewById(C1084R.C1087id.changeColorLayout);
        this.colorPicker = (CarouselPicker) view.findViewById(C1084R.C1087id.colorCarousel);
        this.textTexturePicker = (CarouselPicker) view.findViewById(C1084R.C1087id.textTextureSlider);
        this.arrowTextTexture = (ImageView) view.findViewById(C1084R.C1087id.arrow_text_texture);
        this.colorArrow = (ImageView) view.findViewById(C1084R.C1087id.arrow_color_down);
        this.highlightColor = view.findViewById(C1084R.C1087id.highlightColor);
        this.highlightTextTexture = view.findViewById(C1084R.C1087id.highlightTextTexture);
        this.textTransparent = (SeekBar) view.findViewById(C1084R.C1087id.textTransparent);
        this.previewText = (TextView) view.findViewById(C1084R.C1087id.previewEffectText);
        this.layoutPreview = (LinearLayout) view.findViewById(C1084R.C1087id.layoutPreview);
        this.switchBackgroundTexture = (SwitchCompat) view.findViewById(C1084R.C1087id.switchBackgroundTexture);
        this.arrowBackgroundColorDown = (ImageView) view.findViewById(C1084R.C1087id.arrowBackgroundColorDown);
        this.highlightBackgroundColor = view.findViewById(C1084R.C1087id.highlightBackgroundColor);
        this.backgroundColorCarousel = (CarouselPicker) view.findViewById(C1084R.C1087id.backgroundColorCarousel);
        this.backgroundWidth = (SeekBar) view.findViewById(C1084R.C1087id.backgroundWidth);
        this.backgroundHeight = (SeekBar) view.findViewById(C1084R.C1087id.backgroundHeight);
        this.backgroundFullScreen = (AppCompatCheckBox) view.findViewById(C1084R.C1087id.backgroundFullScreen);
        this.backgroundTransparent = (SeekBar) view.findViewById(C1084R.C1087id.backgroundTransparent);
        this.textSize = (SeekBar) view.findViewById(C1084R.C1087id.textSize);
        this.backgroundBorder = (SeekBar) view.findViewById(C1084R.C1087id.backgroundBorderRadius);
    }

    public void onItemClick(View view, int i) {
        FontUtils.setFontByName((Context) Objects.requireNonNull(getContext()), this.previewText, FontUtils.getListFonts().get(i));
        this.addTextProperties.setFontName(FontUtils.getListFonts().get(i));
        this.addTextProperties.setFontIndex(i);
    }

    public void onShadowItemClick(View view, int i) {
        AddTextProperties.TextShadow textShadow = AddTextProperties.getLstTextShadow().get(i);
        this.previewText.setShadowLayer((float) textShadow.getRadius(), (float) textShadow.getDx(), (float) textShadow.getDy(), textShadow.getColorShadow());
        this.previewText.invalidate();
        this.addTextProperties.setTextShadow(textShadow);
        this.addTextProperties.setTextShadowIndex(i);
    }

    public static TextEditorDialogFragment show(@NonNull AppCompatActivity appCompatActivity, @NonNull String str, @ColorInt int i) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_INPUT_TEXT, str);
        bundle.putInt(EXTRA_COLOR_CODE, i);
        TextEditorDialogFragment textEditorDialogFragment = new TextEditorDialogFragment();
        textEditorDialogFragment.setArguments(bundle);
        textEditorDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
        return textEditorDialogFragment;
    }

    public static TextEditorDialogFragment show(@NonNull AppCompatActivity appCompatActivity, AddTextProperties addTextProperties2) {
        TextEditorDialogFragment textEditorDialogFragment = new TextEditorDialogFragment();
        textEditorDialogFragment.setAddTextProperties(addTextProperties2);
        textEditorDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
        return textEditorDialogFragment;
    }

    public static TextEditorDialogFragment show(@NonNull AppCompatActivity appCompatActivity) {
        return show(appCompatActivity, "Test", ContextCompat.getColor(appCompatActivity, C1084R.C1085color.white));
    }

    public void setAddTextProperties(AddTextProperties addTextProperties2) {
        this.addTextProperties = addTextProperties2;
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        getDialog().getWindow().requestFeature(1);
        getDialog().getWindow().setFlags(1024, 1024);
        return layoutInflater.inflate(C1084R.C1089layout.add_text_dialog, viewGroup, false);
    }

    public void dismissAndShowSticker() {
        this.textEditor.onBackButton();
        dismiss();
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        if (this.addTextProperties == null) {
            this.addTextProperties = AddTextProperties.getDefaultProperties();
        }
        this.mAddTextEditText.setDialogFragment(this);
        initAddTextLayout();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        this.mInputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        setDefaultStyleForEdittext();
        this.mInputMethodManager.toggleSoftInput(2, 0);
        highlightFunction(this.showKeyboard);
        this.lstFonts.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.fontAdapter = new FontAdapter(getContext(), FontUtils.getListFonts());
        this.fontAdapter.setClickListener(this);
        this.lstFonts.setAdapter(this.fontAdapter);
        this.lstShadows.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.shadowAdapter = new ShadowAdapter(getContext(), AddTextProperties.getLstTextShadow());
        this.shadowAdapter.setClickListener(this);
        this.lstShadows.setAdapter(this.shadowAdapter);
        this.colorPicker.setAdapter(new CarouselPicker.CarouselViewAdapter(getContext(), this.colorItems, 0));
        this.colorPicker.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageSelected(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                if (f > 0.0f) {
                    if (TextEditorDialogFragment.this.colorArrow.getVisibility() == 4) {
                        TextEditorDialogFragment.this.colorArrow.setVisibility(0);
                        TextEditorDialogFragment.this.highlightColor.setVisibility(0);
                        TextEditorDialogFragment.this.arrowTextTexture.setVisibility(4);
                        TextEditorDialogFragment.this.highlightTextTexture.setVisibility(8);
                    }
                    TextEditorDialogFragment.this.previewText.getPaint().setShader((Shader) null);
                    float f2 = ((float) i) + f;
                    int parseColor = Color.parseColor(((CarouselPicker.PickerItem) TextEditorDialogFragment.this.colorItems.get(Math.round(f2))).getColor());
                    TextEditorDialogFragment.this.previewText.setTextColor(parseColor);
                    TextEditorDialogFragment.this.addTextProperties.setTextColorIndex(Math.round(f2));
                    TextEditorDialogFragment.this.addTextProperties.setTextColor(parseColor);
                    TextEditorDialogFragment.this.addTextProperties.setTextShader((Shader) null);
                }
            }
        });
        this.textTexturePicker.setAdapter(new CarouselPicker.CarouselViewAdapter(getContext(), this.textTextureItems, 0));
        this.textTexturePicker.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageSelected(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                if (f > 0.0f) {
                    if (TextEditorDialogFragment.this.arrowTextTexture.getVisibility() == 4) {
                        TextEditorDialogFragment.this.arrowTextTexture.setVisibility(0);
                        TextEditorDialogFragment.this.highlightTextTexture.setVisibility(0);
                        TextEditorDialogFragment.this.colorArrow.setVisibility(4);
                        TextEditorDialogFragment.this.highlightColor.setVisibility(8);
                    }
                    float f2 = ((float) i) + f;
                    BitmapShader bitmapShader = new BitmapShader(((CarouselPicker.PickerItem) TextEditorDialogFragment.this.textTextureItems.get(Math.round(f2))).getBitmap(), Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
                    TextEditorDialogFragment.this.previewText.setLayerType(1, (Paint) null);
                    TextEditorDialogFragment.this.previewText.getPaint().setShader(bitmapShader);
                    TextEditorDialogFragment.this.addTextProperties.setTextShader(bitmapShader);
                    TextEditorDialogFragment.this.addTextProperties.setTextShaderIndex(Math.round(f2));
                }
            }
        });
        this.textTransparent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int i2 = 255 - i;
                TextEditorDialogFragment.this.addTextProperties.setTextAlpha(i2);
                TextEditorDialogFragment.this.previewText.setTextColor(Color.argb(i2, Color.red(TextEditorDialogFragment.this.addTextProperties.getTextColor()), Color.green(TextEditorDialogFragment.this.addTextProperties.getTextColor()), Color.blue(TextEditorDialogFragment.this.addTextProperties.getTextColor())));
            }
        });
        this.mAddTextEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextEditorDialogFragment.this.previewText.setText(charSequence.toString());
                TextEditorDialogFragment.this.addTextProperties.setText(charSequence.toString());
            }
        });
        this.switchBackgroundTexture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    TextEditorDialogFragment.this.addTextProperties.setShowBackground(false);
                    TextEditorDialogFragment.this.previewText.setBackgroundResource(0);
                    TextEditorDialogFragment.this.previewText.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                } else if (TextEditorDialogFragment.this.switchBackgroundTexture.isPressed() || TextEditorDialogFragment.this.addTextProperties.isShowBackground()) {
                    TextEditorDialogFragment.this.addTextProperties.setShowBackground(true);
                    TextEditorDialogFragment.this.initPreviewText();
                } else {
                    TextEditorDialogFragment.this.switchBackgroundTexture.setChecked(false);
                    TextEditorDialogFragment.this.addTextProperties.setShowBackground(false);
                    TextEditorDialogFragment.this.initPreviewText();
                }
            }
        });
        this.backgroundColorCarousel.setAdapter(new CarouselPicker.CarouselViewAdapter(getContext(), this.colorItems, 0));
        this.backgroundColorCarousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageSelected(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                if (f > 0.0f) {
                    int i3 = 0;
                    if (TextEditorDialogFragment.this.arrowBackgroundColorDown.getVisibility() == 4) {
                        TextEditorDialogFragment.this.arrowBackgroundColorDown.setVisibility(0);
                        TextEditorDialogFragment.this.highlightBackgroundColor.setVisibility(0);
                    }
                    TextEditorDialogFragment.this.addTextProperties.setShowBackground(true);
                    if (!TextEditorDialogFragment.this.switchBackgroundTexture.isChecked()) {
                        TextEditorDialogFragment.this.switchBackgroundTexture.setChecked(true);
                    }
                    float f2 = ((float) i) + f;
                    int round = Math.round(f2);
                    if (round >= TextEditorDialogFragment.this.colorItems.size()) {
                        i3 = TextEditorDialogFragment.this.colorItems.size() - 1;
                    } else if (round >= 0) {
                        i3 = round;
                    }
                    int parseColor = Color.parseColor(((CarouselPicker.PickerItem) TextEditorDialogFragment.this.colorItems.get(i3)).getColor());
                    int red = Color.red(parseColor);
                    int green = Color.green(parseColor);
                    int blue = Color.blue(parseColor);
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setColor(Color.argb(TextEditorDialogFragment.this.addTextProperties.getBackgroundAlpha(), red, green, blue));
                    gradientDrawable.setCornerRadius((float) SystemUtil.dpToPx((Context) Objects.requireNonNull(TextEditorDialogFragment.this.getContext()), TextEditorDialogFragment.this.addTextProperties.getBackgroundBorder()));
                    TextEditorDialogFragment.this.previewText.setBackground(gradientDrawable);
                    TextEditorDialogFragment.this.addTextProperties.setBackgroundColor(parseColor);
                    TextEditorDialogFragment.this.addTextProperties.setBackgroundColorIndex(Math.round(f2));
                    TextEditorDialogFragment.this.backgroundBorder.setEnabled(true);
                }
            }
        });
        this.backgroundWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextEditorDialogFragment.this.previewText.setPadding(SystemUtil.dpToPx((Context) Objects.requireNonNull(TextEditorDialogFragment.this.getContext()), i), TextEditorDialogFragment.this.previewText.getPaddingTop(), SystemUtil.dpToPx(TextEditorDialogFragment.this.getContext(), i), TextEditorDialogFragment.this.previewText.getPaddingBottom());
                TextEditorDialogFragment.this.addTextProperties.setPaddingWidth(i);
            }
        });
        this.backgroundHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextEditorDialogFragment.this.previewText.setPadding(TextEditorDialogFragment.this.previewText.getPaddingLeft(), SystemUtil.dpToPx((Context) Objects.requireNonNull(TextEditorDialogFragment.this.getContext()), i), TextEditorDialogFragment.this.previewText.getPaddingRight(), SystemUtil.dpToPx(TextEditorDialogFragment.this.getContext(), i));
                TextEditorDialogFragment.this.addTextProperties.setPaddingHeight(i);
            }
        });
        this.backgroundFullScreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    TextEditorDialogFragment.this.previewText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                } else {
                    TextEditorDialogFragment.this.previewText.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                }
                TextEditorDialogFragment.this.addTextProperties.setFullScreen(z);
            }
        });
        this.backgroundTransparent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextEditorDialogFragment.this.addTextProperties.setBackgroundAlpha(255 - i);
                if (TextEditorDialogFragment.this.addTextProperties.isShowBackground()) {
                    int red = Color.red(TextEditorDialogFragment.this.addTextProperties.getBackgroundColor());
                    int green = Color.green(TextEditorDialogFragment.this.addTextProperties.getBackgroundColor());
                    int blue = Color.blue(TextEditorDialogFragment.this.addTextProperties.getBackgroundColor());
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setColor(Color.argb(TextEditorDialogFragment.this.addTextProperties.getBackgroundAlpha(), red, green, blue));
                    gradientDrawable.setCornerRadius((float) SystemUtil.dpToPx((Context) Objects.requireNonNull(TextEditorDialogFragment.this.getContext()), TextEditorDialogFragment.this.addTextProperties.getBackgroundBorder()));
                    TextEditorDialogFragment.this.previewText.setBackground(gradientDrawable);
                }
            }
        });
        this.textSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int i2 = 15;
                if (i >= 15) {
                    i2 = i;
                }
                TextEditorDialogFragment.this.previewText.setTextSize((float) i2);
                TextEditorDialogFragment.this.addTextProperties.setTextSize(i2);
            }
        });
        this.backgroundBorder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextEditorDialogFragment.this.addTextProperties.setBackgroundBorder(i);
                if (TextEditorDialogFragment.this.addTextProperties.isShowBackground()) {
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setCornerRadius((float) SystemUtil.dpToPx((Context) Objects.requireNonNull(TextEditorDialogFragment.this.getContext()), i));
                    gradientDrawable.setColor(Color.argb(TextEditorDialogFragment.this.addTextProperties.getBackgroundAlpha(), Color.red(TextEditorDialogFragment.this.addTextProperties.getBackgroundColor()), Color.green(TextEditorDialogFragment.this.addTextProperties.getBackgroundColor()), Color.blue(TextEditorDialogFragment.this.addTextProperties.getBackgroundColor())));
                    TextEditorDialogFragment.this.previewText.setBackground(gradientDrawable);
                }
            }
        });
        if (SharePreferenceUtil.getHeightOfKeyboard((Context) Objects.requireNonNull(getContext())) > 0) {
            updateAddTextBottomToolbarHeight(SharePreferenceUtil.getHeightOfKeyboard(getContext()));
        }
        initPreviewText();
    }

    /* access modifiers changed from: private */
    public void initPreviewText() {
        if (this.addTextProperties.isFullScreen()) {
            this.previewText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        }
        if (this.addTextProperties.isShowBackground()) {
            if (this.addTextProperties.getBackgroundColor() != 0) {
                this.previewText.setBackgroundColor(this.addTextProperties.getBackgroundColor());
            }
            if (this.addTextProperties.getBackgroundAlpha() < 255) {
                this.previewText.setBackgroundColor(Color.argb(this.addTextProperties.getBackgroundAlpha(), Color.red(this.addTextProperties.getBackgroundColor()), Color.green(this.addTextProperties.getBackgroundColor()), Color.blue(this.addTextProperties.getBackgroundColor())));
            }
            if (this.addTextProperties.getBackgroundBorder() > 0) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius((float) SystemUtil.dpToPx((Context) Objects.requireNonNull(getContext()), this.addTextProperties.getBackgroundBorder()));
                gradientDrawable.setColor(Color.argb(this.addTextProperties.getBackgroundAlpha(), Color.red(this.addTextProperties.getBackgroundColor()), Color.green(this.addTextProperties.getBackgroundColor()), Color.blue(this.addTextProperties.getBackgroundColor())));
                this.previewText.setBackground(gradientDrawable);
            }
        }
        if (this.addTextProperties.getPaddingHeight() > 0) {
            this.previewText.setPadding(this.previewText.getPaddingLeft(), this.addTextProperties.getPaddingHeight(), this.previewText.getPaddingRight(), this.addTextProperties.getPaddingHeight());
            this.backgroundHeight.setProgress(this.addTextProperties.getPaddingHeight());
        }
        if (this.addTextProperties.getPaddingWidth() > 0) {
            this.previewText.setPadding(this.addTextProperties.getPaddingWidth(), this.previewText.getPaddingTop(), this.addTextProperties.getPaddingWidth(), this.previewText.getPaddingBottom());
            this.backgroundWidth.setProgress(this.addTextProperties.getPaddingWidth());
        }
        if (this.addTextProperties.getText() != null) {
            this.previewText.setText(this.addTextProperties.getText());
            this.mAddTextEditText.setText(this.addTextProperties.getText());
        }
        if (this.addTextProperties.getTextShader() != null) {
            this.previewText.setLayerType(1, (Paint) null);
            this.previewText.getPaint().setShader(this.addTextProperties.getTextShader());
        }
        if (this.addTextProperties.getTextAlign() == 4) {
            this.changeAlign.setImageDrawable(getResources().getDrawable(C1084R.C1086drawable.img_alignment_center));
        } else if (this.addTextProperties.getTextAlign() == 3) {
            this.changeAlign.setImageDrawable(getResources().getDrawable(C1084R.C1086drawable.img_alignment_right));
        } else if (this.addTextProperties.getTextAlign() == 2) {
            this.changeAlign.setImageDrawable(getResources().getDrawable(C1084R.C1086drawable.img_alignment_left));
        }
        this.previewText.setPadding(SystemUtil.dpToPx(getContext(), this.addTextProperties.getPaddingWidth()), this.previewText.getPaddingTop(), SystemUtil.dpToPx(getContext(), this.addTextProperties.getPaddingWidth()), this.previewText.getPaddingBottom());
        this.previewText.setTextColor(this.addTextProperties.getTextColor());
        this.previewText.setTextAlignment(this.addTextProperties.getTextAlign());
        this.previewText.setTextSize((float) this.addTextProperties.getTextSize());
        FontUtils.setFontByName(getContext(), this.previewText, this.addTextProperties.getFontName());
        if (this.addTextProperties.getTextShadow() != null) {
            AddTextProperties.TextShadow textShadow = this.addTextProperties.getTextShadow();
            this.previewText.setShadowLayer((float) textShadow.getRadius(), (float) textShadow.getDx(), (float) textShadow.getDy(), textShadow.getColorShadow());
        }
        this.previewText.invalidate();
    }

    private void setDefaultStyleForEdittext() {
        this.mAddTextEditText.requestFocus();
        this.mAddTextEditText.setTextSize(20.0f);
        this.mAddTextEditText.setTextAlignment(4);
        this.mAddTextEditText.setTextColor(Color.parseColor("#424949"));
    }

    private void initAddTextLayout() {
        this.textFunctions = getTextFunctions();
        this.showKeyboard.setOnClickListener(this);
        this.changeFont.setOnClickListener(this);
        this.changeColor.setOnClickListener(this);
        this.changeAlign.setOnClickListener(this);
        this.saveChange.setOnClickListener(this);
        this.changeFontLayout.setVisibility(8);
        this.changeColorLayout.setVisibility(8);
        this.arrowTextTexture.setVisibility(4);
        this.highlightTextTexture.setVisibility(8);
        this.backgroundWidth.setProgress(this.addTextProperties.getPaddingWidth());
        this.colorItems = getColorItems();
        this.textTextureItems = getTextTextures();
    }

    public void onResume() {
        super.onResume();
        ViewCompat.setOnApplyWindowInsetsListener(getDialog().getWindow().getDecorView(), new OnApplyWindowInsetsListener() {
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return ViewCompat.onApplyWindowInsets(TextEditorDialogFragment.this.getDialog().getWindow().getDecorView(), windowInsetsCompat.replaceSystemWindowInsets(windowInsetsCompat.getSystemWindowInsetLeft(), 0, windowInsetsCompat.getSystemWindowInsetRight(), windowInsetsCompat.getSystemWindowInsetBottom()));
            }
        });
    }

    public void updateAddTextBottomToolbarHeight(final int i) {
        new Handler().post(new Runnable() {
            public void run() {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) TextEditorDialogFragment.this.addTextBottomToolbar.getLayoutParams();
                layoutParams.bottomMargin = i;
                TextEditorDialogFragment.this.addTextBottomToolbar.setLayoutParams(layoutParams);
                TextEditorDialogFragment.this.addTextBottomToolbar.invalidate();
                ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) TextEditorDialogFragment.this.changeFontLayout.getLayoutParams();
                layoutParams2.height = i;
                TextEditorDialogFragment.this.changeFontLayout.setLayoutParams(layoutParams2);
                TextEditorDialogFragment.this.changeFontLayout.invalidate();
                ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) TextEditorDialogFragment.this.changeColorLayout.getLayoutParams();
                layoutParams3.height = i;
                TextEditorDialogFragment.this.changeColorLayout.setLayoutParams(layoutParams3);
                TextEditorDialogFragment.this.changeColorLayout.invalidate();
            }
        });
    }

    public void setOnTextEditorListener(TextEditor textEditor2) {
        this.textEditor = textEditor2;
    }

    private void highlightFunction(ImageView imageView) {
        for (ImageView next : this.textFunctions) {
            if (next == imageView) {
                imageView.setBackground(getResources().getDrawable(C1084R.C1086drawable.highlight));
            } else {
                next.setBackground(getResources().getDrawable(C1084R.C1086drawable.fake_highlight));
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1084R.C1087id.changeAlign:
                if (this.addTextProperties.getTextAlign() == 4) {
                    this.addTextProperties.setTextAlign(3);
                    this.changeAlign.setImageDrawable(getResources().getDrawable(C1084R.C1086drawable.img_alignment_right));
                } else if (this.addTextProperties.getTextAlign() == 3) {
                    this.addTextProperties.setTextAlign(2);
                    this.changeAlign.setImageDrawable(getResources().getDrawable(C1084R.C1086drawable.img_alignment_left));
                } else if (this.addTextProperties.getTextAlign() == 2) {
                    this.addTextProperties.setTextAlign(4);
                    this.changeAlign.setImageDrawable(getResources().getDrawable(C1084R.C1086drawable.img_alignment_center));
                }
                this.previewText.setTextAlignment(this.addTextProperties.getTextAlign());
                TextView textView = this.previewText;
                textView.setText(this.previewText.getText().toString().trim() + " ");
                this.previewText.setText(this.previewText.getText().toString().trim());
                return;
            case C1084R.C1087id.changeColor:
                this.mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                this.changeColorLayout.setVisibility(0);
                toggleTextEditEditable(false);
                highlightFunction(this.changeColor);
                this.changeFontLayout.setVisibility(8);
                this.mAddTextEditText.setVisibility(8);
                this.colorPicker.setCurrentItem(this.addTextProperties.getTextColorIndex());
                this.textTexturePicker.setCurrentItem(this.addTextProperties.getTextShaderIndex());
                this.textTransparent.setProgress(255 - this.addTextProperties.getTextAlpha());
                this.switchBackgroundTexture.setChecked(this.addTextProperties.isShowBackground());
                this.backgroundColorCarousel.setCurrentItem(this.addTextProperties.getBackgroundColorIndex());
                this.backgroundTransparent.setProgress(255 - this.addTextProperties.getBackgroundAlpha());
                this.backgroundFullScreen.setChecked(this.addTextProperties.isFullScreen());
                this.backgroundBorder.setProgress(this.addTextProperties.getBackgroundBorder());
                this.backgroundWidth.setProgress(this.addTextProperties.getPaddingWidth());
                this.backgroundHeight.setProgress(this.addTextProperties.getPaddingHeight());
                this.switchBackgroundTexture.setChecked(this.addTextProperties.isShowBackground());
                if (this.addTextProperties.getTextShader() != null && this.arrowTextTexture.getVisibility() == 4) {
                    this.arrowTextTexture.setVisibility(0);
                    this.highlightTextTexture.setVisibility(0);
                    this.colorArrow.setVisibility(4);
                    this.highlightColor.setVisibility(8);
                    return;
                }
                return;
            case C1084R.C1087id.changeFont:
                this.mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                this.changeFontLayout.setVisibility(0);
                this.changeColorLayout.setVisibility(8);
                this.mAddTextEditText.setVisibility(8);
                toggleTextEditEditable(false);
                highlightFunction(this.changeFont);
                this.textSize.setProgress(this.addTextProperties.getTextSize());
                this.fontAdapter.setSelectedItem(this.addTextProperties.getFontIndex());
                this.shadowAdapter.setSelectedItem(this.addTextProperties.getTextShadowIndex());
                return;
            case C1084R.C1087id.saveChange:
                if (this.addTextProperties.getText() == null || this.addTextProperties.getText().length() == 0) {
                    this.mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    this.textEditor.onBackButton();
                    dismiss();
                    return;
                }
                this.addTextProperties.setTextWidth(this.previewText.getMeasuredWidth());
                this.addTextProperties.setTextHeight(this.previewText.getMeasuredHeight());
                this.mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                this.textEditor.onDone(this.addTextProperties);
                dismiss();
                return;
            case C1084R.C1087id.showKeyboard:
                toggleTextEditEditable(true);
                this.mAddTextEditText.setVisibility(0);
                this.mAddTextEditText.requestFocus();
                highlightFunction(this.showKeyboard);
                this.changeFontLayout.setVisibility(8);
                this.changeColorLayout.setVisibility(8);
                this.addTextBottomToolbar.invalidate();
                this.mInputMethodManager.toggleSoftInput(2, 0);
                return;
            default:
                return;
        }
    }

    private void toggleTextEditEditable(boolean z) {
        this.mAddTextEditText.setFocusable(z);
        this.mAddTextEditText.setFocusableInTouchMode(z);
        this.mAddTextEditText.setClickable(z);
    }

    private List<ImageView> getTextFunctions() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.showKeyboard);
        arrayList.add(this.changeFont);
        arrayList.add(this.changeColor);
        arrayList.add(this.changeAlign);
        arrayList.add(this.saveChange);
        return arrayList;
    }

    public List<CarouselPicker.PickerItem> getTextTextures() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 15; i++) {
            try {
                AssetManager assets = getContext().getAssets();
                arrayList.add(new CarouselPicker.DrawableItem(Drawable.createFromStream(assets.open("text_texture/" + (i + 1) + ".jpg"), (String) null)));
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    public List<CarouselPicker.PickerItem> getColorItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CarouselPicker.ColorItem("#f1948a"));
        arrayList.add(new CarouselPicker.ColorItem("#e74c3c"));
        arrayList.add(new CarouselPicker.ColorItem("#DC143C"));
        arrayList.add(new CarouselPicker.ColorItem("#FF0000"));
        arrayList.add(new CarouselPicker.ColorItem("#bb8fce"));
        arrayList.add(new CarouselPicker.ColorItem("#8e44ad"));
        arrayList.add(new CarouselPicker.ColorItem("#6c3483"));
        arrayList.add(new CarouselPicker.ColorItem("#FF00FF"));
        arrayList.add(new CarouselPicker.ColorItem("#3498db"));
        arrayList.add(new CarouselPicker.ColorItem("#2874a6"));
        arrayList.add(new CarouselPicker.ColorItem("#1b4f72"));
        arrayList.add(new CarouselPicker.ColorItem("#0000FF"));
        arrayList.add(new CarouselPicker.ColorItem("#73c6b6"));
        arrayList.add(new CarouselPicker.ColorItem("#16a085"));
        arrayList.add(new CarouselPicker.ColorItem("#117a65"));
        arrayList.add(new CarouselPicker.ColorItem("#0b5345"));
        arrayList.add(new CarouselPicker.ColorItem("#ffffff"));
        arrayList.add(new CarouselPicker.ColorItem("#d7dbdd"));
        arrayList.add(new CarouselPicker.ColorItem("#bdc3c7"));
        arrayList.add(new CarouselPicker.ColorItem("#909497"));
        arrayList.add(new CarouselPicker.ColorItem("#626567"));
        arrayList.add(new CarouselPicker.ColorItem("#000000"));
        arrayList.add(new CarouselPicker.ColorItem("#239b56"));
        arrayList.add(new CarouselPicker.ColorItem("#186a3b"));
        arrayList.add(new CarouselPicker.ColorItem("#f8c471"));
        arrayList.add(new CarouselPicker.ColorItem("#f39c12"));
        arrayList.add(new CarouselPicker.ColorItem("#FFA500"));
        arrayList.add(new CarouselPicker.ColorItem("#FFFF00"));
        arrayList.add(new CarouselPicker.ColorItem("#7e5109"));
        arrayList.add(new CarouselPicker.ColorItem("#e59866"));
        arrayList.add(new CarouselPicker.ColorItem("#d35400"));
        arrayList.add(new CarouselPicker.ColorItem("#a04000"));
        arrayList.add(new CarouselPicker.ColorItem("#6e2c00"));
        arrayList.add(new CarouselPicker.ColorItem("#808b96"));
        arrayList.add(new CarouselPicker.ColorItem("#2c3e50"));
        arrayList.add(new CarouselPicker.ColorItem("#212f3d"));
        arrayList.add(new CarouselPicker.ColorItem("#17202a"));
        return arrayList;
    }
}