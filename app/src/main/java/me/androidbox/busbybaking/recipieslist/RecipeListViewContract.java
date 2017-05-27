package me.androidbox.busbybaking.recipieslist;

import java.util.List;

import me.androidbox.busbybaking.model.Recipe;

/**
 * Created by steve on 5/27/17.
 */

public interface RecipeListViewContract {
    void displayData(List<Recipe> recipeList);
    void displayError(String error);
}
