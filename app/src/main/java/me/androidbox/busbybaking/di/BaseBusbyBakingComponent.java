package me.androidbox.busbybaking.di;

import me.androidbox.busbybaking.recipieslist.RecipeListModelImp;

/**
 * Created by steve on 5/31/17.
 */

public interface BaseBusbyBakingComponent {
    void inject(RecipeListModelImp target);

    RecipeListComponent add(RecipeListModule recipeListModule);
}
