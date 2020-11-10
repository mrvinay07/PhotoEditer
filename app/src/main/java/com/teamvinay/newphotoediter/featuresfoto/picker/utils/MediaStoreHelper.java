package com.teamvinay.newphotoediter.featuresfoto.picker.utils;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.picker.PhotoPicker;
import com.teamvinay.newphotoediter.featuresfoto.picker.entity.PhotoDirectory;

public class MediaStoreHelper {
    public static final int INDEX_ALL_PHOTOS = 0;

    public interface PhotosResultCallback {
        void onResultCallback(List<PhotoDirectory> list);
    }

    public static void getPhotoDirs(FragmentActivity fragmentActivity, Bundle bundle, PhotosResultCallback photosResultCallback) {
        fragmentActivity.getSupportLoaderManager().initLoader(0, bundle, new PhotoDirLoaderCallbacks(fragmentActivity, photosResultCallback));
    }

    private static class PhotoDirLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
        private Context context;
        private PhotosResultCallback resultCallback;

        public void onLoaderReset(Loader<Cursor> loader) {
        }

        public PhotoDirLoaderCallbacks(Context context2, PhotosResultCallback photosResultCallback) {
            this.context = context2;
            this.resultCallback = photosResultCallback;
        }

        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            return new PhotoDirectoryLoader(this.context, bundle.getBoolean(PhotoPicker.EXTRA_SHOW_GIF, false));
        }

        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            if (cursor != null) {
                ArrayList arrayList = new ArrayList();
                PhotoDirectory photoDirectory = new PhotoDirectory();
                photoDirectory.setName(this.context.getString(C1084R.string.all_image));
                photoDirectory.setId("ALL");
                if (cursor.moveToFirst()) {
                    do {
                        int i = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("bucket_id"));
                        String string2 = cursor.getString(cursor.getColumnIndexOrThrow("bucket_display_name"));
                        String string3 = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        if (((long) cursor.getInt(cursor.getColumnIndexOrThrow("_size"))) >= 1) {
                            PhotoDirectory photoDirectory2 = new PhotoDirectory();
                            photoDirectory2.setId(string);
                            photoDirectory2.setName(string2);
                            if (!arrayList.contains(photoDirectory2)) {
                                photoDirectory2.setCoverPath(string3);
                                photoDirectory2.addPhoto(i, string3);
                                photoDirectory2.setDateAdded(cursor.getLong(cursor.getColumnIndexOrThrow("date_added")));
                                arrayList.add(photoDirectory2);
                            } else {
                                ((PhotoDirectory) arrayList.get(arrayList.indexOf(photoDirectory2))).addPhoto(i, string3);
                            }
                            photoDirectory.addPhoto(i, string3);
                        }
                    } while (cursor.moveToNext());
                }
                if (photoDirectory.getPhotoPaths().size() > 0) {
                    photoDirectory.setCoverPath(photoDirectory.getPhotoPaths().get(0));
                }
                Collections.sort(arrayList);
                arrayList.add(0, photoDirectory);
                if (this.resultCallback != null) {
                    this.resultCallback.onResultCallback(arrayList);
                }
            }
        }
    }
}
