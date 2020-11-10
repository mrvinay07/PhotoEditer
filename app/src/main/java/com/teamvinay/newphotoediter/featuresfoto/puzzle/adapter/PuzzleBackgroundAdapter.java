package com.teamvinay.newphotoediter.featuresfoto.puzzle.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.util.ColorUtils;

public class PuzzleBackgroundAdapter extends RecyclerView.Adapter<PuzzleBackgroundAdapter.ViewHolder> {
    /* access modifiers changed from: private */
    public BackgroundChangeListener backgroundChangeListener;
    private Context context;
    /* access modifiers changed from: private */
    public int selectedSquareIndex;
    /* access modifiers changed from: private */
    public List<SquareView> squareViews = new ArrayList();

    public interface BackgroundChangeListener {
        void onBackgroundSelected(SquareView squareView);
    }

    public PuzzleBackgroundAdapter(Context context2, BackgroundChangeListener backgroundChangeListener2) {
        this.context = context2;
        this.backgroundChangeListener = backgroundChangeListener2;
        this.squareViews.add(new SquareView(Color.parseColor("#ffffff"), "White", true));
        this.squareViews.add(new SquareView(C1084R.C1085color.black, "Black"));
        List<String> lstColorForBrush = ColorUtils.lstColorForBrush();
        for (int i = 0; i < lstColorForBrush.size() - 2; i++) {
            this.squareViews.add(new SquareView(Color.parseColor(lstColorForBrush.get(i)), "", true));
        }
    }

    public PuzzleBackgroundAdapter(Context context2, BackgroundChangeListener backgroundChangeListener2, boolean z) {
        this.context = context2;
        this.backgroundChangeListener = backgroundChangeListener2;
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_1, "G1"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_2, "G2"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_3, "G3"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_4, "G4"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_5, "G5"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_11, "G11"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_10, "G10"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_6, "G6"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_7, "G7"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_13, "G13"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_14, "G14"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_16, "G16"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_17, "G17"));
        this.squareViews.add(new SquareView(C1084R.C1086drawable.gradient_18, "G18"));
    }

    public PuzzleBackgroundAdapter(Context context2, BackgroundChangeListener backgroundChangeListener2, List<Drawable> list) {
        this.context = context2;
        this.backgroundChangeListener = backgroundChangeListener2;
        for (Drawable squareView : list) {
            this.squareViews.add(new SquareView(1, "", false, true, squareView));
        }
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.square_view_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SquareView squareView = this.squareViews.get(i);
        if (squareView.isColor) {
            viewHolder.squareView.setBackgroundColor(squareView.drawableId);
        } else if (squareView.drawable != null) {
            viewHolder.squareView.setVisibility(8);
            viewHolder.imageView.setVisibility(0);
            viewHolder.imageView.setImageDrawable(squareView.drawable);
        } else {
            viewHolder.squareView.setBackgroundResource(squareView.drawableId);
        }
        if (this.selectedSquareIndex == i) {
            viewHolder.wrapSquareView.setBackground(this.context.getDrawable(C1084R.C1086drawable.border_view));
        } else {
            viewHolder.wrapSquareView.setBackground(this.context.getDrawable(C1084R.C1086drawable.border_transparent_view));
        }
    }

    public void setSelectedSquareIndex(int i) {
        this.selectedSquareIndex = i;
    }

    public int getItemCount() {
        return this.squareViews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /* access modifiers changed from: private */
        public ImageView imageView;
        /* access modifiers changed from: private */
        public View squareView;
        /* access modifiers changed from: private */
        public ConstraintLayout wrapSquareView;

        public ViewHolder(View view) {
            super(view);
            this.squareView = view.findViewById(C1084R.C1087id.squareView);
            this.wrapSquareView = (ConstraintLayout) view.findViewById(C1084R.C1087id.wrapSquareView);
            this.imageView = (ImageView) view.findViewById(C1084R.C1087id.imgView);
            this.imageView.setVisibility(8);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            int unused = PuzzleBackgroundAdapter.this.selectedSquareIndex = getAdapterPosition();
            PuzzleBackgroundAdapter.this.backgroundChangeListener.onBackgroundSelected((SquareView) PuzzleBackgroundAdapter.this.squareViews.get(PuzzleBackgroundAdapter.this.selectedSquareIndex));
            PuzzleBackgroundAdapter.this.notifyDataSetChanged();
        }
    }

    public static class SquareView {
        public Drawable drawable;
        public int drawableId;
        public boolean isBitmap;
        public boolean isColor;
        public String text;

        SquareView(int i, String str) {
            this.drawableId = i;
            this.text = str;
        }

        public SquareView(int i, String str, boolean z) {
            this.drawableId = i;
            this.text = str;
            this.isColor = z;
        }

        public SquareView(int i, String str, boolean z, boolean z2, Drawable drawable2) {
            this.drawableId = i;
            this.text = str;
            this.isColor = z;
            this.isBitmap = z2;
            this.drawable = drawable2;
        }
    }
}