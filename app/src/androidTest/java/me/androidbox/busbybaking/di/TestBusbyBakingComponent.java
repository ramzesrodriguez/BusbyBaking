package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Component;
import me.androidbox.busbybaking.recipieslist.RecipeListViewAndroidTest;

/**
 * Created by steve on 5/31/17.
 */

@Singleton
@Component(modules = {
        MockNetworkModule.class,
        MockAndroidModule.class,
        MockExoPlayerModule.class,
        MockRecipeListModule.class
})
public interface TestBusbyBakingComponent extends BusbyBakingComponent{
    void inject(RecipeListViewAndroidTest target);

    // TestRecipeListComponent add(MockRecipeListModule mockRecipeListModule);
}

