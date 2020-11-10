package com.teamvinay.newphotoediter.featuresfoto.insta;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.util.ColorUtils;

public class InstaAdapter extends RecyclerView.Adapter<InstaAdapter.ViewHolder> {
    /* access modifiers changed from: private */
    public BackgroundInstaListener backgroundInstaListener;
    private Context context;
    /* access modifiers changed from: private */
    public int selectedSquareIndex;
    /* access modifiers changed from: private */
    public List<SquareView> squareViews = new ArrayList();

    interface BackgroundInstaListener {
        void onBackgroundSelected(SquareView squareView);
    }

    public InstaAdapter(Context context2, BackgroundInstaListener backgroundInstaListener2) {
        this.context = context2;
        this.backgroundInstaListener = backgroundInstaListener2;
        this.squareViews.add(new SquareView(C1084R.C1086drawable.background_blur, "Blur"));
        this.squareViews.add(new SquareView(C1084R.C1085color.white, "White"));
        this.squareViews.add(new SquareView(C1084R.C1085color.black, "Black"));
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
        List<String> lstColorForBrush = ColorUtils.lstColorForBrush();
        for (int i = 0; i < lstColorForBrush.size() - 2; i++) {
            this.squareViews.add(new SquareView(Color.parseColor(lstColorForBrush.get(i)), "", true));
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
        } else {
            viewHolder.squareView.setBackgroundResource(squareView.drawableId);
        }
        if (this.selectedSquareIndex == i) {
            viewHolder.wrapSquareView.setBackground(this.context.getDrawable(C1084R.C1086drawable.border_view));
        } else {
            viewHolder.wrapSquareView.setBackground(this.context.getDrawable(C1084R.C1086drawable.border_transparent_view));
        }
    }

    public int getItemCount() {
        return this.squareViews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /* access modifiers changed from: private */
        public View squareView;
        /* access modifiers changed from: private */
        public ConstraintLayout wrapSquareView;

        public ViewHolder(View view) {
            super(view);
            this.squareView = view.findViewById(C1084R.C1087id.squareView);
            this.wrapSquareView = (ConstraintLayout) view.findViewById(C1084R.C1087id.wrapSquareView);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            int unused = InstaAdapter.this.selectedSquareIndex = getAdapterPosition();
            InstaAdapter.this.backgroundInstaListener.onBackgroundSelected((SquareView) InstaAdapter.this.squareViews.get(InstaAdapter.this.selectedSquareIndex));
            InstaAdapter.this.notifyDataSetChanged();
        }
    }

    class SquareView {
        int drawableId;
        boolean isColor;
        String text;

        SquareView(int i, String str) {
            this.drawableId = i;
            this.text = str;
        }

        SquareView(int i, String str, boolean z) {
            this.drawableId = i;
            this.text = str;
            this.isColor = z;
        }
    }
}
