package com.teamvinay.newphotoediter.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.PuzzleView;

public class FileUtils {
    private static final String TAG = "FileUtils";

    public static String getFolderName(String str) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), str);
        if (file.exists() || file.mkdirs()) {
            return file.getAbsolutePath();
        }
        return "";
    }

    private static boolean isSDAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0079 A[SYNTHETIC, Splitter:B:19:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0081 A[SYNTHETIC, Splitter:B:26:0x0081] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File saveBitmapAsFile(android.graphics.Bitmap r5) {
        /*
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r0 = r0.toString()
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r3 = "/PhotoEditor"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            boolean r2 = r1.exists()
            if (r2 != 0) goto L_0x0027
            r1.mkdirs()
        L_0x0027:
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r2 = "yyyyMMdd_HHmmss"
            java.util.Locale r3 = java.util.Locale.ENGLISH
            r1.<init>(r2, r3)
            java.util.Date r2 = new java.util.Date
            r2.<init>()
            java.lang.String r1 = r1.format(r2)
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            r4.<init>()     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            r4.append(r0)     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            java.lang.String r0 = "/PhotoEditor/"
            r4.append(r0)     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            r4.append(r1)     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            java.lang.String r0 = ".jpg"
            r4.append(r0)     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            r3.createNewFile()     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            r0.<init>(r3)     // Catch:{ Exception -> 0x0072, all -> 0x0070 }
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x006e }
            r4 = 100
            r5.compress(r1, r4, r0)     // Catch:{ Exception -> 0x006e }
            r0.flush()     // Catch:{ Exception -> 0x006e }
            r0.close()     // Catch:{ IOException -> 0x006d }
        L_0x006d:
            return r3
        L_0x006e:
            r5 = move-exception
            goto L_0x0074
        L_0x0070:
            r5 = move-exception
            goto L_0x007f
        L_0x0072:
            r5 = move-exception
            r0 = r2
        L_0x0074:
            r5.printStackTrace()     // Catch:{ all -> 0x007d }
            if (r0 == 0) goto L_0x007c
            r0.close()     // Catch:{ IOException -> 0x007c }
        L_0x007c:
            return r2
        L_0x007d:
            r5 = move-exception
            r2 = r0
        L_0x007f:
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch:{ IOException -> 0x0084 }
        L_0x0084:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: lisa.studio.photoeditor.util.FileUtils.saveBitmapAsFile(android.graphics.Bitmap):java.io.File");
    }

    public static File getNewFile(Context context, String str) {
        String str2;
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        if (isSDAvailable()) {
            str2 = getFolderName(str) + File.separator + format + ".jpg";
        } else {
            str2 = context.getFilesDir().getPath() + File.separator + format + ".jpg";
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return new File(str2);
    }

    public static Bitmap createBitmap(PuzzleView puzzleView, int i) {
        puzzleView.clearHandling();
        puzzleView.invalidate();
        Bitmap createBitmap = Bitmap.createBitmap(i, (int) (((float) i) / (((float) puzzleView.getWidth()) / ((float) puzzleView.getHeight()))), Bitmap.Config.ARGB_8888);
        puzzleView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Bitmap createBitmap(PuzzleView puzzleView) {
        puzzleView.clearHandling();
        puzzleView.invalidate();
        Bitmap createBitmap = Bitmap.createBitmap(puzzleView.getWidth(), puzzleView.getHeight(), Bitmap.Config.ARGB_8888);
        puzzleView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0062, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x008f, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0098, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0099, code lost:
        r6.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0062 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0078 A[Catch:{ all -> 0x008b }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0082 A[SYNTHETIC, Splitter:B:45:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0094 A[SYNTHETIC, Splitter:B:54:0x0094] */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void savePuzzle(lisa.studio.photoeditor.featuresfoto.puzzle.PuzzleView r5, java.io.File r6, int r7, lisa.studio.photoeditor.featuresfoto.puzzle.Callback r8) {
        /*
            r0 = 0
            android.graphics.Bitmap r1 = createBitmap(r5)     // Catch:{ FileNotFoundException -> 0x0071, all -> 0x006d }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0069, all -> 0x0066 }
            r2.<init>(r6)     // Catch:{ FileNotFoundException -> 0x0069, all -> 0x0066 }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
            r1.compress(r3, r7, r2)     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
            boolean r7 = r6.exists()     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
            if (r7 != 0) goto L_0x002a
            java.lang.String r5 = "FileUtils"
            java.lang.String r6 = "notifySystemGallery: the file do not exist."
            android.util.Log.e(r5, r6)     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
            if (r1 == 0) goto L_0x0021
            r1.recycle()
        L_0x0021:
            r2.close()     // Catch:{ IOException -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0029:
            return
        L_0x002a:
            android.content.Context r7 = r5.getContext()     // Catch:{ FileNotFoundException -> 0x003e, all -> 0x0062 }
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch:{ FileNotFoundException -> 0x003e, all -> 0x0062 }
            java.lang.String r3 = r6.getAbsolutePath()     // Catch:{ FileNotFoundException -> 0x003e, all -> 0x0062 }
            java.lang.String r4 = r6.getName()     // Catch:{ FileNotFoundException -> 0x003e, all -> 0x0062 }
            android.provider.MediaStore.Images.Media.insertImage(r7, r3, r4, r0)     // Catch:{ FileNotFoundException -> 0x003e, all -> 0x0062 }
            goto L_0x0042
        L_0x003e:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
        L_0x0042:
            android.content.Context r5 = r5.getContext()     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
            android.content.Intent r7 = new android.content.Intent     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
            java.lang.String r0 = "android.intent.action.MEDIA_SCANNER_SCAN_FILE"
            android.net.Uri r6 = android.net.Uri.fromFile(r6)     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
            r7.<init>(r0, r6)     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
            r5.sendBroadcast(r7)     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
            if (r8 == 0) goto L_0x0059
            r8.onSuccess()     // Catch:{ FileNotFoundException -> 0x0064, all -> 0x0062 }
        L_0x0059:
            if (r1 == 0) goto L_0x005e
            r1.recycle()
        L_0x005e:
            r2.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x008a
        L_0x0062:
            r5 = move-exception
            goto L_0x008d
        L_0x0064:
            r5 = move-exception
            goto L_0x006b
        L_0x0066:
            r5 = move-exception
            r2 = r0
            goto L_0x008d
        L_0x0069:
            r5 = move-exception
            r2 = r0
        L_0x006b:
            r0 = r1
            goto L_0x0073
        L_0x006d:
            r5 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x008d
        L_0x0071:
            r5 = move-exception
            r2 = r0
        L_0x0073:
            r5.printStackTrace()     // Catch:{ all -> 0x008b }
            if (r8 == 0) goto L_0x007b
            r8.onFailed()     // Catch:{ all -> 0x008b }
        L_0x007b:
            if (r0 == 0) goto L_0x0080
            r0.recycle()
        L_0x0080:
            if (r2 == 0) goto L_0x008a
            r2.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x008a
        L_0x0086:
            r5 = move-exception
            r5.printStackTrace()
        L_0x008a:
            return
        L_0x008b:
            r5 = move-exception
            r1 = r0
        L_0x008d:
            if (r1 == 0) goto L_0x0092
            r1.recycle()
        L_0x0092:
            if (r2 == 0) goto L_0x009c
            r2.close()     // Catch:{ IOException -> 0x0098 }
            goto L_0x009c
        L_0x0098:
            r6 = move-exception
            r6.printStackTrace()
        L_0x009c:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: lisa.studio.photoeditor.util.FileUtils.savePuzzle(lisa.studio.photoeditor.featuresfoto.puzzle.PuzzleView, java.io.File, int, lisa.studio.photoeditor.featuresfoto.puzzle.Callback):void");
    }

    public static Bitmap getResizedBitmap(Bitmap bitmap, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        float width = ((float) i) / ((float) bitmap.getWidth());
        float height = ((float) i2) / ((float) bitmap.getHeight());
        Matrix matrix = new Matrix();
        matrix.setScale(width, height, 0.0f, 0.0f);
        Canvas canvas = new Canvas(createBitmap);
        canvas.setMatrix(matrix);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(2));
        bitmap.recycle();
        return createBitmap;
    }
}