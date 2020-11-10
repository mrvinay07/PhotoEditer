package com.teamvinay.newphotoediter.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import java.io.File;
import com.teamvinay.newphotoediter.BaseActivity;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.utils.FileUtils;

/* renamed from: lisa.studio.photoeditor.ui.main.activity.SaveAndShareActivity */
public class SaveAndShareActivity extends BaseActivity {
    /*static final *//* synthetic *//* boolean $assertionsDisabled = false;
    File imgFile;
    private ViewGroup viewGroup;

    *//* access modifiers changed from: protected *//*
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        makeFullScreen();
        setContentView((int) C1084R.C1089layout.save_and_share_layout);
        setSupportActionBar((Toolbar) findViewById(C1084R.C1087id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle((CharSequence) getResources().getString(C1084R.string.save_and_share));
        this.viewGroup = (ViewGroup) findViewById(16908290);
        String string = getIntent().getExtras().getString("path");
        this.imgFile = new File(string);
        Glide.with(getApplicationContext()).load(this.imgFile).into((ImageView) findViewById(C1084R.C1087id.preview));
        findViewById(C1084R.C1087id.preview).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SaveAndShareActivity.lambda$onCreate$0(SaveAndShareActivity.this, view);
            }
        });
        ((TextView) findViewById(C1084R.C1087id.path)).setText(string);
        findViewById(C1084R.C1087id.btnMore).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SaveAndShareActivity.lambda$onCreate$1(SaveAndShareActivity.this, view);
            }
        });
        findViewById(C1084R.C1087id.btnFace).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SaveAndShareActivity.this.shareImageWhatsApp("com.facebook.katana", "Facebook");
            }
        });
        findViewById(C1084R.C1087id.btnInta).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SaveAndShareActivity.this.shareImageWhatsApp("com.instagram.android", "Instagram");
            }
        });
       // AdmobHelp.getInstance().loadNative(this);
    }

    public static *//* synthetic *//* void lambda$onCreate$0(SaveAndShareActivity saveAndShareActivity, View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(FileProvider.getUriForFile(saveAndShareActivity.getApplicationContext(), "lisa.studio.photoeditor.provider", saveAndShareActivity.imgFile), FileUtils.MIME_TYPE_IMAGE);
        intent.addFlags(3);
        saveAndShareActivity.startActivity(intent);
    }

    public static *//* synthetic *//* void lambda$onCreate$1(SaveAndShareActivity saveAndShareActivity, View view) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(FileUtils.MIME_TYPE_IMAGE);
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(saveAndShareActivity.getApplicationContext(), "lisa.studio.photoeditor.provider", saveAndShareActivity.imgFile));
        intent.addFlags(3);
        saveAndShareActivity.startActivity(Intent.createChooser(intent, "Share"));
    }

    public void shareImageWhatsApp(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", getString(C1084R.string.app_name) + "Try it Now https://play.google.com/store/apps/details?id=" + getPackageName());
        intent.setType(FileUtils.MIME_TYPE_IMAGE);
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(this, "lisa.studio.photoeditor.provider", this.imgFile));
        if (isPackageInstalled(str, this)) {
            intent.setPackage(str);
            startActivity(Intent.createChooser(intent, "Share"));
            return;
        }
        Context applicationContext = getApplicationContext();
        Toast.makeText(applicationContext, "Please Install " + str2, 1).show();
    }

    private boolean isPackageInstalled(String str, Context context) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C1084R.C1090menu.menusave, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            AdmobHelp.getInstance().showInterstitialAd(new AdmobHelp.AdCloseListener() {
                public void onAdClosed() {
                    SaveAndShareActivity.this.finish();
                }
            }, this);
            return true;
        } else if (itemId != C1084R.C1087id.menu_home) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            AdmobHelp.getInstance().showInterstitialAd(new AdmobHelp.AdCloseListener() {
                public void onAdClosed() {
                    Intent intent = new Intent(SaveAndShareActivity.this.getApplicationContext(), MainActivity.class);
                    intent.setFlags(268468224);
                    SaveAndShareActivity.this.startActivity(intent);
                    SaveAndShareActivity.this.finish();
                }
            }, this);
            return true;
        }
    }

    *//* access modifiers changed from: protected *//*
    public void onResume() {
        super.onResume();
    }

    public void onBackPressed() {
        AdmobHelp.getInstance().showInterstitialAd(new AdmobHelp.AdCloseListener() {
            public void onAdClosed() {
                SaveAndShareActivity.this.finish();
            }
        }, this);
    }*/
}