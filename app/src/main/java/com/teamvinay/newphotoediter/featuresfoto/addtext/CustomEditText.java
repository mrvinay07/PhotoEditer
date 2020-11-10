package com.teamvinay.newphotoediter.featuresfoto.addtext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatEditText;

public class CustomEditText extends AppCompatEditText {
    private TextEditorDialogFragment dialogFragment;

    public CustomEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setDialogFragment(TextEditorDialogFragment textEditorDialogFragment) {
        this.dialogFragment = textEditorDialogFragment;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4) {
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
            this.dialogFragment.dismissAndShowSticker();
        }
        return false;
    }
}