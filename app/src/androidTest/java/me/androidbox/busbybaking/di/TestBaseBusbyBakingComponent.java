package me.androidbox.busbybaking.di;

/**
 * Created by steve on 6/2/17.
 */

public interface TestBaseBusbyBakingComponent {
    void inject(TestBusbyBakingApplication target);

    TestRecipeListComponent add(MockRecipeListModule recipeListModule);
}
