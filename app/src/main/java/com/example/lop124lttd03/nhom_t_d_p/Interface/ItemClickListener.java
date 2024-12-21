package com.example.lop124lttd03.nhom_t_d_p.Interface;

import android.view.View;

import com.example.lop124lttd03.nhom_t_d_p.Model.Photo;

public interface ItemClickListener {
    void OnItemClick(Photo photo);

    void onclick(View view, int pos, boolean isLongClick);
}
