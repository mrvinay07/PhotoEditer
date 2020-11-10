package com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import java.io.File;
import java.util.ArrayList;
import lisa.studio.photoeditor.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.model.ImageModel;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.myinterface.OnAlbum;

public class AlbumAdapter extends ArrayAdapter<ImageModel> {
    Context context;
    ArrayList<ImageModel> data = new ArrayList<>();
    int layoutResourceId;
    OnAlbum onItem;
    int pHeightItem = 0;
    int pWHIconNext = 0;

    static class RecordHolder {
        ImageView iconNext;
        ImageView imageItem;
        RelativeLayout layoutRoot;
        TextView txtPath;
        TextView txtTitle;

        RecordHolder() {
        }
    }

    public AlbumAdapter(Context context2, int i, ArrayList<ImageModel> arrayList) {
        super(context2, i, arrayList);
        this.layoutResourceId = i;
        this.context = context2;
        this.data = arrayList;
        this.pHeightItem = getDisplayInfo((Activity) context2).widthPixels / 6;
        this.pWHIconNext = this.pHeightItem / 4;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        RecordHolder recordHolder;
        if (view == null) {
            view = ((Activity) this.context).getLayoutInflater().inflate(this.layoutResourceId, viewGroup, false);
            recordHolder = new RecordHolder();
            recordHolder.txtTitle = (TextView) view.findViewById(C1084R.C1087id.name_album);
            recordHolder.txtPath = (TextView) view.findViewById(C1084R.C1087id.path_album);
            recordHolder.imageItem = (ImageView) view.findViewById(C1084R.C1087id.icon_album);
            recordHolder.iconNext = (ImageView) view.findViewById(C1084R.C1087id.iconNext);
            recordHolder.layoutRoot = (RelativeLayout) view.findViewById(C1084R.C1087id.layoutRoot);
            recordHolder.layoutRoot.getLayoutParams().height = this.pHeightItem;
            recordHolder.imageItem.getLayoutParams().width = this.pHeightItem;
            recordHolder.imageItem.getLayoutParams().height = this.pHeightItem;
            recordHolder.iconNext.getLayoutParams().width = this.pWHIconNext;
            recordHolder.iconNext.getLayoutParams().height = this.pWHIconNext;
            view.setTag(recordHolder);
        } else {
            recordHolder = (RecordHolder) view.getTag();
        }
        ImageModel imageModel = this.data.get(i);
        recordHolder.txtTitle.setText(imageModel.getName());
        recordHolder.txtPath.setText(imageModel.getPathFolder());
        ((RequestBuilder) Glide.with(this.context).load(new File(imageModel.getPathFile())).placeholder((int) C1084R.C1086drawable.piclist_icon_default)).into(recordHolder.imageItem);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AlbumAdapter.this.onItem != null) {
                    AlbumAdapter.this.onItem.OnItemAlbumClick(i);
                }
            }
        });
        return view;
    }

    public OnAlbum getOnItem() {
        return this.onItem;
    }

    public void setOnItem(OnAlbum onAlbum) {
        this.onItem = onAlbum;
    }

    public static DisplayMetrics getDisplayInfo(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}