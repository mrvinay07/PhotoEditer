package com.teamvinay.newphotoediter.featuresfoto.mosaic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.RoundedImageView;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.util.SystemUtil;

public class MosaicAdapter extends RecyclerView.Adapter<MosaicAdapter.ViewHolder> {
    private int borderWidth;
    private Context context;
    /* access modifiers changed from: private */
    public MosaicChangeListener mosaicChangeListener;
    /* access modifiers changed from: private */
    public List<MosaicItem> mosaicItems = new ArrayList();
    /* access modifiers changed from: private */
    public int selectedSquareIndex;

    enum Mode {
        BLUR,
        MOSAIC,
        SHADER
    }

    interface MosaicChangeListener {
        void onSelected(MosaicItem mosaicItem);
    }

    public MosaicAdapter(Context context2, MosaicChangeListener mosaicChangeListener2) {
        this.context = context2;
        this.mosaicChangeListener = mosaicChangeListener2;
        this.borderWidth = SystemUtil.dpToPx(context2, 3);
        this.mosaicItems.add(new MosaicItem(C1084R.C1086drawable.blue_mosoic, 0, Mode.BLUR));
        this.mosaicItems.add(new MosaicItem(C1084R.C1086drawable.mosaic_2, 0, Mode.MOSAIC));
        this.mosaicItems.add(new MosaicItem(C1084R.C1086drawable.mosaic_3, C1084R.C1086drawable.mosaic_33, Mode.SHADER));
        this.mosaicItems.add(new MosaicItem(C1084R.C1086drawable.mosaic_4, C1084R.C1086drawable.mosaic_44, Mode.SHADER));
        this.mosaicItems.add(new MosaicItem(C1084R.C1086drawable.mosaic_5, C1084R.C1086drawable.mosaic_55, Mode.SHADER));
        this.mosaicItems.add(new MosaicItem(C1084R.C1086drawable.mosaic_6, C1084R.C1086drawable.mosaic_66, Mode.SHADER));
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.splash_view, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(this.context).load(Integer.valueOf(this.mosaicItems.get(i).frameId)).into((ImageView) viewHolder.mosaic);
        if (this.selectedSquareIndex == i) {
            viewHolder.mosaic.setBorderColor(this.context.getResources().getColor(C1084R.C1085color.colorAccent));
            viewHolder.mosaic.setBorderWidth(this.borderWidth);
            return;
        }
        viewHolder.mosaic.setBorderColor(0);
        viewHolder.mosaic.setBorderWidth(this.borderWidth);
    }

    public int getItemCount() {
        return this.mosaicItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /* access modifiers changed from: private */
        public RoundedImageView mosaic;

        public ViewHolder(View view) {
            super(view);
            this.mosaic = (RoundedImageView) view.findViewById(C1084R.C1087id.splash);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            int unused = MosaicAdapter.this.selectedSquareIndex = getAdapterPosition();
            MosaicAdapter.this.mosaicChangeListener.onSelected((MosaicItem) MosaicAdapter.this.mosaicItems.get(MosaicAdapter.this.selectedSquareIndex));
            MosaicAdapter.this.notifyDataSetChanged();
        }
    }

    static class MosaicItem {
        int frameId;
        Mode mode;
        int shaderId;

        MosaicItem(int i, int i2, Mode mode2) {
            this.frameId = i;
            this.mode = mode2;
            this.shaderId = i2;
        }
    }
}