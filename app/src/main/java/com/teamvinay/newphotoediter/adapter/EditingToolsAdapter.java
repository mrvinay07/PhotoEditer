package com.teamvinay.newphotoediter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;

public class EditingToolsAdapter extends RecyclerView.Adapter<EditingToolsAdapter.ViewHolder> {
    /* access modifiers changed from: private */
    public OnItemSelected mOnItemSelected;
    /* access modifiers changed from: private */
    public List<ToolModel> mToolList = new ArrayList();

    public interface OnItemSelected {
        void onToolSelected(ToolType toolType);
    }

    public EditingToolsAdapter(OnItemSelected onItemSelected) {
        this.mOnItemSelected = onItemSelected;
        this.mToolList.add(new ToolModel("Beauty", C1084R.C1086drawable.beauty, ToolType.BEAUTY));
        this.mToolList.add(new ToolModel("Filter", C1084R.C1086drawable.ic_filter_two, ToolType.FILTER));
        this.mToolList.add(new ToolModel("Sticker", C1084R.C1086drawable.ic_sticker_two, ToolType.STICKER));
        this.mToolList.add(new ToolModel("Text", C1084R.C1086drawable.ic_text_two, ToolType.TEXT));
        this.mToolList.add(new ToolModel("Crop", C1084R.C1086drawable.ic_crop_two, ToolType.CROP));
        this.mToolList.add(new ToolModel("Adjust", C1084R.C1086drawable.ic_adjust_two, ToolType.ADJUST));
        this.mToolList.add(new ToolModel("Overlay", C1084R.C1086drawable.ic_overlay_two, ToolType.OVERLAY));
        this.mToolList.add(new ToolModel("Square", C1084R.C1086drawable.ic_insta_two, ToolType.INSTA));
        this.mToolList.add(new ToolModel("Splash", C1084R.C1086drawable.ic_splash_two, ToolType.SPLASH));
        this.mToolList.add(new ToolModel("Blur", C1084R.C1086drawable.ic_blur_two, ToolType.BLUR));
        this.mToolList.add(new ToolModel("Brush", C1084R.C1086drawable.ic_paint_two, ToolType.BRUSH));
        this.mToolList.add(new ToolModel("Mosaic", C1084R.C1086drawable.mosaic, ToolType.MOSAIC));
    }

    public EditingToolsAdapter(OnItemSelected onItemSelected, boolean z) {
        this.mOnItemSelected = onItemSelected;
        this.mToolList.add(new ToolModel("Layout", C1084R.C1086drawable.ic_collage, ToolType.LAYOUT));
        this.mToolList.add(new ToolModel("Border", C1084R.C1086drawable.ic_border, ToolType.BORDER));
        this.mToolList.add(new ToolModel("Ratio", C1084R.C1086drawable.ic_ratio, ToolType.RATIO));
        this.mToolList.add(new ToolModel("Filter", C1084R.C1086drawable.ic_filter_two, ToolType.FILTER));
        this.mToolList.add(new ToolModel("Sticker", C1084R.C1086drawable.ic_sticker_two, ToolType.STICKER));
        this.mToolList.add(new ToolModel("Text", C1084R.C1086drawable.ic_text_two, ToolType.TEXT));
        this.mToolList.add(new ToolModel("Bg", C1084R.C1086drawable.background_icon, ToolType.BACKGROUND));
    }

    class ToolModel {
        /* access modifiers changed from: private */
        public int mToolIcon;
        /* access modifiers changed from: private */
        public String mToolName;
        /* access modifiers changed from: private */
        public ToolType mToolType;

        ToolModel(String str, int i, ToolType toolType) {
            this.mToolName = str;
            this.mToolIcon = i;
            this.mToolType = toolType;
        }
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.row_editing_tools, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ToolModel toolModel = this.mToolList.get(i);
        viewHolder.txtTool.setText(toolModel.mToolName);
        viewHolder.imgToolIcon.setImageResource(toolModel.mToolIcon);
    }

    public int getItemCount() {
        return this.mToolList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgToolIcon;
        TextView txtTool;
        ConstraintLayout wrapTool;

        ViewHolder(View view) {
            super(view);
            this.imgToolIcon = (ImageView) view.findViewById(C1084R.C1087id.imgToolIcon);
            this.txtTool = (TextView) view.findViewById(C1084R.C1087id.txtTool);
            this.wrapTool = (ConstraintLayout) view.findViewById(C1084R.C1087id.wrapTool);
            this.wrapTool.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    EditingToolsAdapter.this.mOnItemSelected.onToolSelected(((ToolModel) EditingToolsAdapter.this.mToolList.get(ViewHolder.this.getLayoutPosition())).mToolType);
                }
            });
        }
    }
}