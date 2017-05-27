package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterImp;

/**
 * Created by steve on 5/27/17.
 */
@Module
public class PresenterModules {
    @Singleton
    @Provides
    public RecipeListPresenterContract providesRecipeListPresenter() {
        return new RecipeListPresenterImp();
    }
}
