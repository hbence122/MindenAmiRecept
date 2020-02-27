package com.example.mindenamirecept;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KategoriaVH extends RecyclerView.ViewHolder {

    View katView;

    public KategoriaVH(@NonNull View itemView) {
        super(itemView);

        katView = itemView;
    }

    public void setDetails(Context context, String receptKat) {
        TextView mTitleTv = katView.findViewById(R.id.rKategoriaTv);


        mTitleTv.setText(receptKat);


    }
}
