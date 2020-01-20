package com.yoekisoft.myapplication.nepro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.yoekisoft.myapplication.R;

public class ItemCustomList extends ArrayAdapter {
    private String[] ids;
    private String[] names;

    @SuppressLint("ResourceType")   /////////////////////////////
    public ItemCustomList(@NonNull Context context, String[] ids, String[] names) {
        super(context, R.id.itemlayoutid);
        this.ids=ids;
        this.names=names;
    }
}
