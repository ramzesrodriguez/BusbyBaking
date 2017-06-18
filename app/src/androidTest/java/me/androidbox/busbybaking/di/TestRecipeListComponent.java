package me.androidbox.busbybaking.di;

import me.androidbox.busbybaking.recipieslist.RecipeListViewAndroidTest;

/**
 * Created by steve on 6/2/17.
 */
/*@Singleton
@Subcomponent(modules = {MockRecipeListModule.class})*/
public interface TestRecipeListComponent {
    void inject(RecipeListViewAndroidTest target);
}
