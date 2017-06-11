package me.androidbox.busbybaking.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by steve on 6/11/17.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void populateData(List<T> data);
}
