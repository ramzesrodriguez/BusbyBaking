package me.androidbox.busbybaking.recipieslist;

import android.content.Context;
import android.content.Intent;

import me.androidbox.busbybaking.model.Recipe;
import me.androidbox.busbybaking.recipe.Henson;

/**
 * Created by steve on 8/1/17.
 */

public class RecipeItemClickListenerImp implements RecipeItemClickListener {
    @Override
    public void onRecipeItemClick(Recipe recipe, Context context) {
        final Intent intent = Henson.with(context)
                .gotoRecipeDetailActivity()
                .recipe(recipe)
                .build();

        context.startActivity(intent);
    }
}
