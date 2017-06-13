package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Component;
import me.androidbox.busbybaking.recipeVideoSteps.RecipeVideoStepsView;

/**
 * Created by steve on 5/27/17.
 */
@Singleton
@Component(modules = {
        AndroidModule.class,
        NetworkModule.class,
        ExoPlayerModule.class})
public interface BusbyBakingComponent {
    void inject(RecipeVideoStepsView target);

    RecipeListComponent add(RecipeListModule recipeListModule);
}
