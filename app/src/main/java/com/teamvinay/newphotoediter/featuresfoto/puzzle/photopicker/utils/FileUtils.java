package com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.utils;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.FileFilter;
import java.text.DecimalFormat;
import java.util.Comparator;

public class FileUtils {
    private static final boolean DEBUG = false;
    public static final String HIDDEN_PREFIX = ".";
    public static final String MIME_TYPE_APP = "application/*";
    public static final String MIME_TYPE_AUDIO = "audio/*";
    public static final String MIME_TYPE_IMAGE = "image/*";
    public static final String MIME_TYPE_TEXT = "text/*";
    public static final String MIME_TYPE_VIDEO = "video/*";
    public static final String PARAMETER_SHARE_DIALOG_CONTENT_VIDEO = "video";
    static final String TAG = "FileUtils";
    public static final String WEB_DIALOG_PARAM_MEDIA = "media";
    public static Comparator<File> sComparator = new C07681();
    public static FileFilter sDirFilter = new C07703();
    public static FileFilter sFileFilter = new C07692();

    static class C07681 implements Comparator<File> {
        C07681() {
        }

        public int compare(File file, File file2) {
            return file.getName().toLowerCase().compareTo(file2.getName().toLowerCase());
        }
    }

    static class C07692 implements FileFilter {
        C07692() {
        }

        public boolean accept(File file) {
            return file.isFile() && !file.getName().startsWith(FileUtils.HIDDEN_PREFIX);
        }
    }

    static class C07703 implements FileFilter {
        C07703() {
        }

        public boolean accept(File file) {
            return file.isDirectory() && !file.getName().startsWith(FileUtils.HIDDEN_PREFIX);
        }
    }

    private FileUtils() {
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(HIDDEN_PREFIX);
        return lastIndexOf >= 0 ? str.substring(lastIndexOf) : "";
    }

    public static boolean isLocal(String str) {
        return str != null && !str.startsWith("http://") && !str.startsWith("https://");
    }

    public static boolean isMediaUri(Uri uri) {
        return WEB_DIALOG_PARAM_MEDIA.equalsIgnoreCase(uri.getAuthority());
    }

    public static Uri getUri(File file) {
        if (file != null) {
            return Uri.fromFile(file);
        }
        return null;
    }

    public static File getPathWithoutFilename(File file) {
        if (file == null) {
            return null;
        }
        if (file.isDirectory()) {
            return file;
        }
        String name = file.getName();
        String absolutePath = file.getAbsolutePath();
        String substring = absolutePath.substring(0, absolutePath.length() - name.length());
        if (substring.endsWith("/")) {
            substring = substring.substring(0, substring.length() - 1);
        }
        return new File(substring);
    }

