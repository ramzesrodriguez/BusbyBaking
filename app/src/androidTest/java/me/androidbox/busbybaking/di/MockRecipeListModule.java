package me.androidbox.busbybaking.di;

import java.util.Map;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import me.androidbox.busbybaking.adapters.RecipeAdapter;
import me.androidbox.busbybaking.di.scopes.RecipeListScope;
import me.androidbox.busbybaking.networkapi.RecipesAPI;
import me.androidbox.busbybaking.recipieslist.RecipeItemClickListener;
import me.androidbox.busbybaking.recipieslist.RecipeItemClickListenerImp;
import me.androidbox.busbybaking.recipieslist.RecipeListModelContract;
import me.androidbox.busbybaking.recipieslist.RecipeListModelImp;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterContract;
import me.androidbox.busbybaking.recipieslist.RecipeListPresenterImp;
import me.androidbox.busbybaking.recipieslist.RecipeListViewHolderFactory;
import me.androidbox.busbybaking.recipieslist.RecipeSchedulers;
import me.androidbox.busbybaking.utils.Constants;
import timber.log.Timber;

/**
 * Created by steve on 5/31/17.
 */
@Module
public class MockRecipeListModule {
    @RecipeListScope
    @Provides
    RecipeListModelContract providesRecipeListModel(RecipesAPI recipesAPI, RecipeSchedulers recipeSchedulers) {
        Timber.d("mock(RecipeListModelContract");
        return new RecipeListModelImp(recipesAPI, recipeSchedulers);
    }

    @RecipeListScope
    @Provides
    RecipeListPresenterContract providesRecipeListPresenter(RecipeListModelContract recipeListModelContract) {
        return new RecipeListPresenterImp(recipeListModelContract);
    }

    @RecipeListScope
    @Provides
    RecipeItemClickListener providesRecipeItemClickListener() {
        return new RecipeItemClickListenerImp();
    }

    @RecipeListScope
    @Provides
    RecipeAdapter providesRecipeAdapter(RecipeItemClickListener recipeItemClickListener, Map<Integer, RecipeListViewHolderFactory> viewHolderFactories) {
        return new RecipeAdapter(recipeItemClickListener, viewHolderFactories);
    }

    @RecipeListScope
    @Provides
    @IntoMap
    @IntKey(Constants.RECIPE_LIST)
    RecipeListViewHolderFactory providesRecipeListViewHolder() {
        return new RecipeListViewHolderFactory();
    }
}
