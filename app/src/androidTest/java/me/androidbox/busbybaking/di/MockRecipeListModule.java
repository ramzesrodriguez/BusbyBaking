package me.androidbox.busbybaking.di;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.adapters.RecipeAdapter;
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
    RecipeListModelContract providesRecipeListModel() {
      return Mockito.mock(RecipeListModelContract.class);
    }

    @Singleton
    @Provides
    RecipeListPresenterContract providesRecipeListPresenter() {
        return Mockito.mock(RecipeListPresenterImp.class);
    }

    @Singleton
    @Provides
    RecipeAdapter providesRecipeAdapter() {
        return Mockito.mock(RecipeAdapter.class);
    }

    /*

    @RecipeListScope
    @Provides
    RecipeAdapter providesRecipeAdapter(Map<Integer, RecipeListViewHolderFactory> viewHolderFactories) {
        return new RecipeAdapter(activity, viewHolderFactories);
    }
*/

}
