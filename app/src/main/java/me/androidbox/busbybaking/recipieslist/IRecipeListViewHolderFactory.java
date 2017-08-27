package me.androidbox.busbybaking.recipieslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by smason on 6/26/2017 AD.
 */
public interface IRecipeListViewHolderFactory {
    RecyclerView.ViewHolder createViewHolder(View parent);
}
