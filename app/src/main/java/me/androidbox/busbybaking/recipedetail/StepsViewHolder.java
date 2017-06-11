package me.androidbox.busbybaking.recipedetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by steve on 6/11/17.
 */

public class StepsViewHolder extends RecyclerView.ViewHolder {
    public StepsViewHolder(View itemView) {
        super(itemView);
    }

    public static StepsViewHolder newInstance(View itemView) {
        return new StepsViewHolder(itemView);
    }
}
