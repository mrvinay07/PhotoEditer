package com.teamvinay.newphotoediter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;

public class PieceToolsAdapter extends RecyclerView.Adapter<PieceToolsAdapter.ViewHolder> {
    /* access modifiers changed from: private */
    public List<ToolModel> mToolList = new ArrayList();
    /* access modifiers changed from: private */
    public OnPieceFuncItemSelected onPieceFuncItemSelected;

    public interface OnPieceFuncItemSelected {
        void onPieceFuncSelected(ToolType toolType);
    }

    public PieceToolsAdapter(OnPieceFuncItemSelected onPieceFuncItemSelected2) {
        this.onPieceFuncItemSelected = onPieceFuncItemSelected2;
        this.mToolList.add(new ToolModel("Change", C1084R.C1086drawable.background_icon_white, ToolType.REPLACE));
        this.mToolList.add(new ToolModel("Crop", C1084R.C1086drawable.ic_crop_two_white, ToolType.CROP));
        this.mToolList.add(new ToolModel("Filter", C1084R.C1086drawable.ic_filter_two_white, ToolType.FILTER));
        this.mToolList.add(new ToolModel("Rotate", C1084R.C1086drawable.rotate_white, ToolType.ROTATE));
        this.mToolList.add(new ToolModel("H Flip", C1084R.C1086drawable.h_flip_white, ToolType.H_FLIP));
        this.mToolList.add(new ToolModel("V Flip", C1084R.C1086drawable.v_flip_white, ToolType.V_FLIP));
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
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.row_piece_tools, viewGroup, false));
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

        ViewHolder(View view) {
            super(view);
            this.imgToolIcon = (ImageView) view.findViewById(C1084R.C1087id.imgToolIcon);
            this.txtTool = (TextView) view.findViewById(C1084R.C1087id.txtTool);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PieceToolsAdapter.this.onPieceFuncItemSelected.onPieceFuncSelected(((ToolModel) PieceToolsAdapter.this.mToolList.get(ViewHolder.this.getLayoutPosition())).mToolType);
                }
            });
        }
    }
}