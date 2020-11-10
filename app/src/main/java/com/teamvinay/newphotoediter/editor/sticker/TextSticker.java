package com.teamvinay.newphotoediter.editor.sticker;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.teamvinay.newphotoediter.featuresfoto.addtext.AddTextProperties;
import com.teamvinay.newphotoediter.util.SystemUtil;

public class TextSticker extends Sticker {
    private static final String mEllipsis = "…";
    private AddTextProperties addTextProperties;
    private int backgroundAlpha;
    private int backgroundBorder;
    private int backgroundColor;
    private BitmapDrawable backgroundDrawable;
    private final Context context;
    private Drawable drawable;
    private boolean isShowBackground;
    private float lineSpacingExtra = 0.0f;
    private float lineSpacingMultiplier = 1.0f;
    private float maxTextSizePixels;
    private float minTextSizePixels;
    private int paddingHeight;
    private int paddingWidth;
    private StaticLayout staticLayout;
    private String text;
    private Layout.Alignment textAlign;
    private int textAlpha;
    private int textColor;
    private int textHeight;
    private final TextPaint textPaint;
    private AddTextProperties.TextShadow textShadow;
    private int textWidth;

    public TextSticker(@NonNull Context context2) {
        this.context = context2;
        this.textPaint = new TextPaint(1);
        this.textPaint.setTextSize(25.0f);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public TextSticker(@NonNull Context context2, AddTextProperties addTextProperties2) {
        this.context = context2;
        this.addTextProperties = addTextProperties2;
        this.textPaint = new TextPaint(1);
        TextSticker textColor2 = setTextSize(addTextProperties2.getTextSize()).setTextWidth(addTextProperties2.getTextWidth()).setTextHeight(addTextProperties2.getTextHeight()).setText(addTextProperties2.getText()).setPaddingWidth(SystemUtil.dpToPx(context2, addTextProperties2.getPaddingWidth())).setBackgroundBorder(SystemUtil.dpToPx(context2, addTextProperties2.getBackgroundBorder())).setTextShadow(addTextProperties2.getTextShadow()).setTextColor(addTextProperties2.getTextColor()).setTextAlpha(addTextProperties2.getTextAlpha()).setBackgroundColor(addTextProperties2.getBackgroundColor()).setBackgroundAlpha(addTextProperties2.getBackgroundAlpha()).setShowBackground(addTextProperties2.isShowBackground()).setTextColor(addTextProperties2.getTextColor());
        AssetManager assets = context2.getAssets();
        textColor2.setTypeface(Typeface.createFromAsset(assets, "fonts/" + addTextProperties2.getFontName())).setTextAlign(addTextProperties2.getTextAlign()).setTextShare(addTextProperties2.getTextShader()).resizeText();
    }

    public void draw(@NonNull Canvas canvas) {
        Matrix matrix = getMatrix();
        canvas.save();
        canvas.concat(matrix);
        if (this.isShowBackground) {
            Paint paint = new Paint();
            if (this.backgroundDrawable != null) {
                paint.setShader(new BitmapShader(this.backgroundDrawable.getBitmap(), Shader.TileMode.MIRROR, Shader.TileMode.MIRROR));
                paint.setAlpha(this.backgroundAlpha);
            } else {
                paint.setARGB(this.backgroundAlpha, Color.red(this.backgroundColor), Color.green(this.backgroundColor), Color.blue(this.backgroundColor));
            }
            canvas.drawRoundRect(0.0f, 0.0f, (float) this.textWidth, (float) this.textHeight, (float) this.backgroundBorder, (float) this.backgroundBorder, paint);
            canvas.restore();
            canvas.save();
            canvas.concat(matrix);
        }
        canvas.restore();
        canvas.save();
        canvas.concat(matrix);
        canvas.translate((float) this.paddingWidth, (float) ((this.textHeight / 2) - (this.staticLayout.getHeight() / 2)));
        this.staticLayout.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.concat(matrix);
        canvas.restore();
    }

    public AddTextProperties getAddTextProperties() {
        return this.addTextProperties;
    }

    public TextSticker setTextShadow(AddTextProperties.TextShadow textShadow2) {
        this.textShadow = textShadow2;
        return this;
    }

    public TextSticker setBackgroundBorder(int i) {
        this.backgroundBorder = i;
        return this;
    }

    public TextSticker setShowBackground(boolean z) {
        this.isShowBackground = z;
        return this;
    }

    public BitmapDrawable getBackgroundDrawable() {
        return this.backgroundDrawable;
    }

    public TextSticker setBackgroundDrawable(BitmapDrawable bitmapDrawable) {
        this.backgroundDrawable = bitmapDrawable;
        return this;
    }

    public int getWidth() {
        return this.textWidth;
    }

    public int getHeight() {
        return this.textHeight;
    }

    public void release() {
        super.release();
        if (this.drawable != null) {
            this.drawable = null;
        }
    }

    public int getTextAlpha() {
        return this.textAlpha;
    }

    public TextSticker setTextAlpha(int i) {
        this.textAlpha = i;
        return this;
    }

    @NonNull
    public TextSticker setAlpha(@IntRange(from = 0, mo93to = 255) int i) {
        this.textPaint.setAlpha(i);
        return this;
    }

    public int getAlpha() {
        return this.textPaint.getAlpha();
    }

    @NonNull
    public Drawable getDrawable() {
        return this.drawable;
    }

    public TextSticker setDrawable(@NonNull Drawable drawable2) {
        this.drawable = drawable2;
        return this;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public TextSticker setBackgroundColor(int i) {
        this.backgroundColor = i;
        return this;
    }

    public int getBackgroundAlpha() {
        return this.backgroundAlpha;
    }

    public TextSticker setBackgroundAlpha(int i) {
        this.backgroundAlpha = i;
        return this;
    }

    public TextSticker setTextWidth(int i) {
        this.textWidth = i;
        return this;
    }

    public TextSticker setTextHeight(int i) {
        this.textHeight = i;
        return this;
    }

    @NonNull
    public TextSticker setDrawable(@NonNull Drawable drawable2, @Nullable Rect rect) {
        this.drawable = drawable2;
        return this;
    }

    @NonNull
    public TextSticker setTypeface(@Nullable Typeface typeface) {
        this.textPaint.setTypeface(typeface);
        return this;
    }

    @NonNull
    public TextSticker setTextSize(int i) {
        this.textPaint.setTextSize(convertSpToPx((float) i));
        return this;
    }

    @NonNull
    public TextSticker setTextShare(@Nullable Shader shader) {
        this.textPaint.setShader(shader);
        return this;
    }

    @NonNull
    public TextSticker setTextColor(@ColorInt int i) {
        this.textColor = i;
        return this;
    }

    @NonNull
    public TextSticker setTextAlign(@NonNull int i) {
        switch (i) {
            case 2:
                this.textAlign = Layout.Alignment.ALIGN_NORMAL;
                break;
            case 3:
                this.textAlign = Layout.Alignment.ALIGN_OPPOSITE;
                break;
            case 4:
                this.textAlign = Layout.Alignment.ALIGN_CENTER;
                break;
        }
        return this;
    }

    public int getPaddingWidth() {
        return this.paddingWidth;
    }

    public TextSticker setPaddingWidth(int i) {
        this.paddingWidth = i;
        return this;
    }

    public int getPaddingHeight() {
        return this.paddingHeight;
    }

    public TextSticker setPaddingHeight(int i) {
        this.paddingHeight = i;
        return this;
    }

    @NonNull
    public TextSticker setMaxTextSize(@Dimension(unit = 2) float f) {
        this.textPaint.setTextSize(convertSpToPx(f));
        this.maxTextSizePixels = this.textPaint.getTextSize();
        return this;
    }

    @NonNull
    public TextSticker setMinTextSize(float f) {
        this.minTextSizePixels = convertSpToPx(f);
        return this;
    }

    @NonNull
    public TextSticker setLineSpacing(float f, float f2) {
        this.lineSpacingMultiplier = f2;
        this.lineSpacingExtra = f;
        return this;
    }

    @NonNull
    public TextSticker setText(@Nullable String str) {
        this.text = str;
        return this;
    }

    @Nullable
    public String getText() {
        return this.text;
    }

    public void setShadow(int i, int i2, int i3) {
        this.textPaint.setShadowLayer((float) i, (float) i2, (float) i3, this.textColor);
    }

    @NonNull
    public TextSticker resizeText() {
        String text2 = getText();
        if (text2 == null || text2.length() <= 0) {
            return this;
        }
        if (this.textShadow != null) {
            this.textPaint.setShadowLayer((float) this.textShadow.getRadius(), (float) this.textShadow.getDx(), (float) this.textShadow.getDy(), this.textShadow.getColorShadow());
        }
        this.textPaint.setTextAlign(Paint.Align.LEFT);
        this.textPaint.setARGB(this.textAlpha, Color.red(this.textColor), Color.green(this.textColor), Color.blue(this.textColor));
        int i = this.textWidth - (this.paddingWidth * 2);
        this.staticLayout = new StaticLayout(this.text, this.textPaint, i <= 0 ? 100 : i, this.textAlign, this.lineSpacingMultiplier, this.lineSpacingExtra, true);
        return this;
    }

    public float getMinTextSizePixels() {
        return this.minTextSizePixels;
    }

    /* access modifiers changed from: protected */
    public int getTextHeightPixels(@NonNull CharSequence charSequence, int i, float f) {
        this.textPaint.setTextSize(f);
        return new StaticLayout(charSequence, this.textPaint, i, this.textAlign, this.lineSpacingMultiplier, this.lineSpacingExtra, true).getHeight();
    }

    private float convertSpToPx(float f) {
        return f * this.context.getResources().getDisplayMetrics().scaledDensity;
    }
}
