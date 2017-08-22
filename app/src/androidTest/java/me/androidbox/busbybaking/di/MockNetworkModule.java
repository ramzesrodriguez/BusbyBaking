package me.androidbox.busbybaking.di;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.networkapi.RecipesAPI;

/**
 * Created by steve on 6/4/17.
 */
@Module
public class MockNetworkModule {
    @Singleton
    @Provides
    public RecipesAPI providesRecipeAPI() {
        return Mockito.mock(RecipesAPI.class);
    }
}
