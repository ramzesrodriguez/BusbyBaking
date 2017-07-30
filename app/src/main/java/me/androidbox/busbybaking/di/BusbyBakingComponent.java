package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Component;
import me.androidbox.busbybaking.recipeVideoSteps.RecipeVideoStepsView;
import me.androidbox.busbybaking.recipieslist.RecipeListView;

/**
 * Created by steve on 5/27/17.
 */
@Singleton
@Component(modules = {
        AndroidModule.class,
        NetworkModule.class,
        ExoPlayerModule.class,
        RecipeListModule.class})
public interface BusbyBakingComponent {
    void inject(RecipeVideoStepsView target);
    void inject(RecipeListView target);


    // RecipeListComponent add(RecipeListModule recipeListModule);
}
