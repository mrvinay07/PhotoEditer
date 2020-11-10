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
import com.teamvinay.newphotoediter.util.FontUtils;

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.ViewHolder> {
    private Context context;
    private List<String> lstFonts;
    /* access modifiers changed from: private */
    public ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public int selectedItem = 0;

    public interface ItemClickListener {
        void onItemClick(View view, int i);
    }

    public int getItemViewType(int i) {
        return i;
    }

    public FontAdapter(Context context2, List<String> list) {
        this.mInflater = LayoutInflater.from(context2);
        this.context = context2;
        this.lstFonts = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(C1084R.C1089layout.font_adapter, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Resources resources;
        int i2;
        FontUtils.setFontByName(this.context, viewHolder.font, this.lstFonts.get(i));
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
        return this.lstFonts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView font;
        ConstraintLayout wrapperFontItems;

        ViewHolder(View view) {
            super(view);
            this.font = (TextView) view.findViewById(C1084R.C1087id.font_item);
            this.wrapperFontItems = (ConstraintLayout) view.findViewById(C1084R.C1087id.wrapper_font_item);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            int unused = FontAdapter.this.selectedItem = getAdapterPosition();
            if (FontAdapter.this.mClickListener != null) {
                FontAdapter.this.mClickListener.onItemClick(view, FontAdapter.this.selectedItem);
            }
            FontAdapter.this.notifyDataSetChanged();
        }
    }

    public void setSelectedItem(int i) {
        this.selectedItem = i;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}