package me.androidbox.busbybaking.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import me.androidbox.busbybaking.recipieslist.RecipeItemClickListener;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;
import me.androidbox.busbybaking.recipieslist.RecipeListModelImp;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterImp;

/**
 * Created by steve on 5/27/17.
 */
@Module
public class RecipeListModule {
    private Activity activity;

    public RecipeListModule() {
    }

    public RecipeListModule(Activity activity) {
        this.activity = activity;
    }

    @RecipeListScope
    @Provides
    public RecipeItemClickListener providesRecipeListMainActivity() {
        return (RecipeItemClickListener)this.activity;
    }

    @RecipeListScope
    @Provides
    public RecipeListModelContract providesRecipeListModel(RecipesAPI recipesAPI) {
        return new RecipeListModelImp(recipesAPI);
    }

    @RecipeListScope
    @Provides
    public RecipeListPresenterContract providesRecipeListPresenter(RecipeListModelContract recipeListModelContract) {
        return new RecipeListPresenterImp(recipeListModelContract);
    }
}
