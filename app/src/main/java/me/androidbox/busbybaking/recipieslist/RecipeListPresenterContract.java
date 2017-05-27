package me.androidbox.busbybaking.recipieslist;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by steve on 5/27/17.
 */

public interface RecipeListPresenterContract extends MvpView {
    void retrieveAllRecipes();
}
