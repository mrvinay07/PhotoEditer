package com.teamvinay.newphotoediter.ui.view;

public class SaveSettings {
    private boolean isClearViewsEnabled;
    private boolean isTransparencyEnabled;

    /* access modifiers changed from: package-private */
    public boolean isTransparencyEnabled() {
        return this.isTransparencyEnabled;
    }

    /* access modifiers changed from: package-private */
    public boolean isClearViewsEnabled() {
        return this.isClearViewsEnabled;
    }

    private SaveSettings(Builder builder) {
        this.isClearViewsEnabled = builder.isClearViewsEnabled;
        this.isTransparencyEnabled = builder.isTransparencyEnabled;
    }

    /* renamed from: lisa.studio.photoeditor.ui.view.SaveSettings$Builder */
    public static class Builder {
        /* access modifiers changed from: private */
        public boolean isClearViewsEnabled = true;
        /* access modifiers changed from: private */
        public boolean isTransparencyEnabled = true;

        public Builder setTransparencyEnabled(boolean z) {
            this.isTransparencyEnabled = z;
            return this;
        }

        public Builder setClearViewsEnabled(boolean z) {
            this.isClearViewsEnabled = z;
            return this;
        }

        public SaveSettings build() {
            return new SaveSettings(this);
        }
    }
}