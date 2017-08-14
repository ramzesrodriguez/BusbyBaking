package me.androidbox.busbybaking.di;

import dagger.Subcomponent;
import me.androidbox.busbybaking.recipieslist.RecipeListViewAndroidTest;

/**
 * Created by steve on 7/28/17.
 */

@Subcomponent(
        modules = MockRecipeListModule.class)
public interface TestRecipeListComponent {
    void inject(RecipeListViewAndroidTest target);
}
