package me.androidbox.busbybaking.di;

import dagger.Subcomponent;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.recipieslist.RecipeListModelImpTest;

/**
 * Created by steve on 6/2/17.
 */
@RecipeListScope
@Subcomponent(modules = {MockRecipeListModule.class})
public interface TestRecipeListComponent {
    void inject(RecipeListModelImpTest target);
}
