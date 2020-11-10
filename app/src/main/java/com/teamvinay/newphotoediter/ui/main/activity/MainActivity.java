package com.teamvinay.newphotoediter.ui.main.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;

import com.google.ads.consent.ConsentForm;
import java.io.IOException;
import com.teamvinay.newphotoediter.BaseActivity;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.R;
import com.teamvinay.newphotoediter.featuresfoto.picker.PhotoPicker;
import com.teamvinay.newphotoediter.featuresfoto.picker.utils.ImageCaptureManager;
import com.teamvinay.newphotoediter.featuresfoto.picker.utils.PermissionsUtils;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.activity.PickImageActivity;

/* renamed from: lisa.studio.photoeditor.ui.main.activity.MainActivity */
public class MainActivity extends BaseActivity {
    private ImageCaptureManager captureManager;
    private ConsentForm form;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        public final void onClick(View view) {
            MainActivity.lambda$new$2(MainActivity.this, view);
        }
    };
    private ViewGroup viewGroup;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setTheme(C1084R.style.AppTheme_NoActionBar);
        super.onCreate(bundle);
        makeFullScreen();
        setContentView((int) C1084R.C1089layout.activity_main_two);
        this.viewGroup = (ViewGroup) findViewById(16908290);
        ((LinearLayout) findViewById(C1084R.C1087id.editFunction)).setOnClickListener(this.onClickListener);
        ((RelativeLayout) findViewById(C1084R.C1087id.takePhoto)).setOnClickListener(this.onClickListener);
        ((LinearLayout) findViewById(C1084R.C1087id.collage)).setOnClickListener(this.onClickListener);
        this.captureManager = new ImageCaptureManager(this);
        findViewById(R.id.action_item_setting).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
        findViewById(C1084R.C1087id.ratingApp).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               // UtilsApp.RateApp(MainActivity.this);
            }
        });
      //  AdmobHelp.getInstance().loadNative(this);
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            super.onActivityResult(i, i2, intent);
        } else if (i == 1) {
            if (this.captureManager == null) {
                this.captureManager = new ImageCaptureManager(this);
            }
            new Handler().post(new Runnable() {
                public final void run() {
                    MainActivity.this.captureManager.galleryAddPic();
                }
            });
            Intent intent2 = new Intent(getApplicationContext(), EditImageActivity.class);
            intent2.putExtra(PhotoPicker.KEY_SELECTED_PHOTOS, this.captureManager.getCurrentPhotoPath());
            startActivity(intent2);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public static /* synthetic */ void lambda$new$2(MainActivity mainActivity, View view) {
        int id = view.getId();
        if (id != C1084R.C1087id.collage) {
            if (id != C1084R.C1087id.editFunction) {
                if (id == C1084R.C1087id.takePhoto && PermissionsUtils.checkCameraPermission((Activity) mainActivity) && PermissionsUtils.checkWriteStoragePermission((Activity) mainActivity)) {
                    mainActivity.openCamera();
                }
            } else if (PermissionsUtils.checkWriteStoragePermission((Activity) mainActivity)) {
                PhotoPicker.builder().setPhotoCount(1).setPreviewEnabled(false).setShowCamera(false).start(mainActivity);
            }
        } else if (PermissionsUtils.checkWriteStoragePermission((Activity) mainActivity)) {
            Intent intent = new Intent(mainActivity, PickImageActivity.class);
            intent.putExtra(PickImageActivity.KEY_LIMIT_MAX_IMAGE, 9);
            intent.putExtra(PickImageActivity.KEY_LIMIT_MIN_IMAGE, 2);
            mainActivity.startActivityForResult(intent, 1001);
        }
    }

    private void openCamera() {
        try {
            startActivityForResult(this.captureManager.dispatchTakePictureIntent(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

  /*  public void onBackPressed() {
        Rate.Show(this, 1);
    }*/
}
