package com.teamvinay.newphotoediter.featuresfoto.picker.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.picker.entity.Photo;
import com.teamvinay.newphotoediter.featuresfoto.picker.entity.PhotoDirectory;
import com.teamvinay.newphotoediter.featuresfoto.picker.event.OnItemCheckListener;
import com.teamvinay.newphotoediter.featuresfoto.picker.utils.AndroidLifecycleUtils;

public class PhotoGridAdapter extends SelectableAdapter<PhotoGridAdapter.PhotoViewHolder> {
    private static final int COL_NUMBER_DEFAULT = 3;
    public static final int ITEM_TYPE_CAMERA = 100;
    public static final int ITEM_TYPE_PHOTO = 101;
    private int columnNumber;
    private RequestManager glide;
    private boolean hasCamera;
    private int imageSize;
    /* access modifiers changed from: private */
    public View.OnClickListener onCameraClickListener;
    /* access modifiers changed from: private */
    public OnItemCheckListener onItemCheckListener;
    private boolean previewEnable;

    public PhotoGridAdapter(Context context, RequestManager requestManager, List<PhotoDirectory> list) {
        this.onItemCheckListener = null;
        this.onCameraClickListener = null;
        this.hasCamera = true;
        this.previewEnable = true;
        this.columnNumber = 3;
        this.photoDirectories = list;
        this.glide = requestManager;
        setColumnNumber(context, this.columnNumber);
    }

    public PhotoGridAdapter(Context context, RequestManager requestManager, List<PhotoDirectory> list, ArrayList<String> arrayList, int i) {
        this(context, requestManager, list);
        setColumnNumber(context, i);
        this.selectedPhotos = new ArrayList();
        if (arrayList != null) {
            this.selectedPhotos.addAll(arrayList);
        }
    }

    private void setColumnNumber(Context context, int i) {
        this.columnNumber = i;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.imageSize = displayMetrics.widthPixels / i;
    }

    public int getItemViewType(int i) {
        return (!showCamera() || i != 0) ? 101 : 100;
    }

    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        PhotoViewHolder photoViewHolder = new PhotoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.__picker_item_photo, viewGroup, false));
        if (i == 100) {
            photoViewHolder.vSelected.setVisibility(8);
            photoViewHolder.ivPhoto.setScaleType(ImageView.ScaleType.CENTER);
            photoViewHolder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PhotoGridAdapter.this.onCameraClickListener != null) {
                        PhotoGridAdapter.this.onCameraClickListener.onClick(view);
                    }
                }
            });
        }
        return photoViewHolder;
    }

    public void onBindViewHolder(final PhotoViewHolder photoViewHolder, int i) {
        final Photo photo;
        if (getItemViewType(i) == 101) {
            List<Photo> currentPhotos = getCurrentPhotos();
            if (showCamera()) {
                photo = currentPhotos.get(i - 1);
            } else {
                photo = currentPhotos.get(i);
            }
            if (AndroidLifecycleUtils.canLoadImage(photoViewHolder.ivPhoto.getContext())) {
                RequestOptions requestOptions = new RequestOptions();
                ((RequestOptions) ((RequestOptions) ((RequestOptions) requestOptions.centerCrop()).dontAnimate()).override(this.imageSize, this.imageSize)).placeholder((int) C1084R.C1086drawable.grey_background);
                this.glide.setDefaultRequestOptions(requestOptions).load(new File(photo.getPath())).thumbnail(0.5f).into(photoViewHolder.ivPhoto);
            }
            boolean isSelected = isSelected(photo);
            photoViewHolder.vSelected.setSelected(isSelected);
            photoViewHolder.ivPhoto.setSelected(isSelected);
            photoViewHolder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PhotoGridAdapter.this.onItemCheckListener.onItemCheck(photoViewHolder.getAdapterPosition(), photo, PhotoGridAdapter.this.getSelectedPhotos().size() + (PhotoGridAdapter.this.isSelected(photo) ? -1 : 1));
                }
            });
            return;
        }
        photoViewHolder.ivPhoto.setImageResource(C1084R.C1086drawable.black_border);
    }

    public int getItemCount() {
        int size = this.photoDirectories.size() == 0 ? 0 : getCurrentPhotos().size();
        return showCamera() ? size + 1 : size;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public ImageView ivPhoto;
        /* access modifiers changed from: private */
        public View vSelected;

        public PhotoViewHolder(View view) {
            super(view);
            this.ivPhoto = (ImageView) view.findViewById(C1084R.C1087id.iv_photo);
            this.vSelected = view.findViewById(C1084R.C1087id.v_selected);
            this.vSelected.setVisibility(8);
        }
    }

    public void setOnItemCheckListener(OnItemCheckListener onItemCheckListener2) {
        this.onItemCheckListener = onItemCheckListener2;
    }

    public void setOnCameraClickListener(View.OnClickListener onClickListener) {
        this.onCameraClickListener = onClickListener;
    }

    public ArrayList<String> getSelectedPhotoPaths() {
        ArrayList<String> arrayList = new ArrayList<>(getSelectedItemCount());
        for (String add : this.selectedPhotos) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public void setShowCamera(boolean z) {
        this.hasCamera = z;
    }

    public void setPreviewEnable(boolean z) {
        this.previewEnable = z;
    }

    public boolean showCamera() {
        return this.hasCamera && this.currentDirectoryIndex == 0;
    }

    public void onViewRecycled(PhotoViewHolder photoViewHolder) {
        this.glide.clear((View) photoViewHolder.ivPhoto);
        super.onViewRecycled(photoViewHolder);
    }
}