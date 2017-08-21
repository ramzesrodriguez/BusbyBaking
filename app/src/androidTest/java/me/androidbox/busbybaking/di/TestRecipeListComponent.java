package me.androidbox.busbybaking.di;

import dagger.Subcomponent;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.recipieslist.RecipeListViewAndroidTest;

/**
 * Created by steve on 7/28/17.
 */

@RecipeListScope
@Subcomponent(
        modules = MockRecipeListModule.class)
public interface TestRecipeListComponent extends RecipeListComponent {
    void inject(RecipeListViewAndroidTest target);
}
