package com.teamvinay.newphotoediter.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.teamvinay.newphotoediter.R;



/* renamed from: lisa.studio.photoeditor.ui.main.activity.SplashActivity */
public class SplashActivity extends AppCompatActivity {
    Handler mHandler;

    /* renamed from: r */
    Runnable f640r;

    public void onBackPressed() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);

        this.mHandler = new Handler();
        this.f640r = new Runnable() {
            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        };
        this.mHandler.postDelayed(this.f640r, 3000);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (!(this.mHandler == null || this.f640r == null)) {
            this.mHandler.removeCallbacks(this.f640r);
        }
        super.onDestroy();
    }
}