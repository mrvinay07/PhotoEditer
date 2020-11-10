package com.teamvinay.newphotoediter.featuresfoto.draw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;
import com.teamvinay.newphotoediter.ui.view.DrawBitmapModel;
import com.teamvinay.newphotoediter.util.SystemUtil;

import de.hdodenhof.circleimageview.CircleImageView;


public class MagicBrushAdapter extends RecyclerView.Adapter<MagicBrushAdapter.ViewHolder> {
    public static List<DrawBitmapModel> drawBitmapModels = new ArrayList();
    private boolean addNoneColor;
    private int borderSize = 0;
    /* access modifiers changed from: private */
    public BrushMagicListener brushMagicListener;
    private Context context;
    /* access modifiers changed from: private */
    public int selectedColorIndex;

    public MagicBrushAdapter(Context context2, BrushMagicListener brushMagicListener2) {
        this.context = context2;
        this.borderSize = SystemUtil.dpToPx(context2, 2);
        this.brushMagicListener = brushMagicListener2;
        drawBitmapModels = lstDrawBitmapModel(context2);
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1084R.C1089layout.magic_brush_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.magicBrush.setImageResource(drawBitmapModels.get(i).getMainIcon());
        if (this.selectedColorIndex == i) {
            viewHolder.magicBrush.setBorderWidth(this.borderSize);
        } else {
            viewHolder.magicBrush.setBorderWidth(0);
        }
    }

    public void setSelectedColorIndex(int i) {
        this.selectedColorIndex = i;
    }

    public int getItemCount() {
        return drawBitmapModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView magicBrush;

        ViewHolder(View view) {
            super(view);
            this.magicBrush = (CircleImageView) view.findViewById(C1084R.C1087id.magicBrush);
            this.magicBrush.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int unused = MagicBrushAdapter.this.selectedColorIndex = ViewHolder.this.getLayoutPosition();
                    MagicBrushAdapter.this.brushMagicListener.onMagicChanged(MagicBrushAdapter.drawBitmapModels.get(MagicBrushAdapter.this.selectedColorIndex));
                    MagicBrushAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    public static List<DrawBitmapModel> lstDrawBitmapModel(Context context2) {
        if (drawBitmapModels != null && !drawBitmapModels.isEmpty()) {
            return drawBitmapModels;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.f576b4));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.f577b5));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.f578b6));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.f579b7));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.f580b8));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.f581b9));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.b10));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.b11));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.b12));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.b13));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.b14));
        arrayList.add(Integer.valueOf(C1084R.C1086drawable.b15));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.butterfly, arrayList, true, context2));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(C1084R.C1086drawable.magic21));
        arrayList2.add(Integer.valueOf(C1084R.C1086drawable.magic22));
        arrayList2.add(Integer.valueOf(C1084R.C1086drawable.magic23));
        arrayList2.add(Integer.valueOf(C1084R.C1086drawable.magic24));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.heart_1, arrayList2, true, context2));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(Integer.valueOf(C1084R.C1086drawable.f582f1));
        arrayList3.add(Integer.valueOf(C1084R.C1086drawable.f582f1));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.f1_icon, arrayList3, true, context2));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(Integer.valueOf(C1084R.C1086drawable.f592s1));
        arrayList4.add(Integer.valueOf(C1084R.C1086drawable.f592s1));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.s1_icon, arrayList4, true, context2));
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(Integer.valueOf(C1084R.C1086drawable.f573b1));
        arrayList5.add(Integer.valueOf(C1084R.C1086drawable.f574b2));
        arrayList5.add(Integer.valueOf(C1084R.C1086drawable.f575b3));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.b1_icon, arrayList5, true, context2));
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add(Integer.valueOf(C1084R.C1086drawable.f583f3));
        arrayList6.add(Integer.valueOf(C1084R.C1086drawable.f587f7));
        arrayList6.add(Integer.valueOf(C1084R.C1086drawable.f585f5));
        arrayList6.add(Integer.valueOf(C1084R.C1086drawable.f586f6));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.f2_icon, arrayList6, true, context2));
        ArrayList arrayList7 = new ArrayList();
        arrayList7.add(Integer.valueOf(C1084R.C1086drawable.f588m1));
        arrayList7.add(Integer.valueOf(C1084R.C1086drawable.f589m2));
        arrayList7.add(Integer.valueOf(C1084R.C1086drawable.f590m3));
        arrayList7.add(Integer.valueOf(C1084R.C1086drawable.f591m4));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.bb2_icon, arrayList7, true, context2));
        ArrayList arrayList8 = new ArrayList();
        arrayList8.add(Integer.valueOf(C1084R.C1086drawable.ss1));
        arrayList8.add(Integer.valueOf(C1084R.C1086drawable.ss2));
        arrayList8.add(Integer.valueOf(C1084R.C1086drawable.ss3));
        arrayList8.add(Integer.valueOf(C1084R.C1086drawable.ss5));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.f3_icon, arrayList8, true, context2));
        ArrayList arrayList9 = new ArrayList();
        arrayList9.add(Integer.valueOf(C1084R.C1086drawable.s17));
        arrayList9.add(Integer.valueOf(C1084R.C1086drawable.s17));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.smile_icon1, arrayList9, true, context2));
        ArrayList arrayList10 = new ArrayList();
        arrayList10.add(Integer.valueOf(C1084R.C1086drawable.s21));
        arrayList10.add(Integer.valueOf(C1084R.C1086drawable.s21));
        drawBitmapModels.add(new DrawBitmapModel(C1084R.C1086drawable.smile_icon2, arrayList10, true, context2));
        return drawBitmapModels;
    }
}