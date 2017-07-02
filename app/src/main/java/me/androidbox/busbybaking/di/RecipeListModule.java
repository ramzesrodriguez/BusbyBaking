package me.androidbox.busbybaking.di;

import android.app.Activity;

import java.util.Map;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import me.androidbox.busbybaking.adapters.RecipeAdapter;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import me.androidbox.busbybaking.recipieslist.RecipeItemClickListener;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;
import me.androidbox.busbybaking.recipieslist.RecipeListModelImp;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterImp;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;
import me.androidbox.busbybaking.recipieslist.RecipeSchedulers;
import me.androidbox.busbybaking.utils.Constants;

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
    RecipeItemClickListener providesRecipeListMainActivity() {
        return (RecipeItemClickListener)this.activity;
    }

    @RecipeListScope
    @Provides
    RecipeListModelContract providesRecipeListModel(RecipesAPI recipesAPI, RecipeSchedulers recipeSchedulers) {
        return new RecipeListModelImp(recipesAPI, recipeSchedulers);
    }

    @RecipeListScope
    @Provides
    RecipeListPresenterContract providesRecipeListPresenter(RecipeListModelContract recipeListModelContract) {
        return new RecipeListPresenterImp(recipeListModelContract);
    }

    @RecipeListScope
    @Provides
    RecipeAdapter providesRecipeAdapter(Map<Integer, RecipeListViewHolderFactory> viewHolderFactories) {
        return new RecipeAdapter(viewHolderFactories);
    }

    @Provides
    @IntoMap
    @IntKey(Constants.RECIPE_LIST)
    RecipeListViewHolderFactory provideRecipeListViewHolder() {
        return new RecipeListViewHolderFactory();
    }

}
