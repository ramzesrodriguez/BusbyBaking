package me.androidbox.busbybaking.recipieslist;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import me.androidbox.busbybaking.model.Recipe;

/**
 * Created by steve on 5/27/17.
 */

public interface RecipeListViewContract extends MvpView {
    void displayRecipeData(List<Recipe> recipeList);
    void displayRecipeError(String error);
}
