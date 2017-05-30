package me.androidbox.busbybaking.di;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;
import me.androidbox.busbybaking.recipieslist.RecipeListModelImp;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterImp;

/**
 * Created by steve on 5/27/17.
 */
@Module
public class RecipeListPresenterModule {
    @RecipeListScope
    @Provides
    public RecipeListPresenterContract providesRecipeListPresenter(RecipeListModelImp recipeListModelImp) {
        return new RecipeListPresenterImp(recipeListModelImp);
    }

    @RecipeListScope
    @Provides
    public RecipeListModelContract providesRecipeListModel(RecipesAPI recipesAPI) {
        return new RecipeListModelImp(recipesAPI);
    }
}
