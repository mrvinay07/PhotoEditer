package com.teamvinay.newphotoediter.editor.filterscolor;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.editor.filterscolor.FilterUtils;
import com.teamvinay.newphotoediter.util.SystemUtil;

public class FilterViewAdapter extends RecyclerView.Adapter<FilterViewAdapter.ViewHolder> {
    private List<Bitmap> bitmaps;
    private int borderWidth;
    private Context context;
    /* access modifiers changed from: private */
    public List<FilterUtils.FilterBean> filterEffects;
    /* access modifiers changed from: private */
    public FilterListener mFilterListener;
    /* access modifiers changed from: private */
    public int selectedFilterIndex = 0;

    public FilterViewAdapter(List<Bitmap> list, FilterListener filterListener, Context context2, List<FilterUtils.FilterBean> list2) {
        this.mFilterListener = filterListener;
        this.bitmaps = list;
        this.context = context2;
        this.filterEffects = list2;
        this.borderWidth = SystemUtil.dpToPx(context2, 3);
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.row_filter_view, viewGroup, false));
    }

    public void reset() {
        this.selectedFilterIndex = 0;
        notifyDataSetChanged();
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTxtFilterName.setText(this.filterEffects.get(i).getName());
        viewHolder.mImageFilterView.setImageBitmap(this.bitmaps.get(i));
        viewHolder.mImageFilterView.setBorderColor(this.context.getResources().getColor(C1084R.C1085color.colorAccent));
        if (this.selectedFilterIndex == i) {
            viewHolder.mImageFilterView.setBorderColor(this.context.getResources().getColor(C1084R.C1085color.colorAccent));
            viewHolder.mImageFilterView.setBorderWidth(this.borderWidth);
            return;
        }
        viewHolder.mImageFilterView.setBorderColor(0);
        viewHolder.mImageFilterView.setBorderWidth(this.borderWidth);
    }

    public int getItemCount() {
        return this.bitmaps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView mImageFilterView;
        TextView mTxtFilterName;
        ConstraintLayout wrapFilterItem;

        ViewHolder(View view) {
            super(view);
            this.mImageFilterView = (RoundedImageView) view.findViewById(C1084R.C1087id.imgFilterView);
            this.mTxtFilterName = (TextView) view.findViewById(C1084R.C1087id.txtFilterName);
            this.wrapFilterItem = (ConstraintLayout) view.findViewById(C1084R.C1087id.wrapFilterItem);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int unused = FilterViewAdapter.this.selectedFilterIndex = ViewHolder.this.getLayoutPosition();
                    FilterViewAdapter.this.mFilterListener.onFilterSelected(((FilterUtils.FilterBean) FilterViewAdapter.this.filterEffects.get(FilterViewAdapter.this.selectedFilterIndex)).getConfig());
                    FilterViewAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }
}