    public static String getMimeType(File file) {
        String extension = getExtension(file.getName());
        if (extension != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.substring(1));
        }
        return null;
    }

    public static String getMimeType(Context context, Uri uri) {
        Log.d("tag", "mimetype:" + uri);
        return getMimeType(new File(getPath(context, uri)));
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDataColumn(android.content.Context r7, android.net.Uri r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ all -> 0x0039 }
            java.lang.String r7 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r7}     // Catch:{ all -> 0x0039 }
            r6 = 0
            r2 = r8
            r4 = r9
            r5 = r10
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0039 }
            if (r7 == 0) goto L_0x002e
            boolean r8 = r7.moveToFirst()     // Catch:{ all -> 0x002c }
            if (r8 != 0) goto L_0x001c
            goto L_0x002e
        L_0x001c:
            java.lang.String r8 = "_data"
            int r8 = r7.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x002c }
            java.lang.String r8 = r7.getString(r8)     // Catch:{ all -> 0x002c }
            if (r7 == 0) goto L_0x002b
            r7.close()
        L_0x002b:
            return r8
        L_0x002c:
            r8 = move-exception
            goto L_0x003b
        L_0x002e:
            if (r7 == 0) goto L_0x0033
            r7.close()     // Catch:{ all -> 0x002c }
        L_0x0033:
            if (r7 == 0) goto L_0x0038
            r7.close()
        L_0x0038:
            return r0
        L_0x0039:
            r8 = move-exception
            r7 = r0
        L_0x003b:
            if (r7 == 0) goto L_0x0040
            r7.close()
        L_0x0040:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.utils.FileUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getNameColumn(android.content.Context r7, android.net.Uri r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ all -> 0x0039 }
            java.lang.String r7 = "_display_name"
            java.lang.String[] r3 = new java.lang.String[]{r7}     // Catch:{ all -> 0x0039 }
            r6 = 0
            r2 = r8
            r4 = r9
            r5 = r10
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0039 }
            if (r7 == 0) goto L_0x002e
            boolean r8 = r7.moveToFirst()     // Catch:{ all -> 0x002c }
            if (r8 != 0) goto L_0x001c
            goto L_0x002e
        L_0x001c:
            java.lang.String r8 = "_display_name"
            int r8 = r7.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x002c }
            java.lang.String r8 = r7.getString(r8)     // Catch:{ all -> 0x002c }
            if (r7 == 0) goto L_0x002b
            r7.close()
        L_0x002b:
            return r8
        L_0x002c:
            r8 = move-exception
            goto L_0x003b
        L_0x002e:
            if (r7 == 0) goto L_0x0033
            r7.close()     // Catch:{ all -> 0x002c }
        L_0x0033:
            if (r7 == 0) goto L_0x0038
            r7.close()
        L_0x0038:
            return r0
        L_0x0039:
            r8 = move-exception
            r7 = r0
        L_0x003b:
            if (r7 == 0) goto L_0x0040
            r7.close()
        L_0x0040:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: lisa.studio.photoeditor.featuresfoto.puzzle.photopicker.utils.FileUtils.getNameColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static String getPath(Context context, Uri uri) {
        Uri uri2;
        boolean z = Build.VERSION.SDK_INT >= 19;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        if (!z || !DocumentsContract.isDocumentUri(context, uri)) {
            if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, (String) null, (String[]) null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            } else {
                return null;
            }
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if (!"primary".equalsIgnoreCase(split[0])) {
                return null;
            }
            return Environment.getExternalStorageDirectory() + "/" + split[1];
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), (String) null, (String[]) null);
        } else {
            if (!isMediaDocument(uri)) {
                return null;
            }
            String str = DocumentsContract.getDocumentId(uri).split(":")[0];
            if ("image".equals(str)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if (PARAMETER_SHARE_DIALOG_CONTENT_VIDEO.equals(str)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else {
                uri2 = "audio".equals(str) ? MediaStore.Audio.Media.EXTERNAL_CONTENT_URI : null;
            }
            return getDataColumn(context, uri2, "_id=?", new String[]{null});
        }
    }

    public static File getFile(Context context, Uri uri) {
        String path;
        if (uri == null || (path = getPath(context, uri)) == null || !isLocal(path)) {
            return null;
        }
        return new File(path);
    }

    public static String getReadableFileSize(int i) {
        float f;
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        String str = " KB";
        if (i > 1024) {
            f = (float) (i / 1024);
            if (f > 1024.0f) {
                f /= 1024.0f;
                if (f > 1024.0f) {
                    f /= 1024.0f;
                    str = " GB";
                } else {
                    str = " MB";
                }
            }
        } else {
            f = 0.0f;
        }
        return String.valueOf(decimalFormat.format((double) f) + str);
    }

    public static Bitmap getThumbnail(Context context, File file) {
        return getThumbnail(context, getUri(file), getMimeType(file));
    }

    public static Bitmap getThumbnail(Context context, Uri uri) {
        return getThumbnail(context, uri, getMimeType(context, uri));
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getThumbnail(android.content.Context r8, android.net.Uri r9, java.lang.String r10) {
        /*
            boolean r0 = isMediaUri(r9)
            r1 = 0
            if (r0 == 0) goto L_0x0058
            if (r9 == 0) goto L_0x005f
            android.content.ContentResolver r8 = r8.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r2 = r8
            r3 = r9
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0051, Throwable -> 0x004a }
            boolean r0 = r9.moveToFirst()     // Catch:{ Exception -> 0x0048, Throwable -> 0x0046 }
            if (r0 == 0) goto L_0x0040
            r0 = 0
            int r0 = r9.getInt(r0)     // Catch:{ Exception -> 0x0048, Throwable -> 0x0046 }
            java.lang.String r2 = "video"
            boolean r2 = r10.contains(r2)     // Catch:{ Exception -> 0x0048, Throwable -> 0x0046 }
            r3 = 1
            if (r2 == 0) goto L_0x0032
            long r4 = (long) r0     // Catch:{ Exception -> 0x0048, Throwable -> 0x0046 }
            android.graphics.Bitmap r8 = android.provider.MediaStore.Video.Thumbnails.getThumbnail(r8, r4, r3, r1)     // Catch:{ Exception -> 0x0048, Throwable -> 0x0046 }
        L_0x0030:
            r1 = r8
            goto L_0x0040
        L_0x0032:
            java.lang.String r2 = "image/*"
            boolean r10 = r10.contains(r2)     // Catch:{ Exception -> 0x0048, Throwable -> 0x0046 }
            if (r10 == 0) goto L_0x0040
            long r4 = (long) r0     // Catch:{ Exception -> 0x0048, Throwable -> 0x0046 }
            android.graphics.Bitmap r8 = android.provider.MediaStore.Images.Thumbnails.getThumbnail(r8, r4, r3, r1)     // Catch:{ Exception -> 0x0048, Throwable -> 0x0046 }
            goto L_0x0030
        L_0x0040:
            if (r9 == 0) goto L_0x005f
            r9.close()     // Catch:{ Exception -> 0x0048, Throwable -> 0x0046 }
            goto L_0x005f
        L_0x0046:
            goto L_0x004b
        L_0x0048:
            goto L_0x0052
        L_0x004a:
            r9 = r1
        L_0x004b:
            if (r9 == 0) goto L_0x005f
            r9.close()
            goto L_0x005f
        L_0x0051:
            r9 = r1
        L_0x0052:
            if (r9 == 0) goto L_0x005f
            r9.close()
            goto L_0x005f
        L_0x0058:
            java.lang.String r8 = "FileUtils"
            java.lang.String r9 = "You can only retrieve thumbnails for images and videos."
            android.util.Log.e(r8, r9)
        L_0x005f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: lisa.studio.photoeditor.featuresfoto.puzzle.photopicker.utils.FileUtils.getThumbnail(android.content.Context, android.net.Uri, java.lang.String):android.graphics.Bitmap");
    }

    public static Intent createGetContentIntent() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        return intent;
    }
}