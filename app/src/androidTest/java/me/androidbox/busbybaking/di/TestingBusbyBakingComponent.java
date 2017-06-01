package me.androidbox.busbybaking.di;



import javax.inject.Singleton;

import dagger.Component;
import me.androidbox.busbybaking.recipieslist.RecipeListModelImpTest;

/**
 * Created by steve on 5/31/17.
 */
@Singleton
@Component(modules = {MockRecipeListModule.class})
public interface TestingBusbyBakingComponent extends BaseBusbyBakingComponent {
    void inject(RecipeListModelImpTest target);
}
