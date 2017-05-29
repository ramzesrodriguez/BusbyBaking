package me.androidbox.busbybaking.di;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterImp;

/**
 * Created by steve on 5/27/17.
 */
@Module
public class RecipeListPresenterModule {
    @RecipeListScope
    @Provides
    public RecipeListPresenterContract providesRecipeListPresenter() {
        return new RecipeListPresenterImp();
    }
}
