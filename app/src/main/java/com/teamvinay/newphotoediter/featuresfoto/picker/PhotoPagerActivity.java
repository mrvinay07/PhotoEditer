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
import com.teamvinay.newphotoediter.featuresfoto.picker.fragment.ImagePagerFragment;

public class PhotoPagerActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private ImagePagerFragment pagerFragment;
    private boolean showDelete;

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1084R.C1089layout.__picker_activity_photo_pager);
        int intExtra = getIntent().getIntExtra(PhotoPreview.EXTRA_CURRENT_ITEM, 0);
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra(PhotoPreview.EXTRA_PHOTOS);
        this.showDelete = getIntent().getBooleanExtra(PhotoPreview.EXTRA_SHOW_DELETE, true);
        if (this.pagerFragment == null) {
            this.pagerFragment = (ImagePagerFragment) getSupportFragmentManager().findFragmentById(C1084R.C1087id.photoPagerFragment);
        }
        this.pagerFragment.setPhotos(stringArrayListExtra, intExtra);
        setSupportActionBar((Toolbar) findViewById(C1084R.C1087id.toolbar));
        this.actionBar = getSupportActionBar();
        if (this.actionBar != null) {
            this.actionBar.setDisplayHomeAsUpEnabled(true);
            if (Build.VERSION.SDK_INT >= 21) {
                this.actionBar.setElevation(25.0f);
            }
        }
      //  AdmobHelp.getInstance().loadBanner(this);
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(PhotoPicker.KEY_SELECTED_PHOTOS, this.pagerFragment.getPaths());
        setResult(-1, intent);
        finish();
        super.onBackPressed();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}