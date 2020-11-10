package com.teamvinay.newphotoediter.featuresfoto.picker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
//import com.ads.control.AdmobHelp;
import java.util.ArrayList;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.picker.entity.Photo;
import com.teamvinay.newphotoediter.featuresfoto.picker.event.OnItemCheckListener;
import com.teamvinay.newphotoediter.featuresfoto.picker.fragment.ImagePagerFragment;
import com.teamvinay.newphotoediter.featuresfoto.picker.fragment.PhotoPickerFragment;
import com.teamvinay.newphotoediter.ui.main.activity.EditImageActivity;
import com.teamvinay.newphotoediter.ui.main.activity.PuzzleViewActivity;

public class PhotoPickerActivity extends AppCompatActivity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean forwardMain;
    private ImagePagerFragment imagePagerFragment;
    private int maxCount = 9;
    private ArrayList<String> originalPhotos = null;
    private PhotoPickerFragment pickerFragment;
    private boolean showGif = false;

    public PhotoPickerActivity getActivity() {
        return this;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        boolean booleanExtra = getIntent().getBooleanExtra(PhotoPicker.EXTRA_SHOW_CAMERA, true);
        boolean booleanExtra2 = getIntent().getBooleanExtra(PhotoPicker.EXTRA_SHOW_GIF, false);
        boolean booleanExtra3 = getIntent().getBooleanExtra(PhotoPicker.EXTRA_PREVIEW_ENABLED, true);
        this.forwardMain = getIntent().getBooleanExtra(PhotoPicker.MAIN_ACTIVITY, false);
        setShowGif(booleanExtra2);
        setContentView((int) C1084R.C1089layout.__picker_activity_photo_picker);
      //  AdmobHelp.getInstance().loadBanner(this);
        setSupportActionBar((Toolbar) findViewById(C1084R.C1087id.toolbar));
        setTitle(getResources().getString(C1084R.string.tap_to_select));
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            supportActionBar.setElevation(25.0f);
        }
        this.maxCount = getIntent().getIntExtra(PhotoPicker.EXTRA_MAX_COUNT, 9);
        int intExtra = getIntent().getIntExtra(PhotoPicker.EXTRA_GRID_COLUMN, 3);
        this.originalPhotos = getIntent().getStringArrayListExtra(PhotoPicker.EXTRA_ORIGINAL_PHOTOS);
        this.pickerFragment = (PhotoPickerFragment) getSupportFragmentManager().findFragmentByTag("tag");
        if (this.pickerFragment == null) {
            this.pickerFragment = PhotoPickerFragment.newInstance(booleanExtra, booleanExtra2, booleanExtra3, intExtra, this.maxCount, this.originalPhotos);
            getSupportFragmentManager().beginTransaction().replace(C1084R.C1087id.container, this.pickerFragment, "tag").commit();
            getSupportFragmentManager().executePendingTransactions();
        }
        this.pickerFragment.getPhotoGridAdapter().setOnItemCheckListener(new OnItemCheckListener() {
            public final boolean onItemCheck(int i, Photo photo, int i2) {
                return PhotoPickerActivity.lambda$onCreate$0(PhotoPickerActivity.this, i, photo, i2);
            }
        });
    }

    public static /* synthetic */ boolean lambda$onCreate$0(PhotoPickerActivity photoPickerActivity, int i, Photo photo, int i2) {
        if (!photoPickerActivity.forwardMain) {
            Intent intent = new Intent(photoPickerActivity.getApplicationContext(), EditImageActivity.class);
            intent.putExtra(PhotoPicker.KEY_SELECTED_PHOTOS, photo.getPath());
            photoPickerActivity.startActivity(intent);
            return true;
        }
       // PuzzleViewActivity.getInstance().replaceCurrentPiece(photo.getPath());
        photoPickerActivity.finish();
        return true;
    }

    public void onBackPressed() {
        if (this.imagePagerFragment == null || !this.imagePagerFragment.isVisible()) {
            super.onBackPressed();
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean isShowGif() {
        return this.showGif;
    }

    public void setShowGif(boolean z) {
        this.showGif = z;
    }
}