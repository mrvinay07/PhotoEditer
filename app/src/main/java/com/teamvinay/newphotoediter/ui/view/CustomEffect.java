package com.teamvinay.newphotoediter.ui.view;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;

/* renamed from: lisa.studio.photoeditor.ui.view.CustomEffect */
public class CustomEffect {
    private String mEffectName;
    private Map<String, Object> parametersMap;

    private CustomEffect(Builder builder) {
        this.mEffectName = builder.mEffectName;
        this.parametersMap = builder.parametersMap;
    }

    public String getEffectName() {
        return this.mEffectName;
    }

    public Map<String, Object> getParameters() {
        return this.parametersMap;
    }

    /* renamed from: lisa.studio.photoeditor.ui.view.CustomEffect$Builder */
    public static class Builder {
        /* access modifiers changed from: private */
        public String mEffectName;
        /* access modifiers changed from: private */
        public Map<String, Object> parametersMap = new HashMap();

        public Builder(@NonNull String str) throws RuntimeException {
            if (!TextUtils.isEmpty(str)) {
                this.mEffectName = str;
                return;
            }
            throw new RuntimeException("Effect name cannot be empty.Please provide effect name from EffectFactory");
        }

        public Builder setParameter(@NonNull String str, Object obj) {
            this.parametersMap.put(str, obj);
            return this;
        }

        public CustomEffect build() {
            return new CustomEffect(this);
        }
    }
}
