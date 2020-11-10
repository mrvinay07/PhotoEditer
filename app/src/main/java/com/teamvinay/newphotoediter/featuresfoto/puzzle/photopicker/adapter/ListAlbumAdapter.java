package com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import java.util.ArrayList;
import lisa.studio.photoeditor.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.model.ImageModel;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.photopicker.myinterface.OnListAlbum;

public class ListAlbumAdapter extends ArrayAdapter<ImageModel> {
    Context context;
    ArrayList<ImageModel> data = new ArrayList<>();
    int layoutResourceId;
    OnListAlbum onListAlbum;
    int pHeightItem = 0;

    static class RecordHolder {
        ImageView click;
        ImageView imageItem;
        RelativeLayout layoutRoot;

        RecordHolder() {
        }
    }

    public ListAlbumAdapter(Context context2, int i, ArrayList<ImageModel> arrayList) {
        super(context2, i, arrayList);
        this.layoutResourceId = i;
        this.context = context2;
        this.data = arrayList;
        this.pHeightItem = getDisplayInfo((Activity) context2).widthPixels / 3;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        RecordHolder recordHolder;
        if (view == null) {
            view = ((Activity) this.context).getLayoutInflater().inflate(this.layoutResourceId, viewGroup, false);
            recordHolder = new RecordHolder();
            recordHolder.imageItem = (ImageView) view.findViewById(C1084R.C1087id.imageItem);
            recordHolder.click = (ImageView) view.findViewById(C1084R.C1087id.click);
            recordHolder.layoutRoot = (RelativeLayout) view.findViewById(C1084R.C1087id.layoutRoot);
            recordHolder.layoutRoot.getLayoutParams().height = this.pHeightItem;
            recordHolder.imageItem.getLayoutParams().width = this.pHeightItem;
            recordHolder.imageItem.getLayoutParams().height = this.pHeightItem;
            recordHolder.click.getLayoutParams().width = this.pHeightItem;
            recordHolder.click.getLayoutParams().height = this.pHeightItem;
            view.setTag(recordHolder);
        } else {
            recordHolder = (RecordHolder) view.getTag();
        }
        final ImageModel imageModel = this.data.get(i);
        ((RequestBuilder) Glide.with(this.context).load(imageModel.getPathFile()).placeholder((int) C1084R.C1086drawable.piclist_icon_default)).into(recordHolder.imageItem);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ListAlbumAdapter.this.onListAlbum != null) {
                    ListAlbumAdapter.this.onListAlbum.OnItemListAlbumClick(imageModel);
                }
            }
        });
        return view;
    }

    public OnListAlbum getOnListAlbum() {
        return this.onListAlbum;
    }

    public void setOnListAlbum(OnListAlbum onListAlbum2) {
        this.onListAlbum = onListAlbum2;
    }

    public static DisplayMetrics getDisplayInfo(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}