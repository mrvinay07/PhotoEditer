package com.teamvinay.newphotoediter.featuresfoto.sticker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.sticker.adapter.RecyclerTabLayout;

public class TopTabAdapter extends RecyclerTabLayout.Adapter<TopTabAdapter.ViewHolder> {
    private Context context;
    private PagerAdapter mAdapater = this.mViewPager.getAdapter();

    public TopTabAdapter(ViewPager viewPager, Context context2) {
        super(viewPager);
        this.context = context2;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.top_tab_view, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        switch (i) {
            case 0:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.heard));
                break;
            case 1:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.emoij));
                break;
            case 2:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.text));
                break;
            case 3:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.other));
                break;
            case 4:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.giddy));
                break;
            case 5:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.glasses));
                break;
            case 6:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.tie));
                break;
            case 7:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.cat));
                break;
            case 8:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.cheek));
                break;
            case 9:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.eye));
                break;
            case 10:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.diadem));
                break;
            case 11:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.muscle));
                break;
            case 12:
                viewHolder.imageView.setImageDrawable(this.context.getDrawable(C1084R.C1086drawable.tatoo));
                break;
        }
        viewHolder.imageView.setSelected(i == getCurrentIndicatorPosition());
    }

    public int getItemCount() {
        return this.mAdapater.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(C1084R.C1087id.image);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    TopTabAdapter.this.getViewPager().setCurrentItem(ViewHolder.this.getAdapterPosition());
                }
            });
        }
    }
}