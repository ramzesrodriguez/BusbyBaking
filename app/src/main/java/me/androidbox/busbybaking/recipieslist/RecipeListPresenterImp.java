package me.androidbox.busbybaking.recipieslist;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.androidbox.busbybaking.model.Recipe;

/**
 * Created by steve on 5/27/17.
 */

public class RecipeListPresenterImp
        extends
        MvpBasePresenter<RecipeListViewContract>
        implements
        RecipeListPresenterContract, RecipeListModelContract.RecipeGetAllListener {

    private RecipeListModelContract recipeListModelContract;

    @Inject
    public RecipeListPresenterImp(@NonNull RecipeListModelContract recipeListModelContract) {
        this.recipeListModelContract = Preconditions.checkNotNull(recipeListModelContract);
    }

    @Override
    public void retrieveAllRecipes() {
        recipeListModelContract.getRecipesFromAPI(RecipeListPresenterImp.this);
    }

    @Override
    public void onRecipeGetAllSuccess(List<Recipe> recipeList) {
        if(isViewAttached()) {
            getView().displayRecipeData(recipeList);
        }
    }

    @Override
    public void onRecipeGetAllFailure(String errorMessage) {
        if(isViewAttached() && getView() != null) {
            getView().displayRecipeError(errorMessage);
        }
    }
}
