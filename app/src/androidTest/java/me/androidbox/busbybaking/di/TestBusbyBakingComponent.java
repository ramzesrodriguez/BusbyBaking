package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Component;
import me.androidbox.busbybaking.recipieslist.RecipeListViewAndroidTest;

/**
 * Created by steve on 5/31/17.
 */

@Singleton
@Component(modules = {
        MockRecipeListModule.class,
        MockNetworkModule.class,
        MockAndroidModule.class
})
public interface TestBusbyBakingComponent extends RecipeListComponent {
    void inject(RecipeListViewAndroidTest target);
}

