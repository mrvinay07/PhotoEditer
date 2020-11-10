package com.teamvinay.newphotoediter.featuresfoto.picker.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.featuresfoto.picker.utils.AndroidLifecycleUtils;

public class PhotoPagerAdapter extends PagerAdapter {
    private RequestManager mGlide;
    private List<String> paths = new ArrayList();

    public int getItemPosition(Object obj) {
        return -2;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public PhotoPagerAdapter(RequestManager requestManager, List<String> list) {
        this.paths = list;
        this.mGlide = requestManager;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Uri uri;
        final Context context = viewGroup.getContext();
        View inflate = LayoutInflater.from(context).inflate(C1084R.C1089layout.__picker_picker_item_pager, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(C1084R.C1087id.iv_pager);
        String str = this.paths.get(i);
        if (str.startsWith("http")) {
            uri = Uri.parse(str);
        } else {
            uri = Uri.fromFile(new File(str));
        }
        if (AndroidLifecycleUtils.canLoadImage(context)) {
            RequestOptions requestOptions = new RequestOptions();
            ((RequestOptions) ((RequestOptions) requestOptions.dontAnimate()).dontTransform()).override(800, 800);
            this.mGlide.setDefaultRequestOptions(requestOptions).load(uri).thumbnail(0.1f).into(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if ((context instanceof Activity) && !((Activity) context).isFinishing()) {
                    ((Activity) context).onBackPressed();
                }
            }
        });
        viewGroup.addView(inflate);
        return inflate;
    }

    public int getCount() {
        return this.paths.size();
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View view = (View) obj;
        viewGroup.removeView(view);
        this.mGlide.clear(view);
    }
}