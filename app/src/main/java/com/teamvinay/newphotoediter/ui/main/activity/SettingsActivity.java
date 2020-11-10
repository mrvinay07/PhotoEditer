package com.teamvinay.newphotoediter.ui.main.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.teamvinay.newphotoediter.BaseActivity;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.R;

/* renamed from: lisa.studio.photoeditor.ui.main.activity.SettingsActivity */
public class SettingsActivity extends BaseActivity {
    /* access modifiers changed from: protected */
   /* public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        makeFullScreen();
        setContentView(R.layout.setting_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle((CharSequence) getResources().getString(C1084R.string.settings));
        findViewById(C1084R.C1087id.rate_us).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                UtilsApp.RateApp(SettingsActivity.this);
            }
        });
        findViewById(C1084R.C1087id.share_app).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UtilsApp.shareApp(SettingsActivity.this);
            }
        });
        findViewById(C1084R.C1087id.feedback).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UtilsApp.SendFeedBack(SettingsActivity.this, SettingsActivity.this.getString(C1084R.string.email_feedback), SettingsActivity.this.getString(C1084R.string.Title_email));
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }*/
}
