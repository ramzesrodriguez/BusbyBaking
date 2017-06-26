package me.androidbox.busbybaking.recipieslist;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by smason on 6/26/2017 AD.
 */
public interface RecipeListViewHolderFactory {
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent);
}
