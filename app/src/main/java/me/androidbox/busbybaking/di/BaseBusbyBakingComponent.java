package me.androidbox.busbybaking.di;

/**
 * Created by steve on 5/31/17.
 */

public interface BaseBusbyBakingComponent {
    void inject(BusbyBakingApplication target);

    RecipeListComponent add(RecipeListModule recipeListModule);
}
