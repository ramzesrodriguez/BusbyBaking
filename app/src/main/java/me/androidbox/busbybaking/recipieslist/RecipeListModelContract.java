package me.androidbox.busbybaking.recipieslist;

import java.util.List;

import me.androidbox.busbybaking.model.Recipe;

/**
 * Created by steve on 5/27/17.
 */

public interface RecipeListModelContract {

    interface RecipeGetAllListener {
        void onRecipeGetAllSuccess(List<Recipe> recipeList);
        void onRecipeGetAllFailure(String errorMessage);
    }
    void getRecipesFromAPI(RecipeGetAllListener recipeGetAllListener);

    void releaseResources();
}
