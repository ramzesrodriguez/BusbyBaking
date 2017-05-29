package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Component;
import me.androidbox.busbybaking.recipieslist.RecipeListModuleImp;

/**
 * Created by steve on 5/27/17.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface BusbyBakingComponent {
    void inject(RecipeListModuleImp target);
}
