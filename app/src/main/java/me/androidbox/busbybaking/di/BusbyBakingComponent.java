package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by steve on 5/27/17.
 */
@Singleton
@Component(modules = {NetworkModule.class, AndroidModule.class})
public interface BusbyBakingComponent {
    void inject(BusbyBakingApplication target);

    RecipeListComponent add(RecipeListPresenterModule recipeListPresenterModule);
}
