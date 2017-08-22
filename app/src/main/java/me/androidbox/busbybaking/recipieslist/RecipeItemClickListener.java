package me.androidbox.busbybaking.recipieslist;

import android.content.Context;

import me.androidbox.busbybaking.model.Recipe;

/**
 * Created by steve on 6/10/17.
 */

public interface RecipeItemClickListener {
    void onRecipeItemClick(Recipe recipe, Context context);
}
