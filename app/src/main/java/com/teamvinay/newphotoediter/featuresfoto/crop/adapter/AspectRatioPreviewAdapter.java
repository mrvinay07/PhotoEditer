package com.teamvinay.newphotoediter.featuresfoto.crop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.steelkiwi.cropiwa.AspectRatio;
import java.util.Arrays;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;

public class AspectRatioPreviewAdapter extends RecyclerView.Adapter<AspectRatioPreviewAdapter.ViewHolder> {
    /* access modifiers changed from: private */
    public int lastSelectedView;
    /* access modifiers changed from: private */
    public OnNewSelectedListener listener;
    /* access modifiers changed from: private */
    public List<AspectRatioCustom> ratios;
    /* access modifiers changed from: private */
    public AspectRatioCustom selectedRatio;

    public interface OnNewSelectedListener {
        void onNewAspectRatioSelected(AspectRatio aspectRatio);
    }

    public AspectRatioPreviewAdapter() {
        this.ratios = Arrays.asList(new AspectRatioCustom[]{new AspectRatioCustom(10, 10, C1084R.C1086drawable.crop_free, C1084R.C1086drawable.crop_free_click), new AspectRatioCustom(1, 1, C1084R.C1086drawable.ratio_1_1, C1084R.C1086drawable.ratio_1_1_click), new AspectRatioCustom(4, 3, C1084R.C1086drawable.ratio_4_3, C1084R.C1086drawable.ratio_4_3_click), new AspectRatioCustom(3, 4, C1084R.C1086drawable.ratio_3_4, C1084R.C1086drawable.ratio_3_4_click), new AspectRatioCustom(5, 4, C1084R.C1086drawable.ratio_5_4, C1084R.C1086drawable.ratio_5_4_click), new AspectRatioCustom(4, 5, C1084R.C1086drawable.ratio_4_5, C1084R.C1086drawable.ratio_4_5_click), new AspectRatioCustom(3, 2, C1084R.C1086drawable.ratio_3_2, C1084R.C1086drawable.ratio_3_2_click), new AspectRatioCustom(2, 3, C1084R.C1086drawable.ratio_2_3, C1084R.C1086drawable.ratio_2_3_click), new AspectRatioCustom(9, 16, C1084R.C1086drawable.ratio_9_16, C1084R.C1086drawable.ratio_9_16_click), new AspectRatioCustom(16, 9, C1084R.C1086drawable.ratio_16_9, C1084R.C1086drawable.ratio_16_9_click)});
        this.selectedRatio = this.ratios.get(0);
    }

    public AspectRatioPreviewAdapter(boolean z) {
        this.ratios = Arrays.asList(new AspectRatioCustom[]{new AspectRatioCustom(1, 1, C1084R.C1086drawable.ratio_1_1, C1084R.C1086drawable.ratio_1_1_click), new AspectRatioCustom(4, 3, C1084R.C1086drawable.ratio_4_3, C1084R.C1086drawable.ratio_4_3_click), new AspectRatioCustom(3, 4, C1084R.C1086drawable.ratio_3_4, C1084R.C1086drawable.ratio_3_4_click), new AspectRatioCustom(5, 4, C1084R.C1086drawable.ratio_5_4, C1084R.C1086drawable.ratio_5_4_click), new AspectRatioCustom(4, 5, C1084R.C1086drawable.ratio_4_5, C1084R.C1086drawable.ratio_4_5_click), new AspectRatioCustom(3, 2, C1084R.C1086drawable.ratio_3_2, C1084R.C1086drawable.ratio_3_2_click), new AspectRatioCustom(2, 3, C1084R.C1086drawable.ratio_2_3, C1084R.C1086drawable.ratio_2_3_click), new AspectRatioCustom(9, 16, C1084R.C1086drawable.ratio_9_16, C1084R.C1086drawable.ratio_9_16_click), new AspectRatioCustom(16, 9, C1084R.C1086drawable.ratio_16_9, C1084R.C1086drawable.ratio_16_9_click)});
        this.selectedRatio = this.ratios.get(0);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.item_aspect_ratio, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AspectRatioCustom aspectRatioCustom = this.ratios.get(i);
        if (i == this.lastSelectedView) {
            viewHolder.ratioView.setImageResource(aspectRatioCustom.getSelectedIem());
        } else {
            viewHolder.ratioView.setImageResource(aspectRatioCustom.getUnselectItem());
        }
    }

    public int getLastSelectedView() {
        return this.lastSelectedView;
    }

    public void setLastSelectedView(int i) {
        this.lastSelectedView = i;
    }

    public int getItemCount() {
        return this.ratios.size();
    }

    public void setListener(OnNewSelectedListener onNewSelectedListener) {
        this.listener = onNewSelectedListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /* access modifiers changed from: private */
        public ImageView ratioView;

        public ViewHolder(View view) {
            super(view);
            this.ratioView = (ImageView) view.findViewById(C1084R.C1087id.aspect_ratio_preview);
            this.ratioView.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (AspectRatioPreviewAdapter.this.lastSelectedView != getAdapterPosition()) {
                AspectRatioCustom unused = AspectRatioPreviewAdapter.this.selectedRatio = (AspectRatioCustom) AspectRatioPreviewAdapter.this.ratios.get(getAdapterPosition());
                int unused2 = AspectRatioPreviewAdapter.this.lastSelectedView = getAdapterPosition();
                if (AspectRatioPreviewAdapter.this.listener != null) {
                    AspectRatioPreviewAdapter.this.listener.onNewAspectRatioSelected(AspectRatioPreviewAdapter.this.selectedRatio);
                }
                AspectRatioPreviewAdapter.this.notifyDataSetChanged();
            }
        }
    }
}