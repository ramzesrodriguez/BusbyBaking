package me.androidbox.busbybaking.di;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;
import timber.log.Timber;

/**
 * Created by steve on 6/4/17.
 */
@Module
public class MockNetworkModule {
    @Singleton
    @Provides
    public RecipesAPI providesRecipeAPI() {
        Timber.d("mock(RecipeAPI.class)");
        return Mockito.mock(RecipesAPI.class);
    }

}
