package me.androidbox.busbybaking.di;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import me.androidbox.busbybaking.adapters.RecipeAdapter;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import me.androidbox.busbybaking.recipieslist.MainActivity;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;
import me.androidbox.busbybaking.recipieslist.RecipeListModelImp;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterImp;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;
import me.androidbox.busbybaking.recipieslist.RecipeSchedulers;
import me.androidbox.busbybaking.utils.Constants;
import timber.log.Timber;

/**
 * Created by steve on 5/27/17.
 */
@Module
public class RecipeListModule {
    private MainActivity activity;

    public RecipeListModule() {}

    public RecipeListModule(MainActivity activity) {
        this.activity = activity;
    }

    @Singleton
    @Provides
    MainActivity providesRecipeListMainActivity() {
        return this.activity;
    }

    @Singleton
    @Provides
    RecipeListModelContract providesRecipeListModel(RecipesAPI recipesAPI, RecipeSchedulers recipeSchedulers) {
        Timber.d("providesRecipeListModel");
        return new RecipeListModelImp(recipesAPI, recipeSchedulers);
    }

    @Singleton
    @Provides
    RecipeListPresenterContract providesRecipeListPresenter(RecipeListModelContract recipeListModelContract) {
        return new RecipeListPresenterImp(recipeListModelContract);
    }

    @Singleton
    @Provides
    RecipeAdapter providesRecipeAdapter(Map<Integer, RecipeListViewHolderFactory> viewHolderFactories) {
        return new RecipeAdapter(activity, viewHolderFactories);
    }

    @Singleton
    @Provides
    @IntoMap
    @IntKey(Constants.RECIPE_LIST)
    RecipeListViewHolderFactory provideRecipeListViewHolder() {
        return new RecipeListViewHolderFactory();
    }

}
