package me.androidbox.busbybaking.recipieslist.di;


import javax.inject.Singleton;

import dagger.Component;
import me.androidbox.busbybaking.recipieslist.RecipeListModelImpTest;

/**
 * Created by steve on 6/24/17.
 */
@Singleton
@Component(modules = MockRecipeSchedulersModule.class)
public interface TestBusbyComponent {
    void inject(RecipeListModelImpTest target);
}
