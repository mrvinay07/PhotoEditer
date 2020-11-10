package com.teamvinay.newphotoediter.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.C1084R;

public class AddTextAdapter extends RecyclerView.Adapter<AddTextAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<TextFunction> textFunctions;

    AddTextAdapter(@NonNull Context context, @NonNull List<TextFunction> list) {
        this.inflater = LayoutInflater.from(context);
        this.textFunctions = list;
    }

    public AddTextAdapter(@NonNull Context context) {
        this(context, getListTextFunctions(context));
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this.inflater.inflate(C1084R.C1089layout.bottom_toolbar_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.item.setBackground(this.textFunctions.get(i).drawable);
    }

    public int getItemCount() {
        return this.textFunctions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View item;

        public ViewHolder(View view) {
            super(view);
            this.item = view.findViewById(C1084R.C1087id.bottomItemIcon);
        }
    }

    public static List<TextFunction> getListTextFunctions(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TextFunction(context.getResources().getDrawable(C1084R.C1086drawable.img_keyboard)));
        arrayList.add(new TextFunction(context.getResources().getDrawable(C1084R.C1086drawable.img_text)));
        arrayList.add(new TextFunction(context.getResources().getDrawable(C1084R.C1086drawable.img_color)));
        arrayList.add(new TextFunction(context.getResources().getDrawable(C1084R.C1086drawable.img_edit)));
        arrayList.add(new TextFunction(context.getResources().getDrawable(C1084R.C1086drawable.img_confirm)));
        return arrayList;
    }

    static class TextFunction {
        Drawable drawable;

        public TextFunction(Drawable drawable2) {
            this.drawable = drawable2;
        }
    }
}