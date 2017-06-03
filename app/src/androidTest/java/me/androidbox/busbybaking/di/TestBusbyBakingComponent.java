package me.androidbox.busbybaking.di;


import javax.inject.Singleton;

import dagger.Component;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.recipieslist.RecipeListModelImpTest;
import me.androidbox.busbybaking.recipieslist.RecipeListView;

/**
 * Created by steve on 5/31/17.
 */

@Singleton
@Component(modules = {MockAndroidModule.class})
public interface TestBusbyBakingComponent extends BusbyBakingComponent {
    void inject(RecipeListModelImpTest target);
}
