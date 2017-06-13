package me.androidbox.busbybaking.di;

import me.androidbox.busbybaking.recipieslist.RecipeListView;

/**
 * Created by steve on 5/31/17.
 */

public interface BaseBusbyBakingComponent {
    void inject(RecipeListView target);

//    RecipeListComponent add(RecipeListModule recipeListModule);
}
