package me.androidbox.busbybaking.di;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;

/**
 * Created by steve on 5/31/17.
 */
@Module
public class MockRecipeListModule {
    @RecipeListScope
    @Provides
    public RecipeListModelContract providesRecipeListModel() {
      return Mockito.mock(RecipeListModelContract.class);
    }
}
