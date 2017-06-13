package me.androidbox.busbybaking.di;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterImp;

/**
 * Created by steve on 5/31/17.
 */
@Module
public class MockRecipeListModule {
    @Singleton
    @Provides
    public RecipeListModelContract providesRecipeListModel() {
      return Mockito.mock(RecipeListModelContract.class);
    }

    @Singleton
    @Provides
    public RecipeListPresenterContract providesRecipeListPresenter() {
        return Mockito.mock(RecipeListPresenterImp.class);
    }
}
