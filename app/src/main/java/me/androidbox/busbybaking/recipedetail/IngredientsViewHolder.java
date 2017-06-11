package me.androidbox.busbybaking.recipedetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by steve on 6/11/17.
 */

public class IngredientsViewHolder extends RecyclerView.ViewHolder {
    public IngredientsViewHolder(View itemView) {
        super(itemView);
    }

    public static IngredientsViewHolder newInstance(View itemView) {
        return new IngredientsViewHolder(itemView);
    }
}
