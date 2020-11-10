package com.teamvinay.newphotoediter.featuresfoto.puzzle.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.PuzzleLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.SquarePuzzleView;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.slant.NumberSlantLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.NumberStraightLayout;

public class PuzzleAdapter extends RecyclerView.Adapter<PuzzleAdapter.PuzzleViewHolder> {
    private List<Bitmap> bitmapData = new ArrayList();
    private List<PuzzleLayout> layoutData = new ArrayList();
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;
    /* access modifiers changed from: private */
    public int selectedIndex = 0;

    public interface OnItemClickListener {
        void onItemClick(PuzzleLayout puzzleLayout, int i);
    }

    public PuzzleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PuzzleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.item_puzzle, viewGroup, false));
    }

    public void setSelectedIndex(int i) {
        this.selectedIndex = i;
    }

    public void onBindViewHolder(PuzzleViewHolder puzzleViewHolder, final int i) {
        final PuzzleLayout puzzleLayout = this.layoutData.get(i);
        puzzleViewHolder.puzzleView.setNeedDrawLine(true);
        puzzleViewHolder.puzzleView.setNeedDrawOuterLine(true);
        puzzleViewHolder.puzzleView.setTouchEnable(false);
        puzzleViewHolder.puzzleView.setLineSize(6);
        puzzleViewHolder.puzzleView.setPuzzleLayout(puzzleLayout);
        if (this.selectedIndex == i) {
            puzzleViewHolder.puzzleView.setBackgroundColor(Color.parseColor("#4c84ff"));
        } else {
            puzzleViewHolder.puzzleView.setBackgroundColor(0);
        }
        puzzleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PuzzleAdapter.this.onItemClickListener != null) {
                    int i = 0;
                    if (puzzleLayout instanceof NumberSlantLayout) {
                        i = ((NumberSlantLayout) puzzleLayout).getTheme();
                    } else if (puzzleLayout instanceof NumberStraightLayout) {
                        i = ((NumberStraightLayout) puzzleLayout).getTheme();
                    }
                    PuzzleAdapter.this.onItemClickListener.onItemClick(puzzleLayout, i);
                }
                int unused = PuzzleAdapter.this.selectedIndex = i;
                PuzzleAdapter.this.notifyDataSetChanged();
            }
        });
        if (this.bitmapData != null) {
            int size = this.bitmapData.size();
            if (puzzleLayout.getAreaCount() > size) {
                for (int i2 = 0; i2 < puzzleLayout.getAreaCount(); i2++) {
                    puzzleViewHolder.puzzleView.addPiece(this.bitmapData.get(i2 % size));
                }
                return;
            }
            puzzleViewHolder.puzzleView.addPieces(this.bitmapData);
        }
    }

    public int getItemCount() {
        if (this.layoutData == null) {
            return 0;
        }
        return this.layoutData.size();
    }

    public void refreshData(List<PuzzleLayout> list, List<Bitmap> list2) {
        this.layoutData = list;
        this.bitmapData = list2;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public static class PuzzleViewHolder extends RecyclerView.ViewHolder {
        SquarePuzzleView puzzleView;

        public PuzzleViewHolder(View view) {
            super(view);
            this.puzzleView = (SquarePuzzleView) view.findViewById(C1084R.C1087id.puzzle);
        }
    }
}