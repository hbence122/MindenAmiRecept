package com.example.mindenamirecept;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder  extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

    }

    public void setDetails(Context context, String receptNev, String receptLeiras, String image){
        TextView mTitleTv = mView.findViewById(R.id.rTitleTv);
        TextView mDetailTv = mView.findViewById(R.id.Leirastxt);
        ImageView mImageTv = mView.findViewById(R.id.rimageView);

        mTitleTv.setText(receptNev);
        mDetailTv.setText(receptLeiras);
        Picasso.get().load(image).into(mImageTv);
    }
}
