package me.androidbox.busbybaking.di;

import javax.inject.Singleton;

import dagger.Subcomponent;
import me.androidbox.busbybaking.recipieslist.RecipeListViewTest;

/**
 * Created by steve on 6/2/17.
 */
/*@Singleton
@Subcomponent(modules = {MockRecipeListModule.class})*/
public interface TestRecipeListComponent {
    void inject(RecipeListViewTest target);
}
