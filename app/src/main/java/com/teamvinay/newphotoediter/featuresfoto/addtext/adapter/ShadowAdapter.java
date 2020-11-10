package com.teamvinay.newphotoediter.featuresfoto.addtext.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.addtext.AddTextProperties;

public class ShadowAdapter extends RecyclerView.Adapter<ShadowAdapter.ViewHolder> {
    private Context context;
    private List<AddTextProperties.TextShadow> lstTextShadows;
    /* access modifiers changed from: private */
    public ShadowItemClickListener mClickListener;
    private LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public int selectedItem = 0;

    public interface ShadowItemClickListener {
        void onShadowItemClick(View view, int i);
    }

    public int getItemViewType(int i) {
        return i;
    }

    public ShadowAdapter(Context context2, List<AddTextProperties.TextShadow> list) {
        this.mInflater = LayoutInflater.from(context2);
        this.context = context2;
        this.lstTextShadows = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(C1084R.C1089layout.shadow_adapter, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Resources resources;
        int i2;
        AddTextProperties.TextShadow textShadow = this.lstTextShadows.get(i);
        viewHolder.textShadow.setShadowLayer((float) textShadow.getRadius(), (float) textShadow.getDx(), (float) textShadow.getDy(), textShadow.getColorShadow());
        ConstraintLayout constraintLayout = viewHolder.wrapperFontItems;
        if (this.selectedItem != i) {
            resources = this.context.getResources();
            i2 = C1084R.C1086drawable.border_black_view;
        } else {
            resources = this.context.getResources();
            i2 = C1084R.C1086drawable.border_view;
        }
        constraintLayout.setBackground(resources.getDrawable(i2));
    }

    public int getItemCount() {
        return this.lstTextShadows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textShadow;
        ConstraintLayout wrapperFontItems;

        ViewHolder(View view) {
            super(view);
            this.textShadow = (TextView) view.findViewById(C1084R.C1087id.font_item);
            this.wrapperFontItems = (ConstraintLayout) view.findViewById(C1084R.C1087id.wrapper_font_item);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (ShadowAdapter.this.mClickListener != null) {
                ShadowAdapter.this.mClickListener.onShadowItemClick(view, getAdapterPosition());
            }
            int unused = ShadowAdapter.this.selectedItem = getAdapterPosition();
            ShadowAdapter.this.notifyDataSetChanged();
        }
    }

    public void setSelectedItem(int i) {
        this.selectedItem = i;
    }

    public void setClickListener(ShadowItemClickListener shadowItemClickListener) {
        this.mClickListener = shadowItemClickListener;
    }
}