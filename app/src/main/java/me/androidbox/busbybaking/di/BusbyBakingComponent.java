package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by steve on 5/27/17.
 */
@Singleton
@Component(modules = {
        AndroidModule.class,
        NetworkModule.class})
public interface BusbyBakingComponent {

    RecipeListComponent add(RecipeListModule recipeListModule);
}
