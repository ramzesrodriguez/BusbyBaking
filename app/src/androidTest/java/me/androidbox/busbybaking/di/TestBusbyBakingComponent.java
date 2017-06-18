package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Component;
import me.androidbox.busbybaking.recipieslist.RecipeListViewAndroidTest;

/**
 * Created by steve on 5/31/17.
 */

@Singleton
@Component(modules = {
        MockAndroidModule.class,
        MockRecipeListModule.class,
        MockNetworkModule.class
})
public interface TestBusbyBakingComponent  {
    void inject(RecipeListViewAndroidTest target);
}

